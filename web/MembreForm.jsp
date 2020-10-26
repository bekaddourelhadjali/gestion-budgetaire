<%-- 
    Document   : add-student-form
    Created on : Mar 30, 2018, 12:22:39 PM
    Author     : bekal
--%>

<%@page import="model.Compte"%>
<%@page import="model.Membre"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!String email;
    String type;
    int id;
    String nom;%>
<%  if (request.getSession().getAttribute("email") == null) {
        request.getRequestDispatcher("/loginservlet").forward(request, response);
    } else {
        email = (String) request.getSession().getAttribute("email");
        type = (String) request.getSession().getAttribute("type");
        nom = (String) request.getSession().getAttribute("nom");
        id = (int) request.getSession().getAttribute("id");

%>
<%! String comm;%>
<%! Membre m;%>
<%! Compte c;%>
<%            if (request.getParameter("command") == null) {
                comm = "add";
            } else {%>
<% comm = request.getParameter("command");
            }%>
<%  if (comm.equals("load")) {

        m = new Membre();
        m = (Membre) request.getAttribute("membre");
        c = (Compte) request.getAttribute("compte");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap-grid.min.css" >
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" >
        <link type="image/png" rel="shortcut icon" href="css/img.icon.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style3.css" type="text/css" rel="stylesheet"/>


        <link rel="stylesheet" type="text/css" href="css/ionicons.min.css">    
        <title>Membre</title>
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
                        <%if (type.equals("admin")) {%>


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
                        <li class="nav-item dropdown nav-item selected">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                GESTION DES EQUIPES
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                                <a class="dropdown-item " href="EquipeServlet">EQUIPES</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item active" href="MembreServlet">MEMBRES</a>

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
                        <%}%>
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



            </section>  
        </div>
        <div class="container" >
            <form action="MembreServlet" method="POST" >


                <%  if (comm.equals("load")) {%>

                <input type="hidden" name="command" value="update"/>
                <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
                <%} else {%>
                <input type="hidden" name="command" value="add"/>
                <%}%>
                <div class="row">
                    <div class=" col-xl-4 col-lg-4 col-md-4 col-sm-6 ">
                        <div class="form-group">


                            <input type="text" name="nom" class="form-control"   <%  if (comm.equals("load")) {%> value="<%=m.getNom()%>"   <%}%>placeholder="Nom " required/>

                        </div>
                    </div><div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >
                            <input type="text" name="prenom" class="form-control" <%  if (comm.equals("load")) {%> value="<%=m.getPrenom()%>"   <%}%>placeholder="Prenom "  required/>

                        </div> </div>
                </div> <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >
                            <input type="date" name="date_n" class="form-control"<%  if (comm.equals("load")) {%> value="<%=m.getDate_n()%>"  <%}%>placeholder="Date_Naissance "required=""/>
                        </div></div> 
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6  col-sm-6 ">
                        <div class="form-group" >
                            <input type="text" name="adresse" class="form-control"<%  if (comm.equals("load")) {%> value="<%=m.getAdresse()%>"   <%}%>placeholder="Adresse " required/>
                        </div></div>    
                </div> <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >


                            <input type="text" name="grade" class="form-control" <%  if (comm.equals("load")) {%> value="<%=m.getGrade()%>"   <%}%>placeholder="Grade "  required/>

                        </div></div>
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6  "> 
                        <div class="form-group" >
                            <select  class="custom-select form-control" name="equipe" required="">
                                <%  if (comm.equals("load")) {%> 
                                <option  value="<%=m.getEquipe().getId()%>" selected ><%=m.getEquipe().getNom()%> </option>
                                <%} else {%>
                                <option  value="0" selected>Choisir l'equipe</option>
                                <%}%>
                                <c:forEach var="e" items="${equipes}">
                                    <c:if test="${membre.getEquipe().getId()!=e.getId()}"><option value="${e.getId()}">${e.getNom()}</option></c:if>
                                </c:forEach>   
                            </select>
                        </div></div>
                </div>
                <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >
                            <input type="email" name="email" class="form-control"<%  if (comm.equals("load")) {%> value="<%=c.getEmail()%>"  <%} else {%>placeholder="Email" <%}%> required/>
                        </div></div> 
                      <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6  col-sm-6 ">
                        <div class="form-group" >
                            <input type="text" name="password" class="form-control"  placeholder="Password" required/>
                        </div></div>    
                </div>           

                <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6  ">
                    <div class="form-group" >

                        <input type="submit" value="save" class="save btn btn-primary"/>
                    </div>
                </div>


            </form>



            <p>
                <%if (type.equals("admin")) {%>  <a class="btn btn-primary" role="button" href='MembreServlet'>BACK TO LIST</a><%}%> 
            </p>
        </div>
        <footer><br>Copyright © 2018 by LABRI-SBA All rights reserved</footer>     
        <script src="css/jquery-3.3.1.js"></script>
        <script src="css/bootstrap/js/bootstrap.min.js"></script>


    </body>
</html>
<%}%>