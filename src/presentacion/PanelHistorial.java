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

public class PanelHistorial extends JPanel {

    private JTable tablaHistorial;
    private JButton btnEliminar;
    private JLabel lblMensaje;

    private ParqueoService service;

    public PanelHistorial() {
        this.service = new ParqueoService();
        initComponentes();
        cargarTabla();
    }

    private void initComponentes() {
        setLayout(new BorderLayout());

        tablaHistorial = new JTable();
        btnEliminar = new JButton("Eliminar Historial");
        lblMensaje = new JLabel(" ");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(btnEliminar, BorderLayout.CENTER);
        panelInferior.add(lblMensaje, BorderLayout.SOUTH);

        add(new JScrollPane(tablaHistorial), BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnEliminar.addActionListener(e -> eliminarHistorial());
    }

    public void cargarTabla() {
        List<Registro> lista = service.obtenerHistorial();

        String[] columnas = {
                "Placa", "Tipo", "Hora Entrada", "Hora Salida", "Monto"
        };

        List<String[]> datos = new ArrayList<>();

        for (Registro r : lista) {
            datos.add(new String[]{
                    r.getVehiculo().getPlaca(),
                    r.getVehiculo().getTipo(),
                    r.getHoraEntrada().toString(),
                    r.getHoraSalida().toString(),
                    String.valueOf(r.getMonto())
            });
        }

        UtilidadesTablas.configurarTablaConDatos(tablaHistorial, columnas, datos);
    }

    private void eliminarHistorial() {
        try {
            service.eliminarHistorial();

            lblMensaje.setForeground(new Color(0, 128, 0));
            lblMensaje.setText("Historial eliminado correctamente");

            cargarTabla();

        } catch (Exception ex) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText(ex.getMessage());
        }
    }
}