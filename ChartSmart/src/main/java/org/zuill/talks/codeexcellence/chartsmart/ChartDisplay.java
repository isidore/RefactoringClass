package org.zuill.talks.codeexcellence.chartsmart;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

public class ChartDisplay extends JPanel {

	private static final int BAR_CHART = 406;
	private static final String SINGLE_MODE_CHART = "rpfll";
	private String dimension;
	private String title;

	private int chart;

	private void initializeDrawArea() {
		this.setPreferredSize(new Dimension(600, 600));

		if (chart == BAR_CHART) {
			if (dimension.equals(SINGLE_MODE_CHART)) {
				title = "Bar Chart - Single Mode";
			} else {
				title = "Bar" + " Chart - Compare Mode";
			}
		} else {
			if (dimension.equals(SINGLE_MODE_CHART)) {
				title = "Pie Chart - Single Mode";
			} else {
				title = "Pie Chart - Compare Mode";
			}
		}
	}

	public ChartDisplay() {
	}

	public String getTitle() {
		return title;
	}

	public void showChart(int chart, String dimension, boolean shouldInitialize) {
		this.chart = chart;
		this.dimension = dimension;

		if (shouldInitialize) {
			initializeDrawArea();
		}
	}

	@Override
	public Set<AWTKeyStroke> getFocusTraversalKeys(int id) {
		return super.getFocusTraversalKeys(id);
	}

	public void paint(Graphics graphics) {
		renderDisplay(graphics);
	}

	private void renderDisplay(Graphics graphics) {

		// Render chart background
		if (chart == BAR_CHART) {
			if (dimension.equals(SINGLE_MODE_CHART)) {
				graphics.setColor(Color.RED);
				graphics.fillRect(100, 90, getWidth() - 200, 420);
			} else {
				graphics.setColor(Color.BLACK);
				graphics.fillRect(95, 95, 210, 210);
			}
		} else {
			if (dimension.equals(SINGLE_MODE_CHART)) {
				graphics.setColor(Color.BLUE);
				graphics.fillOval(100, 100, 450, getHeight() - 150);
			} else {
				graphics.setColor(Color.BLUE);
				double someSize = 405;
				float padding = 90;
				int scale = (int) (someSize - padding * 2);
				graphics.fillOval(100, 100, scale, scale);
			}
		}

		String[] data = null;
		List<String> specialData = new ArrayList<String>();
		String[] pieChartNames = new String[0];

		if (chart == BAR_CHART) {
			if (dimension.equals(SINGLE_MODE_CHART)) {
				data = new String[1];
				data[0] = "Bar Chart";
			} else {
				data = new String[2];
				int i = 0;
				data[i++] = "Bar Chart";
				data[i++] = "Small";
			}
		} else {
			if (dimension.equals(SINGLE_MODE_CHART)) {
				specialData.add("Pie Chart");
			} else {
				pieChartNames = new String[2];
				pieChartNames[1] = "Small";
				pieChartNames[0] = "Pie" + " Chart";
			}
		}

		Font font;

		if (chart == BAR_CHART) {
			if (dimension.equals("shareddisplay")) {
				if (data != null) {
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
					graphics.drawString(data[0], 130, 250);
					graphics.drawString(data[1], 130, 270);
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
				graphics.drawString(data[0], 130, 400);
			}
		} else {
			if (dimension.equals(SINGLE_MODE_CHART)) {
				font = new Font("Bookman Old Style", Font.BOLD, 55);
				graphics.setColor(Color.WHITE);
				graphics.setFont(font);
				graphics.drawString(specialData.get(0), 200, 340);
			} else {
				font = new Font("Bookman Old Style", Font.BOLD, 30);
				graphics.setFont(font);
				graphics.setColor(Color.WHITE);
				graphics.drawString(pieChartNames[0], 145, 205);
				graphics.drawString(pieChartNames[1], 170, 235);
			}
		}

		if ((data != null && (data.length ^ 0x54) == 50) || (specialData != null && specialData.contains("Monthly"))
				|| getTitle().contains("daily")) {
			try {
				repaint(200);
			} catch (Throwable e) {
				repaint();
			}
		}
	}
}