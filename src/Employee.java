package cm_machine;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Employee  implements Serializable {
	private String name;
	private boolean working;
	private int workHour;
	private int workMin;
	private LocalDateTime  workStartTime;
	
	public Employee(String nameInput) {
		name = nameInput;
		working = false;
		workHour = 0;
		workMin = 0;
		workStartTime = null;
	}
	
	
	//���� �� ����.
	public void workStart() {
		workStartTime = LocalDateTime.now();
		working = true;
	}
	
	//���� �� ����.
	//���� ���� �ʰ� �ִ� ������ ���ó����Ű�� return -1
	//���������� �� ó���Ǿ��ٸ� return 1
	public int workFinish() {
		if (working == false) {
			return -1;
		}
		working = false;
		LocalDateTime currentTime = LocalDateTime.now();
		int hour = currentTime.getHour() - workStartTime.getHour();
		int minute = currentTime.getMinute() - workStartTime.getMinute();
		
		if (hour >= 0) {
			workHour += hour;
		}
		else {
			workHour += 24 + hour;
		}
		
		if (minute >= 0) {
			workMin += minute;
		}
		else {
			workMin += 60 + minute;
			hour -= 1;
		}
		
		workStartTime = null;
		return 1;
	}
	
	//�ٹ��ð� �ʱ�ȭ
	public void resetWorkTime(int hour, int minute) {
		if (minute >= 60) {
			hour += minute / 60;
			minute = minute % 60;
		}
		workHour = hour;
		workMin = minute;
	}
	
	//����Ʈ�� ����� ��Ʈ�� get
	public String getString() {
		String result = "";
		result += this.name;
		if (this.working == true) {
			result += "(�����) / ��� �ð�: ";
			result += (workStartTime.getDayOfMonth()+"");
			result += "�� ";
			result += (workStartTime.getHour()+"");
			result += "�� ";
			result += (workStartTime.getMinute()+"");
			result += "�� ";
		}
		return result;
	}
	
	public int getWorkHour() {
		return workHour;
	}
	
	public int getWorkMin() {
		return workMin;
	}
}
