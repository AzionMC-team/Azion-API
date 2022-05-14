package fr.azion.sothis.api.listeners;

import fr.azion.sothis.api.pojo.Report;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private List<AzionListener> listeners = new ArrayList<>();

    public void addListener(AzionListener toAdd) {
        listeners.add(toAdd);
    }

    public void deflectReportListener(Report report) {
        listeners.forEach(azionListener -> {
            if(azionListener instanceof ReportListener) {
                ((ReportListener) azionListener).onCreateReport(report);
            }
        });
    }
}
