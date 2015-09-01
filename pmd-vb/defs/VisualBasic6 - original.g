killStmt : KILL <WS> ValueStmt();

letStmt : (LET <WS>)? implicitCallStmt_InStmt (<WS>)? (EQ | PLUS_EQ | MINUS_EQ) (<WS>)? ValueStmt();

lineInputStmt : LINE_INPUT <WS> ValueStmt() (<WS>)? "," (<WS>)? ValueStmt();

loadStmt : LOAD <WS> ValueStmt();

lockStmt : LOCK <WS> ValueStmt() ((<WS>)? "," (<WS>)? ValueStmt() (<WS> TO <WS> ValueStmt())?)?;

lsetStmt : LSET <WS> implicitCallStmt_InStmt (<WS>)? EQ (<WS>)? ValueStmt();

macroIfThenElseStmt : macroIfBlockStmt macroElseIfBlockStmt* macroElseBlockStmt? MACRO_END_IF;

macroIfBlockStmt : 
	MACRO_IF <WS> ifConditionStmt <WS> THEN (<NEWLINE>)+ 
	(moduleBody (<NEWLINE>)+)?
;

macroElseIfBlockStmt : 
	MACRO_ELSEIF <WS> ifConditionStmt <WS> THEN (<NEWLINE>)+ 
	(moduleBody (<NEWLINE>)+)?
;

macroElseBlockStmt : 
	MACRO_ELSE (<NEWLINE>)+ 
	(moduleBody (<NEWLINE>)+)?
;

midStmt : MID (<WS>)? LPAREN (<WS>)? argsCall (<WS>)? RPAREN;

mkdirStmt : MKDIR <WS> ValueStmt();

nameStmt : NAME <WS> ValueStmt() <WS> AS <WS> ValueStmt();

onErrorStmt : ON_ERROR <WS> (GOTO <WS> ValueStmt() | RESUME <WS> NEXT);

onGoToStmt : ON <WS> ValueStmt() <WS> GOTO <WS> ValueStmt() ((<WS>)? "," (<WS>)? ValueStmt())*;

onGoSubStmt : ON <WS> ValueStmt() <WS> GOSUB <WS> ValueStmt() ((<WS>)? "," (<WS>)? ValueStmt())*;

openStmt : 
	OPEN <WS> ValueStmt() <WS> FOR <WS> (APPEND | BINARY | INPUT | OUTPUT | RANDOM) 
	(<WS> ACCESS <WS> (READ | WRITE | READ_WRITE))?
	(<WS> (SHARED | LOCK_READ | LOCK_WRITE | LOCK_READ_WRITE))?
	WS AS <WS> ValueStmt()
	(<WS> LEN (<WS>)? EQ (<WS>)? ValueStmt())?
;

outputList :
	outputList_Expression ((<WS>)? (';' | ',') (<WS>)? outputList_Expression?)*
	| outputList_Expression? ((<WS>)? (';' | ',') (<WS>)? outputList_Expression?)+
;

outputList_Expression : 
	ValueStmt()
	| (SPC | TAB) ((<WS>)? LPAREN (<WS>)? argsCall (<WS>)? RPAREN)?
;

printStmt : PRINT <WS> ValueStmt() (<WS>)? "," ((<WS>)? outputList)?;

propertyGetStmt : 
	(visibility <WS>)? (STATIC <WS>)? PROPERTY_GET <WS> AmbiguousIdentifier() ((<WS>)? argList)? (<WS> asTypeClause)? (<NEWLINE>)+ 
	(block (<NEWLINE>)+)? 
	END_PROPERTY
;

propertySetStmt : 
	(visibility <WS>)? (STATIC <WS>)? PROPERTY_SET <WS> AmbiguousIdentifier() ((<WS>)? argList)? (<NEWLINE>)+ 
	(block (<NEWLINE>)+)? 
	END_PROPERTY
;

