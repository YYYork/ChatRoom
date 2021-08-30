import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class ChatFrame extends JFrame {
	private JPanel allPane;
	private JSplitPane splitPane;
	private JScrollPane ChatScreenPane;
	private JPanel bottomPane;
	private JScrollPane printPane;
	private JPanel btnPane;
	private JTextArea area_chatScreen;
	private JTextArea area_printContent;
	private JButton btn_send;
	private JLabel label_null1;
	private JLabel label_null2;
	private JLabel label_null3;

	private String name;
	private Client client;

	public ChatFrame(String name, String ip, int port) {
		this.name = name;
		client = new Client(ip, port);
		
		setBounds(300, 300, 1200, 700);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Container c = getContentPane();
		setTitle("鸭梨聊天室 —— 昵称：" + name);

		allPane = new JPanel();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		area_chatScreen = new JTextArea();
		ChatScreenPane = new JScrollPane(area_chatScreen);
		ChatScreenPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		bottomPane = new JPanel();
		area_printContent = new JTextArea();
		printPane = new JScrollPane(area_printContent);
		btnPane = new JPanel();
		btn_send = new JButton("发送");

		btn_send.registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date(); // 创建一个Date对象，获取当前时间
				// 指定格式化格式
				SimpleDateFormat f = new SimpleDateFormat("[yyyy年MM月dd日/E/HH点mm分ss秒]");
				String message = f.format(now) + " " + name + ":\n" + area_printContent.getText();
				area_printContent.setText("");
				client.send(message);
			}
		},KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_MASK),JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		btn_send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date(); // 创建一个Date对象，获取当前时间
				// 指定格式化格式
				SimpleDateFormat f = new SimpleDateFormat("[yyyy年MM月dd日/E/HH点mm分ss秒]");
				String message = f.format(now) + " " + name + ":\n" + area_printContent.getText();
				area_printContent.setText("");
				client.send(message);
			}
		});

		label_null1 = new JLabel();
		label_null2 = new JLabel();
		label_null3 = new JLabel();

		allPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		allPane.add(splitPane);

		splitPane.setLeftComponent(ChatScreenPane);
		splitPane.setRightComponent(bottomPane);
		splitPane.setContinuousLayout(true);
		splitPane.setDividerLocation(450);

		area_chatScreen.setEditable(false);

		btnPane.setLayout(new GridLayout(1, 4, 30, 30));
		btnPane.add(label_null1);
		btnPane.add(label_null2);
		btnPane.add(label_null3);
		btnPane.add(btn_send);
		bottomPane.setLayout(new GridLayout(2, 1, 0, 0));
		bottomPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		bottomPane.add(printPane);
		bottomPane.add(btnPane);

		c.add(splitPane);
		
		Thread t = new ReceiveMessageThread();
		t.start();

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				int value = JOptionPane.showConfirmDialog(ChatFrame.this, "你要退出聊天室吗？");

				if (value == JOptionPane.YES_OPTION) {	
					client.send("%EXIT%:"+name);
					try {
						Thread.sleep(400);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					client.close();
					System.exit(0);

				}
			}

		});
		
		setVisible(true);
	}

	private class ReceiveMessageThread extends Thread {
		@Override
		public void run() {
			while (true) {
				String message = client.receive();
				area_chatScreen.append(message+"\n");
				int maxHeight = ChatScreenPane.getVerticalScrollBar().getMaximum();
				ChatScreenPane.getViewport().setViewPosition(new Point(0, maxHeight));
			}
		}
	}

}
