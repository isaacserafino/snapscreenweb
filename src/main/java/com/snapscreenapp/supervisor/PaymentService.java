package com.snapscreenapp.supervisor;

import com.snapscreenapp.supervisor.impl.PaymentVerification;
import com.snapscreenapp.supervisor.model.Account;

//TODO: (IMS) Implement
public interface PaymentService {
	public boolean verify(PaymentVerification verification, Account account);
}