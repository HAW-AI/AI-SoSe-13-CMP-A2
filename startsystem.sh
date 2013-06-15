# reset logs
rm logs/*
touch logs/client.log
touch logs/dummy_dienstleister.log
touch logs/server1.log
touch logs/server2.log
touch logs/haproxy.log
sleep 20

#client
java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://jars/HESClient-0.0.1-SNAPSHOT.jar' -jar jars/HESClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar > logs/client.log &

# dummy_dienstleister
java -jar jars/HESDummyClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar > logs/dummy_dienstleister.log &

# server 1
java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://jars/HESServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar file://jars/HESServer-0.0.1-SNAPSHOT.jar' -jar jars/HESServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 1100 8091 > logs/server1.log &

# server 2
java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://jars/HESServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar file://jars/HESServer-0.0.1-SNAPSHOT.jar' -jar jars/HESServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 1100 8092 > logs/server2.log &

# haproxy
haproxy -f haproxy.conf > logs/haproxy.log &
