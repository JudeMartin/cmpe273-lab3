package edu.sjsu.cmpe.cache.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

public class Entry implements Serializable{

	private static final long serialVersionUID = 7717816779761620552L;

	@NotNull
    private long key;

    @NotEmpty
    private String value;

    @NotEmpty
    private DateTime createdAt = new DateTime();

    public long getKey() {
	return key;
    }
    public void setKey(long key) {
	this.key = key;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }
    public DateTime getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
	this.createdAt = createdAt;
    }
}
