/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Commande;
import model.Fourniture;
import model.Materiel;
import model.Produit;
import model.Repartition;
import model.Service;

/**
 *
 * @author bekal
 */
public class ProduitServlet extends HttpServlet {

    PrintWriter out;
    Commande c;
    Fourniture f;
    Materiel m;
    Service s;

    @Override
    public void init() throws ServletException {
        super.init();
        m = new Materiel();
        s = new Service();
        f = new Fourniture();
        c = new Commande();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        if (request.getParameter("command") != null) {
            if (request.getParameter("command").equals("delete")) {
                SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
                            String date = datef.format(new Date());
                            date = date.substring(0, 4);
                            double antsomme = 0;
                            double somme = 0;
                switch (request.getParameter("type")) {
                    case "service":
                        try {
                            s.setId(Integer.parseInt(request.getParameter("id")));  
                            s.getService(s.getId());
                            c = c.getCommande(Integer.parseInt(request.getParameter("idc")));
                            
                            ArrayList<Produit> produits = c.getProduits();
                            
                            for (Produit p : produits) {
                                somme += (p.getPrix() * p.getQuantite());
                            }
                            antsomme = somme;
                            somme = somme - (s.getPrix() * s.getQuantite());
                            
                            s.deleteByID();
                           
                        } catch (SQLException | ClassNotFoundException ex) {
                            out.println(ex);
                        }
                        break;
                    case "materiel":
                        try {
                            m.setId(Integer.parseInt(request.getParameter("id")));
                            m.getMateriel(m.getId());
                            c = c.getCommande(Integer.parseInt(request.getParameter("idc")));
                            
                            ArrayList<Produit> produits = c.getProduits();
                            
                            for (Produit p : produits) {
                                somme += (p.getPrix() * p.getQuantite());
                            }
                            antsomme = somme;
                            somme = somme - (m.getPrix() * m.getQuantite());
                            
                            m.deleteByID();
                        } catch (SQLException | ClassNotFoundException ex) {
                            out.println(ex);
                        }
                        break;
                    case "fourniture":
                        try {
                            f.setId(Integer.parseInt(request.getParameter("id")));
                            f.getFourniture(f.getId());
                            c = c.getCommande(Integer.parseInt(request.getParameter("idc")));
                            
                            ArrayList<Produit> produits = c.getProduits();
                            
                            for (Produit p : produits) {
                                somme += (p.getPrix() * p.getQuantite());
                            }
                            antsomme = somme;
                            somme = somme - (f.getPrix() * f.getQuantite());
                            
                            s.deleteByID();
                           
                            f.deleteByID();
                        } catch (SQLException | ClassNotFoundException ex) {
                            out.println(ex);
                        }
                        break;
                    default:
                        break;
                }
                 
                try {
                    somme = somme + (somme * c.getTva());
                            antsomme = antsomme + (antsomme * c.getTva());
                    Repartition rep;
                rep = Repartition.getRepartitionById(Integer.parseInt(date), c.getIdatricle());
                 rep.setMontant_c(rep.getMontant_c() - antsomme + somme);
                            rep.setSolde(rep.getMontant_p() - rep.getMontant_c());
                            rep.editRepartition();
                    
                } catch (ClassNotFoundException ex) {} catch (SQLException ex) {}
                            
                request.setAttribute("idc", c.getNumero());
                request.setAttribute("type", request.getParameter("type"));
                request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
            } else if (request.getParameter("command").equals("load")) {
                c.setNumero(Integer.parseInt(request.getParameter("idc")));
                switch (request.getParameter("type")) {
                    case "service":
                        try {
                            int i = Integer.parseInt(request.getParameter("id"));
                            s = s.getService(i);
                            s.setId(i);
                            request.setAttribute("service", s);
                            request.setAttribute("idc", c.getNumero());
                            request.setAttribute("type", "service");
                            request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);

                        } catch (SQLException | ClassNotFoundException ex) {
                            out.println(ex);
                        }
                        break;
                    case "materiel":
                        try {
                            int i = Integer.parseInt(request.getParameter("id"));
                            m = m.getCommand(i);
                            m.setId(i);

                            request.setAttribute("materiel", m);
                            request.setAttribute("idc", c.getNumero());
                            request.setAttribute("type", "materiel");
                            request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
                        } catch (SQLException | ClassNotFoundException ex) {
                            out.println(ex);
                        }
                        break;
                    case "fourniture":
                        try {
                            int i = Integer.parseInt(request.getParameter("id"));
                            f = f.getCommand(i);
                            f.setId(i);
                            request.setAttribute("fourniture", f);
                            request.setAttribute("idc", c.getNumero());
                            request.setAttribute("type", "fourniture");
                            request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
                        } catch (SQLException | ClassNotFoundException ex) {
                            out.println(ex);
                        }
                        break;
                    default:
                        break;
                }
            }
        } else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        if (request.getParameter("command") != null) {
            if (request.getParameter("command").equals("new")) {
                c.setNumero((int) request.getAttribute("idc"));
                request.getRequestDispatcher("/ProduitlistServlet.jsp").forward(request, response);
            }
        } else {

        }

