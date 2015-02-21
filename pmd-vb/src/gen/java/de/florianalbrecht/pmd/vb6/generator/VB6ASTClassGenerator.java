package de.florianalbrecht.pmd.vb6.generator;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.antlr.v4.runtime.RuleContext;
import org.apache.commons.lang3.text.WordUtils;

import de.florianalbrecht.pmd.vb6.VB6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;
import de.florianalbrecht.pmd.vb6.ast.AbstractVB6Node;

public class VB6ASTClassGenerator {

	private static final Class<?> VB6_PARSER_CLASS = VB6Parser.class;

	private static final Class<?> VB6_ANTLR_PARSER_CLASS = VisualBasic6Parser.class;

	private static final Class<?> AST_PARENT_CLASS = AbstractVB6Node.class;

	private static final String OUTPUT_DIR = "target/generated-ast";

	private static final String PACKAGE_NAME = "de.florianalbrecht.pmd.vb6.ast";

	private static final int[] IGNORE_RULES = { VisualBasic6Parser.RULE_startRule };

	public static void main(String[] args) throws Exception {
		// get and retrieve rule names
		Field fRN = VB6_ANTLR_PARSER_CLASS.getDeclaredField("ruleNames");
		fRN.setAccessible(true);
		String[] ruleNames = (String[])fRN.get(null);

		// finally, all Rule_ constants
		for (Field f : VB6_ANTLR_PARSER_CLASS.getDeclaredFields()) {
			if (Modifier.isStatic(f.getModifiers()) && f.getName().startsWith("RULE_")) {
				f.setAccessible(true);
				int ruleIndex = f.getInt(null);
				if (!isRuleIgnored(ruleIndex)) {
					String ruleName = ruleNames[ruleIndex];
					String className = "AST" + WordUtils.capitalize(ruleName);

					// create rule class
					createRuleClass(className, ruleIndex, f.getName());
					System.out.println("ruleAstClasses.put(" + VB6_ANTLR_PARSER_CLASS.getSimpleName() + "." + f.getName() + ", " + className
							+ ".class);");
				}
			}
		}
	}

	private static boolean isRuleIgnored(int index) {
		for (int i : IGNORE_RULES) {
			if (i == index) {
				return true;
			}
		}
		return false;
	}

	private static void createRuleClass(String className, int ruleIndex, String ruleIndexFieldName) throws Exception {
		String fullName = PACKAGE_NAME + "." + className;
		File f = new File(new File(OUTPUT_DIR), fullName.replace(".", "/") + ".java");
		f.getParentFile().mkdirs();

		FileWriter fw = new FileWriter(f);
		fw.append("package ").append(PACKAGE_NAME).append(";\n\n");
		fw.append("import ").append(VB6_ANTLR_PARSER_CLASS.getName()).append(";\n");
		if (!AST_PARENT_CLASS.getPackage().getName().equals(PACKAGE_NAME)) {
			fw.append("import ").append(AST_PARENT_CLASS.getName()).append(";\n");
		}
		if (!VB6_PARSER_CLASS.getPackage().getName().equals(PACKAGE_NAME)) {
			fw.append("import ").append(VB6_PARSER_CLASS.getName()).append(";\n");
		}

		fw.append("import ").append(RuleContext.class.getName()).append(";\n\n");

		fw.append("public class ").append(className).append(" extends ").append(AST_PARENT_CLASS.getSimpleName()).append(" {\n\n");
		fw.append("  public ").append(className).append("(").append(VB6_PARSER_CLASS.getSimpleName())
				.append(" parser, RuleContext context) {\n");
		fw.append("    super(parser, context, ").append(VB6_ANTLR_PARSER_CLASS.getSimpleName()).append(".").append(ruleIndexFieldName)
				.append(");\n");
		fw.append("  }\n\n}");

		fw.close();
	}
}
