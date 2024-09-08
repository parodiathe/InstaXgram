package com.Makushev.Makushev_Social_Twitter.config;

import com.Makushev.Makushev_Social_Twitter.request.LoginRequest;
import com.Makushev.Makushev_Social_Twitter.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {

        String jwt = Jwts.builder()
                .setIssuer("Makushev") // Устанавливаем имя издателя токена
                .setIssuedAt(new Date()) // Устанавливаем дату и время выпуска токена
                .setExpiration(new Date(new Date().getTime() + 864000000)) // Устанавливаем дату и время истечения срока действия токена (10 дней)
                .claim("email", auth.getName()) // Добавляем в токен поле "email" со значением, полученным из объекта Authentication
                .signWith(key) // Подписываем токен с помощью ключа, созданного с помощью метода Keys…
                .compact(); // Преобразуем токен в строку

        return jwt;

    }

    public static String getEmailFromJwtToken(String jwt){

        // Удаляем префикс "Bearer " из JWT токена
        jwt = jwt.substring(7);

        // Создаем парсер JWT токенов с заданным ключом
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        // Извлекаем из Claims значение поля "email" и возвращаем его
        String email = String.valueOf(claims.get("email"));
        return email;

    } // good
}
