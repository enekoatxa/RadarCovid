function login(){
	var user = document.forms["formLogin"]["user"].value;
	var pass = document.forms["formLogin"]["pass"].value;

	var formData = new FormData();
	
	formData.append("user", user);
	formData.append("pass", pass);
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/login';

	http.open("GET", url, true);
	http.send(formData);

	http.onreadystatechange = (e) => {
	  alert(http.responseText);
	}
}