package com.lovi.searchbox.annotation.process;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.lovi.searchbox.annotation.QueryController;
import com.lovi.searchbox.annotation.QueryFunction;
import com.lovi.searchbox.query.QueryContainer;
import com.lovi.searchbox.query.QueryHolder;
import com.lovi.searchbox.service.SearchBoxOperations;

public class QueryAnnotationProcessor {
	
	final Logger logger = LoggerFactory.getLogger(QueryAnnotationProcessor.class);
	
	private ApplicationContext applicationContext;
	
	public QueryAnnotationProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public void start() throws Exception{
		
		SearchBoxOperations searchBoxOperations = applicationContext.getBean(SearchBoxOperations.class);
		QueryContainer queryContainer = applicationContext.getBean(QueryContainer.class);
		
		for(Object annotatedClass : applicationContext.getBeansWithAnnotation(QueryController.class).values()){
		
			Class<?> queryControllerAnnotatedClass = annotatedClass.getClass();
			
			final Object queryControllerObject = applicationContext.getBean(queryControllerAnnotatedClass);
			
			//process methods
			for(Method method : queryControllerAnnotatedClass.getDeclaredMethods()) {
				
				QueryFunction queryFunctionAnnotation = method.getAnnotation(QueryFunction.class);
				
				if(queryFunctionAnnotation != null){
					
					try{
						QueryHolder queryHolder = (QueryHolder) method.invoke(queryControllerObject);
						
						String queryName = queryFunctionAnnotation.value();
						
						queryContainer.putQuery(queryName, queryHolder);
						searchBoxOperations.saveScript(queryName, queryHolder.getQuery().getQueryString());
						
					}catch(ClassCastException e){
						logger.error("query {} function not return QueryHolder object",queryFunctionAnnotation.value());
					}catch(Exception e){
						logger.error(e.getMessage());
					}
					
				}
			}
		}
	}
}
