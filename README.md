# searchbox
searchbox is a lightweight search framework built on redis.

![searchbox](https://github.com/quebic-source/searchbox-core/blob/master/searchbox_inside_redis.png)

### Prerequisities
  * JDK 1.8.X
  * Maven 3.3.X
  * Redis 2.6.X

### Full Documentation
See the [Wiki](https://github.com/quebic-source/searchbox-core/wiki) for full documentation, examples, operational details and other information.

### Related Articles
  * [Getting started - searchbox](https://medium.com/@tharanganilupul/getting-started-searchbox-60e7a9c7538f) 
  * [High performance search with searchbox + springboot](https://medium.com/@tharanganilupul/high-performance-search-with-searchbox-springboot-8c9f48a62a74) 

### Getting Started	
  * Download the dependency then Install dependency by using **mvn install**
  * Add searchbox dependency into your project
  ```xml
    <dependency>
		<groupId>com.quebic.searchbox</groupId>
		<artifactId>searchbox-core</artifactId>
		<version>1.0.0-SNAPSHOT</version>
	</dependency>
  
  ```

### Sample application
 * Download the [movies-search-app](https://github.com/quebic-source/searchbox-samples/tree/master/movies-search-app) application from GitHub
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
 * You can easily integrate **searchbox** with your Spring application by using **@EnableSearchBox** annotation.

### configuration
 * Add some properties into **../src/main/resources/application.properties** or **../src/main/resources/application.yml**
 * **appname** is a required property. It is used to identify individual applications.
 ```properties 
 
 searchbox.appname = movies-search-app
 
 ```
 
 * Some of the optional properties.
 ```properties 
 
 searchbox.page.length =  # Search result page size. Default value is 10
 
 server.host = # Http server host. Default value is localhost
 server.port = # Http server port. Default value is 1028
 
 redis.host = # Redis server host. Default value is localhost
 redis.port = # Redis server port. Default value is 6379
 
 ```
 
## Model Annotations
```java

public class Movie {
	
	@Id
	@Index
	private int id;
	
	@Index
	private String title;
	
	@Index
	private String director;

	private int duration;
	
	...

```
 * **@Id** annotation is used to mention identity field of the model. Each model has a unique id.
 * **@Index** annotation is used to mention index fields of the model. Searching is perform only for index fields.
 
## SearchBoxOperations
```java
@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private SearchBoxOperations searchBoxOperations;
	
	...
	
```
### insert,update and save
```java 
<T> void insert(T object) throws SearchBoxOperationsException
<T> void update(T object) throws SearchBoxOperationsException
<T> void save(T object) throws SearchBoxOperationsException
``` 
* **insert** Check id is exists. Not allow for duplicate ids.
* **update** Check id is exists. Then modify perticular model. Otherwise raised Id is not found exception. 
* **save** Check id is exists. If Id is exists update the model. Otherwise insert a new model.

### search by field value
```java 
<T> SearchResult<T> searchByField(Class<T> cls, String field, Object searchValue, Page page) throws SearchBoxOperationsException;
``` 
* example ```searchBoxOperations.searchByField(Movie.class, "id", id)```

### search by perfix value
```java 
<T> SearchResult<T> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix, Page page, boolean allWords) throws SearchBoxOperationsException;
``` 
* example ```searchBoxOperations.searchByFieldPerfix(Movie.class, "title", "av")``` => ["Avatar", "Avengers", ...]

### search by text pattern
```java 
<T> SearchResult<T> searchByFieldPattern(Class<T> cls, String field, String pattern, Page page) throws SearchBoxOperationsException;
``` 
* example ```searchBoxOperations.searchByFieldPattern(Movie.class, "title", "*nic")``` => ["Titanic", ...]

### Query Functions
```java 
@QueryController
public class QueryFunctions {

	@QueryFunction("query1")
	public QueryHolder query1() throws Exception{
		
		Criteria c = Criteria
				.where("@parm_key").is("@parm_value");
		
		Query query = new Query(c);
		
		return new QueryHolder(Movie.class, query);
		
	}
	...
``` 
* QueryFunctions are used PlaceHolders with '@' prefix to pass runtime values into query. Ex: @parm_key, @parm_value

* Calling QueryFunctions by using SearchBoxOperations.
```java 
<T> SearchResult<T> search(String queryName, Map<String, Object> inputParms, Page page) throws SearchBoxOperationsException;
``` 
* example 
```java
String queryName = "query1";
		
Map<String, Object> parms = new HashMap<>();
parms.put("parm_key", "title");
parms.put("parm_value", "Avatar");
	
searchBoxOperations.search("query1", parms);

```

### search with Query
```java 
<T> SearchResult<T> search(Class<T> cls, Query query, Page page) throws SearchBoxOperationsException;
``` 
* example ```searchBoxOperations.search(Movie.class, new Query(Criteria.where("id").is(7)))```

## Authors
* Tharanga Thennakoon - tharanganilupul@gmail.com 
* [Linkedin](https://lk.linkedin.com/in/tharanga-thennakoon)

## License
* This project is licensed under the Apache Licensed V2
