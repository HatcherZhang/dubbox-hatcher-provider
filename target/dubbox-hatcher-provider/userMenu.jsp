<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/zTreeStyle/metro.css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script src="js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<SCRIPT type="text/javascript">
	var setting = {
		data : {
			key : {
				name : "menuName",
			},
			simpleData : {
				enable : true,
				idKey : "menuCode",
				pIdKey : "parentMenuCode",
				rootPId : null
			},
			view : {
				fontCss : {
					color : "#ff0011",
					background : "blue"
				}
			}
		}
	};

	var treeNodes;

	$(document).ready(
		function() {
			$.get("http://localhost/dubbox-hatcher-provider/getUserMenus.do",
				function(data, status) {
					treeNodes = data;
					$.fn.zTree.init($("#treeDemo"),setting, treeNodes);
			});
	});
</SCRIPT>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<img alt="Responsive image" src="img/cvte.png" class="img-circle"
			style="max-width: 10%;" />
	</div>
	<div>
		<a href="login.jsp">点击此链接，退出登录。。。</a>
	</div>
	<div class="tree well">
		<h4>用户菜单展示：</h4>
		<br />
		<ul id="treeDemo" class="ztree" style="width: 560px; overflow: auto;"></ul>
	</div>
</body>
</html>