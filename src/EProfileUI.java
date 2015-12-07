import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class EProfileUI extends JPanel{
	SpringLayout spring = new SpringLayout();
	JPanel p1 = new JPanel();//Main Center
	JPanel p2 = new JPanel(new BorderLayout());//Main West
	JPanel p3 = new JPanel(new GridLayout(1,2));//two button panel
	JPanel p4 = new JPanel(new GridLayout(1,1));
	JPanel p5 = new JPanel(new GridLayout(1,1));//Main North
	JPanel p6 = new JPanel(new GridLayout(1,1));
	JPanel p7 = new JPanel(spring);
	JLabel l1;//pic label
	JLabel l2 = new JLabel("<html>Welcomb to Easy Encryption/Decryption!<br>"
			+ "This Software was created to allow users an easy way to send secure messages<br>"
			+ "using AES algorithm in an open source friendly enviorment.<br>"
			+ "You can create profiles that will automatically manage keys for you.<br>"
			+ "Or you can just use the securty options standalone</html>");//Description
	JLabel l3 = new JLabel("Name:");
	JLabel l4 = new JLabel("Associated Key");
	JTextField t1 = new JTextField();
	JTextArea a1 = new JTextArea();
	JScrollPane s1 = new JScrollPane(a1);
	JButton b1 = new JButton("+");
	JButton b2 = new JButton("-");
	JButton b3 = new JButton("CHANGE PIC");
	JButton b4 = new JButton("SAVE");
	String[] items = {""};
	JList list = new JList(items);
	JScrollPane s2 = new JScrollPane(list);
	ArrayList<EProfile> profiles;
	public EProfileUI(ArrayList<EProfile> profiles){
		this.profiles = profiles;
		updateList();
		this.setLayout(new BorderLayout());
		b1.addActionListener(new cPlusMin(true));
		b2.addActionListener(new cPlusMin(false));
		b3.addActionListener(new chooseFile(false));
		b4.addActionListener(new cUpdateName());
		b3.setEnabled(false);
		b4.setEnabled(false);
		list.addListSelectionListener(new cRefresh());
		BufferedImage img;
		try {
			//File f = new File("default.png");
			URL imgurl = this.getClass().getResource("default.png");
			Image g = ImageIO.read(imgurl);
			ImageIcon icon = new ImageIcon(g);
	        l1 = new JLabel(icon);
	        //l1.setText("Here");
		} catch (IOException e) {
			System.out.println("IO ERROR");
		}
        a1.setEnabled(false);
		p3.add(b1);
		p3.add(b2);
		p2.add(s2,BorderLayout.CENTER);
		p2.add(p3, BorderLayout.SOUTH);
		p4.add(l1);
		p4.setBorder(BorderFactory.createEtchedBorder());
		p5.setBorder(BorderFactory.createTitledBorder("Information"));
		p6.setBorder(new EmptyBorder(10, 10, 10, 10));
		l2.setFont(new Font("Serif", Font.PLAIN, 10));
		p6.add(l2);
		p5.add(p6);
		spring.putConstraint(SpringLayout.NORTH,p4, 10, SpringLayout.NORTH, p7);
		spring.putConstraint(SpringLayout.WEST,p4, 10, SpringLayout.WEST, p7);
		p7.add(p4);
		spring.putConstraint(SpringLayout.NORTH, b3, 1, SpringLayout.SOUTH, p4);
		spring.putConstraint(SpringLayout.WEST , b3, 0, SpringLayout.WEST , p4);
		spring.putConstraint(SpringLayout.EAST , b3, 0, SpringLayout.EAST , p4);
		p7.add(b3);
		spring.putConstraint(SpringLayout.WEST , l3, 4, SpringLayout.EAST , p4);
		spring.putConstraint(SpringLayout.NORTH, l3, 4, SpringLayout.NORTH , p4);
		p7.add(l3);
		spring.putConstraint(SpringLayout.EAST , b4, 0, SpringLayout.EAST , p7);
		spring.putConstraint(SpringLayout.NORTH, b4, 2, SpringLayout.SOUTH , l3);
		p7.add(b4);
		spring.putConstraint(SpringLayout.NORTH, t1, 0, SpringLayout.NORTH , b4);
		spring.putConstraint(SpringLayout.SOUTH, t1, 0, SpringLayout.SOUTH , b4);
		spring.putConstraint(SpringLayout.WEST,  t1, 2, SpringLayout.EAST  , p4);
		spring.putConstraint(SpringLayout.EAST,  t1,-2, SpringLayout.WEST  , b4);
		p7.add(t1);
		spring.putConstraint(SpringLayout.WEST , l4, 4, SpringLayout.EAST , p4);
		spring.putConstraint(SpringLayout.NORTH, l4, 4, SpringLayout.SOUTH , t1);
		p7.add(l4);
		spring.putConstraint(SpringLayout.NORTH, s1, 4, SpringLayout.SOUTH, l4);
		spring.putConstraint(SpringLayout.WEST , s1, 4, SpringLayout.EAST , p4);
		spring.putConstraint(SpringLayout.EAST , s1, 0, SpringLayout.EAST , b4);
		spring.putConstraint(SpringLayout.SOUTH, s1, 0, SpringLayout.SOUTH, b3);
		p7.add(s1);
		this.add(p2,BorderLayout.WEST);
		this.add(p7,BorderLayout.CENTER);
		this.add(p5,BorderLayout.NORTH);
	}
	public void updateList() {
		String[] toUp = new String[profiles.size()];
		for(int i=0;i<toUp.length;i++){
			toUp[i] = profiles.get(i).Name;
		}
		list.setListData(toUp);
		
	}
	public class cPlusMin implements ActionListener{
		boolean plus;
		public cPlusMin(boolean plus){
			this.plus = plus;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(plus){
				list.setListData(extendList());
			}
			else{
				strinkList(list.getSelectedIndex());
			}
		}
		
	}
	public String[] extendList(){
		if(items==null){
			String[] toRet = new String[1];
			toRet[0]="Empty Slot";
			items = toRet;
			profiles.add(new EProfile());
			return toRet;
		}
		if(items[0].equals("")){
			String[] toRet = new String[1];
			toRet[0]="Empty Slot";
			items = toRet;
			profiles.add(new EProfile());
			return toRet;
		}
		String[] toRet = new String[items.length+1];
		for(int i=0;i<items.length;i++){
			toRet[i] = items[i];
		}
		toRet[items.length]="Empty Slot";
		profiles.add(new EProfile());
		items = toRet;
		return toRet;
	}
	public void strinkList(int spot){
		System.out.println(items.length);
		if(items == null){
			return;
		}
		if(items.length-1==0){
			return;
		}
		if(spot==-1){
			spot = items.length-1;
		}
		profiles.remove(spot);
		refreshList();
	}
	public class cRefresh implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			b3.setEnabled(true);
			b4.setEnabled(true);
			if(list.getSelectedIndex()!=-1){//
				System.out.println(list.getSelectedIndex());
				current = profiles.get(list.getSelectedIndex());
				t1.setText(current.Name);
				a1.setText(EAES.stringKey(current.key));
			}
		}
	}
	EProfile current = null;
	public class cUpdateName implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(current!=null){
				current.Name = t1.getText();
				refreshList();
			}
		}	
	}
	private void refreshList() {
		String[] toRet = new String[profiles.size()];
		for(int i=0;i<toRet.length;i++){
			toRet[i] = profiles.get(i).Name;
		}
		items = toRet;
		list.setListData(toRet);
	}
	public class chooseFile implements ActionListener{
		boolean save = false;
		public chooseFile(){
		}
		public chooseFile(boolean save){
			this.save = save;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			 if(!save){
				 int returnVal = chooser.showOpenDialog(EProfileUI.this);
				 if(returnVal == JFileChooser.APPROVE_OPTION) {
					 
				 }
			 }
			 else{
				 int returnVal = chooser.showSaveDialog(EProfileUI.this);
				 if(returnVal == JFileChooser.APPROVE_OPTION) {
					 
				 }
			 }
			 //refreshImage();
		}
		
	}
	
}
