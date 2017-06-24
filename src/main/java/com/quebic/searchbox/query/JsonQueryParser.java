package com.quebic.searchbox.query;

import java.util.Iterator;
import java.util.Map.Entry;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonQueryParser {
	
	private final String _and = "$and";
	private final String _or = "$or";
	
	private final String _neq = "$neq";
	private final String _gt = "$gt";
	private final String _gte = "$gte";
	private final String _lt = "$lt";
	private final String _lte = "$lte";
	private final String _prefix = "$prefix";
	private final String _prefix_all = "$prefix_all";
	private final String _pattern = "$pattern";
	
	public Criteria process(JsonNode node){
		
		Criteria criteria = null;
		
		if(node.isObject()){
			
			Iterator<Entry<String, JsonNode>> nodeFields = node.fields();
			while(nodeFields.hasNext()){
				
				Entry<String, JsonNode> nodeEntry = nodeFields.next();
				
				String fieldKey = nodeEntry.getKey();
				JsonNode fieldValue = nodeEntry.getValue();
				
				if(fieldKey.equals(_or) || fieldKey.equals(_and)){
					
					Criteria otherCriteria = process(fieldValue);
					
					if(criteria == null)
						criteria = otherCriteria;
					else
						criteria = criteria.and(otherCriteria);
						
				}else if(fieldKey.equals(_gt)){
					
					Iterator<Entry<String, JsonNode>> innerFields = fieldValue.fields();
					while(innerFields.hasNext()){
						
						Entry<String, JsonNode> innerField = innerFields.next();
						
						String k = innerField.getKey();
						JsonNode v = innerField.getValue();
						
						Object val = getFieldValue(v);
						
						if(criteria == null)
							criteria = Criteria.where(k).gt(val);
						else
							criteria = criteria.and(k).gt(val);
						
					}
					
				}else if(fieldKey.equals(_gte)){
					
					Iterator<Entry<String, JsonNode>> innerFields = fieldValue.fields();
					while(innerFields.hasNext()){
						
						Entry<String, JsonNode> innerField = innerFields.next();
						
						String k = innerField.getKey();
						JsonNode v = innerField.getValue();
						
						Object val = getFieldValue(v);
						
						if(criteria == null)
							criteria = Criteria.where(k).gte(val);
						else
							criteria = criteria.and(k).gte(val);
						
					}
					
				}else if(fieldKey.equals(_lt)){
					
					Iterator<Entry<String, JsonNode>> innerFields = fieldValue.fields();
					while(innerFields.hasNext()){
						
						Entry<String, JsonNode> innerField = innerFields.next();
						
						String k = innerField.getKey();
						JsonNode v = innerField.getValue();
						
						Object val = getFieldValue(v);
						
						if(criteria == null)
							criteria = Criteria.where(k).lt(val);
						else
							criteria = criteria.and(k).lt(val);
						
					}
					
				}else if(fieldKey.equals(_lte)){
					
					Iterator<Entry<String, JsonNode>> innerFields = fieldValue.fields();
					while(innerFields.hasNext()){
						
						Entry<String, JsonNode> innerField = innerFields.next();
						
						String k = innerField.getKey();
						JsonNode v = innerField.getValue();
						
						Object val = getFieldValue(v);
						
						if(criteria == null)
							criteria = Criteria.where(k).lte(val);
						else
							criteria = criteria.and(k).lte(val);
						
					}
					
				}else if(fieldKey.equals(_prefix)){
					
					Iterator<Entry<String, JsonNode>> innerFields = fieldValue.fields();
					while(innerFields.hasNext()){
						
						Entry<String, JsonNode> innerField = innerFields.next();
						
						String k = innerField.getKey();
						JsonNode v = innerField.getValue();
						
						Object val = getFieldValue(v);
						
						if(criteria == null)
							criteria = Criteria.where(k).searchByPrefix(val.toString());
						else
							criteria = criteria.and(k).searchByPrefix(val.toString());
						
					}
					
				}else if(fieldKey.equals(_prefix_all)){
					
					Iterator<Entry<String, JsonNode>> innerFields = fieldValue.fields();
					while(innerFields.hasNext()){
						
						Entry<String, JsonNode> innerField = innerFields.next();
						
						String k = innerField.getKey();
						JsonNode v = innerField.getValue();
						
						Object val = getFieldValue(v);
						
						if(criteria == null)
							criteria = Criteria.where(k).searchByPrefix(val.toString(), true);
						else
							criteria = criteria.and(k).searchByPrefix(val.toString(), true);
						
					}
					
				}else if(fieldKey.equals(_pattern)){
					
					Iterator<Entry<String, JsonNode>> innerFields = fieldValue.fields();
					while(innerFields.hasNext()){
						
						Entry<String, JsonNode> innerField = innerFields.next();
						
						String k = innerField.getKey();
						JsonNode v = innerField.getValue();
						
						Object val = getFieldValue(v);
						
						if(criteria == null)
							criteria = Criteria.where(k).searchByPattern(val.toString());
						else
							criteria = criteria.and(k).searchByPattern(val.toString());
						
					}
					
				}else if(fieldKey.equals(_neq)){
					
					Iterator<Entry<String, JsonNode>> innerFields = fieldValue.fields();
					while(innerFields.hasNext()){
						
						Entry<String, JsonNode> innerField = innerFields.next();
						
						String k = innerField.getKey();
						JsonNode v = innerField.getValue();
						
						Object val = getFieldValue(v);
						
						if(criteria == null)
							criteria = Criteria.where(k).ne(val);
						else
							criteria = criteria.and(k).ne(val);
						
					}
					
				}else{
					
					Object val = getFieldValue(fieldValue);
						
					if(criteria == null)
						criteria = Criteria.where(fieldKey).is(val);
					else
						criteria = criteria.and(fieldKey).is(val);
					
				}
				
			}
			
		}else if(node.isArray()){
			Iterator<JsonNode> nodeElements = node.elements();
			while(nodeElements.hasNext()){
				
				JsonNode nodeElement = nodeElements.next();
				
				Iterator<Entry<String, JsonNode>> nodeFields = nodeElement.fields();
				while(nodeFields.hasNext()){
					
					Entry<String, JsonNode> nodeEntry = nodeFields.next();
					
					String fieldKey = nodeEntry.getKey();
					JsonNode fieldValue = nodeEntry.getValue();
					
					if(fieldKey.equals(_and)){
						
						Criteria otherCriteria = process(fieldValue);
						
						if(criteria == null)
							criteria = otherCriteria;
						else
							criteria = criteria.or(otherCriteria);
						
					}else if(fieldKey.equals(_gt)){
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).gt(val);
						else
							criteria = criteria.or(fieldKey).gt(val);
						
					}else if(fieldKey.equals(_gte)){
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).gte(val);
						else
							criteria = criteria.or(fieldKey).gte(val);
						
					}else if(fieldKey.equals(_lt)){
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).lt(val);
						else
							criteria = criteria.or(fieldKey).lt(val);
						
					}else if(fieldKey.equals(_lte)){
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).lte(val);
						else
							criteria = criteria.or(fieldKey).lte(val);
						
					}else if(fieldKey.equals(_prefix)){
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).searchByPrefix(val.toString());
						else
							criteria = criteria.or(fieldKey).searchByPrefix(val.toString());
						
					}else if(fieldKey.equals(_prefix_all)){
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).searchByPrefix(val.toString(), true);
						else
							criteria = criteria.or(fieldKey).searchByPrefix(val.toString(), true);
						
					}else if(fieldKey.equals(_pattern)){
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).searchByPattern(val.toString());
						else
							criteria = criteria.or(fieldKey).searchByPattern(val.toString());
						
					}else if(fieldKey.equals(_neq)){
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).ne(val);
						else
							criteria = criteria.or(fieldKey).ne(val);
						
					}else{
						
						Object val = getFieldValue(fieldValue);
							
						if(criteria == null)
							criteria = Criteria.where(fieldKey).is(val);
						else
							criteria = criteria.or(fieldKey).is(val);
						
					}
				}
				
			}
			
		}
		
		return criteria;
		
	}
	
	private Object getFieldValue(JsonNode fieldValue){
		Object val;
		
		if(fieldValue.isTextual())
			val = fieldValue.asText();
		else if(fieldValue.isInt())
			val = fieldValue.asInt();
		else if(fieldValue.isDouble())
			val = fieldValue.asDouble();
		else if(fieldValue.isBoolean())
			val = fieldValue.asBoolean();
		else
			val = null;
		
		return val;
	}

}
