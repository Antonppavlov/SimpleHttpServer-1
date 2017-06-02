# SimpleHttpServer
It is a simple html server to test the http calls. It is written in java.

<h2>GET</h2>

```java
get("/*",((request, response) -> "Success"));
```

<h2>POST</h2>

```java
post("/*",((request, response) -> "Success"));
```

<h2>PUT</h2>

```java
put("/*",((request, response) -> "Success"));
```

<h2>pom.xml</h2>

```xml
<dependency>
  <groupId>com.sparkjava</groupId>
  <artifactId>spark-core</artifactId>
  <version>2.5.5</version>
</dependency>
```
