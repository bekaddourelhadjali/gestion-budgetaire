
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Budget;

@WebServlet(urlPatterns = {"/BudgetServlet"})
public class BudgetServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Command = request.getParameter("command");
        if(Command == null) Command="LIST";
        switch(Command){
            case "LIST" : listBudgets(request,response);
                            break;
            case "ADD" :  addBudget(request,response);
                            break;
        }
    }

    private void listBudgets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try{
                ArrayList<Budget> list = Budget.getBudgets();
                response.setContentType("text/html");
                request.setAttribute("Budgets", list);
                RequestDispatcher dis = request.getRequestDispatcher("/budget.jsp");
                dis.forward(request, response);
            
            }catch(ClassNotFoundException|SQLException e){}
    }

    private void addBudget(HttpServletRequest request, HttpServletResponse response) {
        try{
            double montant = Double.parseDouble(request.getParameter("montant"));
                Budget LastBudget = Budget.getLastBudget();
                int currDate = new Date().getYear()+1900;
                if(LastBudget != null){
                    if(LastBudget.getAnnee() == currDate){
                        LastBudget.setMontant_d(LastBudget.getMontant_d()+montant);
                        LastBudget.setMontant_t(LastBudget.getMontant_t()+montant);
                        LastBudget.editBudget();
                    }else{
                        Budget budget = new Budget(currDate,LastBudget.getMontant_t()+montant,LastBudget.getMontant_t()+montant);
                        budget.addBudget();
                    }
                }else{
                    Budget budget = new Budget(currDate,montant,montant);
                    budget.addBudget();
                }
            listBudgets(request, response);
        }catch(Exception e){}
    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         listBudgets(request, response);
     }
}
