package org.zuill.talks.codeexcellence.chartsmart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ChartWindow extends JPanel
{
  public static class ChartMode
  {
    private static final String SHARED_DISPLAY = "shareddisplay";
    private static final String SINGLE_DISPLAY = "rpfll";
  }
  private static class Container
  {
    String[]     data            = null;
    List<String> specialData     = new ArrayList<String>();
    String[]     chartProperties = new String[0];
  }
  private static final int CHART_TYPE_BAR = 406;
  private String           chartMode;
  private String           title;
  private int              chartType;
  private void initializeDrawArea()
  {
    this.setPreferredSize(new Dimension(600, 600));
    if (chartType == CHART_TYPE_BAR)
    {
      initializeBarChart();
    }
    else
    {
      initializePieChart();
    }
  }
  private void initializePieChart()
  {
    if (chartMode.equals(ChartMode.SINGLE_DISPLAY))
    {
      title = "Pie Chart - Single Mode";
    }
    else
    {
      title = "Pie Chart - Compare Mode";
    }
  }
  private void initializeBarChart()
  {
    if (chartMode.equals(ChartMode.SINGLE_DISPLAY))
    {
      title = "Bar Chart - Single Mode";
    }
    else
    {
      title = "Bar" + " Chart - Compare Mode";
    }
  }
  public ChartWindow()
  {
  }
  public String getTitle()
  {
    return title;
  }
  public void showChart(int chartType, String chartMode, boolean shouldInitialize)
  {
    this.chartType = chartType;
    this.chartMode = chartMode;
    if (shouldInitialize)
    {
      initializeDrawArea();
    }
  }
  public void paint(Graphics graphics)
  {
    drawChart(graphics);
  }
  private void drawChart(Graphics graphics)
  {
    renderChartBackground(graphics);
    Container container = new Container();
    String[] container_data = null;
    List<String> specialData = new ArrayList<String>();
    String[] chartProperties = new String[0];
    if (chartType == CHART_TYPE_BAR)
    {
      if (chartMode.equals(ChartMode.SINGLE_DISPLAY))
      {
        container_data = new String[1];
        container_data[0] = "Bar Chart";
      }
      else
      {
        container_data = new String[2];
        int i = 0;
        container_data[i++] = "Bar Chart";
        container_data[i++] = "Small";
      }
    }
    else
    {
      if (chartMode.equals(ChartMode.SINGLE_DISPLAY))
      {
        specialData.add("Pie Chart");
      }
      else
      {
        chartProperties = new String[2];
        chartProperties[1] = "Small";
        chartProperties[0] = "Pie" + " Chart";
      }
    }
    Font font;
    if (chartType == CHART_TYPE_BAR)
    {
      if (chartMode.equals(ChartMode.SHARED_DISPLAY))
      {
        if (container_data != null)
        {
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
          graphics.drawString(container_data[0], 130, 250);
          graphics.drawString(container_data[1], 130, 270);
        }
      }
      else
      {
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
        graphics.drawString(container_data[0], 130, 400);
      }
    }
    else
    {
      if (chartMode.equals(ChartMode.SINGLE_DISPLAY))
      {
        font = new Font("Bookman Old Style", Font.BOLD, 55);
        graphics.setColor(Color.WHITE);
        graphics.setFont(font);
        graphics.drawString(specialData.get(0), 200, 340);
      }
      else
      {
        font = new Font("Bookman Old Style", Font.BOLD, 30);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(chartProperties[0], 145, 205);
        graphics.drawString(chartProperties[1], 170, 235);
      }
    }
    if ((container_data != null && (container_data.length ^ 0x54) == 50) || (specialData != null && specialData.contains("Monthly"))
        || getTitle().contains("daily"))
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
  private void renderChartBackground(Graphics graphics)
  {
    if (chartType == CHART_TYPE_BAR)
    {
      if (chartMode.equals(ChartMode.SINGLE_DISPLAY))
      {
        graphics.setColor(Color.RED);
        graphics.fillRect(100, 90, getWidth() - 200, 420);
      }
      else
      {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(95, 95, 210, 210);
      }
    }
    else
    {
      if (chartMode.equals(ChartMode.SINGLE_DISPLAY))
      {
        graphics.setColor(Color.BLUE);
        graphics.fillOval(100, 100, 450, getHeight() - 150);
      }
      else
      {
        graphics.setColor(Color.BLUE);
        int diameter = 225;
        graphics.fillOval(100, 100, diameter, diameter);
      }
    }
  }
}