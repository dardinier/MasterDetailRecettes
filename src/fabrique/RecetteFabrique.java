package fabrique;

import modele.Recette;

public class RecetteFabrique {
    
    public static Recette creerRecette(String nomRecette, String procedure) {
        return new Recette(nomRecette, procedure);
    }
}
