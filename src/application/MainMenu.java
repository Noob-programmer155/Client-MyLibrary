package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenu extends BorderPane {
	@FXML
	Label userlabelmain;
	
	@FXML
	ImageView imgusermain;
	
	@FXML
	TextArea logactivity;
	
	@FXML
	Button librarybtn, bookbtn, profilebtn,logoutbtn;
	
	LoginControler ha;
	private int iduser;
	
	MainMenu(int iduser, Image usersd, String name, LoginControler kj){
		BorderPane log = new BorderPane();
		FXMLLoader ha1 = new FXMLLoader(getClass().getResource("Mainmenu.fxml"));
		ha1.setController(this);
		ha1.setRoot(log);
		try {
			ha1.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setCenter(log);
		ha = kj;
		userlabelmain.setText(name);
		imgusermain.setImage(usersd);
		imgusermain.setFitHeight(40);
		imgusermain.setFitWidth(40);
		this.iduser = iduser;
		
		logactivity.appendText("\n"+ha.lw.getActivity("Login success"));
	}
	
	@FXML
	private void librarybtn_pressed(ActionEvent s) {
		logactivity.appendText("\n"+ha.lw.getActivity("Opening Library..."));
//		librarybtn.setStyle("-fx-background-color: white; -fx-text-fill: linear-gradient(to top, #ff5000, #d6ff00); -fx-background-radius: 10 10 10 10;");
		
		Scene oi = new Scene(new Library(logactivity,ha,iduser,this));
		Stage mq = (Stage)((Node)s.getSource()).getScene().getWindow();
		mq.setScene(oi);
	}
	
	@FXML
	private void bookbtn_pressed(ActionEvent f) {
		logactivity.appendText("\n"+ha.lw.getActivity("Opening My Book..."));
//		bookbtn.setStyle("-fx-background-color: white; -fx-text-fill: linear-gradient(to top, #ff5000, #d6ff00); -fx-background-radius: 10 10 10 10;");
		
		Scene j = new Scene(new Mybook(logactivity,ha,iduser,this));
		Stage mq = (Stage)((Node)f.getSource()).getScene().getWindow();
		mq.setScene(j);
	}
	
	@FXML
	private void profilebtn_pressed(ActionEvent j) throws SQLException {
		logactivity.appendText("\n"+ha.lw.getActivity("Opening User Profile..."));
//		profilebtn.setStyle("-fx-background-color: white; -fx-text-fill: linear-gradient(to top, #ff5000, #d6ff00); -fx-background-radius: 10 10 10 10;");
		
		Scene m = new Scene(new profile(logactivity,ha,iduser,this));
		Stage mq = (Stage)((Node)j.getSource()).getScene().getWindow();
		mq.setScene(m);
	}
	
	@FXML
	private void logoutbtn_pressed(ActionEvent s) throws IOException {
		logactivity.appendText("\n"+ha.lw.getActivity("Logout"));
		ha.lw.disconnect();
//		logoutbtn.setStyle("-fx-background-color: white; -fx-text-fill: linear-gradient(to top, #ff5000, #d6ff00); -fx-background-radius: 10 10 10 10;");
		
		Scene m = new Scene(ha);
		Stage mq = (Stage)((Node)s.getSource()).getScene().getWindow();
		mq.setScene(m);
	}
}


