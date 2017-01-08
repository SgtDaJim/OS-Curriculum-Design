/**
 * 
 */
package com.jim.oscd.model;

import java.util.Comparator;

/**
 * @author Jim
 * @date 2017年1月4日
 *
 */
public class RunTimeComparator implements Comparator{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Process p1 = (Process) o1;
		Process p2 = (Process) o2;
		if(p1.getRunTime() > p2.getRunTime()){
			return 1;
		}else{
			return 0;
		}
	}

}
