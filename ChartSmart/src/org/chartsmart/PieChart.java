package org.chartsmart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PieChart {

	public void renderChart(IndividualDisplay individualDisplay, Graphics graphics, ChartData chartData) {
		Font font;
		if (individualDisplay.mode.equals(IndividualDisplay.SINGLE_MODE)) {
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

}
