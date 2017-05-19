package com.snapscreenapp.supervisor.impl;

import com.snapscreenapp.supervisor.AdministrationService;
import com.snapscreenapp.supervisor.AuthenticatedSession;
import com.snapscreenapp.supervisor.PaymentService;
import com.snapscreenapp.supervisor.PersistenceService;
import com.snapscreenapp.supervisor.ViewerService;
import com.snapscreenapp.supervisor.model.Account;
import com.snapscreenapp.supervisor.model.DeviceConnection;
import com.snapscreenapp.supervisor.model.User;
import com.snapscreenapp.supervisor.model.UserId;
import com.snapscreenapp.supervisor.model.ViewerConnection;

// TODO: Implement as Spring REST Service
public class AdministrationServiceImpl implements AdministrationService {
	private PaymentService paymentService;
	private PersistenceService persistenceService;
	private ViewerService viewerService;

	public AdministrationServiceImpl(PaymentService paymentService, PersistenceService persistenceService,
			ViewerService viewerService) {
		this.paymentService = paymentService;
		this.persistenceService = persistenceService;
		this.viewerService = viewerService;
	}

	@Override
	public void registerUser(Registration registration) {
		UserId userId = registration.getUserId();

		persistenceService.createUser(userId);
	}

	@Override
	public User retrieveUserView(AuthenticatedSession authentication) {
		if (!authentication.isAuthenticated())
			return null;

		UserId userId = authentication.getUserId();
		return persistenceService.retrieveUserBy(userId);
	}

	@Override
	public void closeAccount(AuthenticatedSession authentication) {
		if (!authentication.isAuthenticated())
			return;

		UserId userId = authentication.getUserId();
		persistenceService.closeAccount(userId);
	}

	@Override
	public void connectDevice(AuthenticatedSession authentication, DeviceConnection device) {
		if (!authentication.isAuthenticated())
			return;

		UserId userId = authentication.getUserId();
		persistenceService.addDeviceConnection(userId, device);
	}

	@Override
	public void disconnectDevice(AuthenticatedSession authentication, DeviceConnection device) {
		if (!authentication.isAuthenticated())
			return;

		UserId userId = authentication.getUserId();
		persistenceService.removeDeviceConnection(userId, device);
	}

	@Override
	public void createViewerConnection(AuthenticatedSession authentication) {
		if (!authentication.isAuthenticated())
			return;

		UserId userId = authentication.getUserId();
		ViewerConnection viewerConnection = viewerService.createConnection();
		persistenceService.update(userId, viewerConnection);
	}

	@Override
	public void disconnectViewer(AuthenticatedSession authentication) {
		if (!authentication.isAuthenticated())
			return;

		UserId userId = authentication.getUserId();
		persistenceService.update(userId, (ViewerConnection) null);
	}

	@Override
	public void makePayment(AuthenticatedSession authentication, PaymentVerification verification, Account account) {
		if (!(authentication.isAuthenticated() || paymentService.verify(verification, account)))
			return;

		account.recharge();

		UserId userId = authentication.getUserId();
		persistenceService.update(userId, account);
	}
}