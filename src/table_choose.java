import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class table_choose extends JFrame{

	String table_name[] = {"��ȭ","������","�󿵰�","Ƽ��","�¼�","ȸ����","��������"};
	JComboBox<String> choose = new JComboBox<String>();
	
	JLabel l1 = new JLabel("�޺��ڽ����� �Է��� ���̺��� ����ּ���.");
	
	public table_choose() {
		

		setTitle("������");
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
