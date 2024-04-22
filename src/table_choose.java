import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class table_choose extends JFrame{

	String table_name[] = {"영화","상영일정","상영관","티켓","좌석","회원고객","예매정보"};
	JComboBox<String> choose = new JComboBox<String>();
	
	JLabel l1 = new JLabel("콤보박스에서 입력할 테이블을 골라주세요.");
	
	public table_choose() {
		

		setTitle("관리자");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(620, 350);
		
		for (int i = 0; i < table_name.length; i++) {
			choose.addItem(table_name[i]);
		}
		choose.setSelectedIndex(-1);
		add(l1);
		add(choose);
		
		choose.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				int k = choose.getSelectedIndex();
				
				dispose();
				
				switch (k) {
				case 0: new input_movie(); break;
				case 1: new input_screen_date(); break;
				case 2: new input_screen(); break;
				case 3: new input_ticket(); break;
				case 4: new input_seat(); break;
				case 5: new input_member(); break;
				case 6: new input_advance(); break;
					
				}
			}
		});
		
		
		
		setVisible(true);
		setSize(300,150);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new table_choose();
	}

}
