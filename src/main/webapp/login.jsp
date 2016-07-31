<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
	function buttonLogin() {
		var yztext = document.getElementById("exampleInputCAPTCHA1").value;
		var userName = document.getElementById("loginAccount").value;
		var password = document.getElementById("userPwd").value;

		$.post("http://localhost/dubbox-hatcher-provider/isLogin.do", {
			loginAccount : userName,
			userPwd : password,
			captcha : yztext
		}, function(data, status) {
			var result = data.result;
			$('#resultText').text(data.resultMsg);
			$('#myModal').modal('show');
			if(result==true){
				window.location.href='userMenu.jsp';
			}else{
				window.navigate("login.jsp");
			}
		});
	}
	$(function(){
        $('#kaptchaImage').click(function () { 
        	$(this).attr('src', 'kaptcha.jpg?' + Math.floor(Math.random()*100) ); })
    });
</script>
<style type="text/css">
.myStyle {
	display: block;
	width: 21%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	/* align:center */;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

/*body {
	background-color: #7bcfa6
}
*/
#7bcfa6
</style>
</head>
<body>
	
	<h2 align="center">
		<img alt="Responsive image" src="img/cvte.png"  class="img-circle" style="max-width: 10%;" />
		用户登录
	</h2>
		<hr style="height: 2px; border: none; border-top: 2px dotted #185598;" />
		<hr style="height: 5px; border: none; border-top: 5px ridge green;" />
		<div align="center" class="tree well">
			<form class="form-horizontal">
				<div class=" form-group ">
					<label for="exampleInputEmail1">账号</label> <input type="Text"
						class="myStyle" id="loginAccount" placeholder="account">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">密码</label> <input
						type="password" class="myStyle" id="userPwd"
						placeholder="Password">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">验证码 
					<img src="kaptcha.jpg" id="kaptchaImage" /> 
					<br>
					<small>Can't read the image? Click it to get a new one.</small>
					</br>
					</label> <input type="password" class="myStyle" id="exampleInputCAPTCHA1"
						placeholder="CAPTCHA">
				</div>
			</form>
			<button id="buttonLogin" class="btn btn-default"
				onclick="buttonLogin()">登录</button>
		</div>
		<hr style="height: 5px; border: none; border-top: 5px ridge green;" />
		
		
		<br/>
		<br/>
		<form class="form-horizontal  center-block" style="max-width: 50%;">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Account</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="inputEmail3" placeholder="loginAccount">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		  		<label for="inputEmail3" class="col-sm-2 control-label"></label>
				  <img class="col-sm-2 control-label" src="kaptcha.jpg" id="kaptchaImage" /><br/>
				  <small>Can't read the image? Click it to get a new one.</small>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Captcha</label>
		    <div class="col-sm-10">
		        <input type="text" class="form-control" id="inputEmail3" placeholder="Captcha">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Sign in</button>
		    </div>
		  </div>
	</form>
	
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	        <h4 class="modal-title">登陆提示：</h4>
	      </div>
	      <div class="modal-body">
	        <label id="resultText">结果</label>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>
</html>