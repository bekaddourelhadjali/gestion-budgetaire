<%-- 
    Document   : Commandes
    Created on : May 15, 2018, 12:04:37 AM
    Author     : bekal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%!String email;
    int id;
    String nom;
    String type;%>
<%if (request.getSession().getAttribute("email") != null) {
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
        <title>Fournisseurs</title>
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
                      
                    
                        <li class="nav-item dropdown nav-item selected">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                BON DES COMMANDES
                            </a>
                            <div class="dropdown-menu drop" aria-labelledby="navbarDropdown2">
                                <a class="dropdown-item " href="CommandeServlet">COMMANDES</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item active" href="FournisseurServlet">FOURNISSEURS</a>
                                
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
                        <li class="nav-item dropdown nav-item">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                               DECHARGE
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                                <a class="dropdown-item " href="DechargeServlet">DESCHARGE DE MATERIELS</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="DechargeServlet_fn">DECHARGE DE FOURNITURES</a>
                                
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


                <div class="clearfix">
                    <div class="float-xl-right float-md-right float-lg-right ">
                        <form class="form-inline my-2 my-lg-0" action="FournisseurServlet">
                            <input type="hidden" name="command" value="search">
                            <input class="form-control my-2 mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </div>
                    <div class="float-xl-left float-md-left float-lg-left ">
                        <a class="btn btn-primary  my-3 my-lg-2 " role="button" href='FournisseurServlet?command=addp'>Ajouter un Fournisseur</a>
                    </div>
                </div>
            </section>

            <section class="tab-section clear">
                <table id="myTable2" class="table table-sm table-responsive-md table-responsive-sm  table-striped table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th onclick="sortTable(0)">Nom</th>
                            <th onclick="sortTable(1)">Type</th>
                            <th onclick="sortTable(2)">Adresse</th>
                            <th onclick="sortTable(3)">Telephone</th>
                            <th onclick="sortTable(4)">Types</th>
                            <th></th>
                        </tr>
                    </thead>

                    </div>
                    <div class="tab-body">

                        <tbody>              
                            <c:forEach var="f" items="${fournisseurs}">
                                <c:url var="upLink" value="FournisseurServlet">
                                    <c:param name="command" value="load" />
                                    <c:param name="id" value="${f.id}" />
                                </c:url>
                                <c:url var="deLink" value="FournisseurServlet">
                                    <c:param name="command" value="delete" />
                                    <c:param name="id" value="${f.id}" />
                                </c:url>
                                <tr>
                                    <td> ${f.nom} </td>
                                    <td> ${f.type} </td>
                                    <td> ${f.adresse} </td>
                                    <td> ${f.telephone} </td>
                                    <td> ${f.types} </td>
                                    <td><a class="" href="${upLink}" ><i class="ion-settings"></i></a>
                                        <a class="del"  href="${deLink}"><i class="ion-android-delete"></i></a></td>

                                </tr>

                            </c:forEach>

                        </tbody>
                </table>

            </section>

        </div>

        <script src="css/jquery-3.3.1.js"></script>
        <script src="css/bootstrap/js/bootstrap.min.js"></script>
        <script src="css/tablesorter.js"></script>
        <script src="css/script.js"></script>   
       <footer><br>Copyright © 2018 by LABRI-SBA All rights reserved</footer>     
    </body>

</html>
  <%}%>


