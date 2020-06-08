<%@page import ="jar.bean.UserBean"%>
<%@page import ="jar.bean.RessourceBean"%>
<%@page import ="java.util.List"%>
<%List<RessourceBean> result = (List<RessourceBean>)request.getSession().getAttribute("result");%>

<!doctype html>
<html lang="en">
<head>
    <title>main</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/microproject/static/css/bootstrap.min.css">
    <script src="/microproject/static/js/jquery.min.js"></script>
    <script src="/microproject/static/js/popper.js"></script>
    <script src="/microproject/static/js/bootstrap.min.js"></script>
    <script src='/microproject/static/js/page.js'></script>
    <script src='/microproject/static/js/mainPage.js'></script>
</head>
<body>
<div class="container">
    <fieldset>
        <nav class="navbar navbar-inverse" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header"><a class="navbar-brand">${user.getUsername()}</a><a id="userId" style="display: none">${user.getId()}</a></div>
                <div>
                    <ul class="nav navbar-nav">
                        <li><a id="gotoPageHome" class="text-success">Home</a></li>
                        <li><a id="gotoPageProfile" class="text-success">Profile</a></li>
                        <li><a id="gotoPageSendedDemands" class="text-success">Sended demands</a></li>
                        <li><a id="gotoPageRecievedDemands" class="text-success">Recieved demands</a></li>                        
                        <li><a id="gotoPageYourHouse" class="text-success">Your houses</a></li>
                        <li><a href="${pageContext.request.contextPath}/Client?method=Logout" class="text-success">Disconnect</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <%-- information --%>
        <div id="divAlert"> 
        </div>
        <div id="mainDiv">
            <div id="DivSearch">
                <div id="createDemande">
                    <legend>Get a house or room</legend>
                    <form id="FormSearch" method="post">
                        <div id="inputDemande">
                            <div id="plan1">
                                <div class="form-group">
                                    <label>Destination</label>
                                    <input name="destination" type="text" class="form-control" required
                                        placeholder="destination"/>
                                </div>
                                <div class="form-group">
                                    <label>Check in date</label>
                                    <input name="checkin" type="date" class="form-control" required
                                        placeholder="checkin date"/>
                                </div>
                                <div class="form-group">
                                    <label>Check out date</label>
                                    <input name="checkout" type="date" class="form-control" required
                                        placeholder="checkout date"/>
                                </div>
                                <div class="form-group">
                                    <label>Number of people</label>
                                    <input name="nb" type="number" class="form-control" required
                                        placeholder="How many people ?"/>
                                </div>
                                <div class="form-group">
                                    <label>Type: </label>
                                    <input name="type" id="id_room" value="room" type="radio" checked>Room
                                    <input name="type" id="id_house" value="house" type="radio"/>House
                                </div>
                                <div class="form-group">
                                    <label>Smoker: </label>
                                    <input name="smoker" id="id_smoker" value="y" type="radio" >Yes
                                    <input name="smoker" id="id_no_smoker" value="n" type="radio" checked/>No
                                </div>
                            </div>
                        </div>
                        <button id="ButtonSearch"  type="button" class="btn btn-primary float-right">Go</button>
                    </form>
                </div>
                <div id="resultOfSearch">
                </div>
            </div>

            <div id="DivProfile">
            </div>

            <div id="DivResDetails">
            </div>

            <div id="DivSendedDemands">
            </div>

            <div id="DivRecievedDemands">
            </div>
        </div>

        <%if (result!=null) {%>
        <div>
        <legend>Search result</legend>
        <table class="table table-striped">
            <thead>
            <tr>
            <th scope="col">id</th>
            <th scope="col">Type</th>
            <th scope="col">Price</th>
            <th scope="col">Persons</th>
            <th scope="col">Adresse</th>                  
            </tr>
            </thead>
            <tbody>
            <%for (RessourceBean res : result) {%>
                <tr>
                <td><%=res.getId()%></td>
                <td><%=res.getType()%></td>
                <td><%=res.getPrice()%></td>
                <td><%=res.getPersons()%></td>
                <td><%=res.getAdresse()%></td>
                <td><a href="${pageContext.request.contextPath}/Service?method=infoRessource&id=<%=res.getId()%>" class="text-success">details</a></td>
                <td><a href="${pageContext.request.contextPath}/Service?method=sendDemand&id=<%=res.getId()%>" class="text-success">reserve</a></td>
                </tr>
            <%}%>
            </tbody>
        </table>
        </div>

        <%}%>
    </fieldset>
</div>
</body>
</html>
