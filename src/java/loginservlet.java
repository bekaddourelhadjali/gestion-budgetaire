/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Compte;

/**
 *
 * @author bekal
 */
public class loginservlet extends HttpServlet {

    Compte c;
    PrintWriter out;

    @Override
    public void init() throws ServletException {

        super.init();
        c = new Compte();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        String comm = null;

        if (request.getParameter("command") != null) {
            comm = request.getParameter("command");
            if (comm.equals("logout")) {

                request.getSession().removeAttribute("email");
                request.getSession().removeAttribute("type");
                request.getSession().removeAttribute("id");
                request.getSession().invalidate();
                request.getRequestDispatcher("/login.jsp").forward(request, response);

            }

        } else {
            if (request.getSession().getAttribute("email") != null) {

                switch ((String) request.getSession().getAttribute("type")) {
                    case "admin":

                        request.getRequestDispatcher("/BudgetServlet").forward(request, response);

                        break;

                    case "membre":

                        request.getRequestDispatcher("AffectationServlet").forward(request, response);

                        break;
                    default:
                        
                        request.getRequestDispatcher("/loginservlet?command=logout").forward(request, response);

                        break;
                }
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        String comm = null;
        if (request.getParameter("command") != null) {
            comm = request.getParameter("command");
            if (comm.equals("login")) {
                try {
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    Compte comt = new Compte(email, Compte.MD5(password));
                    if (comt.getComptes().isEmpty()) {
                        if (email.equals("admin") && password.equals("admin")) {
                            request.getSession().setAttribute("email", email);
                            request.getSession().setAttribute("type", "admin");
                            request.setAttribute("command", "new");
                            request.getSession().setAttribute("id", 1);
                            request.getSession().setAttribute("nom", "ADMIN");
                            request.getRequestDispatcher("/EquipeServlet").forward(request, response);

                        }
                    } else {
                        comt = comt.log();

                        if (comt.getType() != null) {
                            switch (comt.getType()) {
                                case "admin":
                                    request.getSession().setAttribute("email", email);
                                    request.getSession().setAttribute("type", "admin");
                                    if (comt.getMembre().getEquipe().getChef() == comt.getMembre().getId()) {
                                        request.getSession().setAttribute("chef", "true");
                                    } else {
                                        request.getSession().setAttribute("chef", "false");
                                    }
                                    request.getSession().setAttribute("id", comt.getMembre().getId());
                                    request.getSession().setAttribute("nom", comt.getMembre().getNom().toUpperCase());
                                    request.getRequestDispatcher("/BudgetServlet").forward(request, response);

                                    break;

                                case "membre":

                                    request.getSession().setAttribute("email", email);
                                    if (comt.getMembre().getEquipe().getChef() == comt.getMembre().getId()) {
                                        request.getSession().setAttribute("chef", "true");
                                    } else {
                                        request.getSession().setAttribute("chef", "false");
                                    }
                                    request.getSession().setAttribute("type", "membre");
                                    request.getSession().setAttribute("id", comt.getMembre().getId());
                                    request.getSession().setAttribute("nom", comt.getMembre().getNom().toUpperCase());
                                    request.getRequestDispatcher("AffectationServlet").forward(request, response);

                                    break;
                                default:
                                    request.setAttribute("message", "EXCEPTION");
                                    request.getRequestDispatcher("/login.jsp").forward(request, response);

                                    break;
                            }
                        } else {
                            request.setAttribute("message", "Email ou mot de passe est incorrect");
                            request.getRequestDispatcher("/login.jsp").forward(request, response);
                        }
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    request.setAttribute("message", "EXCEPTION");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }else if (request.getSession().getAttribute("email") != null) {

                switch ((String) request.getSession().getAttribute("type")) {
                    case "admin":

                        request.getRequestDispatcher("/BudgetServlet").forward(request, response);

                        break;

                    case "membre":

                        request.getRequestDispatcher("AffectationServlet").forward(request, response);

                        break;
                    default:
                        
                        request.getRequestDispatcher("/lloginservlet?command=logout").forward(request, response);

                        break;
                }
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
}
/*
    
    }*/
