package negocio.funcion.funcionGE.gramatica;

import java.util.ArrayList;

public class Gramatica1Texto {

	private int wraps;
	private int maxWraps;
	private int nCaso;
	private boolean limiteWraps;
	private int i;
	private ArrayList<Integer> codones;

	public Gramatica1Texto(int maxWraps, int nCaso) {
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

	// <expr> ::= (<expr> <op> <expr> ) | <pre-operation> | <var>

	private String expr1() {// (<expr> <op> <expr> )
		return decodeOp();
	}

	private String expr2() {// <pre-operation>
		return decodePreOp();
	}

	private String expr3() {// <var>
		return decodeVar();
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

		int instruc = codones.get(i) % 3;
		i++;

		switch (instruc) {
		case 0:
			return expr1();
		case 1:
			return expr2();
		case 2:
			return expr3();
		default:
			return "";
		}
	}

	// <op> ::= AND | OR

	private String andOp() {
		return "(" + decodeExpr() + " AND " + decodeExpr() + ")";
	}

	private String orOp() {
		return "(" + decodeExpr() + " OR " + decodeExpr() + ")";
	}

	private String decodeOp() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return "";
		}

		int instruc = codones.get(i) % 2;
		i++;

		switch (instruc) {
		case 0:
			return andOp();
		case 1:
			return orOp();
		default:
			return "";
		}
	}

	// <pre-operation> ::= NOT (<expr>) | IF ((<expr>) (<expr>) (<expr>))

	private String notPre() {// NOT (<expr>)
		return "NOT(" + decodeExpr() + ")";
	}

	private String ifPre() {// IF ((<expr>) (<expr>) (<expr>))
		return "IF(" + decodeExpr() + " ," + decodeExpr() + " ," + decodeExpr() + ")";
	}

	private String decodePreOp() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return "";
		}

		int instruc = codones.get(i) % 2;
		i++;

		switch (instruc) {
		case 0:
			return notPre();
		case 1:
			return ifPre();
		default:
			return "";
		}
	}

	// <var> ::= caso 0 : AO | A1 | DO | D1 | D2 | D3 // caso 1 : AO | A1 | A2 | DO
	// | D1 | D2 | D3 | D4 | D5 | D6 | D7 | D8

	private String decodeVar() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return "";
		}

		if (nCaso == 0) {// caso 0 : AO | A1 | DO | D1 | D2 | D3
			int instruc = codones.get(i) % 6;
			i++;

			switch (instruc) {
			case 0:
				return "A0";
			case 1:
				return "A1";
			case 2:
				return "D0";
			case 3:
				return "D1";
			case 4:
				return "D2";
			case 5:
				return "D3";
			default:
				return "";
			}
		} else {// caso 1 : AO | A1 | A2 | DO | D1 | D2 | D3 | D4 | D5 | D6 | D7
			int instruc = codones.get(i) % 11;
			i++;

			switch (instruc) {
			case 0:
				return "A0";
			case 1:
				return "A1";
			case 2:
				return "A2";
			case 3:
				return "D0";
			case 4:
				return "D1";
			case 5:
				return "D2";
			case 6:
				return "D3";
			case 7:
				return "D4";
			case 8:
				return "D5";
			case 9:
				return "D6";
			case 10:
				return "D7";
			default:
				return "";
			}
		}

	}

	public boolean getLlegadoLimiteWraps() {
		return limiteWraps;
	}
}
