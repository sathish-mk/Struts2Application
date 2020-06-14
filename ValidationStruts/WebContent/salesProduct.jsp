<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
body{
overflow:auto;
}
#form{
visibility:hidden;
position:fixed;
top:20%;
left:30%;
height:auto;
border:2px solid rgba(220,220,220,1);
min-width:60%;

}
</style>
</head>
<body>
<s:select list="categories" headerKey="-1" headerValue="Category to lists"></s:select>
<button onclick="call()">showForm</button>
<button id="edit1">edit</button>
<div id="form">
	<s:form id="theForm" action="salesProductForm" namespace="/" enctype="multipart/form-data" method="post">
		
		<s:select label="select Category" headerKey="-1" headerValue="--select--" list="categories" name="choosenCategory" ></s:select>
		<s:file name="fileUpload" label="Upload Image" ></s:file>
		<s:textfield name="description" label="Describe the product"></s:textfield>
		<s:textfield name="quantity" label="Available Quantity"></s:textfield>
		<s:textfield name="price" value="%{price}" label="Price Per Unit Quantity" />
		<s:textfield name="shopLocation" label="Shop Location"></s:textfield>
		<button onclick="callAjax()">submit</button>
	</s:form>
	<s:textfield name="productName" label="Name of the product"></s:textfield>
	</div>
	<div id="createForm"></div>
	<div>
	yes herre it is
	</div>
	
	
<script type="text/javascript">
	function call(){
		document.getElementById("form").style.visibility="visible";
	}
	function callAjax(){
		
		var http_request=new XMLHttpRequest();
		http_request.onreadystatechange=function(){
			console.log("succes");
			
			console.log("nn");
			
		}
		
		http_request.open("POST","<s:url action='salesProductForm' namespace='/' />",true);
		http_request.send();
	}
	function edit(){
		var http_request=new XMLHttpRequest();
		
		http_request.onreadystatechange=function(){
			console.log("success");
			
		}
		http_request.open("GET","<s:url action='edit' />",true);
		http_request.send();
	}
</script>
<script>
$('#edit1').click(function(){
	window.location.href="<s:url action='edit' />";
});
	$.ajax({
		url:"<s:url action='edit' />",
		success:function(result){
			console.log("its crct");
			$("theForm").trigger('reset');
		}
	});

</script>
</body>
</html>