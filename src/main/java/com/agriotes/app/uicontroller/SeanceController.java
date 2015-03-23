package com.agriotes.app.uicontroller;

import com.agriotes.app.model.Seance;
import com.agriotes.app.uicontroller.util.JsfUtil;
import com.agriotes.app.uicontroller.util.JsfUtil.PersistAction;
import com.agriotes.app.dao.SeanceFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "seanceController")
@SessionScoped
public class SeanceController implements Serializable {

    @EJB
    private com.agriotes.app.dao.SeanceFacade ejbFacade;
    private List<Seance> items = null;
    private Seance selected;

    public SeanceController() {
    }

    public Seance getSelected() {
        return selected;
    }

    public void setSelected(Seance selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getSeancePK().setIdSession(selected.getSession().getIdSession());
        selected.getSeancePK().setIdSalle(selected.getSalle().getIdSalle());
        selected.getSeancePK().setIdModule(selected.getModule().getIdModule());
        selected.getSeancePK().setIdPersonne(selected.getFormateur().getIdPersonne());
    }

    protected void initializeEmbeddableKey() {
        selected.setSeancePK(new com.agriotes.app.model.SeancePK());
    }

    private SeanceFacade getFacade() {
        return ejbFacade;
    }

    public Seance prepareCreate() {
        selected = new Seance();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SeanceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SeanceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SeanceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Seance> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Seance> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Seance> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Seance.class)
    public static class SeanceControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SeanceController controller = (SeanceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "seanceController");
            return controller.getFacade().find(getKey(value));
        }

        com.agriotes.app.model.SeancePK getKey(String value) {
            com.agriotes.app.model.SeancePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.agriotes.app.model.SeancePK();
            key.setIdModule(Integer.parseInt(values[0]));
            key.setIdSession(Integer.parseInt(values[1]));
            key.setIdPersonne(Integer.parseInt(values[2]));
            key.setDebut(java.sql.Date.valueOf(values[3]));
            key.setFin(java.sql.Date.valueOf(values[4]));
            key.setIdSalle(Integer.parseInt(values[5]));
            return key;
        }

        String getStringKey(com.agriotes.app.model.SeancePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdModule());
            sb.append(SEPARATOR);
            sb.append(value.getIdSession());
            sb.append(SEPARATOR);
            sb.append(value.getIdPersonne());
            sb.append(SEPARATOR);
            sb.append(value.getDebut());
            sb.append(SEPARATOR);
            sb.append(value.getFin());
            sb.append(SEPARATOR);
            sb.append(value.getIdSalle());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Seance) {
                Seance o = (Seance) object;
                return getStringKey(o.getSeancePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Seance.class.getName()});
                return null;
            }
        }

    }

}
