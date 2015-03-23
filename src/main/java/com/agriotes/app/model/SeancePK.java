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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jo
 */
@Embeddable
public class SeancePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_module")
    private int idModule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_session")
    private int idSession;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_personne")
    private int idPersonne;
    @Basic(optional = false)
    @NotNull
    @Column(name = "debut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date debut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_salle")
    private int idSalle;

    public SeancePK() {
    }

    public SeancePK(int idModule, int idSession, int idPersonne, Date debut, Date fin, int idSalle) {
        this.idModule = idModule;
        this.idSession = idSession;
        this.idPersonne = idPersonne;
        this.debut = debut;
        this.fin = fin;
        this.idSalle = idSalle;
    }

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
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

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idModule;
        hash += (int) idSession;
        hash += (int) idPersonne;
        hash += (debut != null ? debut.hashCode() : 0);
        hash += (fin != null ? fin.hashCode() : 0);
        hash += (int) idSalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeancePK)) {
            return false;
        }
        SeancePK other = (SeancePK) object;
        if (this.idModule != other.idModule) {
            return false;
        }
        if (this.idSession != other.idSession) {
            return false;
        }
        if (this.idPersonne != other.idPersonne) {
            return false;
        }
        if ((this.debut == null && other.debut != null) || (this.debut != null && !this.debut.equals(other.debut))) {
            return false;
        }
        if ((this.fin == null && other.fin != null) || (this.fin != null && !this.fin.equals(other.fin))) {
            return false;
        }
        if (this.idSalle != other.idSalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.SeancePK[ idModule=" + idModule + ", idSession=" + idSession + ", idPersonne=" + idPersonne + ", debut=" + debut + ", fin=" + fin + ", idSalle=" + idSalle + " ]";
    }
    
}
