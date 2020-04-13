<!--profile.jsp-->
<%@ page import ="jar.bean.UserBean"%>
<%@ page import ="jar.bean.ProfileBean"%>
<%ProfileBean profile = (ProfileBean)request.getAttribute("profile");%>
<%UserBean user = (UserBean)request.getSession().getAttribute("user");%>
<!DOCTYPE html>
<html lang="en">
<head>
<!--profile.html-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/popper.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src='/static/js/page.js'></script>
    <title><%=user.getUsername()%></title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand"><%=user.getUsername()%></a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/Gopage?page=modifyAccount" class="text-success">modify account</a></li>
                    <li><a href="${pageContext.request.contextPath}/Gopage?page=mainPage" class="text-success">back</a></li>
                    <li><a href="${pageContext.request.contextPath}/Client?method=Logout" class="text-success">disconnect</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <%-- information --%>
    <%if(request.getAttribute("info")!=null) {%>
        <div id="alert" class="alert alert-<%=request.getAttribute("type")%>"><%=request.getAttribute("info")%></div>
    <%}%>
    
    <div class="container bootstrap snippet">
        <div class="row">
            <div class="col-sm-10">
                <h1>Bonjour <%=profile.getNom()%> <%=profile.getPrenom()%> !</h1></div>
            <div class="col-sm-2">
                <a href="" class="pull-right"><img title="profile image" class="img-circle img-responsive"
                src="https://bootdey.com/img/Content/avatar/avatar1.png"></a>
            </div>
        </div>
    </div>

    <div class="col-sm-5">
        <ul class="list-group">
            <li class="list-group-item text-muted">Profile</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Email</strong></span>
                <%=profile.getEmail()%>
            </li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Adresse</strong></span>
                <%=profile.getAdresse()%>
            </li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Telephone</strong></span>
                <%=profile.getTelephone()%>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
