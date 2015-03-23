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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "module", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m"),
    @NamedQuery(name = "Module.findByIdModule", query = "SELECT m FROM Module m WHERE m.idModule = :idModule"),
    @NamedQuery(name = "Module.findByNom", query = "SELECT m FROM Module m WHERE m.nom = :nom"),
    @NamedQuery(name = "Module.findByObjectif", query = "SELECT m FROM Module m WHERE m.objectif = :objectif"),
    @NamedQuery(name = "Module.findByContenu", query = "SELECT m FROM Module m WHERE m.contenu = :contenu"),
    @NamedQuery(name = "Module.findByNbHeures", query = "SELECT m FROM Module m WHERE m.nbHeures = :nbHeures"),
    @NamedQuery(name = "Module.findByPrerequis", query = "SELECT m FROM Module m WHERE m.prerequis = :prerequis")})
public class Module implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_module")
    private Integer idModule;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nom")
    private String nom;
    @Size(max = 512)
    @Column(name = "objectif")
    private String objectif;
    @Size(max = 45)
    @Column(name = "contenu")
    private String contenu;
    @Column(name = "nb_heures")
    private Integer nbHeures;
    @Size(max = 512)
    @Column(name = "prerequis")
    private String prerequis;
    @ManyToMany(mappedBy = "moduleList")
    private List<Formation> formationList;
    @JoinTable(name = "module_theme", joinColumns = {
        @JoinColumn(name = "id_module", referencedColumnName = "id_module")}, inverseJoinColumns = {
        @JoinColumn(name = "id_theme", referencedColumnName = "id_theme")})
    @ManyToMany
    private List<Theme> themeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private List<Seance> seanceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModule")
    private List<Evaluation> evaluationList;

    public Module() {
    }

    public Module(Integer idModule) {
        this.idModule = idModule;
    }

    public Module(Integer idModule, String nom) {
        this.idModule = idModule;
        this.nom = nom;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Integer getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(Integer nbHeures) {
        this.nbHeures = nbHeures;
    }

    public String getPrerequis() {
        return prerequis;
    }

    public void setPrerequis(String prerequis) {
        this.prerequis = prerequis;
    }

    public List<Formation> getFormationList() {
        return formationList;
    }

    public void setFormationList(List<Formation> formationList) {
        this.formationList = formationList;
    }

    public List<Theme> getThemeList() {
        return themeList;
    }

    public void setThemeList(List<Theme> themeList) {
        this.themeList = themeList;
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
        hash += (idModule != null ? idModule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.idModule == null && other.idModule != null) || (this.idModule != null && !this.idModule.equals(other.idModule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Module[ idModule=" + idModule + " ]";
    }
    
}
