/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Commande;
import model.Fournisseur;
import model.Article;
import model.Repartition;
/**
 *
 * @author bekal
 */
public class CommandelistServlet extends HttpServlet {
     Commande c;
    Fournisseur f;
    Article a;
    PrintWriter out;
    @Override
    public void init() throws ServletException {

        super.init();
        c = new Commande();
        f=new Fournisseur();
        a=new Article();
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         out=response.getWriter();
         ArrayList<Commande>  b =new ArrayList();
        if(request.getParameter("command")!=null&&request.getParameter("command").equals("search")){
            try {
               b = c.search(request.getParameter("search"));
            } catch (ClassNotFoundException | SQLException ex) {
                out.println(ex);
            }
        }else{
         try {
         b = c.getCommandes();
         } catch (ClassNotFoundException ex) {
             out.println(ex);
         }
       }
        ArrayList<Fournisseur> fournisseurs;
         try {
             fournisseurs = f.getFournisseurs();
             request.setAttribute("fournisseurs", fournisseurs);
             Repartition rep=new Repartition();
                ArrayList<Article> articles =  new ArrayList<>();
                
                for(Repartition r:rep.getRepartitions()){
                   articles.add(Article.GetArticle(r.getIdArticle()));
                    request.setAttribute("articles", articles);
                }
         } catch (ClassNotFoundException ex) {} catch (SQLException ex) { }
       
       
        
        request.setAttribute("commandes", b);
        request.getRequestDispatcher("/Commandes.jsp").forward( request, response);

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out=response.getWriter();
         ArrayList<Commande>  b =new ArrayList();
        if(request.getParameter("command")!=null&&request.getParameter("command").equals("search")){
            try {
               b = c.search(request.getParameter("search"));
            } catch (ClassNotFoundException | SQLException ex) {
                out.println(ex);
            }
        }else{
         try {
         b = c.getCommandes();
         } catch (ClassNotFoundException ex) {
             out.println(ex);
         }
       }
        ArrayList<Fournisseur> fournisseurs;
         try {
             fournisseurs = f.getFournisseurs();
             request.setAttribute("fournisseurs", fournisseurs);
             Repartition rep=new Repartition();
                ArrayList<Article> articles =  new ArrayList<>();
                
                for(Repartition r:rep.getRepartitions()){
                   articles.add(Article.GetArticle(r.getIdArticle()));
                }
              request.setAttribute("articles", articles);
         } catch (ClassNotFoundException ex) {} catch (SQLException ex) {}
       
        request.setAttribute("commandes", b);
        request.getRequestDispatcher("/Commandes.jsp").forward( request, response);
    }

    
}
