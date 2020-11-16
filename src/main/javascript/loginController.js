function login(){
	var idCard = document.forms["formLogin"]["idCard"].value;
	var pass = document.forms["formLogin"]["pass"].value;
	var params = "?idCard="+idCard+"&pass="+pass;
	
	const http = new XMLHttpRequest();
	http.onreadystatechange = function() {
		console.log(http.responseText);
	}
	const url = 'http://127.0.0.1:8090/login';

	http.open("GET", url+params, true);
	http.send();
}