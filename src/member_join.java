
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class member_join extends JFrame {
	
	JPanel p1 = new JPanel();
	
	String col[] = {"아이디","비밀번호","이름","전화번호","-","-","이메일 주소","@"};
	JLabel lb[] = new JLabel[9];
	JTextField tf[] = new JTextField[8];
	
	JComboBox cb = new JComboBox(new String[] {"naver.com","daum.net","gmail.com"});
	
	String col2[] =  {"회원가입","취소"};
	JButton bt[] = new JButton[2];
	
	ArrayList<Bean>list,list1;
	DBMgr mgr = new DBMgr();
	Bean bean,bean1;
	
	public member_join() {
		
		setTitle("회원가입");
		setLayout(null);
		
		for (int i = 0; i < lb.length-1; i++) {
			lb[i] = new JLabel(col[i]);
			lb[i].setPreferredSize(new Dimension(80,30));
		}

		lb[4].setPreferredSize(new Dimension(10,30));
		lb[5].setPreferredSize(new Dimension(10,30));
		lb[7].setPreferredSize(new Dimension(20,30));
		
		for (int i = 0; i < tf.length-1; i++) {
			tf[i] = new JTextField();
			tf[i].setPreferredSize(new Dimension(190,30));
		}
		tf[3].setPreferredSize(new Dimension(50,30));
		tf[4].setPreferredSize(new Dimension(50,30));
		tf[5].setPreferredSize(new Dimension(50,30));
		tf[6].setPreferredSize(new Dimension(70,30));
		
		p1.add(lb[0]);p1.add(tf[0]);
		p1.add(lb[1]);p1.add(tf[1]);
		p1.add(lb[2]);p1.add(tf[2]);
		p1.add(lb[3]);p1.add(tf[3]);
		p1.add(lb[4]);p1.add(tf[4]);p1.add(lb[5]);p1.add(tf[5]);
		p1.add(lb[6]);p1.add(tf[6]);p1.add(lb[7]);p1.add(cb);
		
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new JButton(col2[i]);
			bt[i].setPreferredSize(new Dimension(120,30));
			p1.add(bt[i]);
		}
						
		p1.setBounds(10, 10, 310, 300);
		add(p1);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(350, 300);
		
		bt[0].addActionListener((e)->{
			
			int ok = 0;
			for (int i = 0; i < tf.length-1; i++) {
				if(tf[i].getText().equals("")) {
					ok=1;
				}
			}
			
			if(ok==1) {
				JOptionPane.showMessageDialog(null, "공백이 존재합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
			}else {

				mgr.sql = "select * from member where member_id = '"+tf[0].getText()+"' ;";
				list = mgr.jo5();
				
				if(list.size()>0) {
					
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.","메시지",JOptionPane.ERROR_MESSAGE);
					
				}else if(tf[1].getText().matches("^[0-9]*$")&&tf[3].getText().matches("^[0-9]*$")&&tf[4].getText().matches("^[0-9]*$")&&tf[5].getText().matches("^[0-9]*$")) {
					
						mgr.sql = "insert into member(member_id,member_pw,member_name,member_phone,member_email)"
								+ "values('"+tf[0].getText()+"','"+tf[1].getText()+"','"+tf[2].getText()+"','"+tf[3].getText()+"-"+tf[4].getText()+"-"+tf[5].getText()+"','"+tf[6].getText()+"@"+cb.getSelectedItem()+"');";
						mgr.update();
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new member_login();
				}else {

					JOptionPane.showMessageDialog(null, "비밀번호와 전화번호는 숫자로 입력해야 합니다.","메시지",JOptionPane.ERROR_MESSAGE);	
				}
					
					
			}
			
		});
		bt[1].addActionListener((e)->{
			dispose();
			new member_login();
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new member_join();
	}

}

