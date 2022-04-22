package negocio.funcion.funcionGE.gramatica;

import java.util.ArrayList;

/*
 S ::= <expr>
<expr> ::= ( <expr> AND <expr>) |
	( <expr> OR <expr> ) |
	NOT ( <expr> ) |
	IF ((<expr>) (<expr>) (<expr>)) |
	AO | Al | DO | D1 | D2 | D3
__________________________________________________________
S ::= <expr>
<expr> ::= (<expr> <op> <expr> ) |
			<pre-operation> |
			<var>
<op> ::= AND | OR
<pre-operation> ::= NOT (<expr>) |
					IF ((<expr>) (<expr>) (<expr>))
<var> ::= caso 0 : AO | A1 | DO | D1 | D2 | D3 // caso 1 : AO | A1 | A2 | DO | D1 | D2 | D3 | D4 | D5 | D6 | D7 | D8
 */

public class Gramatica {
	private int wraps;
	private int maxWraps;
	private int[] casoProbar;
	private int nCaso;
	private boolean limiteWraps;
	private int i;
	private ArrayList<Integer> codones;

	public Gramatica(int maxWraps, int[] casoProbar, int nCaso) {
		this.wraps = 0;
		this.maxWraps = maxWraps;
		this.casoProbar = casoProbar;
		this.nCaso = nCaso;
		limiteWraps = false;
	}

	// S ::= <expr>
	public int S(ArrayList<Integer> codones) {
		this.codones = codones;
		i = 0;
		return decodeExpr();
	}

	// <expr> ::= (<expr> <op> <expr> ) | <pre-operation> | <var>

	private int expr1() {//(<expr> <op> <expr> )
		return decodeOp();
	}

	private int expr2() {//<pre-operation>
		return decodePreOp();
	}

	private int expr3() {// <var>
		return decodeVar();
	}

	private int decodeExpr() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return -1;
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
			return Integer.MAX_VALUE;
		}
	}
	
	//<op> ::= AND | OR
	
	private int andOp() {
		return (decodeExpr() == 1 && decodeExpr() == 1)? 1 : 0;
	}
	
	private int orOp() {
		int evaluarIzq = decodeExpr(), evaluarDer = decodeExpr();
		return (evaluarIzq == 1 || evaluarDer == 1)? 1 : 0;
	}
	
	private int decodeOp() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return -1;
		}
		
		int instruc = codones.get(i) % 2;
		i++;

		switch (instruc) {
		case 0:
			return andOp();
		case 1:
			return orOp();
		default:
			return Integer.MAX_VALUE;
		}
	}
	
	//<pre-operation> ::= NOT (<expr>) | IF ((<expr>) (<expr>) (<expr>))
	
	private int notPre() {// NOT (<expr>)
		return (decodeExpr() == 0) ? 1 : 0;
	}
	
	private int ifPre() {// IF ((<expr>) (<expr>) (<expr>))
		int evaluar = decodeExpr(), verdadero = decodeExpr(), falso = decodeExpr();
		//System.out.println(i + " ! " + evaluar + " " + verdadero + " " + falso);
		return (evaluar != 1) ? verdadero : falso;
	}
	
	private int decodePreOp() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return -1;
		}
		
		int instruc = codones.get(i) % 2;
		i++;

		switch (instruc) {
		case 0:
			return notPre();
		case 1:
			return ifPre();
		default:
			return Integer.MAX_VALUE;
		}
	}
	
	//<var> ::= caso 0 : AO | A1 | DO | D1 | D2 | D3 // caso 1 : AO | A1 | A2 | DO | D1 | D2 | D3 | D4 | D5 | D6 | D7 | D8
	
	private int decodeVar() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return -1;
		}
		
		if(nCaso == 0) {// caso 0 : AO | A1 | DO | D1 | D2 | D3
			int instruc = codones.get(i) % 6;
			i++;

			switch (instruc) {
			case 0:
				return casoProbar[0];
			case 1:
				return casoProbar[1];
			case 2:
				return casoProbar[2];
			case 3:
				return casoProbar[3];
			case 4:
				return casoProbar[4];
			case 5:
				return casoProbar[5];
			default:
				return Integer.MAX_VALUE;
			}
		}
		else {// caso 1 : AO | A1 | A2 | DO | D1 | D2 | D3 | D4 | D5 | D6 | D7 | D8
			int instruc = codones.get(i) % 12;
			i++;

			switch (instruc) {
			case 0:
				return casoProbar[0];
			case 1:
				return casoProbar[1];
			case 2:
				return casoProbar[2];
			case 3:
				return casoProbar[3];
			case 4:
				return casoProbar[4];
			case 5:
				return casoProbar[5];
			case 6:
				return casoProbar[6];
			case 7:
				return casoProbar[7];
			case 8:
				return casoProbar[8];
			case 9:
				return casoProbar[9];
			case 10:
				return casoProbar[10];
			case 11:
				return casoProbar[11];
			default:
				return Integer.MAX_VALUE;
			}
		}
		
		
	}
	
	public boolean getLlegadoLimiteWraps() {
		return limiteWraps;
	}

}
