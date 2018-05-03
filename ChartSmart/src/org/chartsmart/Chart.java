package org.chartsmart;

import java.awt.Graphics;

public interface Chart {

	void renderChart(Graphics graphics, ChartData chartData, String mode);

	void renderBackground(Graphics graphics, String mode, int width, int height);

}