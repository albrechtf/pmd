package de.florianalbrecht.pmd.vb6.ast;

import org.antlr.v4.runtime.RuleContext;

import de.florianalbrecht.pmd.vb6.VB6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;

public class ASTSubStmt extends AbstractVB6Node {

  public ASTSubStmt(VB6Parser parser, RuleContext context) {
    super(parser, context, VisualBasic6Parser.RULE_subStmt);
  }

	public int getVisibility() {
		// check for a visibility subnode
		ASTVisibility vis = getFirstChildOfType(ASTVisibility.class);
		if (vis == null) {
			return VISIBILITY_PUBLIC;
		}
		return vis.getVisibility();
	}

}