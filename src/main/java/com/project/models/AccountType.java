package com.project.models;

import java.util.Objects;

public class AccountType {
	private int typeId;// primary key
	private String type; //not null, unique
	
	
	public AccountType(int typeId, String accountType) {
		super();
		this.typeId = typeId;
		this.type = accountType;
	}
	
	public AccountType() {
		super();

	}



	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}



	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		return Objects.hash(type, typeId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AccountType)) {
			return false;
		}
		AccountType other = (AccountType) obj;
		return Objects.equals(type, other.type) && typeId == other.typeId;
	}


	@Override
	public String toString() {
		return "AccountType [typeId=" + typeId + ", type=" + type + "]";
	}
	

}
