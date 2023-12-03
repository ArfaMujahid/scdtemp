//package Model;
//import com.swabunga.spell.engine.SpellDictionary;
//import com.swabunga.spell.engine.SpellDictionaryHashMap;
//import com.swabunga.spell.engine.Word;
//import com.swabunga.spell.event.*;
//
//import javax.swing.*;
//import javax.swing.text.JTextComponent;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Iterator;
//
//public class SpellCheckerWrapper {
//
//    private SpellChecker spellChecker;
//
//    public SpellCheckerWrapper() {
//        initializeSpellChecker();
//    }
//
//    private void initializeSpellChecker() {
//        try {
//            InputStream dictionary = new FileInputStream("path/to/dictionary/english.0");
//            SpellDictionary spellDictionary = new SpellDictionaryHashMap(dictionary);
//            spellChecker = new SpellChecker(spellDictionary);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void checkSpelling(JTextComponent textComponent) {
//        WordTokenizer tokenizer = new StringWordTokenizer(textComponent.getText());
//        Iterator<Word> iterator = spellChecker.checkSpelling(tokenizer);
//        highlightMisspelledWords(iterator, textComponent);
//    }
//
//    private void highlightMisspelledWords(Iterator<Word> iterator, JTextComponent textComponent) {
//        while (iterator.hasNext()) {
//            Word word = iterator.next();
//            int start = word.getPosition();
//            int end = start + word.getWord().length();
//
//            // Highlight misspelled word (customize this part according to your UI)
//            textComponent.getHighlighter().addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
//        }
//    }
//
//    private static class SpellCheckIterator implements Iterator<String> {
//
//        private String text;
//        private int index;
//
//        public SpellCheckIterator(String text) {
//            this.text = text;
//            this.index = 0;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return index < text.length();
//        }
//
//        @Override
//        public String next() {
//            // Return each word for spell checking
//            int start = index;
//            while (index < text.length() && Character.isLetter(text.charAt(index))) {
//                index++;
//            }
//            return text.substring(start, index);
//        }
//
//        @Override
//        public void remove() {
//            throw new UnsupportedOperationException("Remove operation not supported");
//        }
//    }
//}
