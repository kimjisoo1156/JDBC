import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class input_movie extends JFrame{

	
	JLabel l[] = new JLabel[9];
	JTextField t[] = new JTextField[9];
	
	String label[] = {"��ȭ��: ","�󿵽ð�: ","�󿵵��: ","������: ","����: ","�帣: ","������: ","����: ","��ȭ�Ұ�: "};
	
	JButton b1 = new JButton("����");
	JButton b2 = new JButton("���");
	ArrayList<Bean>list,list2;
	DBMgr mgr = new DBMgr();
	Bean bean,bean2;
	int flag=0;
	
	public input_movie() {
		
		setTitle("��ȭ ���");
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
			GridLayout grid = new GridLayout(9,2);
			grid.setVgap(10);
			setLayout(grid);
			
			for (int i = 0; i < 9; i++) {
				
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
						mgr.sql = "select movie_name from movie where movie_name ='"+t[0].getText()+"';";
						list2= mgr.jo1();
						
						if (list2.size()>0) {
							JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ��ȭ�Դϴ�.","�޽���",JOptionPane.ERROR_MESSAGE);
						}else if(!t[1].getText().matches("^[0-9]*$")){
							JOptionPane.showMessageDialog(null, "�󿵽ð��� ���ڷθ� �Է����ּ���","�޽���",JOptionPane.ERROR_MESSAGE);
						}else if(!(t[2].getText().equals("��ü"))&&!(t[2].getText().equals("15"))&&!(t[2].getText().equals("19"))&&!(t[2].getText().equals("12"))){
							JOptionPane.showMessageDialog(null, "�󿵵���� 12,15,19,��ü �߿����� �Է��ϼ��� ","�޽���",JOptionPane.ERROR_MESSAGE);
						}else {
							try {
						         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						         sdf.setLenient(false);
						         sdf.parse(t[6].getText());
						         
						         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						     	
						     	 Date date, date2, date3;
						     	
						         date = new Date(dateFormat.parse(t[6].getText()).getTime());
					             date2 = new Date(dateFormat.parse("2021-01-01").getTime());
					             date3 = new Date(dateFormat.parse("2021-12-31").getTime());
					             
					             //System.out.println(date.compareTo(date2));
					             //System.out.println(date.compareTo(date3));
					             
					             
						         if(!(date.compareTo(date2) >= 0 && date.compareTo(date3) <= 0)) {
										JOptionPane.showMessageDialog(null, "2021-01-01~2021-12-31 ���̿����� ��¥ �Է°����մϴ�.", "�޼���", JOptionPane.ERROR_MESSAGE);
						         }else if(!t[7].getText().matches("^[0-9]*$")){
										JOptionPane.showMessageDialog(null, "������ ���ڷθ� �Է����ּ���","�޽���",JOptionPane.ERROR_MESSAGE);
						         }else {
									
									  mgr.sql =
									  "insert into movie(movie_name,movie_time,movie_class,movie_supervision," +
									  "movie_actor,movie_genre,movie_date,movie_price,movie_intro)values('"+t[0].
									  getText()+"','"+t[1].getText()+"', " +
									  "'"+t[2].getText()+"','"+t[3].getText()+"','"+t[4].getText()+"','"+t[5].
									  getText()+"'," +
									  "'"+t[6].getText()+"',"+t[7].getText()+",'"+t[8].getText()+"')";
									  
									  JOptionPane.showMessageDialog(null,
									  "��ȭ �Է� �Ϸ�Ǿ����ϴ�.","�޽���",JOptionPane.INFORMATION_MESSAGE); dispose();
									  mgr.update(); 
									  admin_main.re1();
									 
							         
						         }
						      } catch(ParseException e1) {
						    	  JOptionPane.showMessageDialog(null, "��¥ ���Ŀ� ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.","�޽���",JOptionPane.ERROR_MESSAGE);
						      }
						}
					}
					
					
					///��ȭ�� ������ �ȵǰ�
					//12,15,19 ��ü �߿��� �Է����ּ���
					// ��¥ ���Ŀ� �°� �Է����ּ��� 20210101 1231 ����������
					//���ڸ��Է°���
				}
			});
			
			b2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
			});
			
			add(b1); add(b2);
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new input_movie();
	}

}
