package de.hdm.itp.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.SimpleDateFormat;

import de.hdm.itp.shared.EditorAdministration;
import de.hdm.itp.shared.ReportGenerator;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.AllCommentsFromUserReport;
import de.hdm.itp.shared.report.AllLikesFromUserReport;
import de.hdm.itp.shared.report.AllPostsFromUserReport;
import de.hdm.itp.shared.report.AllSubsFromUserReport;
import de.hdm.itp.shared.report.AllSubsOfUserReport;
import de.hdm.itp.shared.report.Column;
import de.hdm.itp.shared.report.Row;

/**
 * Die ReportGeneratorImpl Klasse.
 */
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/** Die Instanz der Klasse der Editor Administration . */
	private EditorAdministration admin = null;

	/**
	 * Der Konstruktor für der ReportGeneratorImpl.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
	}

	/**
	 * Initiiert die EditorAdministrationImpl, damit man auf die Funktionen der
	 * EditorAdministration Impl zugreifen kann.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @see javax.servlet.GenericServlet#init()
	 */

	public void init() throws IllegalArgumentException {

		this.admin = EditorAdministrationImpl.editorAdministrationImpl();
		this.admin.init();
	}

	/**
	 * Auslesen des EditorAdministrationsobjekts.
	 *
	 * @return das editor administrations objekt
	 */
	protected EditorAdministration getEditorAdministration() {
		return this.admin;
	}

	/**
	 * Identifizierung des angemeldeten Users.
	 *
	 * @param email the email
	 * @return the user information
	 * @throws IllegalArgumentException the illegal argument exception
	 */

	public User getUserInformation(String email) throws IllegalArgumentException {

		return this.admin.getUserByEmail(email);
	}

	public void setUser(User u) throws IllegalArgumentException {

	}

	/*
	 * ***************************************************************************
	 * Abschnitt: Erstellen der Reports
	 * ***************************************************************************
	 */

	/**
	 * Erstellen des Reports aller Kontakte, die der angemeldete Nutzer besitzt und
	 * mit diesem geteilt wurden.
	 *
	 * @return the all contacts of user report
	 */

	@Override

	// TODO dateFrom DateTill übergeben
	public AllCommentsFromUserReport createAllCommentsFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {

		AllCommentsFromUserReport result = new AllCommentsFromUserReport();

		result.setTitel("All Ihre Kommentare");

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Post des Kommentars"));
		headline.addColumn(new Column("Kommentar"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Änderungsdatum"));

		result.addRow(headline);

		Vector<Comment> comments = this.admin.getCommentsOfUser(u);

		for (Comment c : comments) {
			Row commentRow = new Row();

			if (dateFrom == null) {
				commentRow.addColumn(new Column(String.valueOf(c.getPostId())));
				commentRow.addColumn(new Column(String.valueOf(c.getText())));
				commentRow.addColumn(new Column(String.valueOf(c.getCreateDate())));
				commentRow.addColumn(new Column(String.valueOf(c.getModDate())));

				result.addRow(commentRow);

			} else {
				if (c.getModDate().after(dateFrom) && c.getModDate().before(dateTill)) {

					commentRow.addColumn(new Column(String.valueOf(c.getPostId())));
					commentRow.addColumn(new Column(String.valueOf(c.getText())));
					commentRow.addColumn(new Column(String.valueOf(c.getCreateDate())));
					commentRow.addColumn(new Column(String.valueOf(c.getModDate())));

					result.addRow(commentRow);
				}
				System.out.println("Sorry there was no comment made in between the dates you have choosen");

			}

		}

		return result;

		/*
		 * amount of comments
		 */

	}

	public AllLikesFromUserReport createAllLikesFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException {

		// if this.getAdministration(== null){return null;}

		AllLikesFromUserReport result = new AllLikesFromUserReport();

		result.setTitel("All Ihre likes"); // amountOfLikes

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Beitragsnummer"));
		headline.addColumn(new Column("Beitragstext"));
		headline.addColumn(new Column("Erstellungsdatum"));

		result.addRow(headline);

		Vector<Like> like = this.admin.getAllLikesOfUser(u);

		

		for (Like l : like) {
			Row likesRow = new Row();

			if (dateFrom == null) {
				likesRow.addColumn(new Column(String.valueOf(l.getPostId())));
				likesRow.addColumn(new Column(String.valueOf(l.getOwnerId())));
				likesRow.addColumn(new Column(String.valueOf(l.getCreateDate())));

				result.addRow(likesRow);

			} else {
				if (l.getCreateDate().after(dateFrom) && l.getCreateDate().before(dateTill)) {

					likesRow.addColumn(new Column(String.valueOf(l.getPostId())));
					likesRow.addColumn(new Column(String.valueOf(l.getOwnerId())));
					likesRow.addColumn(new Column(String.valueOf(l.getCreateDate())));

					result.addRow(likesRow);
				}
				System.out.println("Sorry there was no comment made in between the dates you have choosen");

			}

		}
		return result;

		/*
		 * 
		 * amount of likes postID createDate
		 */
	}

	public AllPostsFromUserReport createAllPostsFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException {

		// if this.getAdministration(== null){return null;}

		AllPostsFromUserReport result = new AllPostsFromUserReport();

		result.setTitel("All Ihre Beiträge"); // amountOfPosts

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Beitragsnummer"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Änderungsdatum"));
		headline.addColumn(new Column("Beitragstext"));

		result.addRow(headline);

		Vector<Post> post = this.admin.getAllPostsOfUser(u);

		
		for (Post p : post) {
			Row postRow = new Row();

			postRow.addColumn(new Column(String.valueOf(p.getId())));
			postRow.addColumn(new Column(String.valueOf(p.getCreateDate())));
			postRow.addColumn(new Column(String.valueOf(p.getModDate())));
			postRow.addColumn(new Column(String.valueOf(p.getContent())));

			result.addRow(postRow);
		}

		return result;
		/*
		 * amount of posts postId createDate getContent getModDate
		 */
	}

	public AllSubsFromUserReport createAllSubsFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException {

		// if this.getAdministration(== null){return null;}

		AllSubsFromUserReport result = new AllSubsFromUserReport();

		result.setTitel("Alle von Ihnen abonnierten Benutzer"); // amountOfPosts

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Abonnierter Benutzer"));
		headline.addColumn(new Column("Erstellungsdatum des Abonnements"));

		result.addRow(headline);

		Vector<Subs> subs = this.admin.getSubsOfTargetUser(u);
		
		for (Subs s : subs) {
			Row subsRow = new Row();

			if (dateFrom == null) {

				subsRow.addColumn(new Column(String.valueOf(s.getTargetUser())));
				subsRow.addColumn(new Column(String.valueOf(s.getCreateDate())));
				result.addRow(subsRow);

			} else {
				if (s.getCreateDate().after(dateFrom) && s.getCreateDate().before(dateTill)) {


					subsRow.addColumn(new Column(String.valueOf(s.getTargetUser())));
					subsRow.addColumn(new Column(String.valueOf(s.getCreateDate())));
					
					result.addRow(subsRow);
				}
				System.out.println("Sorry there was no comment made in between the dates you have choosen");

			}

		}

		return result;
		/*
		 * 
		 * //TODO ?? targetUserID ? TargetUser nickname ? amount of Subs getTargetUser
		 * getCreateDate
		 */
	}

	public AllSubsOfUserReport createAllSubsOfUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException {
		// if this.getAdministration(== null){return null;}

		AllSubsOfUserReport result = new AllSubsOfUserReport();

		result.setTitel("All Ihre Abonnenten");

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Abonnent"));
		headline.addColumn(new Column("Abonniert seit"));

		result.addRow(headline);

		Vector<Subs> subs = this.admin.getSubsOfCurrentUser(u);

		for (Subs s : subs) {
			Row subsRow = new Row();

			if (dateFrom == null) {

				subsRow.addColumn(new Column(String.valueOf(s.getTargetUser())));
				subsRow.addColumn(new Column(String.valueOf(s.getCreateDate())));
				result.addRow(subsRow);

			} else {
				if (s.getCreateDate().after(dateFrom) && s.getCreateDate().before(dateTill)) {


					subsRow.addColumn(new Column(String.valueOf(s.getTargetUser())));
					subsRow.addColumn(new Column(String.valueOf(s.getCreateDate())));
					
					result.addRow(subsRow);
				}
				System.out.println("Sorry there was no comment made in between the dates you have choosen");

			}

		}

		return result;

		/*
		 * amount of subs getTargetUser createDate
		 */
	}
}