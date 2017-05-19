package com.snapscreenapp.supervisor;

import org.junit.Test;

import com.snapscreenapp.supervisor.impl.AdministrationServiceImpl;
import com.snapscreenapp.supervisor.impl.Registration;

import mockit.Injectable;
import mockit.Tested;

public class AdministrationServiceImplTest {
	@Tested
	private AdministrationServiceImpl candidate;

	@Injectable
	private AuthenticationService authenticationService;

	@Injectable
	private PaymentService paymentService;

	@Injectable
	private PersistenceService persistenceService;

	@Injectable
	private Registration registration;

	@Test
	public void testRegisterUser() {
		candidate.registerUser(registration);
	}
}