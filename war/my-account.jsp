<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="partypeople.PartyPeopleUser" %>
<%@ page import="partypeople.StorageHandler" %>
<%@ page import="partypeople.Event" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Party People</title>

    <!-- Custom styles for this template -->
    <link href="stylesheets/navbar-static-top.css" rel="stylesheet">
    
    <link href="css/bootstrap-datepicker.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link id="bsdp-css" href="css/datepicker3.css" rel="stylesheet">
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
	<%
	//get current user
		UserService userService = UserServiceFactory.getUserService();
    	User user = userService.getCurrentUser();
    	if (user!=null){
    		pageContext.setAttribute("user", user);
    	}
    %>
    <!-- Static navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/index.jsp">Party People</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="/index.jsp">Home</a></li>
            <li class="active"><a href="/my-account.jsp">My Account</a></li>
            <li><a href="#about">About</a></li>
            
            <%
            if (user!=null) {
            	PartyPeopleUser partyPeopleUser = StorageHandler.getUser(user);
            	if (partyPeopleUser==null){
            		partyPeopleUser = new PartyPeopleUser(user);
            		StorageHandler.save(partyPeopleUser);
            	}
            %>
            <li><a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Logout</a>
            <%} else {
            %>
            <li><a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Login</a>
            <%
            }
            %>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
     <div class="container">
    	
    	
	   	<div class="row">
	
	      <div class="col-md-6">
	      <h1>Created Parties:</h1>
	      <!-- Main component for a primary marketing message or call to action -->
		      <div class="well well-sm">
		        <h2>Party 1</h2>
		        <p>Example party. </p>
		        <p>
		          <a class="btn btn-primary" href="#" role="button">Edit &raquo;</a>
		        </p>
		      </div>
		      
		      <div class="well well-sm">
		        <h2>Party 2</h2>
		        <p>Another party.</p>
		        <p>
		          <a class="btn btn-primary" href="#" role="button">Edit &raquo;</a>
		        </p>
		      </div>
		      
	      </div>

	      <div class="col-md-6">
	      <h1>Attending Parties:</h1>
	      <!-- Main component for a primary marketing message or call to action -->
		      <div class="well well-sm">
		        <h2>Party 1</h2>
		        <p>Example party. </p>
		        <p>
		          <a class="btn btn-primary" href="#" role="button">Details &raquo;</a>
		        </p>
		      </div>
		      
		      <div class="well well-sm">
		        <h2>Party 2</h2>
		        <p>Another party.</p>
		        <p>
		          <a class="btn btn-primary" href="#" role="button">Details &raquo;</a>
		        </p>
		      </div>
	      </div>
	      </div>

	    
	 </div>
	      



	      	
	      

		
		
<!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="js/bootstrap-datepicker.js"></script>

    <script type="text/javascript">
    $('#datepicker input').datepicker({
        todayHighlight: true
    });
    </script>


  </body>
</html>
	      