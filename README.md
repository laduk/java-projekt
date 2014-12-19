Spuštění
============================

Build můžete udělat v adresáři <b>CreatureHunting</b>:

        mvn clean install

Naplnění databáze defaultními daty je možné provést v adresáři <b>CreatureHunting\creaturehunting-data</b> pomocí příkazu:

        mvn sql:execute

Webová aplikace se spustí v adresáři <b>CreatureHunting\creaturehunting-web</b> pomocí příkazu:

        mvn tomcat7:run

a spustí se webová aplikace na url: <b>http://localhost:8080/pa165/</b>

Restový webový client se spustí v adresáři <b>CreatureHunting\creaturehunting-restClient</b> pomocí příkazu:
       
        mvn tomcat7:run

spustí se web na url: <b>http://localhost:8084/creaturehunting-restClient/#/</b>
