package com.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.UserEntity;
import com.model.ResponseEntity;
import com.repository.UserRepository;

@Component
public class AuthTokenValidator implements Filter {

	@Autowired
	UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String authToken = ((HttpServletRequest) request).getHeader("authToken");
		System.out.println("In filter AuthToken = >" + authToken);

		ResponseEntity<String> resp = new ResponseEntity<>();

		if (authToken == null) {
			resp.setData("Invalid user");
			resp.setStatus(-1);
			resp.setMsg("Invalid User access");
		} else {
			List<UserEntity> users = userRepository.findByAuthToken(authToken);
			if (users == null || users.size() == 0) {
				resp.setData("Invalid user");
				resp.setStatus(-1);
				resp.setMsg("Invalid User access");
				
			} else {
				chain.doFilter(request, response);
			}
		}
	}

}
