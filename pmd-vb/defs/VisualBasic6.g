/*
* Copyright (C) 2014 Ulrich Wolffgang <u.wol@wwu.de>
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

/*
* Visual Basic 6.0 Grammar for ANTLR4, Version 1.0 
*
* This is an approximate grammar for Visual Basic 6.0, derived 
* from the Visual Basic 6.0 language reference 
* http://msdn.microsoft.com/en-us/library/aa338033%28v=vs.60%29.aspx 
* and tested against MSDN VB6 statement examples as well as several Visual 
* Basic 6.0 code repositories.
*
* Characteristics:
*
* 1. This grammar is line-based and takes into account whitespace, so that
*    member calls (e.g. "A.B") are distinguished from contextual object calls 
*    in WITH statements (e.g. "A .B").
*
* 2. Keywords can be used as identifiers depending on the context, enabling
*    e.g. "A.Type", but not "Type.B".
*
*
* Known limitations:
*
* 1. Preprocessor statements (#if, #else, ...) must not interfere with regular
*    statements.
*
*
* Change log:
*
* v1.0 Initial revision
* v1.1 Added support for comments (not very elegant grammar, please improve if you can)
*/

grammar VisualBasic6;

options
{
	language = Java;
}


// module ----------------------------------

startRule : module EOF;

module : 
	WS? (comment NEWLINE | comment | NEWLINE)*
	(moduleHeader (comment NEWLINE | comment | NEWLINE)+)?
	moduleConfig? (comment NEWLINE | comment | NEWLINE)*
	moduleAttributes? (comment NEWLINE | comment | NEWLINE)*
	moduleOptions? (comment NEWLINE | comment | NEWLINE)*
	moduleBody? (comment NEWLINE | comment | NEWLINE)*
	WS?
;

comment: COMMENT;

moduleHeader : VERSION WS DOUBLELITERAL WS CLASS;

moduleConfig :
	BEGIN (comment NEWLINE | comment | NEWLINE)+ 
	(ambiguousIdentifier WS? EQ WS? literal (comment NEWLINE | comment | NEWLINE))+ 
	END (comment NEWLINE | comment | NEWLINE)+
;

moduleAttributes : (attributeStmt (comment NEWLINE | comment | NEWLINE)+)+;

moduleOptions : (moduleOption (comment NEWLINE | comment | NEWLINE)+)+;

moduleOption : 
	OPTION_BASE WS INTEGERLITERAL # optionBaseStmt
	| OPTION_COMPARE WS (BINARY | TEXT) # optionCompareStmt
	| OPTION_EXPLICIT # optionExplicitStmt
	| OPTION_PRIVATE_MODULE # optionPrivateModuleStmt
;

moduleBody : 
	moduleBodyElement ((comment NEWLINE | comment | NEWLINE)+ moduleBodyElement)*
;

moduleBodyElement : 
	moduleBlock
	| declareStmt
	| enumerationStmt 
	| eventStmt
	| functionStmt 
	| macroIfThenElseStmt
	| propertyGetStmt 
	| propertySetStmt 
	| propertyLetStmt 
	| subStmt 
	| typeStmt
;


// block ----------------------------------

moduleBlock : block;

attributeStmt : ATTRIBUTE WS implicitCallStmt_InStmt WS? EQ WS? literal (WS? ',' WS? literal)*;

block : blockStmt ((comment NEWLINE | comment | NEWLINE)+ WS? blockStmt)*;

