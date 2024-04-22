
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
	
	JLabel Ldate = new JLabel("현재날짜:");
	
	JLabel lb1 = new JLabel("영화목록");
	static int mv=0 ;
	String col[] = {"영화명","감독명","배우명","장르"};
	JLabel l1[] = new JLabel[4];
	JTextField t1[] = new JTextField[4];
	
	String col1[] = {"영화번호	","영화명","상영시간","상영등급","감독명","배우명","장르","개봉일","가격","영화소개"};
	JTable table1 = new JTable();
	JScrollPane sp1 = new JScrollPane();

	JButton b1 = new JButton("검색");
	JButton b2 = new JButton("로그아웃");
	
	JPopupMenu popup1 = new JPopupMenu();
	JMenuItem menu1 = new JMenuItem("예매");
     
	
	JLabel lb2 = new JLabel("예매내역");

	static int tnum=0;
	static int mnum=0;
	static String col2[] = {"티켓예매번호","티켓발권여부","영화명","상영일","상영관번호","좌석번호","판매가격"};
	static JTable table2 = new JTable();
	static JScrollPane sp2 = new JScrollPane();

	static JPopupMenu popup2 = new JPopupMenu();
	static JMenuItem menu3 = new JMenuItem("삭제");
	static JMenuItem menu4 = new JMenuItem("영화 변경");
	static JMenuItem menu5 = new JMenuItem("상영일정 변경");
	
//	● 영화명, 상영일, 상영관번호, 좌석번호 및 판매가격 정보
	
//● 모든 영화에 대한 조회 기능 : 영화명, 감독명, 배우명, 장르

	static ArrayList<Bean> list,list2,list3,list4,list5,list6;
	static DBMgr mgr = new DBMgr();
	static Bean bean,bean2,bean3,bean4,bean5,bean6;

	public member_main() {
		setTitle("회원");
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
							
							//티켓번호 와 영화번호를 info에 넘겨야함.
							
							m11 = table2.getValueAt(table2.getSelectedRow(), 2).toString();  //영화명
							m12 = table2.getValueAt(table2.getSelectedRow(), 3).toString(); //상영일
							m13 = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 4).toString()); //상영관번호
							m14 = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 5).toString()); //좌석번호
							m15 = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 6).toString()); //판매가격
							
						    tnum = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 0).toString()); //티켓예매번호
							
						    mgr.sql = "select movie_no from advance where advance_no = "+tnum+";";
							list6 = mgr.jo1();
							bean6 = list6.get(0);
							
							mnum = Integer.parseInt(bean6.getA()); //영화번호
							
							new info(); //티켓발행여부가 N이면 티켓을 발행하여만 정보를 볼 수 있습니다.
							
						}else if(state.equals("N")){
						
							int p = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 6).toString());
							int an = Integer.parseInt(table2.getValueAt(table2.getSelectedRow(), 0).toString());
							p+=1000;
							
							int s = JOptionPane.showConfirmDialog(null, "티켓 발행하겠습니까?","티켓발행",JOptionPane.YES_NO_OPTION);
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
		
		

		menu3.addActionListener(new ActionListener() { //삭제
			
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
		
		menu4.addActionListener(new ActionListener() { //영화변경
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//해당영화 삭제되고 예매내역과 티켓에서만 삭제되고
			
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
		menu5.addActionListener(new ActionListener() { //상영일변경
			
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
					
					mv = Integer.parseInt(bean5.getA()); //<-영화번호
					
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
		
		menu1.addActionListener(new ActionListener() {  //예약 누를때 
			
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
					
					movie_reservation.movie_num =Integer.parseInt(table1.getValueAt(row, 0).toString()); //영화번호 넘겨줘
					
					
					movie_reservation2.movie_num =Integer.parseInt(table1.getValueAt(row, 0).toString());
					//System.out.println(table1.getValueAt(row, 0).toString());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				} 
				 
				//System.out.println(table1.getValueAt(row, 0));
				if(date.compareTo(date2) >= 0) {
					JOptionPane.showMessageDialog(null, "상영일자가 현재날짜 이전인 영화는 예매할 수 없습니다.", "메세지", JOptionPane.ERROR_MESSAGE);
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
		
		lb1.setFont(new Font("맑은 고딕",1,20));
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
		Ldate.setText("현재날짜: "+stat_date);  //달력에서 선택한 현재날짜 가져와야함.
		reservation3.stat_date = stat_date;
		reservation2.stat_date = stat_date;
		
		Ldate.setPreferredSize(new Dimension(150, 30));
		p1.add(Ldate);
		p1.add(sp1);
		
		p2.add(sp2);
		
		tp.add("영화목록", p1);
		tp.add("예매내역", p2);

		tp.setBounds(0, 0, 985, 560);
		add(tp);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new member_main();
	}
}
