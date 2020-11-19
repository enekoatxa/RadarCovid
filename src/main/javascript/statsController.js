var allStatsJSON;

function readStats(){
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:8090/stats';
	http.open("GET", url, true);
	http.send();

	http.onreadystatechange = (e) => {
		if(http.readyState === XMLHttpRequest.DONE){
			if(http.responseText=="false"){
			  	alert("There was an error reading the statistics, please reload the page");
			} else {
			  	allStatsJSON = JSON.parse(http.responseText);
				ageData  = allStatsJSON.ageStats;
				genderData = allStatsJSON.genderStats;
				occuData = allStatsJSON.occuStats;
				tempoData = allStatsJSON.timeStats;
			}
		}
	}
}