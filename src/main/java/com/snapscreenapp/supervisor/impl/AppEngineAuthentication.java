package com.snapscreenapp.supervisor.impl;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.snapscreenapp.supervisor.AuthenticatedSession;
import com.snapscreenapp.supervisor.AuthenticationService;

public class AppEngineAuthentication implements AuthenticationService {
	private UserService userService;

	// TODO Get from context
	public AppEngineAuthentication(UserService userService) {
		this.userService = userService;
	}

	@Override
	public AuthenticatedSession authenticate() {
		User user = userService.getCurrentUser();

		if (user == null)
			return new AppEngineSession(null, false);

		String userId = user.getUserId();
		return new AppEngineSession(userId, true);
	}

	@Override
	public String buildLoginUrl(String returnUrl) {
		return userService.createLoginURL(returnUrl);
	}

	@Override
	public String buildLogoutUrl(String returnUrl) {
		return userService.createLogoutURL(returnUrl);
	}
}