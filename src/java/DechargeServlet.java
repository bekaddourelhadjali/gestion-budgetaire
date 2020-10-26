
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Compte;
import model.affectation;
import model.Membre;
import model.Materiel;

@WebServlet("/DechargeServlet")
public class DechargeServlet extends HttpServlet {

    Membre m = new Membre();
    Materiel mat = new Materiel();
    Compte c = new Compte();

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "LIST";
            }

            switch (theCommand) {

                case "LIST":
                    listDecharge(request, response);
                    break;
               

                case "ADD":
                    addDecharge(request, response);
                    break;

                case "DELETE":
                    deleteDecharge(request, response);
                    break;

                default:
                    listDecharge(request, response);
            }

        } catch (Exception exc) {
            out.print(exc);
        }

    }

    private void deleteDecharge(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int idmem = Integer.parseInt(request.getParameter("membreid"));
        int idmat = Integer.parseInt(request.getParameter("materielid"));
        affectation aff = affectation.getaffectation(idmem, idmat);
        aff.deletedecharge();

        listDecharge(request, response);
    }

    private void addDecharge(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out=null;
        try {
            out = response.getWriter();
            int materiel = Integer.parseInt(request.getParameter("materiel"));
            int membre = Integer.parseInt(request.getParameter("membre"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
            String dateaffectation = datef.format(new java.util.Date());
            affectation aff = new affectation(membre, materiel, quantite, dateaffectation);
            Materiel ma=mat.getMateriel(materiel);
            
            if (ma.getQuantite()>=quantite){
                
                aff.addDecharge();
                
            }else{
                out.println("quantite grande");
            }  listDecharge(request, response);
        } catch (IOException ex) {
        } catch (Exception ex) {
            Logger.getLogger(DechargeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private void listDecharge(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<affectation> affectations = affectation.getdecharges();

        request.setAttribute("DECHARGE_LIST", affectations);
        request.setAttribute("chefs", m.getChefs());
        request.setAttribute("materiels", Materiel.getM_Aff());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-Decharge_m.jsp");
        dispatcher.forward(request, response);
    }

}
