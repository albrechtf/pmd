package de.florianalbrecht.pmd.vb6;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Lexer;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser.ModuleContext;

public class VisualBasic6ParserTest {

	@Test
	public void testParser() throws Exception {
		CharStream charStream = readTestResource("test1.vbs");
		Lexer lexer = new VisualBasic6Lexer(charStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		VisualBasic6Parser parser = new VisualBasic6Parser(tokenStream);
		ModuleContext context = parser.module();
		printTree(context, 0);
	}

	private static void printTree(ParseTree node, int indent) throws Exception {
		StringBuilder sbIndent = new StringBuilder();
		for (int i = 0; i < indent; i++) {
			sbIndent.append(" ");
		}

		System.out.print(sbIndent);

		if (node instanceof RuleContext) {
			RuleContext rule = (RuleContext)node;
			System.out.println(VisualBasic6Parser.ruleNames[rule.getRuleIndex()]);
			for (int i = 0; i < rule.getChildCount(); i++) {
				printTree(rule.getChild(i), indent + 2);
			}
		}
		else if (node instanceof TerminalNode) {
			TerminalNode tn = (TerminalNode)node;
			System.out.println(VisualBasic6Parser.tokenNames[tn.getSymbol().getType()]);
		}
		else {
			System.out.println("!!UNKNOWN!!");
		}
	}

	private static CharStream readTestResource(String resourceName) throws Exception {
		InputStream in = VisualBasic6ParserTest.class.getClassLoader().getResourceAsStream(resourceName);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		IOUtils.copy(in, baos);

		return new ANTLRInputStream(new String(baos.toByteArray(), "UTF-8"));
	}

}
