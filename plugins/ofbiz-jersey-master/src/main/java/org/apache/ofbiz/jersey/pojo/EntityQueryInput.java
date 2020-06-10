package org.apache.ofbiz.jersey.pojo;

import java.util.*;
import java.util.stream.Collectors;

public class EntityQueryInput {

	private Map<String, Object> inputFields = new HashMap<>();
	private List<String> fieldList = new ArrayList<>();
	private Boolean areRelationResultsMandatory = false;
	private Map<String, EntityQueryInput> entityRelations = new HashMap<>();

	public EntityQueryInput() {
	}

	public Map<String, Object> getInputFields() {
		return inputFields;
	}

	public void setInputFields(Map<String, Object> inputFields) {
		this.inputFields = inputFields;
	}

	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public Boolean getAreRelationResultsMandatory() {
		return areRelationResultsMandatory;
	}

	public void setAreRelationResultsMandatory(Boolean areRelationResultsMandatory) {
		this.areRelationResultsMandatory = areRelationResultsMandatory;
	}

	public Map<String, EntityQueryInput> getEntityRelations() {
		return entityRelations;
	}

	public void setEntityRelations(Map<String, EntityQueryInput> entityRelations) {
		this.entityRelations = entityRelations;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<>();
		result.put("inputFields", inputFields);
		result.put("fieldList", fieldList);
		result.put("areRelationResultsMandatory", areRelationResultsMandatory);
		Map<String, Map<String, Object>> converted = entityRelations.entrySet().stream().map(x -> new AbstractMap.SimpleEntry<>(x.getKey(), x.getValue().toMap())).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
		result.put("entityRelations", converted);
		return result;
	}
}
