package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LoginControler extends BorderPane {
	private BorderPane log;
	
	@FXML
	ImageView imguserlogin, imgusersignup, imgexit;
	
	@FXML
	Label labellogin,labelsignup,labelexit;
	
	@FXML
	Button loginbtn, signupbtn, exitbtn;
	
	Effect effectbtn;
	
	ScaleTransition ma, vx, kf;
	protected Source lw;
	
	public LoginControler() throws IOException {
		log = new BorderPane();
		FXMLLoader ha = new FXMLLoader(getClass().getResource("clientlog.fxml"));
		ha.setController(this);
		ha.setRoot(log);
		ha.load();
		
		ma = new ScaleTransition(Duration.millis(500),loginbtn);
		ma.setAutoReverse(false);
		vx = new ScaleTransition(Duration.millis(500),signupbtn);
		vx.setAutoReverse(false);
		kf = new ScaleTransition(Duration.millis(500),exitbtn);
		kf.setAutoReverse(false);
		
		loginbtn.hoverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				boolean rw = false;
				if(arg2) {
					if(rw == false) {
						rw = true;
						ma.stop();
						loginbtn.setEffect(null);
						ColorAdjust lq = (ColorAdjust)imguserlogin.getEffect();
						lq.setBrightness(1);
						imguserlogin.setEffect(lq);
						imguserlogin.setBlendMode(BlendMode.SRC_OVER);
						labellogin.setStyle("-fx-text-fill:white;-fx-background-color:linear-gradient(to left, #03ff00, #00ffde);-fx-background-radius:20 20 20 20;");
						ma.setToX(1.2);
						ma.setToY(1.2);
						ma.play();
					}
				}
				else {
					if(rw == true) {
						rw = false;
						ma.stop();
						loginbtn.setEffect(effectbtn);
						ColorAdjust lq = (ColorAdjust)imguserlogin.getEffect();
						lq.setBrightness(0);
						imguserlogin.setEffect(lq);
						imguserlogin.setBlendMode(BlendMode.SOFT_LIGHT);
						labellogin.setStyle("-fx-text-fill:linear-gradient(to left, #03ff00, #00ffde);-fx-border-color:linear-gradient(to left, #03ff00, #00ffde);-fx-border-radius:20 20 20 20;");
						ma.setToX(1);
						ma.setToY(1);
						ma.play();
					}
				}
			}});
		
		signupbtn.hoverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				boolean rw = false;
				if(arg2) {
					if(rw == false) {
						rw = true;
						vx.stop();
						signupbtn.setEffect(null);
						ColorAdjust lq = (ColorAdjust)imgusersignup.getEffect();
						lq.setBrightness(1);
						imgusersignup.setEffect(lq);
						imgusersignup.setBlendMode(BlendMode.SRC_OVER);
						labelsignup.setStyle("-fx-text-fill:white;-fx-background-color:linear-gradient(to left, #ff7500, #e9ff00);-fx-background-radius:20 20 20 20;");
						vx.setToX(1.2);
						vx.setToY(1.2);
						vx.play();
					}
				}
				else {
					if(rw == true) {
						rw = false;
						vx.stop();
						signupbtn.setEffect(effectbtn);
						ColorAdjust lq = (ColorAdjust)imgusersignup.getEffect();
						lq.setBrightness(0);
						imgusersignup.setEffect(lq);
						imgusersignup.setBlendMode(BlendMode.SOFT_LIGHT);
						labelsignup.setStyle("-fx-text-fill:linear-gradient(to left, #ff7500, #e9ff00);-fx-border-color:linear-gradient(to left, #ff7500, #e9ff00);-fx-border-radius:20 20 20 20;");
						vx.setToX(1);
						vx.setToY(1);
						vx.play();
					}
				}
			}});
		
		exitbtn.hoverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				boolean rw = false;
				if(arg2) {
					if(rw == false) {
						rw = true;
						kf.stop();
						exitbtn.setEffect(null);
						ColorAdjust lq = (ColorAdjust)imgexit.getEffect();
						lq.setBrightness(1);
						imgexit.setEffect(lq);
						imgexit.setBlendMode(BlendMode.SRC_OVER);
						labelexit.setStyle("-fx-text-fill:white;-fx-background-color:linear-gradient(to left, #f90202, #ff43f5);-fx-background-radius:20 20 20 20;");
						kf.setToX(1.2);
						kf.setToY(1.2);
						kf.play();
					}
				}
				else {
					if(rw == true) {
						rw = false;
						kf.stop();
						exitbtn.setEffect(effectbtn);
						ColorAdjust lq = (ColorAdjust)imgexit.getEffect();
						lq.setBrightness(0);
						imgexit.setEffect(lq);
						imgexit.setBlendMode(BlendMode.SOFT_LIGHT);
						labelexit.setStyle("-fx-text-fill:linear-gradient(to left, #f90202, #ff43f5);-fx-border-color:linear-gradient(to left, #f90202, #ff43f5);-fx-border-radius:20 20 20 20;");
						kf.setToX(1);
						kf.setToY(1);
						kf.play();
					}
				}
			}});
		
		effectbtn = loginbtn.getEffect();
		setCenter(log);
	}
	
	@FXML
	private void login_pressed(ActionEvent sa) throws IOException {
		lw = new Source();
		
		Scene js = new Scene(new logininit(this));
		Stage gx = (Stage) ((Node)sa.getSource()).getScene().getWindow();
		gx.setScene(js);
	}
	
	@FXML
	private void signup_pressed(ActionEvent sa) throws IOException {
		lw = new Source();
		
		Scene js = new Scene(new signupinit(this));
		Stage gx = (Stage) ((Node)sa.getSource()).getScene().getWindow();
		gx.setScene(js);
	}
	
	@FXML
	private void exit_pressed(ActionEvent sa) {
		Platform.exit();
	}
}

