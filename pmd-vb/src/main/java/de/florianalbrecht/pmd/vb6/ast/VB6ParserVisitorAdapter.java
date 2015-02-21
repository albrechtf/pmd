package de.florianalbrecht.pmd.vb6.ast;

public class VB6ParserVisitorAdapter implements VB6ParserVisitor {

	@Override
	public Object visit(VB6Node node, Object data) {
		return node.childrenAccept(this, data);
	}

}
