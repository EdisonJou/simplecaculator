package caculator;
import javax.swing.*;
public class DisplayCaculator {

	public static void main(String[] args) {
		JFrame caculater=new JFrame();
		CaculatorPane pane=new CaculatorPane();
		caculater.add(pane);
		caculater.setTitle("Caculator");
		caculater.setVisible(true);
		caculater.pack();
	}

}
