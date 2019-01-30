package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.User;


/**
 * The Class MainPanel.
 */
public class MainPanel extends VerticalPanel {
	
	/** The editor administration. */
	private EditorAdministrationAsync editorAdministration = null;
	
	/** The pp. */
	//private Label header_lbl = new Label("Mein Profil");
	public PinboardPanel pp = new PinboardPanel();
	
	/** The user profile. */
	public userProfilePanel userProfile = new userProfilePanel();
	
	/** The user. */
	User user = new User();
	
	/** The main header. */
	protected HeadingElement mainHeader = Document.get().createHElement(1);
	
	/** The vp. */
	VerticalPanel vp = new VerticalPanel();
	
	
/* (non-Javadoc)
 * @see com.google.gwt.user.client.ui.Widget#onLoad()
 */
public void onLoad() {
		super.onLoad();
		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		
		user = ClientsideSettings.getUser();

		//this.add(header_lbl);
		this.addStyleName("Main");
		
		
//		this.add(new HTMLPanel("<div id=\"mainHeader\"></div>"));
//		mainHeader.setInnerHTML("My Profile");
//		mainHeader.setClassName("Header");
//		DOM.getElementById("mainHeader").appendChild(mainHeader);
		
		//vp.add(pp);
		//this.add(vp);
		
		
//		this.addStyleName("Main");		
		
		//header_lbl.setStylePrimaryName("Header");
		
		
		//this.add(this.header_lbl);
		
		/**
		 * Hinzufügen des User-Profils
		 */
		userProfile.setWidth("100%");
		this.add(this.userProfile);
		userProfile.setMainPanel(this);
		
		this.add(this.pp);
		pp.setStylePrimaryName("profilePanel");	
		
}

/**
 * Creates the pinnboard.
 *
 * @param user the user
 */
public void createPinnboard(User user){
	userProfile.clear();
	userProfile.createUserProfile(user);
	pp.clear();
	pp.createPinboard(user);
	
}

}
