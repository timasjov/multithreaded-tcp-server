# Simple multithreaded TCP server

Server is implemented in `Java`, clients in `Python` and `Groovy`.

### Requirements
Running all components require that following software (*) is installed on target computers.
 - Java (1.7)
 - Maven (3.2.3)
 - Python (2.7)
 - Groovy (2.3.6)

(*) tested with mentioned versions. May not work with lower versions.

### Build manual
1) Run the server (Java)
- Go to *"server-java"* directory
- Execute command -> `mvn install exec:java -Dexec.mainClass="ee.ut.ds.TCPServer"`

2) Run first client (Python)
- Go to *"client-python"* directory
- Execute script script -> `python2.7 client.py`

3) Run second client (Groovy)
- Go to *"client-groovy"* directory
- Execute script -> `groovy client.groovy`
