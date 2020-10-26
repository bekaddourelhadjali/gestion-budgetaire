package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Fournisseur {

    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;

    private int id;
    private String nom;
    private String type;
    private String adresse;
    private String telephone;
    private String nrc;
    private String agissant;
    private String agrement;
    private String rib;
    private String nif;
    private String nis;
    private String types;

    public Fournisseur(String nom, String type, String adresse, String telephone, String nrc, String agissant, String agrement, String rib, String nif, String nis, String types) {
        this.nom = nom;
        this.type = type;
        this.telephone = telephone;
        this.nrc = nrc;
        this.agissant = agissant;
        this.agrement = agrement;
        this.rib = rib;
        this.nif = nif;
        this.nis = nis;
        this.types = types;
        this.adresse = adresse;

    }

    public Fournisseur() {
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getAgissant() {
        return agissant;
    }

    public void setAgissant(String agissant) {
        this.agissant = agissant;
    }

    public String getAgrement() {
        return agrement;
    }

    public void setAgrement(String agrement) {
        this.agrement = agrement;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Fournisseur(int id, String nom, String type, String adresse, String telephone, String nrc, String agissant, String agrement, String rib, String nif, String nis, String types) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.telephone = telephone;
        this.nrc = nrc;
        this.agissant = agissant;
        this.agrement = agrement;
        this.rib = rib;
        this.nif = nif;
        this.nis = nis;
        this.types = types;
        this.adresse = adresse;

    }

    public void addFournisseur() throws ClassNotFoundException {
        try {
            conn = connectDb.connecterDB();

            stmt = conn.createStatement();

            String s = "select * from fournisseur where nom='" + this.nom + "' and type='" + this.type + "' and adresse='" + this.adresse + "' and telephone='" + this.telephone + "' ";

            rs = stmt.executeQuery(s);

            if (!rs.next()) {
                String s1 = "insert into fournisseur(nom,type,adresse,telephone,nrc,agrement,agissant,rib,nif,nis,types) values('" + this.nom + "','" + this.type + "','" + this.adresse + "','" + this.telephone + "','"
                        + this.nrc + "','" + this.agrement + "','" + this.agissant + "','" + this.rib + "','" + this.nif + "','" + this.nis + "','" + this.types + "')";

                stmt.executeUpdate(s1);
            }
        } catch (SQLException e) {

        }
    }

    public void deleteFournisseur(int i) throws ClassNotFoundException, SQLException {
        
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s2 = "delete  from fournisseur where idfournisseur=" + i;
            stmt.executeUpdate(s2);

        
    }

    public void editFournisseur() throws ClassNotFoundException, ClassNotFoundException {
        try {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "update fournisseur set nom='" + this.nom + "' ,type='" + this.type + "' ,telephone='" + this.telephone + "' ,adresse='" + this.adresse
                    + "' ,nrc='" + this.nrc + "' ,agrement='" + this.agrement + "' ,agissant='" + this.agissant + "' ,rib='" + this.rib
                    + "' ,nif='" + this.nif + "' ,nis='" + this.nis + "' ,types='" + this.types + "' where idfournisseur=" + this.id;
            stmt.executeUpdate(s);
        } catch (SQLException e) {
        }
    }

    public ArrayList<Fournisseur> search(String x) throws ClassNotFoundException, SQLException {
        ArrayList<Fournisseur> temp = new ArrayList<>();
       
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from fournisseur where nom like '%" + x + "%' or type like '%" + x + "%' or telephone like '%" + x + "%' or adresse like '%" + x + "%' or types like '%" + x + "%'";
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                temp.add(new Fournisseur(rs.getInt("idfournisseur"), rs.getString("nom"), rs.getString("type"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("types")));
            }

      
        return temp;
    }

    Fournisseur(int id, String nom, String type, String adresse, String telephone, String types) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.telephone = telephone;
        this.types = types;
    }

    public ArrayList<Fournisseur> getFournisseurs() throws ClassNotFoundException {
        ArrayList<Fournisseur> x = new ArrayList<>();
        try {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from fournisseur";
            rs = stmt.executeQuery(s);
            while (rs.next()) {
                x.add(new Fournisseur(rs.getInt("idfournisseur"), rs.getString("nom"), rs.getString("type"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("types")));
            }
        } catch (SQLException e) {
            
        }
        return x;
    }

    public Fournisseur getFournisseur(int i) throws ClassNotFoundException {
        Fournisseur x = new Fournisseur();
        try {
            conn = connectDb.connecterDB();
            stmt = conn.createStatement();
            String s = "select * from fournisseur where idfournisseur=" + i;
            
            rs = stmt.executeQuery(s);
          
            while (rs.next()) {
                
                x = new Fournisseur(i, rs.getString("nom"), rs.getString("type"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("nrc"), rs.getString("agissant"), rs.getString("agrement"), rs.getString("rib"), rs.getString("nif"), rs.getString("nis"), rs.getString("types"));
            }
        } catch (SQLException e) {
        }
        return x;
    }

    
}
