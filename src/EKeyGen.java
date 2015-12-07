import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class EKeyGen extends JPanel{
	JRadioButton r1 = new JRadioButton();
	JRadioButton r2 = new JRadioButton();
	JComboBox cb = new JComboBox();
	JTextField t1 = new JTextField();
	JButton b1 = new JButton("BROWSE");
	JButton b2 = new JButton("GENERATE");
	JTextArea a1 = new JTextArea("",5,20);
	JLabel l1 = new JLabel("Attach to profile");
	JLabel l2 = new JLabel("Path to saved key file:");
	JLabel l3 = new JLabel("Key(In Hex)");
	SpringLayout spring = new SpringLayout();
	JScrollPane s1= new JScrollPane(a1);
	ArrayList<EProfile> profiles;
	public EKeyGen(ArrayList<EProfile> profiles){
		this.profiles = profiles;
		cb.setEnabled(false);
    	t1.setEnabled(false);
    	b1.setEnabled(false);
		a1.setWrapStyleWord(true);
		b1.addActionListener(new chooseFile(t1,true));
		b2.setEnabled(false);
		b2.addActionListener(new genButton());
		a1.setEditable(false);
		r1.addItemListener(new Selector(0));
		r2.addItemListener(new Selector(1));
		this.setLayout(spring);
		spring.putConstraint(SpringLayout.WEST,l1,24,SpringLayout.NORTH,this);
		spring.putConstraint(SpringLayout.NORTH,l1,12,SpringLayout.NORTH,this);
		add(l1);
		spring.putConstraint(SpringLayout.NORTH,r1,42,SpringLayout.NORTH,this);
		add(r1);
		spring.putConstraint(SpringLayout.NORTH,cb, 10,SpringLayout.SOUTH,l1);
		spring.putConstraint(SpringLayout.WEST, cb, 0,SpringLayout.WEST,l1);
		spring.putConstraint(SpringLayout.EAST, cb, -4,SpringLayout.EAST,this);
		spring.putConstraint(SpringLayout.SOUTH,cb, 42, SpringLayout.SOUTH, l1);
		add(cb);
		spring.putConstraint(SpringLayout.NORTH,l2,12,SpringLayout.SOUTH,cb);
		spring.putConstraint(SpringLayout.WEST,l2,24,SpringLayout.NORTH,this);
		add(l2);
		spring.putConstraint(SpringLayout.NORTH,r2,40,SpringLayout.SOUTH,r1);
		add(r2);
		ButtonGroup g = new ButtonGroup();
		g.add(r1);
		g.add(r2);
		spring.putConstraint(SpringLayout.NORTH,t1,8,SpringLayout.SOUTH,l2);
		spring.putConstraint(SpringLayout.WEST,t1,0,SpringLayout.WEST,l1);
		spring.putConstraint(SpringLayout.EAST,t1,-4,SpringLayout.WEST,b1);
		spring.putConstraint(SpringLayout.SOUTH,t1,0,SpringLayout.SOUTH,b1);
		add(t1);
		spring.putConstraint(SpringLayout.NORTH,b1,0,SpringLayout.NORTH,t1);
		spring.putConstraint(SpringLayout.EAST,b1,-4,SpringLayout.EAST,this);
		add(b1);
		spring.putConstraint(SpringLayout.WEST,l3,24,SpringLayout.NORTH,this);
		spring.putConstraint(SpringLayout.NORTH,l3,12,SpringLayout.SOUTH,t1);
		add(l3);
		spring.putConstraint(SpringLayout.NORTH,s1,8,SpringLayout.SOUTH,l3);
		spring.putConstraint(SpringLayout.WEST,s1,0,SpringLayout.WEST,l3);
		spring.putConstraint(SpringLayout.EAST,s1,-4,SpringLayout.WEST,b2);
		spring.putConstraint(SpringLayout.SOUTH,s1,-4,SpringLayout.SOUTH,this);
		add(s1);
		spring.putConstraint(SpringLayout.SOUTH,b2,0,SpringLayout.SOUTH,s1);
		spring.putConstraint(SpringLayout.EAST,b2,-4,SpringLayout.EAST,this);
		add(b2);

		
	}
	public void fillComboBar() {
		//cb.set
		String[] profilelist = new String[profiles.size()];
		for(int i=0;i<profilelist.length;i++){
			profilelist[i] = profiles.get(i).Name;
		}
		cb.setModel(new DefaultComboBoxModel(profilelist));
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
				 int returnVal = chooser.showOpenDialog(EKeyGen.this);
				 if(returnVal == JFileChooser.APPROVE_OPTION) {
				       t.setText(chooser.getSelectedFile().getAbsolutePath());
				 }
			 }
			 else{
				 int returnVal = chooser.showSaveDialog(EKeyGen.this);
				 if(returnVal == JFileChooser.APPROVE_OPTION) {
				       t.setText(chooser.getSelectedFile().getAbsolutePath());
				 }
			 }
			 EKeyGen.this.CheckIsReady();
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
	        	cb.setEnabled(true);
	        	t1.setEnabled(false);
	        	b1.setEnabled(false);
	        	break;
	        case 1:
	        	cb.setEnabled(false);
	        	t1.setEnabled(true);
	        	b1.setEnabled(true);
	        	break;
	        }
	    }
	    //else if (e.getStateChange() == ItemEvent.DESELECTED) {
	        // Your deselected code here.
	    //}
	    CheckIsReady();
	}
	}
	public class genButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			char[] key = EAES.GEN_KEY();
			if(r2.isSelected()){
				File f = new File(t1.getText());
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(f);
					//f.write
					if(!f.exists()){
						f.createNewFile();
					}
					for(int i=0;i<key.length;i++){
						fos.write(key[i]);
					}
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else{
				profiles.get(cb.getSelectedIndex()).key = key;
				
			}
			a1.setText(EAES.stringKey(key));
			EAES.printKey(key);
		}
	}
	public void CheckIsReady(){
		if((r1.isSelected() && cb.getSelectedIndex()!=-1)||(r2.isSelected() && !t1.getText().equalsIgnoreCase(""))){
			b2.setEnabled(true);
		}
		else{
			b2.setEnabled(false);
		}
	}
}
