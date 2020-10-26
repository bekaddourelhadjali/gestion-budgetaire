
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!String email;
        String type;
        int id;
            String nom;%>
        <%  if(request.getSession().getAttribute("email")!=null){
            email = (String) request.getSession().getAttribute("email");
            type = (String) request.getSession().getAttribute("type");
            nom = (String) request.getSession().getAttribute("nom");
            id=(int) request.getSession().getAttribute("id");}else{
            request.getRequestDispatcher("/loginservlet").forward(request, response);
        }
            if (email == null || !type.equals("admin")) {
                
                request.getRequestDispatcher("/loginservlet").forward(request, response);
            } else {%>
<!DOCTYPE html>

        <html>
            <head>
                 <%
            response.setHeader("Cache-Control", "no-cache ,no-store , must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");%>
                <link rel="stylesheet" href="css/style3.css"> 
                <link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
                <link rel="stylesheet" href="css/bootstrap/css/bootstrap-grid.min.css">
                <link rel="stylesheet" href="css/ionicons.min.css">
                
                <title>Budget</title>
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
                        <li class="nav-item dropdown selected">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                GESTION BUDGETAIRE
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                                <a class="dropdown-item active" href="BudgetServlet">BUDGETS</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="ArticleServlet">ARTICLES</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="SectionServlet">SECTIONS</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item " href="RepartitionServlet">REPARTITIONS</a>
                        </li>
                      
                    
                        <li class="nav-item dropdown nav-item  ">
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
                    <form class="form-inline my-2 my-lg-0" action="BudgetServlet" method="GET">
                        <input type="hidden" name="command" value="ADD">
                        <input class="form-control my-2 mr-sm-2" type="text" name="montant" placeholder="Montant">
                        <input class="btn btn-outline-primary  my-2  my-sm-0" type="submit" value="ajouter budget">
                    </form>
                    <br/>
                    <section class="tab-section clearfix">
                        <table  id="myTable2" class=" budget-tab table table-sm table-responsive-md table-responsive-sm  table-striped table-bordered">
                            <tr>
                                    <th> Annee</th>
                                    <th>Montant Total</th>
                                    <th>Solde</th>
                            </tr>
                            <c:forEach var="temp" items="${Budgets}">
                                <tr>
                                    <td>${temp.annee} </td>
                                    <td>${temp.montant_d}</td>
                                    <td><c:if test="${temp.montant_t > 0}">${temp.montant_t}</c:if></td>
                                </tr>
                            </c:forEach>
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