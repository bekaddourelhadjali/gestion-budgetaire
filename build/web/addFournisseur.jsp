<%-- 
    Document   : add-student-form
    Created on : Mar 30, 2018, 12:22:39 PM
    Author     : bekal
--%>

<%@page import="model.Fournisseur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
     <%! String comm;%>
        <%! Fournisseur f;%>
        <%if (request.getParameter("command") == null) {
                comm = "add";
            } else {%>
        <% comm = request.getParameter("command");
            }%>
        <%  if (comm.equals("load")) {
                f = (Fournisseur) request.getAttribute("f");
            }%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap-grid.min.css" >
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css" >
        <link href="css/style3.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="css/ionicons.min.css">    
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD Fournisseur</title>
    </head>
    <body>

            <header>
                <nav class="navbar navbar-expand-lg   " style="background-color:rgba(4,27,72,0.8);">
                    <a class="navbar-brand text-white" href="MembreServlet?command=load&id=<%=id%>"><%=nom%></a>
                    <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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



            </section>  
        </div>
        <div class="container" >
            <form action="FournisseurServlet" method="POST" >


                <%  if (comm.equals("load")) {%>

                <input type="hidden" name="command" value="update"/>
                <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
                <%} else {%>
                <input type="hidden" name="command" value="add"/>
                <%}%>
                <div class="row">
                    <div class=" col-xl-4 col-lg-4 col-md-4 col-sm-6 ">
                        <div class="form-group">


                            <input type="text" name="nom" class="form-control"   <%  if (comm.equals("load")) {%> value="<%=f.getNom()%>"   <%}%>placeholder="Nom "  required/>

                        </div>
                    </div><div class="col-xl-4 col-lg-4 col-md-4  col-sm-6  " > 
                        <div class="form-group" >
                            <select  class="custom-select form-control" name="type" required="">
                                
                                 <option  value="personne" <%  if (!(comm.equals("load")&&f.getType().equals("entreprise"))) {%>selected<%}%>>Personne</option>
                                <option  value="entreprise" <%  if (comm.equals("load")&&f.getType().equals("entreprise")) {%>selected<%}%>>Entreprise</option>
                            </select>
                        </div></div>
                </div> <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6  col-sm-6 ">
                        <div class="form-group" >


                            <input type="text" name="adresse" class="form-control"<%  if (comm.equals("load")) {%> value="<%=f.getAdresse()%>"   <%}%>placeholder="Adresse " required/>
                        </div></div>   <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >

                            <input type="text" name="telephone" class="form-control"<%  if (comm.equals("load")) {%> value="<%=f.getTelephone()%>"  <%}%>placeholder="Telephone " required/>
                        </div></div>  
                </div> <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >


                            <input type="text" name="nrc" class="form-control" <%  if (comm.equals("load")) {%> value="<%=f.getNrc()%>"   <%}%>placeholder="Nrc " required/>

                        </div></div><div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >
                            <input type="text" name="agrement" class="form-control" <%  if (comm.equals("load")) {%> value="<%=f.getAgrement()%>"   <%}%>placeholder="Agrement " required/>
                            </tr>
                        </div>  </div> </div>
                <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >


                            <input type="text" name="agissant" class="form-control" <%  if (comm.equals("load")) {%> value="<%=f.getAgissant()%>"  <%}%>placeholder="Agissant " required/>

                        </div></div><div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >
                            <input type="text" name="rib" class="form-control" <%  if (comm.equals("load")) {%> value="<%=f.getRib()%>"   <%}%>placeholder="RIB " required/>

                        </div> </div> </div>
                <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >


                            <input type="text" name="nif" class="form-control" <%  if (comm.equals("load")) {%> value="<%=f.getNif()%>"   <%}%>placeholder="NIF " required/>
                        </div></div><div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div class="form-group" >

                            <input type="text" name="nis" class="form-control" <%  if (comm.equals("load")) {%> value="<%=f.getNis()%>"  <%}%>placeholder="NIS" required/>

                        </div> </div> </div> 
                <div class="row">
                    <div class="col-xl-4 col-lg-4 col-md-4  col-sm-6 ">
                        <div  class="form-group">


                            <input type="text" name="types" class="form-control" <%  if (comm.equals("load")) {%> value="<%=f.getTypes()%>"  <%}%>placeholder="Types " required/>
                        </div></div><div class="col-xl-4 col-lg-4 col-md-4  col-sm-6  ">
                        <div class="form-group" >

                            <input type="submit" value="save" class="save btn btn-primary"/>
                        </div>
                    </div>
                </div>

            </form>



            <p>
                <a class="btn btn-primary" role="button" href='FournisseurServlet'>BACK TO LIST</a>
            </p>
        </div>
        <footer><br>Copyright © 2018 by LABRI-SBA All rights reserved</footer>
        <script src="css/jquery-3.3.1.js"></script>
        <script src="css/bootstrap/js/bootstrap.min.js"></script>
        

    </body>
</html>
<%}%>