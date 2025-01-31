package analyzer;

// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MuParser}.
 */
public interface MuListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MuParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(MuParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(MuParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MuParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MuParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(MuParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(MuParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(MuParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(MuParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(MuParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(MuParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#exprs}.
	 * @param ctx the parse tree
	 */
	void enterExprs(MuParser.ExprsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#exprs}.
	 * @param ctx the parse tree
	 */
	void exitExprs(MuParser.ExprsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MuParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MuParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(MuParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(MuParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(MuParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(MuParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#status}.
	 * @param ctx the parse tree
	 */
	void enterStatus(MuParser.StatusContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#status}.
	 * @param ctx the parse tree
	 */
	void exitStatus(MuParser.StatusContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#commemnt}.
	 * @param ctx the parse tree
	 */
	void enterCommemnt(MuParser.CommemntContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#commemnt}.
	 * @param ctx the parse tree
	 */
	void exitCommemnt(MuParser.CommemntContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#if_stat}.
	 * @param ctx the parse tree
	 */
	void enterIf_stat(MuParser.If_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#if_stat}.
	 * @param ctx the parse tree
	 */
	void exitIf_stat(MuParser.If_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#condition_block}.
	 * @param ctx the parse tree
	 */
	void enterCondition_block(MuParser.Condition_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#condition_block}.
	 * @param ctx the parse tree
	 */
	void exitCondition_block(MuParser.Condition_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#stat_block}.
	 * @param ctx the parse tree
	 */
	void enterStat_block(MuParser.Stat_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#stat_block}.
	 * @param ctx the parse tree
	 */
	void exitStat_block(MuParser.Stat_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#while_stat}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stat(MuParser.While_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#while_stat}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stat(MuParser.While_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuParser#log}.
	 * @param ctx the parse tree
	 */
	void enterLog(MuParser.LogContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuParser#log}.
	 * @param ctx the parse tree
	 */
	void exitLog(MuParser.LogContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(MuParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(MuParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinusExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinusExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpr(MuParser.MultiplicationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpr(MuParser.MultiplicationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(MuParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(MuParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(MuParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(MuParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(MuParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(MuParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(MuParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(MuParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(MuParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(MuParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(MuParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(MuParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pointerExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPointerExpr(MuParser.PointerExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pointerExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPointerExpr(MuParser.PointerExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(MuParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link MuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(MuParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braExpr}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterBraExpr(MuParser.BraExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braExpr}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitBraExpr(MuParser.BraExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sqbraExpr}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterSqbraExpr(MuParser.SqbraExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sqbraExpr}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitSqbraExpr(MuParser.SqbraExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNumberAtom(MuParser.NumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNumberAtom(MuParser.NumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterBooleanAtom(MuParser.BooleanAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitBooleanAtom(MuParser.BooleanAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterIdAtom(MuParser.IdAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitIdAtom(MuParser.IdAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterStringAtom(MuParser.StringAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitStringAtom(MuParser.StringAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stateAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterStateAtom(MuParser.StateAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stateAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitStateAtom(MuParser.StateAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nilAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNilAtom(MuParser.NilAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nilAtom}
	 * labeled alternative in {@link MuParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNilAtom(MuParser.NilAtomContext ctx);
}