package miniNotePad;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.junit.Test;

import cosntvar.ConstVar;
import miniNotePad.custom.center.SimpleDrawLineTextArea;
import miniNotePad.custom.north.LineNumberHeaderView;
import miniNotePad.custom.west.SimpleDrawLineLable;
import miniNotePad.dao.FileTextDAO;
import miniNotePad.dao.TextDAO;
import miniNotePad.line.LineNumber;
public class JNotePad extends JFrame {
	private JMenuBar menuBar;
	
	//文档菜单
	private JMenu fileMenu;
	private JMenuItem menuOpen;
	private JMenuItem menuSave;
	private JMenuItem menuSaveAs;
	private JMenuItem menuClose;
	//编辑菜单
	private JMenu editMenu;
	private JMenuItem menuCut;
	private JMenuItem menuCopy;
	private JMenuItem menuPaste;
	//关于菜单
	private JMenu aboutMenu;
	private JMenuItem menuAbout;
	//文字编辑
	//private JTextArea textArea;
	private SimpleDrawLineTextArea textArea;
	private JLabel stateBar;
	//弹出菜单
	private JPopupMenu popUpMenu;
	//文本操作
	private TextDAO textDAO;
	private JFileChooser fileChooser;
	
	//行号显示控件
	SimpleDrawLineLable drawLineLable;
	//显示行号,以1起始
	LineNumber lineNumber;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public JNotePad() {
		//
		init();
		//
		initImages();
		//初始化菜单
		initComponents();
		//初始化快捷键
		initAccelerator();
		//初始化监听器
		initEventListeners();
	}

	public JNotePad(TextDAO textDAO) {
		this();
		this.textDAO = textDAO;
		//测试
		/*
		 * for(int i=1;i<=80;i++) 
		 * { textArea.append(String.valueOf(i)+"\r\n");
		 * 
		 * }
		 */
		//
	}
	
	public void init() {
		lineNumber = new LineNumber(1);
		drawLineLable = new SimpleDrawLineLable(lineNumber,ConstVar.LINE_SPACE);
	}
	
