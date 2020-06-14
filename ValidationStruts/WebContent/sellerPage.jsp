<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body{
overflow:auto;
height:200%;
}
.a{
display:inline-block;
position:fixed;
marigin-top:50px;
}
.sell{

position:fixed;
margin-left:80%;
margin-top:10px;
top:0px;

}
</style>
</head>
<body>
<div class="a">
	
	<jsp:include page="success.jsp" />
	
	<div class="sell">
	<button id="seller">Sell on Cart</button>
	</div>
	</div>
	<script type="text/javascript">
		var actionButton=document.getElementById("seller");
		actionButton.addEventListener("click",function(){
			window.location.href="salesProductAction.action"
		})
	</script>
</body>
</html>