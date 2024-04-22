import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class member_login extends JFrame {

	JPanel p1 = new JPanel();
	JLabel l1 = new JLabel("영화 예매 프로그램",JLabel.CENTER);
	JLabel l2 = new JLabel("ID: ");
	JLabel l3 = new JLabel("PW: ");
	
	JTextField t1 = new JTextField();
	JPasswordField t2 = new JPasswordField();
	
	JButton b1 = new JButton("로그인");
	JButton b2 = new JButton("회원가입");
	JButton b3 = new JButton("로그아웃");
	
	static String id = "abc001";
	
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	
	public member_login() {
		setTitle("회원 로그인");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		set();
		setSize(400, 200);
	}
	
	private void set() {
		// TODO Auto-generated method stub
		
		l1.setFont(new Font("맑은 고딕", 1, 30));
		t2.setEchoChar('●');
		
		p1.setBounds(0, 0, 340, 240);
		l1.setBounds(20, 5, 340, 40);
		l2.setBounds(38, 60, 40, 30);
		l3.setBounds(30, 80, 40, 30);
		
		t1.setBounds(80, 60, 200, 20);
		t2.setBounds(80, 90, 200, 20);
		
		b1.setBounds(290, 60, 75, 50);
		b2.setBounds(80, 120, 90, 30);
		b3.setBounds(190, 120, 90, 30);
		
		add(l1);
		add(l2);
		add(l3);
		add(t1);
		add(t2);
		add(b1);
		add(b2);
		add(b3);
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (t1.getText().equals("")||t2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.","메시지",JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						mgr.sql="select * from member where member_id = '"+t1.getText()+"' and member_pw ='"+t2.getText()+"';";
						list = mgr.jo5(); //	열개수가 5
						bean = list.get(0);
						
						id = bean.getA();
						JOptionPane.showMessageDialog(null, bean.getC()+"님 환영합니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
						//A는 첫번째
						
						dispose();
						new member_main();
						
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 일치하지 않습니다.","메시지",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
			
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new member_join();
			}
		});
		
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new main();
			}
		});
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new member_login();
	}

}
