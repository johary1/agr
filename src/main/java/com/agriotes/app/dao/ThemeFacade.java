/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agriotes.app.dao;

import com.agriotes.app.model.Theme;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jo
 */
@Stateless
public class ThemeFacade extends AbstractFacade<Theme> {
    @PersistenceContext(unitName = "com.agriotes_slammaven2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThemeFacade() {
        super(Theme.class);
    }
    
}
