package presentacion.paneles;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
//import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import presentacion.recursos.Colores;

public class PanelGE extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel infoTam;
	private JSpinner tS;
	private JLabel infoWr;
	private JSpinner wrS;
	
	public PanelGE() {
		iniGUI();
	}

	private void iniGUI() {
		setLayout(null);
		setBounds(5, 445, 300, 80);
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "ELIGE LAS OPCIONES DE Gr.Ev. ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));
		
		//inte.setBounds(20, 20, 260, 20);
		
		infoTam = new JLabel("Tama�o de gen:");
		infoTam.setBounds(20, 20, 180, 20);
		infoTam.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner1 = new SpinnerNumberModel(20, 5, 100, 1);
		tS = new JSpinner(modeloSpinner1);
		tS.setBounds(200, 20, 70, 20);
		tS.setToolTipText("Seleccionar tama�o del gen");
		
		infoWr = new JLabel("N�mero m�ximo de wraps:");
		infoWr.setBounds(20, 45, 180, 20);
		infoWr.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner2 = new SpinnerNumberModel(2, 0, 10, 1);
		wrS = new JSpinner(modeloSpinner2);
		wrS.setBounds(200, 45, 70, 20);
		wrS.setToolTipText("Seleccionar n�mero m�ximo de wraps");
		
		add(infoTam);
		add(tS);
		add(infoWr);
		add(wrS);
	}
	
	public int getMaxWr() {
		return (int) wrS.getValue();
	}
	
	public int getTamGen() {
		return (int) tS.getValue();
	}
}
