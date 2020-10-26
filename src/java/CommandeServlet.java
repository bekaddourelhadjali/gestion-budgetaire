/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import model.Article;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Commande;
import model.Fournisseur;
import model.Excel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author bekal
 */
public class CommandeServlet extends HttpServlet {

    Commande c;
    Fournisseur f;
    Article a;
    PrintWriter out ;
    @Override
    public void init() throws ServletException {

        super.init();
        c = new Commande();
        f = new Fournisseur();
        a = new Article();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out= response.getWriter();
        String theCommand = request.getParameter("command");
        if (theCommand == null) {
            theCommand = "LIST";
        }
        switch (theCommand) {
            case "LIST":
                request.getRequestDispatcher("/CommandelistServlet").forward(request, response);

                break;

            case "delete":
            
                    deleteCommande(request, response);
                
                break;
            case "loadc":
                loadCommande(request, response);

                break;
            
            case "valider":
                validerCommande(request, response);

                break;
                
                case "print":
      
       
             imprimerCommande(request, response);

                break;

            default: {
                request.getRequestDispatcher("/CommandelistServlet").forward(request, response);
            }
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out= response.getWriter();
        String theCommand = request.getParameter("command");
        if (theCommand == null) {
            request.getRequestDispatcher("/CommandelistServlet").forward(request, response);
        } else {
            if(theCommand.equals("login")){
                request.getRequestDispatcher("/CommandelistServlet").forward(request, response);
            }else
            if (theCommand.equals("add")) {
                
                int idarticle = Integer.parseInt(request.getParameter("article"));
                int idfournisseur = Integer.parseInt(request.getParameter("fournisseur"));
                float tva = Float.parseFloat("0." + request.getParameter("tva"));
                SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
                String type=request.getParameter("type");
                String date = datef.format(new Date());
                try {
                     
                    a = Article.GetArticle(idarticle);
                    
                    String objet = a.getDesignation();
                    
                    c = new Commande(type, objet, date, null, tva, idarticle, idfournisseur);
                    c.addCommande();
                    
                    c = c.getCommandeId();
                    
                    request.setAttribute("idc", c.getNumero());
                    request.setAttribute("command", "new");
                    request.setAttribute("type",type);
                   
                    request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    out.println(ex);
                }

            } else if (theCommand.equals("update")) {
                int idarticle = Integer.parseInt(request.getParameter("article"));
                int idfournisseur = Integer.parseInt(request.getParameter("fournisseur"));
                float tva = Float.parseFloat("0." + request.getParameter("tva"));
                c.setNumero(Integer.parseInt(request.getParameter("idc")));
                try {
                    c = c.getCommande(c.getNumero());
                    a = Article.GetArticle(idarticle);
                    String objet = a.getDesignation();
                    c = new Commande(c.getNumero(), c.getType(), objet, c.getDate(), null, tva, idarticle, idfournisseur);
                    c.editCommande();

                    request.setAttribute("idc", c.getNumero());
                    request.setAttribute("command", "new");
                    request.setAttribute("type",c.getType());
                    request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    out.println(ex.toString());
                }
            }
        }
    }

    private void deleteCommande(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        try {
            out= response.getWriter();
            c.deleteCommande(Integer.parseInt(request.getParameter("id")));
            request.getRequestDispatcher("/CommandelistServlet").forward(request, response);
        } catch (ClassNotFoundException | IOException ex) {out.println(ex); }
    }

   

    private void loadCommande(HttpServletRequest request, HttpServletResponse response) {
        try {
            out= response.getWriter();
            c=c.getCommande(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("command", "new");
            request.setAttribute("idc",Integer.parseInt(request.getParameter("id")));
            request.setAttribute("type", request.getParameter("type"));
            request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
        } catch ( IOException | ServletException |ClassNotFoundException ex) {out.println(ex); }  
    }  

    private void validerCommande(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            out= response.getWriter();
            SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
            String date = datef.format(new Date());
            c.setNumero(Integer.parseInt(request.getParameter("id")));
            c=c.getCommande(c.getNumero());
            c.setDatel(date);
            c.valider();
            request.getRequestDispatcher("/CommandelistServlet").forward(request, response);
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            out.println(ex);
        }
    }

    private void imprimerCommande(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        out= response.getWriter();
        try {
            c=c.getCommande(Integer.parseInt(request.getParameter("id")));
           Excel.CreateCOmExcel(c.getFournisseur(), c);
           request.getRequestDispatcher("/CommandelistServlet").forward(request, response);
        } catch (ClassNotFoundException ex) {out.println(ex);} catch (FileNotFoundException | SQLException | ParseException ex) {out.println(ex);}
        
    }

    

   

}
