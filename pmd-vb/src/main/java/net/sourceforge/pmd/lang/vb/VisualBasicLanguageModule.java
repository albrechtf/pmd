package net.sourceforge.pmd.lang.vb;

import net.sourceforge.pmd.lang.BaseLanguageModule;
import net.sourceforge.pmd.lang.vb.rule.VisualBasicRuleChainVisitor;

public class VisualBasicLanguageModule extends BaseLanguageModule {

	public static final String NAME = "Visual Basic";

	public static final String TERSE_NAME = "vb";

	public VisualBasicLanguageModule() {
		super(NAME, null, TERSE_NAME, VisualBasicRuleChainVisitor.class, "vbs", "frm", "bas");
		addVersion("6", new VB6Handler(), true);
	}

}
