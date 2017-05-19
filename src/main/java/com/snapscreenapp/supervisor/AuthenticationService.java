package com.snapscreenapp.supervisor;

public interface AuthenticationService {
	public AuthenticatedSession authenticate();

	public String buildLoginUrl(String returnUrl);

	public String buildLogoutUrl(String returnUrl);
}