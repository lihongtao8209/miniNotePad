package miniNotePad.custom.north;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
public class LineNumberHeaderView extends javax.swing.JComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = -250767779960701034L;
	private final  Font DEFAULT_FONT = new Font("细明体",Font.PLAIN,16);
    public final Color DEFAULT_BACKGROUD = Color.DARK_GRAY;
    public final Color DEFAULT_FOREGROUD = Color.DARK_GRAY;
    public final int nHEIGHT = 4;
    public final int MARGIN = 0;
    private int lineHeight;
    private int fontLineHeight;
    private FontMetrics fontMetrics;
    public LineNumberHeaderView() {
        setFont(DEFAULT_FONT);
        setForeground(DEFAULT_FOREGROUD);
        setBackground(DEFAULT_BACKGROUD);
        setPreferredSize(8);
    }
    public void setPreferredSize(int row) {
    	
    }
    @Override
    public void setFont(Font font) {
        super.setFont(font);
        fontMetrics = getFontMetrics(getFont());
        fontLineHeight = fontMetrics.getHeight();
    }
    public int getLineHeight() {
        if (lineHeight == 0) {
            return fontLineHeight;
        }
        return lineHeight;
    }
    public void setLineHeight(int lineHeight) {
        if (lineHeight > 0) {
            this.lineHeight = lineHeight;
        }
    }
    public int getStartOffset() {
        return 4;
    }
    

}