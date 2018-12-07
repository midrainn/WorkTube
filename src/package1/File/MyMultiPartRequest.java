package package1.File;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.LocalizedMessage;
import org.apache.struts2.dispatcher.multipart.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.LocalizedMessage;
public class MyMultiPartRequest extends AbstractMultiPartRequest {
    static final Logger LOG = LogManager.getLogger(JakartaMultiPartRequest.class);
    protected Map<String, List<FileItem>> files = new HashMap();
    protected Map<String, List<String>> params = new HashMap();

    public void parse(HttpServletRequest request, String saveDir) throws IOException {
        LocalizedMessage errorMessage;
        try {
            this.setLocale(request);
            this.processUpload(request, saveDir);
        } catch (FileUploadException var6) {
            LOG.warn("Request exceeded size limit!", var6);
            if (var6 instanceof FileUploadBase.SizeLimitExceededException) {
                FileUploadBase.SizeLimitExceededException ex = (FileUploadBase.SizeLimitExceededException)var6;
                errorMessage = this.buildErrorMessage(var6, new Object[]{ex.getPermittedSize(), ex.getActualSize()});
            } else {
                errorMessage = this.buildErrorMessage(var6, new Object[0]);
            }

            if (!this.errors.contains(errorMessage)) {
                this.errors.add(errorMessage);
            }
        } catch (Exception var7) {
            LOG.warn("Unable to parse request", var7);
            errorMessage = this.buildErrorMessage(var7, new Object[0]);
            if (!this.errors.contains(errorMessage)) {
                this.errors.add(errorMessage);
            }
        }

    }

    protected void processUpload(HttpServletRequest request, String saveDir) throws FileUploadException, UnsupportedEncodingException {
        if (ServletFileUpload.isMultipartContent(request)) {
            Iterator i$ = this.parseRequest(request, saveDir).iterator();

            while(i$.hasNext()) {
                FileItem item = (FileItem)i$.next();
                LOG.debug("Found file item: [{}]", item.getFieldName());
                if (item.isFormField()) {
                    this.processNormalFormField(item, request.getCharacterEncoding());
                } else {
                    this.processFileField(item);
                }
            }
        }

    }

    protected void processFileField(FileItem item) {
        LOG.debug("Item is a file upload");
        if (item.getName() != null && item.getName().trim().length() >= 1) {
            Object values;
            if (this.files.get(item.getFieldName()) != null) {
                values = (List)this.files.get(item.getFieldName());
            } else {
                values = new ArrayList();
            }

            ((List)values).add(item);
            this.files.put(item.getFieldName(), (List)values);
        } else {
            LOG.debug("No file has been uploaded for the field: {}", item.getFieldName());
        }
    }

    protected void processNormalFormField(FileItem item, String charset) throws UnsupportedEncodingException {
        LOG.debug("Item is a normal form field");
        Object values;
        if (this.params.get(item.getFieldName()) != null) {
            values = (List)this.params.get(item.getFieldName());
        } else {
            values = new ArrayList();
        }

        if (charset != null) {
            ((List)values).add(item.getString(charset));
        } else {
            ((List)values).add(item.getString());
        }

        this.params.put(item.getFieldName(), (List)values);
        item.delete();
    }

    protected List<FileItem> parseRequest(HttpServletRequest servletRequest, String saveDir) throws FileUploadException {
        DiskFileItemFactory fac = this.createDiskFileItemFactory(saveDir);
        ServletFileUpload upload = this.createServletFileUpload(fac);
        upload.setProgressListener(new FileUploadListener(servletRequest));
//        upload.setFileSizeMax(maxsize);
        return upload.parseRequest(this.createRequestContext(servletRequest));
    }

    protected ServletFileUpload createServletFileUpload(DiskFileItemFactory fac) {
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setSizeMax(this.maxSize);
        return upload;
    }

    protected DiskFileItemFactory createDiskFileItemFactory(String saveDir) {
        DiskFileItemFactory fac = new DiskFileItemFactory();
        fac.setSizeThreshold(0);
        if (saveDir != null) {
            fac.setRepository(new File(saveDir));
        }

        return fac;
    }

