package chapter17.refactored;

import added.HourlyEmployee;
import added.LineItem;

import java.util.ArrayList;
import java.util.List;

// G22: Make Logical Dependencies Physical
public class HourlyReporter {
    private HourlyReportFormatter formatter;
    private List<LineItem> page;

    public HourlyReporter(HourlyReportFormatter formatter) {
        this.formatter = formatter;
        page = new ArrayList<LineItem>();
    }

    public void generateReport(List<HourlyEmployee> employees) {
        for (HourlyEmployee e : employees) {
            addLineItemToPage(e);
            if (page.size() == formatter.getMaxPageSize())
                printAndClearItemList();
        }
        if (page.size() > 0) printAndClearItemList();
    }

    private void printAndClearItemList() {
        formatter.format(page);
        page.clear();
    }

    private void addLineItemToPage(HourlyEmployee e) {
        LineItem item = new LineItem();
        item.name = e.getName();
        item.hours = e.getTenthsWorked() / 10;
        item.tenths = e.getTenthsWorked() % 10;
        page.add(item);
    }


}
