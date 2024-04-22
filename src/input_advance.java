import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class input_advance extends JFrame{

	String label[] = {"영화번호","상영관번호","결제방법","결제상태","결제금액","회원아이디","결제일자"};
	JLabel l[] = new JLabel[7];
	JTextField t[] = new JTextField[7];

	JButton b1 = new JButton("저장");
	JButton b2 = new JButton("취소");
	
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	static String d = "2021-01-01";
	int flag=0;
	
	public input_advance() {
		setTitle("예매정보 등록");
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
						
						mgr.sql = "select movie_no from movie where movie_no = "+t[0].getText()+";";
						list = mgr.jo1();
						if(list.size()>0) {
							mgr.sql = "select screen_no from screen where screen_no = "+t[1].getText()+";";
							list = mgr.jo1();
							if(list.size()>0) {
								
								if (!t[0].getText().matches("^[0-9]*$")||!t[1].getText().matches("^[0-9]*$")){
									JOptionPane.showMessageDialog(null, "영화번호 또는 상영관번호는 숫자로만 입력해주세요","메시지",JOptionPane.ERROR_MESSAGE);
								}else if(!t[2].getText().equals("현금")&&!t[2].getText().equals("카드")) {
									JOptionPane.showMessageDialog(null, "결제방법은 현금 또는 카드로 입력해주세요","메시지",JOptionPane.ERROR_MESSAGE);
								}else if(!t[3].getText().equals("Y")&&!t[3].getText().equals("N")) {
									JOptionPane.showMessageDialog(null, "결제상태는 'Y' 또는 'N'으로 입력해주세요","메시지",JOptionPane.ERROR_MESSAGE);
								}else if(!t[4].getText().matches("^[0-9]*$")){
										JOptionPane.showMessageDialog(null, "결제금액은 숫자로만 입력해주세요","메시지",JOptionPane.ERROR_MESSAGE);
								}else {
									
									mgr.sql = "select * from member where member_id = '"+t[5].getText()+"';";
									list = mgr.jo5();
									if(list.size()>0) {
										
										try {
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
											SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				                             
											Date date=null, date2=null, date3=null;
				                           
											sdf.setLenient(false);
											sdf.parse(t[6].getText());
											
											date = new Date(dateFormat.parse(t[6].getText()).getTime());
											date2 = new Date(dateFormat.parse("2021-01-01").getTime());
				                            date3 = new Date(dateFormat.parse("2021-12-31").getTime());
				                            
				                            if(!(date.compareTo(date2) >= 0 && date.compareTo(date3) <= 0)) {
				                              JOptionPane.showMessageDialog(null, "2021-01-01~2021-12-31 사이에서만 날짜 입력가능합니다.", "메세지", JOptionPane.ERROR_MESSAGE);                          
				                            }else {
				                            	mgr.sql = "insert into advance(movie_no,screen_no,payment_option,payment_state,price,member_id,payment_date)"
				                            			+ "values("+t[0].getText()+","+t[1].getText()+", "
				                            			+ "'"+t[2].getText()+"','"+t[3].getText()+"',"+t[4].getText()+",'"+t[5].getText()+"','"+d+"')";
				                            	
				                            	mgr.update();
				                            	JOptionPane.showMessageDialog(null, "예매 입력 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
				                            	dispose();
				                            	admin_main.re7();
				                            }
										} catch (Exception e2) {
											// TODO: handle exception
											JOptionPane.showMessageDialog(null, "날짜 형식에 맞지 않습니다. 다시 입력해주세요.","메시지",JOptionPane.ERROR_MESSAGE);
				                            
										}
										
									}else {
										JOptionPane.showMessageDialog(null, "존재하지 않는 회원 아이디입니다.","메시지",JOptionPane.ERROR_MESSAGE);
									}
									
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "존재하지 않는 상영관 번호입니다. 다시 입력해주세요.","메시지",JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "존재하지 않는 영화 번호입니다. 다시 입력해주세요.","메시지",JOptionPane.ERROR_MESSAGE);
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
			
			add(b1);add(b2);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new input_advance();
	}

}
