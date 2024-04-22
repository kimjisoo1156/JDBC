import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class input_ticket extends JFrame{

	String label[] = {"상영일정번호","상영관번호","좌석번호","예매번호","발권여부","표준가격","판매가격"};
	JLabel l[] = new JLabel[7];
	JTextField t[] = new JTextField[7];

	JButton b1 = new JButton("저장");
	JButton b2 = new JButton("취소");
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	int flag=0;
	
	public input_ticket() {

		setTitle("티켓 등록");
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
			GridLayout grid = new GridLayout(7,2);
			grid.setVgap(10);
			setLayout(grid);
			
			for (int i = 0; i <7; i++) {
				
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
						if (!t[0].getText().matches("^[0-9]*$")||!t[1].getText().matches("^[0-9]*$")||!t[2].getText().matches("^[0-9]*$")||!t[3].getText().matches("^[0-9]*$")||!t[5].getText().matches("^[0-9]*$")||!t[6].getText().matches("^[0-9]*$")) {
							JOptionPane.showMessageDialog(null, "번호 또는 가격은 숫자로만 입력해주세요","메시지",JOptionPane.ERROR_MESSAGE);
						}else {
							
								mgr.sql = "select screen_no from screen where screen_no = "+t[1].getText()+";";
								list = mgr.jo1();
								if(list.size()>0) {
									
									if(!t[4].getText().equals("Y")&&!t[4].getText().equals("N")) {
										JOptionPane.showMessageDialog(null, "발권 여부는 'Y' 또는 'N'으로 입력해주세요","메시지",JOptionPane.ERROR_MESSAGE);
									}else {
										mgr.sql = "insert into ticket(screen_date_no,screen_no,seat_no,advance_no,ticket_state,avg_price,selling_price)"
												+ "values("+t[0].getText()+","+t[1].getText()+", "
														+ ""+t[2].getText()+","+t[3].getText()+",'"+t[4].getText()+"',"+t[5].getText()+","
																+ ""+t[6].getText()+")";
										
										System.out.println(mgr.sql);
										mgr.update();
										
										
										JOptionPane.showMessageDialog(null, "티켓 입력 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
										dispose();
										admin_main.re4();
									}
								}else {
									JOptionPane.showMessageDialog(null, "존재하지 않는 상영관 번호입니다. 다시 입력해주세요.","메시지",JOptionPane.ERROR_MESSAGE);
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
		
			add(b1);	add(b2);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new input_ticket();
	}

}
