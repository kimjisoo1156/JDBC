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

	String label[] = {"��ȭ��ȣ","�󿵰���ȣ","�������","��������","�����ݾ�","ȸ�����̵�","��������"};
	JLabel l[] = new JLabel[7];
	JTextField t[] = new JTextField[7];

	JButton b1 = new JButton("����");
	JButton b2 = new JButton("���");
	
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	static String d = "2021-01-01";
	int flag=0;
	
	public input_advance() {
		setTitle("�������� ���");
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
						JOptionPane.showMessageDialog(null, "��� �Է����ּ��� ","�޽���",JOptionPane.ERROR_MESSAGE);
					}else {
						
						mgr.sql = "select movie_no from movie where movie_no = "+t[0].getText()+";";
						list = mgr.jo1();
						if(list.size()>0) {
							mgr.sql = "select screen_no from screen where screen_no = "+t[1].getText()+";";
							list = mgr.jo1();
							if(list.size()>0) {
								
								if (!t[0].getText().matches("^[0-9]*$")||!t[1].getText().matches("^[0-9]*$")){
									JOptionPane.showMessageDialog(null, "��ȭ��ȣ �Ǵ� �󿵰���ȣ�� ���ڷθ� �Է����ּ���","�޽���",JOptionPane.ERROR_MESSAGE);
								}else if(!t[2].getText().equals("����")&&!t[2].getText().equals("ī��")) {
									JOptionPane.showMessageDialog(null, "��������� ���� �Ǵ� ī��� �Է����ּ���","�޽���",JOptionPane.ERROR_MESSAGE);
								}else if(!t[3].getText().equals("Y")&&!t[3].getText().equals("N")) {
									JOptionPane.showMessageDialog(null, "�������´� 'Y' �Ǵ� 'N'���� �Է����ּ���","�޽���",JOptionPane.ERROR_MESSAGE);
								}else if(!t[4].getText().matches("^[0-9]*$")){
										JOptionPane.showMessageDialog(null, "�����ݾ��� ���ڷθ� �Է����ּ���","�޽���",JOptionPane.ERROR_MESSAGE);
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
				                              JOptionPane.showMessageDialog(null, "2021-01-01~2021-12-31 ���̿����� ��¥ �Է°����մϴ�.", "�޼���", JOptionPane.ERROR_MESSAGE);                          
				                            }else {
				                            	mgr.sql = "insert into advance(movie_no,screen_no,payment_option,payment_state,price,member_id,payment_date)"
				                            			+ "values("+t[0].getText()+","+t[1].getText()+", "
				                            			+ "'"+t[2].getText()+"','"+t[3].getText()+"',"+t[4].getText()+",'"+t[5].getText()+"','"+d+"')";
				                            	
				                            	mgr.update();
				                            	JOptionPane.showMessageDialog(null, "���� �Է� �Ϸ�Ǿ����ϴ�.","�޽���",JOptionPane.INFORMATION_MESSAGE);
				                            	dispose();
				                            	admin_main.re7();
				                            }
										} catch (Exception e2) {
											// TODO: handle exception
											JOptionPane.showMessageDialog(null, "��¥ ���Ŀ� ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
				                            
										}
										
									}else {
										JOptionPane.showMessageDialog(null, "�������� �ʴ� ȸ�� ���̵��Դϴ�.","�޽���",JOptionPane.ERROR_MESSAGE);
									}
									
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "�������� �ʴ� �󿵰� ��ȣ�Դϴ�. �ٽ� �Է����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "�������� �ʴ� ��ȭ ��ȣ�Դϴ�. �ٽ� �Է����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
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
