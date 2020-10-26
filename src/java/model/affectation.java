package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class affectation {

    int idmembreancien;
    int idmaterielancien;

    int idmembre;
    int idmateriel;
    int quantite;
    String dateaffectation;
    String membre;
    String materiel;
    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;
    public affectation(int idmembre, int idmateriel, int quantite, String dateaffectation) {
        this.idmembre = idmembre;
        this.idmateriel = idmateriel;
        this.quantite = quantite;
        this.dateaffectation = dateaffectation;
    }

    public affectation(int idmembre, int idmateriel, String nom_membre, String nom_materiel, int quantite, String dateaffectation) {
        this.idmembre = idmembre;
        this.idmateriel = idmateriel;

        this.membre = nom_membre;
        this.materiel = nom_materiel;
        this.quantite = quantite;
        this.dateaffectation = dateaffectation;
    }

    public affectation(int idmembreancien, int idmaterielancien, int idmembre, int idmateriel, int quantite, String dateaffectation) {
        this.idmembreancien = idmembreancien;
        this.idmaterielancien = idmaterielancien;
        this.idmembre = idmembre;
        this.idmateriel = idmateriel;
        this.quantite = quantite;
        this.dateaffectation = dateaffectation;
    }

    public String getMembre() {
        return membre;
    }

    public String getMateriel() {
        return materiel;
    }

    public int getIdmembre() {

        return idmembre;
    }

    public int getIdmateriel() {
        return idmateriel;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getDateaffectation() {
        return dateaffectation;
    }

    public void setIdmembre(int idmembre) {
        this.idmembre = idmembre;
    }

    public void setIdmateriel(int idmateriel) {
        this.idmateriel = idmateriel;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDateaffectation(String dateaffectation) {
        this.dateaffectation = dateaffectation;
    }

    public int getIdmembreancien() {
        return idmembreancien;
    }

    public int getIdmaterielancien() {
        return idmaterielancien;
    }

    public static List<affectation> getaffectations(int i) throws Exception {

        List<affectation> affectations = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        Statement st1 = null;
        ResultSet myRs = null;
        ResultSet rs1 = null;
        Statement st2 = null;

        ResultSet rs2 = null;

        try {
            myConn = connectDb.connecterDB();

            String sql = "select * from bddlabri.affectation_m  where idmembre="+i;

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                String s = "select nom from membre where idmembre='" + myRs.getInt("idmembre") + "'";
                String sq = "select designation from materiel where idmateriel='" + myRs.getInt("idmateriel") + "'";

                st1 = myConn.createStatement();
                st2 = myConn.createStatement();

                rs1 = st1.executeQuery(s);
                rs2 = st2.executeQuery(sq);
                if (rs1.next() & rs2.next()) {
                    int idmembre = myRs.getInt("idmembre");
                    int idmateriel = myRs.getInt("idmateriel");
                    String nom_membre = rs1.getString("nom");
                    String nom_materiel = rs2.getString("designation");
                    int quantite = myRs.getInt("quantite");
                    String dateaff = myRs.getString("date_aff");

                    affectation affct = new affectation(idmembre, idmateriel, nom_membre, nom_materiel, quantite, dateaff);

                    affectations.add(affct);
                }
            }

            return affectations;
        } finally {
        }
    }

    public static List<affectation> getdecharges() throws Exception {

        List<affectation> decharges = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        Statement st1 = null;
        ResultSet myRs = null;
        ResultSet rs1 = null;
        Statement st2 = null;

        ResultSet rs2 = null;

        try {
            myConn = connectDb.connecterDB();

            String sql = "select * from bddlabri.affectation_m am where exists (select * from bddlabri.equipe e where e.chef=am.idmembre)";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                System.out.println(myRs.getInt("idmembre"));
                String s = "select nom from membre where idmembre='" + myRs.getInt("idmembre") + "'";
                String sq = "select designation from materiel where idmateriel='" + myRs.getInt("idmateriel") + "'";

                st1 = myConn.createStatement();
                st2 = myConn.createStatement();

                rs1 = st1.executeQuery(s);
                rs2 = st2.executeQuery(sq);
                if (rs1.next() & rs2.next()) {
                    int idmembre = myRs.getInt("idmembre");
                    int idmateriel = myRs.getInt("idmateriel");
                    String nom_membre = rs1.getString("nom");
                    String nom_materiel = rs2.getString("designation");
                    int quantite = myRs.getInt("quantite");
                    String dateaff = myRs.getString("date_aff");

                    affectation affct = new affectation(idmembre, idmateriel, nom_membre, nom_materiel, quantite, dateaff);

                    decharges.add(affct);
                }
            }

            return decharges;
        } finally {
        }
    }
   
    public static affectation getaffectation(int idmem, int idmat) throws Exception {

        affectation aff = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = connectDb.connecterDB();
            String sql = "select * from affectation_m  where idmembre=? and idmateriel=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, idmem);
            myStmt.setInt(2, idmat);

            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                int idmembre = myRs.getInt("idmembre");
                int idmateriel = myRs.getInt("idmateriel");
                int quantite = myRs.getInt("quantite");
                String date = myRs.getString("date_aff");

                aff = new affectation(idmembre, idmateriel, quantite, date);
            } else {
                throw new Exception("Could not find affectation");
            }

            return aff;
        } finally {

        }
    }

    public void addDecharge() throws Exception {
  
        Connection myConn = null;
        PreparedStatement myStmt = null;
        PreparedStatement myStmt1 = null;

        try {
            myConn = connectDb.connecterDB();
            
            String sql = "insert into affectation_m "
                    + "(idmembre,idmateriel,quantite , date_aff) "
                    + "values (?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, this.getIdmembre());
            myStmt.setInt(2, this.getIdmateriel());
            myStmt.setInt(3, this.getQuantite());
            myStmt.setString(4, this.getDateaffectation());
            myStmt.execute();
            String s = "update materiel "
                    + " set quantite = quantite-? "
                    + " where idmateriel=? ";
            myStmt1 = myConn.prepareStatement(s);
            myStmt1.setInt(1, this.getQuantite());
            myStmt1.setInt(2, this.getIdmateriel());

            
            myStmt1.execute();
            } finally {

        }
    }
    
    public void addAffectation() throws Exception {

        Connection myConn = null;
        ResultSet myRs = null;

        PreparedStatement myStmt1 = null;
        PreparedStatement myStmt2 = null;
        PreparedStatement myStmt3 = null;

        try {
            myConn = connectDb.connecterDB();

            String sql1 = "insert into affectation_m "
                    + "(idmembre,idmateriel,quantite , date_aff) "
                    + "values (?, ?, ?, ?)";

            myStmt1 = myConn.prepareStatement(sql1);

            myStmt1.setInt(1, this.getIdmembre());
            myStmt1.setInt(2, this.getIdmateriel());
            myStmt1.setInt(3, this.getQuantite());
            myStmt1.setString(4, this.getDateaffectation());
            myStmt1.execute();
            String sql2 = "select * from bddlabri.affectation_m where idmateriel=? and idmembre=(select chef from bddlabri.equipe where idEquipe=(select idEquipe from (bddlabri.affectation_m natural join bddlabri.membre ) where idmembre=? and idmateriel=? ))";
            myStmt2 = myConn.prepareStatement(sql2);

            myStmt2.setInt(1, this.getIdmateriel());
            myStmt2.setInt(2, this.getIdmembre());
            myStmt2.setInt(3, this.getIdmateriel());

            myRs = myStmt2.executeQuery();
            String sql3 = "update bddlabri.affectation_m  set quantite =quantite-? where idmateriel=?  and idmembre=?";

            myStmt3 = myConn.prepareStatement(sql3);

            myStmt3.setInt(1, this.getQuantite());
            myStmt3.setInt(2, this.idmateriel);
            if (myRs.next()) {
                myStmt3.setInt(3, myRs.getInt("idmembre"));
            }

            myStmt3.execute();

        } finally {

        }
    }
   
    public void updateAffectation() throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = connectDb.connecterDB();
            String sql = "update affectation_m "
                    + "set idmembre=?, idmateriel=?, quantite=?, date_aff=? "
                    + "where idmembre=? and idmateriel=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, this.getIdmembre());
            myStmt.setInt(2, this.getIdmateriel());
            myStmt.setInt(3, this.getQuantite());
            myStmt.setString(4, this.getDateaffectation());
            myStmt.setInt(5, this.getIdmembreancien());
            myStmt.setInt(6, this.getIdmaterielancien());

            myStmt.execute();
        } finally {

        }
    }

    public void deletedecharge() throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        PreparedStatement myStmt1 = null;

        try {

            myConn = connectDb.connecterDB();
            String sql = "delete from affectation_m where idmateriel=? and idmembre=? ";

            myStmt = myConn.prepareStatement(sql);
            String s = "update materiel "
                    + "set quantite=quantite+? "
                    + "where idmateriel=? ";

            myStmt1 = myConn.prepareStatement(s);

            myStmt.setInt(1, this.idmateriel);
            myStmt.setInt(2, this.idmembre);

            myStmt1.setInt(1, this.getQuantite());
            myStmt1.setInt(2, this.getIdmateriel());

            myStmt.execute();
            myStmt1.execute();
        } finally {

        }
    }
   

    public void deleteaffectation() throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt1 = null;
        PreparedStatement myStmt2 = null;
        PreparedStatement myStmt3 = null;
        ResultSet myRs = null;

        try {

            myConn = connectDb.connecterDB();

            String sql1 = "delete from affectation_m where idmateriel=? and idmembre=? ";

            myStmt1 = myConn.prepareStatement(sql1);

            myStmt1.setInt(1, this.idmateriel);
            myStmt1.setInt(2, this.idmembre);

            String sql2 = "select * from bddlabri.affectation_m where idmateriel=? and idmembre=(select chef from bddlabri.equipe where idEquipe=(select idEquipe from (bddlabri.affectation_m natural join bddlabri.membre ) where idmembre=? and idmateriel=? ))";
            myStmt2 = myConn.prepareStatement(sql2);

            myStmt2.setInt(1, this.getIdmateriel());
            myStmt2.setInt(2, this.getIdmembre());
            myStmt2.setInt(3, this.getIdmateriel());

            myRs = myStmt2.executeQuery();
            String sql3 = "update bddlabri.affectation_m  set quantite =quantite+? where idmateriel=? and idmembre=?";

            myStmt3 = myConn.prepareStatement(sql3);

            myStmt3.setInt(1, this.getQuantite());
            myStmt3.setInt(2, this.idmateriel);
            if (myRs.next()) {
                myStmt3.setInt(3, myRs.getInt("idmembre"));
            }

            myStmt1.execute();
            myStmt3.execute();
        } finally {

        }
    }
     public static ArrayList<Materiel> getchefMateriels(int i) throws ClassNotFoundException, SQLException {
        ArrayList<Materiel> x = new ArrayList<>();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from bddlabri.affectation_m where idmembre="+i;
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            Materiel me = new Materiel();
            me=me.getMateriel(rs.getInt("idmateriel"));
            x.add(me);
        }

        return x;
    }

}
