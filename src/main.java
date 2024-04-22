import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class main extends JFrame {

	
	
	JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel p2 = new JPanel(new GridLayout(0, 2));
	
	JLabel l1 = new JLabel("��¥ ����");
	JLabel l2 = new JLabel("������/ȸ�� ����");
	
	static JTextField t1 = new JTextField();
	
	JButton b1 = new JButton("�޷�");
	JButton b2 = new JButton("������");
	JButton b3 = new JButton("ȸ��");
	
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	
	static String m_date = "2021-01-01";
	
	public main() {
		setTitle("��ȭ ���� ���α׷�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		set(); 
		addaction();
		t1.setText("");
		setSize(400, 350);
		setVisible(true);
	}
	
	private void addaction() {
		// TODO Auto-generated method stub
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new calender();
			}
		});
		
		b2.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��¥�� �������ּ���.", "�޼���", JOptionPane.ERROR_MESSAGE);
				}else {
					dispose();
					new admin_main();
				}
			}
		});
		
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��¥�� �������ּ���.", "�޼���", JOptionPane.ERROR_MESSAGE);
				}else {
					dispose();
					new member_login();
				}
			}
		});

	}

	private void set() {
		// TODO Auto-generated method stub
		
		t1.setEditable(false);
		input_advance.d = t1.getText();
		
		l1.setPreferredSize(new Dimension(100,30));
		l2.setPreferredSize(new Dimension(100,30));
		
		t1.setPreferredSize(new Dimension(150,30));
		p1.setPreferredSize(new Dimension(380,80));
		p2.setPreferredSize(new Dimension(400,200));
		
		p1.add(l1);p1.add(t1);p1.add(b1);
		p1.add(l2);
		p2.add(b2);p2.add(b3);
		add(p1);add(p2);
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//new smain();
		new main();
	}

}
