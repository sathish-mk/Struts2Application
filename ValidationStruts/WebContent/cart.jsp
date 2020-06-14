<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="jquery-3.5.1.js"></script>
</head>
<body>
Welcome to cart <s:property value="id"></s:property>
<s:textfield name="comment" label="Add Comment" id="comment"/>
<button class="addcomment">Comment</button>
<div class="comments"></div>

<script>
$(document).ready(function(){
	$.ajax({
		url:"<s:url action='getComments' />",
		method:'post',
		data:{productId:1},
		dataType:'JSON',
		success:function(data){
			console.log(data);
		}
	});
	$.ajax({
		url:"<s:url action='getProductDetails' />",
		method:'post',
		dataType:'JSON',
		data:{id:"2"},
		success:function(data){
			console.log(data);
		}
	});
});
$('.addcomment').click(function(){
	$.ajax({
		url:"<s:url action='addComment' namespace='/' />",
		method:'post',
		data:{productId:1,comment:$('#comment').val()},
		success:function(data){
			console.log("success");
		}
	})
});
</script>
</body>
</html>