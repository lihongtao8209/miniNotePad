package miniNotePad.custom.west;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.event.AdjustmentEvent;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import org.junit.experimental.theories.Theories;

import cosntvar.ConstVar;
import miniNotePad.line.LineNumber;

public class SimpleDrawLineLable extends JLabel {
	/**
	 * 文本框的行号
	 */

	private static final long serialVersionUID = -1741222457293055090L;
	// 字体
	FontMetrics fontMetrics;
	// 文本框
	JTextArea textArea;
	//行间隔
	float lineSpace ;
	//行号计数器
	LineNumber lineNumber;
	//

	public SimpleDrawLineLable() {
		//初始化行号
		lineNumber = new LineNumber(0);
		//字符串的高, 只和字体有关 
		FontMetrics metrics = textArea.getFontMetrics(textArea.getFont()); 
		lineSpace = metrics.getHeight(); 
	}
	
	public SimpleDrawLineLable(int lineSpace) {
		setLineSpace(lineSpace);
	}
	
	public SimpleDrawLineLable(LineNumber lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public SimpleDrawLineLable(LineNumber lineNumber,float lineSpace) {
		this.lineNumber = lineNumber;
		setLineSpace(lineSpace);
	}
	
	/**
	 * 重绘行号
	 */
	public void reDrawLine() {
		lineNumber.add();
		this.repaint();
	}
	
	public void reDrawLine(int lineNo) {
		lineNumber.setCount(lineNo);
		this.repaint();
	}   
			
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
		g2.setFont(font);
		//在文本框中输入回车键
		defaultMessage(g2);
	}

	private void defaultMessage(Graphics2D g2) {
		if(lineNumber.getCount()>GetScreenLineCount()) {
			lineNumber.setStart(lineNumber.getStart()+1);
		}
		for (int i = lineNumber.getStart(); i <=  GetCurrentCount(); i++) {
			g2.drawString(String.valueOf(i), 0, ConstVar.LINE_SPACE * (i-lineNumber.getStart()+1));
		}
	}

	public void Init(JTextArea textArea) {
		setBackground(Color.lightGray);
		setText("  ");
		setPreferredSize(2);
		setTextArea(textArea);
	}


	private void setPreferredSize(int row) {
		int currentRowWidth = 0;
		final int nHEIGHT = Integer.MAX_VALUE - 1000000;
		final int MARGIN = 5;
		int width = fontMetrics.stringWidth(String.valueOf(row));
		if (currentRowWidth < width) {
			currentRowWidth = width;
			setPreferredSize(new Dimension(2 * MARGIN + width + 1, nHEIGHT));
		}
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		fontMetrics = getFontMetrics(getFont());
	}
	
	public void debug(AdjustmentEvent e) {
		String[] adjustInfo=e.getAdjustable().toString().split(",");
		System.out.println(adjustInfo[4]);
		/*System.out.println("getCaretPosition:"+textArea.getCaretPosition());
		System.out.println(textArea.getCaretPosition());
		System.out.println("getAlignmentY:"+textArea.getAlignmentX()+":"+textArea.getAlignmentY());
		System.out.println("getAlignmentY:"+textArea.getAlignmentX()+":"+textArea.getAlignmentY());*/
       
	}
	
	/**
	 * 返回当前行数
	 * @return
	 */
	public int GetCurrentCount()
	{
		return lineNumber.getCount();
	}
	
	/**
	 * 返回屏幕展现的总的行数
	 * @return
	 */
	public int GetScreenLineCount() {
		return (int)(this.getHeight()/getLineSpace());
	}

	public float getLineSpace() {
		return lineSpace;
	}
	
	/**
	 * 此函数设置行间隔
	 * @param lineSpace
	 */
	public void setLineSpace(float lineSpace) {
		this.lineSpace = lineSpace;
	}
}
