<%-- 
    Document   : Commandes
    Created on : May 15, 2018, 12:04:37 AM
    Author     : bekal
--%>

<%@page import="model.Article"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Commande"%>
<%@page import="model.Fournisseur"%>
<%@page import="model.Fourniture"%>
<%@page import="model.Materiel"%>
<%@page import="model.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%!String email;
    String typee;
    int ids;
    String noms;%>
<%  if (request.getSession().getAttribute("email") != null) {
        email = (String) request.getSession().getAttribute("email");
        typee = (String) request.getSession().getAttribute("type");
        noms = (String) request.getSession().getAttribute("nom");
        id = (int) request.getSession().getAttribute("id");
    } else {
        request.getRequestDispatcher("/loginservlet").forward(request, response);
    }
    if (email == null || !typee.equals("admin")) {

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
        <title>Produits</title>

    </head>
    <body>


        <%!String command;
            ArrayList<Fournisseur> fourns;
            ArrayList<Article> arts;
            Commande com;
            Fournisseur fou;
            Service service;
            Materiel materiel;
            Fourniture fourniture;
            String design;
            float prix;
            int quant;
            String type;
            int id;
            String typ;
            String tv;
            String commm;
            String desc;
            String types;
            String message;

        %>

        <%if (request.getParameter("command") != null) {
            
                command = request.getParameter("command");
                commm = (String) request.getAttribute("command");
                if (command.equals("load")) {
                    if (((String) request.getAttribute("type")).equals("service")) {
                        type = "service";

                        service = (Service) request.getAttribute("service");
                        id = service.getId();
                        design = service.getDesignation();
                        prix = service.getPrix();
                        quant = service.getQuantite();
                        desc = service.getDescription();
                    } else if (((String) request.getAttribute("type")).equals("materiel")) {
                        type = "materiel";
                        materiel = (Materiel) request.getAttribute("materiel");
                        id = materiel.getId();
                        design = materiel.getDesignation();
                        prix = materiel.getPrix();
                        quant = materiel.getQuantite();
                        desc = materiel.getDescription();
                        types = materiel.getType();
                    } else if (((String) request.getAttribute("type")).equals("fourniture")) {
                        type = "fourniture";
                        fourniture = (Fourniture) request.getAttribute("fourniture");
                        id = fourniture.getId();
                        design = fourniture.getDesignation();
                        prix = fourniture.getPrix();
                        quant = fourniture.getQuantite();
                    }

                }
            }%>
        <% com = (Commande) request.getAttribute("comm");
            fou = (Fournisseur) request.getAttribute("fournisseur");
            arts = (ArrayList<Article>) request.getAttribute("articles");
            fourns = (ArrayList<Fournisseur>) request.getAttribute("fournisseurs");
            tv = com.getTva() * 100 + "";
            tv = tv.substring(0, 2);
            typ = (String) request.getAttribute("type");
            String idc = com.getNumero() + "";
            if( request.getAttribute("repart")!=null){
                message=(String) request.getAttribute("repart");}else{
                message="";
            }
        %>

        <header>
            <nav class="navbar navbar-expand-lg   " style="background-color:rgba(4,27,72,0.8);">
                <a class="navbar-brand text-white" href="MembreServlet?command=load&id=<%=ids%>"><%=noms%></a>
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
                                <a class="dropdown-item active" href="CommandeServlet">COMMANDES</a>
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
            <section class="menu-section clear">
                <div class="clearfix">

                    <div class="float-left">

                        <form  class="form-inline my-2 my-lg-0" action="CommandeServlet" method="post">
                            <input type="hidden" name="command"  value="update">
                            <input type="hidden" name="idc" value="<%=com.getNumero()%>">
                            <select class="custom-select form-control my-2 mr-sm-2 " name="fournisseur">
                                <option selected>Choisir un Fournisseur</option> 
                                <%for (Fournisseur f : fourns) {%>
                                <option value="<%=f.getId()%>" <%if (f.getId() == com.getFournisseur().getId()) {%>selected<%}%> ><%=f.getNom()%></option>
                                <%}%>
                            </select>
                            <select  class="custom-select form-control my-2  mr-sm-2" name="article">
                                <option selected>Choisir l'objet de la commande</option> 
                                <%for (Article a : arts) {%>
                                <option value="<%=a.getIdArticle()%>" <%if (a.getIdArticle() == com.getIdatricle()) {%>selected<%}%>><%=a.getDesignation()%></option>
                                <%}%>  
                            </select>
                            <div class="form-group">
                                <div class="input-group ">

                                    <input type="number" name="tva" value="<%=tv%>" class="form-control my-2   "> 
                                    <div class="input-group-append"><span class="input-group-text my-2 mr-2">%</span></div>
                                </div>
                            </div>

                            <button class="btn btn-outline-primary  my-2  my-sm-0 " type="submit">Enregistrer</button>

                        </form>
                    </div>
                </div>
            </section>
        </div><div class="container">
            <section class="tab-section clear">
                <h5 class="status"><%=message%></h5>
                <div class="row">

                    <div class="col-xl-4 col-md-6 col-lg-5 col-sm-10 col-10 ">
                        <form  class=" my-2 my-lg-0" action="ProduitServlet" method="post">
                            <input type="hidden" name="command"  value="<%if (command.equals("load")) {%>update<%} else {%>add<%}%>">
                            <input type="hidden" name="idc"  value="<%=(int) request.getAttribute("idc")%>">
                            <input type="hidden" name="id"  value="<%=id%>">
                            <input type="hidden" name="type" value="<%=typ%>">
                            <div class="form-group">
                                <label for="designation" class="sr-only">Designation</label>
                                <input type="text" id="designation" name="designation" <%if (command.equals("load")) {%>value="<%=design%>"<%}%>placeholder='Designation' class="form-control" required> 
                            </div> 
                            <div class="form-group">
                                <label for="quantite" class="sr-only">Quantite</label>
                                <input type="number" id="quantite" name="quantite"  <%if (command.equals("load")) {%>value="<%=quant%>"<%}%>placeholder='Quantite' class="form-control" required> 
                            </div>
                            <div class="form-group">
                                <div class="input-group ">
                                    <label for="prix" class="sr-only">Prix</label>
                                    <input type="text" id="prix" name="prix" <%if (command.equals("load")) {%>value="<%=prix%>"<%}%>placeholder='PRIX'  class="form-control my-2   " required=""> 
                                    <div class="input-group-append"><span class="input-group-text my-2 mr-2">DA</span></div>
                                </div>
                            </div>
                            <%if (!typ.equals("fourniture")) {%>
                            <div class="form-group">
                                <div class="input-group ">
                                    <input type="text" name="desc" <%if (command.equals("load")) {%>value="<%=desc%>"<%}%>placeholder="Description"  class="form-control my-2   " required> 
                                </div>
                            </div><%}%> 
                            <%if (typ.equals("materiel")) {%>
                            <div class="form-group">
                                <div class="input-group ">
                                    <input type="text" name="types" <%if (command.equals("load")) {%>value="<%=types%>"<%}%>placeholder="Type de materiel"  class="form-control my-2   " required> 
                                </div>
                            </div><%}%>
                            <button class="btn btn-outline-primary  my-2  my-sm-0 " type="submit">Enregistrer</button>

                        </form>
                    </div>
                    <div class="col-lg-6">
                        <table class="table table-sm table-responsive-md table-responsive-sm table-hover  table-bordered">
                            <thead class="thead-light">
                                <tr>
                                    <th>Designation</th>
                                    <th>Quantité</th>
                                    <th>Prix</th>
                                    <%if (!typ.equals("fourniture")) {%><th>Description</th><%}%>
                                    <%if (typ.equals("materiel")) {%><th>Type</th><%}%>
                                    <th></th>

                                </tr>
                            </thead>
                            <tbody>
                                <%if (typ.equals("service")) {%>
                                <c:forEach var="s" items="${services}">
                                    <c:url var="upLink" value="ProduitServlet">
                                        <c:param name="command" value="load" />
                                        <c:param name="type" value="service"/>
                                        <c:param name="idc"  value="<%=idc%>"/>
                                        <c:param name="id" value="${s.getId()}" />
                                    </c:url>
                                    <c:url var="deLink" value="ProduitServlet">
                                        <c:param name="command" value="delete" />
                                        <c:param name="type" value="service"/>
                                        <c:param name="idc"  value="<%=idc%>"/>
                                        <c:param name="id" value="${s.getId()}" />
                                    </c:url>
                                    <tr>
                                        <td> ${s.getDesignation()} </td>
                                        <td> ${s.getQuantite()} </td>
                                        <td> ${s.getPrix()} </td>
                                        <td> ${s.getDescription()} </td>
                                        <td><a class="upd" href="${upLink}" ><i class="ion-settings"></i></a>
                                            <a class="del"  href="${deLink}"><i class="ion-android-delete"></i></a></td>
                                    </tr>

                                </c:forEach>
                                <%} else if (typ.equals("fourniture")) {%>
                                <c:forEach var="f" items="${fourns}">
                                    <c:url var="upLink" value="ProduitServlet">
                                        <c:param name="command" value="load" />
                                        <c:param name="type" value="fourniture"/>
                                        <c:param name="idc"  value="<%=idc%>"/>
                                        <c:param name="id" value="${f.getId()}" />
                                    </c:url>
                                    <c:url var="deLink" value="ProduitServlet">
                                        <c:param name="command" value="delete" />
                                        <c:param name="type" value="fourniture"/>
                                        <c:param name="idc"  value="<%=idc%>"/>
                                        <c:param name="id" value="${f.getId()}" />
                                    </c:url>
                                    <tr>
                                        <td> ${f.getDesignation()} </td>
                                        <td> ${f.getQuantite()} </td>
                                        <td> ${f.getPrix()} </td>

                                        <td><a class="upd" href="${upLink}" ><i class="ion-settings"></i></a>
                                            <a class="del"  href="${deLink}"><i class="ion-android-delete"></i></a></td>
                                    </tr>

                                </c:forEach>
                                <%} else if (typ.equals("materiel")) {%>
                                <c:forEach var="m" items="${mat}">
                                    <c:url var="upLink" value="ProduitServlet">
                                        <c:param name="command" value="load" />
                                        <c:param name="idc"  value="<%=idc%>"/>
                                        <c:param name="type" value="materiel"/>
                                        <c:param name="id" value="${m.getId()}" />
                                    </c:url>
                                    <c:url var="deLink" value="ProduitServlet">
                                        <c:param name="command" value="delete" />
                                        <c:param name="idc"  value="<%=idc%>"/>
                                        <c:param name="type" value="materiel"/>
                                        <c:param name="id" value="${m.getId()}" />
                                    </c:url>
                                    <tr>
                                        <td> ${m.getDesignation()} </td>
                                        <td> ${m.getQuantite()} </td>
                                        <td> ${m.getPrix()} </td>
                                        <td> ${m.getDescription()} </td>
                                        <td> ${m.getType()} </td>
                                        <td><a class="upd" href="${upLink}" ><i class="ion-settings"></i></a>
                                            <a class="del"  href="${deLink}"><i class="ion-android-delete"></i></a></td>
                                    </tr>

                                </c:forEach>
                                <%}%>
                            </tbody>
                        </table>

                    </div>
                </div>
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