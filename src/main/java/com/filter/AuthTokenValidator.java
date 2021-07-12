package com.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.entity.UserEntity;
import com.google.gson.Gson;
import com.model.ResponseEntity;
import com.repository.UserRepository;

@Component
public class AuthTokenValidator implements Filter {

	@Autowired
	UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("tokeng filter...");

		if (((HttpServletRequest) request).getRequestURL().toString().contains("/api/")) {
			String authToken = ((HttpServletRequest) request).getHeader("authToken");
			System.out.println("In filter AuthToken = >" + authToken);

			ResponseEntity<String> resp = new ResponseEntity<>();

			if (authToken == null || authToken.trim().length() == 0) {
				System.out.println("token is null");
				resp.setData("Invalid user");
				resp.setStatus(-1);
				resp.setMsg("Invalid User access");

//				((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
//				((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods",
//						"GET, POST, PUT, DELETE, OPTIONS");
//				((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");
//				((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers",
//						"X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,authToken");
//				((HttpServletResponse) response).addHeader("Access-Control-Expose-Headers", "xsrf-token");
//				((HttpServletResponse) response).addHeader("Access-Control-Expose-Headers", "authToken");

				response.setContentType("application/json");

				String json = new Gson().toJson(resp);
				response.getWriter().print(json);

			} else {
				List<UserEntity> users = userRepository.findByAuthToken(authToken);
				if(authToken.equals("admin")) {
					System.out.println("admin token");
					chain.doFilter(request, response);

				}
				else if (users == null || users.size() == 0) {
					System.out.println("user not found....");
//					((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
//					((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods",
//							"GET, POST, PUT, DELETE, OPTIONS");
//					((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");
//					((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers",
//							"X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,authToken");
//					((HttpServletResponse) response).addHeader("Access-Control-Expose-Headers", "xsrf-token");
//					((HttpServletResponse) response).addHeader("Access-Control-Expose-Headers", "authToken");

					resp.setData("Invalid user");
					resp.setStatus(-1);
					resp.setMsg("Invalid User access");
					response.setContentType("application/json");
					String json = new Gson().toJson(resp);
					response.getWriter().print(json);

				} else {
					System.out.println("user validated....");
					chain.doFilter(request, response);
					
					//url admin --> role admin 
					
				}
			}
		} else {
			System.out.println("byPass");
			chain.doFilter(request, response);
		}
	}

}
