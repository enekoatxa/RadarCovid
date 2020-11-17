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
			if(http.responseText=="false"){
		  	alert("There was a problem while logging in, try again")
		  } else {
		  	loggedUser = JSON.parse(http.responseText);
		  	fillData(loggedUser);
			$("#loggedAs").text("You are logged as " + user.username);	
		  }
		}
			hideLogin();
			$('#btnLogin').hide();
			$('#btnLogout').show();			
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
}