package com.packt.cardatabase;

import com.packt.cardatabase.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 모든 요청을 인증
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
					throws ServletException, IOException {

		// Authorization 헤더에서 토큰을 가져옴
		String jws = request.getHeader(HttpHeaders.AUTHORIZATION);			

		if (jws != null) {
			// 토큰을 확인하고 사용자를 얻음
			String user = jwtService.getAuthUser(request);

			// 인증
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(user, null, java.util.Collections.emptyList());

			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);
	}
}
