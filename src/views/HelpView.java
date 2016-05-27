package views;

import globals.Globals;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import mvc.fx.FXController;
import mvc.fx.FXView;
import views.components.AppButton;
import views.components.CloseButton;

/**
 * Hilfefenster
 * 
 * @author miro-albrecht
 *
 */
public class HelpView extends FXView
{
	public HelpView(String newName, FXController newController) {
		// this constructor is the same for all view's
		super(newController);
		construct(newName);
	}

	public Parent constructContainer () {
		
		this.getWindow().setTitle(Globals.appTitle+"Hilfe"+Globals.appVersion);
		this.getWindow().setResizable(false);

		AppButton impressumBtn = new AppButton("_Impressum");
		AppButton quizletBtn = new AppButton("_Quizlet");
		AppButton anleitungBtn = new AppButton("_Anleitung");
		AppButton indexBtn     = new AppButton("Inde_x");
		AppButton logBtn     = new AppButton("_Logger");
		CloseButton closeBtn = new CloseButton();
		
		impressumBtn.setOnAction(e -> getController().showView("impressumview"));
		quizletBtn.setOnAction(e -> getController().showView("quizletview"));
		anleitungBtn.setOnAction(e -> getController().showView("manualview"));
		indexBtn.setOnAction(e -> getController().showView("indexview"));
		logBtn.setOnAction(e -> getController().showView("logview"));

		VBox tempVBox = new VBox();
		tempVBox.setPadding(new Insets(10));
		tempVBox.setSpacing(10);
		tempVBox.setAlignment(Pos.CENTER);
		tempVBox.getChildren().addAll(impressumBtn, quizletBtn, anleitungBtn, indexBtn, logBtn, closeBtn);

		tempVBox.setId("help");
		return tempVBox;
	}

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub
		
	}
	
}
