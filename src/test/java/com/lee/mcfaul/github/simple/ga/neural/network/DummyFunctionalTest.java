package com.lee.mcfaul.github.simple.ga.neural.network;

import com.lee.mcfaul.github.simple.ga.neural.network.categories.FunctionalTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

@Category(FunctionalTest.class) public class DummyFunctionalTest {

	@Test public void dummyFunctionalTest() {
		int x = 0;
		for (int i = 0; i < 10; i++) {
			x++;
		}
		assertEquals(10, x);
	}

}
