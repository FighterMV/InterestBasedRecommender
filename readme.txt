Da ich ihnen keinen Quellcode von meinem aktuellen Arbeitgeber schicken darf, schicke ich ihnen den aktuellen
Stand meiner Masterarbeit. Das Projekt ist noch unter Bearbeitung, ist aber lauffähig.
Es wird ein Service sein, welcher Empfehlungen für den Benutzer berechnet. Diese Empfehlungen basieren auf
den Interessen des Benutzers.

---------------
Installation
---------------

Um das Projekt zu starten sind einige Schritte notwendig:

Die beigelegten libraries im "libs" Ordner müssen mit Maven bekannt gemacht werden.Dazu öffnet man das Terminal
im libs Ordner und gibt folgende Befehle ein:

mvn install:install-file -Dfile=Jama-1.0.2.jar -DgroupId=jama -DartifactId=jama -Dversion=1.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=jaws-bin.jar -DgroupId=jaws-bin -DartifactId=jaws-bin-Dversion=0 -Dpackaging=jar

mvn install:install-file -Dfile=json-lib-2.4-jdk15.jar -DgroupId=net.sf.json-lib -DartifactId=json-lib -Dversion=2.4 -Dpackaging=jar

Anschließend kann in der "hibernate.properties" Datei im Ordner "src/main/resources" die eigene Datenbankkonfiguration eingeben

Dann kann man das Projekt mit Maven bauen und die entstehende war Datei beispielsweise im Tomcat laufen lassen.



----------------
Beispieleingabe
----------------
Username: testuser
Interests: fishing,soccer
Weightings: 10,11 
Items: www.fishing.com,www.bundesliga.de
Links: http://www.fishing.com,http://www.bundesliga.de
ItemKeywords: fishing;bundesliga

Dieses Nutzerinterface dient zur manuellen Eingabe von Requests - später wird das Projekt von anderen Komponenten benutzt
Es werden nur Weightings über 10 berücksichtigt
Je mehr Eingaben gemacht werden desto bessere Ergebnisse erzielt das Programm.