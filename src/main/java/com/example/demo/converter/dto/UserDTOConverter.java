package com.example.demo.converter.dto;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Authority;
import com.example.demo.model.User;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;


public class UserDTOConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(final UserDTO dto) {
        final User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAccountNonExpired(false);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

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
