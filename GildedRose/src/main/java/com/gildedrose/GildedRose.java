package com.gildedrose;

class GildedRose {
	private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			ageItem(items[i]);

			decrementSellInUnlessSulfuras(items[i]);

			handleExpired(items[i]);
		}
	}

	public void decrementSellInUnlessSulfuras(Item item) {
		if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			return;
		}
		item.sellIn--;
	}

	public void handleExpired(Item item) {
		if (0 <= item.sellIn) {
			return;
		}
		if (item.name.equals(AGED_BRIE)) {
			new Brie().expire(item);
		} else if (item.name.equals(BACKSTAGE_PASSES)) {
			new BackstagePass().expire(item);
		} else if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			// Do Nothing
		} else {
			new DefaultAgeItem().expire(item);
		}
	}

	public void ageItem(Item item) {
		if (item.name.equals(AGED_BRIE)) {
			new Brie().age(item);
		} else if (item.name.equals(BACKSTAGE_PASSES)) {
			new BackstagePass().age(item);
		} else if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			new Sulfuras().age(item);
		} else {
			new DefaultAgeItem().age(item);
		}
	}

	public static void incrementQualityUnlessGreaterThan50(Item item) {
		if (item.quality >= 50) {
			return;
		}
		item.quality++;
	}
}