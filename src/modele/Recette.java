package modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class Recette implements Serializable {
    
    private String nomRecette;
    private String procedure;
    
    public static final String PROP_NOMRECETTE = "nomRecette";
    public static final String PROP_PROCEDURE = "procedure";
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public String getNomRecette() {
        return nomRecette;
    }
    
    public void setNomRecette(String nomRecette) {
        String oldNomRecette = this.nomRecette;
        this.nomRecette = nomRecette;
        propertyChangeSupport.firePropertyChange(PROP_NOMRECETTE, oldNomRecette, nomRecette);
    }
    
    public String getProcedure() {
        return procedure;
    }
    
    public void setProcedure(String procedure) {
        String oldProcedure = this.procedure;
        this.procedure = procedure;
        propertyChangeSupport.firePropertyChange(PROP_PROCEDURE, oldProcedure, procedure);
    }

    public Recette(String nomRecette, String procedure) {
        this.nomRecette = nomRecette;
        this.procedure = procedure;
    }
    
    private Object readResolve() throws ObjectStreamException {
        propertyChangeSupport = new PropertyChangeSupport(this);
        return this;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
