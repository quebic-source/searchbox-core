# searchbox
searchbox is a lightweight search framework built on redis

### Prerequisities
  * JDK 1.8.X
  * Maven 3.3.X
  * Redis 2.6.X

### Getting Started
  * Add searchbox dependency.
  ```xml
  
    <dependency>
		<groupId>com.lovi.searchbox</groupId>
		<artifactId>searchbox-core</artifactId>
		<version>1.0.0-SNAPSHOT</version>
	</dependency>
  
  ```

  * Use searchbox remote repository.
  ```xml
  
    <repositories>
        <repository>
            <releases>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
                <checksumPolicy>fail</checksumPolicy>
            </releases>
            <id>searchbox</id>
            <name>searchbox</name>
            <url>http://searchbox.quebic.io/repo/</url>
            <layout>default</layout>
        </repository>
    </repositories>
  ```
