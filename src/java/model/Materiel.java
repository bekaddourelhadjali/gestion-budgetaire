/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author BRAHIM
 */
public class Materiel extends Produit {

    boolean enService;
    String codeBar;
    String type;
    String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Materiel(String designation, Commande commande, int quantite, float prix) {
        super(designation, commande, quantite, prix);

    }

    public Materiel(boolean enService, String codeBar, String type, String description, int id, String designation, Commande commande, int quantite, float prix) {
        super(id, designation, commande, quantite, prix);
        this.enService = enService;
        this.codeBar = codeBar;
        this.type = type;
        this.description = description;
    }

    public Materiel(String designation, int quantite, float prix, boolean enService, String codeBar, String type, String description, Commande commande) {
        super(designation, commande, quantite, prix);
        this.enService = enService;
        this.codeBar = codeBar;
        this.type = type;
        this.description = description;
    }

    public Materiel(int id, String designation, int quantite, float prix, boolean enService, String codeBar, String type, String description, Commande commande) {
        super(id, designation, commande, quantite, prix);

        this.enService = enService;
        this.codeBar = codeBar;
        this.type = type;
        this.description = description;
    }

    public Materiel() {
    }

    public Materiel(String type, String description, String designation, Commande commande, int quantite, float prix) {
        super(designation, commande, quantite, prix);
        this.type = type;
        this.description = description;
    }

    public boolean isEnService() {
        return enService;
    }

    public String getCodeBar() {
        return codeBar;
    }

    public String getType() {
        return type;
    }

    public void setEnService(boolean enService) {
        this.enService = enService;
    }

    public void setCodeBar(String codeBar) {
        this.codeBar = codeBar;
    }

    public void setType(String type) {
        this.type = type;
    }
    static Connection con;
    static Statement stmt = null;
    static ResultSet rs = null;

    public void add() throws SQLException, ClassNotFoundException {
        con = connectDb.connecterDB();
        stmt = con.createStatement();

        String query
                = "insert into materiel (designation,quantite,prix,en_service,codebar,type,description,numero)"
                + " values('" + this.designation + "','" + this.quantite + "','" + this.prix + "','" + 1 + "','" + this.codeBar + "',"
                + "'" + this.type + "','" + this.description + "','" + this.commande.getNumero() + "')";
        stmt.executeUpdate(query);

    }

    public void update() throws SQLException, ClassNotFoundException {
        con = connectDb.connecterDB();
        stmt = con.createStatement();
        String query
                = "update materiel set designation='" + this.designation + "',quantite='" + this.quantite + "',prix='" + this.prix + "'"
                + "en_service=" + this.enService + ",codebar=" + this.codeBar + ",type=" + this.type + ",description=" + this.description + ",numero=" + this.commande.getNumero() + " where idmateriel=" + this.id;
        stmt.executeUpdate(query);
    }

    public void updatep() throws SQLException, ClassNotFoundException {
        con = connectDb.connecterDB();
        stmt = con.createStatement();
        String query
                = "update materiel set description='"+this.description+"', designation='" +"', type='" + this.type + "',quantite=" + this.quantite + ",prix=" + this.prix + "" + " where idmateriel=" + this.id;
        stmt.executeUpdate(query);
    }

    public void deleteByID() throws SQLException, ClassNotFoundException {
        con = connectDb.connecterDB();
        stmt = con.createStatement();

        String query
                = "delete from materiel where idmateriel=" + this.id + "";
        stmt.executeUpdate(query);
    }

    public void deleteByCommand() throws SQLException, ClassNotFoundException {
        con = connectDb.connecterDB();
        stmt = con.createStatement();

        String query
                = "delete from materiel where numero=" + this.commande.getNumero() + "";
        stmt.executeUpdate(query);
    }

    public static ArrayList<Materiel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Materiel> af = new ArrayList<>();
        con = connectDb.connecterDB();
        stmt = con.createStatement();

        String query
                = "Select * from materiel";
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));

            af.add(new Materiel(rs.getString("designation"), rs.getInt("quantite"), rs.getFloat("prix"), rs.getBoolean("en_service"), rs.getString("codebar"), rs.getString("type"), rs.getString("description"), c));
        }
        return af;
    }

    public static ArrayList<Materiel> getByCommand(int numero) throws SQLException, ClassNotFoundException {
        ArrayList<Materiel> af = new ArrayList<>();
        con = connectDb.connecterDB();
        stmt = con.createStatement();
        Commande c = new Commande();

        String query
                = "Select * from materiel where numero=" + numero;
        rs = stmt.executeQuery(query);

        while (rs.next()) {

            c.setNumero(rs.getInt("numero"));
            af.add(new Materiel(rs.getInt("idmateriel"), rs.getString("designation"), rs.getInt("quantite"), rs.getFloat("prix"), rs.getBoolean("en_service"), rs.getString("codebar"), rs.getString("type"), rs.getString("description"), c));
        }
        return af;
    }

    public Materiel getCommand(int numero) throws SQLException, ClassNotFoundException {
        Materiel af = new Materiel();
        con = connectDb.connecterDB();
        stmt = con.createStatement();

        String query
                = "Select * from materiel where idmateriel=" + numero;
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));

            af = new Materiel(rs.getInt("idmateriel"), rs.getString("designation"), rs.getInt("quantite"), rs.getFloat("prix"), rs.getBoolean("en_service"), rs.getString("codebar"), rs.getString("type"), rs.getString("description"), c);
        }
        return af;
    }

    public Materiel getMateriel(int numero) throws SQLException, ClassNotFoundException {
        Materiel af = new Materiel();
        con = connectDb.connecterDB();
        stmt = con.createStatement();

        String query
                = "Select * from materiel where idmateriel=" + numero;
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));
            af = new Materiel(rs.getInt("idmateriel"), rs.getString("designation"), rs.getInt("quantite"), rs.getFloat("prix"), rs.getBoolean("en_service"), rs.getString("codebar"), rs.getString("type"), rs.getString("description"), c);
        }
        return af;
    }
    
    
    public static ArrayList<Materiel> getM_Aff() throws SQLException, ClassNotFoundException {
        ArrayList<Materiel> af = new ArrayList<>();
        con = connectDb.connecterDB();
        stmt = con.createStatement();
        Commande c = new Commande();

        String query
                = "select * from materiel where quantite>0";
        rs = stmt.executeQuery(query);

        while (rs.next()) {

            c.setNumero(rs.getInt("numero"));
            af.add(new Materiel(rs.getInt("idmateriel"), rs.getString("designation"), rs.getInt("quantite"), rs.getFloat("prix"), rs.getBoolean("en_service"), rs.getString("codebar"), rs.getString("type"), rs.getString("description"), c));
        }
        return af;
    }
    
    
}
