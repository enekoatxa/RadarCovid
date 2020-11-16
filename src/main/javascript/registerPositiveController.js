function regPositive(){
	var coords = document.forms["formRegPositive"]["positiveCoords"].value.replace("Long: ", "").replace("Lat: ","");
	var year = document.forms["formRegPositive"]["positiveDate"].value.split("-")[0];
	var month = document.forms["formRegPositive"]["positiveDate"].value.split("-")[1];
	var day = document.forms["formRegPositive"]["positiveDate"].value.split("-")[2];

	//call Java method with this data
	//change loggeds in as text

	var params = "?user="+user+"&pass="+pass+"&longitude="+longitude+"&latitude="+latitude+"&year="+year+"&month="+month+"&day="+day;
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/addPositive';
	http.open("GET", url+params, true);
	http.send();

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