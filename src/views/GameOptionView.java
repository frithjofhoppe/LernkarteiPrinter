package views;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import mvc.Controller;
import mvc.FXView;

public class GameOptionView extends FXView {
	

	public GameOptionView(String newName, Controller newController) {
		// this constructor is the same for all view's
		super(newName, newController);
		construct();
	}

	@Override
	public Parent constructContainer() {
		BorderPane InfoLayout = new BorderPane();
		
		//Objekte
		@SuppressWarnings ("unused")
		AppButton BacktoGameMenu = new AppButton("Zur�ck");
		BorderPane mainLayout = new BorderPane();
		
		VBox itemsLayout = new VBox();
		
		
		
	

		
		mainLayout.setCenter(itemsLayout);
		return InfoLayout;
	}

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub

	}

}
