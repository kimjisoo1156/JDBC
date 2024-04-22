import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.naming.StringRefAddr;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;

public class movie_reservation2 extends JFrame{
	

	JLabel l1 = new JLabel("영화제목: ");
	JLabel l2 = new JLabel("상영관번호: ");
	JLabel l3= new JLabel("상영일자: ");
	JLabel l4 = new JLabel("상영시작시간: ");
	
	String start_date ="2021-01-01";
	String []sd;
	String []ed;
	int M1,M2,D1,D2;
	
	String end_date = "2021-01-15";
	static String movie_name = ".";
	static int movie_num;
	
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	JButton btn = new JButton("예매");
	
	JTextField t1 = new JTextField();
	
	
	JComboBox<String> screen_num = new JComboBox<String>();
	
	static JTextField screen_date = new JTextField();
	
	JComboBox<String> screen_time = new JComboBox<String>();
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
	JLabel l5 = new JLabel("SCREEN");
	
	JRadioButton []r  = new JRadioButton[10];
	ButtonGroup g = new ButtonGroup();
	
	JRadioButton r1 = new JRadioButton("카드");
	JRadioButton r2 = new JRadioButton("현금");
	
	ButtonGroup g2 = new ButtonGroup();
	
	ArrayList<Bean> list,list2,list3,list4,list5,list6,list7,list8,list9,list10,list11;
	DBMgr mgr = new DBMgr();
	Bean bean,bean2,bean3,bean4,bean5,bean6,bean7,bean8,bean9,bean10,bean11;

