<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link href="../script/css/login.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="../script/css/radio-checkbox.css" media="screen" rel="stylesheet" type="text/css" />
    <script src="../script/js/jquery-2.1.1.min.js"></script>
    <script src="../script/js/login.js"></script>
	<script src="../script/js/jquery.cookie.js"></script>
	<script src="../script/js/base64.js"></script>
</head>
<body>
<div class="content">
    <div class="right"></div>
    <div class="main">
        <div class="img">
            <div class="float" style="margin-top: 210px;margin-right: 79px;">
                <div class="logo">
                    <div class="logo-content">
                        <i class="iconfont" style="font-size: 3.5rem;">&#xe657;</i>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
            <div  class="float"style="margin-top: 34px;margin-right: 80px;">
                <div class="title">
                    EBES
                </div>
            </div>
            <div class="clear"></div>
            <div  class="float"style="margin-top: 29px;margin-right: 80px;">
                <div class="line"></div>
            </div>
            <div class="clear"></div>
            <div  class="float"style="margin-top: 27px;margin-right: 80px;">
                <div class="layer">面单电子化管理系统</div>
            </div>
            <div class="clear"></div>
            <div  class="float"style="margin-top: 221px;margin-right: 80px;">
                <div class="foot">Copyright@1999-2017无锡市同步电子有限公司</div>
            </div>
        </div>
         <div class="login">
            <form id="form" name='f'   action="/jsp/login" method="POST" onsubmit = "return login();">
                <div class="login-title" id="message">
                    	输入您的用户名和密码<br>
                    	点击登录进入系统
                </div>
                 <div class="col-xs-12">   
                     <p id="session"style="text-align:center;color:red;display: none;">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>  
                     <%
                     session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new Exception(""));
                     %>
        				
                 </div> 
                <div style="margin-top: 59px;margin-left: 80px;">
                    <div class="input_content">
                        <input type="text" oninput="webChange()" id="Username" class="input" name='username'  placeholder="请输入用户名" autocomplete="on">
                        <i class="iconfont icon">&#xe892;</i>
                    </div>
                    <div class="input_content" style="margin-top: 40px;">
     					<input class="input"  type="password"  id="txtPwd" placeholder="请输入密码" style="display: none"/> 
                        <input id="PSD"  class="input" name='password' placeholder="请输入密码" type="password" autocomplete="off" autocomplete="new-password" >
                        <input type="hidden" name="submit" type="submit" value="Login"/>
                        <i class="iconfont icon">&#xe867;</i>
                    </div>
                    <div class="checkbox checkbox-login" style="margin-top: 40px;">
                        <input id="Remember" class="styled" type="checkbox">
                        <label for="Remember" class="mima">  记住密码
                        </label>
                    </div>
                    <div style="margin-top: 40px;">
                        <button  type="submit" class="button">登录<i class="iconfont">&#xe8e9;</i> </button>
                    </div>
                    <div style="margin-top: 20px;">
                    <p style="font-size: 13px;color: #999999;">说明：无法登录、忘记用户名或密码请联系连金平</p>
                    </div>
                </div>
            </form>
        </div>
        <div class="clear"></div>
        
    </div>

</div>
<script type="text/javascript">
$(function(){
 	var ss=$("#session").html();
 	if(ss=="Bad credentials"){
		$("#message").addClass("red"); // 追加样式 
		document.getElementById("message").innerHTML="密码错误";
	}else if(ss=="User is disabled"){
		$("#message").addClass("red"); // 追加样式 
		document.getElementById("message").innerHTML="您已被禁用";
	}
	else if(ss==""){
		$("#message").removeClass("red"); // 追加样式 
		document.getElementById("message").innerHTML="输入您的用户名和密码<br>点击登录进入系统";
	}else{
		$("#message").addClass("red"); // 追加样式 
		document.getElementById("message").innerHTML=ss;
	}  
 	
})
</script>
</body>
</html>