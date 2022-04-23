package presentacion.paneles;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import negocio.cruce.CruceEnum;
import negocio.funcion.FuncionEnum;
import negocio.mutacion.MutacionEnum;
import negocio.seleccion.SeleccionEnum;
import presentacion.recursos.Colores;

public class PanelGeneral extends JPanel{

	private static final long serialVersionUID = 1L;
	private PanelFuncion pF;
	private PanelGeneracion pG;
	private PanelMutacion pM;
	private PanelSeleccion pS;
	private PanelCruce pC;
	private PanelElitismo pE;
	private PanelGE pGE;
	private PanelPG pPG;
	private PanelEjecutar pEj;
	
	public PanelGeneral() {
		iniGUI();
	}
	
	public void iniGUI() {
		setLayout(null);
		setBounds(5, 85, 310, 680);
		setBackground(Colores.CLARO2);
		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(b);
		
		pF = new PanelFuncion();
		pG = new PanelGeneracion();
		pM = new PanelMutacion();
		pS = new PanelSeleccion();
		pC = new PanelCruce();
		pE = new PanelElitismo();
		pGE = new PanelGE();
		pPG = new PanelPG();
		pEj = new PanelEjecutar();
		
		add(pF);
		add(pM);
		add(pS);
		add(pG);
		add(pC);
		add(pE);
		add(pGE);
		add(pPG);
		add(pEj);
	}
	
	public SeleccionEnum getSeleccion() {
		return pS.getSeleccion();
	}
	
	public MutacionEnum getMutacion() {
		return pM.getMutacion();
	}
	
	public int getPorcentajeMutacion() {
		return pM.getPorcentajeMutacion();
	}
	
	public int getMaxWr() {
		return pGE.getMaxWr();
	}
	
	public int getTamGen() {
		return pGE.getTamGen();
	}
	
	public int getMetIni() {
		return pPG.getMetIni();
	}
	
	public int getMaxPr() {
		return pPG.getMaxPr();
	}
	
	public int getTamPoblacion() {
		return pG.getTamPoblacion();
	}
	
	public int getNumGeneraciones() {
		return pG.getNumGeneraciones();
	}
	
	public FuncionEnum getFuncion() {
		return pF.getFuncion();
	}
	
	public boolean getElitismoSeleccionado() {
		return pE.getElitismoSeleccionado();
	}
	
	public int getPorcentajeElitismo() {
		return pE.getPorcentajeElitismo();
	}
	
	public CruceEnum getCruce() {
		return pC.getCruce();
	}
	
	public int getPorcentajeCruce() {
		return pC.getPorcentajeCruce();
	}
	
	public int getNFuncion() {
		return pF.getNFuncion();
	}
}