blockStmt : 
	appactivateStmt
	| attributeStmt
	| beepStmt
	| chdirStmt
	| chdriveStmt
	| closeStmt
	| constStmt
	| dateStmt
	| deleteSettingStmt
	| deftypeStmt
	| doLoopStmt
	| endStmt
	| eraseStmt
	| errorStmt
	| exitStmt
	| explicitCallStmt
	| filecopyStmt
	| forEachStmt
	| forNextStmt
	| getStmt
	| goSubStmt
	| goToStmt
	| ifThenElseStmt
	| implementsStmt
	| implicitCallStmt_InBlock
	| inputStmt
	| killStmt
	| letStmt
	| lineInputStmt
	| lineLabel
	| loadStmt
	| lockStmt
	| lsetStmt
	| macroIfThenElseStmt
	| midStmt
	| mkdirStmt
	| nameStmt
	| onErrorStmt
	| onGoToStmt
	| onGoSubStmt
	| openStmt
	| printStmt
	| putStmt
	| raiseEventStmt
	| randomizeStmt
	| redimStmt
	| resetStmt
	| resumeStmt
	| returnStmt
	| rmdirStmt
	| rsetStmt
	| savepictureStmt
	| saveSettingStmt
	| seekStmt
	| selectCaseStmt
	| sendkeysStmt
	| setattrStmt
	| setStmt
	| stopStmt
	| timeStmt
	| unloadStmt
	| unlockStmt
	| variableStmt
	| whileWendStmt
	| widthStmt
	| withStmt
	| writeStmt
;

// statements ----------------------------------

appactivateStmt : APPACTIVATE WS valueStmt (WS? ',' WS? valueStmt)?;

beepStmt : BEEP;

chdirStmt : CHDIR WS valueStmt;

chdriveStmt : CHDRIVE WS valueStmt;

closeStmt : CLOSE (WS valueStmt (WS? ',' WS? valueStmt)*)?;

constStmt : (visibility WS)? CONST WS constSubStmt (WS? ',' WS? constSubStmt)*;

constSubStmt : ambiguousIdentifier typeHint? (WS asTypeClause)? WS? EQ WS? valueStmt;

dateStmt : DATE WS? EQ WS? valueStmt;

declareStmt : (visibility WS)? DECLARE WS (FUNCTION | SUB) WS ambiguousIdentifier WS LIB WS STRINGLITERAL (WS ALIAS WS STRINGLITERAL)? (WS? argList)? (WS asTypeClause)?;

deftypeStmt : 
	(
		DEFBOOL | DEFBYTE | DEFINT | DEFLNG | DEFCUR | 
		DEFSNG | DEFDBL | DEFDEC | DEFDATE | 
		DEFSTR | DEFOBJ | DEFVAR
	) WS
	letterrange (WS? ',' WS? letterrange)*
;

deleteSettingStmt : DELETESETTING WS valueStmt WS? ',' WS? valueStmt (WS? ',' WS? valueStmt)?;

doLoopStmt :
	DO (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)? 
	LOOP
	|
	DO WS (WHILE | UNTIL) WS valueStmt (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)? 
	LOOP
	| 
	DO (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+) 
	LOOP WS (WHILE | UNTIL) WS valueStmt
;

endStmt : END;

enumerationStmt: 
	(visibility WS)? ENUM WS ambiguousIdentifier (comment NEWLINE | comment | NEWLINE)+ 
	(enumerationStmt_Constant)* 
	END_ENUM
;

enumerationStmt_Constant : ambiguousIdentifier (WS? EQ WS? valueStmt)? (comment NEWLINE | comment | NEWLINE)+;

eraseStmt : ERASE WS valueStmt;

errorStmt : ERROR WS valueStmt;

eventStmt : (visibility WS)? EVENT WS ambiguousIdentifier WS? argList;

exitStmt : EXIT_DO | EXIT_FOR | EXIT_FUNCTION | EXIT_PROPERTY | EXIT_SUB;

filecopyStmt : FILECOPY WS valueStmt WS? ',' WS? valueStmt;

forEachStmt : 
	FOR WS EACH WS ambiguousIdentifier typeHint? WS IN WS valueStmt (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)?
	NEXT (WS ambiguousIdentifier)?
;

forNextStmt : 
	FOR WS ambiguousIdentifier typeHint? (WS asTypeClause)? WS? EQ WS? valueStmt WS TO WS valueStmt (WS STEP WS valueStmt)? (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)? 
	NEXT (WS ambiguousIdentifier)?