propertyLetStmt : 
	(visibility <WS>)? (STATIC <WS>)? PROPERTY_LET <WS> AmbiguousIdentifier() ((<WS>)? argList)? (<NEWLINE>)+ 
	(block (<NEWLINE>)+)? 
	END_PROPERTY
;

putStmt : PUT <WS> ValueStmt() (<WS>)? "," (<WS>)? (ValueStmt())? (<WS>)? "," (<WS>)? ValueStmt();

raiseEventStmt : RAISEEVENT <WS> AmbiguousIdentifier() ((<WS>)? LPAREN (<WS>)? (argsCall (<WS>)?)? RPAREN)?;

randomizeStmt : RANDOMIZE (<WS> ValueStmt())?;

redimStmt : REDIM <WS> (PRESERVE <WS>)? redimSubStmt ((<WS>)?',' (<WS>)? redimSubStmt)*;

redimSubStmt : implicitCallStmt_InStmt (<WS>)? LPAREN (<WS>)? subscripts (<WS>)? RPAREN (<WS> asTypeClause)?;

resetStmt : RESET;

resumeStmt : RESUME (<WS> (NEXT | AmbiguousIdentifier()))?;

returnStmt : RETURN;

rmdirStmt : RMDIR <WS> ValueStmt();

rsetStmt : RSET <WS> implicitCallStmt_InStmt (<WS>)? EQ (<WS>)? ValueStmt();

savepictureStmt : SAVEPICTURE <WS> ValueStmt() (<WS>)? "," (<WS>)? ValueStmt();

saveSettingStmt : SAVESETTING <WS> ValueStmt() (<WS>)? "," (<WS>)? ValueStmt() (<WS>)? "," (<WS>)? ValueStmt() (<WS>)? "," (<WS>)? ValueStmt();

seekStmt : SEEK <WS> ValueStmt() (<WS>)? "," (<WS>)? ValueStmt();

selectCaseStmt : 
	SELECT <WS> CASE <WS> ValueStmt() (<NEWLINE>)+ 
	sC_Case* 
	sC_CaseElse?
	(<WS>)? END_SELECT
;

sC_Case : 
	CASE <WS> sC_Cond (<WS>)? (':'? NEWLINE* | (<NEWLINE>)+)  
	(block (<NEWLINE>)+)?
;

sC_Cond : 
	IS (<WS>)? comparisonOperator (<WS>)? ValueStmt() # caseCondIs
	| ValueStmt() ((<WS>)? "," (<WS>)? ValueStmt())* # caseCondValue
	| INTEGERLITERAL <WS> TO <WS> ValueStmt() ((<WS>)? "," (<WS>)? ValueStmt())* # caseCondTo
;

sC_CaseElse : 
	CASE <WS> ELSE (<WS>)? (':'? NEWLINE* | (<NEWLINE>)+)  
	(block (<NEWLINE>)+)?
;

sendkeysStmt : SENDKEYS <WS> ValueStmt() ((<WS>)? "," (<WS>)? ValueStmt())?;

setattrStmt : SETATTR <WS> ValueStmt() (<WS>)? "," (<WS>)? ValueStmt();

setStmt : SET <WS> implicitCallStmt_InStmt (<WS>)? EQ (<WS>)? ValueStmt();

stopStmt : STOP;

subStmt : 
	(visibility <WS>)? (STATIC <WS>)? SUB <WS> AmbiguousIdentifier() ((<WS>)? argList)? (<NEWLINE>)+ 
	(block (<NEWLINE>)+)? 
	END_SUB
;

timeStmt : TIME (<WS>)? EQ (<WS>)? ValueStmt();

typeStmt : 
	(visibility <WS>)? TYPE <WS> AmbiguousIdentifier() (<NEWLINE>)+ 
	(typeStmt_Element)*
	END_TYPE
;

