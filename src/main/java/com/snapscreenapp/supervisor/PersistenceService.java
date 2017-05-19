package com.snapscreenapp.supervisor;

import com.snapscreenapp.supervisor.model.Account;
import com.snapscreenapp.supervisor.model.DeviceConnection;
import com.snapscreenapp.supervisor.model.User;
import com.snapscreenapp.supervisor.model.UserId;
import com.snapscreenapp.supervisor.model.ViewerConnection;

public interface PersistenceService {
	public void addDeviceConnection(UserId userId, DeviceConnection device);

	public void closeAccount(UserId userId);

	public void createUser(UserId userId);

	public void removeDeviceConnection(UserId userId, DeviceConnection device);

	public User retrieveUserBy(UserId userId);

	public User retrieveUserBy(DeviceConnection device);

	public void update(UserId userId, Account account);

	public void update(UserId userId, ViewerConnection viewerConnection);
}