.PHONY: run

export CLASSPATH=antlr.jar:generated

run: generated/*.classw
	java Main input.desc

generated/*.class : *.java generated/*.java
	javac -d generated *.java generated/*.java

generated/*.java : Compilador.e4
	java -jar antlr.jar -visitor -o generated Compilador.e4

clean: 
	rm generated/*.*
