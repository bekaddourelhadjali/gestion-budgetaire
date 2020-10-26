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
public class Service extends Produit{
    
    private String description;

    public Service(String designation, Commande commande, int quantite, float prix) {
        super(designation, commande, quantite, prix);
       
    }

    public Service(String description, String designation, Commande commande, int quantite, float prix) {
        super(designation, commande, quantite, prix);
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public Service(){}
    public Service(String description, int id, String designation, Commande commande, int quantite, float prix) {
        super(id, designation, commande, quantite, prix);
        this.description = description;
    }
        static Connection con;
        static  Statement stmt = null;
    static  ResultSet rs = null;
  
    public void add() throws SQLException, ClassNotFoundException{
        con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "insert into Service (designation,quantite,prix,description,numero) values('"+this.designation+"','"+this.quantite+"','"+this.prix+"','"+this.description+"','"+this.commande.getNumero()+"')";
        stmt.executeUpdate(query);
        
    }
    
    public void update() throws SQLException, ClassNotFoundException{
         con=connectDb.connecterDB();
        stmt =con.createStatement();
      
        String query=
        "update Service set designation='"+this.designation+"',quantite='"+this.quantite+"',prix='"+this.prix+"',description='"+this.description+"',numero='"+this+"' where idservice="+this.id;
        stmt.executeUpdate(query);
    }
    public void updatep() throws SQLException, ClassNotFoundException{
         con=connectDb.connecterDB();
        stmt =con.createStatement();
      
        String query=
        "update Service set designation='"+this.designation+"',quantite="+this.quantite+",prix="+this.prix+" where idservice="+this.id;
        stmt.executeUpdate(query);
    }
    
    public void deleteByID() throws SQLException, ClassNotFoundException{
        con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "delete from Service where idservice='"+this.id+"'";
        stmt.executeUpdate(query);
    }
    
    public void deleteByCommand() throws SQLException, ClassNotFoundException{
        con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "delete from Service where numero='"+this+"'";
        stmt.executeUpdate(query);
    }

   public static ArrayList<Service> getAll() throws SQLException, ClassNotFoundException{
       ArrayList<Service> af = new ArrayList<>();
        con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "Select * from Service";
        rs=stmt.executeQuery(query);
        
        while(rs.next()){
           Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));
            
            af.add(new Service(rs.getString("description"),rs.getInt("idservice"),rs.getString("designation"),c,rs.getInt("quantite") , rs.getFloat("prix")));
        }
        return af;
    }
   
   public static ArrayList<Service> getByCommand(int numero) throws SQLException, ClassNotFoundException{
       ArrayList<Service> af = new ArrayList<>();
        con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "Select * from Service where numero="+numero;
        rs=stmt.executeQuery(query);
        
        while(rs.next()){
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));
            
           af.add(new Service(rs.getString("description"),rs.getInt("idservice"),rs.getString("designation"),c,rs.getInt("quantite") , rs.getFloat("prix")));
        }
        return af;
    }
   public Service  getService(int numero) throws SQLException, ClassNotFoundException{
       Service af = new Service();
        con=connectDb.connecterDB();
        stmt =con.createStatement();
        
        String query=
        "Select * from Service where idservice="+numero;
        rs=stmt.executeQuery(query);
        
        while(rs.next()){
            Commande c = new Commande();
            c.setNumero(rs.getInt("numero"));
            
           af=new Service(rs.getString("description"),rs.getInt("idservice"),rs.getString("designation"),c,rs.getInt("quantite") , rs.getFloat("prix"));
        }
        return af;
    }
}

    

