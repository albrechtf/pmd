package de.florianalbrecht.pmd.vb6.ast;

import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;
import de.florianalbrecht.pmd.vb6.VB6Parser;
import org.antlr.v4.runtime.RuleContext;

public class ASTMkdirStmt extends AbstractVB6Node {

  public ASTMkdirStmt(VB6Parser parser, RuleContext context) {
    super(parser, context, VisualBasic6Parser.RULE_mkdirStmt);
  }

}