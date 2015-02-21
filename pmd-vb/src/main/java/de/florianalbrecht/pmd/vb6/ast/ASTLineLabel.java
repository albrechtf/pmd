package de.florianalbrecht.pmd.vb6.ast;

import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;
import de.florianalbrecht.pmd.vb6.VB6Parser;
import org.antlr.v4.runtime.RuleContext;

public class ASTLineLabel extends AbstractVB6Node {

  public ASTLineLabel(VB6Parser parser, RuleContext context) {
    super(parser, context, VisualBasic6Parser.RULE_lineLabel);
  }

}