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
public class CandidaturePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_session")
    private int idSession;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_personne")
    private int idPersonne;

    public CandidaturePK() {
    }

    public CandidaturePK(int idSession, int idPersonne) {
        this.idSession = idSession;
        this.idPersonne = idPersonne;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
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
        hash += (int) idSession;
        hash += (int) idPersonne;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CandidaturePK)) {
            return false;
        }
        CandidaturePK other = (CandidaturePK) object;
        if (this.idSession != other.idSession) {
            return false;
        }
        if (this.idPersonne != other.idPersonne) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.CandidaturePK[ idSession=" + idSession + ", idPersonne=" + idPersonne + " ]";
    }
    
}
