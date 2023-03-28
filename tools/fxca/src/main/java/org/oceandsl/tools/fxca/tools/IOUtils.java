package org.oceandsl.tools.fxca.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class IOUtils {

	public static PrintStream printStreamPrefixWrapper(final PrintStream printWithPrefix, final PrintStream printWithoutPrefix, final String prefix) {
		return new PrintStream(printWithoutPrefix, false) {
			@Override
			public void println(final String x) {
				printWithPrefix.println(prefix + x);
				printWithoutPrefix.println(x);
			}
		};
	}
	
	public static PrintStream printToFileAnd(final PrintStream out, final Path filename) throws FileNotFoundException {
		return printStreamPrefixWrapper(out, new PrintStream(filename.toFile()), "[" + filename.toString() + "] ");
	}

	public static PrintStream printToFileAnd(final PrintStream out, final String filename) throws FileNotFoundException {
		return printStreamPrefixWrapper(out, new PrintStream(filename), "[" + filename + "] ");
	}
	
	public static void createDirectory(Path directoryPath) {
		directoryPath.toFile().mkdirs();
	}
	
	public final static Predicate<Path> isDirectory = element -> Files.isDirectory(element);

	public static Predicate<Path> isDirectoryWithTheseFiles(Iterable<String> filenames) {
		Predicate<Path> result = isDirectory;
		for (String filename : filenames) {
			result = result.and(directory -> Files.exists(directory.resolve(Paths.get(filename))));
		}
		return result;
	}

	public static Predicate<Path> endsWith(String suffix) {
		return element -> element.toAbsolutePath().toString().endsWith(suffix);
	}

	public static List<Path> pathsInDirectory(Path directory) throws IOException {
		return pathsInDirectory(directory, o -> true, o -> true, false);
	}

	public static List<Path> pathsInDirectory(Path directory, final Predicate<Path> fileFilter) throws IOException {
		return pathsInDirectory(directory, fileFilter, null);		
	}

	private static List<Path> pathsInDirectory(final Path directory, final Predicate<Path> fileFilter, final Collection<Path> collectNotMatchingFiles) throws IOException {
		return pathsInDirectory(directory, fileFilter, o -> true, false, collectNotMatchingFiles);
	}

	public static List<Path> pathsInDirectory(final Path directory, final Predicate<Path> fileFilter, final Predicate<Path> directoryFilter, boolean addEntriesForDirectories) throws IOException {
		return pathsInDirectory(directory, fileFilter, directoryFilter, addEntriesForDirectories, null);
	}

	private static List<Path> pathsInDirectory(final Path directory, final Predicate<Path> fileFilter, final Predicate<Path> directoryFilter, boolean addEntriesForDirectories, Collection<Path> collectNotMatchingFiles) throws IOException {

		final List<Path> result = ListTools.ofM();

		SimpleFileVisitor<Path> visitor = new SimpleFileVisitor<>() {

			@Override
			public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) {
				if (!fileFilter.test(filePath)) {
					if (collectNotMatchingFiles != null) {
						collectNotMatchingFiles.add(filePath);
					}
					return FileVisitResult.CONTINUE;
				}
				if (!Files.isDirectory(filePath)) {
					result.add(filePath);
					System.out.println(filePath.toString());
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path filePath, IOException exc) {
				System.out.println("could not visit " + filePath.toString() + ": " + exc.getClass());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) {
				if (!directoryFilter.test(dir) && !dir.equals(directory)) { // no matter what the filter, we should visit the original directory.
					return FileVisitResult.SKIP_SUBTREE;
				}

				if (addEntriesForDirectories && fileFilter.test(dir)) {
					result.add(dir);
				}

				try {
					return super.preVisitDirectory(dir, attrs);
				}
				catch (IOException e) {
					System.out.println("skipping subdir " + dir + " due to I/O exception.");
					return FileVisitResult.SKIP_SUBTREE;
				}
			}
		};

		Files.walkFileTree(directory, visitor);
		return result;
	}
	
public static void printWithCommas(Iterable<String> items) {
		
		StringBuilder result = new StringBuilder();

		for (String item : items) {
			if (!result.isEmpty()) {
				result.append(", ");
			}
			result.append(item);
		}
		
		System.out.println(result.toString());
	}
}


