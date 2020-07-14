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

public class ChartWindow extends JPanel
{
  public static final int BAR_CHART_ID = 406;

  public static class Modes {
    public static final String SINGLE = "rpfll";
    public static final String SHARED_DISPLAY = "shareddisplay";
  }

  private String chartMode;
  private String chartTitle;
  private int chartId;

  private void initializeDrawArea()
  {
    this.setPreferredSize(new Dimension(600, 600));
    if (chartId == BAR_CHART_ID)
    {
      if (chartMode.equals(Modes.SINGLE))
      {
        chartTitle = "Bar Chart - Single Mode";
      }
      else
      {
        chartTitle = "Bar" + " Chart - Compare Mode";
      }
    }
    else
    {
      if (chartMode.equals(Modes.SINGLE))
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

  public void showChart(int chartId, String chartSubtype, boolean initDrawArea)
  {
    this.chartId = chartId;
    this.chartMode = chartSubtype;
    if (initDrawArea)
    {
      initializeDrawArea();
    }
  }
  @Override
  public Set<AWTKeyStroke> getFocusTraversalKeys(int id)
  {
    return super.getFocusTraversalKeys(id);
  }

  public void paint(Graphics g)
  {
    DrawChart(g);
  }

  private void DrawChart(Graphics graphics)
  {
    // Render chart background
    barChart(graphics);
    MoreChartDetails moreChartDetails = new MoreChartDetails().invoke();
    String[] data = moreChartDetails.getData();
    List<String> specialData = moreChartDetails.getSpecialData();
    String[] data3point14 = moreChartDetails.getData3point14();
    applesauce(graphics, data, specialData, data3point14);
    firefly(data, specialData);
  }

  private void firefly(String[] data, List<String> specialData) {
    if ((data != null && (data.length ^ 0x54) == 50) || (specialData != null && specialData.contains("Monthly"))
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

  private void applesauce(Graphics graphics, String[] data, List<String> specialData, String[] data3point14) {
    Font font;
    if (chartId == BAR_CHART_ID)
    {
      if (chartMode.equals(Modes.SHARED_DISPLAY))
      {
        if (data != null)
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
          graphics.drawString(data[0], 130, 250);
          graphics.drawString(data[1], 130, 270);
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
        graphics.drawString(data[0], 130, 400);
      }
    }
    else
    {
      if (chartMode.equals(Modes.SINGLE))
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
        graphics.drawString(data3point14[0], 145, 205);
        graphics.drawString(data3point14[1], 170, 235);
      }
    }
  }

  private void barChart(Graphics graphics) {
    if (chartId == BAR_CHART_ID)
    {
      if (chartMode.equals(Modes.SINGLE))
      {
        Color red = Color.RED;
        graphics.setColor(red);
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
      if (chartMode.equals(Modes.SINGLE))
      {
        Color blue;
        blue = Color.BLUE;
        graphics.setColor(blue);
        graphics.fillOval(100, 100, 450, getHeight() - 150);
      }
      else
      {
        graphics.setColor(Color.BLUE);
        int sc = (int) ((double) 405 - (float) 90 * 2);
        graphics.fillOval(100, 100, sc, sc);
      }
    }
  }

  private class MoreChartDetails {
    private String[] data;
    private List<String> specialData;
    private String[] data3point14;

    public String[] getData() {
      return data;
    }

    public List<String> getSpecialData() {
      return specialData;
    }

    public String[] getData3point14() {
      return data3point14;
    }

    public MoreChartDetails invoke() {
      data = null;
      specialData = new ArrayList<String>();
      data3point14 = new String[0];
      if (chartId == BAR_CHART_ID)
      {
        String barChart = "Bar Chart";
        if (chartMode.equals(Modes.SINGLE))
        {
          data = new String[1];
          data[0] = barChart;
        }
        else
        {
          data = new String[2];
          int i = 0;
          data[i++] = barChart;
          data[i++] = "Small";
        }
      }
      else
      {
        if (chartMode.equals(Modes.SINGLE))
        {
          specialData.add("Pie Chart");
        }
        else
        {
          data3point14 = new String[2];
          data3point14[1] = "Small";
          data3point14[0] = "Pie" + " Chart";
        }
      }
      return this;
    }
  }
}