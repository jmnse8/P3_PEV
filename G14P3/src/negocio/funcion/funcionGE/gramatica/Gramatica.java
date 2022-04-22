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

	public Gramatica(int maxWraps, int[] casoProbar, int nCaso) {
		this.wraps = 0;
		this.maxWraps = maxWraps;
		this.casoProbar = casoProbar;
		this.nCaso = nCaso;
		limiteWraps = false;
	}

	// S ::= <expr>
	public int S(ArrayList<Integer> codones) {
		return decodeExpr(codones, 0);
	}

	// <expr> ::= (<expr> <op> <expr> ) | <pre-operation> | <var>

	private int expr1(ArrayList<Integer> codones, int i) {//(<expr> <op> <expr> )
		return decodeOp(codones, i);
	}

	private int expr2(ArrayList<Integer> codones, int i) {//<pre-operation>
		return decodePreOp(codones, i);
	}

	private int expr3(ArrayList<Integer> codones, int i) {// <var>
		return decodeVar(codones, i);
	}

	private int decodeExpr(ArrayList<Integer> codones, int i) {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps)
			limiteWraps = true;
		
		int instruc = codones.get(i) % 3;

		switch (instruc) {
		case 0:
			return expr1(codones, i);
		case 1:
			return expr2(codones, i);
		case 2:
			return expr3(codones, i);
		default:
			return Integer.MAX_VALUE;
		}
	}
	
	//<op> ::= AND | OR
	
	private int andOp(ArrayList<Integer> codones, int i) {
		return (decodeExpr(codones, i) == 1 && decodeExpr(codones, i) == 1)? 1 : 0;
	}
	
	private int orOp(ArrayList<Integer> codones, int i) {
		return (decodeExpr(codones, i) == 1 || decodeExpr(codones, i) == 1)? 1 : 0;
	}
	
	private int decodeOp(ArrayList<Integer> codones, int i) {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps)
			limiteWraps = true;
		
		int instruc = codones.get(i) % 2;

		switch (instruc) {
		case 0:
			return andOp(codones, i);
		case 1:
			return orOp(codones, i);
		default:
			return Integer.MAX_VALUE;
		}
	}
	
	//<pre-operation> ::= NOT (<expr>) | IF ((<expr>) (<expr>) (<expr>))
	
	private int notPre(ArrayList<Integer> codones, int i) {// NOT (<expr>)
		return (decodeExpr(codones, i) == 0) ? 1 : 0;
	}
	
	private int ifPre(ArrayList<Integer> codones, int i) {// IF ((<expr>) (<expr>) (<expr>))
		return (decodeExpr(codones, i) == 1) ? decodeExpr(codones, i) : decodeExpr(codones, i);
	}
	
	private int decodePreOp(ArrayList<Integer> codones, int i) {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps)
			limiteWraps = true;
		
		int instruc = codones.get(i) % 2;

		switch (instruc) {
		case 0:
			return notPre(codones, i);
		case 1:
			return ifPre(codones, i);
		default:
			return Integer.MAX_VALUE;
		}
	}
	
	//<var> ::= caso 0 : AO | A1 | DO | D1 | D2 | D3 // caso 1 : AO | A1 | A2 | DO | D1 | D2 | D3 | D4 | D5 | D6 | D7 | D8
	
	private int decodeVar(ArrayList<Integer> codones, int i) {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps)
			limiteWraps = true;
		
		if(nCaso == 0) {// caso 0 : AO | A1 | DO | D1 | D2 | D3
			int instruc = codones.get(i) % 12;

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
			int instruc = codones.get(i) % 6;

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

}
