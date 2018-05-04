package com.gildedrose;

public class BackstagePass implements AgeItem {

	public void age(Item item) {
		GildedRose.incrementQualityUnlessGreaterThan50(item);
		if (item.sellIn < 11) {
			GildedRose.incrementQualityUnlessGreaterThan50(item);
		}
		if (item.sellIn < 6) {
			GildedRose.incrementQualityUnlessGreaterThan50(item);
		}
	}

	public void expire(Item item) {
		item.quality = 0;
	}

}
