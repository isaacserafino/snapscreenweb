package com.snapscreenapp.supervisor.impl;

import java.awt.Image;

public class Activity {
	private Image image;

	/** Could include device code and timestamp of next expected activity submission */
	private String title;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}