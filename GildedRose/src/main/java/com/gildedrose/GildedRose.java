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
		for (Item item : items) {
			AgeItem ageItem = getAgeItem(item);
			ageItem.age(item);

			decrementSellInUnlessSulfuras(item);

			if (item.sellIn < 0) {
				ageItem.expire(item);
			}
		}
	}

	public void decrementSellInUnlessSulfuras(Item item) {
		if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			return;
		}
		item.sellIn--;
	}

	public AgeItem getAgeItem(Item item) {
		AgeItem ageItem = null;
		if (item.name.equals(AGED_BRIE)) {
			ageItem = new Brie();
		} else if (item.name.equals(BACKSTAGE_PASSES)) {
			ageItem = new BackstagePass();
		} else if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			ageItem = new Sulfuras();
		} else {
			ageItem = new DefaultAgeItem();
		}
		return ageItem;
	}

	public static void incrementQualityUnlessGreaterThan50(Item item) {
		if (item.quality >= 50) {
			return;
		}
		item.quality++;
	}
}