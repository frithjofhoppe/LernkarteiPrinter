package views;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import mvc.fx.FXController;
import mvc.fx.FXView;
import views.components.AppButton;
import views.components.ContainerLayout;
import views.components.ControlLayout;

public class UserView extends FXView
{

	public UserView(String newName, FXController newController)
	{
		super(newController);
		construct(newName);
	}
	
	BorderPane bp;

	// Der Header Bereich setzt sich aus den Buttons zum �ndern der
	// Benutzerdaten und dem Username zusammen.
	ContainerLayout header;
	ControlLayout changeButtons;
	ControlLayout uName;

	Label username;
	AppButton changeName;
	AppButton changePasswort;
	AppButton changeEmail;

	// Der Body setzte sich aus zwei ContainerLAyouts zusammen (Links und
	// Rechts) darin sind dann nochmals mehrere Elemente vorhanden. Vorgesehen
	// ist eine Statistik und eine Auflistung aller bereits gelernten Karteien
	
	//Rechts
	ContainerLayout right;

	Label hinweis;
	
	//Links
	ContainerLayout left;
	
	ListView<String> stacks;
	AppButton learn;

	// Der Footer enth�lt die Buttons, um ins Hauptmen� zur�ck zu gelangen oder
	// andere Aktionen durchzuf�hren. Er entspricht der Hbox "Controls"
	ControlLayout footer;

	AppButton back;

	@Override
	public Parent constructContainer()
	{
		bp = new BorderPane();
		// TODO Auto-generated method stub
		
		header = new ContainerLayout();
		changeButtons = new ControlLayout();
		uName = new ControlLayout();

		username = new Label();
		changeName = new AppButton("Namen �ndern");
		changePasswort = new AppButton("Passwort �ndern");
		changeEmail = new AppButton("E-Mail �ndern");

		right = new ContainerLayout();
		hinweis = new Label("Hinweis : Hier wird sp�ter eine Statistik angezeigt");
		
		left = new ContainerLayout();
		stacks = new ListView<String>();
		learn = new AppButton("lernen");

		footer = new ControlLayout();

		back = new AppButton("Zur�ck");
		
		footer.getChildren().addAll(back);
		left.getChildren().addAll(stacks, learn);
		right.getChildren().addAll(hinweis);
		
		uName.getChildren().addAll(username);
		changeButtons.getChildren().addAll(changeName, changeEmail, changePasswort);
		
		header.getChildren().addAll(uName, changeButtons);

		bp.setBottom(footer);
		bp.setLeft(left);
		bp.setRight(right);
		bp.setTop(header);

		back.setOnAction(e -> getFXController().showMainView());

		return bp;
	}

	@Override
	public void refreshView()
	{
		// TODO Auto-generated method stub

	}

}
