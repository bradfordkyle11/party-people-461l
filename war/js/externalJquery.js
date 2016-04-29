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


