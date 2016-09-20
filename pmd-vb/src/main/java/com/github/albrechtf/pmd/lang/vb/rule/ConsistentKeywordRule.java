package com.github.albrechtf.pmd.lang.vb.rule;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.albrechtf.pmd.lang.vb.ast.ASTCompilationUnit;
import com.github.albrechtf.pmd.lang.vb.ast.ParseException;
import com.github.albrechtf.pmd.lang.vb.ast.VBNode;

public class ConsistentKeywordRule extends AbstractVisualBasicRule {

	private static final String[] VB_KEYWORDS = { "AddHandler", "AddressOf", "Alias", "And", "AndAlso", "As", "Boolean", "ByRef",
			"Byte", "ByVal", "Call", "Case", "Catch", "CBool", "CByte", "CChar", "CDate", "CDec", "CDbl", "Char", "CInt",
			"Class", "CLng", "CObj", "Const", "Continue", "CSByte", "CShort", "CSng", "CStr", "CType", "CUInt", "CULng",
			"CUShort", "Date", "Decimal", "Declare", "Default", "Delegate", "Dim", "DirectCast", "Do", "Double", "Each", "Else",
			"ElseIf", "End", "EndIf", "Enum", "Erase", "Error", "Event", "Exit", "False", "Finally", "For", "Friend", "Function",
			"Get", "GetType", "GetXMLNamespace", "Global", "GoSub", "GoTo", "Handles", "If", "Implements", "Imports",
			"In", "Inherits", "Integer", "Interface", "Is", "IsNot", "Let", "Lib", "Like", "Long", "Loop", "Me", "Mod", "Module",
			"MustInherit", "MustOverride", "MyBase", "MyClass", "Namespace", "Narrowing", "New", "Next", "Not", "Nothing",
			"NotInheritable", "NotOverridable", "Object", "Of", "On", "Operator", "Option", "Optional", "Or", "OrElse",
			"Overloads", "Overridable", "Overrides", "ParamArray", "Partial", "Private", "Property", "Protected", "Public",
			"RaiseEvent", "ReadOnly", "ReDim", "REM", "RemoveHandler", "Resume", "Return", "SByte", "Select", "Set", "Shadows",
			"Shared", "Short", "Single", "Static", "Step", "Stop", "String", "Structure", "Sub", "SyncLock", "Then", "Throw",
			"To", "True", "Try", "TryCast", "TypeOf", "Variant", "Wend", "UInteger", "ULong", "UShort", "Using", "When", "While",
			"Widening", "With", "WithEvents", "WriteOnly", "Xor" };

	private static final String VALIDATION_MESSAGE = "Use consistent case for keywords: Write \"{0}\" instead of \"{1}\"";

	private Map<String, Pattern> patternCache = new HashMap<String, Pattern>();

	@Override
	public Object visit(ASTCompilationUnit node, Object data) {
		// scan whole file (do not use AST)
		try {
			String allText = node.allText(true);
			String[] lines = allText.split("\n");

			int lineNo = 0;
			for (String line : lines) {
				lineNo++;
				line = line.replaceAll("\r$", "").trim();
				while (line.startsWith("\t")) {
					line = line.substring(1);
				}
				while (line.endsWith("\t")) {
					line = line.substring(0, line.length() - 1);
				}

				if (line.toLowerCase(Locale.US).matches("rem(\\s.*)?")) {
					// only verify that REM is written correctly (all uppercase!)
					if (!line.startsWith("REM")) {
						addViolationWithMessage(data, node,
								MessageFormat.format(VALIDATION_MESSAGE, "REM", line.substring(0, 3)), lineNo, lineNo);
						continue;
					}
				}

				for (String keyword : VB_KEYWORDS) {
					verifyKeyword(data, node, line, lineNo, keyword);
				}

			}

			return super.visit(node, data);
		}
		catch (ParseException e) {
			return super.visit(node, data);
		}
	}

	@Override
	public Object visit(VBNode node, Object data) {
		return null;
	}

	private void verifyKeyword(Object data, VBNode node, String codeLine, int lineNo, String expectedKeyword) {
		// remove string constants
		codeLine = codeLine.replaceAll("\"([^\"]|\"\")*\"", "");

		int commentStart = codeLine.indexOf('\'');
		if (commentStart == -1) {
			// TODO handle REM in the line
		}
		if (commentStart > -1) {
			codeLine = codeLine.substring(0, commentStart);
		}

		Pattern p = patternCache.get(expectedKeyword);
		if (p == null) {
			p = Pattern.compile("(?:^|\\s)(" + expectedKeyword + ")(?:$|\\s)", Pattern.CASE_INSENSITIVE);
			patternCache.put(expectedKeyword, p);
		}

		Matcher m = p.matcher(codeLine);
		int start = 0;
		while (m.find(start)) {
			String kw = m.group(1);
			if (!expectedKeyword.equals(kw)) {
				addViolationWithMessage(data, node, MessageFormat.format(VALIDATION_MESSAGE, expectedKeyword, kw), lineNo, lineNo);
			}
			start = m.end();
		}
	}
}
