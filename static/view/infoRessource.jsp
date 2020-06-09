<%@page import ="jar.bean.RessourceBean"%>
<%@page import ="jar.bean.UserBean"%>
<%@page import ="jar.bean.ProfileBean"%>
<%@page import ="jar.dao.ProfileDao"%>
<%RessourceBean res = (RessourceBean)request.getAttribute("ressource");%>
<%UserBean user = (UserBean)request.getSession().getAttribute("user");%>
<%ProfileBean ownerInfo = ProfileDao.getProfileFromUser(res.getIdu()); %>
<!--infoRessource.html-->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>ressource</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/microproject/static/css/bootstrap.min.css">
    <script src="/microproject/static/js/jquery.min.js"></script>
    <script src="/microproject/static/js/popper.js"></script>
    <script src="/microproject/static/js/bootstrap.min.js"></script>
    <script src='/microproject/static/js/page.js'></script>
</head>
<body>
<div class="container">
    <div class='row-fluid'>
        <nav class="navbar navbar-inverse" role="navigation">

            <div class="navbar-header">
                <a class="navbar-brand">Information</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <%if(user.getId() == res.getIdu()) {%>
                    <li>
                        <a class="text-success" href="${pageContext.request.contextPath}/Gopage?page=modifyRessource&id=<%=res.getId()%>">Modify</a>
                    </li>
                    <li>
                        <a class="text-success" href="${pageContext.request.contextPath}/Service?method=deleteRessource&id=<%=res.getId()%>">Delete</a>
                    </li>
                    <li>
                        <a class="text-success" href="${pageContext.request.contextPath}/Service?method=getDemandsFromRessource&id=<%=res.getId()%>">
                        Demands of this ressource</a>
                    </li>
                    <%}%>
                    <li>
                        <a class="text-success" href="${pageContext.request.contextPath}/Gopage?page=mainPage">Back</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>

    <%-- information --%>
    <%if(request.getAttribute("info")!=null) {%>
        <div id="alert" class="alert alert-<%=request.getAttribute("type")%>"><%=request.getAttribute("info")%></div>
    <%}%>

    <div class='row-fluid'>
        <div class="col-md-4 ">
            <div id='ressourceInfo'>
                <ul class="list-group">
                    <li class="list-group-item text-muted">Ressource</li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Numero</strong></span>
                        <%=res.getId()%>
                    </li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Type</strong></span>
                        <%=res.getType()%>
                    </li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Price</strong></span>
                        <%=res.getPrice()%>
                    </li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Adresse</strong></span>
                        <%=res.getAdresse()%>
                    </li>
                </ul>
            </div>
            <div id='infoOwner'>
                <ul class="list-group">
                    <li class="list-group-item text-muted">Owner's information</li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Owner</strong></span>
                        <%=ownerInfo.getPrenom() + " " + ownerInfo.getNom()%>
                    </li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Email</strong></span>
                        <%=ownerInfo.getEmail()%>
                    </li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong>Telephone</strong></span>
                        <%=ownerInfo.getTelephone()%>
                    </li>
                </ul>
            </div>
        </div>

        <!--google map api----------------------------------------------------->
        <div id='map' style="width:500px;height:380px;" class="col-md-8"></div>
        <input type="hidden" id="adresse"  value="<%=res.getAdresseForMap()%>">

      <script>
          function initMap() {
            var map = new google.maps.Map(document.getElementById('map'), {
              zoom: 15,
              center: {lat: -34.397, lng: 150.644}
            });
            var geocoder = new google.maps.Geocoder();

              geocodeAddress(geocoder, map);

          }
          function geocodeAddress(geocoder, resultsMap) {
            var address = document.getElementById('adresse').value;
            geocoder.geocode({'address': address}, function(results, status) {
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
        </script>
        <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpVUVmmjb1fQP25RB5TCYR20_3WkKlCok&callback=initMap">
        </script>

        <!--end google map api----------------------------------------------------->

                <!----getWheather---------------------------------------->

                <button id="getWheather"  type="button" class="btn btn-primary float-right">getWheather
                </button>
                <input type="hidden" id="city"  value="<%=res.getCity()%>">
        
                <div id="DivWheather">
                </div>
          
                <!----getWheather end---------------------------------------->
    </div>
</div>
</body>
</html>