; 

functionStmt :
	(visibility WS)? (STATIC WS)? FUNCTION WS ambiguousIdentifier (WS? argList)? (WS asTypeClause)? (comment NEWLINE | comment | NEWLINE)+
	(block (comment NEWLINE | comment | NEWLINE)+)?
	END_FUNCTION
;

getStmt : GET WS valueStmt WS? ',' WS? valueStmt? WS? ',' WS? valueStmt;

goSubStmt : GOSUB WS valueStmt;

goToStmt : GOTO WS valueStmt;

ifThenElseStmt : 
	IF WS ifConditionStmt WS THEN WS blockStmt (WS ELSE WS blockStmt)? # inlineIfThenElse
	| ifBlockStmt ifElseIfBlockStmt* ifElseBlockStmt? END_IF # blockIfThenElse
;

ifBlockStmt : 
	IF WS ifConditionStmt WS THEN (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)?
;

ifConditionStmt : valueStmt;

ifElseIfBlockStmt : 
	ELSEIF WS ifConditionStmt WS THEN (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)?
;

ifElseBlockStmt : 
	ELSE (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)?
;

implementsStmt : IMPLEMENTS WS ambiguousIdentifier;

inputStmt : INPUT WS valueStmt (WS? ',' WS? valueStmt)+;

killStmt : KILL WS valueStmt;

letStmt : (LET WS)? implicitCallStmt_InStmt WS? (EQ | PLUS_EQ | MINUS_EQ) WS? valueStmt;

lineInputStmt : LINE_INPUT WS valueStmt WS? ',' WS? valueStmt;

loadStmt : LOAD WS valueStmt;

lockStmt : LOCK WS valueStmt (WS? ',' WS? valueStmt (WS TO WS valueStmt)?)?;

lsetStmt : LSET WS implicitCallStmt_InStmt WS? EQ WS? valueStmt;

macroIfThenElseStmt : macroIfBlockStmt macroElseIfBlockStmt* macroElseBlockStmt? MACRO_END_IF;

macroIfBlockStmt : 
	MACRO_IF WS ifConditionStmt WS THEN (comment NEWLINE | comment | NEWLINE)+ 
	(moduleBody (comment NEWLINE | comment | NEWLINE)+)?
;

macroElseIfBlockStmt : 
	MACRO_ELSEIF WS ifConditionStmt WS THEN (comment NEWLINE | comment | NEWLINE)+ 
	(moduleBody (comment NEWLINE | comment | NEWLINE)+)?
;

macroElseBlockStmt : 
	MACRO_ELSE (comment NEWLINE | comment | NEWLINE)+ 
	(moduleBody (comment NEWLINE | comment | NEWLINE)+)?
;

midStmt : MID WS? LPAREN WS? argsCall WS? RPAREN;

mkdirStmt : MKDIR WS valueStmt;

nameStmt : NAME WS valueStmt WS AS WS valueStmt;

onErrorStmt : ON_ERROR WS (GOTO WS valueStmt | RESUME WS NEXT);

onGoToStmt : ON WS valueStmt WS GOTO WS valueStmt (WS? ',' WS? valueStmt)*;

onGoSubStmt : ON WS valueStmt WS GOSUB WS valueStmt (WS? ',' WS? valueStmt)*;

openStmt : 
	OPEN WS valueStmt WS FOR WS (APPEND | BINARY | INPUT | OUTPUT | RANDOM) 
	(WS ACCESS WS (READ | WRITE | READ_WRITE))?
	(WS (SHARED | LOCK_READ | LOCK_WRITE | LOCK_READ_WRITE))?
	WS AS WS valueStmt
	(WS LEN WS? EQ WS? valueStmt)?
;

outputList :
	outputList_Expression (WS? (';' | ',') WS? outputList_Expression?)*
	| outputList_Expression? (WS? (';' | ',') WS? outputList_Expression?)+
