function login(){
	var user = document.forms["formLogin"]["user"].value;
	var pass = document.forms["formLogin"]["pass"].value;

	//call Java method with this data
	//change loggeds in as text

	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/login';
	const params = 'user=' + user +'&pass=' + pass;
	alert(params);
	http.open("GET", url, false);
	http.send(params);

	http.onreadystatechange = (e) => {
	  console.log(http.responseText)
	}
}

function register(){
	var user = document.forms["formRegister"]["user"].value;
	var pass = document.forms["formRegister"]["pass"].value;
	var email = document.forms["formRegister"]["email"].value;
	var age = document.forms["formRegister"]["age"].value;
	var gender = document.forms["formRegister"]["gender"].value;
	var occupation = document.forms["formRegister"]["occupation"].value;

	// call Java method with this data
}