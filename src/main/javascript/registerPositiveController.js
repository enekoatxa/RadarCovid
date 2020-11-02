function regPositive(){
	var date = document.forms["formRegPositive"]["positiveDate"].value;
	var coords = document.forms["formRegPositive"]["positiveCoords"].value;

	//call Java method with this data
	//change loggeds in as text

	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/addPositive';
	const params = 'user=' + user +'&pass=' + pass;
	alert(params);
	http.open("GET", url, false);
	http.send(params);

	http.onreadystatechange = (e) => {
	  console.log(http.responseText)
	}
}

function fillData(user){
	$("#infoUser").text(user.username);
	$("#infoEmail").text(user.email);
	$("#infoIdCard").text(user.idCard);
	$("#infoAge").text(user.age);
	$("#infoGender").text(user.gender);
	$("#infoOccupation").text(user.occupation);
}