;

outputList_Expression : 
	valueStmt
	| (SPC | TAB) (WS? LPAREN WS? argsCall WS? RPAREN)?
;

printStmt : PRINT WS valueStmt WS? ',' (WS? outputList)?;

propertyGetStmt : 
	(visibility WS)? (STATIC WS)? PROPERTY_GET WS ambiguousIdentifier (WS? argList)? (WS asTypeClause)? (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)? 
	END_PROPERTY
;

propertySetStmt : 
	(visibility WS)? (STATIC WS)? PROPERTY_SET WS ambiguousIdentifier (WS? argList)? (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)? 
	END_PROPERTY
;

propertyLetStmt : 
	(visibility WS)? (STATIC WS)? PROPERTY_LET WS ambiguousIdentifier (WS? argList)? (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)? 
	END_PROPERTY
;

putStmt : PUT WS valueStmt WS? ',' WS? valueStmt? WS? ',' WS? valueStmt;

raiseEventStmt : RAISEEVENT WS ambiguousIdentifier (WS? LPAREN WS? (argsCall WS?)? RPAREN)?;

randomizeStmt : RANDOMIZE (WS valueStmt)?;

redimStmt : REDIM WS (PRESERVE WS)? redimSubStmt (WS?',' WS? redimSubStmt)*;

redimSubStmt : implicitCallStmt_InStmt WS? LPAREN WS? subscripts WS? RPAREN (WS asTypeClause)?;

resetStmt : RESET;

resumeStmt : RESUME (WS (NEXT | ambiguousIdentifier))?;

returnStmt : RETURN;

rmdirStmt : RMDIR WS valueStmt;

rsetStmt : RSET WS implicitCallStmt_InStmt WS? EQ WS? valueStmt;

savepictureStmt : SAVEPICTURE WS valueStmt WS? ',' WS? valueStmt;

saveSettingStmt : SAVESETTING WS valueStmt WS? ',' WS? valueStmt WS? ',' WS? valueStmt WS? ',' WS? valueStmt;

seekStmt : SEEK WS valueStmt WS? ',' WS? valueStmt;

selectCaseStmt : 
	SELECT WS CASE WS valueStmt (comment NEWLINE | comment | NEWLINE)+ 
	sC_Case* 
	sC_CaseElse?
	WS? END_SELECT
;

sC_Case : 
	CASE WS sC_Cond WS? (':'? (comment NEWLINE | comment | NEWLINE)* | (comment NEWLINE | comment | NEWLINE)+)  
	(block (comment NEWLINE | comment | NEWLINE)+)?
;

sC_Cond : 
	IS WS? comparisonOperator WS? valueStmt # caseCondIs
	| valueStmt (WS? ',' WS? valueStmt)* # caseCondValue
	| INTEGERLITERAL WS TO WS valueStmt (WS? ',' WS? valueStmt)* # caseCondTo
;

sC_CaseElse : 
	CASE WS ELSE WS? (':'? (comment NEWLINE | comment | NEWLINE)* | (comment NEWLINE | comment | NEWLINE)+)  
	(block (comment NEWLINE | comment | NEWLINE)+)?
;

sendkeysStmt : SENDKEYS WS valueStmt (WS? ',' WS? valueStmt)?;

setattrStmt : SETATTR WS valueStmt WS? ',' WS? valueStmt;

setStmt : SET WS implicitCallStmt_InStmt WS? EQ WS? valueStmt;

stopStmt : STOP;

subStmt : 
	(visibility WS)? (STATIC WS)? SUB WS ambiguousIdentifier (WS? argList)? (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)? 
	END_SUB
;

timeStmt : TIME WS? EQ WS? valueStmt;

typeStmt : 
	(visibility WS)? TYPE WS ambiguousIdentifier (comment NEWLINE | comment | NEWLINE)+ 
	(typeStmt_Element)*
	END_TYPE
