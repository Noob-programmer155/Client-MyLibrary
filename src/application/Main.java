package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		BorderPane oi = new LoginControler();
		Scene gs = new Scene(oi);
		gs.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.centerOnScreen();
		primaryStage.setScene(gs);
		primaryStage.setTitle("My Library");
		primaryStage.setResizable(true);
		primaryStage.widthProperty().addListener(fd ->{
			primaryStage.sizeToScene();
		});
		primaryStage.heightProperty().addListener(gf ->{
			primaryStage.sizeToScene();
		});
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
