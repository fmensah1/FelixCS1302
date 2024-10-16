package edu.westga.cs1302.bill.model;

import java.util.Comparator;

/**
 * Sort the bill items in the ascending order according to cost
 * 
 * @author fmensah1
 * @version Fall 24
 * 
 */
public class BillAscendingCostComparator implements Comparator<BillItem> {
	
	@Override
	public int compare(BillItem o1, BillItem o2) {
		if (o1.getAmount() < o2.getAmount()) {
			return -1;
		} else if (o1.getAmount() < o2.getAmount()) {
			return 1;
		} else {
			return 0;
		}
	}
  
	@Override 
	public String toString() {
		return "Ascending";
	}
}
