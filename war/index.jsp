<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="partypeople.PartyPeopleUser" %>
<%@ page import="partypeople.StorageHandler" %>
<%@ page import="partypeople.Event" %>
<%@ page import="partypeople.EventBrowser" %>
<%@ page import="partypeople.Filter" %>

<html lang="en">
  <head>
  <style>
 	 p1.italic {
   	 font-size: 75%;
   	 font-style: italic;
	}
	
	p2.thick {
		
		font-weight: bold;
	}
  </style>
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
          <a class="navbar-brand" href="/index.jsp#">Party People</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/index.jsp#">Home</a></li>
            <li><a href="/my-account.jsp">My Account</a></li>
            
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
			<div class="col-sm-8">
				<%
				if (user!=null){
				%>
				<a class="btn btn-primary" href="/new-party-event.jsp" role="button">Create New Party Event</a>
				<%
				} else {
				%>
				<a class="btn btn-primary" role="button" disabled>Login To Create Party Event</a>
				<%
				}
				%>
			</div>
			<div class="col-sm-4">
			
			<!-- Searching form -->
				<form role="form" method="get" action="update-filters">

					<div class="input-group">
						<input type="search" placeholder="Search parties" class="form-control" id="search" name="query">
						<span class="input-group-btn">
				       		<button class="btn btn-primary" type="submit">Search</button>
				      	</span>
					</div>
				</form>
			</div>
	
		</div>
	</div>
    

    
    
    <div class="container">
    	<h1>Upcoming Parties:</h1>
	   	<div class="row">
	   	
	   	
		  <!-- TODO: only display a certain number of parties per page -->
	      <div class="col-md-8">
          <%
			//load and display events
			EventBrowser browser;
			if (request.getAttribute("filter")!=null){
				browser = new EventBrowser((Filter) request.getAttribute("filter"));
			} else {
				browser = new EventBrowser();
			}
	   		List<Event> events = browser.getEvents();
	   		if (events.isEmpty()){
	    	%>
	    	<p>There are no upcoming events.</p>
	    	<%
	    	} else {
	    		for (Event event : events){
	    			pageContext.setAttribute("party_name", event.getName());
	    			pageContext.setAttribute("description", event.getDescription());
	    			pageContext.setAttribute("category", event.getCategory());
	    			pageContext.setAttribute("date", event.getFormattedDate());
	    			pageContext.setAttribute("location", event.getLocation());
	    			DecimalFormat numberFormat = new DecimalFormat("'$'#0.00");
	    			pageContext.setAttribute("price", numberFormat.format(event.getPrice()));
	    			pageContext.setAttribute("id", event.getId().toString());
	    	    	%>
	    	    	<form role="form" method="get" action="party-page">
	    	    	<input type="hidden" value="<%=pageContext.getAttribute("id")%>" name="event-id"/>
	    	    	
	    	    	
	    	    	<div class="well well-sm">
			        <h2><a href="#" onclick="$(this).closest('form').submit()">${party_name}</a></h2>
			        </form>
			        <p1 class=italic>${category}<br><br></p1>
			        <%
			        if(event.isPrivateEvent()){ 
			        	%>
			        	<div class="row">
	      					<div class="col-xs-8 col-sm-6">
	        		        <p>Private event</p>
	     					 </div>
	     				
				        	
			        	</div>
			        	<%
			        } else {
			        %>

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
			        <%
			        }
			        %>
			        	
			        

	
			        
			        <!-- Button for RSVPing or deciding not to come -->
			        <%
			        if (!event.isPrivateEvent()){
			        %>
			        <form role="form" method="post" action="rsvp">
	    	    		<input type="hidden" value="<%=pageContext.getAttribute("id")%>" name="event-id"/>			        
			        	<%
			        	if (user==null){
			        		%>
			        		<input type="hidden" value="false" name="rsvp?"/>
	    	    			<button class="btn btn-primary" type="submit" disabled>Login to RSVP</button>
			        		<%
			        	}
			        	else if(event.isAttending(partyPeopleUser)){
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
			        }
	    	    		%>
	    	    	</form>

			      	</div>

	    	    	<%
	    		}

	    	}
	    	%>
	      </div>
	      
	      <div class="col-md-4">
	      <div class="well well-sm">
	      	<h3>Filters</h3>
	      	
	      	<form role="form" action="update-filters" method="get" name="filter-form" id="filter-form">
	      		<h4>Sort by:</h4>
	      		<div class="form-group">
    			<select class="form-control" id="sort-type" name="sort-type">
    				<option>Soonest</option>
    				<option>A to z</option>
    				<option>Newest</option>
    				<option>Price: low to high</option>
    			</select>
    		</div>
	      		<h4>Categories:</h4>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" id="Birthday" name="Birthday">Birthday</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" id="Graduation" name="Graduation">Graduation</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" id="Sports" name="Sports">Sports</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" id="Holiday" name="Holiday">Holiday</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" id="Social" name="Social">Social</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" id="Music" name="Music">Music</label>
	      		</div>
	 			<div class="checkbox">
	      			<label><input type="checkbox" value="" id="Pool Party" name="Pool Party">Pool Party</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" id="Other" name="Other">Other</label>
      			</div>
	      		<h4>Date Range:</h4>
	      		<div class="input-daterange input-group" data-provide="datepicker" id="datepicker">
    				<input type="text" class="input-sm form-control" id="start" name="start" />
    				<span class="input-group-addon">to</span>
   			 		<input type="text" class="input-sm form-control" id="end" name="end" />
				</div>
				<h4>Distance:</h4>
				<div class="form-group">
	    			<label class="control-label" for="radius">Radius (in mi.):</label>
	    			<input type="number" class="form-control" name="radius" id="radius" placeholder="Enter radius in miles">
	    		</div>
		    	<div class="form-group">
	    			<label class="control-label" for="location">From location:</label>
	    			<input type="text" class="form-control" name="location" id="location" placeholder="Enter location">
	    		</div>
	    		<input type="hidden" name="latlng" id="latlng">
				<button type="button" class="btn btn-primary" onclick="submitFilterForm()">Apply</button>
	      		
    			
    			

	      	</form>
	      	</div>
	      	
	      </div>
		</div>
		
		
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/bootstrap-datepicker.js"></script>

    <script type="text/javascript">
    $('#datepicker input').datepicker({
        todayHighlight: true,
        startDate: new Date()
    });
    </script>
    </script>
    <script>
    function initMap() {
        geocoder = new google.maps.Geocoder();
      }

    function submitFilterForm(){
    	//alert("you are here");
    	if(document.getElementById("location").value != ""){
    		geocodeAddress(geocoder);
    	}
    	else {
    		document.getElementById("filter-form").submit();
    	}
    }
      function geocodeAddress(geocoder) {
		//alert(document.getElementById('latlong').value);
        var address = document.getElementById('location').value;
        geocoder.geocode({'address': address}, function(results, status) {
          if (status === google.maps.GeocoderStatus.OK) {
        	  $('#latlng').val(results[0].geometry.location.toString());
        	 // alert(document.getElementById('latlong').value);
          	document.getElementById("filter-form").submit();
        	  

          } else {
            //alert('Geocode was not successful for the following reason: ' + status);
            document.getElementById("filter-form").submit();
          }
        });
      }
	</script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBux1-zJMJGC5eMzSZI2ofmw_t06DuJajg&callback=initMap"
    async defer></script>
    
    <!-- This script can be used to get request parameters with Javascript -->
    <script>
    function getQueryParameter ( parameterName ) {
    	  var queryString = window.top.location.search.substring(1);
    	  var parameterName = parameterName + "=";
    	  if ( queryString.length > 0 ) {
    	    begin = queryString.indexOf ( parameterName );
    	    if ( begin != -1 ) {
    	      begin += parameterName.length;
    	      end = queryString.indexOf ( "&" , begin );
    	        if ( end == -1 ) {
    	        end = queryString.length
    	      }
    	        
    	      var temp =  unescape ( queryString.substring ( begin, end ) );
    	      var result = temp.replace(/\+/g, " ");
    	      return result;
    	    }
    	  }
    	  return "null";
    	}
    </script>
    
    <!-- This script will update the filter and search forms based on submitted data -->
    <script type="text/javascript">
    	//set search value
    	if (getQueryParameter("query")!="null"){
    		document.getElementById("search").value = getQueryParameter("query");
    	}
    	
    	//set sort type
    	if (getQueryParameter("sort-type")!="null"){
    		document.getElementById("sort-type").value = getQueryParameter("sort-type");
    	}
    	
    	//set categories
    	if (getQueryParameter("Birthday")!="null"){
    		document.getElementById("Birthday").checked = true;
    	}
    	if (getQueryParameter("Graduation")!="null"){
    		document.getElementById("Graduation").checked = true;
    	}
    	if (getQueryParameter("Sports")!="null"){
    		document.getElementById("Sports").checked = true;
    	}
    	if (getQueryParameter("Holiday")!="null"){
    		document.getElementById("Holiday").checked = true;
    	}
    	if (getQueryParameter("Social")!="null"){
    		document.getElementById("Social").checked = true;
    	}
    	if (getQueryParameter("Music")!="null"){
    		document.getElementById("Music").checked = true;
    	}
    	if (getQueryParameter("Pool Party")!="null"){
    		document.getElementById("Pool Party").checked = true;
    	}
    	if (getQueryParameter("Other")!="null"){
    		document.getElementById("Other").checked = true;
    	}
    	
    	//set date range
    	if (getQueryParameter("start")!="null"){
    		document.getElementById("start").value = getQueryParameter("start");
    	}
    	if (getQueryParameter("end")!="null"){
    		document.getElementById("end").value = getQueryParameter("end");
    	}
    	
    	//set distance filter
    	if (getQueryParameter("radius")!="null"){
    		document.getElementById("radius").value = getQueryParameter("radius");
    	}
    	if (getQueryParameter("location")!="null"){
    		document.getElementById("location").value = getQueryParameter("location");
    	}
    	
    	
    </script>


  </body>
</html>
