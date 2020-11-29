function regPositive(){
	var idCard = document.getElementById("idCardReg").value;
	var coords = document.getElementById("positiveCoords").value.replace("Long: ", "").replace("Lat: ","");
	var longitude = coords.split(",")[0];
	var latitude = coords.split(",")[1];
	var date = document.getElementById("positiveDate").value.replaceAll(" ","").replaceAll("-", "/");
	var year = date.split("/")[0];
	var month = date.split("/")[1];
	var day = date.split("/")[2];

	var params = "?user="+loggedUser.username+"&pass="+loggedUser.pass+"&longitude="+longitude+"&latitude="+latitude+"&year="+year+"&month="+month+"&day="+day;
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/addPositive';
	http.open("GET", url+params, true);
	http.send();

	http.onreadystatechange = (e) => {
		if(http.readyState === XMLHttpRequest.DONE){
			if(http.responseText=="false"){
		  	alert("There was an error registering the positive, please try again");
		  } else if(http.responseText=="errorNumber"){
		  	alert("Something is not complete, fill the date and place, please");
		  } else {
		  	alert("The registration was correct!");
		  }
		}
	}
}

function fillData(user){
	if(user==""){
		$("#infoUser").text(" XXXXX ");
		$("#infoEmail").text(" XXXXX ");
		$("#infoIdCard").text(" XXXXX ");
		$("#infoAge").text(" XXXXX ");
		$("#infoGender").text(" XXXXX ");
		$("#infoOccupation").text(" XXXXX ");
	} else {
		$("#infoUser").text(user.username);
		$("#infoEmail").text(user.email);
		$("#infoIdCard").text(user.idCard);
		$("#infoAge").text(user.age);
		$("#infoGender").text(user.gender);
		$("#infoOccupation").text(user.occupation);
	}
}