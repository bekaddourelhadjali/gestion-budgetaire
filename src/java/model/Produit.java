
package model;

public class Produit {
    protected int id;
    protected String designation;
 
    
    protected Commande commande;
    protected int quantite;
    protected float prix;


    public Produit(int id, String designation, Commande commande, int quantite, float prix) {
        this.id = id;
        this.designation = designation;
        this.commande = commande;
        this.quantite = quantite;
        this.prix = prix;
    }
    
    public Produit(String designation, Commande commande, int quantite, float prix) {
        
        this.designation = designation;
        this.commande = commande;
        this.quantite = quantite;
        this.prix = prix;
        
        
    }

    public Produit() {}
    
    public int getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public Commande getCommande() {
        return commande;
    }

    public int getQuantite() {
        return quantite;
    }

    public float getPrix() {
        return prix;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
}
