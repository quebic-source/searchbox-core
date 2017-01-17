package com.lovi.searchbox.query;

import static com.lovi.searchbox.config.ConfigKeys.lua_input_parm_key;
import static com.lovi.searchbox.config.ConfigKeys.lua_input_parm_value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;

import com.lovi.searchbox.query.impl.AndCriteria;
import com.lovi.searchbox.query.impl.FieldCriteria;
import com.lovi.searchbox.query.impl.OrCriteria;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
public abstract class Criteria {
	
	protected List<String> criteriaChain;
	protected int parmId;
	protected String key;
	protected Object value;
	protected ConditionTypes conditionType;
	
	protected enum ConditionTypes{
		EQUAL,
		NOT_EQUAL,
		PREFIX_ONE_WORD,
		PREFIX_ALL_WORDS,
		LT,
		LTE,
		GT,
		GTE,
		PATTERN
	}
	
	public abstract String getScript();
	
	public static Criteria where(String key){
		return new FieldCriteria(key);
	}
	
	public Criteria is(Object value){
		ConditionTypes ctd = ConditionTypes.EQUAL;
		preppareCriteriaValue(value, ctd);
		return this;
	}
	
	public Criteria ne(Object value){
		ConditionTypes ctd = ConditionTypes.NOT_EQUAL;
		preppareCriteriaValue(value, ctd);
		return this;
	}
	
	public Criteria gt(Object value){
		ConditionTypes ctd = ConditionTypes.GT;
		preppareCriteriaValue(value, ctd);
		return this;
	}
	
	public Criteria gte(Object value){
		ConditionTypes ctd = ConditionTypes.GTE;
		preppareCriteriaValue(value, ctd);
		return this;
	}
	
	public Criteria lt(Object value){
		ConditionTypes ctd = ConditionTypes.LT;
		preppareCriteriaValue(value, ctd);
		return this;
	}
	
	public Criteria lte(Object value){
		ConditionTypes ctd = ConditionTypes.LTE;
		preppareCriteriaValue(value, ctd);
		return this;
	}
	
	public Criteria searchByPrefix(String value){
		return searchByPrefix(value, true);
	}
	
	public Criteria searchByPrefix(String value, boolean allWords){
		
		ConditionTypes ctd;
		
		if(allWords)ctd = ConditionTypes.PREFIX_ALL_WORDS;
		else ctd = ConditionTypes.PREFIX_ONE_WORD;
		
		preppareCriteriaValue(value, ctd);
		return this;
	}
	
	public Criteria searchByPattern(String pattern){
		
		ConditionTypes ctd = ConditionTypes.PATTERN;

		preppareCriteriaValue(pattern, ctd);
		return this;
	}
	
	private void preppareCriteriaValue(Object value, ConditionTypes ctd){
		
		if(this instanceof FieldCriteria){
			
			this.conditionType = ctd;
			this.value = value;
			this.criteriaChain.add(createParmsMapToJson());
			this.parmId = this.criteriaChain.size();
			
		}else if(this instanceof AndCriteria){
			
			AndCriteria andCriteria = (AndCriteria) this;
			Criteria otherCriteria = andCriteria.getOtherCriteria();
			
			otherCriteria.setValue(value);
			otherCriteria.setConditionType(ctd);
			otherCriteria.getCriteriaChain().add(otherCriteria.createParmsMapToJson());
			otherCriteria.setParmId(otherCriteria.getCriteriaChain().size());
			
			this.criteriaChain.add("{}");
			this.parmId = criteriaChain.size();
			
		}else if(this instanceof OrCriteria){
			
			OrCriteria orCriteria = (OrCriteria) this;
			Criteria otherCriteria = orCriteria.getOtherCriteria();
			
			otherCriteria.setValue(value);
			otherCriteria.setConditionType(ctd);
			otherCriteria.getCriteriaChain().add(otherCriteria.createParmsMapToJson());
			otherCriteria.setParmId(otherCriteria.getCriteriaChain().size());
		
			this.criteriaChain.add("{}");
			this.parmId = criteriaChain.size();
			
		}
		
	}
	
	public Criteria and(String key){
		return new AndCriteria(this, new FieldCriteria(key, criteriaChain));
	}
	
	public Criteria or(String key){
		return new OrCriteria(this, new FieldCriteria(key, criteriaChain));
	}
	
	public String createParmsMapToJson(){
		
		Map<String, Object> m = new HashMap<>();
		m.put(lua_input_parm_key, key);
		m.put(lua_input_parm_value, value);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			return objectMapper.writeValueAsString(m);
		}catch(Exception e){
			return null;
		}
		
	}
	
	public String getParameterName(){
		return "parm_" + parmId;
	}

	public List<String> getCriteriaChain() {
		return criteriaChain;
	}
	
	public int getParmId() {
		return parmId;
	}

	public void setParmId(int parmId) {
		this.parmId = parmId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ConditionTypes getConditionType() {
		return conditionType;
	}

	public void setConditionType(ConditionTypes conditionType) {
		this.conditionType = conditionType;
	}
	
}
