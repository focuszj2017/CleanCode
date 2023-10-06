package chapter17.refactored;

import java.util.List;
import added.LineItem;

public class HourlyReportFormatter {
    private final int PAGE_SIZE = 55;

    public void format(List<LineItem> page) {
        // do nothing, just make it build
    }

    public int getMaxPageSize() {
        return PAGE_SIZE;
    }
}
