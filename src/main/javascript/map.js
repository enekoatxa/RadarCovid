var mymap;
var mymap2;

function findCurrentPosition() {
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var positionInfo = "Lat: " + position.coords.latitude + ", " + "Long: " + position.coords.longitude;
            document.getElementById("positiveCoords").value = positionInfo;
            mymap2.setView([position.coords.latitude, position.coords.longitude], 13);
        });
    } else {
        alert("Sorry, your browser does not support HTML5 geolocation.");
    }
}

function initMap1(){
	mymap = L.map('map1').setView([43.283251199999995, -2.1757952], 13);
	L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoiZW5la29hdHhhIiwiYSI6ImNrZ3dpb2JnMjBhaDEydHA4YWZ0Nzc5dmYifQ.cVDNrGVHBNYAdV0BGWa0xA'
}).addTo(mymap);
    L.Control.geocoder().addTo(mymap);

    var geocoder = L.Control.geocoder({
  defaultMarkGeocode: false
})
  .on('markgeocode', function(e) {
    var bbox = e.geocode.bbox;
    console.log(e.geocode);
    var poly = L.polygon([
      bbox.getSouthEast(),
      bbox.getNorthEast(),
      bbox.getNorthWest(),
      bbox.getSouthWest()
    ]).addTo(mymap);
    mymap.fitBounds(poly.getBounds());
  })
  .addTo(mymap);
}

function initMap2(){
	mymap2 = L.map('map2').setView([43.283251199999995, -2.1757952], 13);
	L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoiZW5la29hdHhhIiwiYSI6ImNrZ3dpb2JnMjBhaDEydHA4YWZ0Nzc5dmYifQ.cVDNrGVHBNYAdV0BGWa0xA'
}).addTo(mymap2);

	mymap2.on('click', function(e) {
		var positionInfo = "Lat: " + e.latlng.lat + ", " + "Long: " + e.latlng.lng;
        document.getElementById("positiveCoords").value = positionInfo;
	});
}

function paintPositive(positive){
	L.marker([positive.split(",")[0], positive.split(",")[1]]).addTo(mymap);
    var geocoder = L.Control.Geocoder.nominatim();
    geocoder.reverse(new L.LatLng('2', '31'), 1, function (results) {
        console.log(results);
    });
    
}