	int flag=0;
	String pay="결제";
	int seat=0;
	int price=0;
	int count;
	public movie_reservation2() throws ParseException {
		
		setTitle("영화예매");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		set();  //상영관번호에 따른 좌석 보이게
		
		setSize(1000, 600);
		setVisible(true);
		
	}
	private void set() throws ParseException {
		// TODO Auto-generated method stub
		p1.setBounds(0, 0, 1000	, 200);
		p2.setBounds(0, 200, 1000, 500);
		
		p1.setLayout(null);
		t1.setEditable(false);
		screen_date.setEditable(false);
	
		t1.setText(movie_name);
		
		l1.setBounds(10, 10, 100, 30);
		t1.setBounds(80, 10, 120, 30);
		
		l3.setBounds(220, 10, 100, 30);
		
		
	//	screen_date.setText(reservation3.sdate);
		
		
		l4.setBounds(700, 10, 100, 30);
		
		
		screen_time.setBounds(800, 10, 100, 30);

		
		screen_date.setBounds(300, 10, 120, 30);
		
		l2.setBounds(450, 10, 100, 30);
		
		combo();
		combo2();
		
		screen_date.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//영화번호를 기준으로  상영번호를 가져오자  /// ORDER BY screen_no asc
				combo();
				seat();
				
			}

			
		});
				
		
		screen_num.setBounds(550, 10, 120, 30);
		
		

		
		mgr.sql="SELECT seat_count FROM screen where screen_no = "+screen_num.getSelectedItem()+";";
		
		list5 = mgr.jo1();
		
		
		for (int i = 0; i < list5.size(); i++) {
			bean5 = list5.get(i);
			
			count = Integer.parseInt(bean5.getA());
			for (int j = 0; j < count; j++) {
				r[j]= new JRadioButton(j+1+"");
				r[i].setSelected(false);
				r[j].setPreferredSize(new Dimension(50, 50));  //라디오생성하는곳.
				g.add(r[j]);
				p2.add(r[j]);
			}
			
		}
		seat();
		
		
	
		
		
		screen_num.addItemListener(new ItemListener() {
			
			 

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange()==ItemEvent.SELECTED) {
					
					p2.removeAll();  //패널 초기화
					p2.revalidate();
					p2.repaint();
					

					
					int n = Integer.parseInt(screen_num.getSelectedItem().toString());
					
					
					mgr.sql="SELECT seat_count FROM screen where screen_no = "+n+";";
					list4 = mgr.jo1();
					
					for (int i = 0; i < list4.size(); i++) {
						bean4 = list4.get(i);
						
						count = Integer.parseInt(bean4.getA());
						//System.out.println(count);
						for (int j = 0; j < count; j++) {
							r[j]= new JRadioButton(j+1+"");
							r[j].setPreferredSize(new Dimension(50, 50));
							g.add(r[j]);
							p2.add(r[j]);
						}
					}

					seat();
				}
			
			}
			
		});;
	
		
		
		l5.setBounds(0, 70, 1000, 30);
		
		l5.setOpaque(true);
		l5.setBackground(Color.black);
		l5.setFont(new Font("맑은 고딕", ABORT, 30));
		l5.setForeground(Color.white);
		l5.setHorizontalAlignment(JLabel.CENTER);
		
		p1.add(l1); p1.add(t1);
		p1.add(l2); p1.add(screen_num);
		p1.add(l3); p1.add(screen_date);
		p1.add(l4); p1.add(screen_time);
		p1.add(l5);
		
		btn.setBounds(800, 500, 100, 30);
		
		r1.setBounds(650, 500, 50, 50);
		r2.setBounds(725, 500, 50, 50);
		
		g2.add(r1); g2.add(r2);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i <count ; i++) {
					if(r[i].isSelected()) {
						flag=1;
						seat = i+1; //좌석번호
					}
				}
				if(flag==0) {
					JOptionPane.showMessageDialog(null, "좌석을 선택해주세요.", "메세지", JOptionPane.ERROR_MESSAGE);
				}else if(!r1.isSelected()&&!r2.isSelected()) {
					JOptionPane.showMessageDialog(null, "결제방식을 선택해주세요.", "메세지", JOptionPane.ERROR_MESSAGE);
				}else {
					if (flag==1&&r1.isSelected()) {
						pay = "카드";
					}else {
						pay = "현금";
					}
					mgr.sql ="select movie_price from movie where movie_no ="+movie_num+";";
					
					
					
					list6  = mgr.jo1();
					bean6=list6.get(0);
					price = Integer.parseInt(bean6.getA().toString());
					
					mgr.sql = "insert into advance(movie_no,screen_no,payment_option,payment_state,price,member_id,payment_date)"
							+ "values("+movie_num+","+screen_num.getSelectedItem()+", "
									+ "'"+pay+"',\'N\',"+price+",'"+member_login.id+"','"+member_main.stat_date+"')";
					
				//	System.out.println(mgr.sql);
					mgr.update();
					
					//예약하면 ticket도 추가되야해 하지만 상태는 N으로 
					int a_num = 0 ;
					int mm_num =0;
					int s_no=0;
					int price2=price+1000;
					
					mgr.sql = "select advance_no,movie_no from advance order by advance_no desc LIMIT 1";
					list7 = mgr.jo2();
					for (int i = 0; i < list7.size(); i++) {
						bean7 = list7.get(i);
						a_num = Integer.parseInt(bean7.getA().toString());
						mm_num =Integer.parseInt(bean7.getB().toString());
					}
					
					mgr.sql = "select s.screen_date_no from screen_date s  where movie_no ="+movie_num+" and screen_no ="+screen_num.getSelectedItem()+" and screen_date = '"+screen_date.getText()+"';;";
					//System.out.println(mgr.sql);
					
					list8 = mgr.jo1();
					bean8=list8.get(0);
					s_no = Integer.parseInt(bean8.getA().toString());
					
					mgr.sql = "insert into ticket (screen_date_no,screen_no,seat_no,advance_no,ticket_state,avg_price,selling_price)"
							+ "values('"+s_no+"',"+screen_num.getSelectedItem()+","+seat+","+a_num+",\'N\',"+price+","+price2+")";
					
					
					mgr.update();
					
					/*
					 * // seat 꽉차면 비활성화 mgr.sql =
					 * "select seat_count from screen where screen_no = "+screen_num.getSelectedItem
					 * ()+";"; list9 = mgr.jo1(); System.out.println(mgr.sql); mgr.sql =
					 * "select count(*) from screen_date s inner join ticket t on s.screen_date_no = t.screen_date_no where s.movie_no = "
					 * +movie_num+" and s.screen_no = "+screen_num.getSelectedItem()+";"; list10 =
					 * mgr.jo1(); System.out.println(mgr.sql);
					 * if(Integer.parseInt(bean9.getA())==Integer.parseInt(bean10.getA())) { mgr.sql
					 * = "update screen set screen_state = 'N' where screen_no = "+screen_num.
					 * getSelectedItem()+" "; mgr.update(); }
					 */
					
					
					member_main.re2(); 
					JOptionPane.showMessageDialog(null, "예매를 완료 하였습니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
					
					int answer = JOptionPane.showConfirmDialog(null, "티켓 미리 발행해 놓겠습니까? 발행 해놓으면 예매내역에서 티켓조회시 확인가능합니다.","티켓발행",JOptionPane.YES_NO_OPTION);
					if(answer==JOptionPane.YES_OPTION) {
						
						
						mgr.sql = "update advance set payment_state = \'Y\' where advance_no ="+a_num+";";
						mgr.update();
						
						mgr.sql = "update ticket set ticket_state = \'Y\' where advance_no ="+a_num+";";
						mgr.update();
					

						member_main.re2();
						dispose();
					
					}else {
						member_main.re2();
						dispose();
					}
				}
				
			}
		});
		add(btn); add(r1); add(r2);
		add(p1); add(p2);
		
	}
	
	
	private void combo2() {
		// TODO Auto-generated method stub
		screen_time.removeAllItems();
		
		mgr.sql = "SELECT DISTINCT screen_time FROM screen_date where movie_no = "+movie_num+" and screen_date = '"+screen_date.getText()+"';";
		list3=mgr.jo1();

		for (int i = 0; i < list3.size(); i++) {
			bean3 = list3.get(i);
			screen_time.addItem(bean3.getA());
		}
				
		
	}
	private void seat() {
		// TODO Auto-generated method stub
		///좌석 비활성화 활성화
		///////////////////////////
		
		mgr.sql = "select screen_date_no from screen_date where movie_no ="+movie_num+" and screen_no = "+screen_num.getSelectedItem()+" and screen_date = '"+screen_date.getText()+"';";
		
		//System.out.println(mgr.sql);
		
		list9  = mgr.jo1();
		bean9 = list9.get(0);
		int sno = Integer.parseInt(bean9.getA().toString());

		  mgr.sql = "select seat_no from ticket where screen_no = "+screen_num.getSelectedItem()
		  +" and screen_date_no ="+sno+";"; 
		  list10 = mgr.jo1();
			
			
		  for (int i = 0; i < count; i++) { 
			
			  r[i].setEnabled(true);
			
			  for (int j = 0; j < list10.size(); j++) {
				  bean10 = list10.get(j);
				  if(r[i].getText().equals(bean10.getA())) { 
					  r[i].setEnabled(false);
			  	}
				
			}
		  }
		  
		  mgr.sql = "select screen_state from screen where screen_no = "+screen_num.getSelectedItem()+";";
			list = mgr.jo1();
			bean = list.get(0);
			if(bean.getA().equals("N")) {
				for (int i = 0; i < count; i++) { 
					r[i].setEnabled(false);
				}	
			}
		
	}
	private void combo() { //상영관 번호에따라 좌석수 변경되는곳
		// TODO Auto-generated method stub
		
		screen_num.removeAllItems();
		mgr.sql = "SELECT DISTINCT screen_no FROM screen_date where movie_no = "+movie_num+" and screen_date ='"+screen_date.getText()+"';";
		list2 = mgr.jo1();
		
		for (int i = 0; i < list2.size(); i++) {
			bean2 = list2.get(i);
			screen_num.addItem(bean2.getA());
		}
	  

	}
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		new movie_reservation2();
	}

}
