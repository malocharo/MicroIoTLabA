import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Controleur controleur ;
	public static Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Micro IoT Lab");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("fenetre.fxml"));
		BorderPane panneau = (BorderPane) loader.load();
		scene = new Scene(panneau);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void init(){
		if(SerialCommunication.sender==null)
			new SerialCommunication();
	}
	
	@Override
	public void stop(){
	    System.exit(0);
	}

}
