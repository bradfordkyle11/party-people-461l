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
            PartyPeopleUser partyPeopleUser = new PartyPeopleUser();
            if (user!=null) {
            	partyPeopleUser = StorageHandler.getUser(user);
            	pageContext.setAttribute("partyPeopleUser", partyPeopleUser);
            	if (partyPeopleUser==null){
            		partyPeopleUser = new PartyPeopleUser(user);
            		StorageHandler.save(partyPeopleUser);
            	}
            %>
            <li><a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Logout (You are logged in as <%=user.getEmail()%>)</a>
            <%} else {
            %>
            <li><a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Login with Google</a>
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
		      <%
			//load and display events

			if (partyPeopleUser!=null){
	   		List<Event> events = partyPeopleUser.getCreated();
	   		if (events.isEmpty()){
	    	%>
	    	<p>You have not created any parties.</p>
	    	<%
	    	} else {
	    		for (Event event : events){
	    			pageContext.setAttribute("party_name", event.getName());
	    			pageContext.setAttribute("description", event.getDescription());
	    			pageContext.setAttribute("category", event.getCategory());
	    			pageContext.setAttribute("date", event.getDate().toString());
	    			pageContext.setAttribute("location", event.getLocation());
	    			pageContext.setAttribute("price", String.valueOf(event.getPrice()));
	    			pageContext.setAttribute("id", event.getId().toString());
	    	    	%>
	    	    	<form role="form" method="get" action="party-page">
	    	    	<input type="hidden" value="<%=pageContext.getAttribute("id")%>" name="event-id"/>
	    	    	
	    	    	
	    	    	<div class="well well-sm">
			        <h2><a href="#" onclick="$(this).closest('form').submit()">${party_name}</a></h2>
			        </form>

			        
			        <p1 class=italic>${category}<br><br></p1>
			       	<div class="row">
      					<div class="col-xs-8 col-sm-6">
        		        <p>${description}</p>
     					 </div>
     					 <div class="col-xs-8 col-sm-6">
     					 
     					 <p><strong>When: </strong> ${date}</p>
     					 <p><strong>Where:</strong> ${location}</p>
     					 <p><strong>Cost:</strong> ${price}</p>
			        	</div>
			        </div>
			        	
			        

	
					<ul class="list-inline">
						<li>
					        <!-- TODO: functioning edit button -->
					        
					        <!-- Button for editing the party -->
					        <form role="form" method="get" action="request-edit-page">
			    	    		<input type="hidden" value="<%=pageContext.getAttribute("id")%>" name="event-id"/>	
			    	    		<button class="btn btn-primary" type="submit">Edit</button>		        
			    	    	</form>
	    	    		</li>
	    	    		<li>
			    	    	<!-- TODO: functioning delete button -->
			    	    	<!-- Delete button -->
			    	    	<form role="form" method="post" action="delete-party">
			    	    		<input type="hidden" value="<%=pageContext.getAttribute("id")%>" name="event-id"/>	
			    	    		<button class="btn btn-danger" type="submit">Delete</button>		        
			    	    	</form>
	    	    		</li>
	    	    	</ul>

			      	</div>

	    	    	<%
	    		}

	    	}
			}
	    	%>
		       
		      
	      </div>

	      <div class="col-md-6">
	      <h1>Attending Parties:</h1>
	      
		      <%
			//load and display events

			if (partyPeopleUser!=null){
	   		List<Event> events = partyPeopleUser.getAttending();
	   		if (events.isEmpty()){
	    	%>
	    	<p>You have not RSVP'd to attend any parties.</p>
	    	<%
	    	} else {
	    		for (Event event : events){
	    			pageContext.setAttribute("party_name", event.getName());
	    			pageContext.setAttribute("description", event.getDescription());
	    			pageContext.setAttribute("category", event.getCategory());
	    			pageContext.setAttribute("date", event.getDate().toString());
	    			pageContext.setAttribute("location", event.getLocation());
	    			pageContext.setAttribute("price", String.valueOf(event.getPrice()));
	    			pageContext.setAttribute("id", event.getId().toString());
	    	    	%>
	    	    	<form role="form" method="get" action="party-page">
	    	    	<input type="hidden" value="<%=pageContext.getAttribute("id")%>" name="event-id"/>
	    	    	
	    	    	
	    	    	<div class="well well-sm">
			        <h2><a href="#" onclick="$(this).closest('form').submit()">${party_name}</a></h2>
			        </form>

			        
			        <p1 class=italic>${category}<br><br></p1>
			       	<div class="row">
      					<div class="col-xs-8 col-sm-6">
        		        <p>${description}</p>
     					 </div>
     					 <div class="col-xs-8 col-sm-6">
     					 
     					 <p><strong>When: </strong> ${date}</p>
     					 <p><strong>Where:</strong> ${location}</p>
     					 <p><strong>Cost:</strong> ${price}</p>
			        	</div>
			        </div>
			        	
			        

	
			        
			        <!-- Button for RSVPing or deciding not to come -->
			        <form role="form" method="post" action="rsvp">
	    	    		<input type="hidden" value="<%=pageContext.getAttribute("id")%>" name="event-id"/>			        
			        	<%
			        	if(event.isAttending(partyPeopleUser)){
			        		%>
			        		<input type="hidden" value="false" name="rsvp?"/>
	    	    			<button class="btn btn-primary" type="submit">I can't make it</button>
			        		<%
			        	} else {
			        		%>
		    	    		<input type="hidden" value="true" name="rsvp?"/>
		    	    		<button class="btn btn-primary" type="submit">RSVP &raquo;</button>
		    	    		<%
			        	}
	    	    		%>
	    	    	</form>

			      	</div>

	    	    	<%
	    		}

	    	}
			}
	    	%>
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
	      