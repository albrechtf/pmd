package de.florianalbrecht.pmd.vb6.ast;

import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.florianalbrecht.pmd.vb6.VB6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;

public class ASTLiteral extends AbstractVB6Node {

	// must be in exact order to match LITERAL_* constants from VBC interface
	private static final List<Integer> LITERAL_TOKEN_TYPES = Arrays
			.asList(new Integer[] { VisualBasic6Parser.STRINGLITERAL, VisualBasic6Parser.INTEGERLITERAL, VisualBasic6Parser.DOUBLELITERAL,
					VisualBasic6Parser.DATELITERAL, VisualBasic6Parser.COLORLITERAL, VisualBasic6Parser.FILENUMBER, VisualBasic6Parser.TRUE,
					VisualBasic6Parser.FALSE, VisualBasic6Parser.NOTHING, VisualBasic6Parser.NULL });

  public ASTLiteral(VB6Parser parser, RuleContext context) {
    super(parser, context, VisualBasic6Parser.RULE_literal);
  }

	public int getLiteralType() {
		// analyze tokens
		for (int i = 0; i < getRuleContext().getChildCount(); i++) {
			ParseTree pt = getRuleContext().getChild(i);
			if (pt instanceof TerminalNode) {
				Integer tokenType = ((TerminalNode)pt).getSymbol().getType();
				if (LITERAL_TOKEN_TYPES.contains(tokenType)) {
					return LITERAL_TOKEN_TYPES.indexOf(tokenType);
				}
			}
		}

		return -1;
	}

}