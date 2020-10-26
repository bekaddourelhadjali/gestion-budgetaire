
package model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Section {
    
    private int IdSection;
    private String Numero;
    private String Designation;
   

    public Section() {}

    public Section(String Numero, String Designation) {
        this.Numero = Numero;
        this.Designation = Designation;
    }

    public Section(int IdSection, String Numero, String Designation) {
        this.IdSection = IdSection;
        this.Numero = Numero;
        this.Designation = Designation;
    }
    
    
    
    public void AddSection() throws ClassNotFoundException{
        try {   
                Connection conn=connectDb.connecterDB();
                Statement stmt=conn.createStatement();
                String q="insert into section (numero,designation) values('"+this.Numero+"','"+this.Designation+"');";
                stmt.execute(q);
            }catch (SQLException e) {}
    }
    
    public void EditSection() throws ClassNotFoundException{
        try {
                Connection conn=connectDb.connecterDB();
                Statement stmt=conn.createStatement();
                String q = "update section set numero='"+this.Numero+"',designation='"+this.Designation+"' where idSection="+this.IdSection+"";
                stmt.executeUpdate(q);
            }catch (SQLException e) {}              
    }
    
    public void DeleteSection() throws ClassNotFoundException{
        try{
            Connection conn=connectDb.connecterDB();
            Statement stmt=conn.createStatement();
            String  q="delete from section where idSection="+this.IdSection;
            stmt.executeUpdate(q);
            }catch(SQLException  e){
            }   
    }
    
    public  static  Section GetSection(int i) throws ClassNotFoundException {
        Section s =null;
        try{    Connection conn=connectDb.connecterDB();
                Statement stmt=conn.createStatement();
                String query = "SELECT * FROM Section WHERE idSection='"+i+"'";  
                ResultSet rs=stmt.executeQuery(query);
                if(rs.next()){
                   s= new Section(rs.getInt("idsection"),rs.getString("numero"),rs.getString("designation"));}
            }catch(SQLException e){}
        return s;         
    }
    public  static ArrayList<Section> GetSection() {
        ArrayList<Section> Data= new ArrayList<>();
            Data.clear();
        try{    
                Connection conn=connectDb.connecterDB();
                Statement stmt=conn.createStatement();
                String query = "SELECT * FROM Section ";
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next()){
                    Data.add(new Section(rs.getInt("idsection"),rs.getString("numero"),rs.getString("designation")));}
            }catch(Exception e){ e.printStackTrace();}
        return   Data;      
    }
    public ArrayList<Article> getArticles() {
            ArrayList<Article> Data= new ArrayList<>();
        try{    
                Connection conn=connectDb.connecterDB();
                Statement stmt=conn.createStatement();
                String query = "SELECT * FROM article where idsection = " +this.IdSection;
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next()){
                    Data.add(new Article(rs.getInt("idarticle"),rs.getString("numero"),rs.getString("designation"),this.Designation));
                }
            }catch(Exception e){ e.printStackTrace();}
        return   Data;
    }

    public int getIdSection() {
        return IdSection;
    }

    public String getNumero() {
        return Numero;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setIdSection(int IdSection) {
        this.IdSection = IdSection;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }
    
    
}

