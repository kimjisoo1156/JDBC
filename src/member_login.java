import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class member_login extends JFrame {

	JPanel p1 = new JPanel();
	JLabel l1 = new JLabel("��ȭ ���� ���α׷�",JLabel.CENTER);
	JLabel l2 = new JLabel("ID: ");
	JLabel l3 = new JLabel("PW: ");
	
	JTextField t1 = new JTextField();
	JPasswordField t2 = new JPasswordField();
	
	JButton b1 = new JButton("�α���");
	JButton b2 = new JButton("ȸ������");
	JButton b3 = new JButton("�α׾ƿ�");
	
	static String id = "abc001";
	
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	
	public member_login() {
		setTitle("ȸ�� �α���");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		set();
		setSize(400, 200);
	}
	
	private void set() {
		// TODO Auto-generated method stub
		
		l1.setFont(new Font("���� ���", 1, 30));
		t2.setEchoChar('��');
		
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
					JOptionPane.showMessageDialog(null, "��ĭ�� �����մϴ�.","�޽���",JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						mgr.sql="select * from member where member_id = '"+t1.getText()+"' and member_pw ='"+t2.getText()+"';";
						list = mgr.jo5(); //	�������� 5
						bean = list.get(0);
						
						id = bean.getA();
						JOptionPane.showMessageDialog(null, bean.getC()+"�� ȯ���մϴ�.","�޽���",JOptionPane.INFORMATION_MESSAGE);
						//A�� ù��°
						
						dispose();
						new member_main();
						
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.","�޽���",JOptionPane.ERROR_MESSAGE);
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
