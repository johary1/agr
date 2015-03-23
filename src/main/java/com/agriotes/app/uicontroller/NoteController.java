package com.agriotes.app.uicontroller;

import com.agriotes.app.model.Note;
import com.agriotes.app.uicontroller.util.JsfUtil;
import com.agriotes.app.uicontroller.util.JsfUtil.PersistAction;
import com.agriotes.app.dao.NoteFacade;

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

@ManagedBean(name = "noteController")
@SessionScoped
public class NoteController implements Serializable {

    @EJB
    private com.agriotes.app.dao.NoteFacade ejbFacade;
    private List<Note> items = null;
    private Note selected;

    public NoteController() {
    }

    public Note getSelected() {
        return selected;
    }

    public void setSelected(Note selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getNotePK().setIdEvaluation(selected.getEvaluation().getIdEvaluation());
        selected.getNotePK().setIdPersonne(selected.getPersonne().getIdPersonne());
    }

    protected void initializeEmbeddableKey() {
        selected.setNotePK(new com.agriotes.app.model.NotePK());
    }

    private NoteFacade getFacade() {
        return ejbFacade;
    }

    public Note prepareCreate() {
        selected = new Note();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NoteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NoteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NoteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Note> getItems() {
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

    public List<Note> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Note> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Note.class)
    public static class NoteControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NoteController controller = (NoteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "noteController");
            return controller.getFacade().find(getKey(value));
        }

        com.agriotes.app.model.NotePK getKey(String value) {
            com.agriotes.app.model.NotePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.agriotes.app.model.NotePK();
            key.setIdEvaluation(Integer.parseInt(values[0]));
            key.setIdPersonne(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.agriotes.app.model.NotePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdEvaluation());
            sb.append(SEPARATOR);
            sb.append(value.getIdPersonne());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Note) {
                Note o = (Note) object;
                return getStringKey(o.getNotePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Note.class.getName()});
                return null;
            }
        }

    }

}
