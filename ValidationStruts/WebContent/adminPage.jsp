<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	<button onclick="requestLists()">requestList</button>
	<button id="seller" onclick="userSellerList(this.id)">sellers</button>
	<button id="user"onclick="userSellerList(this.id)">Users</button>
	<div id="requestListDisplay"></div>
</div>
<jsp:include page="success.jsp"></jsp:include>
	
</body>
<script type="text/javascript">
var Jdata;
var UserSellerList;
	function requestLists(){
		var RequestTable="";
		document.getElementById("just").innerHTML="";
		var i=0;
		var http_request=new XMLHttpRequest();
		http_request.onreadystatechange=function(){
			Jdata=JSON.parse(this.responseText);
			console.log(Jdata);
			RequestTable="<table>";
			while(i<Jdata.requestList.length){
				console.log("s");
				RequestTable+="<tr><th>"+Jdata.requestList[i].Name+"</th><th>"+Jdata.requestList[i].email+"</th><th><button id='accept' onclick='AcceptDeny("+Jdata.requestList[i].id+",this.id)'>accept</button></th><th><button id='new' onclick='AcceptDeny("+i+",this.id)'>deny</button></th></tr>";
				i++;
			}
			RequestTable+="</table>";
			console.log(RequestTable);
			document.getElementById("just").innerHTML+=RequestTable;
		}
		http_request.open("GET","<s:url namespace='/' action='requestList' />",true);
		http_request.send();
	}
	function AcceptDeny(id_No,process){
		console.log(id_No);
		var http_request=new XMLHttpRequest();
		http_request.onreadystatechange=function(){
			alert("success");
		}
		http_request.open("GET","<s:url action='acceptdeny?id="+id_No+"&process="+process+"' />",true);
		http_request.send();
	}
	function userSellerList(x){
		var RequestTable="";
		document.getElementById("just").innerHTML="";
		var i=0;
		console.log(x);
		var http_request=new XMLHttpRequest();
		http_request.onreadystatechange=function(){
			UserSellerList=JSON.parse(this.responseText);
			console.log("okkk:"+x);
			console.log(UserSellerList);
			RequestTable="<table>";
			while(i<UserSellerList.length){
				console.log("s");
				if(x=="user"){
				RequestTable+="<tr><th>"+UserSellerList[i].Name+"</th><th>"+UserSellerList[i].email+"</th><th><button id='remove' onclick='RemoveUser("+i+")'>Remove</button></th></tr>";
				}
				else if(x=="seller"){
					RequestTable+="<tr><th>"+UserSellerList[i].Name+"</th><th>"+UserSellerList[i].email+"</th><th><button id='remove' onclick='RemoveUser("+i+")'>Remove</button></th><th><button id='RemoveAsSeller' onclick='AcceptDeny("+UserSellerList[i].id+",'new')'>Remove As Seller</button></th></tr>";
				}
				i++;
			}
			RequestTable+="</table>";
			console.log(RequestTable);
			document.getElementById("just").innerHTML+=RequestTable;
		}
		http_request.open("GET","<s:url action='userSellerList?status="+x+"' />",true);
		http_request.send();
	}

</script>
</html>