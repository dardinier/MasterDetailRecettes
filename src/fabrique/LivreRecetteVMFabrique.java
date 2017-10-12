package fabrique;

import vue.modele.LivreRecetteVM;

public class LivreRecetteVMFabrique {
    
    public static LivreRecetteVM creerLivreRecetteVM(String nomLivre) {
        return new LivreRecetteVM(nomLivre);
    }
}
