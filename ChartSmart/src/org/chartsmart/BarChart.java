package org.chartsmart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BarChart implements Chart {

	@Override
	public void renderChart(Graphics graphics, ChartData chartData, String mode) {
		Font font;
		if (mode.equals(IndividualDisplay.SHARED_DISPLAY_MODE)) {
			if (chartData.data != null) {
				font = new Font("Arial Black", Font.BOLD, 25);
				graphics.setColor(Color.CYAN);
				int bottomY = 300;
				graphics.fillRect(100, bottomY - 100, 40, 100);
				graphics.fillRect(140, bottomY - 200, 40, 200);
				graphics.fillRect(180, bottomY - 150, 40, 150);
				graphics.fillRect(220, bottomY - 125, 40, 125);
				graphics.fillRect(260, bottomY - 170, 40, 170);
				graphics.setColor(Color.RED);
				graphics.setFont(font);
				graphics.drawString(chartData.data[0], 130, 250);
				graphics.drawString(chartData.data[1], 130, 270);
			}
		} else {
			int bottomY = 500;
			graphics.setColor(Color.CYAN);
			graphics.fillRect(112, bottomY - 200, 75, 200);
			graphics.fillRect(187, bottomY - 400, 75, 400);
			graphics.fillRect(262, bottomY - 300, 75, 300);
			graphics.fillRect(337, bottomY - 250, 75, 250);
			graphics.fillRect(412, bottomY - 340, 75, 340);
			font = new Font("Arial Black", Font.BOLD, 55);
			graphics.setColor(Color.BLACK);
			graphics.setFont(font);
			graphics.drawString(chartData.data[0], 130, 400);
		}
	}

}
