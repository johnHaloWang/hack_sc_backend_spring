package com.example.demo.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.requireNonNull;
import org.bson.types.ObjectId;

@Value
@Builder
public class User implements UserDetails {
	private static final long serialVersionUID = 2396654715019746670L;
	private ObjectId _id;

	/**
	 * @return the id
	 */
	public String getId() {
		return _id.toHexString();
	}

	public ObjectId get_ObjectId() {
		return _id;
	}
	private String username;
	private String password;
	private String store_id;
	private String contactNumber;
	private String role;
	
	/**
	 * @return the store_id
	 */
	public String getStore_id() {
		return store_id;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}


	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	
	@JsonCreator
	User(@JsonProperty("id") final ObjectId _id, 
		 @JsonProperty("username") final String username,
		 @JsonProperty("password") final String password,
		 @JsonProperty("store_id") final String store_id,
		 @JsonProperty("contactNumber") final String contactNumber,
		 @JsonProperty("role") final String role){

		super();
		this._id = requireNonNull(_id);
		this.username = requireNonNull(username);
		this.password = requireNonNull(password);
		this.store_id = requireNonNull(store_id);
		this.contactNumber = requireNonNull(contactNumber);
		this.role = requireNonNull(role);
	}
	
	@JsonCreator
	User(@JsonProperty("id") final ObjectId _id, 
		 @JsonProperty("username") final String username,
		 @JsonProperty("password") final String password){

		super();
		this._id = requireNonNull(_id);
		this.username = requireNonNull(username);
		this.password = requireNonNull(password);
	}

	
	
	// Builder Pattern
	public User(Builder builder) {
		this._id = builder._id;
		this.username = builder.username;
		this.password = builder.password;
		this.contactNumber = builder.contactNumber;
		this.role = builder.role;
	}

	public static class Builder {
		ObjectId _id;
		String username;
		String store_id;
		String contactNumber;
		String role;
		String password;

		
		public static Builder builder() {
			return new Builder();
		}

		private Builder() {
		}

		// Setter methods
		public Builder setId(ObjectId id) {
			this._id = id;
			return this;
		}

		public Builder setUsername(String name) {
			this.username = name;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public Builder setRole(String role) {
			this.role = role;
			return this;
		}
		public Builder setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
			return this;
		}
		public Builder setStore_id(String store_id) {
			this.store_id = store_id;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public User build() {
			return new User(this);
		}
	}

	@JsonIgnore
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
