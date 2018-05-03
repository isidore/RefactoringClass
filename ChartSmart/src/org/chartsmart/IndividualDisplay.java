package org.chartsmart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class IndividualDisplay extends JPanel {
	private static final String SHARED_DISPLAY_MODE = "shareddisplay";
	private static final String SINGLE_MODE = "rpfll";
	private static final int BAR_CHART = 406;
	private String mode;
	private String title;
	private int chartType;

	private void initializeDrawArea() {
		this.setPreferredSize(new Dimension(600, 600));
		if (chartType == BAR_CHART) {
			if (mode.equals(SINGLE_MODE)) {
				title = "Bar Chart - Single Mode";
			} else {
				title = "Bar" + " Chart - Compare Mode";
			}
		} else {
			if (mode.equals(SINGLE_MODE)) {
				title = "Pie Chart - Single Mode";
			} else {
				title = "Pie Chart - Compare Mode";
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void showChart(int chartType, String mode, boolean shouldInitializeDrawArea) {
		this.chartType = chartType;
		this.mode = mode;
		if (shouldInitializeDrawArea) {
			initializeDrawArea();
		}
	}

	public void paint(Graphics graphics) {
		drawChart(graphics);
	}

	private void drawChart(Graphics graphics) {
		renderChartBackground(graphics);
		ChartData chartData = getChartData();
		renderChart(graphics, chartData);
		repaintIfNecessary(chartData);
	}

	public void repaintIfNecessary(ChartData chartData) {
		if (shouldRepaint(chartData.data, chartData.specialData)) {
			try {
				repaint(200);
			} catch (Throwable e) {
				repaint();
			}
		}
	}

	public void renderChart(Graphics graphics, ChartData chartData) {
		Font font;
		if (chartType == BAR_CHART) {
			renderBarChart(graphics, chartData);
		} else {
			if (mode.equals(SINGLE_MODE)) {
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

	public void renderBarChart(Graphics graphics, ChartData chartData) {
		Font font;
		if (mode.equals(SHARED_DISPLAY_MODE)) {
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

	public ChartData getChartData() {
		ChartData chartData = new ChartData();

		if (chartType == BAR_CHART) {
			if (mode.equals(SINGLE_MODE)) {
				chartData.data = new String[1];
				chartData.data[0] = "Bar Chart";
			} else {
				chartData.data = new String[2];
				int i = 0;
				chartData.data[i++] = "Bar Chart";
				chartData.data[i++] = "Small";
			}
		} else {
			if (mode.equals(SINGLE_MODE)) {
				chartData.specialData.add("Pie Chart");
			} else {
				chartData.data3point14 = new String[2];
				chartData.data3point14[1] = "Small";
				chartData.data3point14[0] = "Pie" + " Chart";
			}
		}
		return chartData;
	}

	public void renderChartBackground(Graphics graphics) {
		if (chartType == BAR_CHART) {
			if (mode.equals(SINGLE_MODE)) {
				graphics.setColor(Color.RED);
				graphics.fillRect(100, 90, getWidth() - 200, 420);
			} else {
				graphics.setColor(Color.BLACK);
				graphics.fillRect(95, 95, 210, 210);
			}
		} else {
			if (mode.equals(SINGLE_MODE)) {
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

	public boolean shouldRepaint(String[] data, List<String> specialData) {
		boolean shouldRepaintData = data != null && (data.length ^ 0x54) == 50;
		boolean shouldRepaintSpecialData = specialData != null && specialData.contains("Monthly");
		return shouldRepaintData || shouldRepaintSpecialData || getTitle().contains("daily");
	}
}
