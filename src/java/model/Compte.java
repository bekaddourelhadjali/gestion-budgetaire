package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Compte {

    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;

    private int id;
    private String email;
    private String password;
    private String type;
    private Membre membre;

    public Compte(int id, String em, String typ) {
        this.id = id;
        this.email = em;
        this.type = typ;

    }

    public Compte(String em, String pa) {
        this.email = em;
        this.password = pa;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Membre getMembre() {
        return membre;
    }

    public Compte(String em, String pass, String typ, int mem) throws ClassNotFoundException, SQLException {
        this.email = em;
        this.password = pass;
        Membre m = new Membre();
        this.membre = m.getMembre(mem);
        this.type = typ;

    }

    public Compte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String em) {
        this.email = em;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String typ) {
        this.type = typ;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public Compte(int id, String em, String pass, String typ, int mem) throws ClassNotFoundException, SQLException {
        this.id = id;
        this.email = em;
        this.password = pass;
        this.type = typ;
        this.membre = new Membre();
        this.membre = this.membre.getMembre(mem);
    }

    public void addCompte() throws ClassNotFoundException {
        try {
            conn = connectDb.connecterDB();

            stmt = conn.createStatement();
            String pass=MD5(this.password);
            String s = "select * from Compte where email='" + this.email + "' and password='" + pass + "' and type='" + this.type + "' and idmembre='" + this.membre.getId() + "' ";

            rs = stmt.executeQuery(s);

            if (!rs.next()) {
                String s1 = "insert into Compte(email,password,type,idmembre) values('" + this.email + "','" + pass + "','" + this.type + "'," + this.membre.getId() + ")";

                stmt.executeUpdate(s1);
            }
        } catch (SQLException e) {

        }
    }

    public void deleteCompte(int i) throws ClassNotFoundException, SQLException {

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s2 = "delete  from Compte where idmembre=" + i;
        stmt.executeUpdate(s2);

    }

    public void editCompte() throws ClassNotFoundException, ClassNotFoundException, SQLException {

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "update Compte set  email='" + this.email + "' ,type='" + this.type + "' where idcompte=" + this.id;
        stmt.executeUpdate(s);

    }
    public void editPass(String pass) throws ClassNotFoundException, ClassNotFoundException, SQLException {
        
        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "update Compte set  password='"+pass+"' where idcompte=" + this.id;
        stmt.executeUpdate(s);

    }

    public ArrayList<Compte> getComptes() throws ClassNotFoundException, SQLException {
        ArrayList<Compte> x = new ArrayList<>();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from Compte";
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            Membre me = new Membre();
            x.add(new Compte(rs.getInt("idcompte"), rs.getString("email"), rs.getString("password"), rs.getString("type"), rs.getInt("idmembre")));
        }

        return x;
    }

    public Compte getCompte(int i) throws ClassNotFoundException, SQLException {
        Compte x = new Compte();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from Compte where idcompte=" + i;

        rs = stmt.executeQuery(s);

        while (rs.next()) {

            x = new Compte(rs.getInt("idcompte"), rs.getString("email"), rs.getString("password"), rs.getString("type"), rs.getInt("idmembre"));
        }

        return x;
    }
    public Compte getCompteByEmail(String i) throws ClassNotFoundException, SQLException {
        Compte x = new Compte();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from Compte where email='"+i+"'";

        rs = stmt.executeQuery(s);

        while (rs.next()) {

            x = new Compte(rs.getInt("idcompte"), rs.getString("email"), rs.getString("password"), rs.getString("type"), rs.getInt("idmembre"));
        }

        return x;
    }

    public Compte log() throws ClassNotFoundException, SQLException {
        Compte x = new Compte();
        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from Compte where email='" + this.email + "' and password='" + this.password + "'";
       
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            
            x = new Compte(rs.getInt("idcompte"), rs.getString("email"), rs.getString("password"), rs.getString("type"), rs.getInt("idmembre"));
        }
       
        
        return x;
    }
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
