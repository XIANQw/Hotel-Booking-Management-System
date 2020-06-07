<!--ressourceList.jsp-->
<%@page import ="jar.bean.RessourceBean"%>
<%@page import ="java.util.List"%>
<%List<RessourceBean> ressources = (List<RessourceBean>)request.getAttribute("ressources");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Your ressources</title>
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
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">${user.username}</a>
            </div>
            <div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/Gopage?page=mainPage">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/Client?method=getProfile&id=${user.getId()}" class="text-success">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/Service?method=getSendedDemands" class="text-success">Sended demands</a></li>
                <li><a href="${pageContext.request.contextPath}/Service?method=getRecievedDemands" class="text-success">Recieved demands</a></li>                        
                <li><a href="${pageContext.request.contextPath}/Service?method=getRessources" class="text-success">Your houses</a></li>
                <li><a href="${pageContext.request.contextPath}/Client?method=Logout" class="text-success">Disconnect</a></li>
            </ul>
            </div>
        </div>
    </nav>
    <%-- information --%>
    <%if(request.getAttribute("info")!=null) {%>
        <div id="alert" class="alert alert-<%=request.getAttribute("type")%>"><%=request.getAttribute("info")%></div>
    <%}%>
    <div id="zoneRessource">
        <div id="createRessource" style="display:none">
            <fieldset>
                <legend>Add a room or house</legend>
                <form action="${pageContext.request.contextPath}/Service?method=createRessource" method="post">
                    <div class="form-group">
                        <label>Type: </label>
                        <input name="type" id="id_room" value="room" type="radio" checked>Room
                        <input name="type" id="id_house" value="house" type="radio"/>House
                    </div>
                    <div class="form-group">
                        <label for="id_price">Price: </label>
                        <input type="number" name="price" id="id_price" class="form-control" placeholder="price" required/>
                    </div>
                    <div class="form-group">
                        <label for="id_number">Adresse: </label>
                        <input type="number" name="number" id="id_number" class="form-control"
                                placeholder="Street number" required/>
                        <input type="text" name="street" id="id_street" class="form-control"
                                placeholder="Street" required/>
                        <input type="number" name="postal" id="id_postal" class="form-control"
                                placeholder="Postal number" required/>
                        <input type="text" name="city" id="id_city" class="form-control"
                                placeholder="City" required/>
                    </div>
                    <div class="form-group">
                        <label>Smoker: </label>
                        <input name="smoker" id="id_smoker" value="y" type="radio" checked>Yes
                        <input name="smoker" id="id_no_smoker" value="n" type="radio"/>No
                    </div>
                    <div id="optionRoom">
                        <div class="form-group">
                            <label for="id_room_type">room type: </label>
                            <select id="id_room_type" name="persons_room" class="form-control" placeholder="room type"
                                    required>
                                <option value="1">Simple</option>
                                <option value="2">Double</option>
                                <option value="3">Family</option>
                            </select>
                        </div>
                    </div>

                    <div id="optionHouse" class="form-group" style="display: none">
                        <label for="id_num_room">number of room: </label>
                        <input type="number" name="persons_house" id="id_num_room" class="form-control" 
                            placeholder="Number of your room" require="false"/>
                    </div>
                    <button id="RessourceQuitCreation" class="btn btn-primary">Cancel</button>
                    <button type="submit" class="btn btn-primary float-right">Create</button>
                </form>
            </fieldset>
        </div>

        <div id="infoRessource">
            <fieldset>
                <legend>Les informations des ressources</legend>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Type</th>
                        <th scope="col">Price</th>
                        <th scope="col">Persons</th>
                        <th scope="col">Adresse</th>
                        <th scope="col">Smoker</th>                       
                    </tr>
                    </thead>
                    <tbody>
                    <%for(RessourceBean ress: ressources) {%>
                    <tr>
                        <td><%=ress.getId()%></td>
                        <td><%=ress.getType()%></td>
                        <td><%=ress.getPrice()%></td>
                        <td><%=ress.getPersons()%></td>
                        <td><%=ress.getAdresse()%></td>
                        <td><%=ress.getSmoker()%></td>
                        <td><a href="${pageContext.request.contextPath}/Service?method=infoRessource&id=<%=ress.getId()%>" class="text-success">details</a></td>
                        <td><a href="${pageContext.request.contextPath}/Service?method=deleteRessource&id=<%=ress.getId()%>" class="text-success">delete</a></td>
                    </tr>
                    <%}%>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><a id="RessourceModeCreation" class="text-success">Add a house</a></td></tr>
                    </tbody>
                </table>
            </fieldset>
        </div>

    </div>


</div>
</body>
</html>
