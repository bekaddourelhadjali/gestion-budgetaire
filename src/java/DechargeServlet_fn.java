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
import model.Membre;
import model.Fourniture;
import model.affectation_fn;

@WebServlet("/DechargeServlet_fn")
public class DechargeServlet_fn extends HttpServlet {

    Membre m = new Membre();
    Fourniture f = new Fourniture();
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
        int idmat = Integer.parseInt(request.getParameter("fournitureid"));
        affectation_fn aff = affectation_fn.getaffectation(idmem, idmat);
        aff.deletedecharge();

        listDecharge(request, response);
    }

    private void addDecharge(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out=response.getWriter();
        try {
            int materiel = Integer.parseInt(request.getParameter("fourniture"));
            int membre = Integer.parseInt(request.getParameter("membre"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
            String dateaffectation = datef.format(new java.util.Date());
            affectation_fn aff = new affectation_fn(membre, materiel, quantite, dateaffectation);
            
            aff.addDecharge();
            
            listDecharge(request, response);
        } catch (Exception ex) {
            out.println(ex);
        }
    }

    private void listDecharge(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<affectation_fn> affectations = affectation_fn.getdecharges();

        request.setAttribute("DECHARGE_LIST", affectations);
        request.setAttribute("chefs", m.getChefs());
        request.setAttribute("fournitures",Fourniture.getF_Aff());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-Decharge_f.jsp");
        dispatcher.forward(request, response);
    }

}
