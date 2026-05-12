import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class WindowBuilder extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre, txtGenero, txtEspecie, txtRecurso, txtRegion, txtPosicion, txtRango, txtAño;
	private JTable table;
	private DefaultTableModel modeloTabla;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				WindowBuilder frame = new WindowBuilder();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public WindowBuilder() {
		setTitle("Gestión de Personajes LoL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);


		// Formulario
		JPanel panelFormulario = new JPanel(new GridLayout(4, 4, 10, 10));
		panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Personaje"));

		panelFormulario.add(new JLabel("Nombre:"));
		txtNombre = new JTextField(); panelFormulario.add(txtNombre);

		panelFormulario.add(new JLabel("Género:"));
		txtGenero = new JTextField(); panelFormulario.add(txtGenero);

		panelFormulario.add(new JLabel("Especie:"));
		txtEspecie = new JTextField(); panelFormulario.add(txtEspecie);

		panelFormulario.add(new JLabel("Recurso:"));
		txtRecurso = new JTextField(); panelFormulario.add(txtRecurso);

		panelFormulario.add(new JLabel("Región:"));
		txtRegion = new JTextField(); panelFormulario.add(txtRegion);

		panelFormulario.add(new JLabel("Posición:"));
		txtPosicion = new JTextField(); panelFormulario.add(txtPosicion);

		panelFormulario.add(new JLabel("Tipo Rango:"));
		txtRango = new JTextField(); panelFormulario.add(txtRango);

		panelFormulario.add(new JLabel("Año Salida:"));
		txtAño = new JTextField(); panelFormulario.add(txtAño);

		contentPane.add(panelFormulario, BorderLayout.NORTH);

		// Botones CRUD
		JPanel panelBotones = new JPanel();
		JButton btnGuardar = new JButton("Crear");
		JButton btnBuscar = new JButton("Buscar");
		JButton btnActualizar = new JButton("Actualizar");
		JButton btnEliminar = new JButton("Eliminar");
		JButton btnLimpiar = new JButton("Limpiar Campos");

		panelBotones.add(btnGuardar);
		panelBotones.add(btnBuscar);
		panelBotones.add(btnActualizar);
		panelBotones.add(btnEliminar);
		panelBotones.add(btnLimpiar);
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		// Panel Tabla
		modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Género", "Especie", "Recurso", "Región", "Posición", "Rango", "Año"}, 0);
		table = new JTable(modeloTabla);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		//Seleccionar en tabla (Fill de datos según la selección)
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if (fila != -1) {
					txtNombre.setText(table.getValueAt(fila, 0).toString());
					txtGenero.setText(table.getValueAt(fila, 1).toString());
					txtEspecie.setText(table.getValueAt(fila, 2).toString());
					txtRecurso.setText(table.getValueAt(fila, 3).toString());
					txtRegion.setText(table.getValueAt(fila, 4).toString());
					txtPosicion.setText(table.getValueAt(fila, 5).toString());
					txtRango.setText(table.getValueAt(fila, 6).toString());
					txtAño.setText(table.getValueAt(fila, 7).toString());
				}
			}
		});


		// Botón Actualizar
		btnActualizar.addActionListener(e -> {
			POJO p = capturarCampos();
			FuncionesSelects.actualizar(p); 
			JOptionPane.showMessageDialog(this, "Personaje actualizado con éxito");
			actualizarTabla(FuncionesSelects.obtenerTodos());
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				List<POJO> todos = FuncionesSelects.obtenerTodos();
				GestorFicheros.guardar(todos);
			}
		});

		try {
			actualizarTabla(FuncionesSelects.obtenerTodos());
		} catch (Exception e) {
			actualizarTabla(GestorFicheros.cargar());
		}


		// Botón buscar
		btnBuscar.addActionListener(e -> {
			String añoStr = txtAño.getText();
			Integer añoInt = (añoStr.isEmpty()) ? 0 : Integer.parseInt(añoStr);

			List<POJO> resultados = FuncionesSelects.buscar(
					txtNombre.getText(), txtGenero.getText(), txtEspecie.getText(), 
					txtRecurso.getText(), txtRegion.getText(), txtPosicion.getText(), 
					txtRango.getText(), añoInt
					);
			actualizarTabla(resultados);
		});

		// Botón Guardar
		btnGuardar.addActionListener(e -> {
			POJO p = capturarCampos();
			FuncionesSelects.guardarPersonaje(p);
			JOptionPane.showMessageDialog(this, "Guardado en DB");
			actualizarTabla(FuncionesSelects.obtenerTodos());
		});

		// Botón Borrar
		btnEliminar.addActionListener(e -> {
			FuncionesSelects.eliminarPersonaje(txtNombre.getText());
			actualizarTabla(FuncionesSelects.obtenerTodos());
		});

		// Evento cierre
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Guardando datos en fichero local...");
			}
		});

		// Carga de datos (al abrir aplicación)
		actualizarTabla(FuncionesSelects.obtenerTodos());
	}

	// Refrescar 
	private void actualizarTabla(List<POJO> lista) {
		modeloTabla.setRowCount(0); 
		for (POJO p : lista) {
			modeloTabla.addRow(new Object[]{
					p.getNombre(), p.getGenero(), p.getEspecie(), p.getRecurso(),
					p.getRegion(), p.getPosicion(), p.getTipoDeRango(), p.getAñoDeSalida()
			});
		}
	}

	private POJO capturarCampos() {
		POJO p = new POJO();
		p.setNombre(txtNombre.getText());
		p.setGenero(txtGenero.getText());
		p.setEspecie(txtEspecie.getText());
		p.setRecurso(txtRecurso.getText());
		p.setRegion(txtRegion.getText());
		p.setPosicion(txtPosicion.getText());
		p.setTipoDeRango(txtRango.getText());
		int a = txtAño.getText().isEmpty() ? 0 : Integer.parseInt(txtAño.getText());
		p.setAñoDeSalida(a);
		return p;
	}
}

