<!--profile.jsp-->
<%@ page import ="jar.bean.UserBean"%>
<%@ page import ="jar.bean.ProfileBean"%>
<%ProfileBean profile = (ProfileBean)request.getAttribute("profile");%>
<!--modifyAccount.html-->
<!DOCTYPE html>
<html lang="en">
<head>
<!--modifyAccount.html-->
    <title>modify your account</title>
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
                <a class="navbar-brand">${user.getUsername()}</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="Client?method=Logout" class="text-success">Disconnect</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <%-- information --%>
    <%if(request.getAttribute("info")!=null) {%>
        <div id="alert" class="alert alert-<%=request.getAttribute("type")%>"><%=request.getAttribute("info")%></div>
    <%}%>

    <fieldset>
        <legend>Account information</legend>
        <div id="modifyCompte">
            <h2>Modify your account</h2>
            <%if (profile != null) {%>
            <form action="${pageContext.request.contextPath}/Client?method=modifyProfile" method="post">
                <div class="form-group">
                    <label>Family name: </label>
                    <input type="text" name="nom" value= "<%=profile.getNom()%>" class="form-control" placeholder="nom"
                           required="required"/>
                </div>
                <div class="form-group">
                    <label>First name: </label>
                    <input type="text" name="prenom" value= "<%=profile.getPrenom()%>" class="form-control" placeholder="prenom"
                           required="required"/>  
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" name="email" value="<%=profile.getEmail()%>" class="form-control"
                           placeholder="email"/>
                </div>
                <div class="form-group">
                    <label>Adresse</label>
                    <input type="text" name="adresse" value="<%=profile.getAdresse()%>" class="form-control"
                           placeholder="adresse"/>
                </div>
                <div class="form-group">
                    <label>Telephone</label>
                    <input type="text" name="tel" value="<%=profile.getTelephone()%>" class="form-control" placeholder="telphone"/>
                </div>
                <a type="button" href="${pageContext.request.contextPath}/Client?method=getProfile" class="btn btn-primary">Cancel</a>
                <button type="submit" class="btn btn-primary float-right">Save</button>
            </form>
            <%} else {%>
                <form action="${pageContext.request.contextPath}/Client?method=modifyProfile" method="post">
                <div class="form-group">
                    <label>Family name: </label>
                    <input type="text" name="nom" value= "" class="form-control" placeholder="nom"
                           required="required"/>
                </div>
                <div class="form-group">
                    <label>First name: </label>
                    <input type="text" name="prenom" value= "" class="form-control" placeholder="prenom"
                           required="required"/>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" name="email" value="" class="form-control"
                           placeholder="email"/>
                </div>
                <div class="form-group">
                    <label>Adresse</label>
                    <input type="text" name="adresse" value="" class="form-control"
                           placeholder="adresse"/>
                </div>
                <div class="form-group">
                    <label>Telephone</label>
                    <input type="text" name="tel" value="" class="form-control" placeholder="telphone"/>
                </div>
                <a type="button" href="${pageContext.request.contextPath}/Client?method=getProfile" class="btn btn-primary">Cancel</a>
                <button type="submit" class="btn btn-primary float-right">Save</button>
            </form>
            <%}%>
        </div>
    </fieldset>
</div>
</body>
</html>
