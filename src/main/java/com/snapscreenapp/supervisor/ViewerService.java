package com.snapscreenapp.supervisor;

import com.snapscreenapp.supervisor.impl.Activity;
import com.snapscreenapp.supervisor.model.ViewerConnection;

// TODO: (IMS) Implement
public interface ViewerService {
	public ViewerConnection createConnection();

	public void submit(ViewerConnection viewerConnection, Activity activity);
}