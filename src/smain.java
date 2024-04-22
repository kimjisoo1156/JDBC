
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;


public class smain {
   Connection con;
   Statement stmt;
   public smain() throws IOException {
	   String Driver="";
	     String 
	url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul";  //마당데이터베이스 접속하여
	     String userid="madang";
	     String pwd="madang";
	
	     try {
	       Class.forName("com.mysql.cj.jdbc.Driver");   
	     } catch(ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	    
	     try { 
	        con=DriverManager.getConnection(url, userid, pwd);
	        stmt = con.createStatement();
	        stmt.execute("drop database if exists madang"); 
			stmt.execute("create database madang default character set utf8");
			stmt.execute("use madang");
	        
			//테이블 생성
			stmt.execute("create table movie(movie_no int primary key not null auto_increment, movie_name varchar(20), movie_time int, movie_class varchar(10), movie_supervision varchar(20), movie_actor varchar(10),movie_genre varchar(20),movie_date Date, movie_price int,movie_intro varchar(500))");
			stmt.execute("create table screen_date(screen_date_no int primary key not null auto_increment, movie_no int,screen_no int, screen_date Date, screen_week varchar(10), screen_count int , screen_time varchar(30))");
			stmt.execute("create table screen(screen_no int primary key not null auto_increment,seat_count int,screen_state varchar(5))");
			stmt.execute("create table ticket(ticket_no int primary key not null auto_increment,screen_date_no int,screen_no int, seat_no int,advance_no int,ticket_state varchar(5),avg_price int,selling_price int )");
			stmt.execute("create table seat(seat_no int ,screen_no int, seat_state varchar(5))");
			stmt.execute("create table member(member_id varchar(30),member_pw int,member_name varchar(50),member_phone varchar(20),member_email varchar(30))");
			stmt.execute("create table advance(advance_no int primary key not null auto_increment, movie_no int, screen_no int, payment_option varchar(20), payment_state varchar(5),price int,member_id varchar(10),payment_date Date)");
			
			importData(con.prepareStatement("insert into movie values(?,?,?,?,?,?,?,?,?,?)"), "./DB데이터/movie.txt");
			importData(con.prepareStatement("insert into screen_date values(?,?,?,?,?,?,?)"), "./DB데이터/screen_date.txt");
			importData(con.prepareStatement("insert into screen values(?,?,?)"), "./DB데이터/screen.txt");
			importData(con.prepareStatement("insert into ticket values(?,?,?,?,?,?,?,?)"), "./DB데이터/ticket.txt");
			importData(con.prepareStatement("insert into seat values(?,?,?)"), "./DB데이터/seat.txt");
			importData(con.prepareStatement("insert into member values(?,?,?,?,?)"), "./DB데이터/member.txt");
			importData(con.prepareStatement("insert into advance values(?,?,?,?,?,?,?,?)"), "./DB데이터/advance.txt");
	
			stmt.execute("grant select, update, insert, delete on madang.* to 'madang'@localhost");
	       
	     } catch(SQLException e) {
	         e.printStackTrace();
       }

    }
  	  
	   private static void importData(PreparedStatement pst, String path) throws IOException, SQLException {
			// TODO Auto-generated method stub
			List<String>lines = Files.readAllLines(Paths.get(path,new String[0]));
			for (int i = 1; i < lines.size(); i++) {
				String[]split = lines.get(i).split("\t");
				for (int j = 0; j < split.length; j++) {
					pst.setObject(j+1, split[j]);
				}
				pst.executeUpdate();
			}
			
		}
  	  
	public static void main(String args[]) throws IOException {
		new smain();
  	}
}