/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agriotes.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "candidature", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Candidature.findAll", query = "SELECT c FROM Candidature c"),
    @NamedQuery(name = "Candidature.findByIdSession", query = "SELECT c FROM Candidature c WHERE c.candidaturePK.idSession = :idSession"),
    @NamedQuery(name = "Candidature.findByIdPersonne", query = "SELECT c FROM Candidature c WHERE c.candidaturePK.idPersonne = :idPersonne"),
    @NamedQuery(name = "Candidature.findByDateEffet", query = "SELECT c FROM Candidature c WHERE c.dateEffet = :dateEffet")})
public class Candidature implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CandidaturePK candidaturePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_effet")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEffet;
    @Lob
    @Size(max = 65535)
    @Column(name = "motivation")
    private String motivation;
    @JoinColumn(name = "id_session", referencedColumnName = "id_session", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Session session;
    @JoinColumn(name = "id_personne", referencedColumnName = "id_personne", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personne personne;
    @JoinColumn(name = "id_etat_candidature", referencedColumnName = "id_etat_candidature")
    @ManyToOne(optional = false)
    private EtatCandidature idEtatCandidature;

    public Candidature() {
    }

    public Candidature(CandidaturePK candidaturePK) {
        this.candidaturePK = candidaturePK;
    }

    public Candidature(CandidaturePK candidaturePK, Date dateEffet) {
        this.candidaturePK = candidaturePK;
        this.dateEffet = dateEffet;
    }

    public Candidature(int idSession, int idPersonne) {
        this.candidaturePK = new CandidaturePK(idSession, idPersonne);
    }

    public CandidaturePK getCandidaturePK() {
        return candidaturePK;
    }

    public void setCandidaturePK(CandidaturePK candidaturePK) {
        this.candidaturePK = candidaturePK;
    }

    public Date getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(Date dateEffet) {
        this.dateEffet = dateEffet;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public EtatCandidature getIdEtatCandidature() {
        return idEtatCandidature;
    }

    public void setIdEtatCandidature(EtatCandidature idEtatCandidature) {
        this.idEtatCandidature = idEtatCandidature;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (candidaturePK != null ? candidaturePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidature)) {
            return false;
        }
        Candidature other = (Candidature) object;
        if ((this.candidaturePK == null && other.candidaturePK != null) || (this.candidaturePK != null && !this.candidaturePK.equals(other.candidaturePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Candidature[ candidaturePK=" + candidaturePK + " ]";
    }
    
}
