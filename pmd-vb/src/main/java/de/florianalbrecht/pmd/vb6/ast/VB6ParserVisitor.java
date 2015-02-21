package de.florianalbrecht.pmd.vb6.ast;

public interface VB6ParserVisitor {

	public Object visit(VB6Node node, Object data);

}
