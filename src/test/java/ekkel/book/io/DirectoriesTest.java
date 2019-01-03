package ekkel.book.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class DirectoriesTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoriesTest.class);

    @Test
    public void directoryTest() throws Exception {
        LOGGER.info(Directory.walk(".", "T.*\\.class").toString());
    }

    @Test
    public void fileProcessingTest() throws Exception {
        new ProcessFiles(
                file -> LOGGER.info("{}", file),
                ".*\\.java"
        ).start();

    }

    @Test
    public void countSizeOfTree() throws Exception {
        TreeInfo info = new FilesSizeInfo();
        info.addAll(Directory.walk(".", ".*\\.java"));
        LOGGER.info("{}", info);
    }

    @Test
    public void usageMessageTest() throws Exception {
        LOGGER.info("Usage printing:\n{}", MakeDirectories.usage());

        final String usageMsg = "Usage: directories path1 ...\n" +
                "Creates each path\n" +
                "Usage: directories -d path1 ...\n" +
                "Deletes each path\n" +
                "Usage: directories -r path1 path2\n" +
                "Renames from path1 to path2\n";


        assertThat(MakeDirectories.usage(), equalTo(usageMsg));

    }

    @Test
    public void fileInfoPrintingTest() throws Exception {
        File file = new File("pom.xml");

        LOGGER.info("File info:\n{}", MakeDirectories.fileData(file));

    }

    @Test
    public void renamingFileTest() throws Exception {
        File oldFile = new File("some.tmp");
        assertTrue("Create of some.tmp failed", oldFile.createNewFile());

        File newFile = MakeDirectories.renameFile("some.tmp", "another.tmp");
        assertNotNull("Renaming method return null result", newFile);

        LOGGER.info("Old file: {}", MakeDirectories.fileData(oldFile));
        LOGGER.info("Renamed file: {}", MakeDirectories.fileData(newFile));

        assertFalse("Old file yet exists", oldFile.exists());
        assertTrue("New file not exists", newFile.exists());

        assertTrue("Delete of some.tmp failed", newFile.delete());
    }

    @Test
    public void createDirectoriesTest() throws Exception {
        String[] fileNames = { "some_1", "some_2" };

        Iterable<File> created = MakeDirectories.createDirectories(fileNames, 0, fileNames.length);
        assertThat(created, hasItems(new File("some_1"), new File("some_2")));

        for (File file : created) {
            LOGGER.info("Created file:\n{}", MakeDirectories.fileData(file));
            file.delete();
        }

    }

    @Test
    public void deleteDirectoriesTest() throws Exception {
        String[] fileNames = { "some_1", "some_2" };
        List<File> createdFiles = Arrays
                .stream(fileNames)
                .map(File::new)
                .peek(file -> assertTrue("Unable to create " + file.getName(), file.mkdir()))
                .collect(Collectors.toList());

        Iterable<File> deletedFiles = MakeDirectories.deleteDirectories(fileNames, 0, fileNames.length);
        assertThat(deletedFiles, hasItems(createdFiles.get(0), createdFiles.get(1)));

        for (File file : deletedFiles) {
            LOGGER.info("Deleted file:\n{}", MakeDirectories.fileData(file));
        }
    }

    @Test
    public void searchFileByDate() throws Exception {
        Calendar calendar = new GregorianCalendar(2017, 3, 20);
        List<File> lastModifiedAfter = new ArrayList<>();

        new ProcessFiles(
                file -> {
                    if (file.lastModified() > calendar.getTimeInMillis()) {
                        lastModifiedAfter.add(file);
                    }
                },
                ".*\\.java"
        ).start();

        lastModifiedAfter.sort(Comparator.comparingLong(File::lastModified));

        for (File file : lastModifiedAfter) {
            LOGGER.info("{} : {}", file, new Date(file.lastModified()));
        }


    }
}
