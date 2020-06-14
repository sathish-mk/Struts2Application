<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
<script src="jquery-3.5.1.js"></script>
<link href="success.css" rel="stylesheet"></link>
</head>
<body>
<div class="sidebarMenu" id="sideMenu">
<div><span>HI</span>, <s:property value="loginEmail"></s:property></div>

<a href="#">profile</a>
<a href="javascript:void(0);" id="sellerRequest2" style="text-decoration:none;color:black;">sell on cart</a>
<a href="logout.action">logout</a>

</div>
	
	<button  id="sideMenu1">&#9776;</button>
	<button class="category" id="Fruits" >Fruits</button>
	<button class="category" id="vegetables" >Vegetables</button>
	<div id="just"></div>
	<div style="position:relative;top:200px;" id="load">this is the last chance for u plzz come quick</div>
<div class="newSeller nt" id="newSeller">
	<p>you are not seller yet....<p>
	<p>Do you want to send request to the admin to access as seller</p>
	<button id="yes">Yes</button>
	<button id="no">No</button>
</div>
<div class="newSeller new">
	<s:form>
		<s:textfield name="name" label="Name"/>
		<label for="email">Email<sup style="color:red;">*</sup></label>
		<s:textfield name="email" />
		<s:textfield name="description" label="About You"/>
	</s:form>
</div>
<script>
var dat;
console.log("doing");

function cart(x){
	console.log(x);
	alert("hello"+typeof(x));
	window.location.href="cart.action?id="+x;
}

	
function sellerRequest(){
	var http_request=new XMLHttpRequest();
	http_request.onreadystatechange=function(){
		var data=JSON.parse(this.responseText);
		console.log(data);
		console.log("request sent success");
	}
	http_request.open("GET","<s:url namespace='/' action='request' />",true);
	http_request.send();
}
function sideMenu(){
	document.getElementById("sideMenu").style.display="block";
	document.getElementById("sideMenu").style.transition=".5s ease";
}
function hide(){
	
	document.getElementById("sideMenu").style.display="none";
	document.getElementById("background").style.pointerEvents="auto";
}

function sellerRequest2(){
	document.getElementById('newSeller').style.display="block";
}
</script>
<script>
$(document).mouseup(function(e){
	var container=$('.sidebarMenu');

	if(!container.is(e.target)&&container.has(e.target).length===0){
		container.hide();
	}
});
$('#yes').on('click',function(){
	$('.new').css('display','block');
	$('#newSeller').css('display','none');
});
$('#no').on('click',function(){
	$('#newSeller').css('display','none');
	
});
$('#sideMenu1').on('click',function(){

	$('#sideMenu').toggle('slide');
	$('#new').hide();

});
$('#sellerRequest2').on('click',function(){
	$('#just').css('height','2000px');
	console.log($('#just').outerHeight());
	
	$('body').css('overflow-y','scroll');
	$.ajax({
		type:'GET',
		url:"<s:url action='request' namespace='/' />",
		dataType:'json',
		success:function(data){
			console.log(data.results[0].result);
			
			if(data.results[0].result=='exist'){
				//window.location.href="<s:url action='salesProductAction' />";
			}
			else if(data.results[0].result=='sent'){
				
			}
			else{}
				$('#sideMenu').hide();
				$('.new').hide();
				$('#newSeller').toggle('slide');
				$('#load').load(location.href+' #load');
			
		}
		});


});var data;
$(document).ready(function(){
	$(document).ready(getProducts('Fruits'));
	function getProducts(x){
		return function(e){
		console.log(x);
		$.ajax({
			url:"<s:url action='Getme' />",
			method:'post',
			data:{id:x},
			dataType:'JSON',
			success:function(dat){
				data=dat;
				console.log(data.length);
				var i=0;
				var display="";
				while(i<data.length){
					display+="<div class='image_content'  onclick='cart((data["+i+"].id))'"+"  ><img class='imag' src='"+data[i].path+"' /><div class='content'><span>"+data[i].name+"</span></div></div>";
					i++;
					}
				$('#just').html(display);

			}
		})
		}
	}
$('.category').each(function(){
	$(this).on('click',getProducts(this.id));
	});
	

	
$(document).on('mouseover','.image_content',function(){
	var container=$('.image_content');
	$('.content').show();
$('.content').css("visibility","visible");

});
$(document).on('mouseout','.image_content',function(){
	$('.content').hide();
});
});

</script>
</body>
</html>