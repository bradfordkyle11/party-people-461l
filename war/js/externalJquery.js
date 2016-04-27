$("#public-or-private").on("change", function(){
	var publicOrPrivate = $("#public-or-private").find(":selected").text();
	if (publicOrPrivate == "Public"){
		$("input[name='password']").prop({
			disabled: true
		});
		$("input[name='password-confirmation']").prop({
			disabled: true
		});
	}
	else{
		$("input[name='password']").prop({
			disabled: false
		});
		$("input[name='password-confirmation']").prop({
			disabled: false
		});
	}
	
});


$("form").on("submit", function() {
	var name = document.forms["new-party"]["party-name"].value;
	if (name==""||name==null){
		$("#party-name-form-group").toggleClass("has-error");
		$("#party-name-form-group").append("<span class='help-block'>Please name your party</span>");
		return false;
	}
	
	var publicOrPrivate = $("#public-or-private").find(":selected").text();
	if (publicOrPrivate=="Private"){
    	var pass1 = document.forms["new-party"]["password"].value;
    	var pass2 = document.forms["new-party"]["password-confirmation"].value;
   	 	if (pass1 != pass2) {
   	 		$("#password-form-group").toggleClass("has-error");
   	 		//$("#password-form-group").append("<span class='help-block'>Passwords do not match</span>");
   	 		$("#password-confirmation-form-group").toggleClass("has-error");
   	 		$("#password-confirmation-form-group").append("<span class='help-block'>Passwords do not match</span>");
       		//alert("Passwords do not match");
        	return false;
   		}
	}
});
