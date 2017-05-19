package com.snapscreenapp.supervisor;

import com.snapscreenapp.supervisor.impl.Activity;
import com.snapscreenapp.supervisor.model.DeviceConnection;

public interface MonitoringService {
	public void submit(DeviceConnection device, Activity activity);
}