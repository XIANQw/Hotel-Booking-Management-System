<!doctype html>
<html lang="en">
<head>
    <title>accueil</title>
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
    <fieldset>
        <legend>Systeme de Gestion Hoteliere</legend>
        
        <%-- information --%>
        <%if(request.getAttribute("info")!=null) {%>
            <div id="alert" class="alert alert-<%=request.getAttribute("type")%>"><%=request.getAttribute("info")%></div>
        <%}%>

        <div id="zoneLogin">
            <h3>login</h3>
            <form action="${pageContext.request.contextPath}/Client?method=Login" method="post">
                <div class="form-group">
                    <label for="id_username">username: </label>
                    <input type="text" name="username" class="form-control" id="id_username" placeholder="login" autofocus
                           required/>
                </div>
                <div class="form-group">
                    <label for="id_password">password: </label>
                    <input type="password" name="password" class="form-control" id="id_password" placeholder="password"
                           required/>
                </div>
                <button type="submit" class="btn btn-primary float-right">Sign in</button>
            </form>
            <div id="gotoSignup">
                <span>Have not yet username? click </span>
                <ins id="ModeSignUp" class="text-success">here</ins>
            </div>
        </div>
        <div id="zoneSignUp" style="display:none">
            <h3>sign up</h3>
            <form action="${pageContext.request.contextPath}/Client?method=Signup" method="post">
                <div class="form-group">
                    <label for="id_nom">Username: </label>
                    <input type="text" name="username" class="form-control" id="id_nom" placeholder="nom"
                           required="required"/>
                </div>
                <div class="form-group">
                    <label for="id_pwd">Password: </label>
                    <input type="password" name="password" class="form-control" id="id_pwd" placeholder="password"
                           required="required"/>
                </div>
                <button type="submit" class="btn btn-primary float-right">Sign up</button>
            </form>
            <div id="gotoLogin">
                <span>Have got an account?</span>
                <ins id="ModeLogin" class="text-success">go to login</ins>
            </div>
        </div>
    </fieldset>
</div>
</body>
</html>