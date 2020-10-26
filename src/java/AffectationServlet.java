
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
import model.Materiel;
import model.Membre;
import model.affectation;

@WebServlet("/AffectationServlet")
public class AffectationServlet extends HttpServlet {

    Membre m;
    Materiel mat;
    Compte c;

    @Override
    public void init() throws ServletException {

        super.init();
        m = new Membre();
        mat = new Materiel();
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
        int idmat = Integer.parseInt(request.getParameter("materielid"));
        affectation aff = affectation.getaffectation(idmem, idmat);
        aff.deleteaffectation();

        listAffectation(request, response);
    }

    private void loadAffectation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int idmem = Integer.parseInt(request.getParameter("membreid"));
        int idmat = Integer.parseInt(request.getParameter("materielid"));
        affectation aff = affectation.getaffectation(idmem, idmat);

        request.setAttribute("THE_AFFECTATION", aff);

        listAffectation(request, response);
    }

    private void updateAffectation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int anidmembre = Integer.parseInt(request.getParameter("idmem"));
        int anidmateriel = Integer.parseInt(request.getParameter("idmat"));
        int idmembre = Integer.parseInt(request.getParameter("membreid"));
        int idmateriel = Integer.parseInt(request.getParameter("materielid"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        String dateaff = datef.format(new java.util.Date());
        affectation aff = new affectation(anidmembre, anidmateriel, idmembre, idmateriel, quantite, dateaff);

        aff.updateAffectation();

        listAffectation(request, response);

    }

    private void addAffectation(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int materiel = Integer.parseInt(request.getParameter("materiel"));
        int membre = Integer.parseInt(request.getParameter("membre"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        String dateaff = datef.format(new java.util.Date());
        affectation aff = new affectation(membre, materiel, quantite, dateaff);

        aff.addAffectation();

        listAffectation(request, response);
    }

    private void listAffectation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PrintWriter out = response.getWriter();

        c = new Compte();
        m = new Membre();

        ArrayList<affectation> affectations = new ArrayList<>();
        m = c.getCompteByEmail((String) request.getSession().getAttribute("email")).getMembre();
        ArrayList<Membre> membres = m.getChefEqMembres(m.getId());
        for (Membre mm : membres) {
            affectations.addAll(affectation.getaffectations(mm.getId()));
        }
        
        if(((String)request.getSession().getAttribute("chef")).equals("true")){
        request.setAttribute("membres", membres);
        request.setAttribute("materiels", affectation.getchefMateriels(m.getId()));
        request.setAttribute("AFFECTATION_LIST", affectations);
        }else{
            request.setAttribute("AFFECTATION_LIST", affectation.getaffectations(m.getId()));
        }
        request.getRequestDispatcher("/list-Affectation.jsp").forward(request, response);

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
