package com.snapscreenapp.supervisor.model;

import java.util.Set;

public interface User {
	public Account getAccount();

	public Set<DeviceConnection> getDevices();

	public ViewerConnection getViewerConnection();
}