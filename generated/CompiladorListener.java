// Generated from Compilador.e4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CompiladorParser}.
 */
public interface CompiladorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CompiladorParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CompiladorParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CompiladorParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CompiladorParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Group}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterGroup(CompiladorParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Group}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitGroup(CompiladorParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Num}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNum(CompiladorParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Num}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNum(CompiladorParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Soma}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSoma(CompiladorParser.SomaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Soma}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSoma(CompiladorParser.SomaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Id}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CompiladorParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CompiladorParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Produto}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterProduto(CompiladorParser.ProdutoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Produto}
	 * labeled alternative in {@link CompiladorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitProduto(CompiladorParser.ProdutoContext ctx);
}