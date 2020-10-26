/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;
import model.Commande;
import model.Fournisseur;
import model.Fourniture;
import model.Materiel;
import model.Repartition;
import model.Service;

/**
 *
 * @author bekal
 */
public class ProduitlistServlet extends HttpServlet {

    PrintWriter out;
    ArrayList<Service> services;
    ArrayList<Fourniture> fourns;
    ArrayList<Materiel> mat;

    @Override
    public void init() throws ServletException {
        super.init();
        mat = new ArrayList();
        fourns = new ArrayList();
        services = new ArrayList();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        if (request.getParameter("command") != null) {
            
            try {
                switch ((String) request.getAttribute("type")) {
                    case "fourniture":
                        fourns = Fourniture.getByCommand((int) (request.getAttribute("idc")));
                        request.setAttribute("fourns", fourns);
                        break;
                    case "materiel":
                        mat = Materiel.getByCommand((int) (request.getAttribute("idc")));
                        request.setAttribute("mat", mat);
                        break;
                    case "service":
                        services = Service.getByCommand((int) (request.getAttribute("idc")));
                        request.setAttribute("services", services);
                        break;
                    default:
                        break;
                }

                Commande c = new Commande();
                c.setNumero((int) request.getAttribute("idc"));
                c = c.getCommande(c.getNumero());
                Fournisseur f = c.getFournisseur();
                f = f.getFournisseur(f.getId());
                ArrayList<Fournisseur> fournisseurs = f.getFournisseurs();
                Repartition rep=new Repartition();
                ArrayList<Article> articles =  new ArrayList<>();
                
                for(Repartition r:rep.getRepartitions()){
                   articles.add(Article.GetArticle(r.getIdArticle()));
                }
                
                request.setAttribute("articles", articles);
                request.setAttribute("fournisseurs", fournisseurs);
                request.setAttribute("comm", c);
                request.setAttribute("fournisseur", f);
                request.setAttribute("idc", (int) request.getAttribute("idc"));
                request.getRequestDispatcher("/produit.jsp").forward(request, response);
                
            } catch (SQLException | ClassNotFoundException ex) {
                out.println(ex);
            
            }
            
        } else {
            request.getRequestDispatcher("/CommandeServlet").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        if (request.getParameter("command") != null) {
            try {
                switch ((String) request.getAttribute("type")) {
                    case "fourniture":
                        fourns = Fourniture.getByCommand((int) (request.getAttribute("idc")));
                        request.setAttribute("fourns", fourns);
                        break;
                    case "materiel":
                        mat = Materiel.getByCommand((int) (request.getAttribute("idc")));
                        request.setAttribute("mat", mat);
                        break;
                    case "service":
                        services = Service.getByCommand((int) (request.getAttribute("idc")));
                        request.setAttribute("services", services);
                        break;
                    default:
                        break;
                }

                Commande c = new Commande();
                c.setNumero((int) request.getAttribute("idc"));
                c = c.getCommande(c.getNumero());
                Fournisseur f = c.getFournisseur();
                f = f.getFournisseur(f.getId());
                ArrayList<Fournisseur> fournisseurs = f.getFournisseurs();
                Repartition rep=new Repartition();
                ArrayList<Article> articles =  new ArrayList<>();
                
                for(Repartition r:rep.getRepartitions()){
                   articles.add(Article.GetArticle(r.getIdArticle()));
                }
                request.setAttribute("articles", articles);
                request.setAttribute("fournisseurs", fournisseurs);
                request.setAttribute("comm", c);
                request.setAttribute("fournisseur", f);
                request.setAttribute("idc", (int) request.getAttribute("idc"));
                request.getRequestDispatcher("/produit.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                out.println(ex);
            }

        } else {

        }

    }
   

}
