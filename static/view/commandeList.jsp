<%@ page import ="jar.bean.*"%>
<%@ page import ="jar.dao.*"%>
<%@ page import ="java.util.*"%>
<%@ page import = "java.text.SimpleDateFormat"%>
<%
List<CommandeBean> cmds = (List<CommandeBean>)request.getAttribute("cmds");
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
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
</head>
<body>
<div class="container">
    <div id="infoRessource">
        <fieldset>
            <nav class="navbar navbar-inverse" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header"><a class="navbar-brand">${user.getUsername()}</a></div>
                    <div>
                        <ul class="nav navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/Gopage?page=mainPage">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/Client?method=getProfile" class="text-success">Profile</a></li>
                            <li><a href="${pageContext.request.contextPath}/Service?method=getCommandes" class="text-success">Sended commandes</a></li>
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

            <div id="commandeList">
            <legend>Your commandes</legend>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Sender</th>
                    <th scope="col">Owner</th>
                    <th scope="col">Ressource</th>
                    <th scope="col">Checkin date</th>
                    <th scope="col">Checkout date</th>
                    <th scope="col">Create Time</th>
                </tr>
                </thead>
                <tbody>
                <% for(CommandeBean cmd : cmds) { %>
                    <%
                    HashMap<String, String> attrs = new HashMap<>();
                    attrs.put("id", Integer.toString(cmd.getIdr()));
                    RessourceBean res = RessourceDao.getRessourcesFrom(attrs).get(0);
                    %>
                    <tr>
                    <td><%=cmd.getId()%></td>
                    <td><a href="${pageContext.request.contextPath}/Client?method=getProfile&id=<%=cmd.getIdu()%>" class="text-success">
                    <%=UserDao.getUsername(cmd.getIdu())%></a></td>
                    <td><a href="${pageContext.request.contextPath}/Client?method=getProfile&id=<%=res.getIdu()%>" class="text-success">
                    <%=UserDao.getUsername(res.getIdu())%></a></td>
                    <td><a href="${pageContext.request.contextPath}/Service?method=infoRessource&id=<%=res.getId()%>" class="text-success"><%=res.getType() + " " + res.getId()%></a></td>
                    <td><%=cmd.getCheckin().toString()%></td>
                    <td><%=cmd.getCheckout().toString()%></td>
                    <td><%=df.format(cmd.getCreateTime())%></td>
                    <td><a href="${pageContext.request.contextPath}/Service?method=DeleteCommande" class="text-success">Delete</a></td>
                    </tr>
                <%}%>
                </tbody>
            </table>
            </div>
        </fieldset>
    </div>
    
</div>

</body>
</html>