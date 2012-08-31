package com.bull.grh.view.admin.vo;

import java.io.Serializable;

public class ValueVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String value;
	private TypeVO type = new TypeVO();
	private ValueVO parent = new ValueVO();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ValueVO getParent() {
		return parent;
	}

	public void setParent(ValueVO parent) {
		this.parent = parent;
	}

	public TypeVO getType() {
		return type;
	}

	public void setType(TypeVO type) {
		this.type = type;
	}

	public ValueVO(String value, ValueVO parent) {
		super();
		this.value = value;
		this.parent = parent;
	}

	public ValueVO(String value) {
		super();
		this.value = value;
	}

	public ValueVO() {
	}

	@Override
	public boolean equals(Object obj) {
		return (this == (ValueVO) obj);
	}
	
}
