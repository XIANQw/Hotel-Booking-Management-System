<%@page import ="jar.bean.RessourceBean"%>
<%RessourceBean res = (RessourceBean)request.getAttribute("ressource");%>
<!--modifyRes.html-->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>modify ressource <%=res.getId()%></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/microproject/static/css/bootstrap.min.css">
    <script src="/microproject/static/js/jquery.min.js"></script>
    <script src="/microproject/static/js/popper.js"></script>
    <script src="/microproject/static/js/bootstrap.min.js"></script>
    <script src='/microproject/static/js/page.js'></script>>
</head>
<body>
<div class="container">
    <div id="modifyRessource">
        <fieldset>
            <legend>Modify a room or house</legend>
            <form action="${pageContext.request.contextPath}/Service?method=modifyRessource&id=<%=res.getId()%>" method="post">
                <div class="form-group">
                    <label>Type: </label>
                    <%if (res.getType().equals("room")) {%>
                    <input name="type" id="id_room" value="room" type="radio" checked>Room               
                    <input name="type" id="id_house" value="house" type="radio"/>House
                    <%} else {%>
                    <input name="type" id="id_room" value="room" type="radio">Room                    
                    <input name="type" id="id_house" value="house" type="radio" checked/>House
                    <%}%>
                </div>
                <div class="form-group">
                    <label for="id_price">Price: </label>
                    <input type="number" name="price" id="id_price" class="form-control" value="<%=res.getPrice()%>" placeholder="price" required/>
                </div>
                <div class="form-group">
                    <label for="id_number">Adresse: </label>
                    <input type="number" name="number" id="id_number" class="form-control" value="<%=res.getNumber()%>"
                            placeholder="Street number" required/>
                    <input type="text" name="street" id="id_street" class="form-control" value="<%=res.getStreet()%>"
                            placeholder="Street" required/>
                    <input type="number" name="postal" id="id_postal" class="form-control" value="<%=res.getPostal()%>"
                            placeholder="Postal number" required/>
                    <input type="text" name="city" id="id_city" class="form-control" value="<%=res.getCity()%>"
                            placeholder="City" required/>
                </div>
                <div class="form-group">
                    <label>Smoker: </label>
                    <%if (res.getSmoker().equals("y")) {%>
                    <input name="smoker" id="id_smoker" value="y" type="radio" checked>Yes
                    <input name="smoker" id="id_no_smoker" value="n" type="radio"/>No
                    <%} else {%>
                    <input name="smoker" id="id_smoker" value="y" type="radio">Yes
                    <input name="smoker" id="id_no_smoker" value="n" type="radio" checked/>No
                    <%}%>
                </div>
                <%if (res.getType().equals("room")) {%>
                <div id="optionRoom">
                    <div class="form-group">
                        <label for="id_room_type">room type: </label>
                        <select id="id_room_type" name="persons_room" class="form-control" placeholder="room type" value="<%=res.getPersons()%>"
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
                <button id="quitCreation" class="btn btn-primary">Cancel</button>
                <button type="submit" class="btn btn-primary float-right">Modify</button>
                <%} else {%>
                <div id="optionRoom" style="display: none">
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

                <div id="optionHouse" class="form-group">
                    <label for="id_num_room">number of room: </label>
                    <input type="number" name="persons_house" id="id_num_room" class="form-control" value="<%=res.getPersons()%>"
                        placeholder="Number of your room" require="false"/>
                </div>
                <button id="quitCreation" class="btn btn-primary">Cancel</button>
                <button type="submit" class="btn btn-primary float-right">Modify</button>
                <%}%>
            </form>
        </fieldset>
    </div>

</div>
</body>
</html>