class logininit extends BorderPane{
	@FXML
	TextField usernamefieldinit;
	
	@FXML
	PasswordField passwordfieldinit;
	
	@FXML
	Button loginuserinitbtn,backloginbtn;
	
	@FXML
	Label loginuserinitbtnvalidity;
	
	private LoginControler lk;
	
	logininit(LoginControler kja){
		lk = kja;
		
		BorderPane log = new BorderPane();
		FXMLLoader ha = new FXMLLoader(getClass().getResource("Loginmenu.fxml"));
		ha.setController(this);
		ha.setRoot(log);
		try {
			ha.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setCenter(log);
		
		loginuserinitbtn.hoverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if(arg2) {
					loginuserinitbtn.setStyle("-fx-background-color:rgba(0.4,0.4,0.4,1);-fx-background-radius:20 20 20 20;");
				}
				else {
					loginuserinitbtn.setStyle("-fx-background-color:rgba(0.4,0.4,0.4,0.4);-fx-background-radius:20 20 20 20;");
				}
			}
		});
		
		backloginbtn.hoverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if(arg2) {
					backloginbtn.setStyle("-fx-background-color:rgba(255,85,0,1);");
				}
				else {
					backloginbtn.setStyle("-fx-background-color:rgba(255,85,0,0.8);");
				}
			}
		});
	}
	
	private boolean kkk = false;
	
	@FXML
	private void loginuserinitbtn_pressed(ActionEvent kj) throws InterruptedException, ClassNotFoundException, SQLException {
		String ha = usernamefieldinit.getText().replaceAll("\\s", "");
		String ye = passwordfieldinit.getText().replaceAll("\\s", "");
		if (ha != "" && ye != "") {
			try {
				kkk = lk.lw.identificationuser(ha, ye);
			} catch (NoSuchAlgorithmException | IllegalStateException | UnsupportedEncodingException
					| InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(kkk == true) {
				loginuserinitbtnvalidity.setText("");
				int ga = lk.lw.getIdUser(ha, ye);
				Scene js = new Scene(new MainMenu(ga, lk.lw.getImage(ha), ha, lk));
				Stage gx = (Stage) ((Node)kj.getSource()).getScene().getWindow();
				gx.setScene(js);
			}
			else {
				loginuserinitbtnvalidity.setText("Can`t Login, maybe username or password does`nt valid");
			}
		}
	}
	
	@FXML
	private void backloginbtn_pressed(ActionEvent kj) throws IOException {
		Scene js = lk.getScene();
		Stage gx = (Stage) ((Node)kj.getSource()).getScene().getWindow();
		gx.setScene(js);
	}
}

class signupinit extends BorderPane{
	public LoginControler ja;
	
	@FXML
	Button imguserinitbtn,signuserinitbtn,signupbackbtn;
	
	@FXML
	TextField usernamefieldsign,emailfieldsign;
	
	@FXML
	PasswordField passwordfieldsign,passwordfieldretypesign;
	
	@FXML
	Label signuserinitbtnvalidity;
	
