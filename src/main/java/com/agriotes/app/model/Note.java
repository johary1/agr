/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agriotes.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jo
 */
@Entity
@Table(name = "note", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findByIdEvaluation", query = "SELECT n FROM Note n WHERE n.notePK.idEvaluation = :idEvaluation"),
    @NamedQuery(name = "Note.findByIdPersonne", query = "SELECT n FROM Note n WHERE n.notePK.idPersonne = :idPersonne"),
    @NamedQuery(name = "Note.findByNote", query = "SELECT n FROM Note n WHERE n.note = :note")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotePK notePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "note")
    private BigDecimal note;
    @JoinColumn(name = "id_personne", referencedColumnName = "id_personne", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personne personne;
    @JoinColumn(name = "id_evaluation", referencedColumnName = "id_evaluation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evaluation evaluation;

    public Note() {
    }

    public Note(NotePK notePK) {
        this.notePK = notePK;
    }

    public Note(NotePK notePK, BigDecimal note) {
        this.notePK = notePK;
        this.note = note;
    }

    public Note(int idEvaluation, int idPersonne) {
        this.notePK = new NotePK(idEvaluation, idPersonne);
    }

    public NotePK getNotePK() {
        return notePK;
    }

    public void setNotePK(NotePK notePK) {
        this.notePK = notePK;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notePK != null ? notePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.notePK == null && other.notePK != null) || (this.notePK != null && !this.notePK.equals(other.notePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Note[ notePK=" + notePK + " ]";
    }
    
}
