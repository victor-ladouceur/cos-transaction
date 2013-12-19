package org.isidis.amd.resources.base;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;
import org.junit.Before;
import org.junit.Test;

public class BaseImplTest {

	private BaseBean instance;
	
	@Before
	public void before() {
		try {
			instance = new BaseBean();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBaseImpl() {
		assertNotNull(instance);
	}

	@Test
	public void testGetNovice() {
		try {
			assertTrue(instance.getNovice("John Doe") instanceof UnitBean);
		} catch (RemoteException e) {
			fail(e.getMessage());
		}
	}

}
