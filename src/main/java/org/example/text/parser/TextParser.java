package org.example.text.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.text.composite.ComponentType;
import org.example.text.composite.TextComponent;
import org.example.text.composite.TextComposite;

public class TextParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger(TextParser.class);
    private static final String PARAGRAPH_SPLIT_REGEX = "\\R+";

    @Override
    public TextComponent parse(String text) {
        logger.info("Разбор текста на абзацы");
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Исходный текст не может быть пустым или null");
        }
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = text.trim().split(PARAGRAPH_SPLIT_REGEX);
        for (String paragraph : paragraphs) {
            String normalized = paragraph.replaceAll("[ \\t]+", " ").trim();
            if (!normalized.isEmpty() && nextParser != null) {
                textComposite.add(nextParser.parse(normalized));
            }
        }
        return textComposite;
    }
}
