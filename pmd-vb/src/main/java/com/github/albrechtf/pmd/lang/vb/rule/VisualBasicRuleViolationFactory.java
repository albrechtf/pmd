package com.github.albrechtf.pmd.lang.vb.rule;

import com.github.albrechtf.pmd.lang.vb.ast.VBNode;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRuleViolationFactory;
import net.sourceforge.pmd.lang.rule.ParametricRuleViolation;

public class VisualBasicRuleViolationFactory extends AbstractRuleViolationFactory {

	public static final VisualBasicRuleViolationFactory INSTANCE = new VisualBasicRuleViolationFactory();

	private VisualBasicRuleViolationFactory() {
	}

	@Override
	protected RuleViolation createRuleViolation(Rule rule, RuleContext ruleContext, Node node, String message) {
		return new ParametricRuleViolation<VBNode>(rule, ruleContext, (VBNode)node, message);
	}

	protected RuleViolation createRuleViolation(Rule rule, RuleContext ruleContext, Node node, String message, int beginLine, int endLine) {
		return null; // FIXME
	}

}
