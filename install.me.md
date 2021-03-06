# ======================================== #
#   Installationsanweisungen f�r Eclipse   #
# ======================================== #
>	@AUTHOR hugo-lucca
	@DATE	29. Mai 2016

### ============ ###
###  Zu Java FX  ###
### ============ ###
- ab Java V1.6 sind alle Java-Lib's daf�r dabei
- viel bessere Darstellungsm�glichkeiten, wegen CSS Unterst�tzung
- aber nicht geeignet f�r multi-threading view-applications
- ...dennoch ben�tigt man ein Eclipse-Plugin dazu:
	Help->New SW->Work with: Mars - http://download.eclipse.org/releases/mars/201602261000
	-> general Purpose -> e(fx)clipse - IDE ->Next->Finish
- Falls mit FXML gearbeitet werden soll, statt nur mit dem FX Toolkit und CSS-Datei (swing like + CSS!)
  dann Scene Builder installieren (das Ganze um die FXML ist jedoch nicht einfach zu beherrschen)
- Neues Projekt direkt auch mit New Java FX Project (f�gt alle ben�tigten Jar's und Dateien gleich richtig dazu)

### ========== ###
###  Zu JUnit  ###
### ========== ###
- auch schon in Eclipse Mars und Java 1.8 integriert
- einfach rechts-klick auf zu testende Klasse und New->New JUnit testCase
- dann Testprozeduren wie assertEquals()-Methode hinzuprogrammieren
- um Integrationstests oder Gesamttests (wie zum Bsp. TestAll()) durchf�hren zu k�nnen, sollten Testsequenzen 
  in einer statischen Methode (siehe Bsp. myTest()) ausgelagert werden, damit man sie von TestAll() aufrufen kann.
