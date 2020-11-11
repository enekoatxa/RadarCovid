var allStatsJSON;

function readStats(){
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/stats';
	http.open("GET", url, true);
	http.send();

	http.onreadystatechange = (e) => {
	  allStatsJSON = (http.responseText);
	  ageData  = allStatsJSON.split(";")[0];
	  genderData = allStatsJSON.split(";")[1];
	  occuData = allStatsJSON.split(";")[2];
	  tempoData = allStatsJSON.split(";")[3];
	}
}