/**
 * 
 */
package com.jim.oscd.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jim.oscd.model.Process;
import com.jim.oscd.model.RunTimeComparator;

/**
 * @author Jim
 * @date 2017年1月4日
 *
 */
public class Algorithm {
	
	protected int totalMemory = 100;//总内存
	protected int totalTape = 4;//总磁带机
	protected int runTimeHour = 0;//运行时间中的小时
	protected int runTimeMin = 0;//运行时间中的分钟
	protected int avgTurnaroundTime = 0;//平均周转时间
	protected final static int STEP = 5;//步长

	protected List<Process> comingProcess;//未到达作业列表
	protected List<Process> waitingProcess;//等待作业列表
	protected List<Process> readyProcess;//就绪进程列表
	protected List<Process> runningProcess;//运行进程列表
	protected List<Process> finishProcess;//已完成作业列表
	
	public Algorithm(){
		this.comingProcess = new ArrayList<Process>();
		this.waitingProcess = new ArrayList<Process>();
		this.readyProcess = new ArrayList<Process>();
		this.runningProcess = new ArrayList<Process>();
		this.finishProcess = new ArrayList<Process>();
	}
	
	public void InitPresetProcess(){
//		this.comingProcess.add(new Process("JOB1", 10, 0, 25, 15, 2, 3));
//		this.comingProcess.add(new Process("JOB2", 10, 20, 30, 60, 1, 1));
//		this.comingProcess.add(new Process("JOB3", 10, 30, 10, 50, 3, 4));
//		this.comingProcess.add(new Process("JOB4", 10, 35, 20, 10, 2, 2));
//		this.comingProcess.add(new Process("JOB5", 10, 40, 15, 30, 2, 5));
		this.comingProcess.add(new Process("JOB1", 10, 0, 40, 15, 2, 3));
		this.comingProcess.add(new Process("JOB2", 10, 20, 20, 20, 1, 2));
		this.comingProcess.add(new Process("JOB3", 10, 30, 18, 50, 1, 1));
		this.comingProcess.add(new Process("JOB4", 10, 35, 33, 40, 2, 4));
		this.comingProcess.add(new Process("JOB5", 10, 40, 20, 30, 2, 5));
	}
	
	public Object[][] getCWTableRow(List<Process> l){
		Object[][] o = new Object[l.size()][6];
		for(int i = 0; i<l.size(); i++){
			o[i][0] = l.get(i).getName();
			o[i][1] = l.get(i).getHour() + ":" + l.get(i).getMinute();
			o[i][2] = l.get(i).getRunTime();
			o[i][3] = l.get(i).getNeedMemory();
			o[i][4] = l.get(i).getTape();
			o[i][5] = l.get(i).getLevel();
		}
		return o;
	}
	
	public Object[][] getRRTableRow(List<Process> l){
		Object[][] o = new Object[l.size()][7];
		for(int i = 0; i<l.size(); i++){
			o[i][0] = l.get(i).getName();
			o[i][1] = l.get(i).getHour() + ":" + l.get(i).getMinute();
			o[i][2] = l.get(i).getRunTime();
			o[i][3] = l.get(i).getHasRunTime();
			o[i][4] = l.get(i).getLevel();
			o[i][5] = l.get(i).getNeedMemory();
			o[i][6] = l.get(i).getTape();		
		}
		return o;
	}
	
	public Object[][] getFTableRow(List<Process> l){
		Object[][] o = new Object[l.size()][4];
		for(int i = 0; i<l.size(); i++){
			o[i][0] = l.get(i).getName();
			o[i][1] = l.get(i).getHour() + ":" + l.get(i).getMinute();
			o[i][2] = l.get(i).getRunTime();
			o[i][3] = l.get(i).getTurnaroundTime();
		}
		return o;
	}
	
