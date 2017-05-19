package com.snapscreenapp.supervisor.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.snapscreenapp.supervisor.model.Account;
import com.snapscreenapp.supervisor.model.DeviceConnection;
import com.snapscreenapp.supervisor.model.User;
import com.snapscreenapp.supervisor.model.ViewerConnection;

@Entity
public class ObjectifyUser implements User {
	@Index
	private Set<ObjectifyDeviceConnection> deviceConnections;
	private LocalDate lastPaymentDate;
	@Id
	private String userId;
	private String viewerAuthorizationKey;

	public ObjectifyUser(String userId) {
		this.userId = userId;
	}

	public Account getAccount() {
		return new Account(lastPaymentDate);
	}

	public Set<DeviceConnection> getDevices() {
		Set<DeviceConnection> devices = new HashSet<DeviceConnection>();
		for (ObjectifyDeviceConnection deviceConnectionImpl : deviceConnections) {
			devices.add((DeviceConnection) deviceConnectionImpl);
		}
		return devices;
	}

	public ViewerConnection getViewerConnection() {
		return new ViewerConnection(viewerAuthorizationKey);
	}

	public void setLastPaymentDate(LocalDate lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public void setViewerAuthorizationKey(String viewerAuthorizationKey) {
		this.viewerAuthorizationKey = viewerAuthorizationKey;
	}
}