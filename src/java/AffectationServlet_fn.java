
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Compte;
import model.Fourniture;
import model.Membre;

import model.affectation_fn;

@WebServlet("/AffectationServlet_fn")
public class AffectationServlet_fn extends HttpServlet {

    Membre m;
    Fourniture fn;
    Compte c;

    @Override
    public void init() throws ServletException {

        super.init();
        m = new Membre();
        fn = new Fourniture();
        c = new Compte();
    }

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "LIST";
            }

            switch (theCommand) {

                case "LIST":
                    listAffectation(request, response);
                    break;

                case "ADD":
                    addAffectation(request, response);
                    break;

                case "LOAD":
                    loadAffectation(request, response);
                    break;

                case "UPDATE":
                    updateAffectation(request, response);
                    break;

                case "DELETE":
                    deleteAffectation(request, response);
                    break;

                default:
                    listAffectation(request, response);
            }

        } catch (Exception exc) {

        }

    }

    private void deleteAffectation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int idmem = Integer.parseInt(request.getParameter("membreid"));
        int idfn = Integer.parseInt(request.getParameter("fournitureid"));
        affectation_fn aff = affectation_fn.getaffectation(idmem, idfn);
        aff.deleteaffectation();

        listAffectation(request, response);
    }

    private void loadAffectation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int idmem = Integer.parseInt(request.getParameter("membreid"));
        int idfn = Integer.parseInt(request.getParameter("fournitureid"));
        affectation_fn aff = affectation_fn.getaffectation(idmem, idfn);

        request.setAttribute("THE_AFFECTATION", aff);

        listAffectation(request, response);
    }

    private void updateAffectation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int anidmembre = Integer.parseInt(request.getParameter("idmem"));
        int anidfourniture = Integer.parseInt(request.getParameter("idfn"));
        int idmembre = Integer.parseInt(request.getParameter("membreid"));
        int idfourniture = Integer.parseInt(request.getParameter("fournitureid"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        String dateaff = datef.format(new java.util.Date());
        affectation_fn aff = new affectation_fn(anidmembre, anidfourniture, idmembre, idfourniture, quantite, dateaff);

        aff.updateAffectation();

        listAffectation(request, response);

    }

    private void addAffectation(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int fourn = Integer.parseInt(request.getParameter("fourniture"));
        int membre = Integer.parseInt(request.getParameter("membre"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        String dateaff = datef.format(new java.util.Date());
        affectation_fn aff = new affectation_fn(membre, fourn, quantite, dateaff);

        aff.addAffectation();

        listAffectation(request, response);
    }

    private void listAffectation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PrintWriter out = response.getWriter();

        c = new Compte();
        m = new Membre();

        ArrayList<affectation_fn> affectations = new ArrayList<>();
        m = c.getCompteByEmail((String) request.getSession().getAttribute("email")).getMembre();
        ArrayList<Membre> membres = m.getChefEqMembres(m.getId());
        for (Membre mm : membres) {
            affectations.addAll(affectation_fn.getaffectations(mm.getId()));
        }

        request.setAttribute("membres", membres);
        request.setAttribute("fournitures", affectation_fn.getchefFournitures(m.getId()));
        request.setAttribute("AFFECTATION_LIST", affectations);
        request.getRequestDispatcher("/list-Affectation_fn.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            listAffectation(request, response);
        } catch (Exception ex) {
            try {
                throw new Exception();
            } catch (Exception ex1) {
            }
        }

    }
}
