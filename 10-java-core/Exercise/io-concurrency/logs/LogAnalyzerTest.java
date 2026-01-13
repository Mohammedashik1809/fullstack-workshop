package io_concurrency;

import java.nio.file.Path;
import java.util.List;

public class LogAnalyzerTest {

    public static void main(String[] args) throws Exception {

        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogEntry> logs = analyzer.readLogs(Path.of("app.log"));

        System.out.println(analyzer.countByLevel(logs));
        System.out.println(analyzer.getErrors(logs));

        analyzer.writeSummary(Path.of("summary.txt"), logs);
    }
}
