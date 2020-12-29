package application;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.LinkedList;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class profile extends BorderPane{
	@FXML
	ImageView userimginfo;
	
	@FXML
	Label usernameinfo,useremailinfo;
	
	@FXML
	Button changeprofilebtn,backprofilebtn;
	
	private LoginControler ha;
	private TextArea init;
	private int id;
	private String user;
	private Image userimg;
	private MainMenu ba;
	profile(TextArea logactivity, LoginControler ga,int id, MainMenu vc) throws SQLException{
		BorderPane la = new BorderPane();
		FXMLLoader ja = new FXMLLoader(getClass().getResource("Profile.fxml"));
		ja.setController(this);
		ja.setRoot(la);
		try {
			ja.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setCenter(la);
		init = logactivity;
		ha = ga;
		ba = vc;
		
		LinkedList<Object> o = ha.lw.getDataUser(id);
		Object[] a = (Object[])o.get(0);
		Image pa = new Image((InputStream)a[2]);
		this.id = id;
		user = (String)a[0];
		userimg = pa;
		userimginfo.setImage(pa);
		userimginfo.setFitHeight(300);
		userimginfo.setFitWidth(300);
		usernameinfo.setText((String)a[0]);
		useremailinfo.setText((String)a[1]);
	}
	
	@FXML
	private void changeprofilebtn_pressed(ActionEvent s) {
		
	}
	
	@FXML
	private void backprofilebtn_pressed(ActionEvent s) {
		init.appendText("\n"+ha.lw.getActivity("return to mainmenu..."));
		
		Scene m = ba.getScene();
		Stage mq = (Stage)((Node)s.getSource()).getScene().getWindow();
		mq.setScene(m);
	}
	
	@FXML
	private void mouse_enter_backprofilebtn(MouseEvent a) {
		backprofilebtn.setStyle("-fx-background-color: rgba(51,77,179,1);");
	}
	
	@FXML
	private void mouse_exit_backprofilebtn(MouseEvent s) {
		backprofilebtn.setStyle("-fx-background-color: rgba(51,77,179,0.8);");
	}
}
