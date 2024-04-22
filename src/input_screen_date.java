import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class input_screen_date extends JFrame{

	String label[] = {"��ȭ��ȣ","�󿵰���ȣ","�󿵽�����","��ȸ��","�󿵽��۽ð�"};  //��ȸ���� �ڵ��� ���� ...�ٲ����.
	JLabel l[] = new JLabel[5];
	JTextField t[] = new JTextField[5];

	JButton b1 = new JButton("����");
	JButton b2 = new JButton("���");
	String week[]  = {"��","��","ȭ","��","��","��","��"};
	
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	Date d1, d2;
	
	ArrayList<Bean>list;
	DBMgr mgr = new DBMgr();
	Bean bean;
	int flag=0;
			
	public input_screen_date() {
		
		setTitle("������ ���");
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
						JOptionPane.showMessageDialog(null, "��� �Է����ּ��� ","�޽���",JOptionPane.ERROR_MESSAGE);
					}else {
						if (!t[0].getText().matches("^[0-9]*$")||!t[1].getText().matches("^[0-9]*$") ||!t[3].getText().matches("^[0-9]*$")){
							JOptionPane.showMessageDialog(null, "��ȣ �Ǵ� ȸ���� ���ڷθ� �Է����ּ���","�޽���",JOptionPane.ERROR_MESSAGE);
						}else {
							
							mgr.sql = "select movie_no from movie where movie_no = "+t[0].getText()+";";
							list = mgr.jo1();
							if(list.size()>0) {
								mgr.sql = "select screen_no from screen where screen_no = "+t[1].getText()+";";
								list = mgr.jo1();
								if(list.size()>0) {
									

									try {
				                        
				                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				                           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				                             
				                           Date date=null, date2=null, date3=null;
				                           
				                           sdf.setLenient(false);
				                           sdf.parse(t[2].getText());
				                           date = new Date(dateFormat.parse(t[2].getText()).getTime());
				                           date2 = new Date(dateFormat.parse("2021-01-01").getTime());
				                            date3 = new Date(dateFormat.parse("2021-12-31").getTime());
				                           
				                          
				                           
				                           if(!(date.compareTo(date2) >= 0 && date.compareTo(date3) <= 0)) {
				                              
				                              JOptionPane.showMessageDialog(null, "2021-01-01~2021-12-31 ���̿����� ��¥ �Է°����մϴ�.", "�޼���", JOptionPane.ERROR_MESSAGE);                          
				                           }else {
				                           
				                                
				                              try {
				                                 d1 = sf.parse(t[2].getText());
				                                 int n = d1.getDay();
				                                 
				                                 mgr.sql = "insert into screen_date(movie_no,screen_no,screen_date,screen_week,screen_count,screen_time)"
				                                       + "values("+t[0].getText()+","+t[1].getText()+", "
				                                             + "'"+t[2].getText()+"','"+week[n]+"',"+t[3].getText()+",'"+t[4].getText()+"');";
				                                 
				                                // System.out.println(mgr.sql);
				                                 mgr.update();
				                                 
				                                 JOptionPane.showMessageDialog(null, "������ �Է� �Ϸ�Ǿ����ϴ�.","�޽���",JOptionPane.INFORMATION_MESSAGE);
				                                 admin_main.re2();
				                                 
				                                 dispose();
				                                 
				                                 
				                              } catch (ParseException e1) {
				                                 // TODO Auto-generated catch block
				                                 
				                              }
				                              
				                           }
				                        } catch(ParseException e1) {
				                           JOptionPane.showMessageDialog(null, "��¥ ���Ŀ� ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
				                            
				                        }
									
								}else {
									JOptionPane.showMessageDialog(null, "�������� �ʴ� �󿵰� ��ȣ�Դϴ�. �ٽ� �Է����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "�������� �ʴ� ��ȭ ��ȣ�Դϴ�. �ٽ� �Է����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
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
			
			add(b1);add(b2);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new input_screen_date();
	}

}
