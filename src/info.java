import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class info extends JFrame{

	JLabel l[] = new JLabel[8];
	
	String name[]= {"Ƽ�Ϲ�ȣ","������","��ȭ�̸�","�󿵰�","�󿵵��","�¼���ȣ","���۽ð�","�����ݾ�"};
	
	static JLabel l2[] = new JLabel[8];
	
	// Ƽ�Ϲ�ȣ ������, ��ȭ�̸�, �󿵰�, �󿵵��, �¼���ȣ, ���۽ð�, �����ݾ� selling_price
	//Ƽ���̶� ������ ��ȭ ���̺� 
	
	//Ƽ�Ϲ�ȣ, �󿵵��, ���۽ð�.
	
	JButton b1 = new JButton("Ȯ��");
	int tt;
	int tt1;
	int tt2;
	
	int s;
	int s1;
	
	String a1;
	String a2;
	
	ArrayList<Bean>list,list2,list3;
	DBMgr mgr = new DBMgr();
	Bean bean,bean2,bean3;
	
	public info() {

		setTitle("����");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 500);
		
		for (int i = 0; i < l.length; i++) {
			l[i] = new JLabel(name[i]);
			l2[i] = new JLabel();
			
			l[i].setBounds(10, i*50, 50, 30);
			l2[i].setBounds(70, i*50, 100, 30);
			
			add(l[i]);
			add(l2[i]);
		}
			
		l2[1].setText(member_main.m12);
		l2[2].setText(member_main.m11);
		l2[3].setText(member_main.m13+"");
		l2[5].setText(member_main.m14+"");
		l2[7].setText(member_main.m15+"");
	
		mgr.sql = "select ticket_no,screen_date_no,screen_no from ticket where advance_no = "+member_main.tnum+";";
		list = mgr.jo3();
		
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			tt = Integer.parseInt(bean.getA());
			tt1 = Integer.parseInt(bean.getB());
			tt2 = Integer.parseInt(bean.getC());

		}
		l2[0].setText(tt+""); //Ƽ�Ϲ�ȣ
		
		mgr.sql = "select movie_no, screen_no from screen_date where screen_date_no ="+tt2+";";
		list2 = mgr.jo2();
		
		for (int i = 0; i < list2.size(); i++) {
			bean2 = list2.get(i);
			s = Integer.parseInt(bean.getA());
			s1 = Integer.parseInt(bean.getB());
		
		}
		
		mgr.sql = "select m.movie_genre, s.screen_time from screen_date s, movie m "
				+ "where s.screen_date_no = "+tt1+" and m.movie_no ="+member_main.mnum+" and s.screen_no = "+tt2+" and  s.movie_no = m.movie_no;";
		//System.out.println(mgr.sql);
		list3 = mgr.jo2();
		for (int i = 0; i < list3.size(); i++) {
			bean3 = list3.get(i);
			a1 = bean3.getA();
			a2 = bean3.getB();
		}
		l2[4].setText(a1);
		l2[6].setText(a2);
		
		
		
		
		add(b1);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new info();
	}

}
