package com.snapscreenapp.supervisor.impl;

import com.snapscreenapp.supervisor.model.UserId;

public class AppEngineUserId implements UserId {
	private String id;

	public AppEngineUserId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}
}