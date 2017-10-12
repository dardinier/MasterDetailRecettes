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
    
    /**
     * Get the value of nomRecette
     *
     * @return the value of nomRecette
     */
    public String getNomRecette() {
        return nomRecette;
    }

    /**
     * Set the value of nomRecette
     *
     * @param nomRecette new value of nomRecette
     */
    public void setNomRecette(String nomRecette) {
        String oldNomRecette = this.nomRecette;
        this.nomRecette = nomRecette;
        propertyChangeSupport.firePropertyChange(PROP_NOMRECETTE, oldNomRecette, nomRecette);
    }

    /**
     * Get the value of procedure
     *
     * @return the value of procedure
     */
    public String getProcedure() {
        return procedure;
    }

    /**
     * Set the value of procedure
     *
     * @param procedure new value of procedure
     */
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
    
    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
