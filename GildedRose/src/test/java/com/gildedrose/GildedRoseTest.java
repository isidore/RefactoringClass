package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GildedRoseTest {

	@Test
	public void foo() {
		Item[] items = new Item[] { new Item("foo", 0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("foo", app.items[0].name);
		assertEquals(0, app.items[0].quality);
		assertEquals(-1, app.items[0].sellIn);
		assertEquals("Item [name=foo, sellIn=-1, quality=0]", app.items[0].toString());
	}

}
