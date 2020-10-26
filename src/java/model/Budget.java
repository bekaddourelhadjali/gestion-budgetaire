
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Budget implements Comparable {
    
    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;
    
    private int annee;
    private double montant_d;
    private double montant_t;

    public Budget() {}

    public Budget(int annee, double montant_d, double montant_t) {
        this.annee = annee;
        this.montant_d = montant_d;
        this.montant_t = montant_t;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getMontant_d() {
        return montant_d;
    }

    public void setMontant_d(double montant_d) {
        this.montant_d = montant_d;
    }

    public double getMontant_t() {
        return montant_t;
    }

    public void setMontant_t(double montant_t) {
        this.montant_t = montant_t;
    }
    
    public void addBudget() throws ClassNotFoundException, SQLException {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s="select * from budget where annee="+this.annee;
            rs=stmt.executeQuery(s);
            if(!rs.next()){
                
                String s1="insert into budget(annee,montant_D,montant_T) values('"+this.annee+"','"+this.montant_d+"','"+this.montant_t+"')";
                stmt.executeUpdate(s1);
            
            }
    }
    
    public void deleteBudget() throws ClassNotFoundException, SQLException {
        
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "delete from budget where annee=" + this.annee;
            stmt.executeUpdate(s);
        
    }
    
    public void editBudget() throws ClassNotFoundException, SQLException{
        conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "update budget set montant_D='" + this.montant_d + "' ,montant_T='" + this.montant_t +
                    "' where annee=" + this.annee;
            stmt.executeUpdate(s);
    }
    
    public static ArrayList<Budget> getBudgets() throws ClassNotFoundException, SQLException{
        
        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        ArrayList<Budget> list = new ArrayList();
        String s = "select * from budget";
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            list.add(new Budget(rs.getInt("annee"), rs.getDouble("montant_D"), rs.getDouble("montant_T")));
        }
        return list;
    }
    
    public static Budget getLastBudget() throws ClassNotFoundException, SQLException{
        
        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        Budget temp = null;
        String s = "select * from budget where annee = (select max(annee) from budget)";
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            temp = new Budget(rs.getInt("annee"), rs.getDouble("montant_D"), rs.getDouble("montant_T"));
        }
        return temp;
    }
    
    public static Budget getBudgetById(int a) throws ClassNotFoundException {
        Budget x = new Budget();
        try {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from budget where annee=" + a;
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                x = new Budget(rs.getInt("annee"), rs.getDouble("montant_D"), rs.getDouble("montant_T"));
            }
        } catch (SQLException e) {}
        return x;
    }

    @Override
    public int compareTo(Object o) {
        return ((Budget)o).annee - this.annee;
    }
    
    public String convert(Double a){
        return Double.toString(a).substring(0, 4);
    }
    
}
