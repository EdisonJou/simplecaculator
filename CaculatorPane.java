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
		//显示按钮，设置其为不可用的，即只用于显示不能按
		display=new JButton("0");
		display.setEnabled(false);
		add(display,BorderLayout.NORTH);
		//清零按钮,并设置按键时间
		clear=new JButton("Clear");
		clear.addActionListener(new clearEvent());
		add(clear, BorderLayout.SOUTH);
		//建立按键盘
		number=new JPanel();
		number.setLayout(new GridLayout(4, 4));
		//加入按钮
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
	//增加按钮的公共方法
	private void addButton(String name,ActionListener e) {
		JButton newbutton=new JButton(name);
		newbutton.addActionListener(e);
		number.add(newbutton);
	}
	//清零事件处理器
	private class clearEvent implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			result=0;
			start=true;
			lastCacul="=";
			display.setText("0");
		}
	}
	//按数字的情况，触发该事件处理器
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
	//处理符号按钮的操作，考虑了+，-可能为两种情况：一种是运算符，一种是正负号
	private class caculSymble implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String input=event.getActionCommand();
			if(start){//此处的-是表示输入为负数
				if(input.equals("-")){
					display.setText(input);
					start=false;
				}
				else
					lastCacul=input;
			}
			else {//否则表示此处的符号是运算符号不是表示正负的，所以进行之前的计算，并保存此次的符号
				caculate(Double.parseDouble(display.getText()));//计算上次的符号和数字
				lastCacul=input;//计算完之前的运算记住更新此次的运算符号
				start=true;
			}
			
		}
		
	}
	//计算的方法并显示计算结果
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
