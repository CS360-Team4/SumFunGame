
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class PreGameScreen extends JFrame {

	private JPanel mainPanel;
	private JButton btnUntimed;
	private JButton btnTimed;
	private JTextField txtName;
	private JLabel lblName;

	public PreGameScreen() {
		mainPanel = new JPanel();
		// GridLayout mainLayout = new GridLayout(2,2);
		// mainPanel.setLayout(mainLayout);

		btnUntimed = new JButton("Start Untimed Game");
		btnUntimed.addActionListener(new UntimedListener());
		btnTimed = new JButton("Start Timed Game");
		btnTimed.addActionListener(new TimedListener());
		lblName = new JLabel("Name: ");
		txtName = new JTextField("");
		txtName.setColumns(15);

		mainPanel.add(lblName);
		mainPanel.add(txtName);
		mainPanel.add(btnUntimed);
		mainPanel.add(btnTimed);

		add(mainPanel);
		setSize(350, 100);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Sum Fun Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class UntimedListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(txtName.getText().length() > 0){
				try {
					GameBoard firstGame = new UntimedGame(txtName.getText());
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PreGameScreen.this.dispose();
			}
		}
	}

	private class TimedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(txtName.getText().length() > 0){
				try {
					GameBoard firstGame = new TimedGame(txtName.getText());
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PreGameScreen.this.dispose();
			}
		}
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}
}
