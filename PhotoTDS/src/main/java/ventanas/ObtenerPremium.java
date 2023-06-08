package ventanas;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;

import javax.swing.JPanel;

import controlador.ControladorPhotoTDS;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ObtenerPremium {

	private static final double PRECIO_PREMIUM = 9.99;
	
	protected JFrame obtenerPremium;
	private ControladorPhotoTDS controlador;
	private JPanel panelPrecio;
	private JLabel lblPrecio;
	private JLabel lblPrecioDescuento;
	
	public ObtenerPremium() {
		controlador = ControladorPhotoTDS.getUnicaInstancia();
		initialize();
	}
	private void initialize() {
		obtenerPremium = new JFrame();
		obtenerPremium.getContentPane().setPreferredSize(new Dimension(300, 300));
		obtenerPremium.setTitle("PhotoTDS");
		obtenerPremium.setResizable(false);
		obtenerPremium.setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		obtenerPremium.setBounds(100, 100, 300, 300);
		obtenerPremium.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obtenerPremium.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		obtenerPremium.getContentPane().add(rigidArea);
		
		JPanel panelTextoPremium = new JPanel();
		panelTextoPremium.setPreferredSize(new Dimension(290, 40));
		obtenerPremium.getContentPane().add(panelTextoPremium);
		panelTextoPremium.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTextPremium = new JLabel("PhotoTDS Premium");
		lblTextPremium.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelTextoPremium.add(lblTextPremium);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panelTextoPremium.add(rigidArea_1);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setPreferredSize(new Dimension(290, 40));
		obtenerPremium.getContentPane().add(panelInfo);
		
		JLabel lblPasateAPremium = new JLabel("Pásate a premium y obtén varios beneficios");
		panelInfo.add(lblPasateAPremium);
		
		JPanel panelSuscripcion = new JPanel();
		panelSuscripcion.setPreferredSize(new Dimension(290, 20));
		obtenerPremium.getContentPane().add(panelSuscripcion);
		
		JLabel lblmes = new JLabel("Suscripción mensual:");
		lblmes.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSuscripcion.add(lblmes);
		
		panelPrecio = new JPanel();
		panelPrecio.setPreferredSize(new Dimension(290, 30));
		obtenerPremium.getContentPane().add(panelPrecio);
		
		lblPrecio = new JLabel("9,99 €/mes");
		panelPrecio.add(lblPrecio);
		
		lblPrecioDescuento = new JLabel();
		panelPrecio.add(lblPrecioDescuento);
		
		JPanel panelDescuento = new JPanel();
		panelDescuento.setPreferredSize(new Dimension(290, 40));
		obtenerPremium.getContentPane().add(panelDescuento);
		panelDescuento.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDescuento = new JLabel("Elige un descuento:");
		panelDescuento.add(lblDescuento);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Sin descuento", "Descuento por edad", "Descuento Popularidad"}));
		panelDescuento.add(comboBox);
		
		JPanel panelAceptar = new JPanel();
		obtenerPremium.getContentPane().add(panelAceptar);
		
		JButton btnSuscribirse = new JButton("Suscribirse");
		btnSuscribirse.setVisible(false);
		panelAceptar.add(btnSuscribirse);
		btnSuscribirse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.makeUserPremium(controlador.getUsuarioActual());
				obtenerPremium.dispose();
			}
		});
		
		JButton btnCancelarSuscripcion = new JButton("Cancelar Suscripcion");
		btnCancelarSuscripcion.setVisible(false);
		panelAceptar.add(btnCancelarSuscripcion);
		btnCancelarSuscripcion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.cancelSuscription(controlador.getUsuarioActual());
				obtenerPremium.dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setVisible(false);
		panelAceptar.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerPremium.dispose();
			}
		});
		
		if (controlador.getUsuarioActual().isPremium()) {
			btnCancelarSuscripcion.setVisible(true);
		} else {
			btnSuscribirse.setVisible(true);
			btnCancelar.setVisible(true);
		}
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) comboBox.getSelectedItem();
				if (selectedOption.equals("Descuento por edad")) {
					JOptionPane.showMessageDialog(obtenerPremium, "Has elegido Descuento Por Edad");
					lblPrecio.setText("<html><s>9,99 €/mes</s></html>");
					double descuento = controlador.calcularDescuento(controlador.getUsuarioActual(), PRECIO_PREMIUM, selectedOption);
					DecimalFormat df = new DecimalFormat("#.##");
					lblPrecioDescuento.setText(df.format(descuento) + " €/mes");
				} else if (selectedOption.equals("Descuento Popularidad")) {
					JOptionPane.showMessageDialog(obtenerPremium, "Has elegido Descuento Popularidad");
					lblPrecio.setText("<html><s>9,99 €/mes</s></html>");
					double descuento = controlador.calcularDescuento(controlador.getUsuarioActual(), PRECIO_PREMIUM, selectedOption);
					DecimalFormat df = new DecimalFormat("#.##");
					lblPrecioDescuento.setText(df.format(descuento) + " €/mes");
				} else {
					lblPrecio.setText("9,99 €/mes");
					lblPrecioDescuento.setText("");
				}
			}
		});
		
	}
}
