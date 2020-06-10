function autoRefreshWheather() {
    getWheather();
    setInterval(getWheather, 300000);
}


function getWheather() {
    var city = $("#city").attr("data-city");
    $.ajax({
        type: "POST",
        data: { dataCity: city },
        url: "Service?method=getWheather",
        success: function (result, status) {
            var str = result;
            var resp = JSON.parse(str);
            var html = htmlWheather(resp);
            $('#DivWheather').html(html);
        }, error: function (res) {
            var str = res;
            alert("error:" + str);
            alert("error=" + res.responseTest)
        }
    });
}

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: { lat: -34.397, lng: 150.644 }
    });
    var geocoder = new google.maps.Geocoder();
    geocodeAddress(geocoder, map);
}

function geocodeAddress(geocoder, resultsMap) {
    //    var idd = $(this).attr("data-id");

    //var address = document.getElementById('adresse').value;
    var address = $("#address").attr("data-address");
    //alert(address);
    geocoder.geocode({ 'address': address }, function (results, status) {
        if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: resultsMap,
                position: results[0].geometry.location
            });
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}
