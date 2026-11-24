package org.example.text.parser;

public final class TextParserChainBuilder {
    private TextParserChainBuilder() {
    }

    public static AbstractTextParser buildChain() {
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();

        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);

        return textParser;
    }
}
