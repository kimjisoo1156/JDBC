import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class all_delete extends JFrame{

	JLabel l1 = new JLabel("�����ϰ� ���� ���ǽ�(�Ӽ��̸� ���迬���� �� ����)�� �Է����ֽñ� �ٶ��ϴ�. ��) movie_name = \"���˵���2\"");
	
	JLabel l2 = new JLabel("<html>���̺� �Ӽ��̸��� �Ʒ� �޺��ڽ��� Ŭ���ϸ� GUI ȭ�鿡 ������ ���ɴϴ�.<br>"+ " ���̺�Ӽ��̸��� �ش��ϴ� ���̺� ���Ͽ� ������ ���� �մϴ�.");

	JTextField tf = new JTextField();
	JComboBox<String> table_name = new JComboBox<String>();
	
	String name[] = {"��ȭ ���̺� �Ӽ� �̸� ����","������ ���̺� �Ӽ� �̸� ����","�󿵰� ���̺� �Ӽ� �̸� ����","Ƽ�� ���̺� �Ӽ� �̸� ����","�¼� ���̺� �Ӽ� �̸� ����","ȸ���� ���̺� �Ӽ� �̸� ����","�������� ���̺� �Ӽ� �̸� ����",};
	String t[] = {"movie","screen_date","screen","ticket","seat","member","advance"};
	
	JPanel p1 = new JPanel();
	JLabel l3 = new JLabel();
	JButton btn = new JButton("����");
	
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	
	//movie_no=1 and screen_date_no = 23 �̰� ������ �ȵǳ�.
	
	
	//�����ϰ� ���� ���ǽ�(�Ӽ��̸� ���迬���� �� ����)�� �Է����ֽñ� �ٶ��ϴ�. ��)movie_name = "���˵���2" 
	//���̺� �Ӽ��̸��� [] �̷����ϴ�.
	//���̺� ���� ��ü ���̺� �Ӽ��� ���������.
	
	public all_delete() {
		
		setTitle("����");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		l1.setBounds(0, 0, 650, 30);
		l2.setBounds(0, 40, 650, 40);
		
		for (int i = 0; i < name.length; i++) {
			table_name.addItem(name[i]);
		}
		table_name.setBounds(0, 80, 300, 30);
		p1.setBounds(0, 120, 650, 180);
		tf.setBounds(150, 300, 150, 30);
		table_name.setSelectedIndex(-1);
		btn.setBounds(350, 300, 100, 30);
		
		
		table_name.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange()==ItemEvent.SELECTED) {
					
					p1.removeAll();  //�г� �ʱ�ȭ
					p1.revalidate();
					p1.repaint();
					
					
					int n = table_name.getSelectedIndex();
					
					switch (n) {  //�ٹٲ� �ؼ� ��������
					
					case 0: l3.setText("<html>movie_no, movie_name, movie_time, movie_class, movie_supervision, movie_actor, <br>"+ "movie_genre, movie_date, movie_price, movie_intro"); break;
					case 1: l3.setText("<html>screen_date_no, movie_no, screen_no, screen_date, screen_week,<br>"+ "screen_enddate, screen_endweek, screen_count, screen_time");break;
					case 2: l3.setText("<html>screen_no, seat_count, screen_state");break;
					case 3: l3.setText("<html>ticket_no, screen_date_no, screen_no,seat_no,advance_no,<br>"+ "ticket_state,avg_price,selling_price");break;
					case 4: l3.setText("<html>seat_no, screen_no, seat_state");break;
					case 5: l3.setText("<html>member_id, member_pw, member_name, member_phone, member_email");break;
					case 6: l3.setText("<html>advance_no,movie_no, screen_no, payment_option,<br>"+ " payment_state, price, member_id, payment_date");break;
					
					}
					
					
					p1.add(l3);
					
					
					
				}
				
			}
		});
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tf.getText().equals("")) {
					 JOptionPane.showMessageDialog(null, "�����ϰ� ������ ���ǽ��� �Է����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
				}else if(table_name.getSelectedIndex()==-1){
					 JOptionPane.showMessageDialog(null, "�޺��ڽ��� ������ ���̺� �Ӽ��� ���� Ȯ�����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
					
				}else {
					

		               int k = table_name.getSelectedIndex();
		               int c = 0;
		               
		               mgr.sql = "select * from "+t[k]+" where "+tf.getText()+";";
		               
		              // System.out.println(mgr.sql);
		               switch (k) {
		               
		               case 0: list=mgr.jo10(); break;
		               case 1: list=mgr.jo7(); break;
		               case 2: list=mgr.jo3(); break;
		               case 3: list=mgr.jo8(); break;
		               case 4: list=mgr.jo3(); break;
		               case 5: list=mgr.jo5();break;
		               case 6: list=mgr.jo8();break;
		               
		               }

		               if (list.size()>0) {
		                  
		                  mgr.sql = "delete from "+t[k]+" where "+tf.getText()+";";
		                  mgr.update();
		                  
		                  JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.","�޽���",JOptionPane.INFORMATION_MESSAGE);
		                  
		                  switch (k) {
		                  
		                  case 0: dispose(); admin_main.re1(); break;
		                  case 1: dispose(); admin_main.re2(); break;
		                  case 2: dispose(); admin_main.re3(); break;
		                  case 3: dispose(); admin_main.re4(); break;
		                  case 4: dispose(); admin_main.re5(); break;
		                  case 5: dispose(); admin_main.re6();break;
		                  case 6: dispose(); admin_main.re7();break;
		                  
		                  }
		               }else {
		                  JOptionPane.showMessageDialog(null, "�޺��ڽ��� �ٽ� �����ϰų� ���̺� �ش��ϴ� �Ӽ��̸��� ����Ͽ� ���ǽ��� �ۼ����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
		               }
		               
					
				}
			}
		});
		add(l1);
		add(l2);
		add(table_name);
		add(p1);
		add(tf); add(btn);
		setVisible(true);
		setSize(700, 400);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new all_delete();
	}

}
