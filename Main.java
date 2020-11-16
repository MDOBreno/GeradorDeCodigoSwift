import java.io.*;
import org.antlr.v4.runtime.*;
import java.util.*;
import java.util.function.*;


class Main {

  static StringBuffer dest = new StringBuffer();
  //Imprime uma string formatadas no dest
  static void out(String fmt, Object ...args) {

	  //substitui todos os argumentos to tipo Token pelo seu texto.
	  for (int c=0;c<args.length;c++)
	     if (args[c] instanceof Token)
		    args[c] = ((Token)args[c]).getText();
      dest.append(String.format(fmt,args));
  }

  public static void geraDeclaracaoVariaveis(CompiladorParser.ClasseContext cl) {
	List<CompiladorParser.VarContext> vars = cl.var();
	for (int c2=0;c2<vars.size();c2++) {
		/* Para cada declaracao de variável : TIPO NOME ; 
		 * gera:
		 * private TIPO NOME;
		 * public TIPO NOME() { return NOME;}
		 * public void NOME(TIPO arg) { NOME = arg;}
		 *
		 */

		CompiladorParser.VarContext varAtual = vars.get(c2);
		out("   private %s %s;\n", varAtual.tipo.getText(), varAtual.nome.getText());
		out("   public %s %s() {return %s;}\n", varAtual.tipo.getText(), varAtual.nome.getText(),varAtual.nome.getText());
		out("   public void %s(%s arg) {%s = arg;}\n\n", varAtual.nome.getText(), varAtual.tipo.getText(),varAtual.nome.getText());
	}
  }
  public static void geraConstrutor(CompiladorParser.ClasseContext cl) {
	/*
	 * Para classe: data class NOME(T1 N1, T2 N2);
	 * gera:
	 *    NOME(T1 _N1, T2 _N2) {
	 *	     N1 = _N1;
	 *       N2 - _N2;
	 *    }  
	 */
	List<CompiladorParser.VarContext> vars = cl.var();
	out("   %s(",cl.nome);
    for (int c2=0;c2<vars.size();c2++) {
		CompiladorParser.VarContext varAtual = vars.get(c2);		
		if (c2>0) out(" , ");
		out("%s _%s",varAtual.tipo, varAtual.nome);
	}
	out(") {\n");
	for (int c2=0;c2<vars.size();c2++) {
		CompiladorParser.VarContext varAtual = vars.get(c2);		
		out("     %s = _%s;\n",varAtual.nome, varAtual.nome);
	}
	out("   }\n\n");
  }

  public static void geraEquals(CompiladorParser.ClasseContext cl) {
	/*
	 * Para classe: data class NOME(T1 N1, T2 N2);
	 * gera:
	 *    public boolean equals(Object o) {
	 *	     if (!o instanceof NOME) return false;
	 *       NOME other = (NOME)o;
	 *       if (!other.N1().equals(N1)) return false;//se tipo Object
	 *       if (!other.N2()==N2) return false; //se tipo primitivo (inicia com minusculas)
	 *       return false;
	 *    }  
	 */
	List<CompiladorParser.VarContext> vars = cl.var();
	out("   public boolean equals(Object o) {\n");
	out("      if (!o instanceof %s) return false;\n",cl.nome);
	out("      %s other = (%s)o;\n",cl.nome,cl.nome);
	for (int c2=0;c2<vars.size();c2++) {
		CompiladorParser.VarContext varAtual = vars.get(c2);		
		String nome = varAtual.nome.getText();
		String tipo = varAtual.tipo.getText();
		//verifica se e tipo primitivo ou Objeto
		if (Character.isLowerCase(tipo.charAt(0))) {
			out("      if (!other.%s()==%s) return false;\n",nome,nome);
		} else {
			out("      if (!other.%s().equals(%s)) return false;\n",nome,nome);
		}
	}
	out("      return true;\n");
	out("   }\n\n");
  }
  
  public static void geraToString(CompiladorParser.ClasseContext cl) {
	/*
	 * Para classe: data class NOME(T1 N1, T2 N2);
	 * gera:
	 *    public String toString() {
	 *	     return "NOME("+N1+","+N2+")";
	 *    }  
	 */
	List<CompiladorParser.VarContext> vars = cl.var();
	out("   public String toString() {\n");
	out("      return \"%s(\"",cl.nome);
	for (int c2=0;c2<vars.size();c2++) {
		CompiladorParser.VarContext varAtual = vars.get(c2);		
		String tipo = varAtual.tipo.getText();
		if (c2>0) out(" + \" , \"");
   	    out(" + %s",varAtual.nome);
	}
	out(" +\")\";\n");
	out("   }\n\n");
  }
  public static void geraCodigo(CompiladorParser.ProgramaContext prog) {
	  out("/* codigo gerado automaticamente */\n\n");
	  List<CompiladorParser.ClasseContext> classes = prog.classe();
	  for (int c=0;c<classes.size();c++) {
		  CompiladorParser.ClasseContext clAtual = classes.get(c);
          out("class %s {\n", clAtual.nome.getText());
		  geraDeclaracaoVariaveis(clAtual);
		  geraConstrutor(clAtual);
		  geraEquals(clAtual);
		  geraToString(clAtual);
		  out("}\n\n");
	  }

  }
  public static void main(String args[]) throws Exception {
     //CharStream src = CharStreams.fromString("10 + 2 *3"); // Ler de uma String 
     CharStream src = CharStreams.fromFileName(args[0]); // Ler de um arquivo
     CompiladorLexer lexer = new CompiladorLexer(src);
     TokenStream tkStream = new CommonTokenStream(lexer);
     CompiladorParser parser = new CompiladorParser(tkStream);

     CompiladorParser.ProgramaContext ctx = parser.programa();
	 if (parser.getNumberOfSyntaxErrors()>0) {
		 System.out.println(parser.getNumberOfSyntaxErrors() + " Erros foram encontrados\n");
		 return;
	 }
	 System.out.println("Entrada ok");
	 geraCodigo(ctx);
	 System.out.println("Codigo gerado:\n" + dest.toString());

  }
}