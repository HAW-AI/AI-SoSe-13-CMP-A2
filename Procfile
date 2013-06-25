redis: redis-server redis.conf

haproxy: haproxy -f haproxy.conf > logs/haproxy.log

client: sleep 2; java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://jars/HESClient-0.0.1-SNAPSHOT.jar' -jar jars/HESClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar

server1: sleep 20; java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://jars/HESServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar file://jars/HESServer-0.0.1-SNAPSHOT.jar' -jar jars/HESServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 1100 8091

server2: sleep 20; java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://jars/HESServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar file://jars/HESServer-0.0.1-SNAPSHOT.jar' -jar jars/HESServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 1101 8092

dummy_dienstleister: java -jar jars/HESDummyClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar
