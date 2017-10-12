package modele;

import io.Sauvegardable;
import io.Sauvegardeur;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LivreRecette implements Serializable, Sauvegardable {
    
    private String nomLivre;
    private List<Recette> recettes;
    
    public static final String PROP_NOMLIVRE = "nomLivre";
    public static final String PROP_RECETTES = "recettes";
    
    public static final String PROP_RECETTES_AJOUT = "recettesAjout";
    public static final String PROP_RECETTES_SUPPRESSION = "recettesSuppression";
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    /**
     * Get the value of nomLivre
     *
     * @return the value of nomLivre
     */
    public String getNomLivre() {
        return nomLivre;
    }

    /**
     * Set the value of nomLivre
     *
     * @param nomLivre new value of nomLivre
     */
    public void setNomLivre(String nomLivre) {
        String oldNomLivre = this.nomLivre;
        this.nomLivre = nomLivre;
        propertyChangeSupport.firePropertyChange(PROP_NOMLIVRE, oldNomLivre, nomLivre);
    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }

    public LivreRecette(String nomLivre) {
        this.nomLivre = nomLivre;
        recettes = new ArrayList<>();
    }
    
    public void ajouterRecette(Recette recette) {
        recettes.add(recette);
        propertyChangeSupport.fireIndexedPropertyChange(PROP_RECETTES_AJOUT, recettes.indexOf(recette), null, recette);
    }
    
    public void supprimerRecette(int index) {
        propertyChangeSupport.fireIndexedPropertyChange(PROP_RECETTES_SUPPRESSION, index, recettes.get(index), null);
        recettes.remove(index);
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

    @Override
    public void sauvergarder(Serializable objet, String nomFichier) throws IOException {
         Sauvegardeur.sauver(this, nomFichier);
    }
}
