import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class EDecrypt extends JPanel{
	JLabel l1 = new JLabel("File from computer:");
	JLabel l3 = new JLabel("Encrypt with profile?     Yes");
	JLabel l4 = new JLabel("Path to save file:");
	JLabel l5 = new JLabel("No");
	JTextField t1 = new JTextField();
	JTextField t3 = new JTextField();
	JRadioButton r3 = new JRadioButton();
	JRadioButton r4 = new JRadioButton();
	JButton b1 = new JButton("BROWSE");
	JButton b3 = new JButton("BROWSE");
	JButton b4 = new JButton("START DECRYPTION");
	JComboBox cb = new JComboBox();
	SpringLayout spring = new SpringLayout();
	CardLayout cards = new CardLayout();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JProgressBar w2 = new JProgressBar();
	ArrayList<EProfile> profiles;
	public EDecrypt(ArrayList<EProfile> profiles){
		this.profiles = profiles;
		r3.addItemListener(new Selector(0));
		r4.addItemListener(new Selector(1));
		spring.putConstraint(SpringLayout.WEST,l1,24,SpringLayout.NORTH,this);
		spring.putConstraint(SpringLayout.NORTH,l1,12,SpringLayout.NORTH,this);
		this.setLayout(spring);
		add(l1);
		spring.putConstraint(SpringLayout.NORTH,t1,32,SpringLayout.NORTH,this);
		spring.putConstraint(SpringLayout.WEST,t1,0,SpringLayout.WEST,l1);
		spring.putConstraint(SpringLayout.EAST,t1,-4,SpringLayout.WEST,b1);
		spring.putConstraint(SpringLayout.SOUTH,t1,0,SpringLayout.SOUTH,b1);
		add(t1);
		spring.putConstraint(SpringLayout.NORTH,b1,32,SpringLayout.NORTH,this);
		//spring.putConstraint(SpringLayout.WEST,b1,0,SpringLayout.WEST,b3);
		spring.putConstraint(SpringLayout.EAST,b1,-40,SpringLayout.EAST,this);
		add(b1);
		spring.putConstraint(SpringLayout.WEST,l3,24,SpringLayout.NORTH,this);
		spring.putConstraint(SpringLayout.NORTH,l3,100,SpringLayout.NORTH,this);
		add(l3);
		spring.putConstraint(SpringLayout.NORTH,r3,-16,SpringLayout.NORTH,l3);
		spring.putConstraint(SpringLayout.SOUTH,r3,16,SpringLayout.SOUTH,l3);
		spring.putConstraint(SpringLayout.WEST,r3,0,SpringLayout.EAST,l3);
		add(r3);
		spring.putConstraint(SpringLayout.EAST,l5,48,SpringLayout.WEST,r3);
		spring.putConstraint(SpringLayout.NORTH,l5,0,SpringLayout.NORTH,l3);
		add(l5);
		spring.putConstraint(SpringLayout.NORTH,r4,-16,SpringLayout.NORTH,l5);
		spring.putConstraint(SpringLayout.SOUTH,r4,16,SpringLayout.SOUTH,l5);
		spring.putConstraint(SpringLayout.WEST,r4,0,SpringLayout.EAST,l5);
		add(r4);
		ButtonGroup g2 = new ButtonGroup();
		g2.add(r3);
		g2.add(r4);
		p1.setLayout(cards);
		p1.add(cb,"CB");
		p1.add(new InnerCard(p1),"IC");
		p1.add(p2,"BP");
		//p1.setBorder(BorderFactory.createTitledBorder("-"));
		cards.show(p1, "IC");//TODO when done switch to BP
		spring.putConstraint(SpringLayout.NORTH,p1,20,SpringLayout.SOUTH,l5);
		spring.putConstraint(SpringLayout.SOUTH,p1,48,SpringLayout.SOUTH,l5);
		spring.putConstraint(SpringLayout.WEST,p1,0,SpringLayout.WEST,l3);
		spring.putConstraint(SpringLayout.EAST,p1,0,SpringLayout.EAST,b1);
		add(p1);
		spring.putConstraint(SpringLayout.WEST,l4,0,SpringLayout.WEST,l3);
		spring.putConstraint(SpringLayout.NORTH,l4,12,SpringLayout.SOUTH,p1);
		add(l4);
		spring.putConstraint(SpringLayout.NORTH,t3,32,SpringLayout.SOUTH,p1);
		spring.putConstraint(SpringLayout.WEST,t3,0,SpringLayout.WEST,l4);
		spring.putConstraint(SpringLayout.EAST,t3,-4,SpringLayout.WEST,b1);
		spring.putConstraint(SpringLayout.SOUTH,t3,0,SpringLayout.SOUTH,b3);
		add(t3);
		spring.putConstraint(SpringLayout.NORTH,b3,0,SpringLayout.NORTH,t3);
		spring.putConstraint(SpringLayout.WEST,b3,0,SpringLayout.WEST,b1);
		spring.putConstraint(SpringLayout.EAST,b3,0,SpringLayout.EAST,b1);
		add(b3);
		spring.putConstraint(SpringLayout.NORTH,w2,-12,SpringLayout.SOUTH,this);
		spring.putConstraint(SpringLayout.WEST,w2,0,SpringLayout.WEST,this);
		spring.putConstraint(SpringLayout.EAST,w2,0,SpringLayout.EAST,this);
		add(w2);
		spring.putConstraint(SpringLayout.NORTH,b4,10,SpringLayout.SOUTH,t3);
		spring.putConstraint(SpringLayout.WEST,b4,0,SpringLayout.WEST,this);
		spring.putConstraint(SpringLayout.EAST,b4,0,SpringLayout.EAST,this);
		spring.putConstraint(SpringLayout.SOUTH,b4,0,SpringLayout.NORTH,w2);

		add(b4);
	}
	public void fillComboBar() {
		//cb.set
		String[] profilelist = new String[profiles.size()];
		for(int i=0;i<profilelist.length;i++){
			profilelist[i] = profiles.get(i).Name;
		}
		cb.setModel(new DefaultComboBoxModel(profilelist));
	}
	public class InnerCard extends JPanel{//pls stop
		JLabel l1 = new JLabel("Key:");
		JTextField t1 = new JTextField();
		JButton b1 = new JButton("BROWSE");
		SpringLayout spring = new SpringLayout();
		public InnerCard(JPanel ref){
			this.setLayout(spring);
			spring.putConstraint(SpringLayout.NORTH,l1,4,SpringLayout.NORTH,this);
			add(l1);
			spring.putConstraint(SpringLayout.NORTH,t1,0,SpringLayout.NORTH,this);
			spring.putConstraint(SpringLayout.WEST,t1,4,SpringLayout.EAST,l1);
			spring.putConstraint(SpringLayout.EAST,t1,-4,SpringLayout.WEST,b1);
			spring.putConstraint(SpringLayout.SOUTH,t1,0,SpringLayout.SOUTH,b1);
			add(t1);
			spring.putConstraint(SpringLayout.NORTH,b1,0,SpringLayout.NORTH,t1);
			spring.putConstraint(SpringLayout.EAST,b1,0,SpringLayout.EAST,this);
			add(b1);
			
		}
	}
	public class Selector implements ItemListener{
		int index = 0;
	public Selector(int index){
		this.index = index;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	        switch(index){
	        case 0:
	        	cards.show(p1, "CB");
	        	break;
	        case 1:
	        	cards.show(p1, "IC");
	        	break;
	        }
	    }
	    //else if (e.getStateChange() == ItemEvent.DESELECTED) {
	        // Your deselected code here.
	    //}
	    CheckIsReady();
	}
	
}
public class chooseFile implements ActionListener{
	JTextField t = null;
	boolean save = false;
	public chooseFile(JTextField toUpdate){
		t = toUpdate;
	}
	public chooseFile(JTextField toUpdate,boolean save){
		t = toUpdate;
		this.save = save;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		 if(!save){
			 int returnVal = chooser.showOpenDialog(EDecrypt.this);
			 if(returnVal == JFileChooser.APPROVE_OPTION) {
			       t.setText(chooser.getSelectedFile().getAbsolutePath());
			 }
		 }
		 else{
			 int returnVal = chooser.showSaveDialog(EDecrypt.this);
			 if(returnVal == JFileChooser.APPROVE_OPTION) {
			       t.setText(chooser.getSelectedFile().getAbsolutePath());
			 }
		 }
		 CheckIsReady();
	}
	
}
public void CheckIsReady(){//TODO enable encryption method
	//if(r1.isSelected() && !t1.getText().equalsIgnoreCase("") && r3.isSelected() && cb.getSelectedIndex()!=-1 && !t3.getText().equalsIgnoreCase("")){
	//	b4.setEnabled(true);
	//}
	//else if(r1.isSelected() && !t1.getText().equalsIgnoreCase("") && r4.isSelected() && !ic.t1.getText().equalsIgnoreCase("") && !t3.getText().equalsIgnoreCase("")){
	//	b4.setEnabled(true);
	//}
	//else{
	//	b4.setEnabled(false);
	//}
}
}

