package controls;

import debug.Logger;
import javafx.stage.Stage;
import models.BoxModel;
import models.CardModel;
import models.DoorModel;
import models.GameModel;
import mvc.Controller;
import views.BoxView;
import views.DoorView;
import views.EditorView1;
import views.SimpleEditorView;
import views.GameView;
import views.HelpView;
import views.ImpressumView;
import views.KarteiView;
import views.LearnView;
import views.MainView;
import views.MainViewSettings;
import views.OptionsView;
import views.StatisticsView;
import views.StatsView;
/**
 * Diese Klasse Kontrolliert alle Sichten und Models. Den Sichten wird die
 * Navigation zur Verf�gung gestellt. Alle Sichten (ausser Modalfenster) werden
 * hier mit eindeutigen Namen versehen.
 * 
 * @author miro albrecht & hugo-lucca
 *
 */
public class MainController extends Controller
{
	public MainController(Stage primaryStage) {
		super(primaryStage, new MainViewSettings());
	}

	@Override
	public void initMyModels() {
		Logger.stop();
		Logger.log("Instanziere Models....");
		this.addUniqueModel(new GameModel("game"));
		this.addUniqueModel(new DoorModel("door"));
		this.addUniqueModel(new BoxModel("box"));
		this.addUniqueModel(new CardModel("cards"));
	}

	@Override
	public void initMyViews() {
		Logger.log("Instanziere Views....");
		this.addUniqueView(new MainView(getMainViewName(), this));
		this.addUniqueView(new StatisticsView("statisticsview", this));
		this.addViewOnNewStage(new StatsView("statsview", new Controller()));
		this.addUniqueView(new DoorView("doorview", this));
		this.addUniqueView(new OptionsView("optionsview", this));
		this.addViewOnNewStage(new HelpView("helpview", new HelpController())); // on new stage
		this.addUniqueView(new GameView("gameview", this));
		this.addUniqueView(new KarteiView("karteiview", this));
		this.addUniqueView(new BoxView("boxview", this));
		this.addUniqueView(new SimpleEditorView("simpleeditorview", this));
		this.addUniqueView(new EditorView1("editorview", this));
		this.addUniqueView(new LearnView("learnview", this));
		this.addUniqueView(new ImpressumView("impressumview", this));
		Logger.log("Instanzierung beendet....");
		this.showMainView();
	}
}