typeStmt_Element : AmbiguousIdentifier() ((<WS>)? LPAREN ((<WS>)? subscripts)? (<WS>)? RPAREN)? (<WS> asTypeClause)? (<NEWLINE>)+;

typeOfStmt : TYPEOF <WS> ValueStmt() (<WS> IS <WS> type)?;

unloadStmt : UNLOAD <WS> ValueStmt();

unlockStmt : UNLOCK <WS> ValueStmt() ((<WS>)? "," (<WS>)? ValueStmt() (<WS> TO <WS> ValueStmt())?)?;

ValueStmt() : 
	literal # vsLiteral
	| midStmt # vsMid
	| NEW <WS> ValueStmt() # vsNew
	| implicitCallStmt_InStmt # vsValueCalls
	| typeOfStmt # vsTypeOf
	| LPAREN (<WS>)? ValueStmt() ((<WS>)? "," (<WS>)? ValueStmt())* RPAREN # vsStruct
	| implicitCallStmt_InStmt (<WS>)? ASSIGN (<WS>)? ValueStmt() # vsAssign
	| ValueStmt() (<WS>)? PLUS (<WS>)? ValueStmt() # vsAdd
	| PLUS (<WS>)? ValueStmt() # vsPlus
	| ADDRESSOF <WS> ValueStmt() # vsAddressOf
	| ValueStmt() <WS> AMPERSAND <WS> ValueStmt() # vsAmp
	| ValueStmt() <WS> AND <WS> ValueStmt() # vsAnd
	| ValueStmt() (<WS>)? LT (<WS>)? ValueStmt() # vsLt
	| ValueStmt() (<WS>)? LEQ (<WS>)? ValueStmt() # vsLeq
	| ValueStmt() (<WS>)? GT (<WS>)? ValueStmt() # vsGt
	| ValueStmt() (<WS>)? GEQ (<WS>)? ValueStmt() # vsGeq
	| ValueStmt() (<WS>)? EQ (<WS>)? ValueStmt() # vsEq
	| ValueStmt() (<WS>)? NEQ (<WS>)? ValueStmt() # vsNeq
	| ValueStmt() (<WS>)? DIV (<WS>)? ValueStmt() # vsDiv
	| ValueStmt() <WS> EQV <WS> ValueStmt() # vsEqv
	| ValueStmt() <WS> IMP <WS> ValueStmt() # vsImp
	| ValueStmt() <WS> IS <WS> ValueStmt() # vsIs
	| ValueStmt() <WS> LIKE <WS> ValueStmt() # vsLike
	| ValueStmt() (<WS>)? MINUS (<WS>)? ValueStmt() # vsMinus
	| MINUS (<WS>)? ValueStmt() # vsNegation
	| ValueStmt() (<WS>)? MOD (<WS>)? ValueStmt() # vsMod
	| ValueStmt() (<WS>)? MULT (<WS>)? ValueStmt() # vsMult
	| NOT <WS> ValueStmt() # vsNot
	| ValueStmt() (<WS>)? OR (<WS>)? ValueStmt() # vsOr
	| ValueStmt() (<WS>)? POW (<WS>)? ValueStmt() # vsPow
	| ValueStmt() (<WS>)? XOR (<WS>)? ValueStmt() # vsXor
;

variableStmt : (DIM | STATIC | visibility) <WS> (WITHEVENTS <WS>)? variableListStmt;

variableListStmt : variableSubStmt ((<WS>)? "," (<WS>)? variableSubStmt)*;

variableSubStmt : AmbiguousIdentifier() ((<WS>)? LPAREN (<WS>)? (subscripts (<WS>)?)? RPAREN (<WS>)?)? typeHint? (<WS> asTypeClause)?;

whileWendStmt : 
	WHILE <WS> ValueStmt() (<NEWLINE>)+ 
	(block NEWLINE)* 
	WEND
;

widthStmt : WIDTH <WS> ValueStmt() (<WS>)? "," (<WS>)? ValueStmt();

