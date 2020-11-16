var allStatsJSON;

function readStats(){
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/stats';
	http.open("GET", url, true);
	http.send();

	http.onreadystatechange = (e) => {
	  allStatsJSON = (http.responseText);
	  allStatsJSON = allStatsJSON.replaceAll("\"", "");
	  ageData  = JSON.parse(allStatsJSON.split(";")[0]);
	  genderData = JSON.parse(allStatsJSON.split(";")[1]);
	  occuData = JSON.parse(allStatsJSON.split(";")[2]);
	  tempoData = JSON.parse(allStatsJSON.split(";")[3]);
	}
}