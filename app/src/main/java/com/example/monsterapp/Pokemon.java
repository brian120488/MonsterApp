package com.example.monsterapp;

public class Pokemon {
    public String[] names;
    public int[] evolutionLevels;
    public int[] pokemonImages;
    public int currentImage = 0;
    public int level = 1;
    public int megaItem;
    public int megaImage;
    public boolean isMax = false;
    public boolean isMega = false;

    public Pokemon(String[] names, int[] evolutionLevels, int[] pokemonImages, int megaItem, int megaImage) {
        this.names = names;
        this.evolutionLevels = evolutionLevels;
        this.pokemonImages = pokemonImages;
        this.megaItem = megaItem;
        this.megaImage = megaImage;
    }

    boolean isEvolving() {
        for(int i = 0; i < evolutionLevels.length; i++) {
            if(level == evolutionLevels[i] && currentImage-1 != i) {
                return true;
            }
        }
        return false;
    }
}
