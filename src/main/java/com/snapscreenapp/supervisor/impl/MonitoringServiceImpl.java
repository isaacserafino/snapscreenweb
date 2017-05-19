package com.snapscreenapp.supervisor.impl;

import com.snapscreenapp.supervisor.MonitoringService;
import com.snapscreenapp.supervisor.PersistenceService;
import com.snapscreenapp.supervisor.ViewerService;
import com.snapscreenapp.supervisor.model.Account;
import com.snapscreenapp.supervisor.model.DeviceConnection;
import com.snapscreenapp.supervisor.model.User;
import com.snapscreenapp.supervisor.model.ViewerConnection;

// TODO: Spring REST Service?
public class MonitoringServiceImpl implements MonitoringService {
	private PersistenceService persistenceService;
	private ViewerService viewerService;

	public MonitoringServiceImpl(PersistenceService persistenceService, ViewerService viewerService) {
		this.persistenceService = persistenceService;
		this.viewerService = viewerService;
	}

	@Override
	public void submit(DeviceConnection device, Activity activity) {
		User user = persistenceService.retrieveUserBy(device);

		if (user == null)
			return;

		Account account = user.getAccount();
		ViewerConnection viewerConnection = user.getViewerConnection();

		if (viewerConnection == null || account == null || !account.isCurrent())
			return;

		viewerService.submit(viewerConnection, activity);
	}
}