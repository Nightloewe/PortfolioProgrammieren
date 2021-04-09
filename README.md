# Portfolio für Programmierung und Algorithmen und Datenstrukturen

Dieses Repository enthält den Source Code für die Portfolio Prüfung. Es wurde JavaFX zur Darstellung und Maven für die Kompilierung verwendet.


### Kompilierung

Zum einfachen Ausführen der Anwendung reicht es in dem Ordner, wo die Datei "pom.xml" liegt den folgenden Befehl auszuführen:
```shell
mvn clean javafx:run
```

Zur Kompilierung zu einem ausführbaren Programm hingegen wird der Befehl `jlink`:
```shell
mvn clean javafx:jlink
```