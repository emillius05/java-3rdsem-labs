package org.example.text.composite;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    List<TextComponent> getChildren();
    ComponentType getType();
    String buildText();
}
