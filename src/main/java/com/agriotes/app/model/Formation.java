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
import javax.persistence.Lob;
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
@Table(name = "formation", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Formation.findAll", query = "SELECT f FROM Formation f"),
    @NamedQuery(name = "Formation.findByIdFormation", query = "SELECT f FROM Formation f WHERE f.idFormation = :idFormation"),
    @NamedQuery(name = "Formation.findByNom", query = "SELECT f FROM Formation f WHERE f.nom = :nom")})
public class Formation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formation")
    private Integer idFormation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nom")
    private String nom;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @JoinTable(name = "module_formation", joinColumns = {
        @JoinColumn(name = "id_formation", referencedColumnName = "id_formation")}, inverseJoinColumns = {
        @JoinColumn(name = "id_module", referencedColumnName = "id_module")})
    @ManyToMany
    private List<Module> moduleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormation")
    private List<Session> sessionList;

    public Formation() {
    }

    public Formation(Integer idFormation) {
        this.idFormation = idFormation;
    }

    public Formation(Integer idFormation, String nom) {
        this.idFormation = idFormation;
        this.nom = nom;
    }

    public Integer getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Integer idFormation) {
        this.idFormation = idFormation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormation != null ? idFormation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.idFormation == null && other.idFormation != null) || (this.idFormation != null && !this.idFormation.equals(other.idFormation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Formation[ idFormation=" + idFormation + " ]";
    }
    
}
