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
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Compte;
import model.Equipe;
import model.Membre;

/**
 *
 * @author bekal
 */
public class MembreServlet extends HttpServlet {

    Membre m;
    Equipe eq;
    Compte c;
    PrintWriter out;

    @Override
    public void init() throws ServletException {

        super.init();
        m = new Membre();
        c=new Compte();
        eq = new Equipe();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        try {
            
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                theCommand = "LIST";
            }
            switch (theCommand) {
                case "LIST":

                    listMembres(request, response);

                    break;

                case "addp":
                    request.setAttribute("equipes", eq.getEquipes());
                    request.getRequestDispatcher("/MembreForm.jsp").forward(request, response);
                    break;
                case "delete":
                    deleteMembre(request, response);

                    break;
                case "load":
                    loadMembre(request, response);

                    break;

                case "search":
                    searchMembre(request, response);
                    break;
                default:

                    listMembres(request, response);
                    break;
            }
            
        } catch (ClassNotFoundException | SQLException ec) {
            out.println(ec);
        }
        
    }

    private void deleteMembre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
          
            c.deleteCompte(Integer.parseInt(request.getParameter("id")));
            m.deleteMembre(Integer.parseInt(request.getParameter("id")));
            out = response.getWriter();
            listMembres(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            out.println(ex);
        }
    }

    private void listMembres(HttpServletRequest request, HttpServletResponse response) {
        try {
            out = response.getWriter();
            ArrayList<Membre> b = m.getMembres();
            request.setAttribute("membres", b);
            request.getRequestDispatcher("/Membre.jsp").forward(request, response);
        } catch (ClassNotFoundException | ServletException | IOException | SQLException ex) {
            out.println(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
       
        String theCommand = request.getParameter("command");
        if (theCommand == null) {
            listMembres(request, response);
        } else {
            
            if (theCommand.equals("add")) {
                String[] dd = request.getParameter("date_n").split("-");
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
                Date ad = new Date(Integer.parseInt(dd[2]), Integer.parseInt(dd[0]), Integer.parseInt(dd[1]));
                String date_n = request.getParameter("date_n");
                String adresse = request.getParameter("adresse");
                String grade = request.getParameter("grade");
                int equipe = Integer.parseInt(request.getParameter("equipe"));
                String email = request.getParameter("email");
                String pass = request.getParameter("password");
                
                try {
                    
                    eq = eq.getEquipe(equipe);
                    m = new Membre(nom, prenom, date_n, adresse, grade, eq);
                    String add=m.addMembre();
                    if(add.equals("true")){
                    c=new Compte(email,pass,"membre",m.getMembreId(m));
                    if(c.getComptes().isEmpty()){
                       c.setType("admin");
                    }
                    c.addCompte();
                    listMembres(request, response);
                    }else out.println(add);
                } catch (ClassNotFoundException | SQLException ex) {
                    out.println(ex);
                }

            } else if (theCommand.equals("update")) {
                String pass="";
               
                    if(request.getParameter("password")!=null)
                    pass=Compte.MD5(request.getParameter("password"));
                
                int id = Integer.parseInt(request.getParameter("id"));
                String[] dd = request.getParameter("date_n").split("-");
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
                Date ad = new Date(Integer.parseInt(dd[2]), Integer.parseInt(dd[0]), Integer.parseInt(dd[1]));
                String date_n = request.getParameter("date_n");
                String adresse = request.getParameter("adresse");
                String grade = request.getParameter("grade");
                int equipe = Integer.parseInt(request.getParameter("equipe"));
                String email = request.getParameter("email");
                
                try {

                    eq = eq.getEquipe(equipe);
                   
                    m = new Membre(id, nom, prenom, date_n, adresse, grade, eq);

                    m.editMembre();
                    c=m.getMembreCompte(m.getMembreId(m));
                    c.setEmail(email);
                    if(!pass.equals("")){
                        c.editPass(pass);
                    }
                    c.editCompte();
                    String ty=(String)request.getSession().getAttribute("type");
                    request.getSession().removeAttribute("nom");
                    request.getSession().setAttribute("nom",m.getNom().toUpperCase());
                    if(ty.equals("admin")){
                    listMembres(request, response);}
                    else{
                        request.getRequestDispatcher("/loginservlet").forward(request, response);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    out.println(ex);
                }
            }
        }
        
    }

    private void loadMembre(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        out = response.getWriter();
        try {
            int i = Integer.parseInt(request.getParameter("id"));
            Membre me = new Membre();
            me = me.getMembre(i);
            c=me.getMembreCompte(i);
            request.setAttribute("membre", me);
            request.setAttribute("compte", c);
            request.setAttribute("equipes", me.getEquipe().getEquipes());
            getServletContext().getRequestDispatcher("/MembreForm.jsp").forward(request, response);
        } catch (SQLException ex) {
        }

    }

    private void searchMembre(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        out = response.getWriter();
        try {

            String search = request.getParameter("search");
            ArrayList<Membre> mem = m.search(search);

            request.setAttribute("membres", mem);
            request.getRequestDispatcher("/Membre.jsp").forward(request, response);
        } catch (SQLException ex) {
            out.println(ex);
        }
    }

}
