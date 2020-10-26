
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Article {
    
    private int IdArticle;
    private String Numero;
    private String Designation;
    private String Section;
   

    public Article() {}

    public Article(int IdArticle, String Numero, String Designation, String Section) {
        this.IdArticle = IdArticle;
        this.Numero = Numero;
        this.Designation = Designation;
        this.Section = Section;
    }

    public Article(String Numero, String Designation, String Section) {
        this.Numero = Numero;
        this.Designation = Designation;
        this.Section = Section;
    }

    
    public void AddArticle() throws ClassNotFoundException{
        try {   
                Connection conn=connectDb.connecterDB();
                Statement  stmt=conn.createStatement();
                String q="insert into article (numero,designation,idSection) values('"+this.Numero+"','"+this.Designation+"',"+Article.GetSectionId(this.Section)+");";
                stmt.execute(q);
            }catch (SQLException e) {}
    }
    
    public void EditArticle() throws ClassNotFoundException{
        try {
                Connection conn=connectDb.connecterDB();
                Statement  stmt=conn.createStatement();
                String q = "update article set numero='"+this.Numero+"',designation='"+this.Designation+"',idSection="+Article.GetSectionId(this.Section)+" where idArticle="+this.IdArticle+"";
                stmt.executeUpdate(q);
            }catch (SQLException e) {}              
    }
    
    public void DeleteArticle() throws ClassNotFoundException{
        try{
            Connection conn=connectDb.connecterDB();
            Statement  stmt=conn.createStatement();
            String  q="delete from article where idArticle="+this.IdArticle;
            stmt.executeUpdate(q);
            }catch(SQLException  e){
            }   
    }
    
    public  static  Article GetArticle(int i) throws ClassNotFoundException {
        Article s =null;
        try{    
            Connection conn=connectDb.connecterDB();
            Statement  stmt=conn.createStatement();
            String query = "SELECT * FROM article WHERE idArticle='"+i+"'";  
            ResultSet   rs=stmt.executeQuery(query);
                if(rs.next()){
                   s= new Article(rs.getInt("idArticle"),rs.getString("numero"),rs.getString("designation"),Article.GetSectionName(rs.getInt("idArticle")));}
            }catch(SQLException e){}
        return s;         
    }
    public  static  int GetSectionId(String s) throws ClassNotFoundException {
        int i=-1;
        try{  
                Connection conn=connectDb.connecterDB();
                Statement  stmt=conn.createStatement();
                String query = "SELECT * FROM Section WHERE Designation='"+s+"'";  
                ResultSet rs=stmt.executeQuery(query);
                if(rs.next()){
                   i= rs.getInt("idsection");}
            }catch(SQLException e){}
        return i;         
    }
    public  static  String GetSectionName(int i) throws ClassNotFoundException {
        String s=null;
        try{  
                Connection conn=connectDb.connecterDB();
                Statement  stmt=conn.createStatement();
                String query = "SELECT * FROM Section WHERE idSection="+i+"";  
                ResultSet rs=stmt.executeQuery(query);
                if(rs.next()){
                   s= rs.getString("designation");}
            }catch(SQLException e){}
        return s;         
    }
    public  static  ArrayList<String> GetSectionListName() throws ClassNotFoundException {
        ArrayList<String> data=new ArrayList<>();
        try{  
                Connection conn=connectDb.connecterDB();
                Statement  stmt=conn.createStatement();
                String query = "select * from section";  
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next()){
                    
                   data.add(rs.getString("designation"));}
            }catch(SQLException e){}
        return data;         
    }
    public  static ArrayList<Article> GetArticle() {
        ArrayList<Article> Data= new ArrayList<>();
            Data.clear();
        try{    
                Connection conn=connectDb.connecterDB();
                Statement  stmt=conn.createStatement();
                String query = "SELECT * FROM article ";
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next()){
                    Data.add(new Article(rs.getInt("idArticle"),rs.getString("numero"),rs.getString("designation"),Article.GetSectionName(rs.getInt("idSection"))));}
            }catch(Exception e){ e.printStackTrace();}
        return   Data;      
    }
    
    /*public static ArrayList<Magasin> Search(String s) {
        ArrayList<Magasin> PersonData= new ArrayList<>();
        try{
                PersonData.clear();
                conn=connectDb.connecterDB();
                stmt=conn.createStatement();
                String query = "SELECT * FROM Magasin  where   Nom  like '%"+s+"%'";
                rs=stmt.executeQuery(query);
                while (rs.next()){
                    PersonData.add(new Magasin(rs.getInt("idMagasin"),rs.getString("nom")));}
                
                }catch(Exception e){ e.printStackTrace();} 
            return   PersonData;
    }*/

    public int getIdArticle() {
        return IdArticle;
    }

    public String getNumero() {
        return Numero;
    }

    public String getDesignation() {
        return Designation;
    }

    public String getSection() {
        return Section;
    }

    public void setIdArticle(int IdArticle) {
        this.IdArticle = IdArticle;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public void setSection(String Section) {
        this.Section = Section;
    }
 
   public Repartition getRepartition(String s) {
        Repartition Data = null;
        boolean valid = true;
        int year = 0;
        try{
            year = Integer.parseInt(s);
        }catch(NumberFormatException e){
            valid = false;
        }
        if(valid){
            try{    
                Connection conn=connectDb.connecterDB();
                Statement  stmt=conn.createStatement();
                String query = "SELECT * FROM repartition WHERE idarticle = " + this.IdArticle + " AND annee = " + year;
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next()){
                    Data = new Repartition(rs.getInt("annee"),rs.getInt("idArticle"),rs.getDouble("montant_P"),rs.getDouble("montant_C"),rs.getDouble("solde"));
                }
            }catch(Exception e){ e.printStackTrace();}
        }
        return   Data;
    }
    
}

