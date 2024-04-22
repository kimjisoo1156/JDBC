import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class input_member extends JFrame{

	String label[] = {"회원아이디","회원비밀번호","고객명","휴대폰번호","전자메일주소"};
	JLabel l[] = new JLabel[5];
	JTextField t[] = new JTextField[5];

	JButton b1 = new JButton("저장");
	JButton b2 = new JButton("취소");
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	
	int flag=0;
	
	public input_member() {
		setTitle("회원고객 등록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(new NorthPanel(), BorderLayout.NORTH);
		c.add(new CenterPanel(), BorderLayout.CENTER);
		
		setVisible(true);
		setSize(400, 450);
	}
	class NorthPanel extends JPanel {
		public NorthPanel() {
			GridLayout grid = new GridLayout(5,2);
			grid.setVgap(10);
			setLayout(grid);
			
			for (int i = 0; i <5; i++) {
				
				l[i] = new JLabel(label[i]);
				add(l[i]);
				t[i] = new JTextField();
				add(t[i]);	
			}
		}
	}
	class CenterPanel extends JPanel {
		public CenterPanel() {
			setLayout(new FlowLayout());
			
			b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					flag = 0;
					for (int i = 0; i < t.length; i++) {
						
						if(t[i].getText().equals("")) {
							flag = 1;
						}
					}
					
					if (flag==1) {
						JOptionPane.showMessageDialog(null, "모두 입력해주세요 ","메시지",JOptionPane.ERROR_MESSAGE);
					}else {

						mgr.sql="select * from member where member_id = '"+t[0].getText()+"';";
						list = mgr.jo5(); 
						if(list.size()>0) {
							JOptionPane.showMessageDialog(null, "중복된 아이디입니다.","메시지",JOptionPane.ERROR_MESSAGE);
							
						}else {
							
							if(!t[1].getText().matches("^[0-9]*$")) {
								JOptionPane.showMessageDialog(null, "비밀번호 숫자로만 입력해주세요","메시지",JOptionPane.ERROR_MESSAGE);
							}else if(!t[3].getText().matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) {
								JOptionPane.showMessageDialog(null, "전화번호 형식이 틀립니다. ","메시지",JOptionPane.ERROR_MESSAGE);
							}else if(!t[4].getText().matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$")){
								JOptionPane.showMessageDialog(null, "이메일 형식이 틀립니다. ","메시지",JOptionPane.ERROR_MESSAGE);
							}else {
								mgr.sql = "insert into member(member_id,member_pw,member_name,member_phone,member_email)"
										+ "values('"+t[0].getText()+"',"+t[1].getText()+", "
										+ "'"+t[2].getText()+"','"+t[3].getText()+"','"+t[4].getText()+"')";
								
								mgr.update();
								
								JOptionPane.showMessageDialog(null, "회원 입력 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
								dispose();
								admin_main.re6();
							}
						}
					}
					
				}
			});
			b2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
			});
			
			add(b1);
			add(b2);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new input_member();
	}

}
