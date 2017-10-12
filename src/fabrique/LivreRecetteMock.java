package fabrique;

import modele.LivreRecette;
import modele.Recette;

public class LivreRecetteMock {
    
    public static void remplirLivreRecette(LivreRecette livreRecette) {
        livreRecette.setNomLivre("Mon premier livre rempli");
        livreRecette.ajouterRecette(new Recette("Ma première recette", "Ma première procédure"));
        livreRecette.ajouterRecette(new Recette("Ma deuxième recette", "Ma deuxième procédure"));
        livreRecette.ajouterRecette(new Recette("Ma troisième recette", "Ma troisième procédure"));
    }
}
