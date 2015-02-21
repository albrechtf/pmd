package de.florianalbrecht.pmd.vb6;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.pmd.lang.AbstractParser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ast.ParseException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Lexer;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser;
import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Parser.ModuleContext;
import de.florianalbrecht.pmd.vb6.ast.*;

public class VB6Parser extends AbstractParser {

	private VisualBasic6Parser parser;

	private static Map<Integer, Class<? extends AbstractVB6Node>> ruleAstClasses = new HashMap<Integer, Class<? extends AbstractVB6Node>>();

	static {
		ruleAstClasses.put(VisualBasic6Parser.RULE_module, ASTModule.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_moduleHeader, ASTModuleHeader.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_moduleConfig, ASTModuleConfig.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_moduleAttributes, ASTModuleAttributes.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_moduleOptions, ASTModuleOptions.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_moduleOption, ASTModuleOption.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_moduleBody, ASTModuleBody.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_moduleBodyElement, ASTModuleBodyElement.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_moduleBlock, ASTModuleBlock.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_attributeStmt, ASTAttributeStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_block, ASTBlock.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_blockStmt, ASTBlockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_comment, ASTComment.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_appactivateStmt, ASTAppactivateStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_beepStmt, ASTBeepStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_chdirStmt, ASTChdirStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_chdriveStmt, ASTChdriveStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_closeStmt, ASTCloseStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_constStmt, ASTConstStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_constSubStmt, ASTConstSubStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_dateStmt, ASTDateStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_declareStmt, ASTDeclareStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_deftypeStmt, ASTDeftypeStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_deleteSettingStmt, ASTDeleteSettingStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_doLoopStmt, ASTDoLoopStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_endStmt, ASTEndStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_enumerationStmt, ASTEnumerationStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_enumerationStmt_Constant, ASTEnumerationStmt_Constant.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_eraseStmt, ASTEraseStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_errorStmt, ASTErrorStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_eventStmt, ASTEventStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_exitStmt, ASTExitStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_filecopyStmt, ASTFilecopyStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_forEachStmt, ASTForEachStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_forNextStmt, ASTForNextStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_functionStmt, ASTFunctionStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_getStmt, ASTGetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_goSubStmt, ASTGoSubStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_goToStmt, ASTGoToStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_ifThenElseStmt, ASTIfThenElseStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_ifBlockStmt, ASTIfBlockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_ifConditionStmt, ASTIfConditionStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_ifElseIfBlockStmt, ASTIfElseIfBlockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_ifElseBlockStmt, ASTIfElseBlockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_implementsStmt, ASTImplementsStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_inputStmt, ASTInputStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_killStmt, ASTKillStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_letStmt, ASTLetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_lineInputStmt, ASTLineInputStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_loadStmt, ASTLoadStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_lockStmt, ASTLockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_lsetStmt, ASTLsetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_macroIfThenElseStmt, ASTMacroIfThenElseStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_macroIfBlockStmt, ASTMacroIfBlockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_macroElseIfBlockStmt, ASTMacroElseIfBlockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_macroElseBlockStmt, ASTMacroElseBlockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_midStmt, ASTMidStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_mkdirStmt, ASTMkdirStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_nameStmt, ASTNameStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_onErrorStmt, ASTOnErrorStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_onGoToStmt, ASTOnGoToStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_onGoSubStmt, ASTOnGoSubStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_openStmt, ASTOpenStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_outputList, ASTOutputList.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_outputList_Expression, ASTOutputList_Expression.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_printStmt, ASTPrintStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_propertyGetStmt, ASTPropertyGetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_propertySetStmt, ASTPropertySetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_propertyLetStmt, ASTPropertyLetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_putStmt, ASTPutStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_raiseEventStmt, ASTRaiseEventStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_randomizeStmt, ASTRandomizeStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_redimStmt, ASTRedimStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_redimSubStmt, ASTRedimSubStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_resetStmt, ASTResetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_resumeStmt, ASTResumeStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_returnStmt, ASTReturnStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_rmdirStmt, ASTRmdirStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_rsetStmt, ASTRsetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_savepictureStmt, ASTSavepictureStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_saveSettingStmt, ASTSaveSettingStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_seekStmt, ASTSeekStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_selectCaseStmt, ASTSelectCaseStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_sC_Case, ASTSC_Case.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_sC_Cond, ASTSC_Cond.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_sC_CaseElse, ASTSC_CaseElse.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_sendkeysStmt, ASTSendkeysStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_setattrStmt, ASTSetattrStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_setStmt, ASTSetStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_stopStmt, ASTStopStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_subStmt, ASTSubStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_timeStmt, ASTTimeStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_typeStmt, ASTTypeStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_typeStmt_Element, ASTTypeStmt_Element.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_typeOfStmt, ASTTypeOfStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_unloadStmt, ASTUnloadStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_unlockStmt, ASTUnlockStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_valueStmt, ASTValueStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_variableStmt, ASTVariableStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_variableListStmt, ASTVariableListStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_variableSubStmt, ASTVariableSubStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_whileWendStmt, ASTWhileWendStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_widthStmt, ASTWidthStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_withStmt, ASTWithStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_writeStmt, ASTWriteStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_explicitCallStmt, ASTExplicitCallStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_eCS_ProcedureCall, ASTECS_ProcedureCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_eCS_MemberProcedureCall, ASTECS_MemberProcedureCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_implicitCallStmt_InBlock, ASTImplicitCallStmt_InBlock.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_iCS_B_SubCall, ASTICS_B_SubCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_iCS_B_FunctionCall, ASTICS_B_FunctionCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_iCS_B_MemberSubCall, ASTICS_B_MemberSubCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_iCS_B_MemberFunctionCall, ASTICS_B_MemberFunctionCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_implicitCallStmt_InStmt, ASTImplicitCallStmt_InStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_iCS_S_VariableCall, ASTICS_S_VariableCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_iCS_S_FunctionOrArrayCall, ASTICS_S_FunctionOrArrayCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_iCS_S_DictionaryCall, ASTICS_S_DictionaryCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_iCS_S_MembersCall, ASTICS_S_MembersCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_memberPropertyCallStmt, ASTMemberPropertyCallStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_memberFunctionOrArrayCallStmt, ASTMemberFunctionOrArrayCallStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_memberSubCallStmt, ASTMemberSubCallStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_memberCall_Value, ASTMemberCall_Value.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_variableCallStmt, ASTVariableCallStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_dictionaryCallStmt, ASTDictionaryCallStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_functionOrArrayCallStmt, ASTFunctionOrArrayCallStmt.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_argsCall, ASTArgsCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_argCall, ASTArgCall.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_argList, ASTArgList.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_arg, ASTArg.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_argDefaultValue, ASTArgDefaultValue.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_subscripts, ASTSubscripts.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_subscript, ASTSubscript.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_ambiguousIdentifier, ASTAmbiguousIdentifier.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_asTypeClause, ASTAsTypeClause.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_baseType, ASTBaseType.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_certainIdentifier, ASTCertainIdentifier.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_comparisonOperator, ASTComparisonOperator.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_complexType, ASTComplexType.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_fieldLength, ASTFieldLength.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_letterrange, ASTLetterrange.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_lineLabel, ASTLineLabel.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_literal, ASTLiteral.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_type, ASTType.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_typeHint, ASTTypeHint.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_visibility, ASTVisibility.class);
		ruleAstClasses.put(VisualBasic6Parser.RULE_ambiguousKeyword, ASTAmbiguousKeyword.class);
	}

