package cm_machine;
import javax.swing.*;

public class WarningWindow extends JFrame {
	private JLabel warningSign = new JLabel("��й�ȣ�� Ʋ�Ƚ��ϴ�!");
	public WarningWindow() {
		super("���!");
		warningSign.setSize(250,100);
		this.add(warningSign);
		this.setBounds(100, 100, 250, 100);
		this.setVisible(true);
	}

}
