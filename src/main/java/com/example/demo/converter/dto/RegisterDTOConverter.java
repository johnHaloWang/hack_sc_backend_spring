package com.example.demo.converter.dto;


import com.example.demo.dto.RegisterDTO;
import com.example.demo.model.Authority;
import com.example.demo.model.User;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;


public class RegisterDTOConverter implements Converter<RegisterDTO, User> {

    @Override
    public User convert(final RegisterDTO dto) {
        final User user = new User();
        user.set_id(ObjectId.get());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setContactNumber(dto.getContactNumber());
		user.setAccountNonExpired(false);
		user.setCredentialsNonExpired(false);
		user.setEnabled(true);
		user.setRole(dto.getRole());
		user.setEmail(dto.getEmail());

        List<Authority> authorities = new ArrayList<>();
		if (user.getRole().equals("ROLE_USER"))
			authorities.add(Authority.ROLE_USER);
		else if (user.getRole().equals("ROLE_ADMIN"))
			authorities.add(Authority.ROLE_ADMIN);
		else if (user.getRole().equals("ANONYMOUS"))
			authorities.add(Authority.ANONYMOUS);
        user.setAuthorities(authorities);
        return user;
    }
}