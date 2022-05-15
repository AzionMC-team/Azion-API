package fr.sothis.api.listeners;

import fr.sothis.api.pojo.Report;

public interface ReportListener extends AzionListener {

    void onCreateReport(Report report);
}
