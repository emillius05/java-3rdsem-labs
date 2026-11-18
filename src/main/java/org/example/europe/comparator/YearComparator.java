package org.example.europe.comparator;

import org.example.europe.entity.Europe;
import java.util.Comparator;

public class YearComparator implements Comparator<Europe.TerritorialChange> {
    @Override
    public int compare(Europe.TerritorialChange o1, Europe.TerritorialChange o2) {
        return Integer.compare(o1.getYear(), o2.getYear());
    }
}
