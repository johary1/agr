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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jo
 */
@Entity
@Table(name = "evaluation", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Evaluation.findAll", query = "SELECT e FROM Evaluation e"),
    @NamedQuery(name = "Evaluation.findByIdEvaluation", query = "SELECT e FROM Evaluation e WHERE e.idEvaluation = :idEvaluation")})
public class Evaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_evaluation")
    private Integer idEvaluation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluation")
    private List<Note> noteList;
    @JoinColumn(name = "id_session", referencedColumnName = "id_session")
    @ManyToOne(optional = false)
    private Session idSession;
    @JoinColumn(name = "id_module", referencedColumnName = "id_module")
    @ManyToOne(optional = false)
    private Module idModule;
    @JoinColumn(name = "id_formateur", referencedColumnName = "id_personne")
    @ManyToOne(optional = false)
    private Formateur idFormateur;

    public Evaluation() {
    }

    public Evaluation(Integer idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Integer getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Integer idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public Session getIdSession() {
        return idSession;
    }

    public void setIdSession(Session idSession) {
        this.idSession = idSession;
    }

    public Module getIdModule() {
        return idModule;
    }

    public void setIdModule(Module idModule) {
        this.idModule = idModule;
    }

    public Formateur getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(Formateur idFormateur) {
        this.idFormateur = idFormateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluation != null ? idEvaluation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluation)) {
            return false;
        }
        Evaluation other = (Evaluation) object;
        if ((this.idEvaluation == null && other.idEvaluation != null) || (this.idEvaluation != null && !this.idEvaluation.equals(other.idEvaluation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Evaluation[ idEvaluation=" + idEvaluation + " ]";
    }
    
}
