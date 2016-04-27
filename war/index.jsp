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
    <link id="bsdp-css" href="css/datepicker3.css" rel="stylesheet">
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

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
          <a class="navbar-brand" href="#">Party People</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="/my-account.jsp">My Account</a></li>
            <li><a href="#contact">About</a></li>
            <li><a href="#logout">Logout</a>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>


	<div class="container">
		<%
		if(request.getAttribute("desiredCategories")!=null){
		%>
		<p>${desiredCategories}</p>
		<%
		} 
		if(request.getAttribute("startDate")!=null){
		%>
		<p>from ${startDate} to ${endDate}</p>
		<%
		}
		%>
		<div class="row">
			<div class="col-sm-8">
				<a class="btn btn-primary" href="/new-party-event.jsp" role="button">Create New Party Event</a>
			</div>
			<div class="col-sm-4">
				<form role="form">
					<input type="search" placeholder="Search parties" class="form-control" id="search">
				</form>
			</div>
	
		</div>
	</div>
    
    <div class="container">
    	<h1>Upcoming Parties:</h1>
	   	<div class="row">
	
	      <div class="col-md-8">
	      <!-- Main component for a primary marketing message or call to action -->
		      <div class="well well-sm">
		        <h2>Party 1</h2>
		        <p>Example party. </p>
		        <p>
		          <a class="btn btn-primary" href="#" role="button">RSVP &raquo;</a>
		        </p>
		      </div>
		      
		      <div class="well well-sm">
		        <h2>Party 2</h2>
		        <p>Another party.</p>
		        <p>
		          <a class="btn btn-primary" href="#" role="button">RSVP &raquo;</a>
		        </p>
		      </div>
	      </div>
	      
	      <div class="col-md-4">
	      <div class="well well-sm">
	      	<h3>Filters</h3>
	      	
	      	<form role="form" action="update-filters" method="post">
	      		<h4>Categories:</h4>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" name="Birthday">Birthday</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" name="Graduation">Graduation</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" name="Sports">Sports</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" name="Holiday">Holiday</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" name="Social">Social</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" name="Music">Music</label>
	      		</div>
	 			<div class="checkbox">
	      			<label><input type="checkbox" value="" name="Pool Party">Pool Party</label>
	      		</div>
	      		<div class="checkbox">
	      			<label><input type="checkbox" value="" name="other">Other</label>
      			</div>
	      		<h4>Date Range:</h4>
	      		<div class="input-daterange input-group" data-provide="datepicker" id="datepicker">
    				<input type="text" class="input-sm form-control" name="start" />
    				<span class="input-group-addon">to</span>
   			 		<input type="text" class="input-sm form-control" name="end" />
				</div>
				<button type="submit" class="btn btn-primary">Apply</button>
	      		
    			
    			

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
    <script src="js/ie10-viewport-bug-workaround.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>

    <script type="text/javascript">
    $('#datepicker input').datepicker({
        todayHighlight: true
    });
    </script>


  </body>
</html>