        String designation;
        int quantite;
        float prix;
        String type;
        String description = "";
        String types = "";
        if (request.getParameter("command") != null) {
            if (request.getParameter("command").equals("add")) {
                designation = request.getParameter("designation");
                quantite = Integer.parseInt(request.getParameter("quantite"));
                type = request.getParameter("type");
                if (!type.equals("fourniture")) {
                    description = request.getParameter("desc");
                }
                if (type.equals("materiel")) {
                    types = request.getParameter("types");
                }
                prix = Float.parseFloat(request.getParameter("prix"));

                try {
                    c = c.getCommande(Integer.parseInt(request.getParameter("idc")));
                    SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
                    String date = datef.format(new Date());
                    date = date.substring(0, 4);
                    ArrayList<Produit> produits = c.getProduits();
                    double somme = 0;
                    for (Produit p : produits) {
                        somme += (p.getPrix() * p.getQuantite());
                    }
                    somme = somme + (prix * quantite);
                    somme = somme + (somme * c.getTva());

                    if (Repartition.getRepartitionById(Integer.parseInt(date), c.getIdatricle()).getSolde() > somme) {
                        switch (type) {
                            case "service":
                                s = new Service(description, designation, c, quantite, prix);
                                 {
                                    try {
                                        s.add();
                                    } catch (SQLException | ClassNotFoundException ex) {
                                        out.println(ex);
                                    }
                                }
                                break;
                            case "materiel":
                                m = new Materiel(types, description, designation, c, quantite, prix);
                                 {
                                    try {
                                        m.add();
                                    } catch (SQLException | ClassNotFoundException ex) {
                                        out.println(ex);
                                    }
                                }
                                break;
                            case "fourniture":
                                f = new Fourniture(designation, c, quantite, prix);
                                 {
                                    try {
                                        f.add();
                                    } catch (SQLException | ClassNotFoundException ex) {
                                        out.println(ex);
                                    }
                                }
                                break;

                        }
                        Repartition rep = Repartition.getRepartitionById(Integer.parseInt(date), c.getIdatricle());
                        rep.setMontant_c(rep.getMontant_c() + somme);
                        rep.setSolde(rep.getMontant_p() - rep.getMontant_c());
                        rep.editRepartition();
                    } else {
                        request.setAttribute("repart", "solde du repartition insuffisant");
                        request.setAttribute("idc", c.getNumero());
                        request.setAttribute("type", type);
                        request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);

                    }
                } catch (ClassNotFoundException | SQLException ex) {

                }

                request.setAttribute("idc", c.getNumero());
                request.setAttribute("type", type);
                request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
            } else if (request.getParameter("command").equals("update")) {
                designation = request.getParameter("designation");
                quantite = Integer.parseInt(request.getParameter("quantite"));
                type = request.getParameter("type");
                prix = Float.parseFloat(request.getParameter("prix"));
                c.setNumero(Integer.parseInt(request.getParameter("idc")));

                int id = Integer.parseInt(request.getParameter("id"));
                if (!type.equals("fourniture")) {
                    description = request.getParameter("desc");
                }
                if (type.equals("materiel")) {
                    types = request.getParameter("types");
                }
                SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
                String date = datef.format(new Date());
                date = date.substring(0, 4);
                float ant = 0;
                switch (type) {
                    case "materiel": {
                        Materiel mmm = new Materiel();
                        try {
                            mmm = mmm.getMateriel(id);
                            ant = mmm.getQuantite() * mmm.getPrix();
                        } catch (SQLException | ClassNotFoundException ex) {
                        }
                        break;
                    }
                    case "service": {
                        Service ser = new Service();
                        try {
                            ser = ser.getService(id);
                        } catch (SQLException | ClassNotFoundException ex) {

                        }
                        ant = ser.getQuantite() * ser.getPrix();

                        break;
                    }
                    case "fourniture": {
                        Fourniture ser = new Fourniture();
                        try {
                            ser = ser.getFourniture(id);
                        } catch (SQLException | ClassNotFoundException ex) {

                        }
                        ant = ser.getQuantite() * ser.getPrix();
                        break;
                    }
                }
                ArrayList<Produit> produits;
                try {
                    produits = c.getProduits();
                    double somme = 0;
                    for (Produit p : produits) {
                        somme += (p.getPrix() * p.getQuantite());
                    }
                    double antsomme = somme;
                    somme = somme + (prix * quantite);
                    somme = somme - ant;
                    somme = somme + (somme * c.getTva());
                    antsomme = antsomme + (antsomme * c.getTva());
                    Double solde = Repartition.getRepartitionById(Integer.parseInt(date), c.getIdatricle()).getSolde();

                    if ((solde + antsomme) > somme) {
                        switch (type) {
                            case "service": {
                                s = new Service(description, designation, c, quantite, prix);
                                s.setId(id);
                                try {
                                    s.updatep();
                                } catch (SQLException | ClassNotFoundException ex) {
                                    out.println(ex);
                                }
                            }
                            break;
                            case "materiel": {
                                m = new Materiel(types, description, designation, c, quantite, prix);
                                m.setId(id);

                                try {
                                    m.updatep();
                                } catch (SQLException | ClassNotFoundException ex) {
                                    out.println(ex);
                                }
                            }
                            break;
                            case "fourniture": {
                                f = new Fourniture(designation, c, quantite, prix);
                                f.setId(id);
                                try {
                                    f.updatep();
                                } catch (SQLException | ClassNotFoundException ex) {
                                    out.println(ex);
                                }
                            }
                            break;
                        }
                        Repartition rep = Repartition.getRepartitionById(Integer.parseInt(date), c.getIdatricle());
                        rep.setMontant_c(rep.getMontant_c() - antsomme + somme);
                        rep.setSolde(rep.getMontant_p() - rep.getMontant_c());
                        rep.editRepartition();
                    } else {
                        request.setAttribute("repart", "solde du repartition insuffisant");
                        request.setAttribute("idc", c.getNumero());
                        request.setAttribute("type", type);
                        request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
                    }

                } catch (ClassNotFoundException ex) {
                } catch (SQLException ex) {
                }

                request.setAttribute("idc", c.getNumero());
                request.setAttribute("type", type);
                request.getRequestDispatcher("/ProduitlistServlet").forward(request, response);
            }
        } else {
        }
    }
}
