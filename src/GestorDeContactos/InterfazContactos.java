package GestorDeContactos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InterfazContactos extends JFrame {
    private GestorDeContactos gestor;
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;
    private JTextField campoBuscar;

    public InterfazContactos() {
        gestor = new GestorDeContactos();
        inicializarComponentes();
        cargarContactosEnTabla();
    }

    private void inicializarComponentes() {
        setTitle("Gestor de Contactos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemNuevo = new JMenuItem("Nuevo (N)");
        JMenuItem itemSalir = new JMenuItem("Salir");
        JMenuItem itemAyuda = new JMenuItem("Ayuda");

        menuArchivo.add(itemNuevo);
        menuArchivo.add(itemSalir);
        menuArchivo.add(itemAyuda);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        // Configuración del panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBotones.setBackground(Color.LIGHT_GRAY);

        // Botones de acción
        JButton btnAgregar = new JButton("Agregar Contacto");
        JButton btnEditar = new JButton("Editar Contacto");
        JButton btnEliminar = new JButton("Eliminar Contacto");
        JButton btnAyuda = new JButton("Ayuda");
        
        btnAgregar.setBackground(Color.GREEN);
        btnEditar.setBackground(Color.YELLOW);
        btnEliminar.setBackground(Color.RED);
        btnAyuda.setBackground(Color.BLUE);
        
        btnAgregar.setForeground(Color.WHITE);
        btnEditar.setForeground(Color.BLACK);
        btnEliminar.setForeground(Color.WHITE);
        btnAyuda.setForeground(Color.WHITE);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnAyuda);
        
        panelPrincipal.add(panelBotones, BorderLayout.NORTH);

        // Panel central con tabla de contactos
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Apellido", "Teléfono", "Email"}, 0);
        tablaContactos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaContactos);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        campoBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");

        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(campoBuscar);
        panelBusqueda.add(btnBuscar);

        panelPrincipal.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Eventos de botones de menú y de acción
        itemNuevo.addActionListener(e -> abrirFormularioContacto());
        btnAgregar.addActionListener(e -> abrirFormularioContacto());
        btnBuscar.addActionListener(e -> buscarContacto());
        btnEditar.addActionListener(e -> editarContactoSeleccionado());
        btnEliminar.addActionListener(e -> eliminarContactoSeleccionado());
        itemSalir.addActionListener(e -> System.exit(0));
        itemAyuda.addActionListener(e -> mostrarAyuda());
        btnAyuda.addActionListener(e -> mostrarAyuda());

        // Evento para clic en la tabla de contactos
        tablaContactos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaContactos.getSelectedRow();
                if (fila != -1) {
                    String nombre = modeloTabla.getValueAt(fila, 0).toString();
                    cargarContactoEnFormulario(gestor.buscarContacto(nombre));
                }
            }
        });

        // Configuración de atajos de teclado
        configurarAtajosTeclado();
    }

    private void abrirFormularioContacto() {
        FormularioContacto formulario = new FormularioContacto(this, gestor);
        formulario.setVisible(true);
    }

    private void buscarContacto() {
        String nombre = campoBuscar.getText();
        Contacto contacto = gestor.buscarContacto(nombre);
        modeloTabla.setRowCount(0);
        if (contacto != null) {
            modeloTabla.addRow(new Object[]{contacto.getNombre(), contacto.getApellido(), contacto.getTelefono(), contacto.getEmail()});
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún contacto.");
        }
    }

    void cargarContactosEnTabla() {
        modeloTabla.setRowCount(0);
        for (Contacto c : gestor.getListaContactos()) {
            modeloTabla.addRow(new Object[]{c.getNombre(), c.getApellido(), c.getTelefono(), c.getEmail()});
        }
    }

    private void cargarContactoEnFormulario(Contacto contacto) {
        if (contacto != null) {
            FormularioContacto formulario = new FormularioContacto(this, gestor);
            formulario.setDatosContacto(contacto);
            formulario.setVisible(true);
        }
    }

    private void configurarAtajosTeclado() {
        InputMap inputMap = tablaContactos.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = tablaContactos.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "nuevoContacto");
        actionMap.put("nuevoContacto", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioContacto();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "editarContacto");
        actionMap.put("editarContacto", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarContactoSeleccionado();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "eliminarContacto");
        actionMap.put("eliminarContacto", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarContactoSeleccionado();
            }
        });
    }

    private void editarContactoSeleccionado() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombre = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
            cargarContactoEnFormulario(gestor.buscarContacto(nombre));
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para editar.");
        }
    }

    private void eliminarContactoSeleccionado() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombre = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
            Contacto contacto = gestor.buscarContacto(nombre);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el contacto?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                gestor.eliminarContacto(contacto);
                cargarContactosEnTabla();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar.");
        }
    }

    private void mostrarAyuda() {
        JOptionPane.showMessageDialog(this, "Gestor de contactos: Permite agregar, editar, buscar y eliminar contactos.\n" +
                "Atajos de teclado:\n- N: Nuevo contacto\n- E: Editar contacto seleccionado\n- D: Eliminar contacto seleccionado");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazContactos interfaz = new InterfazContactos();
            interfaz.setVisible(true);
        });
    }
}