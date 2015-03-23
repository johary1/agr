/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agriotes.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jo
 */
@Entity
@Table(name = "seance", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Seance.findAll", query = "SELECT s FROM Seance s"),
    @NamedQuery(name = "Seance.findByIdModule", query = "SELECT s FROM Seance s WHERE s.seancePK.idModule = :idModule"),
    @NamedQuery(name = "Seance.findByIdSession", query = "SELECT s FROM Seance s WHERE s.seancePK.idSession = :idSession"),
    @NamedQuery(name = "Seance.findByIdPersonne", query = "SELECT s FROM Seance s WHERE s.seancePK.idPersonne = :idPersonne"),
    @NamedQuery(name = "Seance.findByDebut", query = "SELECT s FROM Seance s WHERE s.seancePK.debut = :debut"),
    @NamedQuery(name = "Seance.findByFin", query = "SELECT s FROM Seance s WHERE s.seancePK.fin = :fin"),
    @NamedQuery(name = "Seance.findByIdSalle", query = "SELECT s FROM Seance s WHERE s.seancePK.idSalle = :idSalle")})
public class Seance implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeancePK seancePK;
    @Lob
    @Size(max = 65535)
    @Column(name = "contenu")
    private String contenu;
    @JoinColumn(name = "id_session", referencedColumnName = "id_session", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Session session;
    @JoinColumn(name = "id_salle", referencedColumnName = "id_salle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Salle salle;
    @JoinColumn(name = "id_module", referencedColumnName = "id_module", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Module module;
    @JoinColumn(name = "id_personne", referencedColumnName = "id_personne", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Formateur formateur;

    public Seance() {
    }

    public Seance(SeancePK seancePK) {
        this.seancePK = seancePK;
    }

    public Seance(int idModule, int idSession, int idPersonne, Date debut, Date fin, int idSalle) {
        this.seancePK = new SeancePK(idModule, idSession, idPersonne, debut, fin, idSalle);
    }

    public SeancePK getSeancePK() {
        return seancePK;
    }

    public void setSeancePK(SeancePK seancePK) {
        this.seancePK = seancePK;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seancePK != null ? seancePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seance)) {
            return false;
        }
        Seance other = (Seance) object;
        if ((this.seancePK == null && other.seancePK != null) || (this.seancePK != null && !this.seancePK.equals(other.seancePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Seance[ seancePK=" + seancePK + " ]";
    }
    
}
