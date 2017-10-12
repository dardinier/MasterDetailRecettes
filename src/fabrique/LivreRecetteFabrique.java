package fabrique;

import modele.LivreRecette;

public class LivreRecetteFabrique {
    
    public static LivreRecette creerLivreRecette(String nomLivre) {
        return new LivreRecette(nomLivre);
    }
}
