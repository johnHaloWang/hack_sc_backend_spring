package com.example.demo.data;

//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Builder;
//import lombok.Value;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

//import java.util.ArrayList;
//import java.util.Collection;

import static java.util.Objects.requireNonNull;

import java.util.Date;

import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.core.mapping.Document;

//@Value
//@Builder
//@Document
public class AppUser {
	//implements UserDetails {
	//private static final long serialVersionUID = 2396654715019746670L;
	private ObjectId _id;
	private String username;
	private String password;
	private String store_id;
	private String contactNumber;
	private String role;
	private String email;

	private String creationTime = new Date(System.currentTimeMillis()).toString();
	


	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id.toHexString();
	}

	
	public ObjectId get_ObjectId() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getStore_id() {
		return store_id;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	

	
	public AppUser(ObjectId _id, 
		 String username,
		 String password,
		 String store_id,
		 String contactNumber,
		 String role,
		 String email){

		super();
		this._id = requireNonNull(_id);
		this.username = requireNonNull(username);
		this.password = requireNonNull(password);
		this.store_id = requireNonNull(store_id);
		this.contactNumber = requireNonNull(contactNumber);
		this.role = requireNonNull(role);
		this.email = requireNonNull(email);
	}
	
//	public User(ObjectId _id, 
//		 String username,
//		 String password){
//
//		super();
//		this._id = requireNonNull(_id);
//		this.username = requireNonNull(username);
//		this.password = requireNonNull(password);
//	}

	
	
//	// Builder Pattern
//	public User(Builder builder) {
//		this._id = builder._id;
//		this.username = builder.username;
//		this.password = builder.password;
//		this.contactNumber = builder.contactNumber;
//		this.role = builder.role;
//		this.email = builder.email;
//		this.created = builder.created;
//	}
//
//	public static class Builder {
//		ObjectId _id;
//		String username;
//		String store_id;
//		String contactNumber;
//		String role;
//		String password;
//		String email;
//		Date created;
//
//		
//		public static Builder builder() {
//			return new Builder();
//		}
//
//		private Builder() {
//		}
//
//		// Setter methods
//		public Builder setId(ObjectId id) {
//			this._id = id;
//			return this;
//		}
//
//		public Builder setUsername(String name) {
//			this.username = name;
//			return this;
//		}
//
//		public Builder setPassword(String password) {
//			this.password = password;
//			return this;
//		}
//		
//		public Builder setRole(String role) {
//			this.role = role;
//			return this;
//		}
//		public Builder setContactNumber(String contactNumber) {
//			this.contactNumber = contactNumber;
//			return this;
//		}
//		public Builder setStore_id(String store_id) {
//			this.store_id = store_id;
//			return this;
//		}
//		public Builder setEmail(String email) {
//			this.email = email;
//			return this;
//		}
//
//		public Builder setCreated(Date created) {
//			this.created = created;
//			return this;
//		}
//		// build method to deal with outer class
//		// to return outer instance
//		public User build() {
//			return new User(this);
//		}
//	}

//	@JsonIgnore
//	@Override
//	public Collection<GrantedAuthority> getAuthorities() {
//		return new ArrayList<>();
//	}
//

	
//
//	@JsonIgnore
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@JsonIgnore
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@JsonIgnore
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}

}
