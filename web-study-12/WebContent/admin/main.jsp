<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shop Admin</title>
<link rel="stylesheet" href="admin/css/admin.css">
<script type="text/javascript">
	function worker_check() {
		if (document.frm.workid.value == "") {
			alert("아이디를 입력하세요.");
			return false;
		} else if (document.frm.workPw.value == "") {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div id="wrap">
		<header>
			<div id="logo">
				<a href="ShoppingServlet?command=admin_login_form"> 
				<img src="admin/images/bar_01.gif"
					style="float: left"> <img src="admin/images/text.gif">
				</a>
			</div>
		</header>
		<div class="clear"></div>
		<article>
			<div id="loginform">
				<form name="frm" method="post"
					action="ShoppingServlet?command=admin_login">
					<table>
						<tr>
							<td>아이디</td>
							<td><input type="text" name="workid" size="10"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="workPw" size="10"></td>
						</tr>
						<tr align="center">
							<td colspan="2"><input type="submit" class="btn"
								value="업무 로그인" onclick="return worker_check()"><br>
							<br>
								<h4 style="color: red">${message }</h4></td>
						</tr>
					</table>
				</form>
			</div>
		</article>
	</div>
</body>
</html>