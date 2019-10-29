

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Test extends JFrame{
	
	private JPanel p;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;
	private JPanel p7;
	private JPanel p8;
	private JPanel p9;
	private JPanel p10;
	
	private JTextField t_id1;
	private JTextField t_id2;
	private JTextField t_id3;
	private JTextField t_id4;
	private JTextField t_id5;
	private JTextField t_id6;
	private JTextField t_id7;
	private JTextField t_id8;
	private JTextField t_id9;
	private JTextField t_id10;
	
	private JButton bt;
	private JButton bt2;
	private JButton bt3;
	private JButton bt4;
	private JButton bt5;
	private JButton bt6;
	private JButton bt7;
	private JButton bt8;
	private JButton bt9;
	private JButton bt10;
	private JButton up;
	private JButton down;
	private List<JPanel> list;
	
	private Dimension size;
	int cnt =0;
	public void init() {
		list = new ArrayList();
		p = new JPanel();

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		p7 = new JPanel();
		p8 = new JPanel();
		p9 = new JPanel();
		p10 = new JPanel();
		
		t_id1 = new JTextField();
		t_id2 = new JTextField();
		t_id3 = new JTextField();
		t_id4 = new JTextField();
		t_id5 = new JTextField();
		t_id6 = new JTextField();
		t_id7 = new JTextField();
		t_id8 = new JTextField();
		t_id9 = new JTextField();
		t_id9 = new JTextField();
		t_id10 = new JTextField();
		bt = new JButton("bt");
		bt2 = new JButton("bt2");
		bt3 = new JButton("bt3");
		bt4 = new JButton("bt4");
		bt5 = new JButton("bt5");
		bt6 = new JButton("bt6");
		bt7 = new JButton("bt7");
		bt8 = new JButton("bt8");
		bt9 = new JButton("bt9");
		bt10 = new JButton("bt10");
		up = new JButton("up");
		down = new JButton("down");
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		list.add(p9);
		list.add(p10);
		//git_test
		size = new Dimension(280,50);
		setLayout(new FlowLayout());
		
		t_id1.setPreferredSize(size);
		t_id2.setPreferredSize(size);
		t_id3.setPreferredSize(size);
		t_id4.setPreferredSize(size);
		t_id5.setPreferredSize(size);
		t_id6.setPreferredSize(size);
		t_id7.setPreferredSize(size);
		t_id8.setPreferredSize(size);
		t_id9.setPreferredSize(size);
		t_id10.setPreferredSize(size);
		
		
		
		
		
		p1.add(t_id1);
		p2.add(t_id2);
		p3.add(t_id3);
		p4.add(t_id4);
		p5.add(t_id5);
		p6.add(t_id6);
		p7.add(t_id7);
		p8.add(t_id8);
		p9.add(t_id9);
		p10.add(t_id10);
		p1.add(bt);
		p2.add(bt2);
		p3.add(bt3);
		p4.add(bt4);
		p5.add(bt5);
		p6.add(bt6);
		p7.add(bt7);
		p8.add(bt8);
		p9.add(bt9);
		p10.add(bt10);
		for(int i=0;i<list.size();i++) {
			if(i<3) {
				p.add(list.get(i));
			}
		} 

		p.setBackground(Color.black);
		p.setPreferredSize(new Dimension(400,300));
		add(p);
		add(up); 
		add(down); 
		setSize(400,700);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list.remove(p1);
				p.removeAll();
				defaultRender(3, cnt, p, list);
				p.revalidate();
				p.repaint();
			}
		});
		bt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list.remove(p2);
				p.removeAll();
				defaultRender(3, cnt, p, list);
				p.revalidate();
				p.repaint();
			}
		});
		bt3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list.remove(p3);
				p.removeAll();
				defaultRender(3, cnt, p, list);
				p.revalidate();
				p.repaint();
			}
		});
		bt4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list.remove(p4);
				p.removeAll();
				defaultRender(3, cnt, p, list);
				p.revalidate();
				p.repaint();
			}
		});
		bt5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list.remove(p5);
				p.removeAll();
				defaultRender(3, cnt, p, list);
				p.revalidate();
				p.repaint();
			}
		});
	
		up.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cnt--;
				beforeRender(3, cnt, p, list);
				
				p.revalidate();
				p.repaint();
			}
			
		});
		
		down.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cnt++;
				afterRender(3, cnt,p,list);

				
				p.revalidate();
				p.repaint();
			}
		});

	}
	//멤버변수로 int cnt=0;꼭선언해줄것
	public void defaultRender(int showMax,int cnt,JPanel container,List list) {
		for(int i=0;i<list.size();i++) {
			if(i>(cnt*showMax)-1 && i<((cnt+1)*showMax)) {
				//p.remove(list.get(i));
				container.add((Component) list.get(i));
			}
			else if(((cnt+1)*showMax)-1<i && i<((cnt+2)*showMax)) {
				//p.add(list.get(i));
				container.remove((Component)list.get(i));
			
			}
			else if(cnt==0) {
				for(int a =0;a<showMax;a++) {
					container.add((Component) list.get(a));
				}
			}
	
		}
	}
	//멤버변수로 int cnt=0;꼭선언해줄것
	public void beforeRender(int showMax,int cnt,JPanel container,List list) {
		
		if(cnt<0) {
			JOptionPane.showMessageDialog(this, "처음 페이지입니다");
			cnt++;
			return;
		}
		
		for(int i=0;i<list.size();i++) {
			if(i>(cnt*showMax)-1 && i<((cnt+1)*showMax)) {
				//p.remove(list.get(i));
				container.add((Component) list.get(i));
			}
			else if(((cnt+1)*showMax)-1<i && i<((cnt+2)*showMax)) {
				//p.add(list.get(i));
				container.remove((Component) list.get(i));
			}
			else if(cnt==0) {
				for(int a =0;a<showMax;a++) {
					container.add((Component) list.get(a));
				}
			}
	
		}
	}
	
	//멤버변수로 int cnt=0;꼭선언해줄것
	public void afterRender(int showMax,int cnt,JPanel container,List list) {
		
		if((cnt>=list.size()/showMax && list.size()%showMax==0) || cnt>list.size()/showMax) {
			JOptionPane.showMessageDialog(this, "마지막 페이지 입니다");
			cnt--;
			return;
		}
		for(int i=0;i<list.size();i++) {
			if(i<cnt*showMax) {
				container.remove((Component) list.get(i));
			}
			else if(i>=cnt*showMax && i<(cnt+1)*showMax) {
				container.add((Component) list.get(i));
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("메인 메서드 실행");
		Test test=new Test();
		test.init();
	}

}
