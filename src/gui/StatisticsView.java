package gui;

import application.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StatisticsView extends View
{
	public StatisticsView(String setName, Stage window, MainController controller)
	{
		super (setName, window);
		
		Button b = new Button("Back");
		BorderPane bp = new BorderPane();
		bp.setCenter(b);
		b.setOnAction(e -> controller.show("mainview"));
		this.setScene(new Scene(bp, 800, 450));
	}
}