/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agriotes.app.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jo
 */
@Embeddable
public class NotePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_evaluation")
    private int idEvaluation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_personne")
    private int idPersonne;

    public NotePK() {
    }

    public NotePK(int idEvaluation, int idPersonne) {
        this.idEvaluation = idEvaluation;
        this.idPersonne = idPersonne;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEvaluation;
        hash += (int) idPersonne;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotePK)) {
            return false;
        }
        NotePK other = (NotePK) object;
        if (this.idEvaluation != other.idEvaluation) {
            return false;
        }
        if (this.idPersonne != other.idPersonne) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.NotePK[ idEvaluation=" + idEvaluation + ", idPersonne=" + idPersonne + " ]";
    }
    
}
