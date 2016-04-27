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

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="stylesheets/navbar-static-top.css" rel="stylesheet">
    
    <link href="css/bootstrap-datepicker.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
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
            <li><a href="/my-account.jsp">My Account</a></li>
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
    	<%
    		if (request.getAttribute("event")!=null){
    			pageContext.setAttribute("event", request.getAttribute("event"));
    		}
    	%>
    		<div>
    			<label class="control-label" for="party-name">Party name:</label>
				<p name="party-name"><%=((Event)request.getAttribute("event")).getName()%></p>
    		</div>
    		<div>
    			<label class="control-label" for="description">Description:</label>
	      	  	<p name="description"><%=((Event)request.getAttribute("event")).getDescription()%></p>
    		</div>
    		<div>
    			<label class="control-label" for="category">Category:</label>
    			<p name="category"><%=((Event)request.getAttribute("event")).getCategory()%></p>
    		</div>
			<%
			if (((Event)request.getAttribute("event")).getDate()!=null){
				%>
				<div>
    				<label class="control-label" for="date-and-time">Date and time:</label>
    				<p name="date-and-time"><%=((Event)request.getAttribute("event")).getDate().toString()%></p>
    			</div>
				<%
			}
			%>
    		<div>
    			<label class="control-label" for="location">Location:</label>
    			<p name="location"><%=((Event)request.getAttribute("event")).getLocation()%></p>
    		</div>
    		<div>
    			<label class="control-label" for="price">Price:</label>
				<p name="price"><%=String.valueOf(((Event)request.getAttribute("event")).getPrice())%></p>
    		</div>
    		
	   	
	      	
	      </div>
		</div>
		
		
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/js/externalJquery.js" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>


    <script type="text/javascript">
    $('#datepicker input').datepicker({
        todayHighlight: true
    });
    </script>

  </body>
</html>
