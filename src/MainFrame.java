import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class MainFrame extends Frame
{
	private MenuBar menubar;
	private Menu menu1, menu2, helpmenu;
	private Label lab1, lab2, lab3, lab4, lab5;
	private MaskFormatter MF;
	private JFormattedTextField JFTextFeild;
	private Button btnL, btnUL;
	private JTextField FileInAds,FileOutAds;
	
	public MainFrame() throws ParseException 
	{
		this.setTitle("檔案加密");
		this.setSize(426, 393);
		//this.setLocation(10, 10);
		this.setBackground(Color.gray);
		this.setLayout(null);
		
		//Menubar
		menubar = new MenuBar();
		this.setMenuBar(menubar);
		
		//Menu
		menu1 = new Menu("menu1");
		menu1.add("list1");
		menu1.add("list2");
		menu1.add("list3");
		//menubar.add(menu1);
		
		menu2 = new Menu("menu2");
		menu2.add("list1");
		menu2.add("list2");
		menu2.add("list3");
		//menubar.add(menu2);
		
		helpmenu = new Menu("help");
		helpmenu.add("help0");
		helpmenu.add("help1");
		helpmenu.add("help2");
		//menubar.setHelpMenu(helpmenu);
		
		//---------------------------------------------------------------
		//Label
		lab1 = new Label("請輸入1~8位數密碼",Label.CENTER);
		lab1.setSize(125, 25);
		lab1.setLocation(83, 100);
		//lab1.setBackground(Color.blue);
		this.add(lab1);
		
		//JFTextField
		MF = new MaskFormatter("########");
		JFTextFeild = new JFormattedTextField(MF);
		JFTextFeild.setSize(125, 25);
		JFTextFeild.setLocation(213, 100);
		this.add(JFTextFeild);
				
		//button
		btnL = new Button("進行加密");
		btnL.setSize(75, 50);
		btnL.setLocation(118, 175);
		this.add(btnL);
		
		btnUL = new Button("進行解密");
		btnUL.setSize(75, 50);
		btnUL.setLocation(233, 175);
		this.add(btnUL);
		
		//Labe2
		lab2 = new Label("請選擇檔案輸入位置", Label.RIGHT);
		lab2.setSize(110, 25);
		lab2.setLocation(30, 275);
		//lab2.setBackground(Color.blue);
		this.add(lab2);

		//Labe3		
		lab3 = new Label("請選擇檔案輸出位置", Label.RIGHT);
		lab3.setSize(110, 25);
		lab3.setLocation(30, 310);
		//lab3.setBackground(Color.yellow);
		this.add(lab3);
		
		lab4 = new Label("範例:(C:\\\\Users\\\\Desktop\\\\檔名.副檔名)", Label.LEFT);
		lab4.setSize(250, 25);
		lab4.setLocation(145, 250);
		//lab4.setBackground(Color.yellow);
		this.add(lab4);
		
		//Textfield1
		FileInAds = new JTextField("");
		FileInAds.setSize(250, 25);
		FileInAds.setLocation(145, 275);
		this.add(FileInAds);
		
		lab5 = new Label("範例:(C:\\\\Users\\\\Desktop\\\\檔名.副檔名)", Label.LEFT);
		lab5.setSize(250, 25);
		lab5.setLocation(145, 335);
		//lab5.setBackground(Color.yellow);
		this.add(lab5);
		
		//Textfield2
		FileOutAds = new JTextField();
		FileOutAds.setSize(250 ,25);
		FileOutAds.setLocation(145, 310);
		this.add(FileOutAds);
				
		//WinodowListener
		this.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});	
		btnL.addActionListener(new btnListener());
		btnUL.addActionListener(new btnListener());

		this.setVisible(true);
	}
	
	class btnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try {
				CodeFile();
			} catch (FileNotFoundException e1) {
				// TODO 自動產生的 catch 區塊
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動產生的 catch 區塊
				e1.printStackTrace();
			}
		}
	}
	
	public void CodeFile() throws IOException,FileNotFoundException
	{
		{		
	        String str = JFTextFeild.getText();
	        int password = Integer.valueOf(str);
			FileInputStream filein = new FileInputStream(FileInAds.getText());
			FileOutputStream fileout = new FileOutputStream(FileOutAds.getText());
			byte[] bytes = new byte[filein.available()];
			byte[] bytes2 = new byte[filein.available()];
			int num ;
			while ((num = filein.read(bytes)) != -1)
			{
				for (int i=0; i<num; i++)
				{
					bytes2[i] = (byte) (bytes[i]^password);
				}
				fileout.write(bytes2, 0 ,num);
			}
			filein.close();
			fileout.close();
		}
	}

}

