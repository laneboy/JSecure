import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Easy Encrypter/Decrypter
public class EE extends JFrame{
	ArrayList<EProfile> profiles = loadProfiles();
	JPanel p1 = new JPanel(new BorderLayout());
	JTabbedPane p2 = new JTabbedPane();
	JLabel p3 = new JLabel();
	JPanel p4 = new EProfileUI(profiles);//Main
	JPanel p5 = new EEncrypt(profiles);//Ency
	JPanel p6 = new EDecrypt(profiles);//Dec
	JPanel p7 = new EKeyGen(profiles);//KeyGen
	public EE() throws IOException{
		p2.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        if(p2.getSelectedIndex()==1){
		        	((EEncrypt)p5).fillComboBar();
		        }
		        else if(p2.getSelectedIndex()==2){
		        	((EDecrypt)p6).fillComboBar();
		        }
		        else if(p2.getSelectedIndex()==3){
		        	((EKeyGen)p7).fillComboBar();
		        }
		    }
		});
		p2.addTab("Main", p4);
		p2.addTab("Encrypt", p5);
		p2.addTab("Decrypt", p6);
		p2.addTab("Key Gen", p7);
		p3.setText("Easy Encrypter/Decrypter ©2015 by Lucas Rivera, Andrii Hydsd and Jazmin Velez");
		p1.add(BorderLayout.SOUTH,p3);
		p1.add(BorderLayout.CENTER,p2);
		add(p1);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(500, 400);
		this.setMinimumSize(this.getSize());
		this.setTitle("Easy Encrypter/Decrypter");
		URL imgurl2 = this.getClass().getResource("lock.png");
		Image g = ImageIO.read(imgurl2);
		this.setIconImage(g);
		this.setVisible(true);
	}
	public ArrayList<EProfile> loadProfiles(){//check to see if a profile system exist first
		return new ArrayList<EProfile>();
	}
	public static char[] ReadFileToCharArray(String filePath) throws IOException {	
		char[] toRet = new char[(int) (new File(filePath)).length()];
		byte[] data = Files.readAllBytes(Paths.get(filePath));
		for(int i=0;i<toRet.length;i++){
			if(data[i]<0){
				toRet[i] = (char)(256+data[i]);
			}
			else{
				toRet[i] = (char) data[i];
			}
		}
		return  toRet;	
	}
	public static void WriteCharToFile(String path,char[] data) throws IOException{
		File f = new File(path);
		if(!f.exists()){
			f.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(path);
		System.out.println(data.length);
		for(int i=0;i<data.length;i++){
			fos.write(data[i]);
		}
	    fos.close();
	}
	public static void main(String[] args){
		try {
			new EE();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
