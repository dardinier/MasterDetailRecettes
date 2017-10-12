package vue.cell;

import javafx.scene.control.ListCell;
import vue.modele.RecetteVM;

public class RecetteCell extends ListCell<RecetteVM> {
    
    @Override
    protected void updateItem(RecetteVM item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty && item != null) {
            textProperty().bind(item.nomRecetteProperty());
        } else {
            textProperty().unbind();
            setGraphic(null);
            setText(null);
        }
    }
}
