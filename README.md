# Automate-API-Testing
This repository contains material and code related to API test Automation

## Forcing Maven to using Java 16
```
<properties>
<maven.compiler.source>16</maven.compiler.source>
<maven.compiler.target>16</maven.compiler.target>
</properties>
```
## Adding reprting plugins
```
<build>
<plugins>
<plugin>
<!-- 
				https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin  -->
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-surefire-plugin</artifactId>
<version>3.5.1</version>
</plugin>
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-site-plugin</artifactId>
<version>4.0.0-M13</version>
</plugin>
</plugins>
</build>
<reporting>
<plugins>
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-surefire-report-plugin</artifactId>
<version>3.5.1</version>
</plugin>
</plugins>
</reporting>
```