;

typeStmt_Element : ambiguousIdentifier (WS? LPAREN (WS? subscripts)? WS? RPAREN)? (WS asTypeClause)? (comment NEWLINE | comment | NEWLINE)+;

typeOfStmt : TYPEOF WS valueStmt (WS IS WS type)?;

unloadStmt : UNLOAD WS valueStmt;

unlockStmt : UNLOCK WS valueStmt (WS? ',' WS? valueStmt (WS TO WS valueStmt)?)?;

valueStmt : 
	literal # vsLiteral
	| midStmt # vsMid
	| NEW WS valueStmt # vsNew
	| implicitCallStmt_InStmt # vsValueCalls
	| typeOfStmt # vsTypeOf
	| LPAREN WS? valueStmt (WS? ',' WS? valueStmt)* RPAREN # vsStruct
	| implicitCallStmt_InStmt WS? ASSIGN WS? valueStmt # vsAssign
	| valueStmt WS? PLUS WS? valueStmt # vsAdd
	| PLUS WS? valueStmt # vsPlus
	| ADDRESSOF WS valueStmt # vsAddressOf
	| valueStmt WS AMPERSAND WS valueStmt # vsAmp
	| valueStmt WS AND WS valueStmt # vsAnd
	| valueStmt WS? LT WS? valueStmt # vsLt
	| valueStmt WS? LEQ WS? valueStmt # vsLeq
	| valueStmt WS? GT WS? valueStmt # vsGt
	| valueStmt WS? GEQ WS? valueStmt # vsGeq
	| valueStmt WS? EQ WS? valueStmt # vsEq
	| valueStmt WS? NEQ WS? valueStmt # vsNeq
	| valueStmt WS? DIV WS? valueStmt # vsDiv
	| valueStmt WS EQV WS valueStmt # vsEqv
	| valueStmt WS IMP WS valueStmt # vsImp
	| valueStmt WS IS WS valueStmt # vsIs
	| valueStmt WS LIKE WS valueStmt # vsLike
	| valueStmt WS? MINUS WS? valueStmt # vsMinus
	| MINUS WS? valueStmt # vsNegation
	| valueStmt WS? MOD WS? valueStmt # vsMod
	| valueStmt WS? MULT WS? valueStmt # vsMult
	| NOT WS valueStmt # vsNot
	| valueStmt WS? OR WS? valueStmt # vsOr
	| valueStmt WS? POW WS? valueStmt # vsPow
	| valueStmt WS? XOR WS? valueStmt # vsXor
;

variableStmt : (DIM | STATIC | visibility) WS (WITHEVENTS WS)? variableListStmt;

variableListStmt : variableSubStmt (WS? ',' WS? variableSubStmt)*;

variableSubStmt : ambiguousIdentifier (WS? LPAREN WS? (subscripts WS?)? RPAREN WS?)? typeHint? (WS asTypeClause)?;

whileWendStmt : 
	WHILE WS valueStmt (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE))* 
	WEND
;

widthStmt : WIDTH WS valueStmt WS? ',' WS? valueStmt;

withStmt : 
	WITH WS implicitCallStmt_InStmt (comment NEWLINE | comment | NEWLINE)+ 
	(block (comment NEWLINE | comment | NEWLINE)+)? 
	END_WITH
;

writeStmt : WRITE WS valueStmt WS? ',' (WS? outputList)?;


// complex call statements ----------------------------------

explicitCallStmt : 
	eCS_ProcedureCall 
	| eCS_MemberProcedureCall 
;

eCS_ProcedureCall : CALL WS ambiguousIdentifier typeHint? (WS? LPAREN WS? (argsCall WS?)? RPAREN)?;

eCS_MemberProcedureCall : CALL WS variableCallStmt? memberPropertyCallStmt* '.' ambiguousIdentifier typeHint? (WS? LPAREN WS? (argsCall WS?)? RPAREN)?;


