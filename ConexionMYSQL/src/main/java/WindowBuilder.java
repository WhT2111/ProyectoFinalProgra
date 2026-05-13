import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import javax.swing.event.ListSelectionListener;

public class WindowBuilder extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre, txtGenero, txtEspecie, txtRecurso, txtRegion, txtRango, txtAño, txtWinrate;
	private JComboBox<String> txtPosicion;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JLabel lblFoto;

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
		setBounds(10, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);


		// Formulario
		JPanel panelFormulario = new JPanel(new GridLayout(0, 4, 10, 10));
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
		String[] opcionesPosicion = { "Top", "Jungle", "Mid", "ADC", "Sup" };
		txtPosicion = new JComboBox<>(opcionesPosicion);
		panelFormulario.add(txtPosicion);

		panelFormulario.add(new JLabel("Tipo Rango:"));
		txtRango = new JTextField(); panelFormulario.add(txtRango);

		panelFormulario.add(new JLabel("Año Salida:"));
		txtAño = new JTextField(); panelFormulario.add(txtAño);
		
		panelFormulario.add(new JLabel("Winrate:"));
		txtWinrate = new JTextField(); panelFormulario.add(txtWinrate);
		
	
		lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblFoto.setBounds(450, 145, 35, 35); 
		contentPane.add(lblFoto);
		

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
		modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Género", "Especie", "Recurso", "Región", "Posición", "Rango", "Año", "Winrate"}, 0);
		table = new JTable(modeloTabla);
		// Solo permite seleccionar una fila a la vez
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		
		//Seleccionar en tabla (Fill de datos según la selección)
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            int fila = table.getSelectedRow();
		            if (fila != -1) {
		                String nombre = table.getValueAt(fila, 0).toString();
		                txtNombre.setText(nombre);
		                txtGenero.setText(table.getValueAt(fila, 1).toString());
		                txtEspecie.setText(table.getValueAt(fila, 2).toString());
		                txtRecurso.setText(table.getValueAt(fila, 3).toString());
		                txtRegion.setText(table.getValueAt(fila, 4).toString());
		                String posicionTabla = table.getValueAt(fila, 5).toString();
		                txtPosicion.setSelectedItem(posicionTabla);
		                txtRango.setText(table.getValueAt(fila, 6).toString());
		                txtAño.setText(table.getValueAt(fila, 7).toString());
		                String wr = table.getValueAt(fila, 8).toString().replace("%", "");
		                txtWinrate.setText(wr);
		                cargarImagen(nombre);
		            }
		        }
		    }
		});


		// Botón Actualizar
		btnActualizar.addActionListener(e -> {
			Campeon p = capturarCampos();
			FuncionesSelects.actualizar(p); 
			JOptionPane.showMessageDialog(this, "Personaje actualizado con éxito");
			actualizarTabla(FuncionesSelects.obtenerTodos());
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				List<Campeon> todos = FuncionesSelects.obtenerTodos();
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
		    String añoStr = txtAño.getText().trim();
		    String wrStr = txtWinrate.getText().trim().replace("%", ""); 

		    Integer añoInt = (añoStr.isEmpty()) ? 0 : Integer.parseInt(añoStr);
		    Double wrDouble = (wrStr.isEmpty()) ? 0.0 : Double.parseDouble(wrStr);

		    List<Campeon> resultados = FuncionesSelects.buscar(
		            txtNombre.getText(), txtGenero.getText(), txtEspecie.getText(), 
		            txtRecurso.getText(), txtRegion.getText(), txtPosicion.toString(), 
		            txtRango.getText(), añoInt, wrDouble
		            );
		    actualizarTabla(resultados);
		});

		// Botón Guardar
		btnGuardar.addActionListener(e -> {
			Campeon p = capturarCampos();
			FuncionesSelects.guardarPersonaje(p);
			JOptionPane.showMessageDialog(this, "Guardado en DB");
			actualizarTabla(FuncionesSelects.obtenerTodos());
		});

		// Botón Borrar
		btnEliminar.addActionListener(e -> {
			FuncionesSelects.eliminarPersonaje(txtNombre.getText());
			actualizarTabla(FuncionesSelects.obtenerTodos());
		});
		
		// Botón Limpiar
		btnLimpiar.addActionListener(e -> {
		    txtNombre.setText("");
		    txtGenero.setText("");
		    txtEspecie.setText("");
		    txtRecurso.setText("");
		    txtRegion.setText("");
		    txtPosicion.setSelectedIndex(0);
		    txtRango.setText("");
		    txtAño.setText("");
		    txtWinrate.setText("");
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
	private void actualizarTabla(List<Campeon> lista) {
		modeloTabla.setRowCount(0); 
		for (Campeon p : lista) {
			modeloTabla.addRow(new Object[]{
					p.getNombre(), p.getGenero(), p.getEspecie(), p.getRecurso(),
					p.getRegion(), p.getPosicion(), p.getTipoDeRango(), p.getAñoDeSalida(), p.getWinrate() + "%"
			});
		}
	}

	private Campeon capturarCampos() {
		Campeon p = new Campeon();
		p.setNombre(txtNombre.getText());
		p.setGenero(txtGenero.getText());
		p.setEspecie(txtEspecie.getText());
		p.setRecurso(txtRecurso.getText());
		p.setRegion(txtRegion.getText());
		p.setPosicion(txtPosicion.getSelectedItem().toString());
		p.setTipoDeRango(txtRango.getText());
		int a = txtAño.getText().isEmpty() ? 0 : Integer.parseInt(txtAño.getText());
		p.setAñoDeSalida(a);
		double b = txtWinrate.getText().isEmpty() ? 0 : Double.parseDouble(txtWinrate.getText());
		p.setWinrate(b);
		return p;
	}
	private void cargarImagen(String nombreImagen) {
	    try { 
	        ImageIcon iconoOriginal = new ImageIcon("img/" + nombreImagen + ".png");
	        Image imgEscalada = iconoOriginal.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
	        lblFoto.setIcon(new ImageIcon(imgEscalada));
	    } catch (Exception e) {
	        lblFoto.setIcon(null); 
	    }
	}
}

