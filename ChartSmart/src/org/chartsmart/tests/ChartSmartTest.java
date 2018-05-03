package org.chartsmart.tests;

import junit.framework.TestCase;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.DelayedClipboardReporter;
import org.approvaltests.reporters.UseReporter;
import org.chartsmart.IndvDsp;
import org.chartsmart.MainWindow;

@UseReporter(DelayedClipboardReporter.class)
public class ChartSmartTest extends TestCase
{
  public void testRun() throws Exception
  {
    MainWindow chartSmart = new MainWindow();
    Approvals.verify(chartSmart);
  }
  public void testBarChart() throws Exception
  {
    IndvDsp cw = new IndvDsp();
    cw.iniDS(406, "rpfll", true);
    Approvals.verify(cw);
  }
  public void testBarChartCompare() throws Exception
  {
    IndvDsp cw = new IndvDsp();
    cw.iniDS(406, "shareddisplay", true);
    Approvals.verify(cw);
  }
  public void testPieChart() throws Exception
  {
    IndvDsp cw = new IndvDsp();
    cw.iniDS(323, "rpfll", true);
    Approvals.verify(cw);
  }
  public void testPieChartCompare() throws Exception
  {
    IndvDsp cw = new IndvDsp();
    cw.iniDS(323, "shareddisplay", true);
    Approvals.verify(cw);
  }
}
