package io;

import java.io.IOException;
import java.io.Serializable;

public interface Sauvegardable {
    public void sauvergarder(Serializable objet, String nomFichier) throws IOException;
}
