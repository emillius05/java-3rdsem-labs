package org.example.text.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TextComposite implements TextComponent {
    private final ComponentType type;
    private final List<TextComponent> children = new ArrayList<>();

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        if (component != null) {
            children.add(component);
        }
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String buildText() {
        return switch (type) {
            case TEXT -> children.stream()
                    .map(TextComponent::buildText)
                    .collect(Collectors.joining(System.lineSeparator()));
            case PARAGRAPH, SENTENCE -> children.stream()
                    .map(TextComponent::buildText)
                    .collect(Collectors.joining(" "));
            default -> children.stream()
                    .map(TextComponent::buildText)
                    .collect(Collectors.joining());
        };
    }

    @Override
    public String toString() {
        return buildText();
    }
}