	public void flushRunTime(){
		
		if(runTimeHour == 0 && runTimeMin == 0){//第一个作业到达后马上进入运行状态
			if(this.totalMemory >= this.comingProcess.get(0).getNeedMemory() 
					&& this.totalTape >= this.comingProcess.get(0).getTape()){
				runTimeHour = this.comingProcess.get(0).getHour();
				runTimeMin = this.comingProcess.get(0).getMinute();
				this.runningProcess.add(this.comingProcess.get(0));
				this.totalMemory -= this.comingProcess.get(0).getNeedMemory();
				this.totalTape -= this.comingProcess.get(0).getTape();
				this.comingProcess.remove(0);
			}else{
				System.out.println("作业内存和磁带机数量不符合要求！");
			}
		}else{
			runTimeMin += STEP;
			if(runTimeMin == 60){
				runTimeHour ++;
				runTimeMin = 0;
			}
			//正在运行的进程状态修改
			if(!this.runningProcess.isEmpty()){
				this.runningProcess.get(0).addHasRunTime(STEP);
				if(this.runningProcess.get(0).getRunTime() <= this.runningProcess.get(0).getHasRunTime()){
					this.totalMemory += this.runningProcess.get(0).getNeedMemory();
					this.totalTape += this.runningProcess.get(0).getTape();
					//作业周转时间等于等待时间+运行时间
					this.runningProcess.get(0).addTurnaroundTime(this.runningProcess.get(0).getRunTime());
					this.finishProcess.add(this.runningProcess.get(0));
					this.runningProcess.remove(0);
				}
			}
			//作业到达
			if(!this.comingProcess.isEmpty() && this.comingProcess.get(0).getHour() == runTimeHour 
					&& this.comingProcess.get(0).getMinute() == runTimeMin){
				//符合抢占条件时进行抢占
				if(this.totalMemory >= this.comingProcess.get(0).getNeedMemory()
						&& this.totalTape >= this.comingProcess.get(0).getTape()
						&& this.runningProcess.get(0).getLevel() > this.comingProcess.get(0).getLevel()){
					this.readyProcess.add(this.runningProcess.get(0));
					this.runningProcess.set(0, this.comingProcess.get(0));
					this.totalMemory -= this.comingProcess.get(0).getNeedMemory();
					this.totalTape -= this.comingProcess.get(0).getTape();
					this.comingProcess.remove(0);
				}else{//不能抢占时进入等候队列
					this.waitingProcess.add(this.comingProcess.get(0));
					this.comingProcess.remove(0);
					this.runTimeSort(); //将等候序列中作业按照作业长度排序
				}
			}
			//循环查询等候队列中是否有满足资源的作业，进入就绪队列
			List<Process> del = new ArrayList<Process>();
			for(Process p : this.waitingProcess){
				if(this.totalMemory >= p.getNeedMemory() && this.totalTape >= p.getTape()){
					this.readyProcess.add(p);
					this.totalMemory -= p.getNeedMemory();
					this.totalTape -= p.getTape();
					del.add(p);
				}
			}
			this.waitingProcess.removeAll(del);
			//等候队列中作业周转时间增加
			for(Process p : this.waitingProcess){
				p.addTurnaroundTime(STEP);
			}
			//当运行队列为空时，在就绪队列中拿出第一个进程开始运行
			if(this.runningProcess.isEmpty() && !this.readyProcess.isEmpty()){
				this.runningProcess.add(this.readyProcess.get(0));
				this.readyProcess.remove(0);
			}
			//就绪队列中作业周转时间增加
			for(Process p : this.readyProcess){
				p.addTurnaroundTime(STEP);
			}
			//平均周转时间计算
			if(!this.finishProcess.isEmpty()){
				for(Process p : this.finishProcess){
					this.avgTurnaroundTime += p.getTurnaroundTime();
				}
				this.avgTurnaroundTime /= this.finishProcess.size();
			}		
		}
	}
	
	//按到达时间对作业排序
	public void arrivalTimeSort(){
	    int i, j;
	    int n = this.comingProcess.size();
	    Process target;
	 
	    //假定第一个元素被放到了正确的位置上
	    //这样，仅需遍历1 - n-1
	    for (i = 1; i < n; i++){
	        j = i;
	        target = this.comingProcess.get(i);
	 
	        while (j > 0 && target.getMinute() < this.comingProcess.get(j - 1).getMinute()){
	        	this.comingProcess.set(j, this.comingProcess.get(j - 1));
	            j--;
	        }	 
	        this.comingProcess.set(j, target);
	    }		
	}
	
	//按作业长短排序
	public void runTimeSort(){
		int i, j;
	    int n = this.waitingProcess.size();
	    Process target;
	 
	    //假定第一个元素被放到了正确的位置上
	    //这样，仅需遍历1 - n-1
	    for (i = 1; i < n; i++){
	        j = i;
	        target = this.waitingProcess.get(i);
	 
	        while (j > 0 && target.getRunTime() < this.waitingProcess.get(j - 1).getRunTime()){
	        	this.waitingProcess.set(j, this.waitingProcess.get(j - 1));
	            j--;
	        }	 
	        this.waitingProcess.set(j, target);
	    }	
	}

	/**
	 * @return the avgTurnaroundTime
	 */
	public int getAvgTurnaroundTime() {
		return avgTurnaroundTime;
	}

	/**
	 * @return the runTimeHour
	 */
	public int getRunTimeHour() {
		return runTimeHour;
	}

	/**
	 * @return the runTimeMin
	 */
	public int getRunTimeMin() {
		return runTimeMin;
	}

	/**
	 * @return the totalMemory
	 */
	public int getTotalMemory() {
		return totalMemory;
	}

	/**
	 * @return the totaltape
	 */
	public int getTotalTape() {
		return totalTape;
	}

	/**
	 * @return the comingProcess
	 */
	public List<Process> getComingProcess() {
		return comingProcess;
	}

	/**
	 * @return the waitingProcess
	 */
	public List<Process> getWaitingProcess() {
		return waitingProcess;
	}

	/**
	 * @return the readyProcess
	 */
	public List<Process> getReadyProcess() {
		return readyProcess;
	}

	/**
	 * @return the runningProcess
	 */
	public List<Process> getRunningProcess() {
		return runningProcess;
	}

	/**
	 * @return the finishProcess
	 */
	public List<Process> getFinishProcess() {
		return finishProcess;
	}
	
	

}
