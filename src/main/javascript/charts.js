var myChart;
var ageData = "";
var genderData = "";
var occuData = "";
var tempoData = "";

function paintChart(number){
	if(ageData=="" || genderData =="" || occuData =="" || tempoData ==""){
		alert("The data is loading, please wait")
	} else {
	Chart.defaults.global.elements.rectangle.backgroundColor = 'rgba(54, 162, 235, 0.2)';
	Chart.defaults.global.elements.rectangle.borderColor = 'rgba(54, 162, 235, 1)';
	Chart.defaults.global.elements.line.backgroundColor = 'rgba(54, 162, 235, 0.2)';
	Chart.defaults.global.elements.line.borderColor = 'rgba(54, 162, 235, 1)';
	try{
		myChart.destroy();
	} catch (error){}
	var ctx = document.getElementById('myChart').getContext('2d');
    updateStatButtons(1);
	switch (number){
		case 1:
		console.log(ageData);
		myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: Array.from(Array(100).keys()),
	        datasets: [{
	            label: '# of Positives',
	            data: ageData,
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
			break;
		case 2:
		console.log(genderData);
		updateStatButtons(2);
		myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: ['Male', 'Female', 'Other'],
	        datasets: [{
	            label: '# of Positives',
	            data: genderData,
	            backgroundColor: [
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(255, 206, 86, 0.2)'
	            ],
	            borderColor: [
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 99, 132, 1)',
	                'rgba(255, 206, 86, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
		break;
		case 3:
		console.log(occuData);
		updateStatButtons(3);
		myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: ['1st sector', '2nd sector', '3rd sector', 'unoccupied', 'student'],
	        datasets: [{
	            label: '# of Positives',
	            data: occuData,
	            backgroundColor: [
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)'
	            ],
	            borderColor: [
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 99, 132, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
		break;
		case 4:
		console.log(tempoData);
		updateStatButtons(4);
		myChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	        labels: ['2020-1', '2020-2', '2020-3', '2020-4', '2020-5', '2020-6',
	        	'2020-7', '2020-8', '2020-9', '2020-10', '2020-11', '2020-12',
	        	'2021-1', '2021-2', '2021-3', '2021-4', '2021-5', '2021-6'],
	        datasets: [{
	            label: '# of Positives',
	            data: tempoData,
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
		break;
	}	
	}
}

function updateStatButtons(activeButton){
	for (var i = 1; i <=4 ; i++) {
		if(activeButton==i){
			document.getElementById("statsButton"+i).classList.remove("btn-primary");
   			document.getElementById("statsButton"+i).classList.add("btn-success");
		}
		else{
			document.getElementById("statsButton"+i).classList.add("btn-primary");
   			document.getElementById("statsButton"+i).classList.remove("btn-success");
		}
	}
}