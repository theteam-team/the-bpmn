# the-bpmn

Business Process Model and Notation Application

## Runnig App

Download Java 11 [java 11 link](https://jdk.java.net/archive/)

Unzip java 11 at any desired place

Make a system Environment Variable "JAVA_HOME"

Add the path of the unzipped folder to the value of the system environment variable "JAVA_HOME"

### bpmn-nodes library

```sh
cd bpmn-nodes
./gradlew release
cd ..
```

### designer

```sh
cd designer
./gradlew run
cd ..
```

### engine

```sh
cd engine
./gradlew tomcatrun
cd ..
```
