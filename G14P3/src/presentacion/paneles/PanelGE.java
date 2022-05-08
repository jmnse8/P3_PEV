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
	private JLabel infoCasoG;
	private JSpinner cgS;
	
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
		
		infoTam = new JLabel("Nº codones:");//Tamaño de gen
		infoTam.setBounds(20, 20, 90, 20);
		infoTam.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner1 = new SpinnerNumberModel(20, 3, 100, 1);
		tS = new JSpinner(modeloSpinner1);
		tS.setBounds(115, 20, 50, 20);
		tS.setToolTipText("Seleccionar nº codones:");// tamaño del gen
		
		infoWr = new JLabel("Número máximo de wraps:");
		infoWr.setBounds(20, 45, 180, 20);
		infoWr.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner2 = new SpinnerNumberModel(2, 1, 10, 1);
		wrS = new JSpinner(modeloSpinner2);
		wrS.setBounds(200, 45, 70, 20);
		wrS.setToolTipText("Seleccionar número máximo de wraps");
		
		infoCasoG = new JLabel("Nº Caso:");
		infoCasoG.setBounds(180, 20, 50, 20);
		infoCasoG.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner3 = new SpinnerNumberModel(0, 0, 1, 1);
		cgS = new JSpinner(modeloSpinner3);
		cgS.setBounds(230, 20, 40, 20);
		cgS.setToolTipText("Seleccionar caso de gramática");
		
		add(infoTam);
		add(tS);
		add(infoWr);
		add(wrS);
		add(infoCasoG);
		add(cgS);
	}
	
	public int getMaxWr() {
		return (int) wrS.getValue();
	}
	
	public int getTamGen() {
		return (int) tS.getValue();
	}

	public int getCasoGramatica() {
		return (int) cgS.getValue();
	}
}
