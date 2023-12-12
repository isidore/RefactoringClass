package org.zuill.talks.codeexcellence.chartsmart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.approvaltests.awt.AwtApprovals;
import org.approvaltests.reporters.DelayedClipboardReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

@UseReporter(DelayedClipboardReporter.class)
public class ChartSmartTest
{
  @Test
  public void testMainWindow() throws Exception
  {
    ChartSmart chartSmart = new ChartSmart();
    AwtApprovals.verify(chartSmart);
  }
  @org.junit.jupiter.api.Test
  public void testBarChartWindow() throws Exception
  {
    ChartWindow chartSmart = new ChartWindow();
    chartSmart.iniDS(406, "rpfll", true);
    AwtApprovals.verify(chartSmart);
    assertEquals("Bar Chart - Single Mode", chartSmart.getTitle());
  }
  @Test
  public void testPieChartWindow() throws Exception
  {
    ChartWindow chartSmart = new ChartWindow();
    chartSmart.iniDS(323, "rpfll", true);
    AwtApprovals.verify(chartSmart);
    assertEquals("Pie Chart - Single Mode", chartSmart.getTitle());
  }
  @Test
  public void testBarChartSmallWindow() throws Exception
  {
    ChartWindow chartSmart = new ChartWindow();
    chartSmart.iniDS(406, "shareddisplay", true);
    AwtApprovals.verify(chartSmart);
    assertEquals("Bar Chart - Compare Mode", chartSmart.getTitle());
  }
  @Test
  public void testPieChartSmallWindow() throws Exception
  {
    ChartWindow chartSmart = new ChartWindow();
    chartSmart.iniDS(323, "shareddisplay", true);
    AwtApprovals.verify(chartSmart);
    org.junit.jupiter.api.Assertions.assertEquals("Pie Chart - Compare Mode", chartSmart.getTitle());
  }
}
