package org.example.text.parser;

import org.example.text.composite.ComponentType;
import org.example.text.composite.SymbolLeaf;
import org.example.text.composite.TextComponent;
import org.example.text.composite.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("[\\p{L}\\d-]+|[^\\p{L}\\d\\s-]");

    @Override
    public TextComponent parse(String lexeme) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        Matcher matcher = TOKEN_PATTERN.matcher(lexeme);
        while (matcher.find()) {
            String token = matcher.group();
            if (token.matches("[\\p{L}\\d-]+")) {
                TextComposite wordComposite = new TextComposite(ComponentType.WORD);
                for (char ch : token.toCharArray()) {
                    wordComposite.add(new SymbolLeaf(ch, ComponentType.SYMBOL));
                }
                lexemeComposite.add(wordComposite);
            } else {
                for (char ch : token.toCharArray()) {
                    lexemeComposite.add(new SymbolLeaf(ch, ComponentType.PUNCTUATION));
                }
            }
        }
        return lexemeComposite;
    }
}
