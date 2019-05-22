# car-web-servlet
|DFAL-INF-330| Hálózati alkalmazások beadandó - Gépjármű adatbázis

Dunaújvárosi "Hálózati alkalmazások" tárgyhoz készített beadandó.

Tartalma:
* Belépés/Regisztráció
* "Gépjármű" és "Gépjármű modell" létrehozás/szerkesztés/törlés
* Kijelentkezés

Használt technológiák:
* Spring-Boot
* JSP
* Servlet
* Spring
* Spring-Security
* Spring MVC
* Nativ JDBC
* SQLite
* Maven

Hibák:
* Csak "Eclips"-ből lehet futatni rendesen vagy "jar" helyet "war"-t kell előállítani vagy a "jar"-t "explode"-olni és úgy elindítani, hogy a JSP fájlokat megtalálja.

DB:
* Ha nincs akkor első indításkor létrehozza. (nagyon fapados és nem szép)
