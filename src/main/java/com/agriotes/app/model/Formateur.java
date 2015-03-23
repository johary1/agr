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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jo
 */
@Entity
@Table(name = "formateur", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Formateur.findAll", query = "SELECT f FROM Formateur f"),
    @NamedQuery(name = "Formateur.findByIdPersonne", query = "SELECT f FROM Formateur f WHERE f.idPersonne = :idPersonne"),
    @NamedQuery(name = "Formateur.findByDateEntree", query = "SELECT f FROM Formateur f WHERE f.dateEntree = :dateEntree")})
public class Formateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_personne")
    private Integer idPersonne;
    @Column(name = "date_entree")
    @Temporal(TemporalType.DATE)
    private Date dateEntree;
    @JoinColumn(name = "id_personne", referencedColumnName = "id_personne", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Personne personne;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formateur")
    private List<Seance> seanceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormateur")
    private List<Evaluation> evaluationList;

    public Formateur() {
    }

    public Formateur(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Integer getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public List<Seance> getSeanceList() {
        return seanceList;
    }

    public void setSeanceList(List<Seance> seanceList) {
        this.seanceList = seanceList;
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
        hash += (idPersonne != null ? idPersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formateur)) {
            return false;
        }
        Formateur other = (Formateur) object;
        if ((this.idPersonne == null && other.idPersonne != null) || (this.idPersonne != null && !this.idPersonne.equals(other.idPersonne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Formateur[ idPersonne=" + idPersonne + " ]";
    }
    
}
