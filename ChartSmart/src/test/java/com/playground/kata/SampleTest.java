package com.playground.kata;

import org.approvaltests.Approvals;
import org.approvaltests.legacycode.Range;
import org.junit.Test;

public class SampleTest {

	@Test
	public void test() {
		Approvals.verifyAll("Numbers", Range.get(4, 8));
	}

}
