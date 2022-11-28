/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;
/**
 *
 * @author yanet
 */
public interface DecafParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code asignacion}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(@NotNull DecafParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code asignacion}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(@NotNull DecafParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#keywords}.
	 * @param ctx the parse tree
	 */
	void enterKeywords(@NotNull DecafParser.KeywordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#keywords}.
	 * @param ctx the parse tree
	 */
	void exitKeywords(@NotNull DecafParser.KeywordsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literales}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiterales(@NotNull DecafParser.LiteralesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literales}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiterales(@NotNull DecafParser.LiteralesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code method_dec}
	 * labeled alternative in {@link DecafParser#method_decl}.
	 * @param ctx the parse tree
	 */
	void enterMethod_dec(@NotNull DecafParser.Method_decContext ctx);
	/**
	 * Exit a parse tree produced by the {@code method_dec}
	 * labeled alternative in {@link DecafParser#method_decl}.
	 * @param ctx the parse tree
	 */
	void exitMethod_dec(@NotNull DecafParser.Method_decContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statements}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatements(@NotNull DecafParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statements}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatements(@NotNull DecafParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operacion}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOperacion(@NotNull DecafParser.OperacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operacion}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOperacion(@NotNull DecafParser.OperacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#method_call_error}.
	 * @param ctx the parse tree
	 */
	void enterMethod_call_error(@NotNull DecafParser.Method_call_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#method_call_error}.
	 * @param ctx the parse tree
	 */
	void exitMethod_call_error(@NotNull DecafParser.Method_call_errorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code error}
	 * labeled alternative in {@link DecafParser#start}.
	 * @param ctx the parse tree
	 */
	void enterError(@NotNull DecafParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code error}
	 * labeled alternative in {@link DecafParser#start}.
	 * @param ctx the parse tree
	 */
	void exitError(@NotNull DecafParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull DecafParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull DecafParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull DecafParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull DecafParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code method_c}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMethod_c(@NotNull DecafParser.Method_cContext ctx);
	/**
	 * Exit a parse tree produced by the {@code method_c}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMethod_c(@NotNull DecafParser.Method_cContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#statement_error}.
	 * @param ctx the parse tree
	 */
	void enterStatement_error(@NotNull DecafParser.Statement_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#statement_error}.
	 * @param ctx the parse tree
	 */
	void exitStatement_error(@NotNull DecafParser.Statement_errorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code field_dec}
	 * labeled alternative in {@link DecafParser#field_decl}.
	 * @param ctx the parse tree
	 */
	void enterField_dec(@NotNull DecafParser.Field_decContext ctx);
	/**
	 * Exit a parse tree produced by the {@code field_dec}
	 * labeled alternative in {@link DecafParser#field_decl}.
	 * @param ctx the parse tree
	 */
	void exitField_dec(@NotNull DecafParser.Field_decContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mc_error}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMc_error(@NotNull DecafParser.Mc_errorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mc_error}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMc_error(@NotNull DecafParser.Mc_errorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#method_name}.
	 * @param ctx the parse tree
	 */
	void enterMethod_name(@NotNull DecafParser.Method_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#method_name}.
	 * @param ctx the parse tree
	 */
	void exitMethod_name(@NotNull DecafParser.Method_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#program_error}.
	 * @param ctx the parse tree
	 */
	void enterProgram_error(@NotNull DecafParser.Program_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#program_error}.
	 * @param ctx the parse tree
	 */
	void exitProgram_error(@NotNull DecafParser.Program_errorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#while_error}.
	 * @param ctx the parse tree
	 */
	void enterWhile_error(@NotNull DecafParser.While_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#while_error}.
	 * @param ctx the parse tree
	 */
	void exitWhile_error(@NotNull DecafParser.While_errorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code root}
	 * labeled alternative in {@link DecafParser#start}.
	 * @param ctx the parse tree
	 */
	void enterRoot(@NotNull DecafParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by the {@code root}
	 * labeled alternative in {@link DecafParser#start}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull DecafParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exp}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExp(@NotNull DecafParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exp}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExp(@NotNull DecafParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#for_error}.
	 * @param ctx the parse tree
	 */
	void enterFor_error(@NotNull DecafParser.For_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#for_error}.
	 * @param ctx the parse tree
	 */
	void exitFor_error(@NotNull DecafParser.For_errorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bloque}
	 * labeled alternative in {@link DecafParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBloque(@NotNull DecafParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bloque}
	 * labeled alternative in {@link DecafParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBloque(@NotNull DecafParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#if_error}.
	 * @param ctx the parse tree
	 */
	void enterIf_error(@NotNull DecafParser.If_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#if_error}.
	 * @param ctx the parse tree
	 */
	void exitIf_error(@NotNull DecafParser.If_errorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code par}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPar(@NotNull DecafParser.ParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code par}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPar(@NotNull DecafParser.ParContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ciclo}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCiclo(@NotNull DecafParser.CicloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ciclo}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCiclo(@NotNull DecafParser.CicloContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#addOp}.
	 * @param ctx the parse tree
	 */
	void enterAddOp(@NotNull DecafParser.AddOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#addOp}.
	 * @param ctx the parse tree
	 */
	void exitAddOp(@NotNull DecafParser.AddOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#callout_arg}.
	 * @param ctx the parse tree
	 */
	void enterCallout_arg(@NotNull DecafParser.Callout_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#callout_arg}.
	 * @param ctx the parse tree
	 */
	void exitCallout_arg(@NotNull DecafParser.Callout_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#method_decl_error}.
	 * @param ctx the parse tree
	 */
	void enterMethod_decl_error(@NotNull DecafParser.Method_decl_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#method_decl_error}.
	 * @param ctx the parse tree
	 */
	void exitMethod_decl_error(@NotNull DecafParser.Method_decl_errorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#bin_op}.
	 * @param ctx the parse tree
	 */
	void enterBin_op(@NotNull DecafParser.Bin_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#bin_op}.
	 * @param ctx the parse tree
	 */
	void exitBin_op(@NotNull DecafParser.Bin_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#callout_decl_error}.
	 * @param ctx the parse tree
	 */
	void enterCallout_decl_error(@NotNull DecafParser.Callout_decl_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#callout_decl_error}.
	 * @param ctx the parse tree
	 */
	void exitCallout_decl_error(@NotNull DecafParser.Callout_decl_errorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#block_error}.
	 * @param ctx the parse tree
	 */
	void enterBlock_error(@NotNull DecafParser.Block_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#block_error}.
	 * @param ctx the parse tree
	 */
	void exitBlock_error(@NotNull DecafParser.Block_errorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#mulOp}.
	 * @param ctx the parse tree
	 */
	void enterMulOp(@NotNull DecafParser.MulOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#mulOp}.
	 * @param ctx the parse tree
	 */
	void exitMulOp(@NotNull DecafParser.MulOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#callout_decl}.
	 * @param ctx the parse tree
	 */
	void enterCallout_decl(@NotNull DecafParser.Callout_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#callout_decl}.
	 * @param ctx the parse tree
	 */
	void exitCallout_decl(@NotNull DecafParser.Callout_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#field_decl_error}.
	 * @param ctx the parse tree
	 */
	void enterField_decl_error(@NotNull DecafParser.Field_decl_errorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#field_decl_error}.
	 * @param ctx the parse tree
	 */
	void exitField_decl_error(@NotNull DecafParser.Field_decl_errorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifs}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfs(@NotNull DecafParser.IfsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifs}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfs(@NotNull DecafParser.IfsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecafParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(@NotNull DecafParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecafParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(@NotNull DecafParser.LocationContext ctx);
}
