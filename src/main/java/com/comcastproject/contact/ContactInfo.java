package com.comcastproject.contact;

public class ContactInfo {
	
	private String	name;
	private String	phone;
	private String	email;
	private String	position;
	private Integer id;
	private boolean editable;
	
	@Override
    public String toString() {
        return "Contact Info [Name: " + name + ", Phone: " + phone + ", Email: " + email + ", Position: " + position + "]";
    }  
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
