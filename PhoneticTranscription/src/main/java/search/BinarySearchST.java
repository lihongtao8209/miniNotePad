package search;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys = null;
	private Value[] values = null;
	private int length = 0;

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
		length = keys.length;
	}

	public int rank(Key key) {
		int middle = 0;
		int low = 0;
		int high = length - 1;
		int compareResult = -1;
		while (low < high) {
			middle = low + (high - low) / 2;
			// 比较值
			compareResult = key.compareTo(keys[middle]);
			if (compareResult < 0) {
				high = middle - 1;
			} else if (compareResult > 0) {
				low = middle + 1;
			} else {
				return middle;
			}
		}
		return low;
	}
}
