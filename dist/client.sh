#!/bin/bash
java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://client.jar file://lib/' -jar client.jar