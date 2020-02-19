package be.artisjaap.polyglot.cucumber;

import io.cucumber.plugin.SummaryPrinter;

import java.io.File;

public class PrettyReport implements SummaryPrinter {

    private File htmlReportDir;

    public PrettyReport(File htmlReportDir) {
        this.htmlReportDir = htmlReportDir;
    }

//    @Override
    public void print(Runtime runtime) {
        File buildDirectory = htmlReportDir.getAbsoluteFile().getParentFile().getParentFile();
        String projectDirectory = buildDirectory.getParentFile().getName();
//        Configuration configuration = new Configuration(htmlReportDir, projectDirectory);
//        ReportBuilder reportBuilder = new ReportBuilder(Collections.singletonList(htmlReportDir.getPath() + "/report.json"), configuration);
//        reportBuilder.generateReports();

    }
}

