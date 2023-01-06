package memopad;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class Simplenotepad extends JFrame implements ActionListener{
	JMenuItem itemNew, itemOpen, itemSave, itemExit, itemFont1, itemFont2, itemFont3, itemFontRed, itemFontBlue, itemFontBlack, item10, item15, item20;
	JTextArea area = new JTextArea(); 
	JButton btnNew, btnOpen, btnSave;
	
	public Simplenotepad() {
		super("메모장");
		makeMenu();
		makeToolbar();
		add(area);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200, 200, 800, 600);
		setVisible(true);
	}
	
	public void makeToolbar() {
		JToolBar toolBar = new JToolBar();
		btnNew = new JButton(new ImageIcon("image/새문서.png"));
		btnOpen = new JButton(new ImageIcon("image/파일열기.png"));
		btnSave = new JButton(new ImageIcon("image/저장.png"));
		btnNew.addActionListener(btnL);
		btnOpen.addActionListener(btnL);
		btnSave.addActionListener(btnL);
		toolBar.add(btnNew);
		toolBar.add(btnOpen);
		toolBar.add(btnSave);
		add(toolBar,"North");
	}
	
	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object eBtn = e.getSource();
			if(eBtn == btnNew) {
				area.setText("");
			}else if(eBtn == btnOpen) {
				readFile();
			}else if(eBtn == btnSave) {
				writeFile();
			}
		}
	};
	
	public void makeMenu() {
		JMenuBar menuBar = new JMenuBar();   //메뉴표시줄
		JMenu menuFile = new JMenu("File");   //상위메뉴
		JMenu menuFormat = new JMenu("Format");  //상위메뉴
		JMenu menuFont = new JMenu("Font");
		JMenu menuFontColor = new JMenu("Font Color");  //하위메뉴
		JMenu menuFontSize = new JMenu("Font Size");
		
		//JMenuItem : 상위메뉴 또는 하위메뉴를 클릭했을 때 선택할 수 있는 항목
		itemNew = new JMenuItem("New");
		itemOpen = new JMenuItem("Open");
		itemSave = new JMenuItem("Save");
		itemExit = new JMenuItem("Exit");
		itemFont1 = new JMenuItem("궁서체");
		itemFont2 = new JMenuItem("맑은 고딕");
		itemFont3 = new JMenuItem("휴먼편지체");
		item10 = new JMenuItem("10px");
		item15 = new JMenuItem("15px");
		item20 = new JMenuItem("20px");
		itemFontRed = new JMenuItem("Red");
		itemFontBlue = new JMenuItem("Blue");
		itemFontBlack = new JMenuItem("Black");
		
		itemNew.addActionListener(this);
		itemOpen.addActionListener(this);
		itemSave.addActionListener(this);
		itemExit.addActionListener(this);
		itemFont1.addActionListener(this);
		itemFont2.addActionListener(this);
		itemFont3.addActionListener(this);
		itemFontRed.addActionListener(this);
		itemFontBlue.addActionListener(this);
		itemFontBlack.addActionListener(this);
		item10.addActionListener(this);
		item15.addActionListener(this);
		item20.addActionListener(this);
		
		//File 상위메뉴에 메뉴항목들을 추가
		menuFile.add(itemNew);
		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.add(itemExit);
		
		//Format 상위메뉴에 하위메뉴 추가
		menuFormat.add(menuFont);
		menuFormat.add(menuFontColor);
		menuFormat.add(menuFontSize);
		
		//FontColor 하위메뉴에 메뉴항목들을 추가
		menuFontColor.add(itemFontRed);
		menuFontColor.add(itemFontBlue);
		menuFontColor.add(itemFontBlack);
		
		//FontSize 하위메뉴에 메뉴항목들을 추가
		menuFontSize.add(item10);
		menuFontSize.add(item15);
		menuFontSize.add(item20);
		//Font 하위메뉴에 메뉴항목들을 추가
		menuFont.add(itemFont1);
		menuFont.add(itemFont2);
		menuFont.add(itemFont3);
		//메뉴바에 File , Format 상위메뉴 추가
		menuBar.add(menuFile);
		menuBar.add(menuFormat);
		
		//프레임에 메뉴바 설정
		setJMenuBar(menuBar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem eItem = (JMenuItem) e.getSource();
		if(eItem == itemNew) {  //새로 만들기
			area.setText("");
		}else if(eItem == itemOpen) {  //파일열기
			readFile();
		}else if(eItem == itemSave) {  //파일저장
			writeFile();
		}else if(eItem == itemExit) {  //나가기
			System.exit(0);
		}else if(eItem == itemFont1) {
			area.setFont(new Font("궁서체", Font.PLAIN, area.getFont().getSize()));
		}else if(eItem == itemFont2) {
			area.setFont(new Font("맑은 고딕", Font.PLAIN, area.getFont().getSize()));
		}else if(eItem == itemFont3) {
			area.setFont(new Font("휴먼편지체", Font.PLAIN, area.getFont().getSize()));
		}else if(eItem == itemFontRed) {
			area.setForeground(Color.RED);
		}else if(eItem == itemFontBlue) {
			area.setForeground(Color.BLUE);
		}else if(eItem == itemFontBlack) {
			area.setForeground(Color.BLACK);
		}else if(eItem == item10) {  //글꼴크기 10
			area.setFont(new Font(area.getFont().getFamily(), Font.PLAIN, 10));
		}else if(eItem == item15) {  //글꼴크기 20
			area.setFont(new Font(area.getFont().getFamily(), Font.PLAIN, 15));
		}else if(eItem == item20) {  //글꼴크기 30
			area.setFont(new Font(area.getFont().getFamily(), Font.PLAIN, 20));
		}
	}
	public void readFile() {
		area.setText("");
		FileDialog fileDlg = null;
		fileDlg = new FileDialog(this,"File Open",FileDialog.LOAD);
		fileDlg.setVisible(true);
		System.out.println(fileDlg.getDirectory());
		System.out.println(fileDlg.getFile());
		FileReader fReader = null;
		BufferedReader bufReader = null;
			try {
				fReader = new FileReader(new File(fileDlg.getDirectory()+"/"+fileDlg.getFile()));
				bufReader = new BufferedReader(fReader);
				String line = "";
				while((line=bufReader.readLine()) != null) {
					area.append(line);
				}
				bufReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void writeFile() {
		FileDialog fileDlg = null;
		fileDlg = new FileDialog(this, "File Save", FileDialog.SAVE);
		fileDlg.setVisible(true);
		System.out.println(fileDlg.getDirectory());
		System.out.println(fileDlg.getFile());
		FileWriter fWriter = null;
		BufferedWriter bufWriter = null;
		try {
			fWriter = new FileWriter(new File(fileDlg.getDirectory()+"/"+fileDlg.getFile()));
			bufWriter = new BufferedWriter(fWriter);
			bufWriter.write(area.getText());
			bufWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Simplenotepad();
	}

}
