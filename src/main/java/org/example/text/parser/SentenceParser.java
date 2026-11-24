package org.example.text.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.text.composite.ComponentType;
import org.example.text.composite.TextComponent;
import org.example.text.composite.TextComposite;

public class SentenceParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);
    private static final String LEXEME_SPLIT_REGEX = "[ \\t]+";

    @Override
    public TextComponent parse(String sentence) {
        logger.debug("Разбор предложения на лексемы");
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        String[] lexemes = sentence.trim().split(LEXEME_SPLIT_REGEX);
        for (String lexeme : lexemes) {
            if (!lexeme.isBlank() && nextParser != null) {
                sentenceComposite.add(nextParser.parse(lexeme.trim()));
            }
        }
        return sentenceComposite;
    }
}
