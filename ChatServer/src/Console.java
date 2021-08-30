import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console extends JFrame{
	public static Console console;
	private JTextArea area;
	private JScrollPane pane;
	public Console() {
		console = this;
		setBounds(300,300,500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		setTitle("��������Ϣ����");
		
		area = new JTextArea();
		area.setBorder(BorderFactory.createLineBorder(Color.black));
		area.append("--------------------------------�ͻ��˿����ɹ�,��ʼ����--------------------------------\n");
		area.setEditable(false);
		pane = new JScrollPane(area);
		pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		c.add(pane,BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void appendMessage(String message) {
		area.append(message);
	}
}
