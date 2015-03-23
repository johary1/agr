/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agriotes.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jo
 */
@Entity
@Table(name = "personne", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findByIdPersonne", query = "SELECT p FROM Personne p WHERE p.idPersonne = :idPersonne"),
    @NamedQuery(name = "Personne.findByCivilite", query = "SELECT p FROM Personne p WHERE p.civilite = :civilite"),
    @NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personne.findByAdresse", query = "SELECT p FROM Personne p WHERE p.adresse = :adresse"),
    @NamedQuery(name = "Personne.findByCodePostal", query = "SELECT p FROM Personne p WHERE p.codePostal = :codePostal"),
    @NamedQuery(name = "Personne.findByVille", query = "SELECT p FROM Personne p WHERE p.ville = :ville"),
    @NamedQuery(name = "Personne.findByTelephone", query = "SELECT p FROM Personne p WHERE p.telephone = :telephone"),
    @NamedQuery(name = "Personne.findByTelephone2", query = "SELECT p FROM Personne p WHERE p.telephone2 = :telephone2"),
    @NamedQuery(name = "Personne.findByEmail", query = "SELECT p FROM Personne p WHERE p.email = :email"),
    @NamedQuery(name = "Personne.findByMotPasse", query = "SELECT p FROM Personne p WHERE p.motPasse = :motPasse"),
    @NamedQuery(name = "Personne.findByDateInscription", query = "SELECT p FROM Personne p WHERE p.dateInscription = :dateInscription"),
    @NamedQuery(name = "Personne.findByEstInscrite", query = "SELECT p FROM Personne p WHERE p.estInscrite = :estInscrite")})
public class Personne implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_personne")
    private Integer idPersonne;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "civilite")
    private String civilite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom")
    private String nom;
    @Size(max = 45)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 5)
    @Column(name = "code_postal")
    private String codePostal;
    @Size(max = 30)
    @Column(name = "ville")
    private String ville;
    @Size(max = 15)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 15)
    @Column(name = "telephone2")
    private String telephone2;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "mot_passe")
    private String motPasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_inscription")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInscription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_inscrite")
    private boolean estInscrite;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personne")
    private Formateur formateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personne")
    private List<Candidature> candidatureList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personne")
    private List<Note> noteList;

    public Personne() {
    }

    public Personne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Personne(Integer idPersonne, String civilite, String prenom, String nom, String email, String motPasse, Date dateInscription, boolean estInscrite) {
        this.idPersonne = idPersonne;
        this.civilite = civilite;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.motPasse = motPasse;
        this.dateInscription = dateInscription;
        this.estInscrite = estInscrite;
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public boolean getEstInscrite() {
        return estInscrite;
    }

    public void setEstInscrite(boolean estInscrite) {
        this.estInscrite = estInscrite;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public List<Candidature> getCandidatureList() {
        return candidatureList;
    }

    public void setCandidatureList(List<Candidature> candidatureList) {
        this.candidatureList = candidatureList;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonne != null ? idPersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.idPersonne == null && other.idPersonne != null) || (this.idPersonne != null && !this.idPersonne.equals(other.idPersonne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Personne[ idPersonne=" + idPersonne + " ]";
    }
    
}
