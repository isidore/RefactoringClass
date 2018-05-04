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
			updateQualityToo(items[i]);

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
			incrementQualityUnlessGreaterThan50(item);
		} else if (item.name.equals(BACKSTAGE_PASSES)) {
			item.quality = 0;
		} else {
			decrementQualityUnlessSulfuras(item);
		}
	}

	public void decrementQualityUnlessSulfuras(Item item) {
		if (item.quality > 0) {
			if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			} else {
				item.quality--;
			}
		}
	}

	public void updateQualityToo(Item item) {
		if (item.name.equals(AGED_BRIE)) {
			incrementQualityUnlessGreaterThan50(item);

		} else if (item.name.equals(BACKSTAGE_PASSES)) {
			incrementQualityUnlessGreaterThan50(item);

			incrementQualityForBackstagePasses(item);

		} else {
			decrementQualityUnlessSulfuras(item);
		}
	}

	public void incrementQualityForBackstagePasses(Item item) {
		if (!item.name.equals(BACKSTAGE_PASSES)) {
			return;
		}

		if (item.sellIn < 11) {
			incrementQualityUnlessGreaterThan50(item);
		}

		if (item.sellIn < 6) {
			incrementQualityUnlessGreaterThan50(item);
		}
	}

	public void incrementQualityUnlessGreaterThan50(Item item) {
		if (item.quality >= 50) {
			return;
		}
		item.quality++;
	}
}