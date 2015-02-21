package de.florianalbrecht.pmd.vb6.ast;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.antlr.v4.runtime.RuleContext;

import de.florianalbrecht.pmd.vb6.VB6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;

public class ASTVisibility extends AbstractVB6Node {

	private static final List<String> VISIBILITY_TEXTS = Arrays.asList(new String[] { "public", "private", "friend", "global" });

  public ASTVisibility(VB6Parser parser, RuleContext context) {
    super(parser, context, VisualBasic6Parser.RULE_visibility);
  }

	private String getVisibilityText() {
		return getImage().trim().toLowerCase(Locale.US);
	}

	public int getVisibility() {
		return VISIBILITY_TEXTS.indexOf(getVisibilityText());
	}

	public boolean isPublic() {
		return getVisibility() == VISIBILITY_PUBLIC;
	}

	public boolean isPrivate() {
		return getVisibility() == VISIBILITY_PRIVATE;
	}

	public boolean isFriend() {
		return getVisibility() == VISIBILITY_FRIEND;
	}

	public boolean isGlobal() {
		return getVisibility() == VISIBILITY_GLOBAL;
	}
}