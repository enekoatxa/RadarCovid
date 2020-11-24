function register(){
	var idCard = document.getElementById("idCardReg").value;
	var user = document.getElementById("userReg").value;
	var pass = document.getElementById("passReg").value;
	var email = document.getElementById("emailReg").value;
	var age = document.getElementById("ageReg").value;
	var gender = document.getElementById("genderReg").value;
	var occupation = document.getElementById("occupationReg").value;

	// call Java method with this data
	if(idCard!="" && user!="" && pass!="" && email!="" && age!="" && gender!="" && occupation!=""){
		var params = "?idCard="+idCard+"&user="+user+"&pass="+pass+"&email="+email+"&age="+age+"&gender="+gender+"&occupation="+occupation;
		const http = new XMLHttpRequest();
		const url = 'http://127.0.0.1:8090/register';
		http.open("GET", url+params, true);
		http.send();

		http.onreadystatechange = (e) => {
			if(http.readyState === XMLHttpRequest.DONE){
				if(http.responseText=="error"){
			  	alert("There was a problem while registering, please try again");
			  } else if (http.responseText=="errorNumber"){
			  	alert("The id card must be an 8 digit number, and age should be a number");
			  } else if (http.responseText=="errorUser"){
			  	alert("This user already exists in the database, try again");
			  } else if(http.responseText=="true"){
			  	alert("The registration was correct, now you can log in!");
			  	hideRegister();
			  }
			}
		}
	} else {
		alert("Some input is not complete, try again");
	}
}

function hideRegister(){
	$('#modalRegister').modal('hide');
}