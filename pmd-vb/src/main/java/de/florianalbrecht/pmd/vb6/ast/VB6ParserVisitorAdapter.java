package de.florianalbrecht.pmd.vb6.ast;

public class VB6ParserVisitorAdapter implements VB6ParserVisitor {

	@Override
	public Object visit(VB6Node node, Object data) {
		return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTAmbiguousIdentifier node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTAmbiguousKeyword node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTAppactivateStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTArg node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTArgCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTArgDefaultValue node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTArgList node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTArgsCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTAsTypeClause node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTAttributeStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTBaseType node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTBeepStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTBlock node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTBlockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTCertainIdentifier node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTChdirStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTChdriveStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTCloseStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTComment node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTComparisonOperator node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTComplexType node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTConstStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTConstSubStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTDateStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTDeclareStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTDeftypeStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTDeleteSettingStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTDictionaryCallStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTDoLoopStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTECS_MemberProcedureCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTECS_ProcedureCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTEndStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTEnumerationStmt_Constant node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTEnumerationStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTEraseStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTErrorStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTEventStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTExitStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTExplicitCallStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTFieldLength node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTFilecopyStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTForEachStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTForNextStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTFunctionOrArrayCallStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTFunctionStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTGetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTGoSubStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTGoToStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTICS_B_FunctionCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTICS_B_MemberFunctionCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTICS_B_MemberSubCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTICS_B_SubCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTICS_S_DictionaryCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTICS_S_FunctionOrArrayCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTICS_S_MembersCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTICS_S_VariableCall node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTIfBlockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTIfConditionStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTIfElseBlockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTIfElseIfBlockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTIfThenElseStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTImplementsStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTImplicitCallStmt_InBlock node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTImplicitCallStmt_InStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTInputStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTKillStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTLetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTLetterrange node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTLineInputStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTLineLabel node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTLiteral node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTLoadStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTLockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTLsetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMacroElseBlockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMacroElseIfBlockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMacroIfBlockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMacroIfThenElseStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMemberCall_Value node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMemberFunctionOrArrayCallStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMemberPropertyCallStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMemberSubCallStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMidStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTMkdirStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModule node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModuleAttributes node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModuleBlock node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModuleBody node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModuleBodyElement node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModuleConfig node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModuleHeader node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModuleOption node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTModuleOptions node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTNameStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTOnErrorStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTOnGoSubStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTOnGoToStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTOpenStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTOutputList_Expression node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTOutputList node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTPrintStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTPropertyGetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTPropertyLetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTPropertySetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTPutStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTRaiseEventStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTRandomizeStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTRedimStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTRedimSubStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTResetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTResumeStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTReturnStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTRmdirStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTRsetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSavepictureStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSaveSettingStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSC_Case node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSC_CaseElse node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSC_Cond node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSeekStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSelectCaseStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSendkeysStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSetattrStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSetStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTStopStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSubscript node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSubscripts node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTSubStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTTimeStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTType node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTTypeHint node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTTypeOfStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTTypeStmt_Element node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTTypeStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTUnloadStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTUnlockStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTValueStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTVariableCallStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTVariableListStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTVariableStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTVariableSubStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTVisibility node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTWhileWendStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTWidthStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTWithStmt node, Object data) {
		return visit((VB6Node)node, data);
	}

	@Override
	public Object visit(ASTWriteStmt node, Object data) {
		return visit((VB6Node)node, data);
	}
}
