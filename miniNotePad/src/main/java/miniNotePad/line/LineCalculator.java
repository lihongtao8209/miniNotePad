package miniNotePad.line;

public class LineCalculator {
	private static final LineCalculator instance = new LineCalculator();
	// 行数值
	private static float rowNo;
	// 行数
	private static int rowNumber;
	// 每行的空间
	private static float lineSpace;
	// 文本编辑器的高度
	private static int editorHeight;

	private LineCalculator() {
		rowNo = 0;
		rowNumber = 0;
		lineSpace = 0;
	}

	private LineCalculator(int editorHeight, float lineSpace) {
		this();
		calculat(editorHeight, lineSpace);
	}

	/**
	 * 计算行数值
	 * 
	 * @param editorHeight
	 *            文本编辑器高度
	 * @param lineSpace
	 *            文本编辑器行高
	 */
	public void calculat(int editorHeight, float lineSpace) {
		if (lineSpace > 0.0f) {
			rowNo = (float) editorHeight / lineSpace;
		}
	}

	public int getRowNumber() {
		rowNumber = (int) rowNo;
		return rowNumber;
	}

	public void setRowNumber(int n) {
		rowNumber = n;
	}

	public float getRowNo() {
		return rowNo;
	}

	public void setRowNo(float n) {
		rowNo = n;
	}

	/**
	 * 获得每行的空间
	 * 
	 * @return
	 */
	public float getLineSpace() {
		return lineSpace;
	}

	/**
	 * 设置每行的空间
	 * 
	 * @param lineSpace
	 */
	public void setLineSpace(float lineSpace) {
		LineCalculator.lineSpace = lineSpace;
	}

	/**
	 * 获得编辑器的长度
	 * 
	 * @return
	 */
	public int getEditorHeight() {
		return editorHeight;
	}

	/**
	 * 设置编辑器的长度
	 * 
	 * @param editorHeight
	 */
	public void setEditorHeight(int editorHeight) {
		LineCalculator.editorHeight = editorHeight;
	}

	public static final LineCalculator getInstance() {
		return instance;
	}
}
