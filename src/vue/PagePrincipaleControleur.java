package vue;

import vue.cell.RecetteCell;
import fabrique.LivreRecetteVMFabrique;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import vue.modele.LivreRecetteVM;
import vue.modele.RecetteVM;

public class PagePrincipaleControleur {
    
    LivreRecetteVM livreRecetteVM;
    
    @FXML private TextField nomLivreTextField;
    @FXML private ListView<RecetteVM> recettesListView;
    @FXML private TextField nomRecetteTextField;
    @FXML private TextArea procedureTextArea;
    
    @FXML private TextField nouveauNomRecetteTextField;
    
    @FXML
    private void initialize() {
        livreRecetteVM = LivreRecetteVMFabrique.creerLivreRecetteVM("Mon premier livre");
        nomLivreTextField.textProperty().bindBidirectional(livreRecetteVM.nomLivreProperty());
        recettesListView.itemsProperty().bind(livreRecetteVM.recettesProperty());
        recettesListView.getSelectionModel().selectedItemProperty().addListener((o, ancienRecette, nouveauRecette) -> {
            unbindRecette(ancienRecette);
            bindRecette(nouveauRecette);
        });
        
        recettesListView.setCellFactory(new Callback<ListView<RecetteVM>, ListCell<RecetteVM>>() {
            @Override
            public ListCell<RecetteVM> call(ListView<RecetteVM> param) {
                return new RecetteCell();
            }
        });
    }
    
    private void bindRecette(RecetteVM nouveauRecette) {
        if (nouveauRecette != null) {
            nomRecetteTextField.textProperty().bindBidirectional(nouveauRecette.nomRecetteProperty());
            procedureTextArea.textProperty().bindBidirectional(nouveauRecette.procedureProperty());
        }
    }
    
    private void unbindRecette(RecetteVM ancienRecette) {
        if (ancienRecette != null) {
            nomRecetteTextField.textProperty().unbindBidirectional(ancienRecette.nomRecetteProperty());
            procedureTextArea.textProperty().unbindBidirectional(ancienRecette.procedureProperty());
        }
    }
    
    @FXML
    private void clicAjouterRecette() {
        livreRecetteVM.ajouterRecette(nouveauNomRecetteTextField.getText());
        nouveauNomRecetteTextField.setText("");
    }
    
    @FXML
    private void clicSupprimerRecette() {
        livreRecetteVM.supprimerRecette(recettesListView.getSelectionModel().getSelectedIndex());
        nomRecetteTextField.setText("");
        procedureTextArea.setText("");
    }
    
    @FXML
    private void clicSauvegarder() {
        try {
            livreRecetteVM.sauvegarder("sauvegarde.bin");
        } catch (IOException ex) {
            Logger.getLogger(PagePrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void clicCharger() throws ClassNotFoundException, IOException {
        try {
            livreRecetteVM.charger("sauvegarde.bin");
        } catch (IOException ex) {
            Logger.getLogger(PagePrincipaleControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
