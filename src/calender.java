//package pro;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class calender extends JFrame {
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel(new GridLayout(7, 7));
	
	JLabel lb1 = new JLabel("2021년");
	JLabel lb2 = new JLabel("월");
	
	JComboBox cb1 = new JComboBox();
	
	String wk[] = {"일","월","화","수","목","금","토"};
	JLabel lb[] = new JLabel[7];	
	JButton bt[] = new JButton[42];
	
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sff = new SimpleDateFormat("yyyyMMdd");
	DecimalFormat df = new DecimalFormat("00");
	
	int month=0;
	
	public calender() {
		setTitle("달력");
		
		for (int i = 1; i < 13; i++) {
			cb1.addItem(i);
		}
		
		cb1.setSelectedIndex(month);
		
		p1.add(lb1);p1.add(cb1);p1.add(lb2);
		
		p2();
		
		add("North",p1);
		add("Center",p2);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(350, 400);
		setVisible(true);
		
		cb1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				month = cb1.getSelectedIndex();
				p2();
			}
		});
	}
	
	private void p2() {
		// TODO Auto-generated method stub
		p2.removeAll();
		
		for (int i = 0; i < lb.length; i++) {
			lb[i] = new JLabel(wk[i],JLabel.CENTER);
			p2.add(lb[i]);
		}
		
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new JButton();
			bt[i].setBorder(new LineBorder(Color.black));
			bt[i].setVisible(false);
			p2.add(bt[i]);
			
			int n = i;
			bt[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					String ddd = 2021+"-"+df.format(month+1)+"-"+df.format(Integer.parseInt(bt[n].getText()));
					main.t1.setText(ddd);
					main.m_date = ddd;
					member_main.stat_date = ddd;  //회원영화목록예메 보여주는곳에 날짜 보여주기
					dispose();
				}
			});
		}
		
		cal.set(2021, month, 1);
		
		int start = cal.get(Calendar.DAY_OF_WEEK);
		int end = cal.getActualMaximum(Calendar.DATE);
		
		for (int i = 1; i <= end; i++) {
			bt[i+start-2].setText(i+"");
			bt[i+start-2].setVisible(true);
			
			String date1 = "";
			String date2 = 2021+df.format(month+1)+df.format(i);
			
//			if(Integer.parseInt(date1)>Integer.parseInt(date2)) {
//				bt[i+start-2].setEnabled(false);
//			}else {
//				bt[i+start-2].setEnabled(true);
//			}			
		}
		
		setSize(350, 401);
		setSize(350, 400);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new calender();
	}

}