	public VB6Parser(ParserOptions parserOptions) {
		super(parserOptions);
	}

	public VisualBasic6Parser getInternalParser() {
		return parser;
	}

	@Override
	public boolean canParse() {
		return true;
	}

	@Override
	public Node parse(String fileName, Reader source) throws ParseException {
		// use ANTLR based parser, but build own AST
		ModuleContext context = createParser(source).module();

		ASTModule module = new ASTModule(this, context);
		processChildren(module, context);

		return module;
	}

	private void processChildren(Node node, ParseTree context) {
		node.jjtOpen();

		for (int i = 0; i < context.getChildCount(); i++) {
			// construct child node
			Node childNode = constructChildNode(context.getChild(i));
			if (childNode != null) {
				childNode.jjtSetParent(node);
				node.jjtAddChild(childNode, node.jjtGetNumChildren());
				processChildren(childNode, context.getChild(i));
			}
		}

		node.jjtClose();
	}

	private Node constructChildNode(ParseTree child) {
		Class<? extends AbstractVB6Node> astClass = null;
		int id = 0;

		if (child instanceof RuleContext) {
			RuleContext rule = (RuleContext)child;
			// get AST Class based on rule index
			id = rule.getRuleIndex();
			astClass = ruleAstClasses.get(Integer.valueOf(id));
		}
		else if (child instanceof TerminalNode) {
			// nothing to do yet
			return null;
		}
		if (astClass != null) {
			try {
				Constructor<? extends AbstractVB6Node> cstr = astClass.getConstructor(VB6Parser.class, RuleContext.class);
				return cstr.newInstance(this, child);
			}
			catch (Exception e) {
				throw new ParseException("Could not construct AST Object", e);
			}
		}

		throw new ParseException("Encountered unsupported node type: " + child);
	}

	@Override
	public Map<Integer, String> getSuppressMap() {
		// TODO is this ever required?
		return Collections.emptyMap();
	}

	@Override
	protected TokenManager createTokenManager(Reader source) {
		return new VB6TokenManager(source);
	}

	protected VisualBasic6Parser createParser(Reader source) throws ParseException {
		try {
			VisualBasic6Lexer lexer = new VisualBasic6Lexer(new ANTLRInputStream(source));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			parser = new VisualBasic6Parser(tokenStream);

			// TODO handle / pass suppressMarker from getParserOptions()
			return parser;
		}
		catch (IOException e) {
			throw new ParseException(e);
		}
	}

}
