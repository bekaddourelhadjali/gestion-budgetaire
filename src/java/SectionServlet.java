

 
import model.Section;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/SectionServlet")
public class SectionServlet extends HttpServlet {
    ArrayList<Section> s;
    @Override
    public void init() throws ServletException {
        super.init(); 
        s=Section.GetSection();
    }
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String command=request.getParameter("command");
        if (command==null){
            command="List";
        }
        switch (command){
            case "List":{
                request.setAttribute("SectionList", s);
                RequestDispatcher dispatcher=request.getRequestDispatcher("Section.jsp");
                dispatcher.forward(request, response);
                break;
                
            }
            case "ADD":{
            try {
                Section ss=new Section(request.getParameter("Numero"),request.getParameter("Designation"));
                ss.AddSection();
                s=Section.GetSection();
                request.setAttribute("SectionList", s);
                RequestDispatcher dispatcher=request.getRequestDispatcher("Section.jsp");
                dispatcher.forward(request, response);
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SectionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            case "LOAD":{
            try {
                s=Section.GetSection();
                request.setAttribute("SectionList", s);
                request.setAttribute("Section", Section.GetSection(Integer.parseInt(request.getParameter("SectionId"))));
                RequestDispatcher dispatcher=request.getRequestDispatcher("Section.jsp");
                dispatcher.forward(request, response);
                break;
            } catch (ClassNotFoundException ex) {}
            }
            case "UPDATE":{
            try {
                Section ss=new Section(Integer.parseInt(request.getParameter("SectionId")),request.getParameter("Numero"),request.getParameter("Designation"));
                ss.EditSection();
                s=Section.GetSection();
                request.setAttribute("SectionList", s);
                RequestDispatcher dispatcher=request.getRequestDispatcher("Section.jsp");
                dispatcher.forward(request, response);
                break;
            } catch (ClassNotFoundException ex) { }
            }
            case "DELETE":{
            try {
                Section ss=new Section(Integer.parseInt(request.getParameter("SectionId")),request.getParameter("Numero"),request.getParameter("Designation"));
                ss.DeleteSection();
                s=Section.GetSection();
                request.setAttribute("SectionList", s);
                RequestDispatcher dispatcher=request.getRequestDispatcher("Section.jsp");
                dispatcher.forward(request, response);
                break;
            } catch (ClassNotFoundException ex) {}
            }
        }
        
         
    }
}