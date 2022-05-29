package cm_machine;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


public class MainScreen extends JFrame implements ListSelectionListener{
	private JLabel noticeBoardTitle = new JLabel(" �μ��ΰ� �Խ���");
	private JLabel employeeBoardTitle = new JLabel(" ���� ����� ��Ϻ�");
	private JList employee_list;				//��������Ʈ
	private JList noticeBoard_list;//�Խ��� ����Ʈ
	private JTextField noticeBoard_inputField; //�Խ��� �Է� field
	private JButton addBtn;		//�Խ��� �߰� ��ư
	private JButton delBtn;		//�Խ��� ���� ��ư
	private JButton workingOnBtn;//��� ��ư
	private JButton workingOffBtn;//��� ��ư
	private JButton employeeManageBtn; //���� �ٹ��ð� �ʱ�ȭ ��ư
	private JScrollPane noticeBoard_scrolled;//����Ʈ ��ũ���� �����ϰ� �ϱ� ���� scroll pane
	private JScrollPane employee_scrolled;
	
	
	public MainScreen(String title, DefaultListModel emp_data, DefaultListModel not_data) {
		super(title);
		
		Font tmp = new Font("���� ���", Font.PLAIN, 15);
		noticeBoard_list = new JList(not_data);
		noticeBoard_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		noticeBoard_list.setFont(tmp);
		
		employee_list = new JList(emp_data);
		employee_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//�ϳ��� ���� �� �� �ֵ���\
		employee_list.setFont(tmp);
		
		this.setBounds(100, 100, 1000, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		noticeBoard_inputField = new JTextField();
		noticeBoard_inputField.setBounds(12, 65, 249, 35);
		this.getContentPane().add(noticeBoard_inputField);
		noticeBoard_inputField.setColumns(10);
		
		addBtn=new JButton("�߰�");
		addBtn.setBounds(273, 65, 78, 36);
		addBtn.setFont(tmp);
		this.getContentPane().add(addBtn);
		
		delBtn=new JButton("����");
		delBtn.setBounds(363, 64, 78, 36);
		delBtn.setFont(tmp);
		this.getContentPane().add(delBtn);
		
		workingOnBtn = new JButton("���");
		workingOnBtn.setBounds(486, 65, 116, 36);
		workingOnBtn.setFont(tmp);
		this.getContentPane().add(workingOnBtn);
		
		workingOffBtn = new JButton("���");
		workingOffBtn.setBounds(614, 65, 116, 36);
		workingOffBtn.setFont(tmp);
		this.getContentPane().add(workingOffBtn);
		
		employeeManageBtn = new JButton("���� ����");
		employeeManageBtn.setBounds(799, 65, 116, 36);
		employeeManageBtn.setFont(tmp);
		this.getContentPane().add(employeeManageBtn);
		
		tmp = new Font("���� ���", Font.BOLD, 30);
		noticeBoardTitle.setBounds(12, 25, 429, 30);
		noticeBoardTitle.setFont(tmp);
		this.getContentPane().add(noticeBoardTitle);
		
		employeeBoardTitle.setBounds(486, 25, 429, 30);
		employeeBoardTitle.setFont(tmp);
		this.getContentPane().add(employeeBoardTitle);
		
		noticeBoard_scrolled = new JScrollPane();
		noticeBoard_scrolled.setBounds(12, 111, 429, 314);
		noticeBoard_scrolled.setViewportView(noticeBoard_list);
		this.getContentPane().add(noticeBoard_scrolled);
		
		employee_scrolled = new JScrollPane();
		employee_scrolled.setBounds(486, 111, 429, 314);
		employee_scrolled.setViewportView(employee_list);
		this.getContentPane().add(employee_scrolled);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	public void addBtnListener(CommuteControl listenForButton){
		addBtn.addActionListener(listenForButton);
		delBtn.addActionListener(listenForButton);
		workingOnBtn.addActionListener(listenForButton);
		workingOffBtn.addActionListener(listenForButton);
		employeeManageBtn.addActionListener(listenForButton);
	}

	//ListSelectionListener
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {	//�̰� ������ mouse ������, ���� ���� �ѹ��� ȣ��Ǽ� �� �ι� ȣ��
			System.out.println("selected :"+employee_list.getSelectedValue());
		}
	}
	
	//getter
	public String getNoticeInput() {
		return noticeBoard_inputField.getText();
	}
	
	public int getNoticeSelected() {
		return noticeBoard_list.getSelectedIndex();
	}
	
	public int getEmployeeSelected() {
		return employee_list.getSelectedIndex();
	}

}
