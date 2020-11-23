var allPositivesJSON;
var allPositivesArray = [];

function readAllPositives(){
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/positives';
	http.open("GET", url, true);
	http.send();

	http.onreadystatechange = (e) => {
		if(http.readyState === XMLHttpRequest.DONE){
			allPositivesJSON = (http.responseText).replaceAll("\"", "");
			allPositivesArray = allPositivesJSON.split(";");
			for (var i = 0; i < allPositivesArray.length; i++) {
				paintPositive(allPositivesArray[i]);
			}
		}
	}
}