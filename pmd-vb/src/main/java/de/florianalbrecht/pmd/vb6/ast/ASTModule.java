package de.florianalbrecht.pmd.vb6.ast;

import net.sourceforge.pmd.lang.ast.RootNode;

import org.antlr.v4.runtime.RuleContext;

import de.florianalbrecht.pmd.vb6.VB6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;

public class ASTModule extends AbstractVB6Node implements RootNode {

	public ASTModule(VB6Parser parser, RuleContext ruleContext) {
		super(parser, ruleContext, VisualBasic6Parser.RULE_module);
	}



}
