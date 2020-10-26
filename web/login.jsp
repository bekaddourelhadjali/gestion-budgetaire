<%-- 
    Document   : login.jsp
    Created on : May 13, 2018, 2:49:04 PM
    Author     : bekal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />

        <meta http-equiv="pragma" content="no-cache" />
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap-grid.min.css">
        <title>LOG IN</title>
    </head>


    <body><%!String status;%>
        <% String email;
            response.setHeader("Cache-Control", "no-cache ,no-store , must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            email = (String) request.getSession().getAttribute("email");
            if (email != null) {
                request.getRequestDispatcher("/loginservlet").forward(request, response);
            }
            if (request.getAttribute("message") != null) {
                status = (String) request.getAttribute("message");
            } else {
                status = "";
            }


        %>
        <div class="main-agileits">

            <div class="mainw-agileinfo">

                <div class="login-form">  
                    <div class="login-agileits-top"> 	
                        <form action="loginservlet" method="post">
                            <input type="hidden" name="command" value="login">
                            <p>Email </p>
                            <input type="text" name="email" placeholder="email" required>
                            <p>Mot de passe</p>
                            <input type="password" name="password" placeholder="Mot de passe" required>
                            <h6 class="status"><%=status%></h6>
                            <button  class="btn btn-primary" type="submit">LOG IN</button>
                        </form> 	
                    </div> 
                    <div class="login-agileits-bottom"> 
                        <h6><a href="#" >Mot de passe oublié?</a></h6>
                    </div>  

                </div> 
            </div>	
        </div>
        <footer><br>Copyright © 2018 by LABRI-SBA All rights reserved</footer>  
        <script src="css/jquery-3.3.1.js"></script>
        <script src="css/bootstrap/js/bootstrap.min.js"></script>

    </body>
</html>
