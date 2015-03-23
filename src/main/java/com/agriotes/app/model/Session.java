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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "session", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Session.findAll", query = "SELECT s FROM Session s"),
    @NamedQuery(name = "Session.findByIdSession", query = "SELECT s FROM Session s WHERE s.idSession = :idSession"),
    @NamedQuery(name = "Session.findByNom", query = "SELECT s FROM Session s WHERE s.nom = :nom"),
    @NamedQuery(name = "Session.findByDateDebut", query = "SELECT s FROM Session s WHERE s.dateDebut = :dateDebut"),
    @NamedQuery(name = "Session.findByDateFin", query = "SELECT s FROM Session s WHERE s.dateFin = :dateFin"),
    @NamedQuery(name = "Session.findByDateDebutInscription", query = "SELECT s FROM Session s WHERE s.dateDebutInscription = :dateDebutInscription"),
    @NamedQuery(name = "Session.findByDateFinInscription", query = "SELECT s FROM Session s WHERE s.dateFinInscription = :dateFinInscription")})
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_session")
    private Integer idSession;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "date_debut_inscription")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebutInscription;
    @Column(name = "date_fin_inscription")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinInscription;
    @JoinColumn(name = "id_formation", referencedColumnName = "id_formation")
    @ManyToOne(optional = false)
    private Formation idFormation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private List<Seance> seanceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private List<Candidature> candidatureList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSession")
    private List<Evaluation> evaluationList;

    public Session() {
    }

    public Session(Integer idSession) {
        this.idSession = idSession;
    }

    public Session(Integer idSession, String nom, Date dateDebut, Date dateFin) {
        this.idSession = idSession;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutInscription() {
        return dateDebutInscription;
    }

    public void setDateDebutInscription(Date dateDebutInscription) {
        this.dateDebutInscription = dateDebutInscription;
    }

    public Date getDateFinInscription() {
        return dateFinInscription;
    }

    public void setDateFinInscription(Date dateFinInscription) {
        this.dateFinInscription = dateFinInscription;
    }

    public Formation getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Formation idFormation) {
        this.idFormation = idFormation;
    }

    public List<Seance> getSeanceList() {
        return seanceList;
    }

    public void setSeanceList(List<Seance> seanceList) {
        this.seanceList = seanceList;
    }

    public List<Candidature> getCandidatureList() {
        return candidatureList;
    }

    public void setCandidatureList(List<Candidature> candidatureList) {
        this.candidatureList = candidatureList;
    }

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSession != null ? idSession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Session)) {
            return false;
        }
        Session other = (Session) object;
        if ((this.idSession == null && other.idSession != null) || (this.idSession != null && !this.idSession.equals(other.idSession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Session[ idSession=" + idSession + " ]";
    }
    
}
