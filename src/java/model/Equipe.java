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
 * @author bekal
 */
public class Equipe {

    public static Connection conn;

    public Equipe(int id, String nom, String domaine, int chef) {
        this.id = id;
        this.nom = nom;
        this.domaine = domaine;
        this.chef = chef;
    }
    public static Statement stmt;
    public static ResultSet rs;
    private Membre mem;

    public void setMem(Membre mem) {
        this.mem = mem;
    }

    public Membre getMem() {
        return mem;
    }
    private int id;
    private String nom;
    private String domaine;
    private int chef;

    public Equipe(String no, String dom, int che){
        this.nom = no;
        this.domaine = dom;

        this.chef = che;

    }
    public Equipe(int i,String no, String dom, Membre che){
        this.id=i;
        this.nom = no;
        this.domaine = dom;

        this.mem = che;

    }
    
 

    public Equipe(String no, String dom) {
        this.nom = no;
        this.domaine = dom;
    }

    public Equipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String no) {
        this.nom = no;
    }

    public int getChef() {
        return this.chef;
    }

    public void setChef(int ch) {
        this.chef = ch;
    }

    public String getDomaine() {
        return this.domaine;
    }

    public void setDomaine(String dom) {
        this.domaine = dom;
    }

    public void addEquipe() throws ClassNotFoundException, SQLException {

        conn = connectDb.connecterDB();

        stmt = conn.createStatement();

        String s = "select * from Equipe where nom='" + this.nom + "' and domaine='" + this.domaine + "' and chef=" + this.chef;

        rs = stmt.executeQuery(s);
        if(this.chef!=0){
        if (!rs.next()) {
            String s1 = "insert into Equipe(nom,domaine,chef) values('" + this.nom + "','" + this.domaine + "'," + this.chef + ")";

            stmt.executeUpdate(s1);
        }}else{
            String s1 = "insert into Equipe(nom,domaine) values('" + this.nom + "','" + this.domaine+ "')";

            stmt.executeUpdate(s1);
        }

    }

    public void deleteEquipe(int i) throws ClassNotFoundException, SQLException {

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s2 = "delete  from Equipe where idequipe=" + i;
        stmt.executeUpdate(s2);

    }

    public void editEquipe() throws ClassNotFoundException, ClassNotFoundException, SQLException {

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "update Equipe set nom='" + this.nom + "' ,domaine='" + this.domaine + "' ,chef=" + this.chef + " where idequipe=" + this.id;
        stmt.executeUpdate(s);

    }

    public ArrayList<Equipe> getEquipes() throws ClassNotFoundException, SQLException {
        ArrayList<Equipe> x = new ArrayList<>();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from Equipe";
       Membre m = new Membre();
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            
           
            boolean add = x.add(new Equipe(rs.getInt("idequipe"), rs.getString("nom"), rs.getString("domaine"),rs.getInt("chef")));
   
        }

        return x;
    }
   
    public Equipe getEquipe(int i) throws ClassNotFoundException, SQLException {
        Equipe x = new Equipe();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from Equipe where idequipe=" + i;

        rs = stmt.executeQuery(s);

        while (rs.next()) {
            Membre m = new Membre();
            x = new Equipe(rs.getInt("idequipe"), rs.getString("nom"), rs.getString("domaine"), rs.getInt("chef"));
        }

        return x;
    }
        
    public ArrayList<Equipe> search(String search) throws ClassNotFoundException, SQLException {
        ArrayList<Equipe> x = new ArrayList<>();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from Equipe where nom LIKE '%" + search + "%' or domaine LIKE '%" + search + "%'";
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            Equipe me = new Equipe();
            Membre m = new Membre();
            x.add(new Equipe(rs.getInt("idequipe"), rs.getString("nom"), rs.getString("domaine"), m.getMembre(rs.getInt("chef"))));
        }

        return x;
    }

    
    
}
