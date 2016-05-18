package controls;

import debug.Logger;
import javafx.stage.Stage;
import models.CardModel;
import models.ConfigModel;
import models.DoorModel;
import models.GameModel;
import models.LearnModel;
import models.ProfilModel;
import models.QuizletModel;
import models.StackModel;
import mvc.fx.FXController;
import views.DoorView;
import views.EditorView1;
import views.GameOptionView;
import views.GameView;
import views.HelpView;
import views.ImpressumView;
import views.LearnView;
import views.MainView;
import views.MainViewSettings;
import views.OptionsView;
import views.PreLearnView;
import views.QuizletImportView;
import views.SimpleEditorView;
import views.StackView;
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
public class MainController extends FXController
{
	public MainController(Stage primaryStage) {
		super(new MainViewSettings(primaryStage));
	}

	@Override
	public void initMyModels() {
		Logger.stop();
		Logger.log("Instanziere Models....");
		this.addUniqueModel(new GameModel("game"));
		this.addUniqueModel(new DoorModel("door"));
		this.addUniqueModel(new StackModel("stack"));
		this.addUniqueModel(new CardModel("cards"));
		this.addUniqueModel(new LearnModel("learn"));
		this.addUniqueModel(new ProfilModel("profil"));
		this.addUniqueModel(new QuizletModel("quizlet"));
		this.addUniqueModel(new ConfigModel("config"));
	}

	@Override
	public void initMyViews() {
		Logger.log("Instanziere Views....");
		this.addUniqueView(new MainView(getMainViewName(), this));
		this.addUniqueView(new StatisticsView("statisticsview", this));
		this.addUniqueView(new StatsView("statsview", this));
		this.addUniqueView(new DoorView("doorview", this));
		this.addUniqueView(new OptionsView("optionsview", this));
		this.addViewOnNewStage(new HelpView("helpview", new HelpController())); // on new stage
		this.addUniqueView(new GameOptionView("gameoptionview", this));
		this.addUniqueView(new GameView("gameview", this));
		this.addUniqueView(new StackView("stack", this));
		this.addUniqueView(new QuizletImportView("quizlet", this));
		this.addUniqueView(new SimpleEditorView("simpleeditorview", this));
		this.addUniqueView(new EditorView1("editorview", this));
		this.addUniqueView(new PreLearnView("prelearn", this));
		this.addUniqueView(new LearnView("learnview", this));
		this.addUniqueView(new ImpressumView("impressumview", this));
		Logger.log("Instanzierung beendet....");
	}

	@Override
	public void startApp() {
		this.showMainView();
	}
}
