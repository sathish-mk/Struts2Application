<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lng="en-US">
<head>
<meta name="viewport" content="width=device-width">
<script src="jquery-3.5.1.js"></script>
<title>Web Development</title>
<link href="index.css" rel="stylesheet" />
<script>

$('document').ready(function(){
  $(".content").css({
'height':(($(".content1").height())+'px')
  });
});
</script>
</head>
<body>
<s:if test="hasActionMessages()">
	<div style="float:center" id="error">
		<script>
		document.addEventListener('DOMContentLoad',function(){
			document.getElementById("register").style.display="block";
		});
		</script>	
		<div style="color:white" class="logoutmessage">
		<s:actionmessage />
		<button onclick="call()">X</button>
		</div>
		
	</div>
</s:if>
<script>
function call(){
	$('.logoutmessage').hide();
}
</script>
    <header>
    
       <div class="main">
               <div class="logo">
                      <img src="images.png" />
               </div>
               <ul>
                   <li><a href="#">Home</a></li>
	<li><a href="javascript:void(0);" onclick="login()">Login</a></li>
	<li><a href="javascript:void(0);" onclick="register()">Register</a></li>
	<li><a href="#">Contact</a></li>
	<li><a href="#">About</a></li>
               </ul>
       </div>
       <div class="title">
            <h1> Web Development </h1>
      </div>
      <div class="button">
         <a href="#" class="btn1">Watch Video</a>
         <a href="#" class="btn2">Learn More</a>
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
     
    </header>
<br>
<div>

<div class="content"><img src="fruits.jpg" id="image" draggable="false" /></div>
<div class="content1"><h1>Fruits</h1>
<br><p>&emsp;&emsp;Many hundreds of fruits, including fleshy fruits (like apple, kiwifruit, mango, peach, pear, and watermelon) are commercially valuable as human food, eaten both fresh and as jams, marmalade and other preserves. Fruits are also used in manufactured foods (e.g., cakes, cookies, ice cream, muffins, or yogurt) or beverages, such as fruit juices (e.g., apple juice, grape juice, or orange juice) or alcoholic beverages (e.g., brandy, fruit beer, or wine). Fruits are also used for gift giving, e.g., in the form of Fruit Baskets and Fruit Bouquets.

Many "vegetables" in culinary parlance are botanical fruits, including bell pepper, cucumber, eggplant, green bean, okra, pumpkin, squash, tomato, and zucchini. Olive fruit is pressed for olive oil. Spices like allspice, black pepper, paprika, and vanilla are derived from berries.
<p>
<br><br>
</div>
</div>
<br>
<div>

<div class="content new" ><img src="vegetables.jpg" id="image" draggable="false" /></div>
<div class="content1 new1"><h1>Vegetables</h1>
<br><p>&emsp;&emsp;Many hundreds of fruits, including fleshy fruits (like apple, kiwifruit, mango, peach, pear, and watermelon) are commercially valuable as human food, eaten both fresh and as jams, marmalade and other preserves. Fruits are also used in manufactured foods (e.g., cakes, cookies, ice cream, muffins, or yogurt) or beverages, such as fruit juices (e.g., apple juice, grape juice, or orange juice) or alcoholic beverages (e.g., brandy, fruit beer, or wine). Fruits are also used for gift giving, e.g., in the form of Fruit Baskets and Fruit Bouquets.

Many "vegetables" in culinary parlance are botanical fruits, including bell pepper, cucumber, eggplant, green bean, okra, pumpkin, squash, tomato, and zucchini. Olive fruit is pressed for olive oil. Spices like allspice, black pepper, paprika, and vanilla are derived from berries.
<p>
</div>
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
