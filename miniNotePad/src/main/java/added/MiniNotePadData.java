package added;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

public class MiniNotePadData {
	// �ĵ��˵�
	private JMenu fileMenu;
	private JMenuItem menuOpen;
	private JMenuItem menuSave;
	private JMenuItem menuSaveAs;
	private JMenuItem menuClose;
	// �༭�˵�
	private JMenu editMenu;
	private JMenuItem menuCut;
	private JMenuItem menuCopy;
	private JMenuItem menuPaste;
	// ���ڲ˵�
	private JMenu aboutMenu;
	private JMenuItem menuAbout;
	// ���ֱ༭
	private JTextArea textArea;
	private JLabel stateBar;
	// �����˵�
	private JPopupMenu popUpMenu;
	public JMenu getFileMenu() {
		return fileMenu;
	}
	public void setFileMenu(JMenu fileMenu) {
		this.fileMenu = fileMenu;
	}
	public JMenuItem getMenuOpen() {
		return menuOpen;
	}
	public void setMenuOpen(JMenuItem menuOpen) {
		this.menuOpen = menuOpen;
	}
	public JMenuItem getMenuSave() {
		return menuSave;
	}
	public void setMenuSave(JMenuItem menuSave) {
		this.menuSave = menuSave;
	}
	public JMenuItem getMenuSaveAs() {
		return menuSaveAs;
	}
	public void setMenuSaveAs(JMenuItem menuSaveAs) {
		this.menuSaveAs = menuSaveAs;
	}
	public JMenuItem getMenuClose() {
		return menuClose;
	}
	public void setMenuClose(JMenuItem menuClose) {
		this.menuClose = menuClose;
	}
	public JMenu getEditMenu() {
		return editMenu;
	}
	public void setEditMenu(JMenu editMenu) {
		this.editMenu = editMenu;
	}
	public JMenuItem getMenuCut() {
		return menuCut;
	}
	public void setMenuCut(JMenuItem menuCut) {
		this.menuCut = menuCut;
	}
	public JMenuItem getMenuCopy() {
		return menuCopy;
	}
	public void setMenuCopy(JMenuItem menuCopy) {
		this.menuCopy = menuCopy;
	}
	public JMenuItem getMenuPaste() {
		return menuPaste;
	}
	public void setMenuPaste(JMenuItem menuPaste) {
		this.menuPaste = menuPaste;
	}
	public JMenu getAboutMenu() {
		return aboutMenu;
	}
	public void setAboutMenu(JMenu aboutMenu) {
		this.aboutMenu = aboutMenu;
	}
	public JMenuItem getMenuAbout() {
		return menuAbout;
	}
	public void setMenuAbout(JMenuItem menuAbout) {
		this.menuAbout = menuAbout;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public JLabel getStateBar() {
		return stateBar;
	}
	public void setStateBar(JLabel stateBar) {
		this.stateBar = stateBar;
	}
	public JPopupMenu getPopUpMenu() {
		return popUpMenu;
	}
	public void setPopUpMenu(JPopupMenu popUpMenu) {
		this.popUpMenu = popUpMenu;
	}
}
