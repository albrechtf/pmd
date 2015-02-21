package de.florianalbrecht.pmd.vb6;

import java.io.Writer;

import net.sf.saxon.sxpath.IndependentContext;
import net.sourceforge.pmd.lang.AbstractLanguageVersionHandler;
import net.sourceforge.pmd.lang.Parser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.VisitorStarter;
import net.sourceforge.pmd.lang.XPathHandler;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ast.xpath.AbstractASTXPathHandler;
import net.sourceforge.pmd.lang.rule.RuleViolationFactory;
import de.florianalbrecht.pmd.vb6.ast.AbstractVB6Node;
import de.florianalbrecht.pmd.vb6.rule.VisualBasicRuleViolationFactory;

public class VB6Handler extends AbstractLanguageVersionHandler {

	@Override
	public RuleViolationFactory getRuleViolationFactory() {
		return VisualBasicRuleViolationFactory.INSTANCE;
	}

	@Override
	public Parser getParser(ParserOptions parserOptions) {
		return new VB6Parser(parserOptions);
	}

	@Override
	public XPathHandler getXPathHandler() {
		return new AbstractASTXPathHandler() {
			public void initialize() {
			}

			public void initialize(IndependentContext context) {
			}
		};
	}

	@Override
	public VisitorStarter getDumpFacade(final Writer writer, final String prefix, final boolean recurse) {
		return new VisitorStarter() {
			public void start(Node rootNode) {
				new DumpFacade().initializeWith(writer, prefix, recurse, (AbstractVB6Node)rootNode);
			}
		};
	}

}
