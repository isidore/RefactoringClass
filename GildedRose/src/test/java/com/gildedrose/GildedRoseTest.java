package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.UseReporter;
import org.approvaltests.reporters.macosx.DiffMergeReporter;
import org.junit.Test;

@UseReporter(DiffMergeReporter.class)
public class GildedRoseTest {

	@Test
	public void foo() {
		Item[] items = new Item[] { new Item("foo", 0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("Item [name=foo, sellIn=-1, quality=0]", app.items[0].toString());
		Approvals.verify(app.items[0]);

	}

}
