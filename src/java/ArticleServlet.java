


import model.Article;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Section;

@WebServlet(name = "ArticleServlet", urlPatterns = {"/ArticleServlet"})
public class ArticleServlet extends HttpServlet {
    ArrayList<Article> l;
    @Override
    public void init() throws ServletException {
        super.init(); 
        l=Article.GetArticle();
    }
    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ArticleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ArticleServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String command=request.getParameter("command");
        if (command==null){
            command="List";
        }
        switch (command){
            case "List":{
                l=Article.GetArticle();
                request.setAttribute("ArticleList", l);
                RequestDispatcher dispatcher=request.getRequestDispatcher("Article.jsp");
                dispatcher.forward(request, response);
                break;
                
            }
            case "ADD":{
                
             try {
                 Article ll=new Article(request.getParameter("Numero"),request.getParameter("Designation"),request.getParameter("Section"));
                 ll.AddArticle();
                 l=Article.GetArticle();
                 request.setAttribute("ArticleList", l);
                 RequestDispatcher dispatcher=request.getRequestDispatcher("Article.jsp");
                 dispatcher.forward(request, response);
                 break;
             } catch (ClassNotFoundException ex) {}
            }
            case "LOAD":{
             try {
                 l=Article.GetArticle();
                 request.setAttribute("ArticleList", l);
                 request.setAttribute("Article", Article.GetArticle(Integer.parseInt(request.getParameter("ArticleId"))));
                 RequestDispatcher dispatcher=request.getRequestDispatcher("Article.jsp");
                 dispatcher.forward(request, response);
                 break;
             } catch (ClassNotFoundException ex) {}
            }
            case "UPDATE":{
             try {
                 Article ll=new Article(Integer.parseInt(request.getParameter("ArticleId")),request.getParameter("Numero"),request.getParameter("Designation"),request.getParameter("Section"));
                 ll.EditArticle();
                 l=Article.GetArticle();
                 request.setAttribute("ArticleList", l);
                 RequestDispatcher dispatcher=request.getRequestDispatcher("Article.jsp");
                 dispatcher.forward(request, response);
                 break;
             } catch (ClassNotFoundException ex) {}
            }
            case "DELETE":{
             try {
                 Article ll=new Article(Integer.parseInt(request.getParameter("ArticleId")),request.getParameter("Numero"),request.getParameter("Designation"),request.getParameter("Section"));
                 ll.DeleteArticle();
                 l=Article.GetArticle();
                 request.setAttribute("ArticleList", l);
                 RequestDispatcher dispatcher=request.getRequestDispatcher("Article.jsp");
                 dispatcher.forward(request, response);
                 break;
             } catch (ClassNotFoundException ex) {}
            }

        }
    }
}

