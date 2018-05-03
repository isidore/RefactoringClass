package org.chartsmart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PieChart implements Chart {

	public void renderChart(Graphics graphics, ChartData chartData, String mode) {
		Font font;
		if (mode.equals(IndividualDisplay.SINGLE_MODE)) {
			font = new Font("Bookman Old Style", Font.BOLD, 55);
			graphics.setColor(Color.WHITE);
			graphics.setFont(font);
			graphics.drawString(chartData.specialData.get(0), 200, 340);
		} else {
			font = new Font("Bookman Old Style", Font.BOLD, 30);
			graphics.setFont(font);
			graphics.setColor(Color.WHITE);
			graphics.drawString(chartData.data3point14[0], 145, 205);
			graphics.drawString(chartData.data3point14[1], 170, 235);
		}
	}

	public void renderBackground(Graphics graphics, String mode, int height) {
		if (mode.equals(IndividualDisplay.SINGLE_MODE)) {
			graphics.setColor(Color.BLUE);
			graphics.fillOval(100, 100, 450, height - 150);
		} else {
			graphics.setColor(Color.BLUE);
			double isq = 405;
			float padding = 90;
			int sc = (int) (isq - padding * 2);
			graphics.fillOval(100, 100, sc, sc);
		}
	}

}
