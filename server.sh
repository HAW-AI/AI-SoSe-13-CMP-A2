#!/bin/bash
java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://target/HESServer-0.0.1-SNAPSHOT.jar' -jar target/HESServer-0.0.1-SNAPSHOT.jar $1