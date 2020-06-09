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
                        <li><a id="gotoPageYourHouses" class="text-success">Your houses</a></li>
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
                <div id="DivProfileContent"></div>
                <div id="DivModifyProfile" style="display:none">
                    <form id="FormModifyProfile" method="post">
                        <div class="form-group">
                            <label>Family name: </label>
                            <input type="text" id="id_modify_profile_nom" name="nom" value= "" class="form-control" placeholder="nom"
                                required="required"/>
                        </div>
                        <div class="form-group">
                            <label>First name: </label>
                            <input type="text" id="id_modify_profile_prenom" name="prenom" value= "" class="form-control" placeholder="prenom"
                                required="required"/>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="text" id="id_modify_profile_email" name="email" value="" class="form-control"
                                placeholder="email"/>
                        </div>
                        <div class="form-group">
                            <label>Adresse</label>
                            <input type="text" id="id_modify_profile_adresse" name="adresse" value="" class="form-control"
                                placeholder="adresse"/>
                        </div>
                        <div class="form-group">
                            <label>Telephone</label>
                            <input type="text" id="id_modify_profile_telephone" name="tel" value="" class="form-control" placeholder="telphone"/>
                        </div>
                        <a type="button" id="ButtonQuitModifyProfile" class="btn btn-primary">Cancel</a>
                        <button type="button" id="ButtonModifyProfile" class="btn btn-primary float-right">Save</button>
                    </form>
                </div>
            </div>

            <div id="DivResDetails"></div>

            <div id="DivSendedDemands"></div>

            <div id="DivRecievedDemands"></div>

            <div id="DivYourHouses">
                <div id="DivResList"></div>
                <div id="DivAddRes" style="display:none" >
                    <fieldset>
                        <legend>Add a room or house</legend>
                        <form id="FormAddRes" method="post">
                            <div class="form-group">
                                <label>Type: </label>
                                <input name="type" id="id_room" onclick="gotoCreationRoom()" value="room" type="radio" checked>Room
                                <input name="type" id="id_house" onclick="gotoCreationHouse()" value="house" type="radio"/>House
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
                            <button id="ButtonAddRes" type="button" class="btn btn-primary float-right">Create</button>
                        </form>
                    </fieldset>
                </div>
            </div>
        </div>

    </fieldset>
</div>
</body>
</html>
