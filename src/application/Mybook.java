package application;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;

import application.Source.access;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
public class Mybook extends BorderPane {
	
	@FXML
	TextField searchbookuser;
	
	@FXML
	Button searchbtnuser, filterbookbtnuser, backbookbtn;
	
	@FXML
	VBox containerbookuser;
	
	private TextArea init;
	private MainMenu b;
	private LoginControler mw;
	private int iduser;
	
	Mybook (TextArea logactivity, LoginControler ga, int id, MainMenu bcx){
		BorderPane la = new BorderPane();
		FXMLLoader ja = new FXMLLoader(getClass().getResource("My_book.fxml"));
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
		mw = ga;
		iduser = id;
		b = bcx;
		try {
			mainContainerMyBook(5, 10);
		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void searchbtnuser_pressed(ActionEvent s) throws SQLException {
		init.appendText("\n"+mw.lw.getActivity("searching book...."));
		String sas = searchbookuser.getText();
		mw.lw.getDataFromconnectionclient(sas, access.CLIENT, iduser);
	}
	
	@FXML
	private void filterbookbtnuser_pressed(ActionEvent s) {
		init.appendText("\n"+mw.lw.getActivity("filtering book...."));
	}
	
	@FXML
	private void backbookbtn_pressed(ActionEvent k) {
		init.appendText("\n"+mw.lw.getActivity("return to mainmenu..."));
		
		Scene m = b.getScene();
		Stage xz = (Stage)((Node)k.getSource()).getScene().getWindow();
		xz.setScene(m);
	}
	
	private void mainContainerMyBook(int jumlahTab, double space) throws SQLException {
		Source ka = mw.lw;
		int had=0;
		HBox h = new HBox();
		h.setSpacing(space);
		containerbookuser.getChildren().add(h);
		Iterator<Object> la = ka.getDataBookUser(iduser).iterator();
		while(la.hasNext()) {
			if(had >= jumlahTab) {
				h = new HBox();
				h.setSpacing(space);
				containerbookuser.getChildren().add(h);
				had = 0;
			}
			Object[] pa = (Object[])la.next();
			Image ks = new Image((InputStream)pa[3]);
			Container_MyBook ja = new Container_MyBook(mw,ks,(String)pa[0],(String)pa[1],(String)pa[2]);
			h.getChildren().add(ja);
			had++;
		}
	}
	
	@FXML
	private void mouse_enter_backbookbtn(MouseEvent as) {
		backbookbtn.setStyle("-fx-background-color: rgba(102,77,179,1);");
	}
	
	@FXML
	private void mouse_exit_backbookbtn(MouseEvent aa) {
		backbookbtn.setStyle("-fx-background-color: rgba(102,77,179,0.7);");
	}
}

class Container_MyBook extends BorderPane{
	
	@FXML
	ImageView imguserbook;
	
	@FXML
	Label booktitlebook, authorbookbook;
	
	@FXML
	Button readbookbookbtn,downloadbookbookbtn;
	
	boolean mzx = false;
	private LoginControler uq;
	Popup ka;
	Label pop;
	String desc;
	Container_MyBook(LoginControler ja,Image img, String title, String author, String desc){
		uq = ja;
		initial();
		ka = new Popup();
		ka.setWidth(200);
		pop = new Label();
		pop.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
		pop.setStyle("-fx-background-color:black;-fx-text-fill:white;");
		pop.setWrapText(true);
		ka.getContent().add(pop);
		ka.hide();
		
		imguserbook.setImage(img);
		booktitlebook.setText(title);
		authorbookbook.setText(author);
		this.desc = desc;
	}
	
	@FXML
	private void mouse_enter_booktitlebook(MouseEvent s) {
		if(mzx == false) {
			mzx = true;
			pop.setText(booktitlebook.getText()+"\n ===========>>>> \n"+desc);
			ka.setAnchorX(s.getX());
			ka.setAnchorY(s.getY());
			ka.show(((Node)s.getSource()).getScene().getWindow());
		}
	}
	
	@FXML
	private void mouse_exit_booktitlebook(MouseEvent s) {
		if(mzx == true) {
			mzx = false;
			ka.hide();
		}
	}
	
	@FXML
	private void mouse_enter_authorbookbook(MouseEvent s) {
		if(mzx == false) {
			mzx = true;
			pop.setText(authorbookbook.getText());
			ka.setAnchorX(s.getX());
			ka.setAnchorY(s.getY());
			ka.show(((Node)s.getSource()).getScene().getWindow());
		}
	}
	
	@FXML
	private void mouse_exit_authorbookbook(MouseEvent s) {
		if(mzx == true) {
			mzx = false;
			ka.hide();
		}
	}
	
	@FXML
	private void readbookbookbtn_pressed(ActionEvent s) {
		
	}
	
	@FXML
	private void downloadbookbookbtn_pressed(ActionEvent s) {
		
	}
	
	private void initial() {
		BorderPane kj = new BorderPane();
		FXMLLoader js = new FXMLLoader(getClass().getResource("my_book_container.fxml"));
		js.setController(this);
		js.setRoot(kj);
		try {
			js.load();}
		catch(IOException s) {}		
		
		setCenter(kj);
	}
}
