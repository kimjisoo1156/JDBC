
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class member_main extends JFrame {
	
	JTabbedPane tp = new JTabbedPane();
	
	JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel p2 = new JPanel();
	
	static String m11="";
	static String m12="";
	static int m13=0;
	static int m14=0;
	static int m15=0;
	
	static String stat_date ="2021-01-01";
	
	JLabel Ldate = new JLabel("���糯¥:");
	
	JLabel lb1 = new JLabel("��ȭ���");
	static int mv=0 ;
	String col[] = {"��ȭ��","������","����","�帣"};
	JLabel l1[] = new JLabel[4];
	JTextField t1[] = new JTextField[4];
	
	String col1[] = {"��ȭ��ȣ	","��ȭ��","�󿵽ð�","�󿵵��","������","����","�帣","������","����","��ȭ�Ұ�"};
	JTable table1 = new JTable();
	JScrollPane sp1 = new JScrollPane();

	JButton b1 = new JButton("�˻�");
	JButton b2 = new JButton("�α׾ƿ�");
	
	JPopupMenu popup1 = new JPopupMenu();
	JMenuItem menu1 = new JMenuItem("����");
     
	
	JLabel lb2 = new JLabel("���ų���");

	static int tnum=0;
	static int mnum=0;
	static String col2[] = {"Ƽ�Ͽ��Ź�ȣ","Ƽ�Ϲ߱ǿ���","��ȭ��","����","�󿵰���ȣ","�¼���ȣ","�ǸŰ���"};
	static JTable table2 = new JTable();
	static JScrollPane sp2 = new JScrollPane();

	static JPopupMenu popup2 = new JPopupMenu();
	static JMenuItem menu3 = new JMenuItem("����");
	static JMenuItem menu4 = new JMenuItem("��ȭ ����");
	static JMenuItem menu5 = new JMenuItem("������ ����");
	
//	�� ��ȭ��, ����, �󿵰���ȣ, �¼���ȣ �� �ǸŰ��� ����
	
//�� ��� ��ȭ�� ���� ��ȸ ��� : ��ȭ��, ������, ����, �帣

	static ArrayList<Bean> list,list2,list3,list4,list5,list6;
	static DBMgr mgr = new DBMgr();
	static Bean bean,bean2,bean3,bean4,bean5,bean6;

	public member_main() {
		setTitle("ȸ��");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		set();

		mgr.sql = "SELECT m.movie_no, m.movie_name, m.movie_time, m.movie_class, m.movie_supervision, m.movie_actor, m.movie_genre, m.movie_date, m.movie_price, m.movie_intro  FROM movie m inner join screen_date s on m.movie_no = s.movie_no "
				+ " where m.movie_name like '%"+t1[0].getText()+"%' and m.movie_supervision like '%"+t1[1].getText()+"%' and m.movie_actor like '%"+t1[2].getText()+"%' and m.movie_genre like '%"+t1[3].getText()+"%' group by m.movie_no order by m.movie_no;";
		
		re1();
		re2();
		
		setSize(1000, 600);
		setVisible(true);
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mgr.sql = "SELECT m.movie_no, m.movie_name, m.movie_time, m.movie_class, m.movie_supervision, m.movie_actor, m.movie_genre, m.movie_date, m.movie_price, m.movie_intro FROM movie m inner join screen_date s on m.movie_no = s.movie_no "
						+ " where m.movie_name like '%"+t1[0].getText()+"%' and m.movie_supervision like '%"+t1[1].getText()+"%' and m.movie_actor like '%"+t1[2].getText()+"%' and m.movie_genre like '%"+t1[3].getText()+"%' group by m.movie_no order by m.movie_no;";
				
				re1();
			}
		});
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new main();
			}
		});
	}

	static void re2() {
		// TODO Auto-generated method stub
		mgr.sql="SELECT t.advance_no,t.ticket_state, m.movie_name, s.screen_date, s.screen_no, t.seat_no, t.selling_price\r\n"
				+ " FROM advance a, movie m, screen_date s, ticket t where  a.screen_no = s.screen_no and t.screen_no = s.screen_no and t.screen_date_no = s.screen_date_no and m.movie_no = a.movie_no and\r\n"
				+ " a.advance_no = t.advance_no and a.member_id = '"+member_login.id+"'  order by a.advance_no asc;";

		list2 = mgr.jo7();
		Object record2[][] = new Object[list2.size()][col2.length];
		for (int i = 0; i < list2.size(); i++) {
			bean2 = list2.get(i);
			record2[i][0] = bean2.getA();
			record2[i][1] = bean2.getB();
			record2[i][2] = bean2.getC();	
			record2[i][3] = bean2.getD();
			record2[i][4] = bean2.getE();
			record2[i][5] = bean2.getF();
			record2[i][6] = bean2.getG();
		}
		
	      
		DefaultTableModel model2 = new DefaultTableModel(record2,col2) {
			public boolean isCellsEditable(int arg0, int arg1) {
				return false;
			};
		};
		popup2.add(menu3);
		popup2.add(menu4);
		popup2.add(menu5);
		

		table2.setModel(model2);
		sp2.setViewportView(table2);		
		sp2.setPreferredSize(new Dimension(970,300));
		 
        
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
							
				if(SwingUtilities.isRightMouseButton(e)) {
					int row = table2.rowAtPoint(e.getPoint());
					
					popup2.show(table2, e.getX(), e.getY());
					table2.setRowSelectionInterval(row, row);
					
				}else if(e.getClickCount()==1){ //
					try {
						String state = table2.getValueAt(table2.getSelectedRow(), 1).toString();
						if(state.equals("Y")) {
							
							//Ƽ�Ϲ�ȣ �� ��ȭ��ȣ�� info�� �Ѱܾ���.
							
							m11 = table2.getValueAt(table2.getSelectedRow(), 2).toString();  //��ȭ��
							m12 = table2.getValueAt(table2.getSelectedRow(), 3).toString(); //����
							m13 = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 4).toString()); //�󿵰���ȣ
							m14 = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 5).toString()); //�¼���ȣ
							m15 = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 6).toString()); //�ǸŰ���
							
						    tnum = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 0).toString()); //Ƽ�Ͽ��Ź�ȣ
							
						    mgr.sql = "select movie_no from advance where advance_no = "+tnum+";";
							list6 = mgr.jo1();
							bean6 = list6.get(0);
							
							mnum = Integer.parseInt(bean6.getA()); //��ȭ��ȣ
							
							new info(); //Ƽ�Ϲ��࿩�ΰ� N�̸� Ƽ���� �����Ͽ��� ������ �� �� �ֽ��ϴ�.
							
						}else if(state.equals("N")){
						
							int p = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 6).toString());
							int an = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 0).toString());
							p+=1000;
							
							int s = JOptionPane.showConfirmDialog(null, "Ƽ�� �����ϰڽ��ϱ�?","Ƽ�Ϲ���",JOptionPane.YES_NO_OPTION);
							//table2.clearSelection();
							if(s==JOptionPane.YES_OPTION) {
								
						
								mgr.sql = "update ticket set ticket_state = \'Y\' ,selling_price = "+p+" where advance_no ="+an+";";
								mgr.update();
								re2();
								
							}else {
								
							}
							
						}
						table2.clearSelection();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				//	table2.clearSelection();
					
				}
			}
		});
		
		

		menu3.addActionListener(new ActionListener() { //����
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JMenuItem mu = (JMenuItem)e.getSource();
				try {
					int nn = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 0).toString());
					
					mgr.sql = "delete from advance where advance_no = "+nn+";";
					mgr.update();
					
					mgr.sql = "delete from ticket where advance_no = "+nn+";";
					mgr.update();
					re2();
				} catch (Exception e2) {
					// TODO: handle exception
					//e2.printStackTrace();
				}
		
				
				
				
			}
		});
		
		menu4.addActionListener(new ActionListener() { //��ȭ����
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//�ش翵ȭ �����ǰ� ���ų����� Ƽ�Ͽ����� �����ǰ�
			
				try {
					
					int nn = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 0).toString());
				
					mgr.sql = "delete from advance where advance_no = "+nn+";";
					mgr.update();
					
					mgr.sql = "delete from ticket where advance_no = "+nn+";";
					mgr.update();
					
					re2();
					
					new reservation2();   
				} catch (Exception e1) {
					
				}
				
			
			}
		});
		menu5.addActionListener(new ActionListener() { //���Ϻ���
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					int nn = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 0).toString());
					String namen = table2.getValueAt(table2.getSelectedRow(), 2).toString();
					
					mgr.sql = "select movie_no from movie where movie_name = '"+namen+"';";
					//System.out.println(mgr.sql);
					
					list5 = mgr.jo1();
					bean5 = list5.get(0);
					
					mv = Integer.parseInt(bean5.getA()); //<-��ȭ��ȣ
					
					mgr.sql = "delete from advance where advance_no = "+nn+";";
					mgr.update();
					
					mgr.sql = "delete from ticket where advance_no = "+nn+";";
					mgr.update();
					
					re2();
					
					new reservation3();   
				} catch (Exception e1) {
					
				}
			
			}
		});
	
		 
		
		table2.getColumnModel().getColumn(0).setWidth(0);
		table2.getColumnModel().getColumn(0).setMinWidth(0);
	    table2.getColumnModel().getColumn(0).setMaxWidth(0);
		      
	    table2.getColumnModel().getColumn(1).setWidth(0);
		table2.getColumnModel().getColumn(1).setMinWidth(0);
	    table2.getColumnModel().getColumn(1).setMaxWidth(0);
	}
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Date date, date2, date3;
	int compare = 0;
	
	private void re1() {
		// TODO Auto-generated method stub
		list = mgr.jo10();
		Object record[][] = new Object[list.size()][col1.length];
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			record[i][0] = bean.getA();
			record[i][1] = bean.getB();
			record[i][2] = bean.getC();	
			record[i][3] = bean.getD();
			record[i][4] = bean.getE();
			record[i][5] = bean.getF();
			record[i][6] = bean.getG();
			record[i][7] = bean.getH();
			record[i][8] = bean.getI();
			record[i][9] = bean.getJ();
		}
		 
		DefaultTableModel model = new DefaultTableModel(record,col1){
			public boolean isCellsEditable(int arg0, int arg1) {
				return false;
			};
		};
		
		
		table1 = new JTable(model) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				Component c = super.prepareRenderer(renderer, row, column);
				
				try {
					date = new Date(dateFormat.parse(main.m_date).getTime());
					date2 = new Date(dateFormat.parse(table1.getValueAt(row, 7)+"").getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				if(date.compareTo(date2) >= 0) {
					c.setBackground(Color.gray);	
				}else {
					c.setBackground(null);	
				}
				return c;
				
			}
		};

		popup1.add(menu1);
		
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
							
				if(SwingUtilities.isRightMouseButton(e)) {
					int row = table1.rowAtPoint(e.getPoint());
					
					popup1.show(table1, e.getX(), e.getY());
					table1.setRowSelectionInterval(row, row);
				}
			}
		});
		
		table1.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e) {
				table1.setToolTipText(getToolTipText(e));
			}
			public String getToolTipText(MouseEvent e) {
				Point p = e.getPoint();
				if (table1.columnAtPoint(p)==9) {
					return table1.getModel().getValueAt(table1.rowAtPoint(p), table1.columnAtPoint(p)).toString();
					
				}else {
					return null;
				}
			}
		});
			
		
		for( ActionListener al : menu1.getActionListeners() ) {
	        menu1.removeActionListener( al );
	    }
		
		menu1.addActionListener(new ActionListener() {  //���� ������ 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int row = table1.getSelectedRow();
				
				try {
					
					//System.out.println(row);
					date = new Date(dateFormat.parse(main.m_date).getTime());
					date2 = new Date(dateFormat.parse(table1.getValueAt(row, 7)+"").getTime());
					
					
					movie_reservation.movie_name = table1.getValueAt(row, 1).toString();
					movie_reservation2.movie_name = table1.getValueAt(row, 1).toString();
					
					//System.out.println(table1.getValueAt(row, 1).toString());
					
					movie_reservation.movie_num =Integer.parseInt(table1.getValueAt(row, 0).toString()); //��ȭ��ȣ �Ѱ���
					
					
					movie_reservation2.movie_num =Integer.parseInt(table1.getValueAt(row, 0).toString());
					//System.out.println(table1.getValueAt(row, 0).toString());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				} 
				 
				//System.out.println(table1.getValueAt(row, 0));
				if(date.compareTo(date2) >= 0) {
					JOptionPane.showMessageDialog(null, "�����ڰ� ���糯¥ ������ ��ȭ�� ������ �� �����ϴ�.", "�޼���", JOptionPane.ERROR_MESSAGE);
				}else {
					new movie_reservation();
				}
				
			}
		});
		
		table1.getColumnModel().getColumn(9).setPreferredWidth(150);
		
		sp1.setPreferredSize(new Dimension(970,300));
		table1.setModel(model);
		sp1.setViewportView(table1);		

	}

	private void set() {
		// TODO Auto-generated method stub
		
		lb1.setFont(new Font("���� ���",1,20));
		lb1.setPreferredSize(new Dimension(930,30));
		p1.add(lb1);
		
		for (int i = 0; i < col.length; i++) {
			l1[i] = new JLabel(col[i]);
			t1[i] = new JTextField();
			l1[i].setPreferredSize(new Dimension(50,20));
			t1[i].setPreferredSize(new Dimension(100,20));
			p1.add(l1[i]);p1.add(t1[i]);
		}
		
	
		
		p1.add(b1);p1.add(b2);
		Ldate.setText("���糯¥: "+stat_date);  //�޷¿��� ������ ���糯¥ �����;���.
		reservation3.stat_date = stat_date;
		reservation2.stat_date = stat_date;
		
		Ldate.setPreferredSize(new Dimension(150, 30));
		p1.add(Ldate);
		p1.add(sp1);
		
		p2.add(sp2);
		
		tp.add("��ȭ���", p1);
		tp.add("���ų���", p2);

		tp.setBounds(0, 0, 985, 560);
		add(tp);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new member_main();
	}
}
