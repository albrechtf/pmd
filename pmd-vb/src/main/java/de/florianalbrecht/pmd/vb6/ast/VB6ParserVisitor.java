package de.florianalbrecht.pmd.vb6.ast;

public interface VB6ParserVisitor {

	public Object visit(VB6Node node, Object data);

	public Object visit(ASTAmbiguousIdentifier node, Object data);

	public Object visit(ASTAmbiguousKeyword node, Object data);

	public Object visit(ASTAppactivateStmt node, Object data);

	public Object visit(ASTArg node, Object data);

	public Object visit(ASTArgCall node, Object data);

	public Object visit(ASTArgDefaultValue node, Object data);

	public Object visit(ASTArgList node, Object data);

	public Object visit(ASTArgsCall node, Object data);

	public Object visit(ASTAsTypeClause node, Object data);

	public Object visit(ASTAttributeStmt node, Object data);

	public Object visit(ASTBaseType node, Object data);

	public Object visit(ASTBeepStmt node, Object data);

	public Object visit(ASTBlock node, Object data);

	public Object visit(ASTBlockStmt node, Object data);

	public Object visit(ASTCertainIdentifier node, Object data);

	public Object visit(ASTChdirStmt node, Object data);

	public Object visit(ASTChdriveStmt node, Object data);

	public Object visit(ASTCloseStmt node, Object data);

	public Object visit(ASTComment node, Object data);

	public Object visit(ASTComparisonOperator node, Object data);

	public Object visit(ASTComplexType node, Object data);

	public Object visit(ASTConstStmt node, Object data);

	public Object visit(ASTConstSubStmt node, Object data);

	public Object visit(ASTDateStmt node, Object data);

	public Object visit(ASTDeclareStmt node, Object data);

	public Object visit(ASTDeftypeStmt node, Object data);

	public Object visit(ASTDeleteSettingStmt node, Object data);

	public Object visit(ASTDictionaryCallStmt node, Object data);

	public Object visit(ASTDoLoopStmt node, Object data);

	public Object visit(ASTECS_MemberProcedureCall node, Object data);

	public Object visit(ASTECS_ProcedureCall node, Object data);

	public Object visit(ASTEndStmt node, Object data);

	public Object visit(ASTEnumerationStmt_Constant node, Object data);

	public Object visit(ASTEnumerationStmt node, Object data);

	public Object visit(ASTEraseStmt node, Object data);

	public Object visit(ASTErrorStmt node, Object data);

	public Object visit(ASTEventStmt node, Object data);

	public Object visit(ASTExitStmt node, Object data);

	public Object visit(ASTExplicitCallStmt node, Object data);

	public Object visit(ASTFieldLength node, Object data);

	public Object visit(ASTFilecopyStmt node, Object data);

	public Object visit(ASTForEachStmt node, Object data);

	public Object visit(ASTForNextStmt node, Object data);

	public Object visit(ASTFunctionOrArrayCallStmt node, Object data);

	public Object visit(ASTFunctionStmt node, Object data);

	public Object visit(ASTGetStmt node, Object data);

	public Object visit(ASTGoSubStmt node, Object data);

	public Object visit(ASTGoToStmt node, Object data);

	public Object visit(ASTICS_B_FunctionCall node, Object data);

	public Object visit(ASTICS_B_MemberFunctionCall node, Object data);

	public Object visit(ASTICS_B_MemberSubCall node, Object data);

	public Object visit(ASTICS_B_SubCall node, Object data);

	public Object visit(ASTICS_S_DictionaryCall node, Object data);

	public Object visit(ASTICS_S_FunctionOrArrayCall node, Object data);

	public Object visit(ASTICS_S_MembersCall node, Object data);

	public Object visit(ASTICS_S_VariableCall node, Object data);

	public Object visit(ASTIfBlockStmt node, Object data);

	public Object visit(ASTIfConditionStmt node, Object data);

	public Object visit(ASTIfElseBlockStmt node, Object data);

	public Object visit(ASTIfElseIfBlockStmt node, Object data);

	public Object visit(ASTIfThenElseStmt node, Object data);

	public Object visit(ASTImplementsStmt node, Object data);

	public Object visit(ASTImplicitCallStmt_InBlock node, Object data);

	public Object visit(ASTImplicitCallStmt_InStmt node, Object data);

	public Object visit(ASTInputStmt node, Object data);

	public Object visit(ASTKillStmt node, Object data);

	public Object visit(ASTLetStmt node, Object data);

	public Object visit(ASTLetterrange node, Object data);

	public Object visit(ASTLineInputStmt node, Object data);

