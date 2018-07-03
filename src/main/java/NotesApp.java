import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotesApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(
				new Scene(
						(Parent) new FXMLLoader(
								getClass().getResource(
										"/view/mainWindow.fxml"
								)
						).load()
				)
		);
		primaryStage.show();
	}
}
