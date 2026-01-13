package io_concurrency;

import java.util.List;

public class DownloadTest {

    public static void main(String[] args) {
        DownloadManager manager = new DownloadManager(3);

        manager.downloadAll(List.of(
                "file1.zip","file2.zip","file3.zip","file4.zip","file5.zip"
        ));
    }
}
