package com.snapscreenapp.supervisor.model;

import static java.time.LocalDate.now;

import java.time.LocalDate;

public class Account {
	private LocalDate lastPaymentDate;

	public Account(LocalDate lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public LocalDate getLastPaymentDate() {
		return lastPaymentDate;
	}

	public boolean isCurrent() {
		return lastPaymentDate != null && now().minusMonths(1).isBefore(lastPaymentDate);
	}

	public void recharge() {
		lastPaymentDate = now();
	}
}