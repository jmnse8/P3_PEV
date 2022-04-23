package presentacion.paneles;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import presentacion.recursos.Colores;

public class PanelPG  extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel infoTam;
	private JComboBox<String> tCB;
	private JLabel infoWr;
	private JSpinner mpS;
	
	public PanelPG() {
		iniGUI();
	}

	private void iniGUI() {
		setLayout(null);
		setBounds(5, 525, 300, 80);
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "ELIGE LAS OPCIONES DE Pr.Ge. ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));
		
		//inte.setBounds(20, 20, 260, 20);
		
		infoTam = new JLabel("Método inicialización:");
		infoTam.setBounds(20, 20, 180, 20);
		infoTam.setForeground(Colores.OSCURO2);
		
		tCB = new JComboBox<String>();
		tCB.addItem("Creciente");
		tCB.addItem("Completa");
		tCB.addItem("Ramped and Half");
		tCB.setBackground(Colores.CLARO1);
		tCB.setForeground(Colores.OSCURO2);
		tCB.setBounds(150, 20, 120, 20);
		tCB.setToolTipText("Seleccionar método inicialización");
		
		infoWr = new JLabel("Máxima profundidad:");
		infoWr.setBounds(20, 45, 180, 20);
		infoWr.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner2 = new SpinnerNumberModel(4, 2, 10, 1);
		mpS = new JSpinner(modeloSpinner2);
		mpS.setBounds(200, 45, 70, 20);
		mpS.setToolTipText("Seleccionar máxima profundidad");
		
		add(infoTam);
		add(tCB);
		add(infoWr);
		add(mpS);
	}
	
	public int getMetIni() {
		switch ((String) tCB.getSelectedItem()) {
		case "Creciente":
			return 0;
		case "Completa":
			return 1;
		case "Ramped and Half":
			return 2;
		default:
			return 0;
		}
	}
	
	public int getMaxPr() {
		return (int) mpS.getValue();
	}
}
