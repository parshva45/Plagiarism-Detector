// Generated from Python3.g4 by ANTLR 4.4
package edu.northeastern.cs5500.strategies.implementations.ast.pythonparser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Python3Parser}.
 */
public interface Python3Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Python3Parser#testlist}.
	 * @param ctx the parse tree
	 */
	void enterTestlist(@NotNull Python3Parser.TestlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#testlist}.
	 * @param ctx the parse tree
	 */
	void exitTestlist(@NotNull Python3Parser.TestlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#assert_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssert_stmt(@NotNull Python3Parser.Assert_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#assert_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssert_stmt(@NotNull Python3Parser.Assert_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(@NotNull Python3Parser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(@NotNull Python3Parser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#not_test}.
	 * @param ctx the parse tree
	 */
	void enterNot_test(@NotNull Python3Parser.Not_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#not_test}.
	 * @param ctx the parse tree
	 */
	void exitNot_test(@NotNull Python3Parser.Not_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#file_input}.
	 * @param ctx the parse tree
	 */
	void enterFile_input(@NotNull Python3Parser.File_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#file_input}.
	 * @param ctx the parse tree
	 */
	void exitFile_input(@NotNull Python3Parser.File_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#xor_expr}.
	 * @param ctx the parse tree
	 */
	void enterXor_expr(@NotNull Python3Parser.Xor_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#xor_expr}.
	 * @param ctx the parse tree
	 */
	void exitXor_expr(@NotNull Python3Parser.Xor_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(@NotNull Python3Parser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(@NotNull Python3Parser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_from}.
	 * @param ctx the parse tree
	 */
	void enterImport_from(@NotNull Python3Parser.Import_fromContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_from}.
	 * @param ctx the parse tree
	 */
	void exitImport_from(@NotNull Python3Parser.Import_fromContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#single_input}.
	 * @param ctx the parse tree
	 */
	void enterSingle_input(@NotNull Python3Parser.Single_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#single_input}.
	 * @param ctx the parse tree
	 */
	void exitSingle_input(@NotNull Python3Parser.Single_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#decorated}.
	 * @param ctx the parse tree
	 */
	void enterDecorated(@NotNull Python3Parser.DecoratedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#decorated}.
	 * @param ctx the parse tree
	 */
	void exitDecorated(@NotNull Python3Parser.DecoratedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#with_item}.
	 * @param ctx the parse tree
	 */
	void enterWith_item(@NotNull Python3Parser.With_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#with_item}.
	 * @param ctx the parse tree
	 */
	void exitWith_item(@NotNull Python3Parser.With_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#raise_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRaise_stmt(@NotNull Python3Parser.Raise_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#raise_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRaise_stmt(@NotNull Python3Parser.Raise_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void enterImport_as_name(@NotNull Python3Parser.Import_as_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void exitImport_as_name(@NotNull Python3Parser.Import_as_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#except_clause}.
	 * @param ctx the parse tree
	 */
	void enterExcept_clause(@NotNull Python3Parser.Except_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#except_clause}.
	 * @param ctx the parse tree
	 */
	void exitExcept_clause(@NotNull Python3Parser.Except_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCompound_stmt(@NotNull Python3Parser.Compound_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCompound_stmt(@NotNull Python3Parser.Compound_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull Python3Parser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull Python3Parser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#and_expr}.
	 * @param ctx the parse tree
	 */
	void enterAnd_expr(@NotNull Python3Parser.And_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#and_expr}.
	 * @param ctx the parse tree
	 */
	void exitAnd_expr(@NotNull Python3Parser.And_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#lambdef_nocond}.
	 * @param ctx the parse tree
	 */
	void enterLambdef_nocond(@NotNull Python3Parser.Lambdef_nocondContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#lambdef_nocond}.
	 * @param ctx the parse tree
	 */
	void exitLambdef_nocond(@NotNull Python3Parser.Lambdef_nocondContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#dictorsetmaker}.
	 * @param ctx the parse tree
	 */
	void enterDictorsetmaker(@NotNull Python3Parser.DictorsetmakerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#dictorsetmaker}.
	 * @param ctx the parse tree
	 */
	void exitDictorsetmaker(@NotNull Python3Parser.DictorsetmakerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(@NotNull Python3Parser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(@NotNull Python3Parser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#dotted_name}.
	 * @param ctx the parse tree
	 */
	void enterDotted_name(@NotNull Python3Parser.Dotted_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#dotted_name}.
	 * @param ctx the parse tree
	 */
	void exitDotted_name(@NotNull Python3Parser.Dotted_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFlow_stmt(@NotNull Python3Parser.Flow_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#flow_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFlow_stmt(@NotNull Python3Parser.Flow_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(@NotNull Python3Parser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(@NotNull Python3Parser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#or_test}.
	 * @param ctx the parse tree
	 */
	void enterOr_test(@NotNull Python3Parser.Or_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#or_test}.
	 * @param ctx the parse tree
	 */
	void exitOr_test(@NotNull Python3Parser.Or_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(@NotNull Python3Parser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(@NotNull Python3Parser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(@NotNull Python3Parser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(@NotNull Python3Parser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#subscript}.
	 * @param ctx the parse tree
	 */
	void enterSubscript(@NotNull Python3Parser.SubscriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#subscript}.
	 * @param ctx the parse tree
	 */
	void exitSubscript(@NotNull Python3Parser.SubscriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comp_for}.
	 * @param ctx the parse tree
	 */
	void enterComp_for(@NotNull Python3Parser.Comp_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comp_for}.
	 * @param ctx the parse tree
	 */
	void exitComp_for(@NotNull Python3Parser.Comp_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#yield_arg}.
	 * @param ctx the parse tree
	 */
	void enterYield_arg(@NotNull Python3Parser.Yield_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#yield_arg}.
	 * @param ctx the parse tree
	 */
	void exitYield_arg(@NotNull Python3Parser.Yield_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#yield_expr}.
	 * @param ctx the parse tree
	 */
	void enterYield_expr(@NotNull Python3Parser.Yield_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#yield_expr}.
	 * @param ctx the parse tree
	 */
	void exitYield_expr(@NotNull Python3Parser.Yield_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void enterImport_stmt(@NotNull Python3Parser.Import_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void exitImport_stmt(@NotNull Python3Parser.Import_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#shift_expr}.
	 * @param ctx the parse tree
	 */
	void enterShift_expr(@NotNull Python3Parser.Shift_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#shift_expr}.
	 * @param ctx the parse tree
	 */
	void exitShift_expr(@NotNull Python3Parser.Shift_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#lambdef}.
	 * @param ctx the parse tree
	 */
	void enterLambdef(@NotNull Python3Parser.LambdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#lambdef}.
	 * @param ctx the parse tree
	 */
	void exitLambdef(@NotNull Python3Parser.LambdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#and_test}.
	 * @param ctx the parse tree
	 */
	void enterAnd_test(@NotNull Python3Parser.And_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#and_test}.
	 * @param ctx the parse tree
	 */
	void exitAnd_test(@NotNull Python3Parser.And_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void enterImport_as_names(@NotNull Python3Parser.Import_as_namesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void exitImport_as_names(@NotNull Python3Parser.Import_as_namesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#decorators}.
	 * @param ctx the parse tree
	 */
	void enterDecorators(@NotNull Python3Parser.DecoratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#decorators}.
	 * @param ctx the parse tree
	 */
	void exitDecorators(@NotNull Python3Parser.DecoratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#try_stmt}.
	 * @param ctx the parse tree
	 */
	void enterTry_stmt(@NotNull Python3Parser.Try_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#try_stmt}.
	 * @param ctx the parse tree
	 */
	void exitTry_stmt(@NotNull Python3Parser.Try_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterComp_op(@NotNull Python3Parser.Comp_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitComp_op(@NotNull Python3Parser.Comp_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#star_expr}.
	 * @param ctx the parse tree
	 */
	void enterStar_expr(@NotNull Python3Parser.Star_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#star_expr}.
	 * @param ctx the parse tree
	 */
	void exitStar_expr(@NotNull Python3Parser.Star_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#break_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#break_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(@NotNull Python3Parser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(@NotNull Python3Parser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#decorator}.
	 * @param ctx the parse tree
	 */
	void enterDecorator(@NotNull Python3Parser.DecoratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#decorator}.
	 * @param ctx the parse tree
	 */
	void exitDecorator(@NotNull Python3Parser.DecoratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#tfpdef}.
	 * @param ctx the parse tree
	 */
	void enterTfpdef(@NotNull Python3Parser.TfpdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#tfpdef}.
	 * @param ctx the parse tree
	 */
	void exitTfpdef(@NotNull Python3Parser.TfpdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull Python3Parser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull Python3Parser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void enterTestlist_comp(@NotNull Python3Parser.Testlist_compContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void exitTestlist_comp(@NotNull Python3Parser.Testlist_compContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(@NotNull Python3Parser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(@NotNull Python3Parser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#with_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWith_stmt(@NotNull Python3Parser.With_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#with_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWith_stmt(@NotNull Python3Parser.With_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#classdef}.
	 * @param ctx the parse tree
	 */
	void enterClassdef(@NotNull Python3Parser.ClassdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#classdef}.
	 * @param ctx the parse tree
	 */
	void exitClassdef(@NotNull Python3Parser.ClassdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#exprlist}.
	 * @param ctx the parse tree
	 */
	void enterExprlist(@NotNull Python3Parser.ExprlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#exprlist}.
	 * @param ctx the parse tree
	 */
	void exitExprlist(@NotNull Python3Parser.ExprlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSmall_stmt(@NotNull Python3Parser.Small_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSmall_stmt(@NotNull Python3Parser.Small_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#trailer}.
	 * @param ctx the parse tree
	 */
	void enterTrailer(@NotNull Python3Parser.TrailerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#trailer}.
	 * @param ctx the parse tree
	 */
	void exitTrailer(@NotNull Python3Parser.TrailerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#dotted_as_names}.
	 * @param ctx the parse tree
	 */
	void enterDotted_as_names(@NotNull Python3Parser.Dotted_as_namesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#dotted_as_names}.
	 * @param ctx the parse tree
	 */
	void exitDotted_as_names(@NotNull Python3Parser.Dotted_as_namesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#arith_expr}.
	 * @param ctx the parse tree
	 */
	void enterArith_expr(@NotNull Python3Parser.Arith_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#arith_expr}.
	 * @param ctx the parse tree
	 */
	void exitArith_expr(@NotNull Python3Parser.Arith_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#arglist}.
	 * @param ctx the parse tree
	 */
	void enterArglist(@NotNull Python3Parser.ArglistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#arglist}.
	 * @param ctx the parse tree
	 */
	void exitArglist(@NotNull Python3Parser.ArglistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt(@NotNull Python3Parser.Simple_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt(@NotNull Python3Parser.Simple_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#typedargslist}.
	 * @param ctx the parse tree
	 */
	void enterTypedargslist(@NotNull Python3Parser.TypedargslistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#typedargslist}.
	 * @param ctx the parse tree
	 */
	void exitTypedargslist(@NotNull Python3Parser.TypedargslistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull Python3Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull Python3Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull Python3Parser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull Python3Parser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#power}.
	 * @param ctx the parse tree
	 */
	void enterPower(@NotNull Python3Parser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#power}.
	 * @param ctx the parse tree
	 */
	void exitPower(@NotNull Python3Parser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#dotted_as_name}.
	 * @param ctx the parse tree
	 */
	void enterDotted_as_name(@NotNull Python3Parser.Dotted_as_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#dotted_as_name}.
	 * @param ctx the parse tree
	 */
	void exitDotted_as_name(@NotNull Python3Parser.Dotted_as_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull Python3Parser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull Python3Parser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#sliceop}.
	 * @param ctx the parse tree
	 */
	void enterSliceop(@NotNull Python3Parser.SliceopContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#sliceop}.
	 * @param ctx the parse tree
	 */
	void exitSliceop(@NotNull Python3Parser.SliceopContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#funcdef}.
	 * @param ctx the parse tree
	 */
	void enterFuncdef(@NotNull Python3Parser.FuncdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#funcdef}.
	 * @param ctx the parse tree
	 */
	void exitFuncdef(@NotNull Python3Parser.FuncdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#subscriptlist}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptlist(@NotNull Python3Parser.SubscriptlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#subscriptlist}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptlist(@NotNull Python3Parser.SubscriptlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#test_nocond}.
	 * @param ctx the parse tree
	 */
	void enterTest_nocond(@NotNull Python3Parser.Test_nocondContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#test_nocond}.
	 * @param ctx the parse tree
	 */
	void exitTest_nocond(@NotNull Python3Parser.Test_nocondContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comp_iter}.
	 * @param ctx the parse tree
	 */
	void enterComp_iter(@NotNull Python3Parser.Comp_iterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comp_iter}.
	 * @param ctx the parse tree
	 */
	void exitComp_iter(@NotNull Python3Parser.Comp_iterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#nonlocal_stmt}.
	 * @param ctx the parse tree
	 */
	void enterNonlocal_stmt(@NotNull Python3Parser.Nonlocal_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#nonlocal_stmt}.
	 * @param ctx the parse tree
	 */
	void exitNonlocal_stmt(@NotNull Python3Parser.Nonlocal_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#eval_input}.
	 * @param ctx the parse tree
	 */
	void enterEval_input(@NotNull Python3Parser.Eval_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#eval_input}.
	 * @param ctx the parse tree
	 */
	void exitEval_input(@NotNull Python3Parser.Eval_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#vfpdef}.
	 * @param ctx the parse tree
	 */
	void enterVfpdef(@NotNull Python3Parser.VfpdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#vfpdef}.
	 * @param ctx the parse tree
	 */
	void exitVfpdef(@NotNull Python3Parser.VfpdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#import_name}.
	 * @param ctx the parse tree
	 */
	void enterImport_name(@NotNull Python3Parser.Import_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#import_name}.
	 * @param ctx the parse tree
	 */
	void exitImport_name(@NotNull Python3Parser.Import_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#comp_if}.
	 * @param ctx the parse tree
	 */
	void enterComp_if(@NotNull Python3Parser.Comp_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#comp_if}.
	 * @param ctx the parse tree
	 */
	void exitComp_if(@NotNull Python3Parser.Comp_ifContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#augassign}.
	 * @param ctx the parse tree
	 */
	void enterAugassign(@NotNull Python3Parser.AugassignContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#augassign}.
	 * @param ctx the parse tree
	 */
	void exitAugassign(@NotNull Python3Parser.AugassignContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#pass_stmt}.
	 * @param ctx the parse tree
	 */
	void enterPass_stmt(@NotNull Python3Parser.Pass_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#pass_stmt}.
	 * @param ctx the parse tree
	 */
	void exitPass_stmt(@NotNull Python3Parser.Pass_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExpr_stmt(@NotNull Python3Parser.Expr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExpr_stmt(@NotNull Python3Parser.Expr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#yield_stmt}.
	 * @param ctx the parse tree
	 */
	void enterYield_stmt(@NotNull Python3Parser.Yield_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#yield_stmt}.
	 * @param ctx the parse tree
	 */
	void exitYield_stmt(@NotNull Python3Parser.Yield_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(@NotNull Python3Parser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(@NotNull Python3Parser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#continue_stmt}.
	 * @param ctx the parse tree
	 */
	void enterContinue_stmt(@NotNull Python3Parser.Continue_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#continue_stmt}.
	 * @param ctx the parse tree
	 */
	void exitContinue_stmt(@NotNull Python3Parser.Continue_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#testlist_star_expr}.
	 * @param ctx the parse tree
	 */
	void enterTestlist_star_expr(@NotNull Python3Parser.Testlist_star_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#testlist_star_expr}.
	 * @param ctx the parse tree
	 */
	void exitTestlist_star_expr(@NotNull Python3Parser.Testlist_star_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#varargslist}.
	 * @param ctx the parse tree
	 */
	void enterVarargslist(@NotNull Python3Parser.VarargslistContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#varargslist}.
	 * @param ctx the parse tree
	 */
	void exitVarargslist(@NotNull Python3Parser.VarargslistContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFor_stmt(@NotNull Python3Parser.For_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFor_stmt(@NotNull Python3Parser.For_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#del_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDel_stmt(@NotNull Python3Parser.Del_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#del_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDel_stmt(@NotNull Python3Parser.Del_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull Python3Parser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull Python3Parser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull Python3Parser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull Python3Parser.StmtContext ctx);
}