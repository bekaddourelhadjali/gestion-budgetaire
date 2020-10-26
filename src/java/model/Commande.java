package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Commande {

    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;
    private int numero;
    private String type;
    private String objet;
    private String date;
    private String datel;
    private float tva;
    private int idatricle;
    private Fournisseur fournisseur = new Fournisseur();

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public Commande() {
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDatel(String datel) {
        this.datel = datel;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public void setIdatricle(int idatricle) {
        this.idatricle = idatricle;
    }

    public int getNumero() {
        return numero;
    }

    public String getType() {
        return type;
    }

    public String getObjet() {
        return objet;
    }

    public String getDate() {
        return date;
    }

    public String getDatel() {
        return datel;
    }

    public float getTva() {
        return tva;
    }

    public int getIdatricle() {
        return idatricle;
    }

    public Commande(int numero, String type, String objet, String date, String datel, float tva, int idatricle, int fournisseur) throws ClassNotFoundException {
        this.numero = numero;
        this.type = type;
        this.objet = objet;
        this.date = date;
        this.datel = datel;
        this.tva = tva;
        this.idatricle = idatricle;

        this.fournisseur = this.fournisseur.getFournisseur(fournisseur);
    }

    public Commande(String type, String objet, String date, String datel, float tva, int idatricle, int fournisseur) throws ClassNotFoundException {
        this.type = type;
        this.objet = objet;
        this.date = date;
        this.datel = datel;
        this.tva = tva;
        this.idatricle = idatricle;

        this.fournisseur = this.fournisseur.getFournisseur(fournisseur);
    }

    public void addCommande() throws ClassNotFoundException, SQLException {

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s1 = "insert into Commande(type,objet,date,tva,idarticle,idfournisseur) values('" + this.type + "','" + this.objet + "','" + this.date + "'," + this.tva + "," + this.idatricle + "," + this.fournisseur.getId() + ")";

        stmt.executeUpdate(s1);

    }

    public void deleteCommande(int i) throws ClassNotFoundException {
        try {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s2 = "delete  from commande where  numero=" + i;
            stmt.executeUpdate(s2);

        } catch (SQLException e) {
        }
    }

    public void editCommande() throws ClassNotFoundException, SQLException {

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "update Commande set objet='" + this.objet + "'  ,date='" + this.date
                + "' ,tva=" + this.tva + ",idarticle=" + this.idatricle + ",idfournisseur=" + this.fournisseur.getId() + " where numero=" + this.numero;
        stmt.executeUpdate(s);

    }

    public ArrayList<Commande> search(String x) throws ClassNotFoundException, SQLException {
        ArrayList<Commande> temp = new ArrayList<>();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from commande where date like '%" + x + "%' or type like '%" + x + "%' or objet like '%" + x + "%'";
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            temp.add(new Commande(rs.getInt("numero"),rs.getString("type"), rs.getString("objet"), rs.getString("date"), rs.getString("datel"), rs.getFloat("tva"), rs.getInt("idarticle"), rs.getInt("idfournisseur")));
        }

        return temp;
    }

    public ArrayList<Commande> getCommandes() throws ClassNotFoundException {
        ArrayList<Commande> x = new ArrayList<>();
        try {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from commande";

            rs = stmt.executeQuery(s);

            while (rs.next()) {

                x.add(new Commande(rs.getInt("numero"), rs.getString("type"), rs.getString("objet"), rs.getString("date"), rs.getString("datel"), rs.getFloat("tva"), rs.getInt("idarticle"), rs.getInt("idfournisseur")));
            }
        } catch (SQLException e) {

        }
        return x;
    }

    public Commande getCommande(int i) throws ClassNotFoundException {
        Commande x = new Commande();
        try {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from Commande where numero=" + i;
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                x = new Commande(i, rs.getString("type"), rs.getString("objet"), rs.getString("date"), rs.getString("datel"), rs.getFloat("tva"), rs.getInt("idarticle"), rs.getInt("idfournisseur"));
            }
        } catch (SQLException e) {
        }
        return x;
    }

    public Commande getCommandeId() throws ClassNotFoundException, SQLException {
        Commande x = new Commande();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from Commande";
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            x = new Commande(rs.getInt("numero"), rs.getString("type"), rs.getString("objet"), rs.getString("date"), rs.getString("datel"), rs.getFloat("tva"), rs.getInt("idarticle"), rs.getInt("idfournisseur"));
        }
        return x;
    }

    public void valider() throws ClassNotFoundException, SQLException {
        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "update Commande set datel='" + this.datel
                + "' where numero=" + this.numero;
        stmt.executeUpdate(s);
    }

    public ArrayList<Produit> getProduits() throws ClassNotFoundException, SQLException {
       ArrayList<Produit> x = new ArrayList<>();
            String idt="id"+this.type;
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from "+this.type +" where numero="+this.numero;

            rs = stmt.executeQuery(s);

            while (rs.next()) {

                x.add(new Produit(rs.getInt(idt), rs.getString("designation"),this, rs.getInt("quantite"), rs.getFloat("prix")));
            }
        return x;
    }
   
}
