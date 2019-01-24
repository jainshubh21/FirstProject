 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,intial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/NavBar.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shop Drop</title>
</head>
<body>
<div class="container" >
   <nav class="navbar navbar-default bg-success" id="navcolor">
     <div  class="navbar-header">
        <a href="#" class="navbar-brand"><img src="resources/Images/Logo.png" height="50px" width="70px"></a>
        <button type="button" class="navbar-toggle collapsed" 
   data-toggle="collapse" data-target="#collapse-example" aria-expanded="false">
        
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        
      </button>
       </div>
<div class="collapse navbar-collapse" id="collapse-example">

<ul class="nav navbar-nav nav-tabs" id="links">
<li><a href="<c:url value='/home'></c:url>"><span class="glyphicon glyphicon-home"></span>Home</a></li>
<li><a href="<c:url value='/aboutus'></c:url>">About Us</a></li>
<li><a href="<c:url value='/all/getallproducts'></c:url>">Browse all products</a></li>
<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown">Select By Cateogory
			<span class="caret"></span></a>
			<ul class="dropdown-menu">
			<c:forEach var="c" items="${categories }">
								<li><a
									href="<c:url value='/all/searchbycategory?searchCondition=${c.categoryName }'></c:url>">${c.categoryName }</a></li>
							</c:forEach>
							<li><a
								href="<c:url value='/all/searchbycategory?searchCondition=All'></c:url>">All</a></li>
</ul>
</li>
			
<li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span></a></li>			
<li><a href="<c:url value='/all/getregistrationform'></c:url>"><span class="glyphicon glyphicon-user"></span>Sign Up</a></li>
<li><a href="<c:url value='/login'></c:url>"><span class="glyphicon glyphicon-log-in"></span>Sign In</a></li>
<li><a><span class="glyphicon glyphicon-log-out"></span>Sign Out</a></li>
</ul>
</div>
</nav>
</div>

 
</body>
</html>

