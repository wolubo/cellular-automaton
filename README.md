# Simulation zellulärer Automaten
Das Programm simuliert [zelluläre Automaten](https://de.wikipedia.org/wiki/Zellul%C3%A4rer_Automat) (ZA), die sich mittels einer DSL (Domain Specific Language) definieren lassen. Siehe dazu auch meinen [Blog](https://www.wolfgang-bongartz.de/blog/?id=simulator-f%C3%BCr-zellul%C3%A4re-automaten).
Die DSL beinhaltet derzeit nur einige wenige Sprachelemente. Sie lässt sich aber leicht erweitern, sodass prinzipiell auch die Definition komplexer ZA möglich
ist. Die Simulation der im Programm definierten ZA wird natürlich grafisch dargestellt.

## Starten
Die folgenden Kommandos bauen die Applikation und starten sie dann. Die Kommandos werden in einem Terminal eingegeben. 
Voraussetzung ist, dass das Build-Tool [Gradle](https://gradle.org/) und [Java](https://www.java.com/) installiert sind.
````
gradle build
java -jar build\libs\cellular-automaton.jar 
````
Mit dem Kommando 'gradle javadoc' lässt sich eine Dokumentation der Applikation generieren, die dann im Verzeichnis 'build/docs/javadoc' zu finden ist.

## Bedienung

Auf der linken Seite des Fensters befindet sich der Editor, in dem das
aktuelle Skript bearbeitet werden kann. Auf der rechten Seite befindet sich der Anzeigebereich, in dem die Simulation abläuft.
* Laden einer Definitionsdatei: Klick auf ‚Load definition...' im File-Menü.
* Speichern einer Definitionsdatei: Klick auf ‚Save definition...' im File-Menü.
* Der Button ‚Start' populiert den im Editor definierten ZA mit "Bewohnern" und startet eine Simulation.
* Der Button ‚Stop' pausiert eine laufende Simulation.
* Der Button ‚Step' erzeugt nur die nächste Generation des ZA und stoppt dann wieder.
* Der Button ‚Compile' populiert den im Editor definierten ZA mit "Bewohnern", startet die Simulation aber nicht.

## Die Skriptsprache
Hinweis: Das File 'src/javacc/Grammar.jj' enthält die Definition des Parsers.

### Beispiel-Definitionen
#### GameOfLife.cad
Definiert einen ZA nach den Regeln von [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

#### TheTimeMachine.cad
Dieser ZA basiert auf dem Roman [„The Time Machine" von H.G. Wells](https://en.wikipedia.org/wiki/The_Time_Machine). In diesem Roman existieren auf der Erde in einer fernen
Zukunft zwei Arten, die sich aus den Nachkommen des Menschen entwickelt
haben: Die Eloi und die Morlock. Die Morlock versorgen die Eloi mit
allem, was zum Leben nötig ist. Die Eloi widerum führen ein auf den
ersten Blick sorgenfreies und völlig unbeschwertes Leben. Allerdings
dienen sie den Morlock als Nahrungsquelle, werden von diesen also
letztlich als Nutztiere gehalten.

Für den ZA gelten die folgenden Regeln:
* In jeder Zelle kann ein Eloi oder ein Morlock leben. Eloi werden in blau und Morlocks werden in rot dargestellt.
* Sowohl Eloi als auch Morlock altern in jedem Durchgang. Außerdem verfügt jedes Individuum über einen gewissen Energievorrat, von dem
  in jedem Durchgang etwas verbraucht wird. Ist die Energie
  aufgebraucht stirbt das Individuum
* Lebt ein Eloi in der Umgebung mindestens eines Morlocks, so wird es
  in jedem Durchgang mit neuer Energie versorgt
* Leben in der Umgebung eines Eloi mindestens zwei weitere Eloi und
  mindestens ein Morlock, so wird nach dem Zufallsprinzip eine Zelle
  der Umgebung bestimmt. Ist diese Zelle leer, so entsteht in ihr ein
  neuer Eloi
* Leben in der Umgebung eines Morlock mindestens zwei weitere Morlocks
  und mindestens ein Eloi, so wird nach dem Zufallsprinzip eine Zelle
  der Umgebung bestimmt. Ist diese Zelle leer, so entsteht in ihr ein
  neuer Morlock
* Leben in der Umgebung eines Eloi weniger als zwei weitere Eloi, so
  stirbt es an Vereinsamung
* Leben in der Umgebung eines Eloi mehr als fünf weitere Eloi, so
  stirbt es an Platzmangel
* Leben in der Umgebung eines Morlock mehr als drei weitere Morlock,
  so stirbt es an den Streitereien mit den anderen Morlocks
* Ist ein Eloi älter als 20 so wird seine Energie an die Morlocks in
  seiner Umgebung verteilt und es stirbt
* Ist ein Morlock älter als 70, so stirbt es an Altersschwäche

### Definition eines ZA

Die Definition eines ZA beginnt mit den Schlüsselworten CELLULAR AUTOMATON
und einer öffnenden Klammer. Sie endet mit einer schließenden
Klammer. Innerhalb der Klammern werden das „Habitat", die Menge der
möglichen „Bewohner" und alle Regeln des ZA definiert. Mit dem Schlüsselwort POPULATE lässt sich angeben,
wie viele Zellen vor dem Start einer Simulation nach dem Zufallsprinzip populiert werden sollen (Angabe in Prozent).

````
cellular automaton (
    ...
    populate=30%
    ...
)
````

### Definition des Habitats
Die Menge aller Zellen eines ZA wird hier als ‚Habitat' bezeichnet. Ein
ZA hat genau ein Habitat.

````
habitat (
    width=400
    height=400
    environment=moore
)
````
* WIDTH: Breite des Habitats
* HEIGHT: Höhe des Habitats
* ENVIRONMENT: Definiert, wie viele Nachbarn eine Zelle des Habitats hat (MOORE oder NEUMANN)

### Definition der Bewohner
Die Definition einer Art von Bewohnern beginnt mit den Schlüsselworten
INHABITANT TEMPLATE. Es folgt ein Bezeichner, mit dem sich die Art in den Regeldefinitionen ansprechen läßt.

Mittels einer Key-Value-List werden der Art Attribute zugeordnet, die jedem „neugeborenen" Bewohner als Startwerte
verwendet werden. Außerdem können die Attribute in Regeln verwendet werden. Abschließend wird jeder Art eine Farbe
für die Darstellung in der Simulation zugeordnet.

````
inhabitant template Eloi (
    energy=10;
    age=0;
) is blue
````

### Definition der Regeln

Jede Regel beginnt mit dem Schlüsselwort IF. Es folgt ein boolscher
Ausdruck, das Schlüsselwort THEN und danach ein
Kommando. Es können beliebig viele Regeln definiert werden. Die Simulation arbeitet rundenbasiert. In jeder Runde
werden die Regeln auf jede einzelne Zelle des Habitats angewendet, um den Zustand der Zelle in der nächsten Runde
zu bestimmen.

````
if check(Eloi) and (age>20) then kill();
````

#### Boolsche Ausdrücke

Ein Ausdruck besteht immer aus einer binären Operation der Form
````
<operand1> <operator> <operant2>
````
oder einer unären Operation der Form
````
NOT operand
````
#### Operanden bolscher Ausdrücke
-   Die boolschen Konstanten TRUE und FALSE
-   Natürliche Zahlen (wobei 0 als FALSE und alle anderen Zahlen als TRUE interpretiert werden)
-   Funktionen (siehe unten)
-   Bezeichner von Bewohner-Attributen
-   Geklammerte boolsche Ausdrücke

#### Operatoren bolscher Ausdrücke
| Operator | Bedeutung |
|:---:| --- |
| NOT | Logische Negation |
| AND |  Logische Konjunktion |
| OR | Logische Disjunktion |
| XOR | Logische Kontravalenz |
| == | Arithmetische Gleichheit |
| \<= | Kleiner als oder gleich (arithmetisch) |
| \>= | Größer als oder gleich (arithmetisch) |
| \< | Kleiner als (arithmetisch) |
| \> | Größer als (arithmetisch) |

#### Funktionen
Funktionen prüfen Zellinhalte. Beispiel:
````
check()
````

Funktion | Parameter | Beschreibung
|:---| --- | --- |
Count | Bewohner-Art | Liefert die Anzahl der entsprechenden Bewohner in der Umgebung der Zelle zurück.
getValue | Bewohner-Attribut | Gibt den aktuellen Wert des entsprechenden Attributs zurück.
check | optional: Bewohner-Art | Prüft, ob die Zelle bewohnt ist. Wird der Name einer Bewohner-Art übergeben so wird geprüft, ob die Zelle von genau dieser Art bewohnt ist.

Komplexere Ausdrücke lassen sich durch Klammern realisieren.

### Kommandos

Kommandos ändern den Inhalt einer Zelle. Beispiel:
````
add(age, 1)
````

Kommando | Parameter | Beschreibung
|:--- | --- | --- |
KILL | | Leert eine Zelle. Tötet also einen etwaigen Bewohner.
CREATE | Name einer Bewohner-Art | Erzeugt einen neuen Bewohner in der Umgebung der Zelle. Dazu wird eine Zelle nach dem Zufallsprinzip ausgewählt. Ist sie nicht leer wird kein neuer Bewohner erzeugt.
ADD | Bewohner-Attribut, Integer | Addiert oder Substrahiert eine Zahl zu bzw. von einem Attribut,
DEPLOY | Bewohner-Attribut | Verteilt den Wert eines Attributs auf die Bewohner der Zellen in der Umgebung. Läßt sich auf eine bestimmte Bewohner-Art einschränken.
