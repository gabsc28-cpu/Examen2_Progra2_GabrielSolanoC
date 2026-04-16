/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;
import logica.ParqueoService;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author gabsc
 */

public class PanelIngreso extends JPanel {

    private JTextField txtPlaca;
    private JComboBox<String> cbTipo;
    private JButton btnRegistrar;
    private JLabel lblMensaje;

    private ParqueoService service;

    public PanelIngreso() {
        this.service = new ParqueoService();
        initComponentes();
    }

    private void initComponentes() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblPlaca = new JLabel("Placa:");
        JLabel lblTipo = new JLabel("Tipo:");

        txtPlaca = new JTextField(15);
        cbTipo = new JComboBox<>(new String[]{"Carro", "Moto"});

        btnRegistrar = new JButton("Registrar Ingreso");
        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(Color.RED);

        gbc.insets = new Insets(10, 10, 10, 10);

        // Placa
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblPlaca, gbc);

        gbc.gridx = 1;
        add(txtPlaca, gbc);

        // Tipo
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblTipo, gbc);

        gbc.gridx = 1;
        add(cbTipo, gbc);

        // Botón
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnRegistrar, gbc);

        // Mensaje
        gbc.gridy = 3;
        add(lblMensaje, gbc);

        // Acción del botón
        btnRegistrar.addActionListener(e -> registrarIngreso());
    }

    private void registrarIngreso() {
        try {
            String placa = txtPlaca.getText();
            String tipo = (String) cbTipo.getSelectedItem();

            service.registrarIngreso(placa, tipo);

            lblMensaje.setForeground(new Color(0, 128, 0));
            lblMensaje.setText("Ingreso registrado correctamente");

            txtPlaca.setText("");
            cbTipo.setSelectedIndex(0);

        } catch (Exception ex) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText(ex.getMessage());
        }
    }
}
