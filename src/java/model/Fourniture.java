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
import static model.Materiel.con;

/**
 *
 * @author BRAHIM
 */
public class Fourniture extends Produit{
    public Fourniture(){}
    public Fourniture(int id, String designation, Commande commande, int quantite, float prix) {
        super(id, designation, commande, quantite, prix);
       
    }
    public Fourniture(String designation, Commande commande, int quantite, float prix) {
        super(designation, commande, quantite, prix);
       
    }
    static Connection con=null;
    static  Statement stmt = null;
    static  ResultSet rs = null;
  
    public void add() throws SQLException, ClassNotFoundException{
         con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "insert into fourniture (designation,quantite,prix,numero) values('"+this.getDesignation()+"','"+this.getQuantite()+"','"+this.getPrix()+"','"+this.commande.getNumero()+"')";
        stmt.executeUpdate(query);
        
    }
    
    public void update() throws SQLException, ClassNotFoundException{
        con=connectDb.connecterDB();
        stmt =con.createStatement();
      
        String query=
        "update fourniture set designation='"+this.designation+"',quantite='"+this.quantite+"',prix='"+this.prix+"' where idfourniture="+this.id;
        stmt.executeUpdate(query);
    }
    public void updatep() throws SQLException, ClassNotFoundException{
        con=connectDb.connecterDB();
        stmt =con.createStatement();
      
        String query=
        "update fourniture set designation='"+this.designation+"',quantite="+this.quantite+",prix="+this.prix+" where idfourniture="+this.id;
        stmt.executeUpdate(query);
    }
    public void deleteByID() throws SQLException, ClassNotFoundException{
         con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "delete from fourniture where idfourniture='"+this.id+"'";
        stmt.executeUpdate(query);
    }
    
    public void deleteByCommand() throws SQLException, ClassNotFoundException{
         con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "delete from fourniture where numero='"+this+"'";
        stmt.executeUpdate(query);
    }

   public static ArrayList<Fourniture> getAll() throws SQLException, ClassNotFoundException{
       ArrayList<Fourniture> af = new ArrayList<>();
         con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "Select * from fourniture";
        rs=stmt.executeQuery(query);
        
        while(rs.next()){
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));
            af.add(new Fourniture(rs.getString("designation"), c, rs.getInt("quantite") , rs.getFloat("prix")));
        }
        return af;
    }
   
   public static ArrayList<Fourniture> getByCommand(int numero) throws SQLException, ClassNotFoundException{
       ArrayList<Fourniture> af = new ArrayList<>();
         con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "Select * from fourniture where numero="+numero;
        rs=stmt.executeQuery(query);
        
        while(rs.next()){
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));
            af.add(new Fourniture(rs.getInt("idfourniture"),rs.getString("designation"),c,rs.getInt("quantite") , rs.getFloat("prix")));
        }
        return af;
    }
   public Fourniture  getCommand(int numero) throws SQLException, ClassNotFoundException{
       Fourniture af = new Fourniture();
        con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "Select * from fourniture where idfourniture="+numero;
        rs=stmt.executeQuery(query);
        
        while(rs.next()){
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));
            
           af=new Fourniture(rs.getString("designation"), c, rs.getInt("quantite"),  rs.getFloat("prix"));
        }
        return af;
    }
   public static ArrayList<Fourniture> getF_Aff() throws SQLException, ClassNotFoundException {
        ArrayList<Fourniture> af = new ArrayList<>();
        con = connectDb.connecterDB();
        stmt = con.createStatement();
        Commande c = new Commande();

        String query
                = "select * from fourniture where quantite>0";
        rs = stmt.executeQuery(query);

        while (rs.next()) {

            c.setNumero(rs.getInt("numero"));
            af.add(new Fourniture(rs.getInt("idfourniture"),rs.getString("designation"),c,rs.getInt("quantite") , rs.getFloat("prix")));
        }
        return af;
    }
   public Fourniture getFourniture(int numero) throws SQLException, ClassNotFoundException {
        Fourniture af = new Fourniture();
        con = connectDb.connecterDB();
        stmt = con.createStatement();

        String query
                = "Select * from fourniture where idfourniture=" + numero;
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));
            af = new Fourniture(rs.getInt("idfourniture"), rs.getString("designation"),c, rs.getInt("quantite"), rs.getFloat("prix"));
        }
        return af;
    }
}
