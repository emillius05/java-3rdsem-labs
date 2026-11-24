package org.example.text.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.text.composite.ComponentType;
import org.example.text.composite.TextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TextProcessingService {
    private static final Logger logger = LogManager.getLogger(TextProcessingService.class);
    private static final Set<Character> VOWELS = Set.of(
            'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я',
            'a', 'e', 'i', 'o', 'u', 'y'
    );

    /**
     * Нормализует текст, заменяя табуляции и последовательности пробелов одним пробелом.
     */
    public String normalizeWhitespace(TextComponent textComponent) {
        if (textComponent == null) {
            throw new IllegalArgumentException("Компонент текста не может быть null");
        }
        return textComponent.buildText().replaceAll("[ \\t]+", " ").trim();
    }

    /**
     * Вариант 7: Рассортировать слова текста по возрастанию доли гласных букв
     * (отношение количества гласных к общему количеству букв в слове).
     */
    public List<String> sortWordsByVowelRatio(TextComponent textComponent) {
        logger.info("Выполнение Варианта 7: сортировка слов по возрастанию доли гласных букв");
        if (textComponent == null) {
            throw new IllegalArgumentException("Компонент текста не может быть null");
        }
        List<String> words = new ArrayList<>();
        collectWords(textComponent, words);

        words.sort(Comparator
                .comparingDouble(this::calculateVowelRatio)
                .thenComparing(String.CASE_INSENSITIVE_ORDER));

        logger.debug("Отсортированные слова: {}", words);
        return List.copyOf(words);
    }

    public double calculateVowelRatio(String word) {
        if (word == null || word.isBlank()) {
            return 0.0;
        }
        long letterCount = word.chars()
                .filter(Character::isLetter)
                .count();
        if (letterCount == 0) {
            return 0.0;
        }
        long vowelCount = word.toLowerCase().chars()
                .filter(Character::isLetter)
                .filter(c -> VOWELS.contains((char) c))
                .count();
        return (double) vowelCount / letterCount;
    }

    private void collectWords(TextComponent component, List<String> result) {
        if (component.getType() == ComponentType.WORD) {
            String wordText = component.buildText();
            boolean hasLetters = wordText.chars().anyMatch(Character::isLetter);
            if (hasLetters) {
                result.add(wordText);
            }
            return;
        }
        for (TextComponent child : component.getChildren()) {
            collectWords(child, result);
        }
    }
}
