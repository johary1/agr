/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agriotes.app.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jo
 */
@Entity
@Table(name = "etat_candidature", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "EtatCandidature.findAll", query = "SELECT e FROM EtatCandidature e"),
    @NamedQuery(name = "EtatCandidature.findByIdEtatCandidature", query = "SELECT e FROM EtatCandidature e WHERE e.idEtatCandidature = :idEtatCandidature"),
    @NamedQuery(name = "EtatCandidature.findByLibelle", query = "SELECT e FROM EtatCandidature e WHERE e.libelle = :libelle")})
public class EtatCandidature implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "id_etat_candidature")
    private String idEtatCandidature;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtatCandidature")
    private List<Candidature> candidatureList;

    public EtatCandidature() {
    }

    public EtatCandidature(String idEtatCandidature) {
        this.idEtatCandidature = idEtatCandidature;
    }

    public EtatCandidature(String idEtatCandidature, String libelle) {
        this.idEtatCandidature = idEtatCandidature;
        this.libelle = libelle;
    }

    public String getIdEtatCandidature() {
        return idEtatCandidature;
    }

    public void setIdEtatCandidature(String idEtatCandidature) {
        this.idEtatCandidature = idEtatCandidature;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Candidature> getCandidatureList() {
        return candidatureList;
    }

    public void setCandidatureList(List<Candidature> candidatureList) {
        this.candidatureList = candidatureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtatCandidature != null ? idEtatCandidature.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtatCandidature)) {
            return false;
        }
        EtatCandidature other = (EtatCandidature) object;
        if ((this.idEtatCandidature == null && other.idEtatCandidature != null) || (this.idEtatCandidature != null && !this.idEtatCandidature.equals(other.idEtatCandidature))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.EtatCandidature[ idEtatCandidature=" + idEtatCandidature + " ]";
    }
    
}
