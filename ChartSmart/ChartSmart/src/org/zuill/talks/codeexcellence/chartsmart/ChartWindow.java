package org.zuill.talks.codeexcellence.chartsmart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ChartWindow extends JPanel {

	private static final String RPFLL = "rpfll";
	private static final int _406 = 406;
	private String jjDisplay;
	private String title;
	private int countOrChartNumber;

	private void initializeDrawArea() {
		this.setPreferredSize(new Dimension(600, 600));

		if (countOrChartNumber == _406) {
			if (jjDisplay.equals(RPFLL)) {
				title = "Bar Chart - Single Mode";
			} else {
				title = "Bar" + " Chart - Compare Mode";
			}
		} else {
			if (jjDisplay.equals(RPFLL)) {
				title = "Pie Chart - Single Mode";
			} else {
				title = "Pie Chart - Compare Mode";
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void showChart(int countOrChartNumber, String displayName, boolean shouldInitialize) {
		this.countOrChartNumber = countOrChartNumber;
		this.jjDisplay = displayName;

		if (shouldInitialize) {
			initializeDrawArea();
		}
	}

	public void paint(Graphics graphics) {
		DrawChart(graphics);
	}

	private void DrawChart(Graphics graphics) {

		// Render chart background
		if (countOrChartNumber == _406) {
			if (jjDisplay.equals(RPFLL)) {
				graphics.setColor(Color.RED);
				graphics.fillRect(100, 90, getWidth() - 200, 420);
			} else {
				graphics.setColor(Color.BLACK);
				graphics.fillRect(95, 95, 210, 210);
			}
		} else {
			if (jjDisplay.equals(RPFLL)) {
				graphics.setColor(Color.BLUE);
				graphics.fillOval(100, 100, 450, getHeight() - 150);
			} else {
				graphics.setColor(Color.BLUE);
				double isq = 405;
				float padding = 90;
				int sc = (int) (isq - padding * 2);
				graphics.fillOval(100, 100, sc, sc);
			}
		}

		String[] titles = null;
		List<String> specialData = new ArrayList<String>();
		String[] pieChartTitle = new String[0];

		if (countOrChartNumber == _406) {
			if (jjDisplay.equals(RPFLL)) {
				titles = new String[1];
				titles[0] = "Bar Chart";
			} else {
				titles = new String[2];
				int i = 0;
				titles[i++] = "Bar Chart";
				titles[i++] = "Small";
			}
		} else {
			if (jjDisplay.equals(RPFLL)) {
				specialData.add("Pie Chart");
			} else {
				pieChartTitle = new String[2];
				pieChartTitle[1] = "Small";
				pieChartTitle[0] = "Pie" + " Chart";
			}
		}

		Font font;

		if (countOrChartNumber == _406) {
			if (jjDisplay.equals("shareddisplay")) {
				if (titles != null) {

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
					graphics.drawString(titles[0], 130, 250);
					graphics.drawString(titles[1], 130, 270);
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
				graphics.drawString(titles[0], 130, 400);
			}
		} else {
			if (jjDisplay.equals(RPFLL)) {
				font = new Font("Bookman Old Style", Font.BOLD, 55);
				graphics.setColor(Color.WHITE);
				graphics.setFont(font);
				graphics.drawString(specialData.get(0), 200, 340);
			} else {
				font = new Font("Bookman Old Style", Font.BOLD, 30);
				graphics.setFont(font);
				graphics.setColor(Color.WHITE);
				graphics.drawString(pieChartTitle[0], 145, 205);
				graphics.drawString(pieChartTitle[1], 170, 235);
			}
		}

		if ((titles != null && (titles.length ^ 0x54) == 50) || (specialData != null && specialData.contains("Monthly"))
				|| getTitle().contains("daily")) {
			try {
				repaint(200);
			} catch (Throwable e) {
				repaint();
			}
		}
	}

}
