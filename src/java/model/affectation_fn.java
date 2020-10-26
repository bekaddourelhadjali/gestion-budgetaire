package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class affectation_fn {

    int idmembreancien;
    int idfournitureancien;

    int idmembre;
    int idfourniture;
    int quantite;
    String dateaffectation;
    String membre;
    String fourniture;

    public void setIdmembreancien(int idmembreancien) {
        this.idmembreancien = idmembreancien;
    }

    public void setIdfournitureancien(int idfournitureancien) {
        this.idfournitureancien = idfournitureancien;
    }

    public void setMembre(String membre) {
        this.membre = membre;
    }

    public void setFourniture(String materiel) {
        this.fourniture = materiel;
    }
    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;

    public affectation_fn(int idmembre, int idfourniture, int quantite, String dateaffectation) {
        this.idmembre = idmembre;
        this.idfourniture = idfourniture;
        this.quantite = quantite;
        this.dateaffectation = dateaffectation;
    }

    public affectation_fn(int idmembre, int idfourniture, String nom_membre, String nom_materiel, int quantite, String dateaffectation) {
        this.idmembre = idmembre;
        this.idfourniture =idfourniture;
        this.membre = nom_membre;
        this.fourniture = nom_materiel;
        this.quantite = quantite;
        this.dateaffectation = dateaffectation;
    }

    public affectation_fn(int idmembreancien, int idfournitureancien, int idmembre, int idmateriel, int quantite, String dateaffectation) {
        this.idmembreancien = idmembreancien;
        this.idfournitureancien = this.idfournitureancien;
        this.idmembre = idmembre;
        this.idfourniture = idmateriel;
        this.quantite = quantite;
        this.dateaffectation = dateaffectation;
    }

    public String getMembre() {
        return membre;
    }

    public String getFourniture() {
        return fourniture;
    }

    public int getIdmembre() {

        return idmembre;
    }

    public int getIdfourniture() {
        return idfourniture;
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

    public void setIdfourniture(int idmateriel) {
        this.idfourniture = idmateriel;
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

    public int getIdfournitureancien() {
        return idfournitureancien;
    }

    public static List<affectation_fn> getaffectations(int i) throws Exception {

        List<affectation_fn> affectations = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        Statement st1 = null;
        ResultSet myRs = null;
        ResultSet rs1 = null;
        Statement st2 = null;

        ResultSet rs2 = null;

        try {
            myConn = connectDb.connecterDB();

            String sql = "select * from bddlabri.affectation_f  where idmembre="+i;

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                String s = "select nom from membre where idmembre='" + myRs.getInt("idmembre") + "'";
                String sq = "select designation from fourniture where idfourniture='" + myRs.getInt("idfourniture") + "'";

                st1 = myConn.createStatement();
                st2 = myConn.createStatement();

                rs1 = st1.executeQuery(s);
                rs2 = st2.executeQuery(sq);
                if (rs1.next() & rs2.next()) {
                    int idmembre = myRs.getInt("idmembre");
                    int idmateriel = myRs.getInt("idfourniture");
                    String nom_membre = rs1.getString("nom");
                    String nom_materiel = rs2.getString("designation");
                    int quantite = myRs.getInt("quantite");
                    String dateaff = myRs.getString("date_aff");

                    affectation_fn affct = new affectation_fn(idmembre, idmateriel, nom_membre, nom_materiel, quantite, dateaff);

                    affectations.add(affct);
                }
            }

            return affectations;
        } finally {
        }
    }

    public static List<affectation_fn> getdecharges() throws Exception {

        List<affectation_fn> decharges = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        Statement st1 = null;
        ResultSet myRs = null;
        ResultSet rs1 = null;
        Statement st2 = null;

        ResultSet rs2 = null;

        try {
            myConn = connectDb.connecterDB();

            String sql = "select * from bddlabri.affectation_f am where exists (select * from bddlabri.equipe e where e.chef=am.idmembre)";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                String s = "select nom from membre where idmembre='" + myRs.getInt("idmembre") + "'";
                String sq = "select designation from fourniture where idfourniture='" + myRs.getInt("idfourniture") + "'";

                st1 = myConn.createStatement();
                st2 = myConn.createStatement();

                rs1 = st1.executeQuery(s);
                rs2 = st2.executeQuery(sq);
                if (rs1.next() & rs2.next()) {
                    int idmembre = myRs.getInt("idmembre");
                    int idfourniture = myRs.getInt("idfourniture");
                    String nom_membre = rs1.getString("nom");
                    String nom_materiel = rs2.getString("designation");
                    int quantite = myRs.getInt("quantite");
                    String dateaff = myRs.getString("date_aff");
                    affectation_fn affct = new affectation_fn(idmembre, idfourniture, nom_membre, nom_materiel, quantite, dateaff);

                    decharges.add(affct);
                }
            }

            return decharges;
        } finally {
        }
    }
    public static affectation_fn getaffectation(int idmem, int idmat) throws Exception {

        affectation_fn aff = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = connectDb.connecterDB();
            String sql = "select * from affectation_f  where idmembre=? and idfourniture=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, idmem);
            myStmt.setInt(2, idmat);

            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                int idmembre = myRs.getInt("idmembre");
                int idfourniture = myRs.getInt("idfourniture");
                int quantite = myRs.getInt("quantite");
                String date = myRs.getString("date_aff");
                aff = new affectation_fn(idmembre, idfourniture, quantite, date);
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
            stmt=myConn.createStatement();
            String sq = "select * from affectation_f where idfourniture="+this.getIdfourniture()+" and idmembre="+this.getIdmembre();
            rs=stmt.executeQuery(sq);
            if(rs.next()){
                this.updateAffectation();
            }else{
            String sql = "insert into affectation_f "
                    + "(idmembre,idfourniture,quantite , date_aff) "
                    + "values (?, ?, ?, ?)";
            String s = "update fourniture "
                    + " set quantite = quantite-? "
                    + " where idfourniture=? ";

            myStmt = myConn.prepareStatement(sql);
            myStmt1 = myConn.prepareStatement(s);
            myStmt.setInt(1, this.getIdmembre());
            myStmt.setInt(2, this.getIdfourniture());
            myStmt.setInt(3, this.getQuantite());
            myStmt.setString(4, this.getDateaffectation());

            myStmt1.setInt(1, this.getQuantite());
            myStmt1.setInt(2, this.getIdfourniture());

            myStmt.execute();
            myStmt1.execute();
            }} finally {

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

            String sql1 = "insert into affectation_f "
                    + "(idmembre,idfourniture,quantite , date_aff) "
                    + "values (?, ?, ?, ?)";

            myStmt1 = myConn.prepareStatement(sql1);

            myStmt1.setInt(1, this.getIdmembre());
            myStmt1.setInt(2, this.getIdfourniture());
            myStmt1.setInt(3, this.getQuantite());
            myStmt1.setString(4, this.getDateaffectation());

            myStmt1.execute();
            String sql2 = "select * from bddlabri.affectation_f where idfourniture=? and idmembre=(select chef from bddlabri.equipe where idEquipe=(select idEquipe from (bddlabri.affectation_f natural join bddlabri.membre ) where idmembre=? and idfourniture=? ))";
            myStmt2 = myConn.prepareStatement(sql2);

            myStmt2.setInt(1, this.getIdfourniture());
            myStmt2.setInt(2, this.getIdmembre());
            myStmt2.setInt(3, this.getIdfourniture());

            myRs = myStmt2.executeQuery();
            String sql3 = "update bddlabri.affectation_f  set quantite =quantite-? where idfourniture=?  and idmembre=?";

            myStmt3 = myConn.prepareStatement(sql3);

            myStmt3.setInt(1, this.getQuantite());
            myStmt3.setInt(2, this.idfourniture);
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
            String sql = "update affectation_f "
                    + "set idmembre=?, idfourniture=?, quantite=?, date_aff=? "
                    + "where idmembre=? and idfourniture=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, this.getIdmembre());
            myStmt.setInt(2, this.getIdfourniture());
            myStmt.setInt(3, this.getQuantite());
            myStmt.setString(4, this.getDateaffectation());
            myStmt.setInt(5, this.getIdmembreancien());
            myStmt.setInt(6, this.getIdfournitureancien());

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
            String sql = "delete from affectation_f where idfourniture=? and idmembre=? ";

            myStmt = myConn.prepareStatement(sql);
            String s = "update fourniture "
                    + "set quantite=quantite+? "
                    + "where idfourniture=? ";

            myStmt1 = myConn.prepareStatement(s);

            myStmt.setInt(1, this.idfourniture);
            myStmt.setInt(2, this.idmembre);

            myStmt1.setInt(1, this.getQuantite());
            myStmt1.setInt(2, this.getIdfourniture());

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

            String sql1 = "delete from affectation_f where idfourniture=? and idmembre=? ";

            myStmt1 = myConn.prepareStatement(sql1);

            myStmt1.setInt(1, this.idfourniture);
            myStmt1.setInt(2, this.idmembre);

            String sql2 = "select * from bddlabri.affectation_f where idfourniture=? and idmembre=(select chef from bddlabri.equipe where idEquipe=(select idEquipe from (bddlabri.affectation_f natural join bddlabri.membre ) where idmembre=? and idfourniture=? ))";
            myStmt2 = myConn.prepareStatement(sql2);

            myStmt2.setInt(1, this.getIdfourniture());
            myStmt2.setInt(2, this.getIdmembre());
            myStmt2.setInt(3, this.getIdfourniture());

            myRs = myStmt2.executeQuery();
            String sql3 = "update bddlabri.affectation_f  set quantite =quantite+? where idfourniture=? and idmembre=?";

            myStmt3 = myConn.prepareStatement(sql3);

            myStmt3.setInt(1, this.getQuantite());
            myStmt3.setInt(2, this.idfourniture);
            if (myRs.next()) {
                myStmt3.setInt(3, myRs.getInt("idmembre"));
            }

            myStmt1.execute();
            myStmt3.execute();
        } finally {

        }
    }

    public static ArrayList<Fourniture> getchefFournitures(int i) throws ClassNotFoundException, SQLException {
        ArrayList<Fourniture> x = new ArrayList<>();

        conn = connectDb.connecterDB();
        stmt = conn.createStatement();
        String s = "select * from bddlabri.affectation_f where idmembre=" + i;
        rs = stmt.executeQuery(s);
        while (rs.next()) {
            Fourniture me = new Fourniture();
            me = me.getFourniture(rs.getInt("idfourniture"));
            x.add(me);
        }

        return x;
    }

}
