package com.gildedrose;

public class Brie implements AgeItem {

	@Override
	public void age(Item item) {
		GildedRose.incrementQualityUnlessGreaterThan50(item);
	}

	public void expire(Item item) {
		age(item);

	}

}
