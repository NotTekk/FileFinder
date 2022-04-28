package com.tr3ntu.fileFinder;

import com.tr3ntu.fileFinder.test.FileDiscovery;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static com.tr3ntu.fileFinder.test.FileDiscovery.finalTotal;

public class Main {

    public static void main(String[] args) throws IOException {

        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();

        paths = File.listRoots();

        for (File path : paths) {
            String str = path.toString();
            String slash = "\\";

            String s = new StringBuilder(str).append(slash).toString();

            Path startingDir = Paths.get(s);

            String pattern = "*.txt";

            FileDiscovery file = new FileDiscovery(pattern);
            Files.walkFileTree(startingDir, file);
            file.getMatched();
        }

        System.out.println("Total Matched Number of Files : " + finalTotal);

    }
}