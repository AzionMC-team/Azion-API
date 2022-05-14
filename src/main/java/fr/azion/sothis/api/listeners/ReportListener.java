package fr.azion.sothis.api.listeners;

import fr.azion.sothis.api.pojo.Report;

public interface ReportListener extends AzionListener {

    void onCreateReport(Report report);
}
