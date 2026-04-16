/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author gabsc
 */


public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;

    private PanelIngreso panelIngreso;
    private PanelSalida panelSalida;
    private PanelHistorial panelHistorial;

    public MainFrame() {
        setTitle("Sistema de Parqueo Público");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponentes();
    }

    private void initComponentes() {

        tabbedPane = new JTabbedPane();

        panelIngreso = new PanelIngreso();
        panelSalida = new PanelSalida();
        panelHistorial = new PanelHistorial();

        tabbedPane.addTab("Ingreso", panelIngreso);
        tabbedPane.addTab("Salida", panelSalida);
        tabbedPane.addTab("Historial", panelHistorial);

        // 👇 LISTENER PARA RECARGAR
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane.getSelectedIndex();

                if (index == 1) { // Salida
                    panelSalida.cargarTabla();
                } else if (index == 2) { // Historial
                    panelHistorial.cargarTabla();
                }
            }
        });

        add(tabbedPane);
    }
}