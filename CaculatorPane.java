package caculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaculatorPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton display;
	private JButton clear;
	private double result;
	private String lastCacul;
	private boolean start;
	private JPanel number;
	public CaculatorPane() {
		setLayout(new BorderLayout());
		start=true;
		result=0;
		lastCacul="=";
		//��ʾ��ť��������Ϊ�����õģ���ֻ������ʾ���ܰ�
		display=new JButton("0");
		display.setEnabled(false);
		add(display,BorderLayout.NORTH);
		//���㰴ť,�����ð���ʱ��
		clear=new JButton("Clear");
		clear.addActionListener(new clearEvent());
		add(clear, BorderLayout.SOUTH);
		//����������
		number=new JPanel();
		number.setLayout(new GridLayout(4, 4));
		//���밴ť
		addNumber numb= new addNumber();
		caculSymble symble =new caculSymble();
		addButton("7", numb);
		addButton("8", numb);
		addButton("9", numb);
		addButton("+", symble);
		addButton("4", numb);
		addButton("5", numb);
		addButton("6", numb);
		addButton("-", symble);
		addButton("1", numb);
		addButton("2", numb);
		addButton("3", numb);
		addButton("*", symble);
		addButton("0", numb);
		addButton(".", numb);
		addButton("=", symble);
		addButton("/", symble);
		
		add(number, BorderLayout.CENTER);
	}
	//���Ӱ�ť�Ĺ�������
	private void addButton(String name,ActionListener e) {
		JButton newbutton=new JButton(name);
		newbutton.addActionListener(e);
		number.add(newbutton);
	}
	//�����¼�������
	private class clearEvent implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			result=0;
			start=true;
			lastCacul="=";
			display.setText("0");
		}
	}
	//�����ֵ�������������¼�������
	private class addNumber implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String input=event.getActionCommand();
			if(start){
				display.setText("");
				start=false;
			}
			display.setText(display.getText()+input);
		}
	}
	//������Ű�ť�Ĳ�����������+��-����Ϊ���������һ�����������һ����������
	private class caculSymble implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String input=event.getActionCommand();
			if(start){//�˴���-�Ǳ�ʾ����Ϊ����
				if(input.equals("-")){
					display.setText(input);
					start=false;
				}
				else
					lastCacul=input;
			}
			else {//�����ʾ�˴��ķ�����������Ų��Ǳ�ʾ�����ģ����Խ���֮ǰ�ļ��㣬������˴εķ���
				caculate(Double.parseDouble(display.getText()));//�����ϴεķ��ź�����
				lastCacul=input;//������֮ǰ�������ס���´˴ε��������
				start=true;
			}
			
		}
		
	}
	//����ķ�������ʾ������
	private void caculate(double x){
		switch (lastCacul) {
		case "-":result-=x;
			break;
		case "+":result+=x;
		    break;
		case "*":result*=x;
		    break;
		case "/":result/=x;
		    break;
		case "=":result=x;
		    break;
		default:
			break;
		}
		display.setText(""+result);
	}

}
