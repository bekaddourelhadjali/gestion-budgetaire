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
import model.Fournisseur;

/**
 *
 * @author bekal
 */
public class FournisseurServlet extends HttpServlet {

    Fournisseur a;
    PrintWriter out;
    @Override
    public void init() throws ServletException {
        
        super.init();
        a = new Fournisseur();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            out=response.getWriter();
        try {
            
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                theCommand = "LIST";
            }
            switch (theCommand) {
                case "LIST":

                    listFournisseur(request, response);

                    break;

               
                case "addp":

                    request.getRequestDispatcher("/addFournisseur.jsp").forward(request, response);
                    break;
                case "delete":
                    deleteFournisseur(request, response);

                    break;
                case "load":
                    loadFournisseur(request, response);

                    break;
               
                    
                case "search":
                    searchFournisseur(request,response);
                    break;
                default:

                    listFournisseur(request, response);
                    break;
            }
            // liststudents(request, response);
        } catch (ClassNotFoundException ec) {

        } 

    }

    private void addFournisseur(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
         out=response.getWriter();
        Fournisseur f = new Fournisseur(request.getParameter("nom"), request.getParameter("type"), request.getParameter("adresse"),
                request.getParameter("telephone"), request.getParameter("nrc"), request.getParameter("agrement"), request.getParameter("agissant"),
                request.getParameter("rib"), request.getParameter("nif"), request.getParameter("nis"), request.getParameter("types"));

        f.addFournisseur();
        listFournisseur(request, response);

    }

    private void deleteFournisseur(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        try {
            a.deleteFournisseur(Integer.parseInt(request.getParameter("id")));
            out=response.getWriter();
            listFournisseur(request, response);
        } catch (ClassNotFoundException ex) {
        out.println(ex);} catch (SQLException ex) {out.println("VOUS NE VOULEZ PAS SUPPRIMER CE FOURNISSSEUR CAR IL A UNE COMMANDE");}
    }

    private void listFournisseur(HttpServletRequest request, HttpServletResponse response)  {
        try {
             out=response.getWriter();
            ArrayList<Fournisseur> b = a.getFournisseurs();
            request.setAttribute("fournisseurs", b);
            request.getRequestDispatcher("/Fournisseur.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
           out.println(ex);
        } catch (ServletException ex) {
           out.println(ex);
        } catch (IOException ex) {
           out.println(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out=response.getWriter();
       
            
            String theCommand = request.getParameter("command");
            
            switch (theCommand) {
                case "add":{
                
                try {
                addFournisseur(request, response);
            } catch (ClassNotFoundException | SQLException ex) {out.println(ex);}
        
                }

                    

                    break;

               
                case "update":

        {
            try {
                updateFournisseur(request, response);
            } catch (ClassNotFoundException ex) {out.println(ex);}
        }
                    break;
                
            }
            // liststudents(request, response);
        

    }

    private void updateFournisseur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        Fournisseur f = new Fournisseur(Integer.parseInt(request.getParameter("id")), request.getParameter("nom"), request.getParameter("type"), request.getParameter("adresse"),
                request.getParameter("telephone"), request.getParameter("nrc"), request.getParameter("agrement"), request.getParameter("agissant"),
                request.getParameter("rib"), request.getParameter("nif"), request.getParameter("nis"), request.getParameter("types"));
       
        f.editFournisseur();
        listFournisseur(request, response);
    }

    private void loadFournisseur(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        int i=Integer.parseInt(request.getParameter("id"));
        Fournisseur f = new Fournisseur();
        f = a.getFournisseur(i);
        request.setAttribute("f", f);
        getServletContext().getRequestDispatcher("/addFournisseur.jsp").forward(request, response);
     
    }

    private void searchFournisseur(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        PrintWriter out=response.getWriter();
        try {
            
            String search=request.getParameter("search");
            ArrayList<Fournisseur> fournisseurs=a.search(search);
            
            request.setAttribute("fournisseurs", fournisseurs);
            request.getRequestDispatcher("/Fournisseur.jsp").forward(request, response);
        } catch (SQLException ex) { out.println(ex);}
    }

}
