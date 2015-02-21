package de.florianalbrecht.pmd.vb6.ast;

import org.antlr.v4.runtime.RuleContext;

import de.florianalbrecht.pmd.vb6.VB6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;

public class ASTComment extends AbstractVB6Node {

  public ASTComment(VB6Parser parser, RuleContext context) {
		super(parser, context, VisualBasic6Parser.RULE_comment);
  }

}