withStmt : 
	WITH <WS> implicitCallStmt_InStmt (<NEWLINE>)+ 
	(block (<NEWLINE>)+)? 
	END_WITH
;

writeStmt : WRITE <WS> ValueStmt() (<WS>)? "," ((<WS>)? outputList)?;


// complex call statements ----------------------------------

explicitCallStmt : 
	eCS_ProcedureCall 
	| eCS_MemberProcedureCall 
;

eCS_ProcedureCall : CALL <WS> AmbiguousIdentifier() typeHint? ((<WS>)? LPAREN (<WS>)? (argsCall (<WS>)?)? RPAREN)?;

eCS_MemberProcedureCall : CALL <WS> variableCallStmt? memberPropertyCallStmt* '.' AmbiguousIdentifier() typeHint? ((<WS>)? LPAREN (<WS>)? (argsCall (<WS>)?)? RPAREN)?;


implicitCallStmt_InBlock :
	iCS_B_SubCall
	| iCS_B_FunctionCall
	| iCS_B_MemberSubCall
	| iCS_B_MemberFunctionCall
;

// certainIdentifier instead of AmbiguousIdentifier() for preventing ambiguity with statement keywords 
iCS_B_SubCall : certainIdentifier (<WS> argsCall)?;

iCS_B_FunctionCall : functionOrArrayCallStmt dictionaryCallStmt?;

iCS_B_MemberSubCall : implicitCallStmt_InStmt* memberSubCallStmt;

iCS_B_MemberFunctionCall : implicitCallStmt_InStmt* memberFunctionOrArrayCallStmt dictionaryCallStmt?;


implicitCallStmt_InStmt :
	iCS_S_VariableCall
	| iCS_S_FunctionOrArrayCall
	| iCS_S_DictionaryCall
	| iCS_S_MembersCall
;

iCS_S_VariableCall : variableCallStmt dictionaryCallStmt?;

iCS_S_FunctionOrArrayCall : functionOrArrayCallStmt dictionaryCallStmt?;

iCS_S_DictionaryCall : dictionaryCallStmt;

iCS_S_MembersCall : (variableCallStmt | functionOrArrayCallStmt)? memberCall_Value+ dictionaryCallStmt?;


// member call statements ----------------------------------

memberPropertyCallStmt : '.' AmbiguousIdentifier();

memberFunctionOrArrayCallStmt : '.' functionOrArrayCallStmt;

memberSubCallStmt : '.' AmbiguousIdentifier() (<WS> argsCall)?;

memberCall_Value : memberPropertyCallStmt | memberFunctionOrArrayCallStmt;


// atomic call statements ----------------------------------

variableCallStmt : AmbiguousIdentifier() typeHint?;

dictionaryCallStmt : '!' AmbiguousIdentifier() typeHint?;

functionOrArrayCallStmt : (AmbiguousIdentifier() | baseType) typeHint? (<WS>)? LPAREN (<WS>)? (argsCall (<WS>)?)? RPAREN;


argsCall : (argCall? (<WS>)? (',' | ';') (<WS>)?)* argCall ((<WS>)? (',' | ';') (<WS>)? argCall?)*;

argCall : ((BYVAL | BYREF | PARAMARRAY) <WS>)? ValueStmt();


// atomic rules for statements

argList : LPAREN ((<WS>)? arg ((<WS>)? "," (<WS>)? arg)*)? (<WS>)? RPAREN;

arg : (OPTIONAL <WS>)? ((BYVAL | BYREF) <WS>)? (PARAMARRAY <WS>)? AmbiguousIdentifier() ((<WS>)? LPAREN (<WS>)? RPAREN)? (<WS> asTypeClause)? ((<WS>)? argDefaultValue)?;

argDefaultValue : EQ (<WS>)? (literal | AmbiguousIdentifier());

subscripts : subscript ((<WS>)? "," (<WS>)? subscript)*;

