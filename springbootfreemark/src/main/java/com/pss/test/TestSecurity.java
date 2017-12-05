package com.pss.test;
import	java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane; 

public class TestSecurity extends Applet { 
	/** 
	 *	
	 */ 
	private static final long serialVersionUID = 1L;
	
	public	TestSecurity() { 
		 System. out .println( "this is good! ");
	} 
	
	public void init() {
		JButton button = new JButton( "Create a file" );
		button.addActionListener( new	ActionListener() {
		public void actionPerformed(ActionEvent evt){
			File file = new File( "C:\\a.txt" );
			try	{
				file.createNewFile();
				JOptionPane. showMessageDialog ( null ,	" 成功创建文件c:\\a.txt" ,	"消息" ,
				JOptionPane. INFORMATION_MESSAGE ); 
			} catch (Exception	ex)	{ 
			JOptionPane. showMessageDialog ( null ,	ex.getMessage(),	"错误" ,	JOptionPane. ERROR_MESSAGE );
		}
		}
	});
		add(button);
	}
}