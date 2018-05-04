package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.UseReporter;
import org.approvaltests.reporters.macosx.DiffMergeReporter;
import org.junit.Test;

@UseReporter(DiffMergeReporter.class)
public class GildedRoseTest {

	@Test
	public void foo() throws Exception {
		String[] names = { "foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert",
				"Sulfuras, Hand of Ragnaros", "", null };
		Integer[] qualities = { 0, 1, 50, 49, 51, -1, Integer.MAX_VALUE, Integer.MIN_VALUE };
		Integer[] sellIns = { 0, 11, -1, Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 10, 12, 6, 5, 7 };
		CombinationApprovals.verifyAllCombinations(this::getItem, names, sellIns, qualities);
	}

	public Item getItem(String name, Integer sellIn, Integer quality) {
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		Item item = app.items[0];
		return item;
	}

}
