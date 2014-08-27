package com.zhuc.my.jfreeChart.t1;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartDemo2 extends ApplicationFrame {
	public PieChartDemo2(String paramString) {
		super(paramString);
		JPanel localJPanel = createDemoPanel();
		localJPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(localJPanel);
	}

	private static PieDataset createDataset() {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		localDefaultPieDataset.setValue("One", 43.200000000000003D);
		localDefaultPieDataset.setValue("Two", 10.0D);
		localDefaultPieDataset.setValue("Three", 27.5D);
		localDefaultPieDataset.setValue("Four", 17.5D);
		localDefaultPieDataset.setValue("Five", 11.0D);
		localDefaultPieDataset.setValue("Six", 19.399999999999999D);
		return localDefaultPieDataset;
	}

	private static JFreeChart createChart(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory
				.createPieChart("Pie Chart Demo 2", paramPieDataset, true, true, false);
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setSectionPaint("One", new Color(160, 160, 255));
		localPiePlot.setSectionPaint("Two", new Color(128, 128, 223));
		localPiePlot.setSectionPaint("Three", new Color(96, 96, 191));
		localPiePlot.setSectionPaint("Four", new Color(64, 64, 159));
		localPiePlot.setSectionPaint("Five", new Color(32, 32, 127));
		localPiePlot.setSectionPaint("Six", new Color(0, 0, 111));
		localPiePlot.setNoDataMessage("No data available");
		localPiePlot.setExplodePercent("Two", 0.2D);
		localPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
		localPiePlot.setLabelBackgroundPaint(new Color(220, 220, 220));
		localPiePlot
				.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("Tooltip for legend item {0}"));
		localPiePlot.setSimpleLabels(true);
		localPiePlot.setInteriorGap(0.0D);
		return localJFreeChart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart localJFreeChart = createChart(createDataset());
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setMouseWheelEnabled(true);
		return localChartPanel;
	}

	public static void main(String[] paramArrayOfString) throws IOException {
		PieChartDemo2 localPieChartDemo2 = new PieChartDemo2("JFreeChart: PieChartDemo2.java");
		localPieChartDemo2.pack();
		RefineryUtilities.centerFrameOnScreen(localPieChartDemo2);
		localPieChartDemo2.setVisible(true);

		//		JFreeChart chart = createChart(createDataset());
		//		ChartUtilities.saveChartAsJPEG(new File("D://f.jpg"), chart, 800, 600);
	}
}