implicitCallStmt_InBlock :
	iCS_B_SubCall
	| iCS_B_FunctionCall
	| iCS_B_MemberSubCall
	| iCS_B_MemberFunctionCall
;

// certainIdentifier instead of ambiguousIdentifier for preventing ambiguity with statement keywords 
iCS_B_SubCall : certainIdentifier (WS argsCall)?;

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

memberPropertyCallStmt : '.' ambiguousIdentifier;

memberFunctionOrArrayCallStmt : '.' functionOrArrayCallStmt;

memberSubCallStmt : '.' ambiguousIdentifier (WS argsCall)?;

memberCall_Value : memberPropertyCallStmt | memberFunctionOrArrayCallStmt;


// atomic call statements ----------------------------------

variableCallStmt : ambiguousIdentifier typeHint?;

dictionaryCallStmt : '!' ambiguousIdentifier typeHint?;

functionOrArrayCallStmt : (ambiguousIdentifier | baseType) typeHint? WS? LPAREN WS? (argsCall WS?)? RPAREN;


argsCall : (argCall? WS? (',' | ';') WS?)* argCall (WS? (',' | ';') WS? argCall?)*;

argCall : ((BYVAL | BYREF | PARAMARRAY) WS)? valueStmt;


// atomic rules for statements

argList : LPAREN (WS? arg (WS? ',' WS? arg)*)? WS? RPAREN;

arg : (OPTIONAL WS)? ((BYVAL | BYREF) WS)? (PARAMARRAY WS)? ambiguousIdentifier (WS? LPAREN WS? RPAREN)? (WS asTypeClause)? (WS? argDefaultValue)?;

argDefaultValue : EQ WS? (literal | ambiguousIdentifier);

subscripts : subscript (WS? ',' WS? subscript)*;

subscript : (valueStmt WS TO WS)? valueStmt;


// atomic rules ----------------------------------

ambiguousIdentifier : 
	(IDENTIFIER | ambiguousKeyword)+
	| L_SQUARE_BRACKET (IDENTIFIER | ambiguousKeyword)+ R_SQUARE_BRACKET
;

asTypeClause : AS WS (NEW WS)? type (WS fieldLength)?;

baseType : BOOLEAN | BYTE | COLLECTION | DATE | DOUBLE | INTEGER | LONG | SINGLE | STRING | VARIANT;

certainIdentifier : 
	IDENTIFIER (ambiguousKeyword | IDENTIFIER)*
	| ambiguousKeyword (ambiguousKeyword | IDENTIFIER)+
;

comparisonOperator : LT | LEQ | GT | GEQ | EQ | NEQ | IS | LIKE;

complexType : ambiguousIdentifier ('.' ambiguousIdentifier)*;

fieldLength : MULT WS? (INTEGERLITERAL | ambiguousIdentifier);

letterrange : certainIdentifier (WS? MINUS WS? certainIdentifier)?;

lineLabel : ambiguousIdentifier ':';

literal : COLORLITERAL | DATELITERAL | DOUBLELITERAL | FILENUMBER | INTEGERLITERAL | STRINGLITERAL | TRUE | FALSE | NOTHING | NULL;

type : (baseType | complexType) (WS? LPAREN WS? RPAREN)?;

typeHint : '&' | '%' | '#' | '!' | '@' | '$';

visibility : PRIVATE | PUBLIC | FRIEND | GLOBAL;


// ambiguous keywords
ambiguousKeyword : 
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


// lexer rules --------------------------------------------------------------------------------


