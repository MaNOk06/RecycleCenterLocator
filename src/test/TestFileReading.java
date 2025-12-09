package test;

import service.CentreDataLoader;
import model.*;
import java.io.File;


public class TestFileReading {
    public static void main(String[] args) {
        System.out.println("=== DEBUG: FINDING FILE LOCATION ===\n");

        // 1. Show where Java is looking
        System.out.println("Current directory: " + System.getProperty("user.dir"));

        // 2. Try to find the file
        File file = new File("centres.txt");
        System.out.println("Looking for: " + file.getAbsolutePath());
        System.out.println("File exists: " + file.exists());

        // 3. List files in current directory
        System.out.println("\nFiles in current directory:");
        File currentDir = new File(System.getProperty("user.dir"));
        File[] files = currentDir.listFiles();
        if (files != null) {
            for (File f : files) {
                System.out.println(" - " + f.getName() + (f.isDirectory() ? " (folder)" : ""));
            }
        }

        // 4. Check src folder
        System.out.println("\nFiles in src folder:");
        File srcDir = new File(currentDir, "src");
        if (srcDir.exists() && srcDir.listFiles() != null) {
            for (File f : srcDir.listFiles()) {
                System.out.println(" - " + f.getName() + (f.isDirectory() ? " (folder)" : ""));
            }
        }

        // 5. Check for resources folder
        System.out.println("\nFiles in resources folder:");
        File resourcesDir = new File(currentDir, "resources");
        if (resourcesDir.exists() && resourcesDir.listFiles() != null) {
            for (File f : resourcesDir.listFiles()) {
                System.out.println(" - " + f.getName() + (f.isDirectory() ? " (folder)" : ""));
            }
        }

        System.out.println("\n=== TESTING FILE READING ===\n");

        CentreDataLoader loader = new CentreDataLoader();

        // Try multiple possible locations
        String[] possiblePaths = {
                "centres.txt",
                "./centres.txt",
                "resources/centres.txt",
                "src/centres.txt",
                currentDir.getAbsolutePath() + "/centres.txt"
        };

        boolean fileFound = false;

        for (String path : possiblePaths) {
            File testFile = new File(path);
            if (testFile.exists()) {
                System.out.println("Found file at: " + testFile.getAbsolutePath());
                System.out.println("Attempting to load...");

                var centres = loader.loadCentresFromFile(path);

                System.out.println("\nSummary:");
                System.out.println("Total centres loaded: " + centres.size());

                for (RecyclingCentre centre : centres) {
                    System.out.println("\n--- " + centre.getName() + " ---");
                    System.out.println("Type: " + (centre instanceof EcoRecyclingCentre ? "ECO CENTRE" : "Regular Centre"));
                    System.out.println("Location: " + centre.getLocation().getPlaceName());
                    System.out.println("Coordinates: (" + centre.getLocation().getX() + ", " + centre.getLocation().getY() + ")");
                    System.out.println("Accepts: " + centre.getMaterials());
                    System.out.println("Accepts PLASTIC? " + centre.acceptsMaterial(Material.PLASTIC));
                }

                fileFound = true;
                break;
            }
        }

        if (!fileFound) {
            System.out.println("\n❌ ERROR: Could not find centres.txt anywhere!");
            System.out.println("Please place centres.txt in one of these locations:");
            System.out.println("1. " + currentDir.getAbsolutePath() + "\\centres.txt");
            System.out.println("2. " + currentDir.getAbsolutePath() + "\\resources\\centres.txt");
            System.out.println("3. " + currentDir.getAbsolutePath() + "\\src\\centres.txt");

            // Show what backup data we get
            System.out.println("\nCurrent backup data (used when file not found):");
            var backupCentres = loader.loadCentresFromFile("dummy.txt"); // Will trigger backup
            System.out.println("Backup centres created: " + backupCentres.size());
        }

        System.out.println("\n✅TEST COMPLETE!");
    }
}