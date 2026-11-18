package org.example.europe.action;

import org.example.europe.entity.Europe;

@FunctionalInterface
public interface HistoryFilter {
    boolean test(Europe.TerritorialChange change);
}