// keywords
ACCESS : A C C E S S;
ADDRESSOF : A D D R E S S O F;
ALIAS : A L I A S;
AND : A N D;
ATTRIBUTE : A T T R I B U T E;
APPACTIVATE : A P P A C T I V A T E;
APPEND : A P P E N D;
AS : A S;
BEGIN : B E G I N;
BEEP : B E E P;
BINARY : B I N A R Y;
BOOLEAN : B O O L E A N;
BYVAL : B Y V A L;
BYREF : B Y R E F;
BYTE : B Y T E;
CALL : C A L L;
CASE : C A S E;
CHDIR : C H D I R;
CHDRIVE : C H D R I V E;
CLASS : C L A S S;
CLOSE : C L O S E;
COLLECTION : C O L L E C T I O N;
CONST : C O N S T;
DATE : D A T E;
DECLARE : D E C L A R E;
DEFBOOL : D E F B O O L; 
DEFBYTE : D E F B Y T E;
DEFDATE : D E F D A T E;
DEFDBL : D E F D B L;
DEFDEC : D E F D E C;
DEFCUR : D E F C U R;
DEFINT : D E F I N T;
DEFLNG : D E F L N G;
DEFOBJ : D E F O B J;
DEFSNG : D E F S N G;
DEFSTR : D E F S T R;
DEFVAR : D E F V A R;
DELETESETTING : D E L E T E S E T T I N G;
DIM : D I M;
DO : D O;
DOUBLE : D O U B L E;
EACH : E A C H;
ELSE : E L S E;
ELSEIF : E L S E I F;
END_ENUM : E N D ' ' E N U M;
END_FUNCTION : E N D ' ' F U N C T I O N;
END_IF : E N D ' ' I F;
END_PROPERTY : E N D ' ' P R O P E R T Y;
END_SELECT : E N D ' ' S E L E C T;
END_SUB : E N D ' ' S U B;
END_TYPE : E N D ' ' T Y P E;
END_WITH : E N D ' ' W I T H;
END : E N D;
ENUM : E N U M;
EQV : E Q V;
ERASE : E R A S E;
ERROR : E R R O R;
EVENT : E V E N T;
EXIT_DO : E X I T ' ' D O;
EXIT_FOR : E X I T ' ' F O R;
EXIT_FUNCTION : E X I T ' ' F U N C T I O N;
EXIT_PROPERTY : E X I T ' ' P R O P E R T Y;
EXIT_SUB : E X I T ' ' S U B;
FALSE : F A L S E;
FILECOPY : F I L E C O P Y;
FRIEND : F R I E N D;
FOR : F O R;
FUNCTION : F U N C T I O N;
GET : G E T;
GLOBAL : G L O B A L;
GOSUB : G O S U B;
GOTO : G O T O;
IF : I F;
IMP : I M P;
IMPLEMENTS : I M P L E M E N T S;
IN : I N;
INPUT : I N P U T;
IS : I S;
INTEGER : I N T E G E R;
KILL: K I L L;
LOAD : L O A D;
LOCK : L O C K;
LONG : L O N G;
LOOP : L O O P;
LEN : L E N;
LET : L E T;
LIB : L I B;
LIKE : L I K E;
LINE_INPUT : L I N E ' ' I N P U T;
LOCK_READ : L O C K ' ' R E A D;
LOCK_WRITE : L O C K ' ' W R I T E;
LOCK_READ_WRITE : L O C K ' ' R E A D ' ' W R I T E;
LSET : L S E T;
MACRO_IF : '#' I F;
MACRO_ELSEIF : '#' E L S E I F;
MACRO_ELSE : '#' E L S E;
MACRO_END_IF : '#' E N D ' ' I F;
ME : M E;
MID : M I D;
MKDIR : M K D I R;
MOD : M O D;
NAME : N A M E;
NEXT : N E X T;
NEW : N E W;
NOT : N O T;
NOTHING : N O T H I N G;
NULL : N U L L;
ON : O N;
ON_ERROR : O N ' ' E R R O R;
OPEN : O P E N;
OPTIONAL : O P T I O N A L;
OPTION_BASE : O P T I O N ' ' B A S E;
OPTION_EXPLICIT : O P T I O N ' ' E X P L I C I T;
OPTION_COMPARE : O P T I O N ' ' C O M P A R E;
OPTION_PRIVATE_MODULE : O P T I O N ' ' P R I V A T E ' ' M O D U L E;
OR : O R;
OUTPUT : O U T P U T;
PARAMARRAY : P A R A M A R R A Y;
PRESERVE : P R E S E R V E;
PRINT : P R I N T;
PRIVATE : P R I V A T E;
PROPERTY_GET : P R O P E R T Y ' ' G E T;
PROPERTY_LET : P R O P E R T Y ' ' L E T;
PROPERTY_SET : P R O P E R T Y ' ' S E T;
PUBLIC : P U B L I C;
PUT : P U T;
RANDOM : R A N D O M;
RANDOMIZE : R A N D O M I Z E;
RAISEEVENT : R A I S E E V E N T;
READ : R E A D;
READ_WRITE : R E A D ' ' W R I T E;
REDIM : R E D I M;
REM : R E M;
RESET : R E S E T;
RESUME : R E S U M E;
RETURN : R E T U R N;
RMDIR : R M D I R;
RSET : R S E T;
SAVEPICTURE : S A V E P I C T U R E;
SAVESETTING : S A V E S E T T I N G;
SEEK : S E E K;
SELECT : S E L E C T;
SENDKEYS : S E N D K E Y S;
SET : S E T;
SETATTR : S E T A T T R;
SHARED : S H A R E D;
SINGLE : S I N G L E;
SPC : S P C;
STATIC : S T A T I C;
STEP : S T E P;
STOP : S T O P;
STRING : S T R I N G;
SUB : S U B;
TAB : T A B;
TEXT : T E X T;
THEN : T H E N;
TIME : T I M E;
TO : T O;
TRUE : T R U E;
TYPE : T Y P E;
TYPEOF : T Y P E O F;
UNLOAD : U N L O A D;
UNLOCK : U N L O C K;
UNTIL : U N T I L;
VARIANT : V A R I A N T;
VERSION : V E R S I O N;
WEND : W E N D;
WHILE : W H I L E;
WIDTH : W I D T H;
WITH : W I T H;
WITHEVENTS : W I T H E V E N T S;
WRITE : W R I T E;
XOR : X O R;


