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
          <a class="navbar-brand" href="index.jsp">Party People</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="#about">My Account</a></li>
            <li><a href="#contact">About</a></li>
            <li><a href="#logout">Logout</a>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
    <div class="container">
    	<h1>Create a new party event:</h1>
    	<form id="new-party" role="form" action="new-party" method="post">
    		<div id="party-name-form-group" class="form-group">
    			<label class="control-label" for="party-name">Party name:</label>
    			<input type="text" class="form-control" id="party-name" name="party-name" placeholder="Enter the name of the party">
    		</div>
    		<div class="form-group">
    			<label class="control-label" for="description">Description:</label>
	      	  	<textarea class="form-control" name="description" placeholder="Enter description" rows="3"></textarea>
    		</div>
    		<div class="form-group">
    			<label class="control-label" for="category">Category:</label>
    			<select class="form-control" name="category" placeholder="Select category">
    				<option>Birthday</option>
    				<option>Graduation</option>
    				<option>Sports</option>
    				<option>Holiday</option>
    				<option>Social</option>
    				<option>Music</option>
    				<option>Pool Party</option>
    				<option>Other</option>
    			</select>
    		</div>
    		<div class="form-group">
    			<label class="control-label" for ="date">Date:</label>
    			<div class="input-group date" data-provide="datepicker" id="datepicker">
					<input type="text" class="form-control" placeholder="Enter date"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
				</div>
    		</div>
    		<div class="form-group">
    			<label class="control-label" for="time">Time:</label>
    			<input type="time" class="form-control" name="time" placeholder="Enter time">
    		</div>
    		<div class="form-group">
    			<label class="control-label" for="location">Location:</label>
    			<input type="text" class="form-control" name="location" placeholder="Enter location">
    		</div>
    		<div class="form-group">
    			<label class="control-label" for="public-or-private">Public or private:</label>
    			<select class="form-control" id="public-or-private" name="public-or-private">
    				<option id="public">Public</option>
    				<option id="private">Private</option>
    			</select>
    		</div>
    		<div id="password-form-group" class="form-group">
    			<label class="control-label" for="password">Password:</label>
    			<input type="password" class="form-control" name="password" placeholder="Enter password" disabled>
    		</div>
    		<div id="password-confirmation-form-group" class="form-group">
    			<label class="control-label" for="password-confirmation">Confirm password:</label>
    			<input type="password" class="form-control" name="password-confirmation" placeholder="Confirm password" disabled>
    		</div>
    		<div class="form-group">
    			<label class="control-label" for="price">Price:</label>
    			<input type="number" class="form-control" name="price" placeholder="Enter price">
    		</div>
    		<div class="form-group">
    			<label class="control-label" for="items-needed">Items needed:</label>
    			<textarea class="form-control" name="items-needed" rows="3" placeholder="Enter items needed separated by commas"></textarea>
    		</div>
    		<button type="submit" class="btn btn-primary">Create party</button>
    		
    	</form>
	   	
	      	
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
