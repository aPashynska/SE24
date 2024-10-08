package org.hbrs.se1.ws24.exercises.uebung1.control;

public class TranslatorFactory {
    // Hier wird eine Instanz von GermanTranslator erstellt und zurückgegeben.
    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }


}