// symbols
AMPERSAND : '&';
ASSIGN : ':=';
DIV : '\\' | '/';
EQ : '=';
GEQ : '>=';
GT : '>';
LEQ : '<=';
LPAREN : '(';
LT : '<';
MINUS : '-';
MINUS_EQ : '-=';
MULT : '*';
NEQ : '<>';
PLUS : '+';
PLUS_EQ : '+=';
POW : '^';
RPAREN : ')';
L_SQUARE_BRACKET : '[';
R_SQUARE_BRACKET : ']';


// literals
STRINGLITERAL : '"' (~["\r\n] | '""')* '"';
DATELITERAL : '#' (~[#\r\n])* '#';
COLORLITERAL : '&H' [0-9A-F]+ '&'?;
INTEGERLITERAL : (PLUS|MINUS)? ('0'..'9')+ ( ('e' | 'E') INTEGERLITERAL)* ('#' | '&')?;
DOUBLELITERAL : (PLUS|MINUS)? ('0'..'9')* '.' ('0'..'9')+ ( ('e' | 'E') (PLUS|MINUS)? ('0'..'9')+)* ('#' | '&')?;
FILENUMBER : '#' LETTERORDIGIT+;


// identifier
IDENTIFIER : LETTER (LETTERORDIGIT)*;


// whitespace, line breaks, comments...
LINE_CONTINUATION : ' ' '_' '\r'? '\n' -> skip;
COMMENT : WS? ('\'' | ':'? REM ' ') (LINE_CONTINUATION | ~('\n' | '\r'))*;
NEWLINE : WS? ('\r'? '\n' | ':' ' ') WS?;

WS : [ \t]+;


// letters
fragment LETTER : [a-zA-Z_];
fragment LETTERORDIGIT : [a-zA-Z0-9_];

// case insensitive chars
fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');