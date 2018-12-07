var aaa=1;
var clock=false;
var clock1=false;
selectprogramtype=function (select) {
    $("#programtype").html($(select).text());
    // alert("aaa");
}

test=function () {
    alert(!$("#checkbox1").is(":checked"));
    // alert($("#checkbox1").val());
    return;
}
subfile=function () {
    if(!$("#checkbox1").is(":checked"))
    {
        alert("请确认阅读警告");
        return;
    }
    if(clock)return;
    clock=true;
    getuploadstatus();
    var fromdata=new FormData();
    fromdata.append("type",$("#programtype").text());
    fromdata.append("workname",$("#workname").val());
    var file=$("#faceimage").prop("files")[0];
    fromdata.append("faceimage",file);
    file=$("#showimage1").prop("files")[0];
    fromdata.append("showimage1",file);
    file=$("#showimage2").prop("files")[0];
    fromdata.append("showimage2",file);
    file=$("#showimage3").prop("files")[0];
    fromdata.append("showimage3",file);
    file=$("#program").prop("files")[0];
    fromdata.append("program",file);
    file=$("#video").prop("files")[0];
    fromdata.append("video",file);
    $.ajax({
        url:"uploadfile",
        type:"POST",
        dataType:"text",
        contentType: false,
        processData: false,
        data:fromdata,
        // data:{file2FileName:"111"},
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
            alert(data);
            clock=false;
            // fromdata=null;
        },
        error:function () {
            // alert("异常");
            clock=false;
        }
    })
}

getuploadstatus=function () {
    // $("#uploadstatus").html("<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\"60\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "+aaa+"%;\">\n" +
    //     aaa +
    //     "            </div>");
    $("#uploadstatus").css("display","block");
    $.ajax({
        url:"uploadstatus",
        type:"POST",
        dataType:"text",
        data:{
            act:"getstatus"
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data){
            // alert(data);
            $("#uploadstatus").html("<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\""+data+"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "+data+"%;\">\n" +
                data +
                "%            </div>");
            aaa=data;
            if(aaa<100)
            {
                setTimeout(function () {
                    getuploadstatus();
                },500)
            }
            else
            {
                aaa=0;
                $("#uploadstatus").html("<div class=\"progress-bar-success\" role=\"progressbar\" aria-valuenow=\"100\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 100%;\">\n" +
                    "文件上传完毕，请等待后台校验            </div>");
                aaa=data;
                if(!clock1)
                {
                    clock1=true;
                    setTimeout(function () {
                        $("#uploadstatus").css("display","none");
                        clock1=false;
                    },5000);
                }
            }
        }
    })
}
