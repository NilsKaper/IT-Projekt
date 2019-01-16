package de.hdm.itp.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.itp.client.gui.report.AllCommentsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllCommentsOfAllPostsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllLikesFromUserReportForm;
import de.hdm.itp.client.gui.report.AllPostsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllSubsFromUserReportForm;
import de.hdm.itp.client.gui.report.AllSubsOfUserReportForm;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;

public class NavPanelReport extends VerticalPanel {
	
//	final VerticalPanel resultPanel = new VerticalPanel();
	
	MainPanelReport resultPanel = new MainPanelReport();

	
	private static ReportGeneratorAsync reportGenerator = null;

	
	private User u = new User();
	
	
	
	final Button AllSubsFromUserReportBtn = new Button("All Ihre Abonnements");
	final Button AllSubsOfUserReportBtn = new Button("All Ihre Abonnenten");
	final Button AllCommentsFromUserReportBtn = new Button("All Ihre Kommentare");
	final Button AllLikesFromUserReportBtn = new Button("All Ihre Likes");
	final Button AllPostsFromUserReportBtn = new Button("All Ihre Beiträge");

	final Button AllSubsFromUserBetweenDatesReportBtn = new Button("All Ihre Abonnements in dem Angegebenen Zeitraum");
	final Button AllSubsOfUserBetweenDatesReportBtn = new Button("All Ihre Abonnement in dem Angegebenen Zeitraum");
	final Button AllCommentsFromUserBetweenDatesReportBtn = new Button("All Ihre Kommentare in dem Angegebenen Zeitraum");
	final Button AllLikesFromUserBetweenDatesReportBtn = new Button("All Ihre Likes in dem Angegebenen Zeitraum");
	final Button AllPostsFromUserBetweenDatesReportBtn = new Button("All Ihre Beiträge in dem Angegebenen Zeitraum\"");
	
	final CheckBox checkBoxShowAll = new CheckBox("Suchanfrage Aller");
	final CheckBox checkBoxPickDate = new CheckBox("Suchanfrage nach Zeitraum");
	
	final DateBox dateFrom = new DateBox();
	final DateBox dateTill = new DateBox();
	
	final Label lblFrom = new Label("Von");
	final Label lblTill = new Label("Bis");

	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("NavReport");
		
	//	this.setWidth("300px");
				
		this.add(checkBoxShowAll);
		this.add(checkBoxPickDate);
		
		this.add(lblFrom);
		this.add(dateFrom);
		this.add(lblTill);
		this.add(dateTill);
		
		this.add(AllSubsFromUserReportBtn);
		this.add(AllSubsOfUserReportBtn);
		this.add(AllCommentsFromUserReportBtn);
		this.add(AllLikesFromUserReportBtn);
		this.add(AllPostsFromUserReportBtn);

		this.add(AllSubsFromUserBetweenDatesReportBtn);
		this.add(AllSubsOfUserBetweenDatesReportBtn);
		this.add(AllCommentsFromUserBetweenDatesReportBtn);
		this.add(AllLikesFromUserBetweenDatesReportBtn);
		this.add(AllPostsFromUserBetweenDatesReportBtn);

		
		if (reportGenerator == null) {

			reportGenerator = ClientsideSettings.getReportGenerator();
		}

		
		
		u.setId(10000001);
		
		reportGenerator.setUser(u, new setUserCallback());


		checkBoxShowAll.setValue(true);
		
		//setting date Format
		
		dateFrom.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd.MM.yyyy"))); 
		dateTill.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd.MM.yyyy"))); 