	private File imgusers;
	
	signupinit(LoginControler k){
		ja = k;
		
		BorderPane log = new BorderPane();
		FXMLLoader ha = new FXMLLoader(getClass().getResource("Signupmenu.fxml"));
		ha.setController(this);
		ha.setRoot(log);
		try {
			ha.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setCenter(log);
		
		signuserinitbtn.hoverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if(arg2) {
					signuserinitbtn.setStyle("-fx-background-color:rgba(0.4,0.4,0.4,1);-fx-background-radius:20 20 20 20;");
				}
				else {
					signuserinitbtn.setStyle("-fx-background-color:rgba(0.4,0.4,0.4,0.4);-fx-background-radius:20 20 20 20;");
				}
			}});
		
		signupbackbtn.hoverProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if(arg2) {
					signupbackbtn.setStyle("-fx-background-color:rgba(255,85,0,1);");
				}
				else {
					signupbackbtn.setStyle("-fx-background-color:rgba(255,85,0,0.8);");
				}
			}});
	}
	
	@FXML
	private void imguserinitbtn_pressed(ActionEvent l) throws IOException{
		FileChooser jwt = new FileChooser();
		jwt.setTitle("Choose your image");
		jwt.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		imgusers = jwt.showOpenDialog(ja.getScene().getWindow());
		if(imgusers != null) {}
		else {
			imgusers = new File(getClass().getResource("Image\\login and signup.png").toExternalForm());
		}
		BufferedImage md = ImageIO.read(imgusers);
		ByteArrayOutputStream kq = new ByteArrayOutputStream();
		ImageIO.write(md, "png", kq);
		InputStream lq = new ByteArrayInputStream(kq.toByteArray());
		ImageView lw = new ImageView();
		lw.setImage(new Image(lq));
		lw.setFitHeight(91.0);
		lw.setFitWidth(109.0);
		imguserinitbtn.setGraphic(lw);
		imguserinitbtn.setText("");
	}
	
	private boolean ms = false;
	
	@FXML
	private void signuserinitbtn_pressed(ActionEvent o) throws SQLException, FileNotFoundException, IOException {
		String ha = usernamefieldsign.getText();
		String ye = emailfieldsign.getText();
		String we = passwordfieldsign.getText();
		String we1 = passwordfieldretypesign.getText();
		if(ha != "" && ye != "" && we != "" && we1 != "") {
			if(we.equals(we1)) {
				if(imgusers == null) {
					imgusers = new File(getClass().getResource("Image\\login and signup.png").toExternalForm());
				}
				BufferedImage md = ImageIO.read(imgusers);
				ByteArrayOutputStream kq = new ByteArrayOutputStream();
				ImageIO.write(md, "png", kq);
				InputStream lq = new ByteArrayInputStream(kq.toByteArray());
				ms = ja.lw.adduser(lq, ha, ye, we);
				if(ms == true) {
					int gas = ja.lw.getIdUser(ha, we);
					signuserinitbtnvalidity.setText("");
					InputStream bv = new ByteArrayInputStream(kq.toByteArray());
					Scene js = new Scene(new MainMenu(gas,new Image(bv), ha, ja));
					Stage gx = (Stage) ((Node)o.getSource()).getScene().getWindow();
					gx.setScene(js);
				}
				else {
					signuserinitbtnvalidity.setText("Please fill in all fields correctly");
				}
			}
			else {
				signuserinitbtnvalidity.setText("This password are not same");
			}
		}
		else {
			signuserinitbtnvalidity.setText("Please fill in all fields correctly");
		}
	}
	
	@FXML
	private void signupbackbtn_pressed(ActionEvent h) {
		Scene lk = ja.getScene();
		Stage mz = (Stage)((Node)h.getSource()).getScene().getWindow();
		mz.setScene(lk);
	}
	
	@FXML
	private void key_typed_passwordfieldretypesign(KeyEvent jh) {
		if (passwordfieldsign.getText().equals(passwordfieldretypesign.getText())) {
			passwordfieldretypesign.setStyle("-fx-background-radius: 0 10 10 0;-fx-border-color:green;-fx-border-radius: 0 10 10 0;-fx-border-width:2;");
		}
		else {
			passwordfieldretypesign.setStyle("-fx-background-radius: 0 10 10 0;-fx-border-color:red;-fx-border-radius: 0 10 10 0;-fx-border-width:2;");
		}
	}
}
