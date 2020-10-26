<%-- 
    Document   : Commandes
    Created on : May 15, 2018, 12:04:37 AM
    Author     : bekal
--%>


<%@page import="model.Membre"%>
<%@page import="model.Equipe"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%!String email;
    String type;
    Membre mm;
    int id;
    String nom;
%>
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
        <title>Equipes</title>
        <%
            response.setHeader("Cache-Control", "no-cache ,no-store , must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");%>
    </head>
    <body>



        <%! String comm;%>
        <%! Equipe e;%>

        <%! int i;%>
        <%if (request.getParameter("command") == null) {
                comm = "add";
            } else {%>
        <% comm = request.getParameter("command");
            }%>
        <%  if (comm.equals("load")) {
                mm = new Membre();
                e = new Equipe();
                e = (Equipe) request.getAttribute("equipe");
                if (e.getChef() != 0) {

                    mm = mm.getMembre(e.getChef());

                }
            }%>

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
                        <li class="nav-item dropdown nav-item selected">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                GESTION DES EQUIPES
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                                <a class="dropdown-item active" href="EquipeServlet">EQUIPES</a>
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
        <section class="nav-menu"> 
            <div class="container">


        </section>
        <section class="menu-section clearfix">
            <div class="container">
                <div class="clearfix">

                    <div class="float-left">

                        <form  class="form-inline my-2 my-lg-0" action="EquipeServlet" method="post">
                            <%  if (comm.equals("load")) {%>

                            <input type="hidden" name="command" value="update"/>
                            <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
                            <%} else {%>
                            <input type="hidden" name="command" value="add"/>
                            <%}%>




                            <div class="form-group">
                                <div class="input-group my-2 mr-sm-2">
                                    <input type="text" name="nom" <%  if (comm.equals("load")) {%> value="<%=e.getNom()%>"   <%} %>placeholder="Le nom d'equipe "  class="form-control my-2   " required> 
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group my-2 mr-sm-2">
                                    <input type="text" name="domaine" <%  if (comm.equals("load")) {%> value="<%=e.getDomaine()%>"   <%} %>placeholder="Le domine de recherche  "  class="form-control my-2   " required> 
                                </div>
                            </div>
                            <%  if (comm.equals("load")) {%> 
                            <select  class="custom-select form-control my-2  mr-sm-2" name="membre">
                                <%if (mm.getId() == 0) {%> <option  value="0" selected>Choisir le chef d'equipe</option><%} else {%>
                                <option  value="<%=mm.getId()%>" selected><%=mm.getNom()%> <%=mm.getPrenom()%></option><%}%>
                                <%for (Membre m : (ArrayList<Membre>) request.getAttribute("membres")) {%>
                                <%  if (comm.equals("load")) {%>      <%if (m.getId() != mm.getId()) {%>
                                <option value="<%=m.getId()%>" ><%=m.getNom()%> <%=m.getPrenom()%></option> 

                                <%}%>
                                <%} else {%>
                                <option value="<%=m.getId()%>" ><%=m.getNom()%> <%=m.getPrenom()%></option> 
                                <%}%>
                                <%}%>   
                            </select>
                            <%} %>






                            <button class="btn btn-outline-primary  my-2  my-sm-0 " type="submit"> <%  if (comm.equals("load")) {%> Update<%} else {%>Ajouter<%}%></button>

                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div><div class="container">
        <section class="tab-section clear">

            <table id="myTable2" class="table table-sm table-responsive-md table-responsive-sm table-hover  table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th onclick="sortTable(0)">NOM</th>
                        <th onclick="sortTable(1)">DOMAINE</th>
                        <th onclick="sortTable(2)">CHEF</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Equipe eq : (ArrayList<Equipe>) request.getAttribute("equipes")) {%>
                    <tr >
                        <td><%=eq.getNom()%> </td>
                        <td> <%=eq.getDomaine()%> </td>
                        <td> <%if (eq.getMem().getId() != 0) {%><%=eq.getMem().getNom()%> <%=eq.getMem().getPrenom()%><%}%> </td>

                        <td><a class="upd" href="EquipeServlet?command=load&id=<%=eq.getId()%>" ><i class="ion-settings"></i></a>
                            <a class="del"  href="EquipeServlet?command=delete&id=<%=eq.getId()%>"><i class="ion-android-delete"></i></a>
                    </tr>
                    <%}%>

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
  <% }%>
