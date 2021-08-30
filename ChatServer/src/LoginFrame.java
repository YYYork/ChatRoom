import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	private JLabel label_port;
	private JTextField field_port;
	private JButton btn_confirm;
	private JPanel panel;

	public LoginFrame() {
		setBounds(300, 300, 400, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container c = getContentPane();
		setTitle("聊天室初始化");

		label_port = new JLabel("开启端口");
		label_port.setFont(new Font("微软雅黑", Font.BOLD, 20));
		field_port = new JTextField();
		btn_confirm = new JButton("确认");

		btn_confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String port = field_port.getText();
				if (port.equals("")) {
					JOptionPane.showMessageDialog(LoginFrame.this, "您不能不输入端口！");
					return;
				} else {
					int Int_port = Integer.valueOf(port).intValue();
						Thread thread = new ThreadHelp();
						Thread thead2 = new ThreadHelpServer(Int_port);
						thread.start();
						thead2.start();
						dispose();
				}
			}
		});

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3, 40, 40));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(label_port);
		panel.add(field_port);
		panel.add(btn_confirm);

		c.add(panel);
		setVisible(true);
	}
}
