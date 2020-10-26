
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;
import model.Budget;
import model.Repartition;
import model.Section;

@WebServlet(urlPatterns = {"/RepartitionServlet"})
public class RepartitionServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Command = request.getParameter("command");
        if(Command == null) Command="LIST";
        switch(Command){
            case "LIST" : listBudgets(request,response);
                            break;
            case "MODIFY" : repartir(request,response);
                            break;
        }
    }
    private void listBudgets(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
                try{
                    response.setContentType("text/html");
                    String year = request.getParameter("annees");
                    if(year == null) year="choisir une annee";
                    ArrayList<Budget> list = Budget.getBudgets();
                    ArrayList<Section> list2 = Section.GetSection();
                    Collections.sort(list);
                    request.setAttribute("Budgets", list);
                    request.setAttribute("Sections", list2);
                    request.setAttribute("year", year);
                    RequestDispatcher dis = request.getRequestDispatcher("/repartition.jsp");
                    dis.forward(request, response);
            
                }catch(ClassNotFoundException|SQLException e){}
    }

    public boolean allPositive(ArrayList<Double> liste){
        boolean all = true;
        for(Double x : liste){
            if(x < 0){
                all = false;
                break;
            }
        }
        return all;
    }
    
    private void repartir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try{
                    response.setContentType("text/html");
                    String year = request.getParameter("command2");
                    ArrayList<Article> list = Article.GetArticle();
                    ArrayList<Double> list1 = new ArrayList();
                    if((year != null) && !(year.equals("choisir une annee"))){
                        Budget temp = Budget.getBudgetById(Integer.parseInt(year));
                        int sum1 = 0, sum2 = 0;
                        for(Article a : list){
                            Repartition rep = a.getRepartition(year);
                            double mont = Double.parseDouble(request.getParameter(Integer.toString(a.getIdArticle())));
                            list1.add(mont);
                            if(rep != null)
                                sum1 += rep.getMontant_p();
                            sum2 += mont;
                        }
                        if((temp.getMontant_t() + sum1 >= sum2) && (allPositive(list1))){
                            for(Article a : list){
                            Repartition rep = a.getRepartition(year);
                            double mont = Double.parseDouble(request.getParameter(Integer.toString(a.getIdArticle())));
                                if(rep != null){
                                    if(mont != 0){
                                            temp.setMontant_t(temp.getMontant_t()+rep.getMontant_p()-mont);
                                            rep.setMontant_p(mont);
                                            rep.setSolde(mont);
                                            temp.editBudget();
                                            rep.editRepartition();
                                    }else{
                                        temp.setMontant_t(temp.getMontant_t()+rep.getMontant_p());
                                        temp.editBudget();
                                        rep.deleteRepartition();
                                    }
                                }else{
                                    if(mont != 0){
                                        Repartition rep2 = new Repartition(Integer.parseInt(year), a.getIdArticle(), mont, 0, mont);
                                        temp.setMontant_t(temp.getMontant_t()-mont);
                                        temp.editBudget();
                                        rep2.addRepartition();
                                    }
                                }
                            }
                        }
                    }
                    listBudgets(request, response);
                }catch(ClassNotFoundException|SQLException e){}
    }
}
