package com.tr3ntu.fileFinder.test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileDiscovery extends SimpleFileVisitor<Path> implements IDiscover {

    public static int finalTotal = 0;

        public PathMatcher matcher;
        public int numMatches = 0;

        public FileDiscovery(String pattern) {
            matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + pattern);
        }

        @Override
        public Path getMatch(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;
                System.out.println(file);
            }
            return name;
        }

        @Override
        public int getMatched() {
            System.out.println("Matched: "
                    + numMatches);
            finalTotal = finalTotal + numMatches;
            return 0;
        }

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attrs) {
            getMatch(file);
            return CONTINUE;
        }

        // Invoke the pattern matching
        // method on each directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                                                 BasicFileAttributes attrs) {
            getMatch(dir);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return CONTINUE;
        }

        @Override
        public void run() {

        }
}
