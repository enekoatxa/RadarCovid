function register(){
	var idCard = document.forms["formRegister"]["idCard"].value;
	var user = document.forms["formRegister"]["user"].value;
	var pass = document.forms["formRegister"]["pass"].value;
	var email = document.forms["formRegister"]["email"].value;
	var age = document.forms["formRegister"]["age"].value;
	var gender = document.forms["formRegister"]["gender"].value;
	var occupation = document.forms["formRegister"]["occupation"].value;

	// call Java method with this data
	var formData = new FormData();
	
	formData.append("idCard", idCard);
	formData.append("user", user);
	formData.append("pass", pass);
	formData.append("email", email);
	formData.append("age", age);
	formData.append("gender", gender);
	formData.append("occupation", occupation);
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/register';

	http.open("GET", url, true);
	http.send(formData);

	http.onreadystatechange = (e) => {
	  console.log(http.responseText)
	}
}