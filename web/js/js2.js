var wait=60;
var clock=false;
ClockBtn=function(btn){
    if (wait==0) {
        btn.innerHTML="获取验证码";
        btn.disabled=false;
        wait=60;
    }else{
        btn.disabled=true;
        btn.innerHTML=wait+"秒后可重新发送";
        wait--;
        setTimeout(function(){
            ClockBtn(btn)
        },1000)
    }
};
getpass=function(btn)
{

    // alert($("#phonenumber").val());
    if(clock)return;
    clock=true;
    $.ajax({
        url:"registeraction",
        type:"GET",
        dataType:"text",
        data:{
            act:"GetPassNum",
            phonenumber:$("#phonenumber").val(),
            invitenum:$("#invitenum").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            clock=false;
            alert(data);
            if(data=="验证码获取成功")
            {
                ClockBtn(btn);
            }

        }
    })
}
submitrespons=function()
{
    if(!($("#password1").val()==$("#password2").val()))
    {
        alert("密码不一致！");
        return;
    }
    if(clock)return;
    clock=true;
    // alert($("#phonenumber").val());
    $.ajax({
        url:"registeraction",
        type:"GET",
        // async:false,
        dataType:"text",
        data:{
            act:"UsePassNum",
            phonenumber:$("#phonenumber").val(),
            username:$("#username1").val(),
            password:$("#password1").val(),
            passnum:$("#passnum").val(),
            name:$("#name1").val(),
            invitenum:$("#invitenum").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            alert(data);

            clock=false;
            if(data=="注册成功")
            {
                window.location.href="comLogin";
            }
        }
    })
}
isPhoneNumber=function()
{
    // $("#tip1").html("<font color=\"green\">aaaa</font>");
    // alert("aaa");
    if($("#phonenumber").val().length!=11)
    {
        $("#tip4").html("<font color=\"green\"></font>");
        return;
    }
    $.ajax({
        url:"registeraction",
        type:"GET",
        dataType:"text",
        data:{
            act:"isPhoneNumber",
            phonenumber: $("#phonenumber").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            if(data=="true")
            {
                $("#tip4").html("<font color=\"green\">当前手机号支持获取验证码</font>");
            }
            else if(data=="false1")
            {
                $("#tip4").html("<font color=\"red\">此号码已经注册</font>");
            }
            else
            {

                $("#tip4").html("<font color=\"red\">当前手机号不支持获取验证码<a href='./PhoneNumberSupport' target=\"_Blank\">点击此处查看支持的号段</a></font>");
            }
        }
    })
}
isUserName=function()
{
    // $("#tip1").html("<font color=\"green\">aaaa</font>");
    // alert("aaa");
    if($("#username1").val()=="")
    {
        $("#tip1").html("<font color=\"green\"></font>");
        return;
    }
    $.ajax({
        url:"registeraction",
        type:"GET",
        dataType:"text",
        data:{
            act:"isUserName",
            username:$("#username1").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            if(data=="true")
            {
                $("#tip1").html("<font color=\"green\">用户名格式正确</font>");
            }
            else
            {
                $("#tip1").html("<font color=\"red\">用户名必须为大小写字母开头，并含有数字的8-15位字符</font>");
            }
        }
    })
}
isPassword=function()
{
    if($("#password1").val()=="")
    {
        $("#tip2").html("<font color=\"green\"></font>");
        return;
    }
    passwordequals();
    // $("#tip1").html("<font color=\"green\">aaaa</font>");
    // alert("aaa");
    $.ajax({
        url:"registeraction",
        type:"GET",
        dataType:"text",
        data:{
            act:"isPassword",
            password:$("#password1").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            if(data=="true")
            {
                $("#tip2").html("<font color=\"green\">密码格式正确</font>");
            }
            else
            {
                $("#tip2").html("<font color=\"red\">密码必须为大小写字母开头，并含有数字的8-15位字符</font>");
            }
        }
    })
}
passwordequals=function()
{
    if($("#password2").val()=="")
    {
        $("#tip3").html("<font color=\"red\"></font>");
    }
    else if($("#password1").val()==$("#password2").val())
    {
        $("#tip3").html("<font color=\"green\">密码相同</font>");
    }
    else
    {

        $("#tip3").html("<font color=\"red\">两次密码输入不一致</font>");
    }
}
commlogin=function(btn)
{
    btn.innerHTML="验证请求中..";
    btn.disabled=true;
    if(clock)return;
    clock=true;
    $.ajax({
        url:"login",
        type:"POST",
        dataType:"text",
        // async:false,
        data:{
            loginaction:"commonlogin",
            username:$("#username1").val(),
            password:$("#password1").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        //administrator
        //admin123456a
        success:function(data,textStatus){

            if(data=="true")
            {
                // alert("登陆成功");
                loginsuccessjmp();
            }
            else
            {
               alert("登陆失败");
            }
            clock=false;
            btn.innerHTML="登陆";
            btn.disabled=false;
        }
    })
}
var loginsuccessclock=false;
var loginsuccesstime=3;
loginsuccessjmp=function()
{
    if(loginsuccessclock)
    {
        alert(loginsuccessclock);
        return;

    }
    // alert(loginsuccessclock);
    loginsuccessclock=true;
    if(loginsuccesstime>=0)
    {
        $("#mainpanel1").html("<div style='margin-left: 30%'><h1>登陆成功,页面将在"+loginsuccesstime+"秒后跳转,或者<a href='index'>点击此处</a></h1></div>");
        loginsuccesstime--;
        setTimeout(function () {
            loginsuccessjmp();
        },1000);
    }
    else
    {
        window.location.href="index";
    }
    loginsuccessclock=false;
}
adminlogin=function()
{
    if(clock)return;
    clock=true;
    $.ajax({
        url:"login.action",
        type:"POST",
        // async:false,
        dataType:"text",
        data:{
            loginaction:"adminlogin",
            username:$("#username1").val(),
            password:$("#password1").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            if(data=="true")
            {
                alert("登陆成功");
                window.location.href="Hpage1";
            }
            else
            {
                alert("登陆失败");
            }
            clock=false;
        }
    })
}
loginout=function()
{
    if(clock)return;
    clock=true;
    $.ajax({
        url:"login.action",
        type:"POST",
        async:false,
        dataType:"text",
        data:{
            loginaction:"loginout"
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            if(data=="true")
            {
                alert("退出成功");
                // window.location.href="index";
                window.location.reload();
            }
            else
            {
                alert("登陆失败");
            }
            clock=false;
        }
    })
}
isName=function()
{
    $.ajax({
        url:"registeraction",
        type:"GET",
        dataType:"text",
        data:{
            act:"isName",
            name:$("#name1").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            if(data=="true")
            {
                $("#tip5").html("<font color=\"green\">昵称格式正确</font>");
            }
            else
            {
                $("#tip5").html("<font color=\"red\">昵称必须为2-15位字母数字或汉字</font>");
            }
        }
    })
}
isInviteNum=function()
{
    if($("#invitenum").val().length!=30)
    {
        $("#tip6").html("<font color=\"green\"></font>");
        return;
    }

    $.ajax({
        url:"registeraction",
        type:"GET",
        dataType:"text",
        data:{
            act:"isInviteNum",
            invitenum:$("#invitenum").val()
        },
        scriptCharset: 'utf-8',
        statusCode: {404: function() {
                alert('page not found'); }
        },
        success:function(data,textStatus){
            if(data=="true")
            {
                $("#tip6").html("<font color=\"green\">邀请码有效</font>");
            }
            else
            {
                $("#tip6").html("<font color=\"red\">无效的邀请码</font>");
            }
        }
    })
}
alertaaa=function () {
    alert("aaaa");
}