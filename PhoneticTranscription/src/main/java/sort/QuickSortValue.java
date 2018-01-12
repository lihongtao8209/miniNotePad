package sort;

public class QuickSortValue implements Comparable<QuickSortValue> {

	private Object value;



	public QuickSortValue() {

	}

	public QuickSortValue(Object value) {
		this.value = value;
	}

	public int compareTo(QuickSortValue v) {
		Character left = (Character)this.value;
		Character right= (Character)v.value;
		if(v.value instanceof Integer) {
			return (Integer)this.value-(Integer)v.value;
		}
		if(v.value instanceof Character) {
			return left.compareTo(right);
		}
		return (Integer)this.value-(Integer)v.value;
	}
	
	public Object getValue() {
		return value;
	}
}
