package com.snapscreenapp.supervisor;

import com.snapscreenapp.supervisor.impl.PaymentVerification;
import com.snapscreenapp.supervisor.impl.Registration;
import com.snapscreenapp.supervisor.model.Account;
import com.snapscreenapp.supervisor.model.DeviceConnection;
import com.snapscreenapp.supervisor.model.User;

public interface AdministrationService {
	public void registerUser(Registration registration);

	public User retrieveUserView(AuthenticatedSession authentication);

	public void closeAccount(AuthenticatedSession authentication);

	public void connectDevice(AuthenticatedSession authentication, DeviceConnection device);

	public void disconnectDevice(AuthenticatedSession authentication, DeviceConnection device);

	public void createViewerConnection(AuthenticatedSession authentication);

	public void disconnectViewer(AuthenticatedSession authentication);

	public void makePayment(AuthenticatedSession authentication, PaymentVerification paymentVerification,
			Account account);
}