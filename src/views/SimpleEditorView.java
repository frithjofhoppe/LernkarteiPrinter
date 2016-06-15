package views;

import java.util.ArrayList;

import globals.Globals;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.ModelInterface.Command;
import mvc.fx.FXController;
import mvc.fx.FXViewModel;
import views.components.AppButton;
import views.components.ControlLayout;


public class SimpleEditorView extends FXViewModel
{
	// ArrayList<VBox> cards;

	public SimpleEditorView (String newName, FXController newController)
	{
		// this constructor is the same for all view's
		super(newController);
		construct(newName);
	}

	VBox	editLayout	= new VBox(10);
	Label	headLbl;
	ScrollPane scroller = new ScrollPane();

	@Override
	public Parent constructContainer ()
	{
		headLbl = new Label("");
		headLbl.setId("bold");

		AppButton backBtn = new AppButton("_Zur�ck");
		backBtn.setOnAction(e -> getFXController().showView("stack"));

		//Info Button
		AppButton infobtn = new AppButton("Hilfe");
		infobtn.setOnAction(e ->
		getFXController().showView("bbcodeinfo"));
		
		BorderPane headLayout = new BorderPane(headLbl);
		headLayout.setPadding(new Insets(25));

		editLayout.setPadding(new Insets(10));
		editLayout.setAlignment(Pos.TOP_CENTER);

		BorderPane mainLayout = new BorderPane();
		mainLayout.setPadding(new Insets(15));
		mainLayout.setTop(headLayout);
		mainLayout.setCenter(scroller);
		mainLayout.setBottom(new ControlLayout(backBtn, infobtn));

		getFXController().getModel("cards").registerView(this);
		return mainLayout;
	}

	@Override
	public void refreshView ()
	{
		editLayout.getChildren().clear();

		String data = getData();

		if (data != null)
		{
			headLbl.setText(data + " - " + getFXController().getViewData("stack"));

			ArrayList<String> cardStrings = getFXController().getModel("cards").getDataList(data);
			ArrayList<HBox> cards = new ArrayList<>();
			debug.Debugger.out("" + cardStrings.size());
			for (String s : cardStrings)
			{
				String[] cardSides = s.split(Globals.SEPARATOR);
				TextField front = new TextField(cardSides[1]);
				TextField back = new TextField(cardSides[2]);

				front.focusedProperty().addListener(e ->
				{
					if (!front.isFocused())
					{
						if (back.getText() != null && !back.getText().equals("") && front.getText() != null
								&& !front.getText().equals(""))
						{
							getFXController().getModel("cards").doAction(Command.UPDATE, cardSides[0], front.getText(), back.getText());
						}
					}
				});

				back.focusedProperty().addListener(e ->
				{
					if (!back.isFocused())
					{
						if (back.getText() != null && !back.getText().equals("") && front.getText() != null
								&& !front.getText().equals(""))
						{
							getFXController().getModel("cards").doAction(Command.UPDATE, cardSides[0], front.getText(), back.getText());
						}
					}
				});

				Button delete  = new Button("X");
				Button editBtn = new Button("\u270E"); // \u270d \u2055 \u2699 \u270E
				
				delete.setOnAction(e -> getFXController().getModel("cards").doAction(Command.DELETE, cardSides[0]));
				editBtn.setOnAction(e ->
				{
					getFXController().setViewData("editorview",cardSides[0] + Globals.SEPARATOR + front.getText() + Globals.SEPARATOR + back.getText());
					getFXController().showView("editorview");
				});
				HBox v = new HBox(8);
				v.setAlignment(Pos.CENTER);
				v.getChildren().addAll(front, back, delete, editBtn);
				cards.add(v);
			}

			TextField front = new TextField();
			TextField back = new TextField();
			Button editBtn = new Button("\u270E"); // \u270d \u2055 \u2699 \u270E
			
			if(back.getText() == null){		
				editBtn.setOnAction(e ->
				{		
						getFXController().setViewData("editorview",front.getText() + Globals.SEPARATOR + back.getText());
						getFXController().showView("editorview");
				});
			}
			
			Button addBtn = new Button("\u2713");
			addBtn.setMaxWidth(35);

			addBtn.setOnAction(e ->
			{
				if (back.getText() != null && !back.getText().equals("") && front.getText() != null
						&& !front.getText().equals(""))
				{
					getFXController().getModel("cards").doAction(Command.NEW, front.getText(), back.getText(), data);
				}
			});

			HBox v = new HBox(8);

			v.setAlignment(Pos.CENTER);
			v.getChildren().addAll(front, back, addBtn, editBtn);
			cards.add(v);

			editLayout.getChildren().addAll(cards);
		}
		
		scroller.setContent(editLayout);
	}
}
