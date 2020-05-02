package be.artisjaap.crossword.crosswordengine;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WordProvider {
    private List<WordStats> allWords;
    
    private WordProvider(List<WordStats> allWords) {
        List<WordStats> sortFromLongToShort = allWords.stream().sorted(Comparator.comparing(WordStats::numberOfCharacters).reversed()).collect(Collectors.toList());
        this.allWords = sortFromLongToShort;
    }
    
    public static WordProvider initWithWords(List<String> words){
        return new WordProvider(words.stream().map(word -> WordStats.from(word)).collect(Collectors.toList()));
    }
    
    public boolean allWordsUsed() {
        return !allWords.stream().anyMatch(WordStats::notAlreadyUsed);
    }
    
    public Optional<String> firstUnuseWord() {
        return allWords.stream().filter(WordStats::notAlreadyUsed).findFirst()
                .map(WordStats::asUsed)
                .map(WordStats::word);
      
    }
    
    private Optional<WordStats> containsWord(String word){
        return allWords.stream().filter(wordStats -> wordStats.word().equals(word)).findFirst();
    }
    
    public void putBackWord(String word) {
        containsWord(word).ifPresent(WordStats::asUnused);
    }


    public Optional<String> firstUnuseWordContaining(List<String> chars) {
        if(!chars.isEmpty() && chars.get(0).length() > 1){
            for (int i = 0; i < chars.size(); i++) {
                String containing = chars.get(i);
                Optional<String> foundString = allWords.stream().filter(WordStats::notAlreadyUsed)
                        .filter(word -> word.word().indexOf(containing) >= 0)
                        .findFirst()
                        .map(WordStats::asUsed)
                        .map(WordStats::word);
                
                if(foundString.isPresent()){
                    return foundString;
                }

            }
        }
        
        return allWords.stream().filter(WordStats::notAlreadyUsed)
                .filter(word -> wordContainsChars(word, chars))
                .findFirst()
                .map(WordStats::asUsed)
                .map(WordStats::word);
    }

    private boolean wordContainsChars(WordStats word, List<String> chars) {
        return chars.stream().anyMatch(c -> word.word().indexOf(c) >= 0);
    }
    
    public boolean allWordsAvailable(){
        return !allWords.stream().anyMatch(wordStats -> wordStats.alreadyUsed());
    }


    public long countWordsUnused() {
        return allWords.stream().map(WordStats::notAlreadyUsed).count();
    }

    public boolean wordAvailableFor(String str) {
        return allWords.stream().filter(WordStats::notAlreadyUsed).filter(word -> word.word().indexOf(str) >= 0).findFirst().isPresent();
    }
}
