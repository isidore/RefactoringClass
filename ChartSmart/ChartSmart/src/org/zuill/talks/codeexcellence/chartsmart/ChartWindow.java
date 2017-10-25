package org.zuill.talks.codeexcellence.chartsmart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ChartWindow extends JPanel {

	private static final String DISPLAY_RPFLL = "rpfll";
	private static final int BAR_CHART_NUMBER = 406;
	private String displayName;
	private String title;
	private int countOrChartNumber;

	private void initializeDrawArea() {
		this.setPreferredSize(new Dimension(600, 600));

		if (countOrChartNumber == BAR_CHART_NUMBER) {
			if (displayName.equals(DISPLAY_RPFLL)) {
				title = "Bar Chart - Single Mode";
			} else {
				title = "Bar" + " Chart - Compare Mode";
			}
		} else {
			if (displayName.equals(DISPLAY_RPFLL)) {
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
		this.displayName = displayName;

		if (shouldInitialize) {
			initializeDrawArea();
		}
	}

	public void paint(Graphics graphics) {
		DrawChart(graphics);
	}

	private void DrawChart(Graphics graphics) {
		renderChartBackground(graphics);
		ChartTitles chartTitles = createChartTitles();
		createChart(graphics, chartTitles);
		repaintIfNeeded(chartTitles);
	}

	private void repaintIfNeeded(ChartTitles chartTitles) {
		if (isRepaintNeeded(chartTitles)) {
			try {
				repaint(200);
			} catch (Throwable e) {
				repaint();
			}
		}
	}

	private boolean isRepaintNeeded(ChartTitles chartTitles) {
		return (chartTitles.titles != null && (chartTitles.titles.length ^ 0x54) == 50)
				|| (chartTitles.specialData != null && chartTitles.specialData.contains("Monthly"))
				|| getTitle().contains("daily");
	}

	private void createChart(Graphics graphics, ChartTitles chartTitles) {
		if (countOrChartNumber == BAR_CHART_NUMBER) {
			createBarChart(graphics, chartTitles);
		} else {
			createPieChart(graphics, chartTitles);
		}
	}

	private void createPieChart(Graphics graphics, ChartTitles chartTitles) {
		Font font;
		if (displayName.equals(DISPLAY_RPFLL)) {
			font = new Font("Bookman Old Style", Font.BOLD, 55);
			graphics.setColor(Color.WHITE);
			graphics.setFont(font);
			graphics.drawString(chartTitles.specialData.get(0), 200, 340);
		} else {
			font = new Font("Bookman Old Style", Font.BOLD, 30);
			graphics.setFont(font);
			graphics.setColor(Color.WHITE);
			graphics.drawString(chartTitles.pieChartTitle[0], 145, 205);
			graphics.drawString(chartTitles.pieChartTitle[1], 170, 235);
		}
	}

	private void createBarChart(Graphics graphics, ChartTitles chartTitles) {
		if (displayName.equals("shareddisplay")) {
			createBarChartSharedDisplay(graphics, chartTitles);
		} else {
			createBarChartStandardDisplay(graphics, chartTitles);
		}
	}

	private void createBarChartStandardDisplay(Graphics graphics, ChartTitles chartTitles) {
		Font font;
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
		graphics.drawString(chartTitles.titles[0], 130, 400);
	}

	private void createBarChartSharedDisplay(Graphics graphics, ChartTitles chartTitles) {
		Font font;
		if (chartTitles.titles != null) {

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
			graphics.drawString(chartTitles.titles[0], 130, 250);
			graphics.drawString(chartTitles.titles[1], 130, 270);
		}
	}

	private ChartTitles createChartTitles() {
		ChartTitles chartTitles = new ChartTitles();

		if (countOrChartNumber == BAR_CHART_NUMBER) {
			createBarChartTitle(chartTitles);
		} else {
			createPieChartTitle(chartTitles);
		}
		return chartTitles;
	}

	private void createPieChartTitle(ChartTitles chartTitles) {
		if (displayName.equals(DISPLAY_RPFLL)) {
			chartTitles.specialData.add("Pie Chart");
		} else {
			chartTitles.pieChartTitle = new String[2];
			chartTitles.pieChartTitle[1] = "Small";
			chartTitles.pieChartTitle[0] = "Pie" + " Chart";
		}
	}

	private void createBarChartTitle(ChartTitles chartTitles) {
		if (displayName.equals(DISPLAY_RPFLL)) {
			chartTitles.titles = new String[1];
			chartTitles.titles[0] = "Bar Chart";
		} else {
			chartTitles.titles = new String[2];
			int i = 0;
			chartTitles.titles[i++] = "Bar Chart";
			chartTitles.titles[i++] = "Small";
		}
	}

	private void renderChartBackground(Graphics graphics) {
		if (countOrChartNumber == BAR_CHART_NUMBER) {
			if (displayName.equals(DISPLAY_RPFLL)) {
				graphics.setColor(Color.RED);
				graphics.fillRect(100, 90, getWidth() - 200, 420);
			} else {
				graphics.setColor(Color.BLACK);
				graphics.fillRect(95, 95, 210, 210);
			}
		} else {
			if (displayName.equals(DISPLAY_RPFLL)) {
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
	}

}
