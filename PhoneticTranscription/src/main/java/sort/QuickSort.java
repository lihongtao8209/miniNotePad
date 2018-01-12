package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import debug.Debug;

public class QuickSort {

	public QuickSort() {
		
	}

	private void sort(Comparable[] a) {
		toSort(a,0,a.length-1);
	}
	
	public void toSort(Comparable[] a,int low,int high) {
		if(high<=low) {
			return;
		}
		int j=partition(a,low,high);
		//将左半部分a[low..j-1]排序
		toSort(a,low,j-1);
		//将右半部分a[j+1..high]排序
		toSort(a,j+1,high);
	}

	
	private int partition(Comparable[] a, int low, int high) {
		//将数组划分为a[low..i-1],a[i],a[i+1,high]
		int i = low;
		int j=high+1;
		Comparable v=a[low];
		while(true) {
			//扫描左右,检查扫描是否结束并交换元素
			while(less(a[++i],v)) if(i==high) break;
			while(less(v,a[--j])) if(j==low)  break;
			if(i>=j) break;
			exch(a,i,j);
		}
		exch(a,low,j);
		return j;
	}
	
	private void exch(Comparable[] a, int i, int j) {
		Comparable temporary= a[i];
		a[i]=a[j];
		a[j]=temporary;
		
	}

	/* Comparable 接口
	 * compareTo
	 * 参数: o   要比较的对象 
	 * 返回:     负整数	 小于指定对象 
	 *           零或	 等于指定对象 
	 *           正整数   大于指定对象 
	 * 抛出： ClassCastException -
	 * 如果指定对象的类型不允许它与此对象进行比较。
	 */
	private int compare(Comparable left,Comparable right) {
		return left.compareTo(right);
	}
	
	private boolean less(Comparable left,Comparable right) {
		if(compare(left,right)<0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Test
	public void test() {
		int a[] = {2,1,3,4}; 
		QuickSortValue quickSortValues[] = new QuickSortValue[4];
		quickSortValues[0] =  new QuickSortValue('B');
		quickSortValues[1] =  new QuickSortValue('A');
		quickSortValues[2] =  new QuickSortValue('C');
		quickSortValues[3] =  new QuickSortValue('D');
	/*	Arrays.sort(quickSortValues);
		for(int i=0;i<quickSortValues.length;i++) {
			Debug.debug(quickSortValues[i].getValue().toString());
		}*/
		QuickSort quickSort = new QuickSort();
		quickSort.sort(quickSortValues);
		for(int i=0;i<quickSortValues.length;i++) {
			Debug.debug(quickSortValues[i].getValue().toString());
		}
	}
	
}