	public Object visit(ASTLineLabel node, Object data);

	public Object visit(ASTLiteral node, Object data);

	public Object visit(ASTLoadStmt node, Object data);

	public Object visit(ASTLockStmt node, Object data);

	public Object visit(ASTLsetStmt node, Object data);

	public Object visit(ASTMacroElseBlockStmt node, Object data);

	public Object visit(ASTMacroElseIfBlockStmt node, Object data);

	public Object visit(ASTMacroIfBlockStmt node, Object data);

	public Object visit(ASTMacroIfThenElseStmt node, Object data);

	public Object visit(ASTMemberCall_Value node, Object data);

	public Object visit(ASTMemberFunctionOrArrayCallStmt node, Object data);

	public Object visit(ASTMemberPropertyCallStmt node, Object data);

	public Object visit(ASTMemberSubCallStmt node, Object data);

	public Object visit(ASTMidStmt node, Object data);

	public Object visit(ASTMkdirStmt node, Object data);

	public Object visit(ASTModule node, Object data);

	public Object visit(ASTModuleAttributes node, Object data);

	public Object visit(ASTModuleBlock node, Object data);

	public Object visit(ASTModuleBody node, Object data);

	public Object visit(ASTModuleBodyElement node, Object data);

	public Object visit(ASTModuleConfig node, Object data);

	public Object visit(ASTModuleHeader node, Object data);

	public Object visit(ASTModuleOption node, Object data);

	public Object visit(ASTModuleOptions node, Object data);

	public Object visit(ASTNameStmt node, Object data);

	public Object visit(ASTOnErrorStmt node, Object data);

	public Object visit(ASTOnGoSubStmt node, Object data);

	public Object visit(ASTOnGoToStmt node, Object data);

	public Object visit(ASTOpenStmt node, Object data);

	public Object visit(ASTOutputList_Expression node, Object data);

	public Object visit(ASTOutputList node, Object data);

	public Object visit(ASTPrintStmt node, Object data);

	public Object visit(ASTPropertyGetStmt node, Object data);

	public Object visit(ASTPropertyLetStmt node, Object data);

	public Object visit(ASTPropertySetStmt node, Object data);

	public Object visit(ASTPutStmt node, Object data);

	public Object visit(ASTRaiseEventStmt node, Object data);

	public Object visit(ASTRandomizeStmt node, Object data);

	public Object visit(ASTRedimStmt node, Object data);

	public Object visit(ASTRedimSubStmt node, Object data);

	public Object visit(ASTResetStmt node, Object data);

	public Object visit(ASTResumeStmt node, Object data);

	public Object visit(ASTReturnStmt node, Object data);

	public Object visit(ASTRmdirStmt node, Object data);

	public Object visit(ASTRsetStmt node, Object data);

	public Object visit(ASTSavepictureStmt node, Object data);

	public Object visit(ASTSaveSettingStmt node, Object data);

	public Object visit(ASTSC_Case node, Object data);

	public Object visit(ASTSC_CaseElse node, Object data);

	public Object visit(ASTSC_Cond node, Object data);

	public Object visit(ASTSeekStmt node, Object data);

	public Object visit(ASTSelectCaseStmt node, Object data);

	public Object visit(ASTSendkeysStmt node, Object data);

	public Object visit(ASTSetattrStmt node, Object data);

	public Object visit(ASTSetStmt node, Object data);

	public Object visit(ASTStopStmt node, Object data);

	public Object visit(ASTSubscript node, Object data);

	public Object visit(ASTSubscripts node, Object data);

	public Object visit(ASTSubStmt node, Object data);

	public Object visit(ASTTimeStmt node, Object data);

	public Object visit(ASTType node, Object data);

	public Object visit(ASTTypeHint node, Object data);

	public Object visit(ASTTypeOfStmt node, Object data);

	public Object visit(ASTTypeStmt_Element node, Object data);

	public Object visit(ASTTypeStmt node, Object data);

	public Object visit(ASTUnloadStmt node, Object data);

	public Object visit(ASTUnlockStmt node, Object data);

	public Object visit(ASTValueStmt node, Object data);

	public Object visit(ASTVariableCallStmt node, Object data);

	public Object visit(ASTVariableListStmt node, Object data);

	public Object visit(ASTVariableStmt node, Object data);

	public Object visit(ASTVariableSubStmt node, Object data);

	public Object visit(ASTVisibility node, Object data);

	public Object visit(ASTWhileWendStmt node, Object data);

	public Object visit(ASTWidthStmt node, Object data);

	public Object visit(ASTWithStmt node, Object data);

	public Object visit(ASTWriteStmt node, Object data);

}
