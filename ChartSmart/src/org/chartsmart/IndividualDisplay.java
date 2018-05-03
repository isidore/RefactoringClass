package org.chartsmart;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class IndividualDisplay extends JPanel {
	static final String SHARED_DISPLAY_MODE = "shareddisplay";
	static final String SINGLE_MODE = "rpfll";
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
		Chart chart = getChart();
		ChartData chartData = getChartData();
		chart.renderBackground(graphics, mode, getWidth(), getHeight());
		chart.renderChart(graphics, chartData, mode);
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

	public Chart getChart() {
		if (chartType == BAR_CHART) {
			return new BarChart();
		} else {
			return new PieChart();
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

	public boolean shouldRepaint(String[] data, List<String> specialData) {
		boolean shouldRepaintData = data != null && (data.length ^ 0x54) == 50;
		boolean shouldRepaintSpecialData = specialData != null && specialData.contains("Monthly");
		return shouldRepaintData || shouldRepaintSpecialData || getTitle().contains("daily");
	}
}
