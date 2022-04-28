package com.tr3ntu.fileFinder.test;

import java.nio.file.Path;
import java.nio.file.PathMatcher;

public interface IDiscover extends Runnable {

    Path getMatch(Path file);

    int getMatched();

}
