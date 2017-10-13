package modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LivreRecette implements Serializable {
    
    private String nomLivre;
    private List<Recette> recettes;
    
    public static final String PROP_NOMLIVRE = "nomLivre";
    
    public static final String PROP_RECETTES_AJOUT = "recettesAjout";
    public static final String PROP_RECETTES_SUPPRESSION = "recettesSuppression";
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public String getNomLivre() {
        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        String oldNomLivre = this.nomLivre;
        this.nomLivre = nomLivre;
        propertyChangeSupport.firePropertyChange(PROP_NOMLIVRE, oldNomLivre, nomLivre);
    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public LivreRecette(String nomLivre) {
        this.nomLivre = nomLivre;
        recettes = new ArrayList<>();
    }
    
    public void ajouterRecette(Recette recette) {
        propertyChangeSupport.fireIndexedPropertyChange(PROP_RECETTES_AJOUT, recettes.size(), null, recette);
        recettes.add(recette);
    }
    
    public void supprimerRecette(int index) {
        propertyChangeSupport.fireIndexedPropertyChange(PROP_RECETTES_SUPPRESSION, index, recettes.get(index), null);
        recettes.remove(index);
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
