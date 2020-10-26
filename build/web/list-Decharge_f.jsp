<%-- 
    Document   : Commandes
    Created on : May 15, 2018, 12:04:37 AM
    Author     : bekal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%!String email;
    String type;
    int id;
    String nom;%>
<%  if (request.getSession().getAttribute("email") != null) {
        email = (String) request.getSession().getAttribute("email");
        type = (String) request.getSession().getAttribute("type");
        nom = (String) request.getSession().getAttribute("nom");
        id = (int) request.getSession().getAttribute("id");
    } else {
        request.getRequestDispatcher("/loginservlet").forward(request, response);
    }
    if (email == null || !type.equals("admin")) {

        request.getRequestDispatcher("/loginservlet").forward(request, response);
    } else {%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/style3.css"> 
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="css/ionicons.min.css">
        <title>Decharges</title>
        <%
            response.setHeader("Cache-Control", "no-cache ,no-store , must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");%>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg   " style="background-color:rgba(4,27,72,0.8);">
                <a class="navbar-brand text-white" href="MembreServlet?command=load&id=<%=id%>"><%=nom%></a>
                <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <!--<span class="navbar-toggler-icon"></span>-->
                    <i class="ion-android-menu"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                GESTION BUDGETAIRE
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                                <a class="dropdown-item " href="BudgetServlet">BUDGETS</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="ArticleServlet">ARTICLES</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="SectionServlet">SECTIONS</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="RepartitionServlet">REPARTITIONS</a>
                        </li>


                        <li class="nav-item dropdown nav-item ">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                BON DES COMMANDES
                            </a>
                            <div class="dropdown-menu drop" aria-labelledby="navbarDropdown2">
                                <a class="dropdown-item " href="CommandeServlet">COMMANDES</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="FournisseurServlet">FOURNISSEURS</a>

                        </li>
                        <li class="nav-item dropdown nav-item">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                GESTION DES EQUIPES
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                                <a class="dropdown-item " href="EquipeServlet">EQUIPES</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="MembreServlet">MEMBRES</a>

                        </li>
                        <li class="nav-item dropdown nav-item selected">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                DECHARGE
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                                <a class="dropdown-item " href="DechargeServlet">DESCHARGE DE MATERIELS</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item active" href="DechargeServlet_fn">DECHARGE DE FOURNITURES</a>

                        </li>
                        <li class="nav-item dropdown nav-item">
                            <a class="nav-link dropdown-toggle text-white " href="#" id="navbarDropdown4" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                AFFECTATION
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown4">
                                <a class="dropdown-item " href="AffectationServlet">AFFECTATION DES MATERIELS</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item " href="AffectationServlet_fn">AFFECTATION DES FOURNITURES</a>

                        </li>
                    </ul>

                    <a class=" btn btn-primary" role="button" href="loginservlet?command=logout">LOG OUT</a>
                </div>
            </nav>

        </header>
        <div class="container">
            <section class="menu-section clear">
                <div class="container">

                    <div class="clearfix">
                        <div class="float-xl-right float-md-right float-lg-right ">
                            <form class="form-inline my-2 my-lg-0" action="DechargeServlet_fn">
                                <input type="hidden" name="command" value="search">
                                <input class="form-control my-2 mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
                                <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
                            </form>
                        </div>
                        <div class="float-left">

                            <form  class="form-inline my-2 my-lg-0" action="DechargeServlet_fn" >
                                <input type="hidden" name="command"  value="ADD">
                                <select class="custom-select form-control my-2 mr-sm-2 " name="membre">
                                    <option selected>Choisir un Membre</option> 
                                    <c:forEach var="c" items="${chefs}">
                                        <option value="${c.id}">${c.nom}</option>
                                    </c:forEach>   
                                </select>
                                <select class="custom-select form-control my-2 mr-sm-2 " name="fourniture">
                                    <option selected>Choisir une fourniture</option> 
                                    <c:forEach var="f" items="${fournitures}">
                                        <option value="${f.getId()}">${f.getDesignation()}</option>
                                    </c:forEach>   
                                </select>
                                <div class="form-group">
                                    <div class="input-group ">

                                        <input type="number" name="quantite" placeholder='Quantite' class="form-control my-2 mr-sm-2 " required> 

                                    </div>
                                </div>

                                <button class="btn btn-outline-primary  my-2  my-sm-0 " type="submit">Ajouter</button>

                            </form>
                        </div>
                    </div>
                </div>
            </section>

            <section class="tab-section clear">
                <table id="myTable2" class="table table-sm table-responsive-md table-responsive-sm  table-striped table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th onclick="sortTable(0)">Chef</th>
                            <th onclick="sortTable(1)">Fourniture</th>
                            <th onclick="sortTable(2)">Quantite</th>
                            <th onclick="sortTable(3)">Date d'affectation</th>
                            <th></th>
                        </tr>
                    </thead>

             
                    <div class="tab-body">
                        <tbody> 
                            <c:forEach var="temp" items="${DECHARGE_LIST}">
                                <c:url var="deleteLink" value="DechargeServlet_fn">
                                    <c:param name="command" value="DELETE" />
                                    <c:param name="membreid" value="${temp.idmembre}" />
                                    <c:param name="fournitureid" value="${temp.idfourniture}"/>
                                </c:url>

                                <tr>
                                    <td>  ${temp.membre}  </td>
                                    <td>  ${temp.fourniture}</td>
                                    <td> ${temp.quantite} </td>
                                    <td> ${temp.dateaffectation} </td>
                                    <td><a href="#"></a>
                                        <a class="del"  href="${deleteLink}"><i class="ion-android-delete"></i></a>
                                    </td>
                                </tr>

                            </c:forEach>
                        </tbody>
                 </div>
                </table>
            </section>

        </div>
        <footer><br>Copyright © 2018 by LABRI-SBA All rights reserved</footer>     
        <script src="css/jquery-3.3.1.js"></script>
        <script src="css/bootstrap/js/bootstrap.min.js"></script>
        <script src="css/tablesorter.js"></script>
        <script src="css/script.js"></script>   

    </body>

</html>

<%}%>






