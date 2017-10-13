package vue.modele;

import fabrique.RecetteFabrique;
import io.Chargeur;
import io.Sauvegardeur;
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.LivreRecette;
import modele.Recette;

public class LivreRecetteVM implements PropertyChangeListener {

    private final StringProperty nomLivre = new SimpleStringProperty();
        public String getNomLivre() {return nomLivre.get();}
        public void setNomLivre(String value) {nomLivre.set(value);}
        public StringProperty nomLivreProperty() {return nomLivre;}
    
    private ObservableList<RecetteVM> recettesObs = FXCollections.observableArrayList();
    private final ListProperty<RecetteVM> recettes = new SimpleListProperty<>(recettesObs);
        public ObservableList getRecettes() {return recettes.get();}
        public void setRecettes(ObservableList value) {recettes.set(value);}
        public ListProperty recettesProperty() {return recettes;}
    
    private LivreRecette modele;

    public LivreRecetteVM(LivreRecette livreRecette) {
        initVM(livreRecette);
    }
    
    private void initVM(LivreRecette livreRecette) {
        modele = livreRecette;
        modele.addPropertyChangeListener(this);
        nomLivre.set(modele.getNomLivre());
        nomLivre.addListener((o, ancienNomLivre, nouveauNomLivre) -> {
            modele.setNomLivre(nouveauNomLivre);
        });
        recettesObs.clear();
        modele.getRecettes().forEach((recette) -> {
            recettesObs.add(new RecetteVM(recette));
        });
    }
    
    public void ajouterRecette(String nomRecette) {
        modele.ajouterRecette(RecetteFabrique.creerRecette(nomRecette, ""));
    }
    
    public void supprimerRecette(int index) {
        modele.supprimerRecette(index);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case LivreRecette.PROP_NOMLIVRE :
                nomLivre.set((String) evt.getNewValue());
                break;
            case LivreRecette.PROP_RECETTES_AJOUT :
                Recette recette = (Recette) evt.getNewValue();
                recettesObs.add(((IndexedPropertyChangeEvent)evt).getIndex(), new RecetteVM(recette));
                break;
            case LivreRecette.PROP_RECETTES_SUPPRESSION :
                recettesObs.remove(((IndexedPropertyChangeEvent)evt).getIndex());
                break;
        }
    }

    public void sauvegarder(String nomFichier) throws IOException {
        Sauvegardeur sauvegardeur = new Sauvegardeur();
        sauvegardeur.sauver(modele, nomFichier);
    }

    public void charger(String nomFichier) throws ClassNotFoundException, IOException {
        Chargeur chargeur = new Chargeur();
        initVM((LivreRecette) chargeur.charger(nomFichier));
    }
}
