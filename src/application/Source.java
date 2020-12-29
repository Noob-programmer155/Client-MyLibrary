package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javafx.scene.image.Image;

public class Source {
	enum access{
		CLIENT,
		SERVER
	}
	Connection hs;
	public Source() throws UnknownHostException, IOException{
		try {
			setConnections();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setConnections() throws SQLException {
		hs =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Ar14E-65BIdJt#");
	}
	
	protected boolean identificationuser(String username, String password) throws NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException, InterruptedException, ClassNotFoundException {
		String user = new String(username.getBytes(),"UTF-8");
		String pass = new String(password.getBytes(),"UTF-8");
		return UAC(user,pass);
	}
	
	private boolean UAC(String Username, String Password) {
		boolean valid = false;
		try {
		PreparedStatement ja = hs.prepareStatement("select id from users where name = ? and password = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ja.setString(1, Username);
		ja.setString(2, Password);
		valid = true;
		ja.close();}
		catch(SQLException s) {valid = false;}
		return valid;
	}
	
	protected boolean adduser(InputStream imageuser, String username, String email, String password) throws IOException, SQLException {
		PreparedStatement ja = hs.prepareStatement("select count(id) as ids from users", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet gf = ja.executeQuery();
		gf.next();
		int rq = gf.getInt("ids")+1;
		String user = new String(username.getBytes(),"UTF-8");
		String pass = new String(password.getBytes(),"UTF-8");
		String mail = new String(email.getBytes(),"UTF-8");
		gf.close();
		ja.close();
		return UAC(rq,imageuser, user, mail, pass);
	}
	
	private boolean UAC(int k ,InputStream imageuser, String username, String email, String password) {
		boolean valid = false;
		try {
		PreparedStatement ja = hs.prepareStatement("insert into users values(?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ja.setInt(1, k);
		ja.setString(2, username);
		ja.setString(3, email);
		ja.setString(4, password);
		ja.setBlob(5, imageuser);
		ja.setString(6, "WHITELIST");
		ja.setString(7, null);
		ja.executeUpdate();
		valid = true;
		ja.close();}
		catch(SQLException s) {valid = false;}
		return valid;
	}
	
	protected LinkedList<Object> getDataFromconnectionclient(String message, access h, int s) throws SQLException {
		LinkedList<Object> kw = null;
		switch(h) {
		case CLIENT:
			kw = sourceDataBookServer(message);
			break;
		case SERVER:
			kw = sourceDataBookuser(message,s);
		}
		return kw;
	}
	
	private LinkedList<Object> sourceDataBookServer(String message) throws SQLException{
		PreparedStatement ka = hs.prepareStatement("select * from books where book_title = ? or book_author = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ka.setString(1, message);
		ka.setString(2, message);
		ResultSet rs = ka.executeQuery();
		LinkedList<Object> mqw = new LinkedList<>();
		while(rs.next()) {
			Blob j = rs.getBlob(4);
			Object[] k = {rs.getString(1),rs.getString(2),rs.getString(3), j.getBinaryStream()};
			mqw.add(k);
		}
		rs.close();
		ka.close();
		return mqw;
	}
	
	private LinkedList<Object> sourceDataBookuser(String message, int id) throws SQLException{
		PreparedStatement ka = hs.prepareStatement("select books from users where id = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ka.setInt(1, id);
		ResultSet rs = ka.executeQuery();
		LinkedList<Object> mqw = new LinkedList<>();
		if(rs.next()) {
			String ks = rs.getString("books");
			if(!rs.wasNull()) {
				String[] s = ks.split(":");
				rs.close();
				ka.close();
				PreparedStatement lcv = hs.prepareStatement("select * from books where book_title = ? and book_author = ?", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				for (String lc : s) {
					String[] lv = lc.split("<test>");
					if (message.equals(lv[0]) || message.equals(lv[1])) {
						lcv.setString(1, lv[0]);
						lcv.setString(2, lv[1]);
						ResultSet rl = lcv.executeQuery();
						rl.next();
						Object[] k = {rl.getString(1),rl.getString(2),rl.getString(3), rl.getBlob(4).getBinaryStream()};
						mqw.add(k);
						rl.close();}
				}
				lcv.close();
			}
		}
		return mqw;
	}
	
	protected Image getImage(String username) throws SQLException {
		PreparedStatement ka = hs.prepareStatement("select image from users where name = ?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ka.setString(1, username);
		ResultSet jq = ka.executeQuery();
		jq.next();
		Blob j = jq.getBlob("image");
		Image ha = new Image(j.getBinaryStream());
		jq.close();
		ka.close();
		return ha;
	}
	
	protected String getActivity(String j) {
		LocalDateTime p = LocalDateTime.now();
		DateTimeFormatter je = DateTimeFormatter.ofPattern("[hh:mm:ss a;E L uuuu]");
		String ha = p.format(je);
		return ha+" : "+j;
	}
	
	protected LinkedList<Object> getDataBook() throws SQLException{
		return sourceDataBook();
	}
	
	private LinkedList<Object> sourceDataBook() throws SQLException{
		Statement ka = hs.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet da = ka.executeQuery("select * from books");
		LinkedList<Object> mn = new LinkedList<>();
		while(da.next()) {
			Blob j = da.getBlob(4);
			Object[] k = {da.getString(1),da.getString(2),da.getString(3), j.getBinaryStream()};
			mn.add(k);
		}
		da.close();
		ka.close();
		return mn;
	}
	
	protected int getIdUser(String username, String password) throws SQLException {
		return sourceIdUser(username,password);
	}
	
	private int sourceIdUser(String username, String password) throws SQLException {
		PreparedStatement ka = hs.prepareStatement("select id from users where name = ? and password = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ka.setString(1, username);
		ka.setString(2, password);
		ResultSet hw = ka.executeQuery();
		int s = 0;
		if (hw.next()) {
			s = hw.getInt("id");
		}
		hw.close();
		ka.close();
		return s;
	}
	
	protected LinkedList<Object> getDataBookUser(int i) throws SQLException{
		return sourceDataBookUser(i);
	}
	
	private LinkedList<Object> sourceDataBookUser(int i) throws SQLException{
		Statement ka = hs.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet da = ka.executeQuery("select books from users where id="+i);
		LinkedList<Object> mn = new LinkedList<>();
		if(da.next()) {
			String as = da.getString("books");
			if(da.wasNull()) {
				da.close();
			}
			else {
				String[] fa = as.split(":");
				da.close();
				for(String ga : fa) {
					String[] wq = ga.split("<test>");
					ResultSet da1 = ka.executeQuery("select * from books where book_title= '"+wq[0]+"' and book_author= '"+wq[1]+"'");
					da1.next();
					try {
					Object[] k = {da1.getString(1),da1.getString(2),da1.getString(3), da1.getBlob(4).getBinaryStream()};mn.add(k);}
					catch(SQLException k) {
						System.out.println("Error on String Location = "+ga);
						System.out.println("Error on main String = "+fa[0]);
						System.out.println(wq[0] +"-----"+wq[1]);
					}
					da1.close();
				}
				ka.close();
			}
		}
		return mn;
	}
	
	protected boolean setBuyBookUser(int i, String title, String author) {
		boolean r = false;
		try {
		Statement ka = hs.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		String sa = title+"<test>"+author;
		ResultSet jhw = ka.executeQuery("select books from users where id ="+i);
		if(jhw.next()) {
			String sd = jhw.getString("books");
			if(jhw.wasNull()) {
				jhw.close();
				ka.executeLargeUpdate("update users set books = '"+sa+"' where id = "+i);
			}
			else {
				jhw.close();
				ka.executeLargeUpdate("update users set books = '"+sd+":"+sa+"' where id = "+i);
			}
		}
		jhw.close();
		ka.close();
		r = true;
		}
		catch(SQLException k) { r = false; k.printStackTrace();}
		return r;
	}
	
	protected LinkedList<Object> getDataUser(int i) throws SQLException{
		return getDataUserSource(i);
	}
	
	private LinkedList<Object> getDataUserSource(int i) throws SQLException{
		Statement ka = hs.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet pq = ka.executeQuery("select * from users where id="+i);
		LinkedList<Object> mn = new LinkedList<>();
		if(pq.next()) {
			Blob h = pq.getBlob(5);
			Object[] ss = {pq.getString(2),pq.getString(3),h.getBinaryStream()};
			mn.add(ss);
		}
		pq.close();
		ka.close();
		return mn;
	}
	
	protected void disconnect() {
		try {
			hs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}