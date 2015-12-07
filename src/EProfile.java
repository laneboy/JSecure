import java.awt.Image;
import java.io.Serializable;


public class EProfile implements Serializable{
	Image g;
	boolean defaultImage = true;
	String Name = "Empty Slot";
	char[] key = EAES.GEN_KEY();
}
