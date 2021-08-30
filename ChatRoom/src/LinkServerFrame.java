import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LinkServerFrame extends JFrame{
	private JPanel panel;
	private JLabel label_ip;
	private JLabel label_port;
	private JLabel label_name;
	private JTextField field_ip;
	private JTextField field_port;
	private JTextField field_name;
	private JButton btn_confirm;
	private JLabel label_null;
	
	public LinkServerFrame() {
		setBounds(300,300,500,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Ѽ�������ҡ�����½����");
		Container c = getContentPane();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2,20,20));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		Font font = new Font("΢���ź�",Font.BOLD,20);
		label_ip = new JLabel("IP��");
		label_ip.setFont(font);
		label_port = new JLabel("�˿ڣ�");
		label_port.setFont(font);
		label_name = new JLabel("�ǳƣ�");
		label_name.setFont(font);
		label_null = new JLabel("");
		field_ip = new JTextField("43.248.186.140");
		field_port = new JTextField("10075");
		field_name = new JTextField();
		btn_confirm = new JButton("��½");
		
		btn_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ip = field_ip.getText();
				String port = field_port.getText();
				String name = field_name.getText();
				if(ip.equals("")||port.equals("")||name.equals("")) {
					JOptionPane.showMessageDialog(LinkServerFrame.this,"�������������еĿ�!");
					return;
				}else {
					int Int_port = Integer.valueOf(port).intValue();
					new ChatFrame(name, ip, Int_port);
					setVisible(false);
				}
			}
		});
		
		panel.add(label_ip);
		panel.add(field_ip);
		panel.add(label_port);
		panel.add(field_port);
		panel.add(label_name);
		panel.add(field_name);
		panel.add(label_null);
		panel.add(btn_confirm);
		
		c.add(panel);
		setVisible(true);
	}
}
