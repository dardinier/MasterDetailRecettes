package vue.modele;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import modele.Recette;

public class RecetteVM implements PropertyChangeListener {

    private final StringProperty nomRecette = new SimpleStringProperty();
        public String getNomRecette() {return nomRecette.get();}
        public void setNomRecette(String value) {nomRecette.set(value);}
        public StringProperty nomRecetteProperty() {return nomRecette;}
    
    private final StringProperty procedure = new SimpleStringProperty();
        public String getProcedure() {return procedure.get();}
        public void setProcedure(String value) {procedure.set(value);}
        public StringProperty procedureProperty() {return procedure;}
    
    private Recette modele;

    public RecetteVM(Recette recette) {
        this.nomRecette.set(recette.getNomRecette());
        this.procedure.set(recette.getProcedure());
        modele = recette;
        modele.addPropertyChangeListener(this);
        nomRecetteProperty().addListener((o, ancienNomRecette, nouveauNomRecette) -> {
            modele.setNomRecette(nouveauNomRecette);
        });
        procedureProperty().addListener((o, ancienProcedure, nouveauProcedure) -> {
            modele.setProcedure(nouveauProcedure);
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Recette.PROP_NOMRECETTE :
                nomRecette.set((String) evt.getNewValue());
                break;
            case Recette.PROP_PROCEDURE :
                procedure.set((String) evt.getNewValue());
        }
    }
}