		dateFrom.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
			}
		});


		dateTill.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
			}
		});
		
		lblFrom.setVisible(false);
		lblTill.setVisible(false);
		dateFrom.setVisible(false);
		dateTill.setVisible(false);
		
		dateFrom.setValue(null);
		dateTill.setValue(null);
		
		AllSubsFromUserBetweenDatesReportBtn.setVisible(false);
		AllSubsOfUserBetweenDatesReportBtn.setVisible(false);
		AllCommentsFromUserBetweenDatesReportBtn.setVisible(false);
		AllLikesFromUserBetweenDatesReportBtn.setVisible(false);
		AllPostsFromUserBetweenDatesReportBtn.setVisible(false);

		// checkbox
		
		

		checkBoxShowAll.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				checkBoxShowAll.setValue(true);
				lblFrom.setVisible(false);
				lblTill.setVisible(false);
				dateFrom.setVisible(false);
				dateTill.setVisible(false);
				
				dateFrom.setValue(null);
				dateTill.setValue(null);

				
				AllSubsFromUserReportBtn.setVisible(true);
				AllSubsOfUserReportBtn.setVisible(true);
				AllCommentsFromUserReportBtn.setVisible(true);
				AllLikesFromUserReportBtn.setVisible(true);
				AllPostsFromUserReportBtn.setVisible(true);
				
				AllSubsFromUserBetweenDatesReportBtn.setVisible(false);
				AllSubsOfUserBetweenDatesReportBtn.setVisible(false);
				AllCommentsFromUserBetweenDatesReportBtn.setVisible(false);
				AllLikesFromUserBetweenDatesReportBtn.setVisible(false);
				AllPostsFromUserBetweenDatesReportBtn.setVisible(false);

				
				
				
				if (checkBoxPickDate.getValue() == true) {
					checkBoxPickDate.setValue(false);

				} else {
					Window.alert("You can just enable one Checkbox");

				}
			}
		});
		checkBoxPickDate.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				checkBoxPickDate.setValue(true);
				dateFrom.setVisible(true);
				dateTill.setVisible(true);
				
				
				// setting default value for DateFrom and DateTill

				lblFrom.setVisible(true);
				lblTill.setVisible(true);
				dateFrom.setValue(new Date());
				dateTill.setValue(new Date());
				
				AllSubsFromUserBetweenDatesReportBtn.setVisible(true);
				AllSubsOfUserBetweenDatesReportBtn.setVisible(true);
				AllCommentsFromUserBetweenDatesReportBtn.setVisible(true);
				AllLikesFromUserBetweenDatesReportBtn.setVisible(true);
				AllPostsFromUserBetweenDatesReportBtn.setVisible(true);
				
			
				AllSubsFromUserReportBtn.setVisible(false);
				AllSubsOfUserReportBtn.setVisible(false);
				AllCommentsFromUserReportBtn.setVisible(false);
				AllLikesFromUserReportBtn.setVisible(false);
				AllPostsFromUserReportBtn.setVisible(false);
				
				if (checkBoxShowAll.getValue() == true) {
					checkBoxShowAll.setValue(false);

				} else {
					Window.alert("You can just enable one Checkbox");

				}
			}
		});

		// button
		
		AllSubsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllSubsOfUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsOfUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));

				RootPanel.get().add(resultPanel);
				
			}
		});
		AllCommentsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				resultPanel.clear();
				resultPanel.add(new AllCommentsOfAllPostsFromUserReportForm(u, dateFrom.getValue(), dateTill.getValue())); 
				RootPanel.get().add(resultPanel);	
				
			}
		});
		AllLikesFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			
				
				resultPanel.clear();
				resultPanel.add(new AllLikesFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllPostsFromUserReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllPostsFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);

				
			}
		});
		
		//#########between button
		
		AllSubsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllSubsOfUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllSubsOfUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));

				RootPanel.get().add(resultPanel);
				
			}
		});
		AllCommentsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllCommentsOfAllPostsFromUserReportForm(u, dateFrom.getValue(), dateTill.getValue()));  //new AllCommentsFromUserReportForm(u, dateFrom.getValue(), dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllLikesFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllLikesFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);
				
			}
		});
		AllPostsFromUserBetweenDatesReportBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				resultPanel.clear();
				resultPanel.add(new AllPostsFromUserReportForm(u,  dateFrom.getValue(),  dateTill.getValue()));
				RootPanel.get().add(resultPanel);

				
			}
		});
		
		
		
	}
	
}


class setUserCallback implements AsyncCallback<Void> {

	public void onFailure(Throwable caught) {
		/*
		 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
		 */
		ClientsideSettings.getLogger().severe("User wurde nicht gesetzt, etwas mit der DB stimmt nicht !");
	}

	@Override
	public void onSuccess(Void result) {
		/*
		 * Wir erwarten diesen Ausgang, wollen aber keine Notifikation ausgeben.
		 */

	}

}

