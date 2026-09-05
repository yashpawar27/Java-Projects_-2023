package game;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PiecePanel extends JPanel {

	public PiecePanel() {
		setLayout(null);
		
		JLabel titleLBL = new JLabel("Choose Color");
		titleLBL.setFont(new Font("Marker Felt", Font.BOLD | Font.ITALIC, 24));
		titleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		titleLBL.setBounds(95, 6, 233, 40);
		add(titleLBL);

	}
}
