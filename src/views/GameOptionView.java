package views;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import mvc.Controller;
import mvc.fx.FXView;

public class GameOptionView extends FXView {

	public GameOptionView(String newName, Controller newController) {
		// this constructor is the same for all view's
		super(newName, newController);
		construct();
	}

	@Override
	public Parent constructContainer() {
		

		// Objekte
		
		AppButton BacktoGameMenu = new AppButton("Zur�ck");
		Label Anleitung = new Label();
		BorderPane mainLayout = new BorderPane();

		VBox itemsLayout = new VBox();
		itemsLayout.setAlignment(Pos.CENTER);

		Anleitung.setText("Hie cha dr schisstim si text ihsetze\n");
		
		BacktoGameMenu.setOnAction(e -> getController().getView("gameview").show());

		itemsLayout.getChildren().addAll(Anleitung, BacktoGameMenu);
		
		Anleitung.setAlignment(Pos.TOP_CENTER);
		mainLayout.setCenter(itemsLayout);
	
		return mainLayout;
	}

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub

	}

}