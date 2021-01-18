package com.metaxiii.fr.lib;

public enum ConstantDictionnary {
    HELLO("Bonjour"),
    WARNING_SUPPORT("L'application est supportée à l'heure actuelle pour les bases de données MySQL et MariaDB");

    private String sentence;

    ConstantDictionnary(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }
}
