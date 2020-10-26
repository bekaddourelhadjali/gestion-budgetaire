
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Repartition {
    
    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;
    
    private int annee;
    private int idArticle;
    private double montant_p;
    private double montant_c;
    private double solde;
    
    public Repartition(){}

    public Repartition(int annee, int idArticle, double montant_p, double montant_c, double solde) {
        this.annee = annee;
        this.idArticle = idArticle;
        this.montant_p = montant_p;
        this.montant_c = montant_c;
        this.solde = solde;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public double getMontant_p() {
        return montant_p;
    }

    public void setMontant_p(double montant_p) {
        this.montant_p = montant_p;
    }

    public double getMontant_c() {
        return montant_c;
    }

    public void setMontant_c(double montant_c) {
        this.montant_c = montant_c;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    public void addRepartition() throws ClassNotFoundException, SQLException {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s="select * from repartition where annee='"+this.annee+"' and idarticle='"+this.idArticle+"'";
            rs=stmt.executeQuery(s);
            if(!rs.next()){
                
                String s1="insert into repartition(annee,idarticle,montant_P,montant_C,solde) values('"+this.annee+"','"+this.idArticle+"','"+this.montant_p+"','"+this.montant_c+"','"+this.solde+"')";
                stmt.executeUpdate(s1);
            
            }
    }
    
    public void deleteRepartition() throws ClassNotFoundException, SQLException {
        
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "delete from repartition where annee = " + this.annee + " and idarticle = " + this.idArticle;
            stmt.executeUpdate(s);
        
    }
    
    public void editRepartition() throws ClassNotFoundException, SQLException{
        conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "update repartition set montant_P = " + this.montant_p + " ,montant_C = " + this.montant_c + " ,solde = " + this.solde +
                    " where annee = " + this.annee + " and idarticle = " + this.idArticle;
            stmt.executeUpdate(s);
    }
    
    public static ArrayList<Repartition> getRepartitions() throws ClassNotFoundException, SQLException{
        
        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        ArrayList<Repartition> list = new ArrayList();
        String s = "select * from repartition";
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            list.add(new Repartition(rs.getInt("annee"), rs.getInt("idarticle"), rs.getDouble("montant_P"), rs.getDouble("montant_C"), rs.getDouble("solde")));
        }
        return list;
    }
    
    public static Repartition getRepartitionById(int a,int b) throws ClassNotFoundException, SQLException {
        Repartition x = new Repartition();
        try {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from repartition where annee=" + a + " and idarticle=" + b;
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                x = new Repartition(rs.getInt("annee"), rs.getInt("idarticle"), rs.getDouble("montant_P"), rs.getDouble("montant_C"), rs.getDouble("solde"));
            }
        } catch (SQLException e) {
        }
        return x;
    }
    
    public static ArrayList<Repartition> getRepartitionByYear(int a) throws ClassNotFoundException, SQLException {
        ArrayList<Repartition> x = new ArrayList();
        try {
            conn = connectDb.connecterDB();           
            stmt = conn.createStatement();
            String s = "select * from repartition where annee=" + a;
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                x.add(new Repartition(rs.getInt("annee"), rs.getInt("idarticle"), rs.getDouble("montant_P"), rs.getDouble("montant_C"), rs.getDouble("solde")));
            }
        } catch (SQLException e) {
        }
        return x;
    }
 
}
