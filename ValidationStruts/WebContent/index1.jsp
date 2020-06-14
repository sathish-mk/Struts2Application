<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session='true' %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.btnCancel{
 
  background-color:red;
  color: white;
  padding:5px;
  border:none;
  border-radius: 50%;
  cursor: pointer;
  margin-left:180px;
  opacity: 0.5;
}

ul li{
display:inline-block;
}
ul{
	float:right;
	margin-top:10vh;
}
ul li a,ul li button{
color:black;
height:30px;
text-decoration:none;
border:1px solid  blue;
padding:5px 20px;
}
ul li a:hover,ul li button:hover{
color:green;
background-color:rgba(0,255,0,0.2);
transition:0.6s ease;
}
.form-popup{
	display:none;
	position:fixed;
	width:25%;
	padding:20px;
	margin:100px 400px;
	border:3px solid #f1f1f1;
}
.form-container {
  max-width: 100%;
  max-height:300px;
  
  background-color: white;
}


.form-container input[type=text], .form-container input[type=password] {
  width: 100%;
  padding:15px;
  margin: 10px 0 10px 0;
  
  border: none;
  background: #f1f1f1;
}


.form-container input[type=text]:focus, .form-container input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}


.form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 115%;
  margin-bottom:10px;
  opacity: 0.8;
}


.btn:hover,.btnCancel:hover{
	opacity:1;
}
</style>

</head>
<body>
<s:if test="hasActionMessages()">
		<div style="float:center" id="error">
<script>
document.addEventListener('DOMContentLoad',function(){
	console.log("yess buddy");
	document.getElementById("register").style.display="block";
});
</script>		
			<s:actionmessage />
		</div>
		</s:if>
		<s:if test="hasActionErrors()">
		<div style="float:center" id="error">
		<script>
	document.addEventListener('DOMContentLoad',function(){
		console.log("yess buddy");
		document.getElementById("register").style.display="block";
	});
</script>
			<s:actionerror />
		</div>
		</s:if>
	<div align="center">
	<ul>
	
	<li><button onclick="register()">register</button></li>
	<li><button onclick="login()">Login</button></li>
	</ul>
	</div>
	<div id="register" class="form-popup">
	<span style="font-size:20px;">Register</span>
	<button onclick="cancel()" class="btnCancel">X</button>
		<s:form action="/registeration" cssClass="form-container">
		<s:textfield name="user" label="User Name" type="text" required="true"></s:textfield>
		<s:textfield name="email" label="Email" type="text" required="true"/>
		<s:textfield name="password" label="Password" type="password" required="true"/>
		<s:submit value="register" cssClass="btn" />
		</s:form>	
	</div>
	<div id="login" class="form-popup">
		<s:if test="hasActionErrors()">
		<div class="errors">
			<s:actionerror />
		</div>
		</s:if>
	<span style="font-size:20px;">Login</span>
	<button onclick="cancel()" class="btnCancel">X</button>
		<s:form action="/loginAuthentification" cssClass="form-container">
		<s:textfield name="loginEmail" label="Email" type="text" required="true"/>
		<s:textfield name="loginPassword" label="Password" type="password" required="true"/>
		<s:submit value="login" cssClass="btn" />
		</s:form>	
	</div>
	
	<script>
		function register(){
			document.getElementById("register").style.display="block";
			document.getElementById("login").style.display="none";
		}
		function cancel(){
			document.getElementById("register").style.display="none";
			document.getElementById("login").style.display="none";
		}
		function login(){
			document.getElementById("register").style.display="none";
			document.getElementById("login").style.display="block";
		}
	</script>
</body>
</html>