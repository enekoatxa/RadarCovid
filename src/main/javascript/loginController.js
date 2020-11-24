var loggedUser;

function login(){
	var idCard = document.getElementById("idCardLog").value;
	var pass = document.getElementById("passLog").value;
	var params = "?idCard="+idCard+"&pass="+pass;
	
	if(idCard!="" && pass!=""){
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/login';

	http.open("GET", url+params, true);
	http.send();
	http.onreadystatechange = (e) => {
		if(http.readyState === XMLHttpRequest.DONE){
			if(http.responseText=="error"){
			  	alert("There was a problem while reading form database, try again")
			  } else if (http.responseText=="errorNumber") {
			  		alert("The id card has to be a number (8 digits)");
			  } else if (http.responseText=="errorPass") {
			  		alert("The password is incorrect, try again");
			  } else if (http.responseText=="errorUser") {
			  		alert("The user does not exist in the database, try again");
			  } else {
			  	loggedUser = JSON.parse((http.responseText));
			  	fillData(loggedUser);
				$("#loggedAs").text("You are logged as " + loggedUser.username);
				hideLogin();
				$('#btnLogin').hide();
				$('#btnLogout').show();
				$('#nav3').show();
			  }
			}		
		}
	} else {
		alert("Some input is not complete, try again");
	}
}

function hideLogin(){
	$('#modalLogin').modal('hide');
}

function logOut(){
	loggedUser="";
	fillData(loggedUser);
	$('#btnLogin').show();
	$('#btnLogout').hide();
	$('#nav3').hide();
	$("#loggedAs").text("You are not logged in");
	alert("You succesfully logged out");
	$('#nav1').click();
}