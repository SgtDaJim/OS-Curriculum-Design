/**
 * 
 */
package com.jim.oscd.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import com.jim.oscd.controller.Algorithm;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

/**
 * @author Jim
 * @date 2017年1月4日
 *
 */
public class OSCD extends JFrame {

	private JPanel contentPane;
	private JTable runningProcessTable;
	private JTable readyProcessTable;
	private JTable waitingProcessTable;
	private JTable comingProcessTable;
	private JScrollPane readyProcessScrollPane;
	private JScrollPane waitingProcessScrollPane;
	private JScrollPane comingProcessScrollPane;
	private JTextField remainMemoryTextField;
	private JTextField remainTapeTextField;

	public DefaultTableModel finishProcessTableModel;
	public DefaultTableModel runningProcessTableModel;
	public DefaultTableModel readyProcessTableModel;
	public DefaultTableModel waitingProcessTableModel;
	public DefaultTableModel comingProcessTableModel;
	public JTextField hourTextField;
	public JTextField minuteTextField;
	
	private Algorithm a ;
	
	protected int runTimeHour = 0;
	protected int runTimeMin = 0;
	protected final static int STEP = 5;
	private JTable finishProcessTable;
	private JTextField turnaroundTimeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OSCD frame = new OSCD();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OSCD() {
		setTitle("OS Curriculum Design");
		
		a = new Algorithm();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 834);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane runningProcessScrollPane = new JScrollPane();
		runningProcessScrollPane.setBounds(10, 267, 520, 44);
		contentPane.add(runningProcessScrollPane);
		
		runningProcessTable = new JTable();
		runningProcessTable.setEnabled(false);
		runningProcessScrollPane.setViewportView(runningProcessTable);
		runningProcessTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\u8FDB\u7A0B\u540D", "\u5230\u8FBE\u65F6\u95F4", "\u8FD0\u884C\u65F6\u95F4", "\u5DF2\u8FD0\u884C", "\u4F18\u5148\u7EA7", "\u9700\u8981\u5185\u5B58", "\u9700\u8981\u78C1\u5E26\u673A"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		runningProcessTableModel = (DefaultTableModel) runningProcessTable.getModel();
		
		readyProcessScrollPane = new JScrollPane();
		readyProcessScrollPane.setBounds(10, 346, 520, 109);
		contentPane.add(readyProcessScrollPane);
		
		readyProcessTable = new JTable();
		readyProcessTable.setEnabled(false);
		readyProcessScrollPane.setViewportView(readyProcessTable);		
		readyProcessTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\u8FDB\u7A0B\u540D", "\u5230\u8FBE\u65F6\u95F4", "\u8FD0\u884C\u65F6\u95F4", "\u5DF2\u8FD0\u884C", "\u4F18\u5148\u7EA7", "\u9700\u8981\u5185\u5B58", "\u9700\u8981\u78C1\u5E26\u673A"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		readyProcessTableModel = (DefaultTableModel) readyProcessTable.getModel();
		
		waitingProcessScrollPane = new JScrollPane();
		waitingProcessScrollPane.setBounds(10, 484, 520, 109);
		contentPane.add(waitingProcessScrollPane);
		
		comingProcessScrollPane = new JScrollPane();
		comingProcessScrollPane.setBounds(10, 628, 520, 109);
		contentPane.add(comingProcessScrollPane);
		
		comingProcessTable = new JTable();
		comingProcessTable.setEnabled(false);
		comingProcessTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"\u4F5C\u4E1A\u540D", "\u5230\u8FBE\u65F6\u95F4", "\u8FD0\u884C\u65F6\u95F4", "\u9700\u8981\u5185\u5B58", "\u9700\u8981\u78C1\u5E26\u673A", "\u4F18\u5148\u7EA7"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		comingProcessScrollPane.setViewportView(comingProcessTable);
		comingProcessTableModel = (DefaultTableModel) comingProcessTable.getModel();
		
		waitingProcessTable = new JTable();
		waitingProcessTable.setEnabled(false);
		waitingProcessTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"\u4F5C\u4E1A\u540D", "\u5230\u8FBE\u65F6\u95F4", "\u8FD0\u884C\u65F6\u95F4", "\u9700\u8981\u5185\u5B58", "\u9700\u8981\u78C1\u5E26\u673A", "\u4F18\u5148\u7EA7"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		waitingProcessScrollPane.setViewportView(waitingProcessTable);
		waitingProcessTableModel = (DefaultTableModel) waitingProcessTable.getModel();
		
		JLabel runningProcessLabel = new JLabel("正在运行进程：");
		runningProcessLabel.setBounds(10, 242, 107, 15);
		contentPane.add(runningProcessLabel);
		
		JLabel readyProcessLabel = new JLabel("已经就绪进程：");
		readyProcessLabel.setBounds(10, 321, 107, 15);
		contentPane.add(readyProcessLabel);
		
		JLabel waitingProcessLabel = new JLabel("正在等待作业：");
		waitingProcessLabel.setBounds(10, 459, 107, 15);
		contentPane.add(waitingProcessLabel);
		
		JLabel comingProcessLabel = new JLabel("尚未到达作业：");
		comingProcessLabel.setBounds(10, 603, 107, 15);
		contentPane.add(comingProcessLabel);
		
