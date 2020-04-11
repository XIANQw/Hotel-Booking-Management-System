<!--ressources.jsp-->
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
                <li><a href="${pageContext.request.contextPath}/Gopage?page=profile" class="text-success">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/Service?method=getCommandes&id=${user.id}" class="text-success">Commandes</a></li>
                <li><a href="${pageContext.request.contextPath}/Service?method=getRessources&id=${user.id}" class="text-success">Your houses</a></li>
                <li><a href="${pageContext.request.contextPath}/Client?method=Logout" class="text-success">Disconnect</a></li>
            </ul>
            </div>
        </div>
    </nav>
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
                        <input name="type" id="id_room" value="Room" type="radio" checked>Room
                        <input name="type" id="id_house" value="House" type="radio"/>House
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

                    <div id="optionRoom">
                        <div class="form-group">
                            <label for="id_room_type">room type: </label>
                            <select name="room_type" id="id_room_type" class="form-control" placeholder="room type"
                                    required>
                                <option value="Simple">Simple</option>
                                <option value="Double">Double</option>
                                <option value="Family">Family</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Smoker: </label>
                            <input name="smoker" id="id_smoker" value="y" type="radio" checked>Yes
                            <input name="smoker" id="id_no_smoker" value="n" type="radio"/>No
                        </div>
                    </div>
                    <div id="optionHouse" class="form-group" style="display: none">
                        <label for="id_type_house">House type: </label>
                        <select name="type_house" id="id_type_house" class="form-control" placeholder="House type"
                                required>
                            <option value="Small">Small (5 persons)</option>
                            <option value="Medium">Medium (10 persons)</option>
                            <option value="Big">Big (20 persons)</option>
                        </select>
                    </div>

                    <button id="quitCreation" class="btn btn-primary">Cancel</button>
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
                        <th scope="col">Number</th>
                        <th scope="col">Street</th>
                        <th scope="col">Postal</th>
                        <th scope="col">City</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%-- {% for item in res %}
                    <tr>
                        <th scope="row">{{item.numero}}</th>
                        <td>{{item.type}}</td>
                        <td>{{item.price}}</td>
                        <td><a href="/gestionnaire/consulterRes/?resId={{item.id}}" class="text-success"> Consulter</a>
                        </td>
                    </tr>
                    {% endfor %} --%>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><a id="ModeCreation" class="text-success">Add a house</a></td>
                    </tr>
                    </tbody>
                </table>
            </fieldset>
        </div>

    </div>


</div>
</body>
</html>
