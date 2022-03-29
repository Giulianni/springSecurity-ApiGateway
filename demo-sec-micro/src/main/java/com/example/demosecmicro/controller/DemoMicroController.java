package com.example.demosecmicro.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/micro1")
public class DemoMicroController {

	@GetMapping("/message")
	public String getMessage(HttpServletRequest request, @AuthenticationPrincipal Jwt principal) {
		// Desde el bearer token.
		log.info("\n===================\n");
		log.info("Authorization header: {}", request.getHeader(HttpHeaders.AUTHORIZATION));

		// Desde el security context.
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		log.info("\n===================\n");
		log.info("Authentication: {}", authentication);
		log.info("Principal: {}", authentication.getPrincipal());
		log.info("Credentials: {}", authentication.getCredentials());
		log.info("Name: {}", authentication.getName());
		log.info("Details: {}", authentication.getDetails());
		log.info("Authorities: {}", authentication.getAuthorities());

		// Desde el JWT directamente.
		log.info("\n===================\n");
		log.info("Claims: {}", principal.getClaims());
		log.info("Headers: {}", principal.getHeaders());
		log.info("Audiences: {}", principal.getAudience());
		log.info("Issuer: {}", principal.getIssuer());
		log.info("Expiry date: {}", principal.getExpiresAt());

		return "Hello, World!";
	}

}
