<%-- 
    Document   : Commandes
    Created on : May 15, 2018, 12:04:37 AM
    Author     : bekal
--%>

<%@page import="model.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/style3.css"> 
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap/css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="css/ionicons.min.css">
        <title>Article</title>
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

                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown selected">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                GESTION BUDGETAIRE
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                                <a class="dropdown-item " href="BudgetServlet">BUDGETS</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item active" href="ArticleServlet">ARTICLES</a>
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
             <div class="float-xl-right ">
                        <form class="form-inline my-2 my-lg-0" action="ArticleServlet" >
                            <input type="hidden" name="command" value="search">
                            <input class="form-control my-2 mr-sm-2" type="search" name="search"  placeholder="rechercher" aria-label="Search">
                            <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </div>
                    
            <form  class="form-inline my-2 my-lg-0" action="ArticleServlet" method="get">
                    <%! String com;%>
                    <% com =request.getParameter("command");%>
                    <%if ( com !=null && com.equals("LOAD")){%>
                        <input type="hidden" name="command" value="UPDATE" />
                    <input type="hidden" name="ArticleId" value="${Article.getIdArticle()}" />
                    <%}else {%> <input type="hidden" name="command" value="ADD" /><%}%>
                        
                            <input class="form-control my-2 mr-sm-2" type="text" name="Numero" 
                                <%if (com!=null && com.equals("LOAD")){%>value="${Article.getNumero()}" <%}else {%>
                                   placeholder="Numero" <%}%>>
                            <input class="form-control my-2 mr-sm-2" type="text" name="Designation"  
                                   <%if (com!=null && com.equals("LOAD")){%>value="${Article.getDesignation()}" <%}else {%>
                                   placeholder="Designation" <%}%>>
                            <select  class="custom-select form-control my-2  mr-sm-2" name="Section">
                                <option selected>Choisir la Section</option> 
                                <%for (String s1: Article.GetSectionListName()){%>
                                    <option> <%=s1%></option><%}%>
                                </select> 

                           <%if (com !=null && com.equals("LOAD")){%><button class="btn btn-outline-primary  my-2  my-sm-0  del" type="submit" >Modifier</button>
                            <%}else{%> <button class="btn btn-outline-primary  my-2  my-sm-0 " type="submit" >Ajouter</button><%}%>

                        </form>
        
        <section class="tab-section clearfix">
            <table id="myTable2" class="table table-sm table-responsive-md table-responsive-sm  table-striped table-bordered">
                <thead class="thead-light">

                    <tr>
                      
                        <th onclick="sortTable(0)">Numero</th>
                        <th onclick="sortTable(1)" > Designation </th>
                        <th onclick="sortTable(2)">Section</th>
                        
                        <th></th>
                    </tr> 
                </thead>

                </div>
                <div class="tab-body">

                    <tbody>	
                        <c:forEach var="temp" items="${ArticleList}">

                            <c:url var="tempLink" value="ArticleServlet">
                                <c:param name="command" value="LOAD" />
                                <c:param name="ArticleId" value="${temp.getIdArticle()}" />
                            </c:url>

                            <c:url var="deleteLink" value="ArticleServlet">
                                <c:param name="command" value="DELETE" />
                                <c:param name="ArticleId" value="${temp.getIdArticle()}" />
                            </c:url>

                            <tr>
                              
                                <td> ${temp.getNumero()} </td>
                                <td> ${temp.getDesignation()} </td>
                                <td> ${temp.getSection()} </td>

                                <td><a class="" href="${tempLink}" ><i class="ion-settings"></i></a>
                                    <a class="del"  href="${deleteLink}"><i class="ion-android-delete"></i></a></td>
                            </tr>

                        </c:forEach>
                    </tbody>
            </table>

        </section>
            </div>
    </div>
  <footer><br>Copyright © 2018 by LABRI-SBA All rights reserved</footer>    
    <script src="css/jquery-3.3.1.js"></script>
    <script src="css/bootstrap/js/bootstrap.min.js"></script>
    <script src="css/tablesorter.js"></script>
    <script src="css/script.js"></script>   

</body>

</html>

<%}%>




