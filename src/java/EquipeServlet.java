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
import model.Equipe;
import model.Membre;

/**
 *
 * @author bekal
 */
public class EquipeServlet extends HttpServlet {

    Equipe e;
    Membre m;
    PrintWriter out;

    @Override
    public void init() throws ServletException {

        super.init();
        e = new Equipe();
        m = new Membre();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        String theCommand = request.getParameter("command");
        if (theCommand == null) {
            theCommand = "LIST";
        }
        switch (theCommand) {
            case "LIST":
                listEquipes(request, response);

                break;

            case "delete":

                deleteEquipe(request, response);

                break;
            case "load":
                loadEquipe(request, response);

                break;

            default: {
                listEquipes(request, response);
            }
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        String theCommand = request.getParameter("command");
        if (request.getAttribute("command") != null && ((String) request.getAttribute("command")).equals("new")) {
                listEquipes(request, response);
        } else {
            if (theCommand == null) {
                listEquipes(request, response);
            } else {
                int idchef = 0;
                if (theCommand.equals("add")) {

                    //idchef = Integer.parseInt(request.getParameter("membre"));

                    String nom = request.getParameter("nom");
                    String domaine = request.getParameter("domaine");
                    try {

                   
                            e = new Equipe(nom, domaine);
              
                        e.addEquipe();

                        listEquipes(request, response);
                    } catch (ClassNotFoundException | SQLException ex) {
                        out.println(ex);
                    }

                } else if (theCommand.equals("update")) {
                    if (request.getParameter("membre") != null) {
                        idchef = Integer.parseInt(request.getParameter("membre"));
                    }
                    String nom = request.getParameter("nom");
                    String domaine = request.getParameter("domaine");
                    try {

                        if (idchef != 0) {
                          
                            e = new Equipe(nom, domaine,idchef);

                        } else {
                            e = new Equipe(nom, domaine);

                        }
                        e.setId(Integer.parseInt(request.getParameter("id")));
                        e.editEquipe();

                        listEquipes(request, response);
                    } catch (ClassNotFoundException | SQLException ex) {
                        out.println(ex);
                    }
                }
            }
        }
    }

    private void deleteEquipe(HttpServletRequest request, HttpServletResponse response) {

        try {
            out = response.getWriter();
            e.deleteEquipe(Integer.parseInt(request.getParameter("id")));
            listEquipes(request, response);
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            out.println(ex);
        }
    }

    private void loadEquipe(HttpServletRequest request, HttpServletResponse response) {
        try {
            out = response.getWriter();
            e = e.getEquipe(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("equipe", e);
            ArrayList<Equipe> b = e.getEquipes();
            for(Equipe bb:b){
                bb.setMem(m.getMembre(bb.getChef()));
            }
            request.setAttribute("membre", m.getMembre(e.getChef()));
            request.setAttribute("equipes", b);
            request.setAttribute("membres", m.getMembresByEquipe(e));
            request.getRequestDispatcher("/Equipe.jsp").forward(request, response);
        } catch (IOException | SQLException | ClassNotFoundException | ServletException ex) {
            out.println(ex);
        }
    }

    private void searchEquipe(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            String search = request.getParameter("search");
            ArrayList<Equipe> equipes = e.search(search);

            request.setAttribute("equipes", equipes);
            request.getRequestDispatcher("/Equipe.jsp").forward(request, response);
        } catch (SQLException ex) {
            out.println(ex);
        }
    }

    private void listEquipes(HttpServletRequest request, HttpServletResponse response) {
        try {
            out = response.getWriter();
            
            ArrayList<Equipe> b = e.getEquipes();
            for(Equipe bb:b){
                bb.setMem(m.getMembre(bb.getChef()));
            }
            m=m.getMembre(1);
            request.setAttribute("membres", m.getMembres());
            request.setAttribute("equipes", b);
            request.getRequestDispatcher("/Equipe.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException | ServletException | IOException ex) {
            out.println(ex);
        }
    }

}
