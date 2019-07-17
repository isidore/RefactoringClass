package org.zuill.talks.codeexcellence.chartsmart;

import static org.junit.Assert.assertEquals;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.DelayedClipboardReporter;
import org.approvaltests.reporters.UseReporter;
import org.approvaltests.testcommitrevert.TestCommitRevertRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@UseReporter(DelayedClipboardReporter.class)
@RunWith(TestCommitRevertRunner.class)
public class ChartSmartTest //extends TestCommitRevertTest
{
  @Test
  public void testMainWindow() throws Exception
  {
    ChartSmart chartSmart = new ChartSmart();
    Approvals.verify(chartSmart);
  }
  @Test
  public void testBarChartWindow() throws Exception
  {
    ChartWindow chartSmart = new ChartWindow();
    chartSmart.showChart(406, "rpfll", true);
    Approvals.verify(chartSmart);
    assertEquals("Bar Chart - Single Mode", chartSmart.getTitle());
  }
  @Test
  public void testPieChartWindow() throws Exception
  {
    ChartWindow chartSmart = new ChartWindow();
    chartSmart.showChart(323, "rpfll", true);
    Approvals.verify(chartSmart);
    assertEquals("Pie Chart - Single Mode", chartSmart.getTitle());
  }
  @Test
  public void testBarChartSmallWindow() throws Exception
  {
    ChartWindow chartSmart = new ChartWindow();
    chartSmart.showChart(406, "shareddisplay", true);
    Approvals.verify(chartSmart);
    assertEquals("Bar Chart - Compare Mode", chartSmart.getTitle());
  }
  @Test
  public void testPieChartSmallWindow() throws Exception
  {
    ChartWindow chartSmart = new ChartWindow();
    chartSmart.showChart(323, "shareddisplay", true);
    Approvals.verify(chartSmart);
    assertEquals("Pie Chart - Compare Mode", chartSmart.getTitle());
  }
}
