package de.florianalbrecht.pmd.vb6.ast;

import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;
import de.florianalbrecht.pmd.vb6.VB6Parser;
import org.antlr.v4.runtime.RuleContext;

public class ASTICS_S_MembersCall extends AbstractVB6Node {

  public ASTICS_S_MembersCall(VB6Parser parser, RuleContext context) {
    super(parser, context, VisualBasic6Parser.RULE_iCS_S_MembersCall);
  }

}