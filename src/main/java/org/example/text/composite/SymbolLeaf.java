package org.example.text.composite;

import java.util.List;

public class SymbolLeaf implements TextComponent {
    private final char value;
    private final ComponentType type;

    public SymbolLeaf(char value, ComponentType type) {
        this.value = value;
        this.type = type;
    }

    public char getValue() {
        return value;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Листовой элемент SymbolLeaf не поддерживает добавление компонентов");
    }

    @Override
    public List<TextComponent> getChildren() {
        return List.of();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String buildText() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return buildText();
    }
}
