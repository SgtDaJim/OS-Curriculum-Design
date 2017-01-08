/**
 * 
 */
package com.jim.oscd.model;

/**
 * @author Jim
 * @date 2017年1月4日
 *
 */
public class Process {
	
	protected String name = "";//进程名
	protected int hour;//到达时间中的小时
	protected int minute;//到达时间中的分钟
	protected int runTime;//运行时间
	protected int hasRunTime;//已运行时间
	protected int needMemory;//需要内存
	protected int tape;//需要磁带机
	protected int level;//优先级
	protected int turnaroundTime;//周转时间
	
	public Process(){
		
	}
	
	public Process(
			String name,
			int hour,
			int minute,
			int runTime,
			int needMemory,
			int tape,
			int level
			){
		this.name = name;
		this.hour = hour;
		this.minute = minute;
		this.runTime = runTime;
		this.hasRunTime = 0;
		this.needMemory = needMemory;
		this.tape = tape;
		this.level = level;
		this.turnaroundTime = 0;
	}

	/**
	 * @return the hasRunTime
	 */
	public int getHasRunTime() {
		return hasRunTime;
	}

	/**
	 * @param hasRunTime the hasRunTime to set
	 */
	public void setHasRunTime(int hasRunTime) {
		this.hasRunTime = hasRunTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @return the runTime
	 */
	public int getRunTime() {
		return runTime;
	}

	/**
	 * @return the needMemory
	 */
	public int getNeedMemory() {
		return needMemory;
	}

	/**
	 * @return the tape
	 */
	public int getTape() {
		return tape;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	public void addHasRunTime(int step){
		this.hasRunTime += step;
	}

	/**
	 * @return the turnaroundTime
	 */
	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	/**
	 * @param turnaroundTime the turnaroundTime to set
	 */
	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}
	
	public void addTurnaroundTime(int turnaroundTime){
		this.turnaroundTime += turnaroundTime;
	}
	
	

}
