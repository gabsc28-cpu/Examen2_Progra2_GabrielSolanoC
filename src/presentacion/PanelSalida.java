/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import entidades.Registro;
import logica.ParqueoService;
import utilidades.UtilidadesTablas;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabsc
 */

public class PanelSalida extends JPanel {

    private JTable tablaActivos;
    private JButton btnSalida;
    private JLabel lblMensaje;

    private ParqueoService service;

    public PanelSalida() {
        this.service = new ParqueoService();
        initComponentes();
        cargarTabla();
    }

    private void initComponentes() {
        setLayout(new BorderLayout());

        tablaActivos = new JTable();
        btnSalida = new JButton("Registrar Salida");
        lblMensaje = new JLabel(" ");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(btnSalida, BorderLayout.CENTER);
        panelInferior.add(lblMensaje, BorderLayout.SOUTH);

        add(new JScrollPane(tablaActivos), BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnSalida.addActionListener(e -> registrarSalida());
    }

    public void cargarTabla() {
        List<Registro> lista = service.obtenerActivos();

        String[] columnas = {"Placa", "Tipo", "Hora Entrada"};
        List<String[]> datos = new ArrayList<>();

        for (Registro r : lista) {
            datos.add(new String[]{
                    r.getVehiculo().getPlaca(),
                    r.getVehiculo().getTipo(),
                    r.getHoraEntrada().toString()
            });
        }

        UtilidadesTablas.configurarTablaConDatos(tablaActivos, columnas, datos);
    }

    private void registrarSalida() {
        int fila = tablaActivos.getSelectedRow();

        if (fila == -1) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText("Seleccione un vehículo");
            return;
        }

        try {
            String placa = tablaActivos.getValueAt(fila, 0).toString();

            service.registrarSalida(placa);

            lblMensaje.setForeground(new Color(0, 128, 0));
            lblMensaje.setText("Salida registrada correctamente");

            cargarTabla();

        } catch (Exception ex) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText(ex.getMessage());
        }
    }
}