		JButton insertButton = new JButton("载入设定数据");
		insertButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				a.InitPresetProcess();
				Object[][] row = a.getCWTableRow(a.getComingProcess());
				comingProcessTableModel.setRowCount(0);
				for(int i = 0; i<a.getComingProcess().size(); i++){
					comingProcessTableModel.addRow(row[i]);
				}
			}
		});
		insertButton.setBounds(249, 747, 127, 38);
		contentPane.add(insertButton);
		
		JButton stepButton = new JButton("单步运行");
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.flushRunTime();
				Object[][] rowComing = a.getCWTableRow(a.getComingProcess());
				Object[][] rowWaiting = a.getCWTableRow(a.getWaitingProcess());
				Object[][] rowReady = a.getRRTableRow(a.getReadyProcess());
				Object[][] rowRunning = a.getRRTableRow(a.getRunningProcess());
				Object[][] rowFinish = a.getFTableRow(a.getFinishProcess());
				comingProcessTableModel.setRowCount(0);
				waitingProcessTableModel.setRowCount(0);
				readyProcessTableModel.setRowCount(0);
				runningProcessTableModel.setRowCount(0);
				finishProcessTableModel.setRowCount(0);
				for(Object[] o : rowComing){
					comingProcessTableModel.addRow(o);
				}
				for(Object[] o : rowWaiting){
					waitingProcessTableModel.addRow(o);
				}
				for(Object[] o : rowReady){
					readyProcessTableModel.addRow(o);
				}
				for(Object[] o : rowRunning){
					runningProcessTableModel.addRow(o);
				}
				for(Object[] o : rowFinish){
					finishProcessTableModel.addRow(o);
				}
				hourTextField.setText(a.getRunTimeHour()+"");
				minuteTextField.setText(a.getRunTimeMin()+"");
				remainMemoryTextField.setText(a.getTotalMemory()+"");
				remainTapeTextField.setText(a.getTotalTape()+"");
				turnaroundTimeTextField.setText(a.getAvgTurnaroundTime()+"");
			}
		});
		stepButton.setBounds(386, 747, 127, 38);
		contentPane.add(stepButton);
		
		JLabel remainMemoryLable = new JLabel("剩余内存容量：");
		remainMemoryLable.setBounds(10, 78, 97, 15);
		contentPane.add(remainMemoryLable);
		
		remainMemoryTextField = new JTextField();
		remainMemoryTextField.setEditable(false);
		remainMemoryTextField.setBounds(117, 75, 40, 21);
		contentPane.add(remainMemoryTextField);
		remainMemoryTextField.setColumns(10);
		
		JLabel label = new JLabel("剩余磁带机：");
		label.setBounds(220, 78, 84, 15);
		contentPane.add(label);
		
		remainTapeTextField = new JTextField();
		remainTapeTextField.setEditable(false);
		remainTapeTextField.setBounds(314, 75, 40, 21);
		contentPane.add(remainTapeTextField);
		remainTapeTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("/100");
		label_1.setBounds(167, 78, 40, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("/4");
		label_2.setBounds(364, 78, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("运行时间：");
		label_3.setBounds(10, 53, 76, 15);
		contentPane.add(label_3);
		
		hourTextField = new JTextField();
		hourTextField.setEditable(false);
		hourTextField.setBounds(102, 47, 34, 21);
		contentPane.add(hourTextField);
		hourTextField.setColumns(10);
		hourTextField.setText(this.runTimeHour+"");
		
		JLabel label_4 = new JLabel("：");
		label_4.setBounds(138, 50, 19, 15);
		contentPane.add(label_4);
		
		minuteTextField = new JTextField();
		minuteTextField.setEditable(false);
		minuteTextField.setBounds(146, 47, 34, 21);
		contentPane.add(minuteTextField);
		minuteTextField.setColumns(10);
		minuteTextField.setText(this.runTimeMin+"");
		
		JScrollPane finishProcessScrollPane = new JScrollPane();
		finishProcessScrollPane.setBounds(10, 130, 520, 109);
		contentPane.add(finishProcessScrollPane);
		
		finishProcessTable = new JTable();
		finishProcessTable.setEnabled(false);
		finishProcessTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\u4F5C\u4E1A\u540D", "\u5230\u8FBE\u65F6\u95F4", "\u8FD0\u884C\u65F6\u95F4", "\u5468\u8F6C\u65F6\u95F4"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		finishProcessScrollPane.setViewportView(finishProcessTable);
		finishProcessTableModel = (DefaultTableModel) finishProcessTable.getModel();
		
		JLabel turnaroundTimeLabel = new JLabel("平均周转时间：");
		turnaroundTimeLabel.setBounds(10, 105, 97, 15);
		contentPane.add(turnaroundTimeLabel);
		
		turnaroundTimeTextField = new JTextField();
		turnaroundTimeTextField.setEditable(false);
		turnaroundTimeTextField.setBounds(117, 105, 40, 21);
		contentPane.add(turnaroundTimeTextField);
		turnaroundTimeTextField.setColumns(10);
		
		JLabel signLabel = new JLabel("Designed By Ro$es");
		signLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		signLabel.setBounds(10, 10, 170, 33);
		contentPane.add(signLabel);
		
		JLabel label_5 = new JLabel("作业调度采用最小作业优先算法");
		label_5.setBounds(261, 10, 228, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("进程调度采用可抢占的优先级调度算法");
		label_6.setBounds(261, 28, 228, 15);
		contentPane.add(label_6);
		
	}
	
}
