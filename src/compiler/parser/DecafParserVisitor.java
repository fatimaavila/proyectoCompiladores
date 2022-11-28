/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
/**
 *
 * @author yanet
 */
public interface DecafParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code asignacion}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(@NotNull DecafParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#keywords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywords(@NotNull DecafParser.KeywordsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literales}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiterales(@NotNull DecafParser.LiteralesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code method_dec}
	 * labeled alternative in {@link DecafParser#method_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_dec(@NotNull DecafParser.Method_decContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statements}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(@NotNull DecafParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operacion}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperacion(@NotNull DecafParser.OperacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#method_call_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_call_error(@NotNull DecafParser.Method_call_errorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code error}
	 * labeled alternative in {@link DecafParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError(@NotNull DecafParser.ErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull DecafParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(@NotNull DecafParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code method_c}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_c(@NotNull DecafParser.Method_cContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#statement_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_error(@NotNull DecafParser.Statement_errorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code field_dec}
	 * labeled alternative in {@link DecafParser#field_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_dec(@NotNull DecafParser.Field_decContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mc_error}
	 * labeled alternative in {@link DecafParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMc_error(@NotNull DecafParser.Mc_errorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#method_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_name(@NotNull DecafParser.Method_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#program_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram_error(@NotNull DecafParser.Program_errorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#while_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_error(@NotNull DecafParser.While_errorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code root}
	 * labeled alternative in {@link DecafParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(@NotNull DecafParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exp}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(@NotNull DecafParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#for_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_error(@NotNull DecafParser.For_errorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bloque}
	 * labeled alternative in {@link DecafParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(@NotNull DecafParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#if_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_error(@NotNull DecafParser.If_errorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code par}
	 * labeled alternative in {@link DecafParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPar(@NotNull DecafParser.ParContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ciclo}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCiclo(@NotNull DecafParser.CicloContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#addOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOp(@NotNull DecafParser.AddOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#callout_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout_arg(@NotNull DecafParser.Callout_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#method_decl_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_decl_error(@NotNull DecafParser.Method_decl_errorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#bin_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op(@NotNull DecafParser.Bin_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#callout_decl_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout_decl_error(@NotNull DecafParser.Callout_decl_errorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#block_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_error(@NotNull DecafParser.Block_errorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#mulOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulOp(@NotNull DecafParser.MulOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#callout_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout_decl(@NotNull DecafParser.Callout_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#field_decl_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_decl_error(@NotNull DecafParser.Field_decl_errorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifs}
	 * labeled alternative in {@link DecafParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfs(@NotNull DecafParser.IfsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DecafParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocation(@NotNull DecafParser.LocationContext ctx);
}
