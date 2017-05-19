package com.snapscreenapp.supervisor.model;

public class ViewerConnection {
	private String authorizationKey;

	public ViewerConnection(String authorizationKey) {
		this.authorizationKey = authorizationKey;
	}

	public String getAuthorizationKey() {
		return authorizationKey;
	}
}