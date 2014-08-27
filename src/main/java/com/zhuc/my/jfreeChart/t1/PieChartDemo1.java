package com.zhuc.my.jfreeChart.t1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

public class PieChartDemo1 extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	public PieChartDemo1(String paramString) {
		super(paramString);
		ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow", true));
		setContentPane(createDemoPanel());
	}

	private static PieDataset createDataset() {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		localDefaultPieDataset.setValue("Samsung", new Double(27.800000000000001D));
		localDefaultPieDataset.setValue("Others", new Double(55.299999999999997D));
		localDefaultPieDataset.setValue("Nokia", new Double(16.800000000000001D));
		localDefaultPieDataset.setValue("Apple", new Double(17.100000000000001D));
		return localDefaultPieDataset;
	}

	private static JFreeChart createChart(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart("Smart Phones Manufactured / Q3 2011",
				paramPieDataset, false, true, false);
		localJFreeChart.setBackgroundPaint(new GradientPaint(new Point(0, 0), new Color(20, 20, 20),
				new Point(400, 200), Color.DARK_GRAY));
		TextTitle localTextTitle1 = localJFreeChart.getTitle();
		localTextTitle1.setHorizontalAlignment(HorizontalAlignment.LEFT);
		localTextTitle1.setPaint(new Color(240, 240, 240));
		localTextTitle1.setFont(new Font("Arial", 1, 26));
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setBackgroundPaint(null);
		localPiePlot.setInteriorGap(0.04D);
		localPiePlot.setOutlineVisible(false);
		localPiePlot.setSectionPaint("Others", createGradientPaint(new Color(200, 200, 255), Color.BLUE));
		localPiePlot.setSectionPaint("Samsung", createGradientPaint(new Color(255, 200, 200), Color.RED));
		localPiePlot.setSectionPaint("Apple", createGradientPaint(new Color(200, 255, 200), Color.GREEN));
		localPiePlot.setSectionPaint("Nokia", createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
		localPiePlot.setBaseSectionOutlinePaint(Color.WHITE);
		localPiePlot.setSectionOutlinesVisible(true);
		localPiePlot.setBaseSectionOutlineStroke(new BasicStroke(2.0F));
		localPiePlot.setLabelFont(new Font("Courier New", 1, 20));
		localPiePlot.setLabelLinkPaint(Color.WHITE);
		localPiePlot.setLabelLinkStroke(new BasicStroke(2.0F));
		localPiePlot.setLabelOutlineStroke(null);
		localPiePlot.setLabelPaint(Color.WHITE);
		localPiePlot.setLabelBackgroundPaint(null);
		TextTitle localTextTitle2 = new TextTitle("Source: http://www.bbc.co.uk/news/business-15489523", new Font(
				"Courier New", 0, 12));
		localTextTitle2.setPaint(Color.WHITE);
		localTextTitle2.setPosition(RectangleEdge.BOTTOM);
		localTextTitle2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		localJFreeChart.addSubtitle(localTextTitle2);
		return localJFreeChart;
	}

	private static RadialGradientPaint createGradientPaint(Color paramColor1, Color paramColor2) {
		Point2D.Float localFloat = new Point2D.Float(0.0F, 0.0F);
		float f = 200.0F;
		float[] arrayOfFloat = { 0.0F, 1.0F };
		return new RadialGradientPaint(localFloat, f, arrayOfFloat, new Color[] { paramColor1, paramColor2 });
	}

	public static JPanel createDemoPanel() {
		JFreeChart localJFreeChart = createChart(createDataset());
		localJFreeChart.setPadding(new RectangleInsets(4.0D, 8.0D, 2.0D, 2.0D));
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setMouseWheelEnabled(true);
		localChartPanel.setPreferredSize(new Dimension(600, 300));
		return localChartPanel;
	}

	public static void main(String[] paramArrayOfString) throws IOException {
		//		PieChartDemo1 localPieChartDemo1 = new PieChartDemo1("JFreeChart: Pie Chart Demo 1");
		//		localPieChartDemo1.pack();
		//		RefineryUtilities.centerFrameOnScreen(localPieChartDemo1);
		//		localPieChartDemo1.setVisible(true);

		JFreeChart chart = createChart(createDataset());
		ChartUtilities.saveChartAsJPEG(new File("D://f.jpg"), chart, 800, 600);

	}
}