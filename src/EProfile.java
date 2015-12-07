
import java.io.Serializable;
import javax.swing.ImageIcon;


public class EProfile implements Serializable{
	ImageIcon g;
	boolean defaultImage = true;
	String Name = "Empty Slot";
	char[] key = EAES.GEN_KEY();
}
