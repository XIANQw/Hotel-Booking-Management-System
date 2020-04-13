<%@page import ="jar.bean.RessourceBean"%>
<%RessourceBean res = (RessourceBean)request.getAttribute("ressource");%>
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
                    <li>
                        <a class="text-success" href="${pageContext.request.contextPath}/Service?method=modifyRessource&id=<%=res.getId()%>">modify</a>
                    </li>
                    <li>
                        <a class="text-success" href="${pageContext.request.contextPath}/Service?method=deleteRessource&id=<%=res.getId()%>">delete</a>
                    </li>
                    <li>
                    <a class="text-success" href="${pageContext.request.contextPath}/Service?method=getRessources" class="text-success">back</a>
                    </li>
                    <li>
                        <a class="text-success" href="/gestionnaire/consulterRes/consulterDemRes/?resId={{res.id}}">Commandes of this ressource</a>
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
            <div id='meubleDansRes'>
                <table class="table table-striped">
                    <thead class="thead-light">
                    <tr>
                        <li>Info de Meuble</li>
                        <th>Nom Meuble</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {% for item in resMeu %}
                    <tr>
                        <th>{{item.nom_Meuble}}</th>

                        {% if request.session.username == 'root'  %}
                        <td>{{item.status}}</td>
                        <td><a class="text-success"
                               href="/gestionnaire/consulterRes/removeMeu/?resId={{res.id}}&meuId={{item.id}}">Remove</a>
                        </td>
                        {%  endif %}
                    </tr>
                    {% endfor %}
                    </tbody>
                </table>
            </div>
        </div>

        {% if request.session.username == 'root'%}
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
                {% for item in meubles %}
                <tr>
                    <td>{{item.id}}</td>
                    <td>{{item.nom_Meuble}}</td>
                    <td>{{item.status}}</td>
                    <td><a href="/gestionnaire/consulterRes/ajouterMeu/?resId={{res.id}}&meuId={{item.id}}">Add in
                        <%=res.getType()%> <%=res.getId()%></a></td>
                    <td><a class="modify" href="javascript:;">Modify</a></td>
                    <td><a href="/gestionnaire/consulterRes/deleteMeu/?resId={{res.id}}&meuId={{item.id}}">Delete</a>
                    </td>
                </tr>
                {% endfor %}
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><input id="hrefCreerMeu" type="button" class="btn btn-primary" value="Creer nouveau meuble"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        {% endif %}
    </div>

    <div class="row-fluid">
        <div id="creerMeubleForm" class="col-md-8 col-md-offset-4">
            <form id="formCreerMeu" class="form-inline" action="/gestionnaire/consulterRes/creerMeuble/" method="post"
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
    </div>
</div>
<div class="row-fluid">
    <form id="formModifMeu" class="form-inline" action="/gestionnaire/consulterRes/modifMeuble/" method="post"
          onsubmit="checkModif()">
        <input type='hidden' id="modifMeuNom" type="hidden" name="meuNom">
        <input type='hidden' id="modifMeuId" type="hidden" name="meuId">
        <input type="hidden" name="resId" value="<%=res.getId()%>">
    </form>
</div>
</body>
</html>
