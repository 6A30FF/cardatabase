package com.packt.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
	static final long EXPIRATIONTIME = 86400000; // 1 day in ms
	static final String PREFIX = "Bearer";
	// 시연 용도 비밀키 생성
	// 애플리케이션 구성에서 읽을 수 있음
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	// 서명된 JWT 토큰 생성
	public String getToken(String username) {
		String token = Jwts.builder()
			  .setSubject(username)
			  .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
			  .signWith(key)
			  .compact();
		return token;
  }

	// 요청 권한 부여 헤더에서 토큰을 가져와 토큰을 확인하고 사용자 이름을 얻음
	public String getAuthUser(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
	
		if (token != null) {
			String user = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();

			if (user != null)
				return user;
		}

		return null;
	}
}
