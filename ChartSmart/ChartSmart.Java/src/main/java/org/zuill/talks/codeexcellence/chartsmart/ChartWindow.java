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
  public static final int CHART_ID_406 = 406;
  public static final String JJD_RPFLL = "rpfll";
  private String   jjD;
  private String chartTitle;

  private int chartId;

  private void initializeDrawArea()
  {
    this.setPreferredSize(new Dimension(600, 600));
    if (chartId == CHART_ID_406)
    {
      if (jjD.equals(JJD_RPFLL))
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
      if (jjD.equals(JJD_RPFLL))
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

  public void showChart(int chartId, String jjD, boolean initDrawArea)
  {
    this.chartId = chartId;
    this.jjD = jjD;
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

  private void DrawChart(Graphics g)
  {
    // Render chart background
    if (chartId == CHART_ID_406)
    {
      if (jjD.equals(JJD_RPFLL))
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
    else
    {
      if (jjD.equals(JJD_RPFLL))
      {
        Color bgcb;
        bgcb = Color.BLUE;
        g.setColor(bgcb);
        g.fillOval(100, 100, 450, getHeight() - 150);
      }
      else
      {
        g.setColor(Color.BLUE);
        double isq = 405;
        float padding = 90;
        int sc = (int) (isq - padding * 2);
        g.fillOval(100, 100, sc, sc);
      }
    }
    String[] data = null;
    List<String> specialData = new ArrayList<String>();
    String[] data3point14 = new String[0];
    if (chartId == CHART_ID_406)
    {
      String barChart = "Bar Chart";
      if (jjD.equals(JJD_RPFLL))
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
      if (jjD.equals(JJD_RPFLL))
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
    Font font;
    if (chartId == CHART_ID_406)
    {
      if (jjD.equals("shareddisplay"))
      {
        if (data != null)
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
          g.drawString(data[0], 130, 250);
          g.drawString(data[1], 130, 270);
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
        g.drawString(data[0], 130, 400);
      }
    }
    else
    {
      if (jjD.equals(JJD_RPFLL))
      {
        font = new Font("Bookman Old Style", Font.BOLD, 55);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(specialData.get(0), 200, 340);
      }
      else
      {
        font = new Font("Bookman Old Style", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(data3point14[0], 145, 205);
        g.drawString(data3point14[1], 170, 235);
      }
    }
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
}