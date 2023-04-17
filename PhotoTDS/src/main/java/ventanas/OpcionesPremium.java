package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class OpcionesPremium extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public OpcionesPremium() {
		setResizable(false);
		
		setBounds(100, 100, 145, 173);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnNewButton = new JButton("Premium");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setPreferredSize(new Dimension(105, 23));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setOpaque(false);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel_1);
		
		JButton btnNewButton_1 = new JButton("Generar PDF");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton_1.setPreferredSize(new Dimension(105, 23));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel_2);
		
		JButton btnNewButton_2 = new JButton("Generar Excel");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setPreferredSize(new Dimension(105, 23));
		btnNewButton_2.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_2.add(btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel_3);
		
		JButton btnNewButton_3 = new JButton("Top Me gusta");
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setPreferredSize(new Dimension(105, 23));
		btnNewButton_3.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setOpaque(false);
		btnNewButton_3.setLocation(new Point(200, 200));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_3.add(btnNewButton_3);
	}

}