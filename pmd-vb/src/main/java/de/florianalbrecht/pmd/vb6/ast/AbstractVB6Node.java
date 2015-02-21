package de.florianalbrecht.pmd.vb6.ast;

import java.util.List;

import net.sourceforge.pmd.lang.ast.AbstractNode;
import net.sourceforge.pmd.lang.ast.Node;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.jaxen.JaxenException;

import de.florianalbrecht.pmd.vb6.VB6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;

public class AbstractVB6Node extends AbstractNode implements VB6Node, VisualBasicConstants {

	private VB6Parser parser;

	private RuleContext ruleContext;

	public AbstractVB6Node(VB6Parser parser, RuleContext ruleContext, int id) {
		super(id);
		this.parser = parser;
		this.ruleContext = ruleContext;
	}

	protected RuleContext getRuleContext() {
		return ruleContext;
	}

	public void jjtOpen() {
		// get first token of tree
		TokenStream stream = parser.getInternalParser().getInputStream();
		Token firstToken = stream.get(ruleContext.getSourceInterval().a);

		if (beginLine == -1) {
			beginLine = firstToken.getLine();
			beginColumn = firstToken.getCharPositionInLine() + 1;
		}
	}

	public void jjtClose() {
		TokenStream stream = parser.getInternalParser().getInputStream();
		Token startToken = stream.get(ruleContext.getSourceInterval().a);
		Token endToken = stream.get(ruleContext.getSourceInterval().b);

		if (beginLine == -1 && (children == null || children.length == 0)) {
			beginColumn = startToken.getCharPositionInLine() + 1;
		}
		if (beginLine == -1) {
			beginLine = startToken.getLine();
		}
		endLine = endToken.getLine();
		endColumn = endToken.getCharPositionInLine() + endToken.getText().length() + 1;
	}

	/**
	 * Accept the visitor. *
	 */
	public Object jjtAccept(VB6ParserVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	/**
	 * Accept the visitor. *
	 */
	public Object childrenAccept(VB6ParserVisitor visitor, Object data) {
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				((VB6Node)children[i]).jjtAccept(visitor, data);
			}
		}
		return data;
	}

	// overridden to suppress compiler warning
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Node> findChildNodesWithXPath(String xpathString) throws JaxenException {
		return super.findChildNodesWithXPath(xpathString);
	}

	public String getImage() {
		return ruleContext.getText();
	}

	@Override
	public String toString() {
		return VisualBasic6Parser.ruleNames[ruleContext.getRuleIndex()];
		// return VisualBasic6Parser.ruleNames[ruleContext.getRuleIndex()] + " @ " + beginLine + "," + beginColumn;
	}

}
