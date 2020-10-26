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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bekal
 */
public class Membre {
    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;

    private int id;
    private String nom;
    private String prenom;
    private String date_n;
    private String adresse;
    private String grade;
    private Equipe equipe;

   

    public Membre() {}


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_n(String date_n) {
        this.date_n = date_n;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDate_n() {
        return date_n;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getGrade() {
        return grade;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Membre(int id, String nom, String prenom, String date_n, String adresse, String grade, int equi) throws ClassNotFoundException, SQLException {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_n = date_n;
        this.adresse = adresse;
        this.grade = grade;
        this.equipe=new Equipe();
        this.equipe = this.equipe.getEquipe(equi);
    }
    public Membre(int id, String nom, String prenom, String date_n, String adresse, String grade, Equipe equi) throws ClassNotFoundException, SQLException {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_n = date_n;
        this.adresse = adresse;
        this.grade = grade;
        this.equipe = equi;
    }
    public Membre(String nom, String prenom, String date_n, String adresse, String grade, Equipe equipe) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_n = date_n;
        this.adresse = adresse;
        this.grade = grade;
        this.equipe = equipe;
    }

    public String addMembre() throws ClassNotFoundException, SQLException {
            String add="true";
            conn = connectDb.connecterDB();
             String s1="";
            stmt = conn.createStatement();
            Membre m=new Membre(this.nom,this.prenom,this.date_n,this.adresse,this.grade,this.equipe);
            if(m.getMembreId(m)==0){
               s1 = "insert into membre(nom,prenom,date_n,adresse,grade,idequipe) values('" + this.nom + "','" + this.prenom + "','" + this.date_n + "','" + this.adresse+ "','" + this.grade+ "'," + this.equipe.getId()+ ")";
                
                stmt.executeUpdate(s1);
            }else {
                add="Ce compte existe déja";
            }
            return add;
            
       
    }

    public void deleteMembre(int i) throws ClassNotFoundException, SQLException {

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s2 = "delete  from Membre where idmembre=" + i;
        stmt.executeUpdate(s2);

    }

    public void editMembre() throws ClassNotFoundException, ClassNotFoundException, SQLException {
     
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "update Membre set nom='" + this.nom + "' ,prenom='" + this.prenom + "' ,date_n='" + this.date_n + "' ,adresse='" + this.adresse+ "' ,grade='" + this.grade+"', idequipe='"+this.equipe.getId()
                    + "'where idmembre=" + this.id;
            stmt.executeUpdate(s);
        
    }

    public ArrayList<Membre> getMembres() throws SQLException, ClassNotFoundException {
        ArrayList<Membre> x = new ArrayList<>();
    
            
            Equipe eq;
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from membre";
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                eq = new Equipe();
                x.add(new Membre(rs.getInt("idmembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("date_n"), rs.getString("adresse"),rs.getString("grade"),rs.getInt("idequipe")));
            }
            
            
        
        return x;
    }
    
    public Membre getMembre(int i) throws ClassNotFoundException, SQLException {
        Membre x = new Membre();
      
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from membre where idmembre=" + i;

            rs = stmt.executeQuery(s);

            while (rs.next()) {
                Equipe eq = new Equipe();
                x = new Membre(rs.getInt("idmembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("date_n"), rs.getString("adresse"),rs.getString("grade"),eq.getEquipe(rs.getInt("idequipe")));
            }
        
        return x;
    }
    public ArrayList<Membre> getChefEqMembres(int i) throws ClassNotFoundException, SQLException {
        ArrayList<Membre> x = new ArrayList<>();
      
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from membre where idequipe="+this.equipe.getId()+" and idmembre !="+this.id;

            rs = stmt.executeQuery(s);

            while (rs.next()) {
                Equipe eq = new Equipe();
                x.add(new Membre(rs.getInt("idmembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("date_n"), rs.getString("adresse"),rs.getString("grade"),eq.getEquipe(rs.getInt("idequipe"))));
            }
        
        return x;
    }
    public ArrayList<Membre> search(String search) throws ClassNotFoundException, SQLException {
    ArrayList<Membre> x = new ArrayList<>();
      
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from Membre where nom LIKE '%"+search+"%' or prenom LIKE '%"+search+"%' or date_n LIKE '%"+search+"%' or adresse LIKE '%"+search+"%' or grade LIKE '%"+search+"%'";
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                Equipe eq = new Equipe();
                x.add(new Membre(rs.getInt("idmembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("date_n"), rs.getString("adresse"),rs.getString("grade"),eq.getEquipe(rs.getInt("idequipe"))));
            }
        
        return x;
    }
    public ArrayList<Membre> getChefs() throws ClassNotFoundException, SQLException {
    ArrayList<Membre> x = new ArrayList<>();
      
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from Membre where idmembre in (select chef from equipe)";
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                Equipe eq = new Equipe();
                x.add(new Membre(rs.getInt("idmembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("date_n"), rs.getString("adresse"),rs.getString("grade"),eq.getEquipe(rs.getInt("idequipe"))));
            }
        
        return x;
    }
    public Compte getMembreCompte(int i) throws ClassNotFoundException, SQLException {
        Compte x = new Compte();
      
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from Compte where idmembre=" + i;

            rs = stmt.executeQuery(s);

            while (rs.next()) {

                x = new Compte(rs.getInt("idcompte"), rs.getString("email"), rs.getString("type"));
            }
        
        return x;
    }
     public ArrayList<Membre> getMembresByEquipe(Equipe e) throws ClassNotFoundException, SQLException {
        ArrayList<Membre> x = new ArrayList<>();
        
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from Membre where idequipe="+e.getId();

            rs = stmt.executeQuery(s);

            while (rs.next()) {

               Equipe eq = new Equipe();
                x.add(new Membre(rs.getInt("idmembre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("date_n"), rs.getString("adresse"),rs.getString("grade"),eq.getEquipe(rs.getInt("idequipe"))));
            
            }
        
        return x;
    }   
     public int getMembreId(Membre m) throws ClassNotFoundException, SQLException {
        Membre x = new Membre();
        x=m;
        
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from Membre where nom='" + this.nom + "' and prenom='" + this.prenom + "' and date_n='" + this.date_n + "' and adresse='" + this.adresse + "' and grade='" + this.grade + "' ";

            rs = stmt.executeQuery(s);

            while (rs.next()) {

               x.setId(rs.getInt("idmembre"));
            }
        
        return x.getId();
    }   
    
}
