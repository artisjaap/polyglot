package be.artisjaap.crossword.crosswordengine;

public class WordStats {
    private String word;
    private int numberOfCharacters;
    private Boolean isAlreadyUsed;

    private WordStats(String word, Boolean used){
        this.word = word;
        this.isAlreadyUsed = used;
        this.numberOfCharacters = word.length();
    }
    
    public static WordStats from(String word){
        return new WordStats(word, false);
    }
    
    public WordStats asUsed() {
        this.isAlreadyUsed = true;
        return this;
    }
    
    public WordStats asUnused() {
        this.isAlreadyUsed = false;
        return this;
    }

    public int numberOfCharacters() {
        return numberOfCharacters;
    }

    public Boolean alreadyUsed() {
        return isAlreadyUsed;
    }

    public boolean notAlreadyUsed() {
        return !alreadyUsed();
    }

    public String word() {
        return word;
    }
}
