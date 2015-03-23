package com.agriotes.app.uicontroller;

import com.agriotes.app.model.Candidature;
import com.agriotes.app.uicontroller.util.JsfUtil;
import com.agriotes.app.uicontroller.util.JsfUtil.PersistAction;
import com.agriotes.app.dao.CandidatureFacade;

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

@ManagedBean(name = "candidatureController")
@SessionScoped
public class CandidatureController implements Serializable {

    @EJB
    private com.agriotes.app.dao.CandidatureFacade ejbFacade;
    private List<Candidature> items = null;
    private Candidature selected;

    public CandidatureController() {
    }

    public Candidature getSelected() {
        return selected;
    }

    public void setSelected(Candidature selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getCandidaturePK().setIdPersonne(selected.getPersonne().getIdPersonne());
        selected.getCandidaturePK().setIdSession(selected.getSession().getIdSession());
    }

    protected void initializeEmbeddableKey() {
        selected.setCandidaturePK(new com.agriotes.app.model.CandidaturePK());
    }

    private CandidatureFacade getFacade() {
        return ejbFacade;
    }

    public Candidature prepareCreate() {
        selected = new Candidature();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CandidatureCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CandidatureUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CandidatureDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Candidature> getItems() {
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

    public List<Candidature> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Candidature> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Candidature.class)
    public static class CandidatureControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CandidatureController controller = (CandidatureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "candidatureController");
            return controller.getFacade().find(getKey(value));
        }

        com.agriotes.app.model.CandidaturePK getKey(String value) {
            com.agriotes.app.model.CandidaturePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.agriotes.app.model.CandidaturePK();
            key.setIdSession(Integer.parseInt(values[0]));
            key.setIdPersonne(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.agriotes.app.model.CandidaturePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdSession());
            sb.append(SEPARATOR);
            sb.append(value.getIdPersonne());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Candidature) {
                Candidature o = (Candidature) object;
                return getStringKey(o.getCandidaturePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Candidature.class.getName()});
                return null;
            }
        }

    }

}
