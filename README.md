# searchbox
searchbox is a lightweight search framework built on redis.

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

### Sample application
 * Download the [movies-search-app](https://github.com/loviworld/searchbox-samples/movies-search-app) application from GitHub
 * Build the application using **mvn package**
 * Run the application using **java -jar target\movies-search-app-0.0.1-SNAPSHOT.jar**
 * Consume web app from **localhost:1028**
 
## Stand up searchbox
```java
@SpringBootApplication
@EnableSearchBox
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
}
```
 * You can easily integrate **searchbox** with your Spring application by using **@EnableSearchBox**.

### SearchBox configuration
 * Add some properties into **../src/main/resources/application.properties** or **../src/main/resources/application.yml**
 * **appname** is required property. It is used to identify individual applications.
 ```properties 
 
 searchbox.appname = movies-search-app
 
 ```
 
 * Some of the optional properties.
 ```properties 
 
 searchbox.page.length =  #Search result page size. Default value is 10
 
 server.host = #Http server host. Default value is localhost
 server.port = #Http server port. Default value is 1028
 
 redis.host = #Redis server host. Default value is localhost
 redis.port = #Redis server port. Default value is 6379
 
 ```
 
## Stand up SearchBox
