<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="partypeople.PartyPeopleUser" %>
<%@ page import="partypeople.StorageHandler" %>
<%@ page import="partypeople.Event" %>
<%@ page import="partypeople.Item" %>
<%@ page import="partypeople.Comment" %>

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
            PartyPeopleUser partyPeopleUser = new PartyPeopleUser();
            if (user!=null) {
            	partyPeopleUser = StorageHandler.getUser(user);
            	if (partyPeopleUser==null){
            		partyPeopleUser = new PartyPeopleUser(user);
            		StorageHandler.save(partyPeopleUser);
            	}
            %>
            <li><a href="<%= userService.createLogoutURL("/") %>">Logout</a>
            <%} else {
            %>
            <li><a href="<%= userService.createLoginURL("/") %>">Login</a>
            <%
            }
            %>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
   	<%
	if (request.getAttribute("event")!=null){
		pageContext.setAttribute("event", request.getAttribute("event"));
	}
	else {
		response.sendRedirect("/index.jsp");
	}
 	%>
 	<div class="container">
 	<h1><%=((Event)request.getAttribute("event")).getName()%></h1>
   		
   		
   					        <!-- Button for RSVPing or deciding not to come -->
    <form role="form" method="post" action="rsvp">
   		<input type="hidden" value="<%=String.valueOf(((Event)request.getAttribute("event")).getId())%>" name="event-id"/>			        
    	<%
    	if(((Event)pageContext.getAttribute("event")).isAttending(partyPeopleUser)){
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
   	</div> <!-- /container -->
   	
    <div class="container">

    	<div class="row">
    		<div class="col-md-8">
    		<div class="well well-sm">
    		<h4>Details</h4>
    		<div>
	      	  	<p name="description"><%="Description: " + ((Event)request.getAttribute("event")).getDescription()%></p>
    		</div>
    		<div>
    			<p name="category"><%="Category: " + ((Event)request.getAttribute("event")).getCategory()%></p>
    		</div>
			<%
			if (((Event)request.getAttribute("event")).getDate()!=null){
				%>
				<div>
    				<p name="date-and-time"><%="Date and Time: " + ((Event)request.getAttribute("event")).getDate().toString()%></p>
    			</div>
				<%
			}
			%>
    		<div>
    			<p name="location"><%="Location: " + ((Event)request.getAttribute("event")).getLocation()%></p>
    		</div>
    		<div id="map"></div>
    		<div>
				<p name="price"><%="Price: " + String.valueOf(((Event)request.getAttribute("event")).getPrice())%></p>
    		</div>
    		<div>
    		<p>Attendees:</p>
    		<%
    			if (((Event)request.getAttribute("event")).getAttending().isEmpty()){
    				%>
    				<p>none</p>    				
    				<%

    			}
    			for (PartyPeopleUser attendee : ((Event)request.getAttribute("event")).getAttending()){
    				pageContext.setAttribute("attendee", attendee.toString());
    				%>
    				<p>${attendee}</p>
    				<%
    			}
    		%>
    		</div>
    		</div> <!-- /well well-sm -->
    		</div> <!-- /col-md-8 -->
    		<div class="col-md-4">
    		<%
    		if (!((Event)pageContext.getAttribute("event")).getItemsNeeded().isEmpty()){
    			
    		
    		%>
    		<!-- Display item sign-up checklist -->
    			<div class="well well-sm">
 			        
    				<form role="form" method="post" action="item-sign-up">
	    				<h4>Items needed:</h4>
	    				<input type="hidden" name="num-items" value="<%=((Event)pageContext.getAttribute("event")).getItemsNeeded().size()%>">
	    				<%
	    				int i = 0;
	    				boolean bringingItem = false;
	    				for (Item neededItem : ((Event)pageContext.getAttribute("event")).getItemsNeeded()){
	    					if (neededItem.getUser()==null){
	    					%>
	    					<div class="checkbox">
		      					<label><input type="checkbox" value="" name="<%="item" + String.valueOf(i)%>"><%=neededItem.getName()%></label>
		      					<input type="hidden" value="<%=String.valueOf(neededItem.getId())%>" name="<%="id" + String.valueOf(i)%>"></input>
		      				</div>
	    					<%
	    					} else if (user!=null){
	    						if (neededItem.getUser().equals(partyPeopleUser)){
	    							bringingItem = true;
	    							%>
	    							<div class="checkbox">
				      					<label><input type="checkbox" value="" name="<%="item" + String.valueOf(i)%>" checked="true" disabled><%=neededItem.getName()%></label>
				      					<input type="hidden" value="" name="<%="item" + String.valueOf(i)%>">
				      					<input type="hidden" value="<%=String.valueOf(neededItem.getId())%>" name="<%="id" + String.valueOf(i)%>"></input>
				      				</div>
	    							<%
	    						}
	    						else {
	    							%>
	    							<div class="checkbox">
				      					<label><input type="checkbox" value="" name="<%="item" + String.valueOf(i)%>"><%=neededItem.getName()%></label>
				      					<input type="hidden" value="<%=String.valueOf(neededItem.getId())%>" name="<%="id" + String.valueOf(i)%>"></input>
				      				</div>
	    							<%
	    						}
	    					} else {
	    						%>
    							<div class="checkbox">
			      					<label><input type="checkbox" value="" name="<%="item" + String.valueOf(i)%>" checked="true" disabled><%=neededItem.getName()%></label>
			      					<input type="hidden" value="" name="<%="item" + String.valueOf(i)%>">
			      					<input type="hidden" value="<%=String.valueOf(neededItem.getId())%>" name="<%="id" + String.valueOf(i)%>"></input>
			      				</div>
    							<%
	    					}
	    					i++;
	    				}
	    				
	    				if (user==null){
	    				%>
	    				
	    				<button class="btn btn-primary" type="submit" disabled>Login to sign up to bring item</button>
	    				<%
	    				} else if (bringingItem==false){
	    				%>
	    				<input type="hidden" name="sign-up" value="true">
	    				<button class="btn btn-primary" type="submit">I will bring this</button>
	    				<%
	    				} else {
	    					%>
	    					<input type="hidden" name="sign-up" value="false">
	    					<button class="btn btn-primary" type="submit">I can't bring this anymore</button>
	    					<%
	    				}
	    				%>
    				</form>
    				
    			</div> <!-- /well well-sm -->
    			<%
    		}
    			%>
    		</div> <!-- /col-md-4 -->
   		</div> <!-- /row -->
   		<form role="form" action="post-comment" method="post">
   			<div class="form-group">
    			<label class="control-label" for ="comment-content">Write a comment:</label>
    			<textarea class="form-control" rows="3" name="comment-content" placeholder="Write a comment"></textarea>
    			<input type="hidden" value="<%=String.valueOf(((Event)request.getAttribute("event")).getId())%>" name="event-id"/>
    			<input type="hidden" value="post" name="action">
    		</div>
    		<%
    		if (user!=null){
    		%>
    		<button class="btn btn-primary" type="submit">Submit</button> 
    		<%
    		} else {
    		%>
    		<button class="btn btn-primary" disabled>Login to comment</button> 
    		<%
    		}
    		%>
   		</form>
   		
   		<div>
   			<h3>Comments</h3>
   			<%
   			if (((Event)request.getAttribute("event")).getComments().isEmpty()){
   				%>
   				<p>There are no comments about this party yet.</p>
   				<%
   			} else {
   				List<Comment> comments = ((Event)request.getAttribute("event")).getComments();
   				Collections.sort(comments);
   				for(Comment comment : comments){
   			%>
   				<div class="well well-sm">
   					<p><%=partyPeopleUser.toString() + " wrote on " + comment.getTimePosted().toString() %></p>
   					<p><%=comment.getContent() %>
   				</div>
   				<%
   				}
   			}
   			%>
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
    <script src="js/bootstrap-datepicker.js"></script>

    <script>
      var map;
      function initMap() {
    	  var myLatLng = {lat: <%=((Event)request.getAttribute("event")).getLatitude()%>, lng: <%=((Event)request.getAttribute("event")).getLongitude()%>};
		if (myLatLng.lat!=0 || myLatLng.lat!=0){
        map = new google.maps.Map(document.getElementById('map'), {
          center: myLatLng,
          zoom: 15
        });
        
        
        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map
          });
		}
		else{
			var m = document.getElementById("map").style.height = 0;
		}
      }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBux1-zJMJGC5eMzSZI2ofmw_t06DuJajg&callback=initMap"
    async defer></script>


    <script type="text/javascript">
    $('#datepicker input').datepicker({
        todayHighlight: true
    });
    </script>

  </body>
</html>
