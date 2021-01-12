package com.tc.yuxiu.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenUtil {

	private static final String SECRET = "123";

	public static String createToken() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		Date iatDate = new Date();
		Date experiesDate = new Date(System.currentTimeMillis()+ (5 * 60 * 1000));
        // 添加头部 withHeader(map)
        // 添加自定义数据.withClaim("id", "1")
        // 签发时间.withIssuedAt(iatDate)
        // 设置过期的日期.withExpiresAt(experiesDate)
        // 加密.sign(Algorithm.HMAC256(SECRET));

		String token = JWT.create()
                .withHeader(map)
				.withClaim("id", "1")
				.withIssuedAt(iatDate)
				.withExpiresAt(experiesDate)
				.sign(Algorithm.HMAC256(SECRET));
		return token;
	}

	public static Map<String, Claim> parseToken(String token) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
		} catch (Exception e) {
			System.out.println("token失效");
			return null;
		}
		return jwt.getClaims();
	}

}
