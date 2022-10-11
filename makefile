JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Card.java \
		City.java \
		Deck.java \
		Disease.java \
		Main.java \
		Map.java \
		Output.java \
		Pawn.java \
		Reader.java \

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

clean:
		$(RM) *.class
