import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class admin_main extends JFrame{

	JTabbedPane tp = new JTabbedPane();
	
	static String col1[] = {"영화번호","영화명","상영시간","상영등급","감독명","배우명","장르","개봉일","가격","영화소개"};
	static String col2[] = {"상영일정번호","영화번호","상영관번호","상영시작일","상영요일","상영회차","상영시작시간"};
	static String col3[] = {"상영관번호","좌석수","상영관사용여부"};
	static String col4[] = {"티켓번호","상영일정번호","상영관번호","좌석번호","예매번호","발권여부","표준가격","판매가격"};
	static String col5[] = {"좌석번호","상영관번호","좌석사용여부"};
	static String col6[] = {"회원아이디","회원비밀번호","고객명","휴대폰번호","전자메일주소"};
	static String col7[] = {"예매번호","영화번호","상영관번호","결제방법","결제상태","결제금액","회원아이디","결제일자"};
	
	static JTable table1 = new JTable();
	static JTable table2 = new JTable();
	static JTable table3 = new JTable();
	static JTable table4 = new JTable();
	static JTable table5 = new JTable();
	static JTable table6 = new JTable();
	static JTable table7 = new JTable();
	
	static JScrollPane sp1 = new JScrollPane();
	static JScrollPane sp2 = new JScrollPane();
	static JScrollPane sp3 = new JScrollPane();
	static JScrollPane sp4 = new JScrollPane();
	static JScrollPane sp5 = new JScrollPane();
	static JScrollPane sp6 = new JScrollPane();
	static JScrollPane sp7 = new JScrollPane();
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JPanel p7 = new JPanel();
	

	JButton btn1 = new JButton("초기화");
	JButton btn2 = new JButton("전체 테이블 보기");
	JButton btn3 = new JButton("입력");
	
	JButton btn4 = new JButton("삭제");
	JButton btn5 = new JButton("변경");
	
	JButton btn6 = new JButton("로그아웃");
	
	
	static ArrayList<Bean> list1,list2,list3,list4,list5,list6,list7;
	static DBMgr mgr = new DBMgr();
	static Bean bean1,bean2,bean3,bean4,bean5,bean6,bean7;
	
	public admin_main() {

		setTitle("관리자");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		re1(); 
		re2();
		re3(); 
		re4();
		re5(); 
		re6();
		re7();
		
		p1.add(sp1); p2.add(sp2);
		p3.add(sp3); p4.add(sp4);
		p5.add(sp5); p6.add(sp6);
		p7.add(sp7); 
		
		tp.add("영화", p1); tp.add("상영일정", p2);
		tp.add("상영관", p3); tp.add("티켓", p4);
		tp.add("좌석", p5); tp.add("회원고객", p6);
		tp.add("예매정보", p7); 

		tp.setBounds(0, 0, 985, 480);
		add(tp);
		
		
		btn3.setBounds(290, 500, 100, 30);
		btn4.setBounds(400, 500, 100, 30);
		btn5.setBounds(510, 500, 100, 30);
		btn1.setBounds(620, 500, 100, 30);
		
		btn2.setBounds(730, 500, 130, 30);
		
		btn6.setBounds(870, 500, 100, 30);
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new smain();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				re1(); 
				re2();
				re3(); 
				re4();
				re5(); 
				re6();
				re7();
				JOptionPane.showMessageDialog(null, "초기화가 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new all_table();
			}
		});
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new table_choose();
				
			}
		});
		
		
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				new all_delete();
				
				
			}
		});
		btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new all_update();
			}
		});
		btn6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new main();
			}
		});
		
		add(btn1); add(btn2); add(btn3); add(btn4); add(btn5); add(btn6);
		setVisible(true);
		setSize(1020, 600);
	}
	
	
	static void re7() {
		// TODO Auto-generated method stub
		
		mgr.sql="SELECT * FROM advance";
		
		list7 = mgr.jo8();
		Object record7[][] = new Object[list7.size()][col7.length];
		for (int i = 0; i < list7.size(); i++) {
			bean7 = list7.get(i);
			record7[i][0] = bean7.getA();
			record7[i][1] = bean7.getB();
			record7[i][2] = bean7.getC();	
			record7[i][3] = bean7.getD();
			record7[i][4] = bean7.getE();
			record7[i][5] = bean7.getF();
			record7[i][6] = bean7.getG();
			record7[i][7] = bean7.getH();
		}
		
		DefaultTableModel model7 = new DefaultTableModel(record7,col7) {
			public boolean isCellsEditable(int arg0, int arg1) {
				return false;
			};
		};
		table7.setModel(model7);
		sp7.setViewportView(table7);	
		sp7.setPreferredSize(new Dimension(970,300));
	}


	static void re6() {
		// TODO Auto-generated method stub
		mgr.sql="SELECT * FROM member";
		
		list6 = mgr.jo5();
		Object record6[][] = new Object[list6.size()][col6.length];
		for (int i = 0; i < list6.size(); i++) {
			bean6 = list6.get(i);
			record6[i][0] = bean6.getA();
			record6[i][1] = bean6.getB();
			record6[i][2] = bean6.getC();	
			record6[i][3] = bean6.getD();
			record6[i][4] = bean6.getE();

		}
		
		DefaultTableModel model6 = new DefaultTableModel(record6,col6) {
			public boolean isCellsEditable(int arg0, int arg1) {
				return false;
			};
		};
		
		table6.setModel(model6);
		sp6.setViewportView(table6);	
		sp6.setPreferredSize(new Dimension(970,300));
	}


	static void re5() {
		// TODO Auto-generated method stub
		mgr.sql="SELECT * FROM seat";
		
		list5 = mgr.jo3();
		Object record5[][] = new Object[list5.size()][col5.length];
		for (int i = 0; i < list5.size(); i++) {
			bean5 = list5.get(i);
			record5[i][0] = bean5.getA();
			record5[i][1] = bean5.getB();
			record5[i][2] = bean5.getC();	
			
		}
		
		DefaultTableModel model5 = new DefaultTableModel(record5,col5) {
			public boolean isCellsEditable(int arg0, int arg1) {
				return false;
			};
		};
	
		
		table5.setModel(model5);
		sp5.setViewportView(table5);	
		sp5.setPreferredSize(new Dimension(970,300));
	}


	static void re4() {
		// TODO Auto-generated method stub
		mgr.sql="SELECT * FROM ticket";
		
		list4 = mgr.jo8();
		Object record4[][] = new Object[list4.size()][col4.length];
		for (int i = 0; i < list4.size(); i++) {
			bean4 = list4.get(i);
			record4[i][0] = bean4.getA();
			record4[i][1] = bean4.getB();
			record4[i][2] = bean4.getC();	
			record4[i][3] = bean4.getD();
			record4[i][4] = bean4.getE();
			record4[i][5] = bean4.getF();
			record4[i][6] = bean4.getG();
			record4[i][7] = bean4.getH();
		}
		
		DefaultTableModel model4 = new DefaultTableModel(record4,col4) {
			public boolean isCellsEditable(int arg0, int arg1) {
				return false;
			};
		};
		
		table4.setModel(model4);
		sp4.setViewportView(table4);	
		sp4.setPreferredSize(new Dimension(970,300));
	}


	static void re3() {
		// TODO Auto-generated method stub
		mgr.sql="SELECT * FROM screen";
		list3 = mgr.jo3();
		Object record3[][] = new Object[list3.size()][col3.length];
		for (int i = 0; i < list3.size(); i++) {
			bean3 = list3.get(i);
			record3[i][0] = bean3.getA();
			record3[i][1] = bean3.getB();
			record3[i][2] = bean3.getC();	
			
		}
		
		DefaultTableModel model3 = new DefaultTableModel(record3,col3) {
			public boolean isCellsEditable(int arg0, int arg1) {
				return false;
			};
		};
		
		
		table3.setModel(model3);
		sp3.setViewportView(table3);	
		sp3.setPreferredSize(new Dimension(970,300));
	}


	static void re2() {
		// TODO Auto-generated method stub
		mgr.sql="SELECT * FROM screen_date";
		
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
		
		table2.setModel(model2);
		sp2.setViewportView(table2);	
		sp2.setPreferredSize(new Dimension(970,300));
	}


	static void re1() {
		// TODO Auto-generated method stub
		mgr.sql="SELECT * FROM movie";
		
		list1 = mgr.jo10();
		Object record1[][] = new Object[list1.size()][col1.length];
		for (int i = 0; i < list1.size(); i++) {
			bean1 = list1.get(i);
			record1[i][0] = bean1.getA();
			record1[i][1] = bean1.getB();
			record1[i][2] = bean1.getC();	
			record1[i][3] = bean1.getD();
			record1[i][4] = bean1.getE();
			record1[i][5] = bean1.getF();
			record1[i][6] = bean1.getG();
			record1[i][7] = bean1.getH();
			record1[i][8] = bean1.getI();
			record1[i][9] = bean1.getJ();
			
		}
		
		DefaultTableModel model1 = new DefaultTableModel(record1,col1) {
			public boolean isCellsEditable(int arg0, int arg1) {
				return false;
			};
		};
		
		
		
		table1.setModel(model1);
		sp1.setViewportView(table1);	
		sp1.setPreferredSize(new Dimension(970,300));
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new admin_main();
	}

}
