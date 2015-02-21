package de.florianalbrecht.pmd.vb6.rule;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRuleViolationFactory;
import net.sourceforge.pmd.lang.rule.ParametricRuleViolation;
import de.florianalbrecht.pmd.vb6.ast.AbstractVB6Node;

public class VisualBasicRuleViolationFactory extends AbstractRuleViolationFactory {

	public static final VisualBasicRuleViolationFactory INSTANCE = new VisualBasicRuleViolationFactory();

	private VisualBasicRuleViolationFactory() {
	}

	@Override
	protected RuleViolation createRuleViolation(Rule rule, RuleContext ruleContext, Node node, String message) {
		return new ParametricRuleViolation<AbstractVB6Node>(rule, ruleContext, (AbstractVB6Node)node, message);
	}

	protected RuleViolation createRuleViolation(Rule rule, RuleContext ruleContext, Node node, String message, int beginLine, int endLine) {
		return null; // FIXME
	}

}
