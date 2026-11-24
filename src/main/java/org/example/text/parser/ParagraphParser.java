package org.example.text.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.text.composite.ComponentType;
import org.example.text.composite.TextComponent;
import org.example.text.composite.TextComposite;

public class ParagraphParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private static final String SENTENCE_SPLIT_REGEX = "(?<=[.!?])\\s+";

    @Override
    public TextComponent parse(String paragraph) {
        logger.debug("Разбор абзаца на предложения");
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
        String[] sentences = paragraph.trim().split(SENTENCE_SPLIT_REGEX);
        for (String sentence : sentences) {
            if (!sentence.isBlank() && nextParser != null) {
                paragraphComposite.add(nextParser.parse(sentence.trim()));
            }
        }
        return paragraphComposite;
    }
}
