package negocio.funcion.funcionGE.gramatica;

import java.util.ArrayList;

public class Gramatica0Texto {
	/*
	  S ::= <expr>
			<expr> ::= ( <expr> AND <expr>) |
			( <expr> OR <expr> ) |
			NOT ( <expr> ) |
			IF ((<expr>) (<expr>) (<expr>)) |
			AO | Al | DO | D1 | D2 | D3
	 */
	private int wraps;
	private int maxWraps;
	private int nCaso;
	private boolean limiteWraps;
	private int i;
	private ArrayList<Integer> codones;

	public Gramatica0Texto(int maxWraps, int nCaso) {
		this.wraps = 0;
		this.maxWraps = maxWraps;
		this.nCaso = nCaso;
		limiteWraps = false;
	}

	// S ::= <expr>
	public String S(ArrayList<Integer> codones) {
		this.codones = codones;
		i = 0;
		return decodeExpr();
	}

	private String decodeExpr() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return "";
		}
		if (nCaso == 0) {
			int instruc = codones.get(i) % 10;
			i++;

			switch (instruc) {
				case 0:
					return andOp();
				case 1:
					return orOp();
				case 2:
					return notPre();
				case 3:
					return ifPre();
				case 4:
					return "A0";
				case 5:
					return "A1";
				case 6:
					return "D0";
				case 7:
					return "D1";
				case 8:
					return "D2";
				case 9:
					return "D3";
				default:
					return "";
			}
		} else {
			int instruc = codones.get(i) % 15;
			i++;

			switch (instruc) {
				case 0:
					return andOp();
				case 1:
					return orOp();
				case 2:
					return notPre();
				case 3:
					return ifPre();
				case 4:
					return "A0";
				case 5:
					return "A1";
				case 6:
					return "A2";
				case 7:
					return "D0";
				case 8:
					return "D1";
				case 9:
					return "D2";
				case 10:
					return "D3";
				case 11:
					return "D4";
				case 12:
					return "D5";
				case 13:
					return "D6";
				case 14:
					return "D7";
				default:
					return "";
			}
		}
	}

	private String andOp() {//( <expr> AND <expr>)
		return "(" + decodeExpr() + " AND " + decodeExpr() + ")";
	}
	
	private String orOp() {//( <expr> OR <expr> )
		return "(" + decodeExpr() + " OR " + decodeExpr() + ")";
	}
	
	private String notPre() {// NOT (<expr>)
		return "NOT(" + decodeExpr() + ")";
	}
	
	private String ifPre() {// IF ((<expr>) (<expr>) (<expr>))
		return "IF(" + decodeExpr() + " ," + decodeExpr() + " ," + decodeExpr() + ")";
	}
	
	public boolean getLlegadoLimiteWraps() {
		return limiteWraps;
	}
}