	private void initImages() {
		String captionImagePath="/image/test7.png";
		try {
			Image captionImage = ImageIO.read(this.getClass().getResource(captionImagePath));
			this.setIconImage(captionImage);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void initEventListeners() {
		initSystemButtonListener();
		initEditListener();
		initMenuListener();
	}

	private void initSystemButtonListener() {
		//设置默认关闭按钮事件
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//自定义关闭按钮事件
		addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent event) {
						closeWindow(event);
					}
				});
	}

	private void initEditListener() {
		textArea.addMouseListener(
		new MouseAdapter() {
			//按钮右键释放,弹出文本编辑区域菜单
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					popUpMenu.show(editMenu, e.getX(),e.getY());
				}
			}
			//按钮左键点击，文本编辑区域菜单消失
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					popUpMenu.setVisible(false);
				}
			}
			
		});
		
		textArea.addKeyListener(new KeyAdapter() {
			
			// 文本编辑区键盘事件
			public void keyTyped(KeyEvent event) {
				int charCode = event.getKeyChar(); 
				//回车
				if(charCode == 10) {
					System.out.println("keyTyped(...) 脱字号:"+textArea.getCaretPosition());
					drawLineLable.reDrawLine(textArea.getCaretPosition()+1);
				}
				System.out.println("输入的字符: ["+(event.getKeyChar()=='\n'?"回车符":+event.getKeyChar())+","+Character.hashCode(event.getKeyChar()) +"]");
				jtextAreaActionPerformed(event);
			}
			
		});
		
		textArea.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				System.out.println("caretUpdate() 脱字号:"+textArea.getCaretPosition());
			}
			
		});
		
	}
	
	private void initMenuListener() {
		//菜单-开启文件
		menuOpen.addActionListener(this::openFile);
		//菜单-储存文档
		menuSave.addActionListener(this::saveFile);
		//菜单-另存新文件
		menuSaveAs.addActionListener(this::saveFileAs);
		//菜单-关闭文档
		menuClose.addActionListener(this::closeFile);
		//菜单-剪切
		menuCut.addActionListener(this::cut);
		//菜单-复制
		menuCut.addActionListener(this::copy);
		//菜单-粘贴
		menuCut.addActionListener(this::paste);
		//菜单-关与
		menuAbout.addActionListener(event->{
			
			ImageIcon icon=new ImageIcon(this.getClass().getResource("/image/JminiNotePad.jpg"));
			//显示对话框
			JOptionPane.showOptionDialog(null,
					"miniNotePad 0.1\n", 
					"关于JNotePad", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, 
					icon, null, null);
		});
	     
	}
	

	private void openFile(ActionEvent event)
	{
		if(stateBar.getText().contentEquals("未修改")) {
			//文档选择对话框
			showFileDialog();
		}
		else
		{
			int option = JOptionPane.showConfirmDialog(this, "文件已经修改,是否保存?", "存储文件", 
						                               JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, 
						                               null);
			switch(option) {
			case JOptionPane.YES_OPTION: //保存文件
				//saveFile();
				break;
			case JOptionPane.NO_OPTION:	 //放弃保存文件
				showFileDialog();
				break;
			}
		}
	}
	
	private void showFileDialog() {
		//文档选择对话框
		int option = fileChooser.showDialog(null, null);
		//用户按下确定键
		if(option == JFileChooser.APPROVE_OPTION) {
			try {
				//设定文件标题 
				setTitle(fileChooser.getSelectedFile().toString());
				//清除前一次文件
				textArea.setText("");
				//设定状态栏
				stateBar.setText("未修改");
				//读取文档
				String text = textDAO.read(fileChooser.getSelectedFile().toString());
				//附加至文字标编辑区
				textArea.setText(text);
				
			}catch(Throwable t){
				JOptionPane.showMessageDialog(this, t.toString(),"打开文件失败",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	private void saveFile(ActionEvent event)
	{
		Path path= Paths.get(getTitle());
		//若指定的文件不存在,执行另存为文件
		if(Files.notExists(path))
		{
			saveFileAs(event);
		}
		else
		{
			try {
				//储存文档
				textDAO.save(path.toString(), textArea.getText());
				//设置状态栏为未修改
				stateBar.setText("未修改");
			}catch(Throwable e) {
				JOptionPane.showMessageDialog(this, e.toString(),"写入文件失败",JOptionPane.ERROR_MESSAGE);
			}
		
		}
	}
	
	private void saveFileAs(ActionEvent event)
	{
		//显示文档对话框
		int option=fileChooser.showDialog(null, null);
		//如果确认选取文档
		if(option==JFileChooser.APPROVE_OPTION) {
			//在标题栏上设定文件名
			setTitle(fileChooser.getSelectedFile().toString());
			//建立文档
			textDAO.create(fileChooser.getSelectedFile().toString());
			//文档存储
			saveFile(event);
		}
	}
	
	private void closeFile(ActionEvent event)
	{
		if(stateBar.getText().equals("未修改")){
			//文件没有修改,不需要存储
			//释放窗口资源,关闭程序
			dispose();
		}else {//文件已经修改,需要另存为 
			int option = JOptionPane.showConfirmDialog(null, "文件已经修改,是否存储?",
													   "保存文件?",JOptionPane.YES_NO_OPTION,
													   JOptionPane.WARNING_MESSAGE,null);
			switch(option) {
			case JOptionPane.YES_OPTION:
				saveFile(event);
			case JOptionPane.NO_OPTION:
				dispose();
			}
		}
	}
	
	private void cut(ActionEvent event)
	{
		textArea.cut();
		stateBar.setText("已修改");
		popUpMenu.setVisible(false);
	}
	
	private void copy(ActionEvent event)
	{
		textArea.copy();
		popUpMenu.setVisible(false);
	}
	
	private void paste(ActionEvent event)
	{
		textArea.paste();
		stateBar.setText("已修改");
		popUpMenu.setVisible(false);
	}
	
	private void closeWindow(WindowEvent event) {
		
		//关闭主程序界面
		closeFile(new ActionEvent(event.getSource(),event.getID(),"windowClosing"));
	}
	
	private void jtextAreaActionPerformed(KeyEvent event)
	{	/*回车符被过滤*/
		stateBar.setText("已修改");
	}
	
	private void initComponents() {
		fileChooser = new JFileChooser();
		//设置标题
		setTitle("新增文本文档");
		//设置大小
		setSize(500,560);
		//初始化子菜单
		initFileMenu();
		initEditMenu();
		initAboutMenu();
		//初始化菜单列
		initMenuBar();
		//初始化文本编辑区域和状态栏
		initTextArea();
		initStateBar();
		//初始化文本编辑区域的弹出菜单
		showPopMenu();
	}
	


	private void initFileMenu() {
		String [] contents= {"文件","打开","保存","另存为..","关闭"};
		fileMenu = new JMenu(contents[0]);
		menuOpen=new JMenuItem(contents[1]);
		menuSave=new JMenuItem(contents[2]);
		menuSaveAs=new JMenuItem(contents[3]);
		menuClose=new JMenuItem(contents[4]);
		fileMenu.add(menuOpen);
		fileMenu.addSeparator();
		fileMenu.add(menuSave);
		fileMenu.add(menuSaveAs);
		fileMenu.addSeparator();
		fileMenu.add(menuClose);
	}

	private void initEditMenu() {
		String [] contents= {"编辑","剪切","拷贝","粘贴"};
		editMenu = new JMenu(contents[0]);
		menuCut = new JMenuItem(contents[1]);
		menuCopy = new JMenuItem(contents[2]);
		menuPaste = new JMenuItem(contents[3]);
		
		editMenu.add(menuCut);
		editMenu.add(menuCopy);
		editMenu.add(menuPaste);
	}

	private void initAboutMenu() {
		//关于菜单
		aboutMenu=new JMenu("关于");
		menuAbout=new JMenuItem("关于JNotePad");
		aboutMenu.add(menuAbout);
	}
	
	/**
	 * 初始化菜单栏
	 */
	private void initMenuBar() {
		//初始化关于"菜单列及子菜
		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(aboutMenu);
		//将子菜单加入关于菜单
		setJMenuBar(menuBar);
	}
	
	/**
	 * 初始化快捷键 
	 */
	private void initAccelerator() {
		Map<String,Integer> acceleratorMap = new HashMap<String,Integer>();
		acceleratorMap.put("打开",KeyEvent.VK_O);
		acceleratorMap.put("保存",KeyEvent.VK_S);
		acceleratorMap.put("关闭",KeyEvent.VK_Q);
		acceleratorMap.put("剪切",KeyEvent.VK_X);
		acceleratorMap.put("拷贝",KeyEvent.VK_C);
		acceleratorMap.put("粘贴",KeyEvent.VK_V);
		
		menuOpen.setAccelerator(KeyStroke.getKeyStroke(acceleratorMap.get("打开"),InputEvent.CTRL_MASK));
		menuSave.setAccelerator(KeyStroke.getKeyStroke(acceleratorMap.get("保存"),InputEvent.CTRL_MASK));
		menuClose.setAccelerator(KeyStroke.getKeyStroke(acceleratorMap.get("关闭"),InputEvent.CTRL_MASK));
	    menuCut.setAccelerator(KeyStroke.getKeyStroke(acceleratorMap.get("剪切"),InputEvent.CTRL_MASK));
		menuCopy.setAccelerator(KeyStroke.getKeyStroke(acceleratorMap.get("拷贝"),InputEvent.CTRL_MASK));
		menuPaste.setAccelerator(KeyStroke.getKeyStroke(acceleratorMap.get("粘贴"),InputEvent.CTRL_MASK));
	}
	
	/**
	 * 初始化文本编辑区
	 */
	private void initTextArea()
	{		
		textArea = new SimpleDrawLineTextArea();
		textArea.setFont(new Font("细明体",Font.PLAIN,16));
		textArea.setLineWrap(true);
		JScrollPane panel = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
													 ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//
		LineNumberHeaderView lineNumberHeaderView = new LineNumberHeaderView();		
		getContentPane().add(lineNumberHeaderView,  BorderLayout.NORTH);
		//
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(drawLineLable, BorderLayout.WEST);
		drawLineLable.Init(textArea);
		
		//校对文本框的行号
		textArea.setMargin(new Insets(4,10,10,10));
		//获得垂直滚动条
		panel.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				System.out.println("总行数:"+drawLineLable.GetCurrentCount());
			}
		});
	}
	
	/**
	 * ״̬初始化状态栏
	 */
	private void initStateBar() {
		stateBar = new JLabel("未修改");
		stateBar.setHorizontalAlignment(SwingConstants.LEFT);
		stateBar.setBorder(BorderFactory.createEtchedBorder());
		getContentPane().add(stateBar, BorderLayout.SOUTH);
	}

	private void showPopMenu() {
		popUpMenu = editMenu.getPopupMenu();
		
	}


	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(()->{
			new JNotePad(new FileTextDAO()).setVisible(true);
			
		});
	}
}
