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
                        <a class="text-success" href="${pageContext.request.contextPath}/Service?method=getCommandesFromRessource&id=<%=res.getId()%>">
                        Commandes of this ressource</a>
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

        
        <div id='toutRessource' class="col-md-8">
            <table class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <li>Liste Meuble disponible</li>
                    <th>#</th>
                    <th>Nom Meuble</th>
                    <th>Status</th>
                    <th></th>
                    <th>Operation</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="${pageContext.request.contextPath}/Service?method=addItem">Add in
                        <%=res.getType()%> <%=res.getId()%></a></td>
                    <td><a class="modify" href="javascript:;">Modify</a></td>
                    <td><a href="${pageContext.request.contextPath}/Service?method=removeItem">Delete</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <%-- <div class="row-fluid">
        <div id="creerMeubleForm" class="col-md-8 col-md-offset-4">
            <form id="formCreerMeu" class="form-inline" action="${pageContext.request.contextPath}/Service?method=addItem" method="post"
                  onsubmit="check()">
                <div class="title">
                    <p>Create an item</p><a onclick="layer.style.display=none"></a>
                </div>
                <div class="form-group">
                    <label>Item: </label>
                    <input class="form-control" type="input" id="nomMeuble" name="nameItem"
                           placeholder="Item name"/>
                </div>
                <input type="hidden" name="resId" value="<%=res.getId()%>">
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
        </div>
    </div> --%>
</div>
<%-- <div class="row-fluid">
    <form id="formModifMeu" class="form-inline" action="${pageContext.request.contextPath}/Service?method=modifyItem" method="post"
          onsubmit="checkModif()">
        <input type='hidden' id="modifMeuNom" type="hidden" name="meuNom">
        <input type='hidden' id="modifMeuId" type="hidden" name="meuId">
        <input type="hidden" name="resId" value="<%=res.getId()%>">
    </form>
</div> --%>
</body>
</html>
