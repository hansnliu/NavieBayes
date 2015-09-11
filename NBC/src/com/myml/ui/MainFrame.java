/*
 * MainFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.myml.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import com.myml.util.Main;

/**
 * 
 * @author __USER__
 */
public class MainFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates new form MainFrame */
	public MainFrame() {
		initComponents();
		this.setTitle("邮件检测1.0");
		this.setLocationRelativeTo(null);
		
		// 测试按钮的监听和处理
		TestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) ParseResultTable
						.getModel();
				// 清除已有数据
				model.getDataVector().clear();
				// 通知更新
				model.fireTableDataChanged();
				// 刷新表格
				ParseResultTable.updateUI();

				// 得到训练数据中邮件的个数
				double hamnum = Double.parseDouble(HamNum.getText());
				// 得到测试数据中邮件的个数
				double spamnum = Double.parseDouble(SpamNum.getText());
				// 得到测试数据的文件名
				String testfilename = TestFilename.getText();

				// 得到返回结果
				Object[][] wordpx = new Main().excute(hamnum, spamnum,
						testfilename);

				// 填充结果到表格
				for (int i = 0; i < wordpx.length; i++) {
					model.addRow(wordpx[i]);
				}
				
				//获取单词在ham和spam中概率
				Object[] hampx = new Object[wordpx.length];
				Object[] spampx = new Object[wordpx.length];
				for (int i = 0; i < hampx.length; i++) {
					hampx[i] = wordpx[i][1];
					spampx[i] = wordpx[i][2];
				}
				//连乘计算
				double multihampx = GetMultipx(hampx)
						* (hamnum / (hamnum + spamnum));
				double multispampx = GetMultipx(spampx)
						* (spamnum / (hamnum + spamnum));
				
				System.out.println("正常邮件概率计算 公式的分子："+multihampx);
				System.out.println("垃圾邮件概率计算 公式的分子："+multispampx);
				
				System.out.println("分母（全概率公式）："+(multihampx+multispampx));
				System.out.println(multihampx/(multihampx+multispampx));
				System.out.println(multispampx/(multihampx+multispampx));
				
				// 显示概率
				HamProbability.setText(String.valueOf(multihampx/(multihampx+multispampx)));
				SpamProbabilty.setText(String.valueOf(multispampx/(multihampx+multispampx)));

				// 显示结果
				ResultTextField.setText(GetResult(multihampx, multispampx));
			}
		});
	}
	
	//计算概率
	public double GetMultipx(Object[] px) {
		double multipx = 1.0;
		for (int k = 0; k < px.length; k++) {
			if((Double)px[k]==0.0)
				continue;
			multipx *= (Double) px[k];
		}
		if(multipx==1.0)			//每个测试单词都没出现，这里在求拉普拉斯，还需进一步改进
			return 0.0;
		else
			return multipx;
	}
	
	//比较概率
	public String GetResult(double hampx, double spampx) {
		String str = "";
		if (hampx  > spampx )
			str = "它是正常邮件";
		else if (hampx  < spampx )
			str = "它是垃圾邮件";
		else
			str = "不能判断";
		return str;
	}
	

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		ParseResultTable = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		HamNum = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		SpamNum = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		TestFilename = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		HamProbability = new javax.swing.JTextField();
		Jlabellll = new javax.swing.JLabel();
		SpamProbabilty = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		ResultTextField = new javax.swing.JTextField();
		TestButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		ParseResultTable.setFont(new java.awt.Font("宋体", 0, 18));
		ParseResultTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "\tWord", "\t在正常邮件中概率", "\t在垃圾邮件中概率" }) {
			/**
							 * 
							 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.Object.class, java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, false, false };

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(ParseResultTable);

		jLabel1.setFont(new java.awt.Font("宋体", 0, 18));
		jLabel1
				.setText("\u8f93\u5165\u8bad\u7ec3\u6570\u636e\u4e2a\u6570\uff1a");

		jLabel2.setFont(new java.awt.Font("宋体", 0, 18));
		jLabel2.setText("\u6b63\u5e38\u90ae\u4ef6\uff1a");

		HamNum.setFont(new java.awt.Font("宋体", 0, 18));

		jLabel3.setFont(new java.awt.Font("宋体", 0, 18));
		jLabel3.setText("\u4e2a");

		jLabel4.setFont(new java.awt.Font("宋体", 0, 18));
		jLabel4.setText("\u5783\u573e\u90ae\u4ef6\uff1a");

		SpamNum.setFont(new java.awt.Font("宋体", 0, 18));

		jLabel5.setFont(new java.awt.Font("宋体", 0, 18));
		jLabel5.setText("\u4e2a");

		jLabel6.setFont(new java.awt.Font("宋体", 0, 18));
		jLabel6
				.setText("\u8f93\u5165\u6d4b\u8bd5\u6570\u636e\u6587\u4ef6\u540d\uff1a");

		TestFilename.setFont(new java.awt.Font("宋体", 0, 18));

		jLabel7.setFont(new java.awt.Font("宋体", 0, 18));
		jLabel7.setText("\u7ed3\u679c\uff1a");

		jLabel8.setFont(new java.awt.Font("宋体", 0, 18));
		jLabel8.setText("\u6b63\u5e38\u90ae\u4ef6\u6982\u7387\uff1a");

		HamProbability.setFont(new java.awt.Font("宋体", 0, 18));

		Jlabellll.setFont(new java.awt.Font("新宋体", 0, 18));
		Jlabellll.setText("\u5783\u573e\u90ae\u4ef6\u6982\u7387\uff1a");

		SpamProbabilty.setFont(new java.awt.Font("宋体", 0, 18));

		jLabel9.setFont(new java.awt.Font("新宋体", 0, 18));
		jLabel9.setText("\u9884\u6d4b\u7ed3\u679c\uff1a");

		ResultTextField.setFont(new java.awt.Font("宋体", 0, 18));

		TestButton.setFont(new java.awt.Font("宋体", 0, 18));
		TestButton.setText("\u6d4b\u8bd5");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(22, 22, 22)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				687,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel7)
																						.addComponent(
																								jLabel1)
																						.addGroup(
																								layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING,
																												false)
																										.addGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												layout
																														.createSequentialGroup()
																														.addComponent(
																																jLabel8)
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addComponent(
																																HamProbability))
																										.addGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												layout
																														.createSequentialGroup()
																														.addGroup(
																																layout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																		.addComponent(
																																				Jlabellll)
																																		.addComponent(
																																				jLabel9))
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addGroup(
																																layout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																		.addComponent(
																																				SpamProbabilty,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				258,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				ResultTextField,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				271,
																																				Short.MAX_VALUE)))))
																		.addContainerGap(
																				297,
																				Short.MAX_VALUE))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel6)
																										.addGap(
																												18,
																												18,
																												18)
																										.addComponent(
																												TestFilename))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel2)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												HamNum,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												30,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jLabel3)
																										.addGap(
																												80,
																												80,
																												80)
																										.addComponent(
																												jLabel4)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												SpamNum,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												29,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jLabel5)))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				91,
																				Short.MAX_VALUE)
																		.addComponent(
																				TestButton)
																		.addGap(
																				164,
																				164,
																				164)))));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel1)
										.addGap(22, 22, 22)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																HamNum,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3)
														.addComponent(jLabel4)
														.addComponent(
																SpamNum,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																TestFilename,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																TestButton))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jLabel7)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												233, Short.MAX_VALUE)
										.addGap(18, 18, 18)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																HamProbability,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(Jlabellll)
														.addComponent(
																SpamProbabilty,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(16, 16, 16)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel9)
														.addComponent(
																ResultTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JTextField HamNum;
	private javax.swing.JTextField HamProbability;
	private javax.swing.JLabel Jlabellll;
	private javax.swing.JTable ParseResultTable;
	private javax.swing.JTextField ResultTextField;
	private javax.swing.JTextField SpamNum;
	private javax.swing.JTextField SpamProbabilty;
	private javax.swing.JButton TestButton;
	private javax.swing.JTextField TestFilename;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JScrollPane jScrollPane1;
	// End of variables declaration//GEN-END:variables

}