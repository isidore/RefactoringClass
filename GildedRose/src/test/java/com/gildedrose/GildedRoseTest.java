package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.UseReporter;
import org.approvaltests.reporters.macosx.DiffMergeReporter;
import org.junit.Test;

@UseReporter(DiffMergeReporter.class)
public class GildedRoseTest {

	@Test
	public void foo() throws Exception {
		String[] names = { "foo" };
		CombinationApprovals.verifyAllCombinations(this::getItem, names);
	}

	public Item getItem(String name) {
		Item[] items = new Item[] { new Item(name, 0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		Item item = app.items[0];
		return item;
	}

}
