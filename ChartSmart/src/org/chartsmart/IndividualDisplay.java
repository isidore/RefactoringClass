package org.chartsmart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class IndividualDisplay extends JPanel {
	static final String SHARED_DISPLAY_MODE = "shareddisplay";
	private static final String SINGLE_MODE = "rpfll";
	private static final int BAR_CHART = 406;
	String mode;
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
		if (chartType == BAR_CHART) {
			BarChart barChart = new BarChart();
			barChart.renderChart(this, graphics, chartData);
		} else {
			PieChart pieChart = new PieChart();
			renderPieChart(pieChart, graphics, chartData);
		}
	}

	public void renderPieChart(PieChart pieChart, Graphics graphics, ChartData chartData) {
		Font font;
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

	public ChartData getChartData() {

		if (chartType == BAR_CHART) {
			return getBarChartData();
		} else {
			return getPieChartData();
		}
	}

	public ChartData getPieChartData() {
		ChartData chartData = new ChartData();
		if (mode.equals(SINGLE_MODE)) {
			chartData.specialData.add("Pie Chart");
		} else {
			chartData.data3point14 = new String[2];
			chartData.data3point14[1] = "Small";
			chartData.data3point14[0] = "Pie" + " Chart";
		}
		return chartData;
	}

	public ChartData getBarChartData() {
		ChartData chartData = new ChartData();
		if (mode.equals(SINGLE_MODE)) {
			chartData.data = new String[1];
			chartData.data[0] = "Bar Chart";
		} else {
			chartData.data = new String[2];
			int i = 0;
			chartData.data[i++] = "Bar Chart";
			chartData.data[i++] = "Small";
		}
		return chartData;
	}

	public void renderChartBackground(Graphics graphics) {
		if (chartType == BAR_CHART) {
			renderBarChartBackground(graphics);
		} else {
			renderPieChartBackground(graphics);
		}
	}

	public void renderPieChartBackground(Graphics graphics) {
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

	public void renderBarChartBackground(Graphics graphics) {
		if (mode.equals(SINGLE_MODE)) {
			graphics.setColor(Color.RED);
			graphics.fillRect(100, 90, getWidth() - 200, 420);
		} else {
			graphics.setColor(Color.BLACK);
			graphics.fillRect(95, 95, 210, 210);
		}
	}

	public boolean shouldRepaint(String[] data, List<String> specialData) {
		boolean shouldRepaintData = data != null && (data.length ^ 0x54) == 50;
		boolean shouldRepaintSpecialData = specialData != null && specialData.contains("Monthly");
		return shouldRepaintData || shouldRepaintSpecialData || getTitle().contains("daily");
	}
}
