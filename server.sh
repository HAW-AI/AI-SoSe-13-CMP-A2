#!/bin/bash
java -Djava.security.manager -Djava.security.policy=the.policy -Djava.rmi.server.codebase='file://server.jar file://lib/' -jar server.jar $1