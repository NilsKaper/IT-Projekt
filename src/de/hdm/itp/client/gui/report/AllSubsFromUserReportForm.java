package de.hdm.itp.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itp.client.ClientsideSettings;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.AllSubsFromUserReport;
import de.hdm.itp.shared.report.HTMLReportWriter;

public class AllSubsFromUserReportForm extends ReportResultPanel {

	private User u;

	public AllSubsFromUserReportForm(User u) {
		this.u = u;
		run();
	}

	protected void run() {

		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();

		reportGenerator.createAllSubsFromUserReport(u, new AsyncCallback<AllSubsFromUserReport>() {

			public void onFailure(Throwable caught) {

				ClientsideSettings.getLogger().severe("Erzeugen des Reports fehlgeschlagen!");
				Window.alert("Fehlgeschlagen");
				Window.alert(caught.getMessage());

			}

			public void onSuccess(AllSubsFromUserReport report) {

				if (report != null) {

					HTMLReportWriter writer = new HTMLReportWriter();
					writer.process(report);
					append(writer.getReportText());

				}
			}

		});
	}
}