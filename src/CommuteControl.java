package cm_machine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;
import java.util.Vector;




public class CommuteControl implements ActionListener{
	private DefaultListModel<String> employeeData;
	private DefaultListModel<String> noticeBoardData;
	private Vector<Employee> employeeVec = new Vector<Employee>();
	private MainScreen mainScreen;
	private EmployeeManageScreen employeeManageScreen;
	
	//Constructor
	public CommuteControl() {
		employeeData = new DefaultListModel<String>();
		noticeBoardData = new DefaultListModel<String>();
		
		readFile();
		
		mainScreen = new MainScreen("�μ��ΰ�/����� ��� ���α׷�", employeeData, noticeBoardData);
		mainScreen.addBtnListener(this);
	}
	
	//Init - reading file to initiate employeeData and noticeBoardData
	private void readFile() {
		try {
			FileInputStream fis = new FileInputStream("noticeBoard.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			noticeBoardData = (DefaultListModel<String>)ois.readObject();
		}
		catch ( FileNotFoundException e) {
			System.out.println(e);
		}
		catch ( IOException e ) {
			System.out.println(e);
		}
		catch (ClassNotFoundException e) {
			System.out.println(e);
		}	
		
		//���� ������ �б�
		try {
			FileInputStream fis = new FileInputStream("employeeData.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			employeeVec = (Vector<Employee>)ois.readObject();
			
			for (int i = 0; i < employeeVec.size(); i++) {
				employeeData.addElement(employeeVec.get(i).getString());
			}
		}
		catch ( FileNotFoundException e) {
			System.out.println(e);
		}
		catch ( IOException e ) {
            System.out.println(e);
        } 
		catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	//action listener
	public void actionPerformed(ActionEvent e){
        if(e.getActionCommand() == "�߰�")  {
        	addNotice(mainScreen.getNoticeInput());
        }
        else if (e.getActionCommand() == "����") {
        	delNotice(mainScreen.getNoticeSelected());
        }
        else if (e.getActionCommand() == "���") {
        	workStart(mainScreen.getEmployeeSelected());
        }
        else if (e.getActionCommand() == "���") {
        	workFinished(mainScreen.getEmployeeSelected());
        }
        else if (e.getActionCommand() == "���� ����") {
        	employeeManage();
        }
        else if (e.getActionCommand() == "���� �߰�") {
        	int qualified = checkPassword(employeeManageScreen.getPasswordInput());
        	if (qualified == 0) {
        		new WarningWindow();
        		return;
        	}
        	addEmployee(employeeManageScreen.getEmployeeInput());
        }
        else if (e.getActionCommand() == "���� ����") {
        	int qualified = checkPassword(employeeManageScreen.getPasswordInput());
        	if (qualified == 0) {
        		new WarningWindow();
        		return;
        	}
        	delEmployee(employeeManageScreen.getEmployeeSelected());
        }
        else if (e.getActionCommand() == "�ð� Ȯ��") {
        	checkTime(employeeManageScreen.getEmployeeSelected());
        }
        else if (e.getActionCommand() == "�ð� ����") {
        	int qualified = checkPassword(employeeManageScreen.getPasswordInput());
        	if (qualified == 0) {
        		new WarningWindow();
        		return;
        	}
        	int hour =  Integer.parseInt(employeeManageScreen.getHourInput());
        	int min = Integer.parseInt(employeeManageScreen.getMinInput());
        	resetWorkTime(employeeManageScreen.getEmployeeSelected(), hour, min);
        }
    }
	
	//add Notice
	private void addNotice(String input) {
		if(input==null||input.length()==0) return;
		noticeBoardData.addElement(input);
		updateFile(0);
	}
	//delete Notice
	private void delNotice(int input) {
		if (noticeBoardData.getSize() == 0) return;
		noticeBoardData.remove(input);
		updateFile(0);
	}
	//set Employee work start!
	private void workStart(int input) {
		Employee tmp = employeeVec.get(input);
		employeeVec.remove(input);
		tmp.workStart();
		employeeVec.add(input,tmp);
		
		employeeData.remove(input);
		employeeData.add(input, tmp.getString());
		updateFile(1);
	}
	//set Employee work Finish!
	private void workFinished(int input) {
		Employee tmp = employeeVec.get(input);
		employeeVec.remove(input);
		tmp.workFinish();
		employeeVec.add(input,tmp);
		
		employeeData.remove(input);
		employeeData.add(input, tmp.getString());
		updateFile(1);
	}
	//show EmployeeManagement Window
	private void employeeManage() {
		employeeManageScreen = new EmployeeManageScreen("���� ���� ������", employeeData);
		employeeManageScreen.addBtnListener(this);
	}
	//Add Employee
	private void addEmployee(String input) {
		Employee tmp = new Employee(input);
		employeeVec.addElement(tmp);
		
		employeeData.addElement(tmp.getString());
		updateFile(1);
	}
	//Delete Employee
	private void delEmployee(int input) {
		employeeVec.remove(input);
		
		employeeData.remove(input);
		updateFile(1);
	}
	//check Time
	private void checkTime(int input) {
		int hour = employeeVec.elementAt(input).getWorkHour();
		int min = employeeVec.elementAt(input).getWorkMin();
		employeeManageScreen.setWorkTime(hour+"�� "+min+"��");
	}
	//reset Employee Work Time
	private void resetWorkTime(int input, int hour, int min) {
		employeeVec.elementAt(input).resetWorkTime(hour, min);
		updateFile(1);
	}
	//check password
	private int checkPassword(String input) {
		if (input.equals("1111")) return 1;
		return 0;
	}
	
	//update File
	private void updateFile(int num) {
		if (num == 0) {
			try {
				FileOutputStream fos = new FileOutputStream("noticeBoard.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(noticeBoardData);
				oos.close();
				fos.close();
			}
			catch (IOException e) {
				System.out.println(e);
			}
		}
		else {
			try {
				FileOutputStream fos = new FileOutputStream("employeeData.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(employeeVec);
				oos.close();
				fos.close();
			}
			catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
