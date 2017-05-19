package com.snapscreenapp.supervisor.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.time.LocalDate;

import com.googlecode.objectify.VoidWork;
import com.snapscreenapp.supervisor.PersistenceService;
import com.snapscreenapp.supervisor.model.Account;
import com.snapscreenapp.supervisor.model.DeviceConnection;
import com.snapscreenapp.supervisor.model.User;
import com.snapscreenapp.supervisor.model.UserId;
import com.snapscreenapp.supervisor.model.ViewerConnection;

public class ObjectifyPersistence implements PersistenceService {
	@Override
	public void addDeviceConnection(UserId userId, DeviceConnection deviceConnection) {
		String id = userId.getId();
		String code = deviceConnection.getCode();
		ObjectifyDeviceConnection device = new ObjectifyDeviceConnection(id, code);
		ofy().save().entity(device).now();
	}

	@Override
	public void closeAccount(UserId userId) {
		String id = userId.getId();
		ObjectifyUser user = new ObjectifyUser(id);
		ofy().delete().entity(user).now();
	}

	@Override
	public void createUser(UserId userId) {
		String id = userId.getId();
		ObjectifyUser user = new ObjectifyUser(id);
		ofy().save().entity(user).now();
	}

	@Override
	public void removeDeviceConnection(UserId userId, DeviceConnection deviceConnection) {
		String id = userId.getId();
		String code = deviceConnection.getCode();
		ObjectifyDeviceConnection device = new ObjectifyDeviceConnection(id, code);
		ofy().delete().entity(device).now();
	}

	@Override
	public User retrieveUserBy(UserId userId) {
		String id = userId.getId();

		return ofy().load().type(ObjectifyUser.class).id(id).now();
	}

	@Override
	public User retrieveUserBy(DeviceConnection device) {
		String code = device.getCode();

		return ofy().load().type(ObjectifyUser.class).filterKey("deviceConnections", code).first().now();
	}

	@Override
	public void update(UserId userId, Account account) {
		final String id = userId.getId();
		final LocalDate date = account.getLastPaymentDate();

		ofy().transact(new VoidWork() {
			public void vrun() {
				ObjectifyUser user = ofy().load().type(ObjectifyUser.class).id(id).now();
				user.setLastPaymentDate(date);
				ofy().save().entity(user).now();
			}
		});
	}

	@Override
	public void update(UserId userId, ViewerConnection viewerConnection) {
		final String id = userId.getId();
		final String authorizationKey = viewerConnection.getAuthorizationKey();

		ofy().transact(new VoidWork() {
			public void vrun() {
				ObjectifyUser user = ofy().load().type(ObjectifyUser.class).id(id).now();
				user.setViewerAuthorizationKey(authorizationKey);
				ofy().save().entity(user).now();
			}
		});
	}
}