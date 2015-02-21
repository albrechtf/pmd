package de.florianalbrecht.pmd.vb6.ast;


public interface VB6Node {

	/**
	 * Accept the visitor. *
	 */
	Object jjtAccept(VB6ParserVisitor visitor, Object data);

	/**
	 * Accept the visitor. *
	 */
	Object childrenAccept(VB6ParserVisitor visitor, Object data);

}
