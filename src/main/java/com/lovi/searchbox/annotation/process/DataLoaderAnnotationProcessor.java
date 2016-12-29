package com.lovi.searchbox.annotation.process;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.lovi.searchbox.annotation.DataLoader;
import com.lovi.searchbox.annotation.DataLoaderFunction;
import com.lovi.searchbox.dataloader.DataLoadHandler;
import com.lovi.searchbox.dataloader.DataLoaderContainer;
import com.lovi.searchbox.exception.DataLoadingException;
public class DataLoaderAnnotationProcessor {
	
	final Logger logger = LoggerFactory.getLogger(DataLoaderAnnotationProcessor.class);
	
	private ApplicationContext applicationContext;
	
	public DataLoaderAnnotationProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public void start(DataLoaderContainer dataLoaderContainer) throws Exception{
		
		for(Object annotatedClass : applicationContext.getBeansWithAnnotation(DataLoader.class).values()){
		
			Class<?> dataLoaderAnnotatedClass = annotatedClass.getClass();
			
			final Object dataLoaderObject = applicationContext.getBean(dataLoaderAnnotatedClass);
			
			//process methods
			for(Method method : dataLoaderAnnotatedClass.getDeclaredMethods()) {
				
				Annotation dataLoaderFunctionAnnotation = method.getAnnotation(DataLoaderFunction.class);
				
				if(dataLoaderFunctionAnnotation != null){
					
					dataLoaderContainer.addHandler(new DataLoadHandler<Object>() {

						@SuppressWarnings("unchecked")
						@Override
						public Collection<Object> handle() throws DataLoadingException {
							
							try {
								return (Collection<Object>) method.invoke(dataLoaderObject);
							} catch (Exception e) {
								throw new DataLoadingException(e);
							} 
						}
						
					});
					
					
				}
			}
		}
	}
}
