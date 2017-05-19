package com.snapscreenapp.supervisor.impl;

import com.snapscreenapp.supervisor.AuthenticatedSession;
import com.snapscreenapp.supervisor.model.UserId;

public class AppEngineSession implements AuthenticatedSession {
	private String id;
	private boolean authenticated;

	public AppEngineSession(String id, boolean authenticated) {
		this.id = id;
		this.authenticated = authenticated;
	}

	@Override
	public UserId getUserId() {
		return new AppEngineUserId(id);
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}
}