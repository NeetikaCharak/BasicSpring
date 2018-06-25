/**
 * 
 */
package com.neetika.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.neetika.service.HibernateServiceImpl;

/**
 * @author Neetika
 *
 */
public class TestHibernateDaoImpl {

	/**
	 * Test method for {@link com.neetika.dao.HibernateDaoImpl#getCircleCount()}.
	 */
	@Test
	public void testGetCircleCount() {
		/*fail("Not yet implemented");*/
		
		HibernateDaoImpl mockDaoImpl = Mockito.mock(HibernateDaoImpl.class);
		HibernateServiceImpl service = new HibernateServiceImpl(mockDaoImpl);
		
		Mockito.when(mockDaoImpl.getCircleCount()).thenReturn(50);
		assertEquals(50, service.getCircleCount());
		
		Mockito.when(mockDaoImpl.getCircleCount()).thenThrow(new RuntimeException());
		assertEquals(-1, service.getCircleCount());
	}

}
