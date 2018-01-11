package miniNotePad.line;

public class LineNumber {
	
	private static int start=1;
	private static int count=1;

	public LineNumber() {
		count = 0;
	}

	public LineNumber(int n) {
		count = n;
	}

	
	public void reset() {
		count = 0;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int n) {
		count = n;
	}
	
	//获得起始长度
	public int getStart() {
		return start;
	}
	//设置起始长度
	public void setStart(int start) {
		LineNumber.start = start;
	}

	public synchronized void add() {
		count  = count+1;
	}

}
