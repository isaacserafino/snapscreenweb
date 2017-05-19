package com.snapscreenapp.supervisor;

import com.snapscreenapp.supervisor.model.UserId;

public interface AuthenticatedSession {
	public UserId getUserId();

	public boolean isAuthenticated();
}