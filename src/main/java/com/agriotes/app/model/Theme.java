/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agriotes.app.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jo
 */
@Entity
@Table(name = "theme", catalog = "lagarenne2015", schema = "")
@NamedQueries({
    @NamedQuery(name = "Theme.findAll", query = "SELECT t FROM Theme t"),
    @NamedQuery(name = "Theme.findByIdTheme", query = "SELECT t FROM Theme t WHERE t.idTheme = :idTheme"),
    @NamedQuery(name = "Theme.findByLibelle", query = "SELECT t FROM Theme t WHERE t.libelle = :libelle")})
public class Theme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_theme")
    private Integer idTheme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "libelle")
    private String libelle;
    @ManyToMany(mappedBy = "themeList")
    private List<Module> moduleList;

    public Theme() {
    }

    public Theme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public Theme(Integer idTheme, String libelle) {
        this.idTheme = idTheme;
        this.libelle = libelle;
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTheme != null ? idTheme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Theme)) {
            return false;
        }
        Theme other = (Theme) object;
        if ((this.idTheme == null && other.idTheme != null) || (this.idTheme != null && !this.idTheme.equals(other.idTheme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agriotes.app.model.Theme[ idTheme=" + idTheme + " ]";
    }
    
}
