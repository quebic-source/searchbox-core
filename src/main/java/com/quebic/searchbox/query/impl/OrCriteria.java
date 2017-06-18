package com.quebic.searchbox.query.impl;

import com.quebic.searchbox.query.Criteria;

public class OrCriteria extends Criteria {

	private Criteria criteria;
	private Criteria otherCriteria;

	public OrCriteria(Criteria criteria, Criteria otherCriteria) {
		
		criteriaChain = criteria.getCriteriaChain();
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
		
	}

	@Override
	public String getScript(){
		
		String parm = getParameterName();
		
		String q1 = criteria.getScript();
		String q2 = otherCriteria.getScript();
		
		StringBuilder stmtStringBuilder = new StringBuilder();
		
		stmtStringBuilder.append(q1);
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(q2);
		stmtStringBuilder.append("\n");
		
		stmtStringBuilder.append("local %1$s = union(%2$s, %3$s)");
		stmtStringBuilder.append("\n");
		
		String stmt = String.format(stmtStringBuilder.toString(), parm, criteria.getParameterName(), otherCriteria.getParameterName());
		
		return stmt;
		
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public Criteria getOtherCriteria() {
		return otherCriteria;
	}

	public void setOtherCriteria(Criteria otherCriteria) {
		this.otherCriteria = otherCriteria;
	}
	
	
}
