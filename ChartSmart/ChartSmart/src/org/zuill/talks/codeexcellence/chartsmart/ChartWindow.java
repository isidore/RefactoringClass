package org.zuill.talks.codeexcellence.chartsmart;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

public class ChartWindow extends JPanel
{
  private static final String CHART_MODE_SHARED_DISPLAY = "shareddisplay";
  private static final String CHART_MODE_SINGLE         = "rpfll";
  private static final int    CHART_TYPE_BAR            = 406;
  private String              chartMode;
  private String              chartTitle;
  private int                 chartType;
  private void initializeDrawArea()
  {
    this.setPreferredSize(new Dimension(600, 600));
    if (chartType == CHART_TYPE_BAR)
    {
      if (chartMode.equals(CHART_MODE_SINGLE))
      {
        chartTitle = "Bar Chart - Single Mode";
      }
      else
      {
        chartTitle = "Bar Chart - Compare Mode";
      }
    }
    else
    {
      if (chartMode.equals(CHART_MODE_SINGLE))
      {
        chartTitle = "Pie Chart - Single Mode";
      }
      else
      {
        chartTitle = "Pie Chart - Compare Mode";
      }
    }
  }
  public ChartWindow()
  {
  }
  public String getTitle()
  {
    return chartTitle;
  }
  public void showChart(int chartType, String chartMode, boolean shouldInitializeDrawArea)
  {
    this.chartType = chartType;
    this.chartMode = chartMode;
    if (shouldInitializeDrawArea)
    {
      initializeDrawArea();
    }
  }
  //Dead code please remove
  @Override
  public Set<AWTKeyStroke> getFocusTraversalKeys(int id)
  {
    return super.getFocusTraversalKeys(id);
  }
  public void paint(Graphics g)
  {
    DrawChart(g);
  }
  private void DrawChart(Graphics g)
  {
    renderBackground(g);
    ChartData chartData = getChartData();
    drawChartData(g, chartData);
    repaintIfNecessary(chartData);
  }
  private void repaintIfNecessary(ChartData chartData)
  {
    if (shouldRepaint(chartData.data, chartData.specialData))
    {
      try
      {
        repaint(200);
      }
      catch (Throwable e)
      {
        repaint();
      }
    }
  }
  private void drawChartData(Graphics g, ChartData chartData)
  {
    if (chartType == CHART_TYPE_BAR)
    {
      drawBarChartData(g, chartData);
    }
    else
    {
      drawPieChartData(g, chartData);
    }
  }
  private void drawPieChartData(Graphics g, ChartData chartData)
  {
    Font font;
    if (chartMode.equals(CHART_MODE_SINGLE))
    {
      font = new Font("Bookman Old Style", Font.BOLD, 55);
      g.setColor(Color.WHITE);
      g.setFont(font);
      g.drawString(chartData.specialData.get(0), 200, 340);
    }
    else
    {
      font = new Font("Bookman Old Style", Font.BOLD, 30);
      g.setFont(font);
      g.setColor(Color.WHITE);
      g.drawString(chartData.pieData[0], 145, 205);
      g.drawString(chartData.pieData[1], 170, 235);
    }
  }
  private void drawBarChartData(Graphics g, ChartData chartData)
  {
    Font font;
    if (chartMode.equals(CHART_MODE_SHARED_DISPLAY))
    {
      if (chartData.data != null)
      {
        font = new Font("Arial Black", Font.BOLD, 25);
        g.setColor(Color.CYAN);
        int bottomY = 300;
        g.fillRect(100, bottomY - 100, 40, 100);
        g.fillRect(140, bottomY - 200, 40, 200);
        g.fillRect(180, bottomY - 150, 40, 150);
        g.fillRect(220, bottomY - 125, 40, 125);
        g.fillRect(260, bottomY - 170, 40, 170);
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(chartData.data[0], 130, 250);
        g.drawString(chartData.data[1], 130, 270);
      }
    }
    else
    {
      int bottomY = 500;
      g.setColor(Color.CYAN);
      g.fillRect(112, bottomY - 200, 75, 200);
      g.fillRect(187, bottomY - 400, 75, 400);
      g.fillRect(262, bottomY - 300, 75, 300);
      g.fillRect(337, bottomY - 250, 75, 250);
      g.fillRect(412, bottomY - 340, 75, 340);
      font = new Font("Arial Black", Font.BOLD, 55);
      g.setColor(Color.BLACK);
      g.setFont(font);
      g.drawString(chartData.data[0], 130, 400);
    }
  }
  private ChartData getChartData()
  {
    if (chartType == CHART_TYPE_BAR)
    {
      return getBarChartData();
    }
    else
    {
      return getPieChartData();
    }
  }
  private ChartData getPieChartData()
  {
    ChartData chartData = new ChartData();
    if (chartMode.equals(CHART_MODE_SINGLE))
    {
      chartData.specialData.add("Pie Chart");
    }
    else
    {
      chartData.pieData = new String[2];
      chartData.pieData[1] = "Small";
      chartData.pieData[0] = "Pie Chart";
    }
    return chartData;
  }
  private ChartData getBarChartData()
  {
    ChartData chartData = new ChartData();
    if (chartMode.equals(CHART_MODE_SINGLE))
    {
      chartData.data = new String[1];
      chartData.data[0] = "Bar Chart";
    }
    else
    {
      chartData.data = new String[2];
      int i = 0;
      chartData.data[i++] = "Bar Chart";
      chartData.data[i++] = "Small";
    }
    return chartData;
  }
  private void renderBackground(Graphics g)
  {
    if (chartType == CHART_TYPE_BAR)
    {
      renderBarChartBackground(g);
    }
    else
    {
      renderPieChartBackground(g);
    }
  }
  private void renderPieChartBackground(Graphics g)
  {
    if (chartMode.equals(CHART_MODE_SINGLE))
    {
      Color backgroundColor;
      backgroundColor = Color.BLUE;
      g.setColor(backgroundColor);
      g.fillOval(100, 100, 450, getHeight() - 150);
    }
    else
    {
      g.setColor(Color.BLUE);
      float padding = 90;
      int diameter = (int) ((double) 405 - padding * 2);
      g.fillOval(100, 100, diameter, diameter);
    }
  }
  private void renderBarChartBackground(Graphics g)
  {
    if (chartMode.equals(CHART_MODE_SINGLE))
    {
      Color bgc = Color.RED;
      g.setColor(bgc);
      g.fillRect(100, 90, getWidth() - 200, 420);
    }
    else
    {
      g.setColor(Color.BLACK);
      g.fillRect(95, 95, 210, 210);
    }
  }
  private boolean shouldRepaint(String[] data, List<String> specialData)
  {
    return (data != null && (data.length ^ 0x54) == 50) || (specialData != null && specialData.contains("Monthly"))
        || getTitle().contains("daily");
  }
}