subscript : (ValueStmt() <WS> TO <WS>)? ValueStmt();


// atomic rules ----------------------------------

AmbiguousIdentifier() : 
	(IDENTIFIER | AmbiguousKeyword())+
	| L_SQUARE_BRACKET (IDENTIFIER | AmbiguousKeyword())+ R_SQUARE_BRACKET
;

asTypeClause : AS <WS> (NEW <WS>)? type (<WS> fieldLength)?;

baseType : BOOLEAN | BYTE | COLLECTION | DATE | DOUBLE | INTEGER | LONG | SINGLE | STRING | VARIANT;

certainIdentifier : 
	IDENTIFIER (AmbiguousKeyword() | IDENTIFIER)*
	| AmbiguousKeyword() (AmbiguousKeyword() | IDENTIFIER)+
;

comparisonOperator : LT | LEQ | GT | GEQ | EQ | NEQ | IS | LIKE;

complexType : AmbiguousIdentifier() ('.' AmbiguousIdentifier())*;

fieldLength : MULT (<WS>)? (INTEGERLITERAL | AmbiguousIdentifier());

letterrange : certainIdentifier ((<WS>)? MINUS (<WS>)? certainIdentifier)?;

lineLabel : AmbiguousIdentifier() ':';

literal : COLORLITERAL | DATELITERAL | DOUBLELITERAL | FILENUMBER | INTEGERLITERAL | STRINGLITERAL | TRUE | FALSE | NOTHING | NULL;

type : (baseType | complexType) ((<WS>)? LPAREN (<WS>)? RPAREN)?;

typeHint : '&' | '%' | '#' | '!' | '@' | '$';

visibility : PRIVATE | PUBLIC | FRIEND | GLOBAL;


// ambiguous keywords
AmbiguousKeyword() : 
	ACCESS | ADDRESSOF | ALIAS | AND | ATTRIBUTE | APPACTIVATE | APPEND | AS |
	BEEP | BEGIN | BINARY | BOOLEAN | BYVAL | BYREF | BYTE | 
	CALL | CASE | CLASS | CLOSE | CHDIR | CHDRIVE | COLLECTION | CONST | 
	DATE | DECLARE | DEFBOOL | DEFBYTE | DEFCUR | DEFDBL | DEFDATE | DEFDEC | DEFINT | DEFLNG | DEFOBJ | DEFSNG | DEFSTR | DEFVAR | DELETESETTING | DIM | DO | DOUBLE | 
	EACH | ELSE | ELSEIF | END | ENUM | EQV | ERASE | ERROR | EVENT | 
	FALSE | FILECOPY | FRIEND | FOR | FUNCTION | 
	GET | GLOBAL | GOSUB | GOTO | 
	IF | IMP | IMPLEMENTS | IN | INPUT | IS | INTEGER |
	KILL | 
	LOAD | LOCK | LONG | LOOP | LEN | LET | LIB | LIKE | LSET |
	ME | MID | MKDIR | MOD | 
	NAME | NEXT | NEW | NOT | NOTHING | NULL | 
	ON | OPEN | OPTIONAL | OR | OUTPUT | 
	PARAMARRAY | PRESERVE | PRINT | PRIVATE | PUBLIC | PUT |
	RANDOM | RANDOMIZE | RAISEEVENT | READ | REDIM | REM | RESET | RESUME | RETURN | RMDIR | RSET |
	SAVEPICTURE | SAVESETTING | SEEK | SELECT | SENDKEYS | SET | SETATTR | SHARED | SINGLE | SPC | STATIC | STEP | STOP | STRING | SUB | 
	TAB | TEXT | THEN | TIME | TO | TRUE | TYPE | TYPEOF | 
	UNLOAD | UNLOCK | UNTIL | 
	VARIANT | VERSION | 
	WEND | WHILE | WIDTH | WITH | WITHEVENTS | WRITE |
	XOR
;