    public Enumeration<String> getFileParameterNames() {
        return Collections.enumeration(this.files.keySet());
    }

    public String[] getContentType(String fieldName) {
        List<FileItem> items = (List)this.files.get(fieldName);
        if (items == null) {
            return null;
        } else {
            List<String> contentTypes = new ArrayList(items.size());
            Iterator i$ = items.iterator();

            while(i$.hasNext()) {
                FileItem fileItem = (FileItem)i$.next();
                contentTypes.add(fileItem.getContentType());
            }

            return (String[])contentTypes.toArray(new String[contentTypes.size()]);
        }
    }

    public UploadedFile[] getFile(String fieldName) {
        List<FileItem> items = (List)this.files.get(fieldName);
        if (items == null) {
            return null;
        } else {
            List<UploadedFile> fileList = new ArrayList(items.size());

            File storeLocation;
            for(Iterator i$ = items.iterator(); i$.hasNext(); fileList.add(new StrutsUploadedFile(storeLocation))) {
                FileItem fileItem = (FileItem)i$.next();
                storeLocation = ((DiskFileItem)fileItem).getStoreLocation();
                if (fileItem.isInMemory() && storeLocation != null && !storeLocation.exists()) {
                    try {
                        storeLocation.createNewFile();
                    } catch (IOException var8) {
                        LOG.error("Cannot write uploaded empty file to disk: {}", storeLocation.getAbsolutePath(), var8);
                    }
                }
            }

            return (UploadedFile[])fileList.toArray(new UploadedFile[fileList.size()]);
        }
    }

    public String[] getFileNames(String fieldName) {
        List<FileItem> items = (List)this.files.get(fieldName);
        if (items == null) {
            return null;
        } else {
            List<String> fileNames = new ArrayList(items.size());
            Iterator i$ = items.iterator();

            while(i$.hasNext()) {
                FileItem fileItem = (FileItem)i$.next();
                fileNames.add(this.getCanonicalName(fileItem.getName()));
            }

            return (String[])fileNames.toArray(new String[fileNames.size()]);
        }
    }

    public String[] getFilesystemName(String fieldName) {
        List<FileItem> items = (List)this.files.get(fieldName);
        if (items == null) {
            return null;
        } else {
            List<String> fileNames = new ArrayList(items.size());
            Iterator i$ = items.iterator();

            while(i$.hasNext()) {
                FileItem fileItem = (FileItem)i$.next();
                fileNames.add(((DiskFileItem)fileItem).getStoreLocation().getName());
            }

            return (String[])fileNames.toArray(new String[fileNames.size()]);
        }
    }

    public String getParameter(String name) {
        List<String> v = (List)this.params.get(name);
        return v != null && v.size() > 0 ? (String)v.get(0) : null;
    }

    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(this.params.keySet());
    }

    public String[] getParameterValues(String name) {
        List<String> v = (List)this.params.get(name);
        return v != null && v.size() > 0 ? (String[])v.toArray(new String[v.size()]) : null;
    }

    protected RequestContext createRequestContext(final HttpServletRequest req) {
        return new RequestContext() {
            public String getCharacterEncoding() {
                return req.getCharacterEncoding();
            }

            public String getContentType() {
                return req.getContentType();
            }

            public int getContentLength() {
                return req.getContentLength();
            }

            public InputStream getInputStream() throws IOException {
                InputStream in = req.getInputStream();
                if (in == null) {
                    throw new IOException("Missing content in the request");
                } else {
                    return req.getInputStream();
                }
            }
        };
    }

    public void cleanUp() {
        Set<String> names = this.files.keySet();
        Iterator i$ = names.iterator();

        while(i$.hasNext()) {
            String name = (String)i$.next();
            List<FileItem> items = (List)this.files.get(name);
            i$ = items.iterator();

            while(i$.hasNext()) {
                FileItem item = (FileItem)i$.next();
                LOG.debug("Removing file {} {}", name, item);
                if (!item.isInMemory()) {
                    item.delete();
                }
            }
        }

    }

}
