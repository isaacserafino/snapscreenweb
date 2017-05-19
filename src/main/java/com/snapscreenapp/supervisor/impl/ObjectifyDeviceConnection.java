package com.snapscreenapp.supervisor.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.snapscreenapp.supervisor.model.DeviceConnection;

@Entity
public class ObjectifyDeviceConnection implements DeviceConnection {
	@Parent
	Key<ObjectifyUser> user;
	@Id
	private String code;

	public ObjectifyDeviceConnection(String userId, String code) {
		Key<ObjectifyUser> user = Key.create(ObjectifyUser.class, userId);
		this.user = user;

		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectifyDeviceConnection other = (ObjectifyDeviceConnection) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}