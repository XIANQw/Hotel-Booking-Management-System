<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.HashMap"%>
<%@ page import ="jar.bean.UserBean"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link rel="stylesheet" href="/microproject/static/css/bootstrap.min.css">
    <script src="/microproject/static/js/jquery.min.js"></script>
    <script src="/microproject/static/js/popper.js"></script>
    <script src="/microproject/static/js/bootstrap.min.js"></script>
    <script src='/microproject/static/js/page.js'></script>
    <title>main</title>
</head>
<body>
<div class="container">
    <div class='row-fluid'>
        <div id="infoRessource">
            <fieldset>
                <legend>All users</legend>
                <%if(request.getAttribute("info")!=null) {%>
                    <div id="alert" class="alert alert-<%=request.getAttribute("type")%>"><%=request.getAttribute("info")%></div>
                <%}%>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Username</th>
                        <th scope="col">Password</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% HashMap<String, UserBean> users=(HashMap<String, UserBean>)request.getAttribute("users");
                        if(users!=null && !users.isEmpty()){
                            for(String username : users.keySet()){
                    %>			
                                <tr>
                                <td><%=users.get(username).getId()%></td>
                                <td><%=username%></td>
                                <td><%=users.get(username).getPassword()%></td>
                                </tr>
                    <%      }
                        }
                    %>
                    </tbody>
                </table>
            </fieldset>
        </div>
    </div>
</div>

</body>
</html>