package org.example.text.parser;

import org.example.text.composite.TextComponent;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser;

    public void setNextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);
}
