
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

public class reservation2 extends JFrame {
	
	JTabbedPane tp = new JTabbedPane();
	
	JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	
	static String stat_date ="2021-01-01";
	
	JLabel Ldate = new JLabel("현재날짜:");
	
	JLabel lb1 = new JLabel("영화목록");
	
	String col[] = {"영화명","감독명","배우명","장르"};
	JLabel l1[] = new JLabel[4];
	String col1[] = {"영화번호	","영화명","상영시간","상영등급","감독명","배우명","장르","개봉일","가격","영화소개"};
	JTable table1 = new JTable();
	JScrollPane sp1 = new JScrollPane();

	
	JPopupMenu popup1 = new JPopupMenu();
	JMenuItem menu1 = new JMenuItem("예매");
     
	
	JLabel lb2 = new JLabel("예매내역");
	
	static ArrayList<Bean> list,list2,list3,list4;
	static DBMgr mgr = new DBMgr();
	static Bean bean,bean2,bean3,bean4;

	public reservation2() {
		setTitle("영화변경하기");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		set();

		mgr.sql = "SELECT m.movie_no, m.movie_name, m.movie_time, m.movie_class, m.movie_supervision, m.movie_actor, m.movie_genre, m.movie_date, m.movie_price, m.movie_intro  FROM movie m inner join screen_date s on m.movie_no = s.movie_no "
				+ "group by m.movie_no order by m.movie_no;";
		
		re1();
		
		setSize(1000, 600);
		setVisible(true);
		
		
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
		// 이거 넣었는데 왜 더블클릭 수정되는지 몰겠음,, 그래서 걍 한번 누르면 예매나 예매금지 메시지 띄움.
		
		
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
			
		menu1.addActionListener(new ActionListener() {  //예약 누를때 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int row = table1.getSelectedRow();
				
				try {
					date = new Date(dateFormat.parse(main.m_date).getTime());
					date2 = new Date(dateFormat.parse(table1.getValueAt(row, 7)+"").getTime());

					
					movie_reservation.movie_name = table1.getValueAt(row, 1).toString();
					movie_reservation.movie_num =Integer.parseInt(table1.getValueAt(row, 0).toString()); //영화번호 넘겨줘

					
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				 
				//System.out.println(table1.getValueAt(row, 0));
				if(date.compareTo(date2) >= 0) {
					JOptionPane.showMessageDialog(null, "상영일자가 현재날짜 이전인 영화는 예매할 수 없습니다.", "메세지", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						dispose();
						new movie_reservation();
						member_main.re2();
						
					} catch (Exception e1) {
					}
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
		
	
		Ldate.setText("현재날짜: "+stat_date);  //달력에서 선택한 현재날짜 가져와야함.
		Ldate.setPreferredSize(new Dimension(150, 30));
		p1.add(Ldate);
		p1.add(sp1);
		
		
		tp.add("영화목록", p1);

		tp.setBounds(0, 0, 985, 560);
		add(tp);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new reservation2();
	}
}
