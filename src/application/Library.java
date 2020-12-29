package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import application.Source.access;

import java.io.*;

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
import javafx.scene.input.*;

import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
public class Library extends BorderPane{
	@FXML
	TextField searchbook;
	
	@FXML
	Button searchbtn,filterbookbtn,backlibrarybtn;
	
	@FXML
	VBox containerbook;
	
	private LoginControler ha;
	private MainMenu ja;
	private TextArea init;
	private LinkedList<Object> contain;
	private int id;
	Library(TextArea logactivity, LoginControler ga,int id, MainMenu vzx){
		BorderPane la = new BorderPane();
		FXMLLoader jaz = new FXMLLoader(getClass().getResource("theLibrary.fxml"));
		jaz.setController(this);
		jaz.setRoot(la);
		try {
			jaz.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setCenter(la);
		init = logactivity;
		ha = ga;
		this.id = id;
		ja = vzx;
		try {
			contain = ha.lw.getDataBook();
			mainContainerLibrary(5, 10, contain);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@FXML
	private void searchbtn_pressed(ActionEvent s) throws SQLException {
		init.appendText("\n"+ha.lw.getActivity("searching book...."));
		String sas = searchbook.getText();
		LinkedList <Object> das = ha.lw.getDataFromconnectionclient(sas, access.SERVER,id);
		if(!das.isEmpty()) {
			containerbook.getChildren().removeAll(containerbook.getChildren());
			mainContainerLibrary(5, 10, das);}
		else {}
	}
	
	@FXML
	private void filterbookbtn_pressed(ActionEvent a) {
		init.appendText("\n"+ha.lw.getActivity("filtering book...."));
	}
	
	@FXML
	private void backlibrarybtn_pressed(ActionEvent s) {
		init.appendText("\n"+ha.lw.getActivity("return to mainmenu..."));
		
		Scene ma = ja.getScene();
		Stage la = (Stage)((Node)s.getSource()).getScene().getWindow();
		la.setScene(ma);
	}
	
	private void mainContainerLibrary(int jumlahTab, double space, LinkedList<Object> sa) throws SQLException {
		int had=0;
		HBox h = new HBox();
		h.setSpacing(space);
		containerbook.getChildren().add(h);
		Iterator<Object> la = sa.iterator();
		while(la.hasNext()) {
			if(had >= jumlahTab) {
				h = new HBox();
				h.setSpacing(space);
				containerbook.getChildren().add(h);
				had = 0;
			}
			Object[] pa = (Object[])la.next();
			Image ks = new Image((InputStream)pa[3]);
			Container_Library ja = new Container_Library(ha,id,init,ks,(String)pa[0],(String)pa[1],(String)pa[2]);
			h.getChildren().add(ja);
			had++;
		}
	}
	
	@FXML
	private void mouse_enter_backlibrarybtn(MouseEvent s) {
		backlibrarybtn.setStyle("-fx-background-color: rgba(102,77,179,1);");
	}
	
	@FXML
	private void mouse_exit_backlibrarybtn(MouseEvent a) {
		backlibrarybtn.setStyle("-fx-background-color: rgba(102,77,179,0.7);");
	}
}

class Container_Library extends BorderPane {
	
	@FXML
	ImageView bookimglibrary;
	
	@FXML
	Label booktitlelibrary,authorbooklibrary;
	
	@FXML
	Button readbooklibrarybtn,buybooklibrarybtn;
	
	boolean mzx = false;
	private LoginControler uq;
	private int user;
	private TextArea Init;
	String title;
	String author;
	Popup ka;
	Label pop;
	String desc;
	
	Container_Library(LoginControler ja,int user, TextArea init,Image img, String title, String author, String desc){
		uq = ja;
		this.user = user;
		this.title = title;
		this.author = author;
		Init = init;
		initial();
		ka = new Popup();
		ka.setWidth(300);
		pop = new Label();
		pop.setMaxWidth(300);
		pop.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
		pop.setStyle("-fx-background-color:rgba(0,0,0,0.75);-fx-text-fill:white;");
		pop.setWrapText(true);
		ka.getContent().add(pop);
		ka.hide();
		
		bookimglibrary.setImage(img);
		booktitlelibrary.setText(title);
		authorbooklibrary.setText(author);
		this.desc = desc;
	}
	
	@FXML
	private void mouse_enter_booktitlelibrary(MouseEvent g) {
		if(mzx == false) {
			mzx = true;
			pop.setText(booktitlelibrary.getText()+"\n ===========>>>> \n"+desc);
			ka.setAnchorX(g.getX());
			ka.setAnchorY(g.getY());
			ka.show(((Node)g.getSource()).getScene().getWindow());
		}
	}
	
	@FXML
	private void mouse_exit_booktitlelibrary(MouseEvent as) {
		if(mzx == true) {
			mzx = false;
			ka.hide();
		}
	}
	
	@FXML 
	private void mouse_enter_authorbooklibrary(MouseEvent d) {
		if(mzx == false) {
			mzx = true;
			pop.setText(authorbooklibrary.getText());
			ka.setAnchorX(d.getX());
			ka.setAnchorY(d.getY());
			ka.show(((Node)d.getSource()).getScene().getWindow());
		}
	}
	
	@FXML
	private void mouse_exit_authorbooklibrary(MouseEvent d){
		if(mzx == true) {
			mzx = false;
			ka.hide();
		}
	}
	
	@FXML
	private void readbooklibrarybtn_pressed(ActionEvent s) {
		
	}
	
	@FXML
	private void buybooklibrarybtn_pressed(ActionEvent s) {
		Init.appendText("\n"+uq.lw.getActivity("Buying book..."));
		if(uq.lw.setBuyBookUser(user, title, author)) {
			Init.appendText("\n"+uq.lw.getActivity("Buying book success"));
		}
		else {
			Init.appendText("\n"+uq.lw.getActivity("Buying book failed"));
		}
	}
	
	private void initial() {
		BorderPane kj = new BorderPane();
		FXMLLoader js = new FXMLLoader(getClass().getResource("library_container.fxml"));
		js.setController(this);
		js.setRoot(kj);
		try {
			js.load();}
		catch(IOException s) {}		
		
		setMinWidth(200);
		setMinHeight(250);
		setCenter(kj);
	}
}