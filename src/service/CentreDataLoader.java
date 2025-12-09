package service; // or service - keep your package name

import model.*;
import java.io.*;
import java.util.*;

public class CentreDataLoader {

    public List<RecyclingCentre> loadCentresFromFile(String filename) {
        List<RecyclingCentre> centres = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Loading centres from: " + filename);

            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                System.out.println("Processing line " + lineCount + ": " + line);

                RecyclingCentre centre = parseLine(line);
                if (centre != null) {
                    centres.add(centre);
                    System.out.println("  ✓ Added: " + centre.getName());
                }
            }

            System.out.println("Successfully loaded " + centres.size() + " centres from file");

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found: " + filename);
            System.err.println("Creating backup data instead...");
            centres = createBackupData();

        } catch (IOException e) {
            System.err.println("ERROR reading file: " + e.getMessage());
            centres = createBackupData();
        }

        return centres;
    }

    private RecyclingCentre parseLine(String line) {
        try {
            // Split by pipe character |
            String[] parts = line.split("\\|");

            // We need exactly 5 parts: name|place|x|y|materials
            if (parts.length != 5) {
                System.err.println("  ✗ Skipping - Wrong number of parts. Expected 5, got " + parts.length);
                return null;
            }

            // Extract data
            String name = parts[0].trim();
            String placeName = parts[1].trim();

            // Parse coordinates
            int x = Integer.parseInt(parts[2].trim());
            int y = Integer.parseInt(parts[3].trim());

            // Parse materials (comma-separated list)
            Set<Material> materials = new HashSet<>();
            String[] materialNames = parts[4].split(",");

            for (String materialName : materialNames) {
                String cleanName = materialName.trim().toUpperCase();
                try {
                    Material material = Material.valueOf(cleanName);
                    materials.add(material);
                } catch (IllegalArgumentException e) {
                    System.err.println("  ✗ Unknown material: '" + materialName + "'");
                }
            }

            // Create Location object
            Location location = new Location(placeName, x, y);

            // Check if eco centre (simple rule)
            if (name.toLowerCase().contains("eco")) {
                System.out.println("  → Detected as Eco Centre");
                return new EcoRecyclingCentre(name, location, materials, materials);
            }

            return new RecyclingCentre(name, location, materials);

        } catch (NumberFormatException e) {
            System.err.println("  ✗ Skipping - Invalid number in coordinates");
            return null;
        } catch (Exception e) {
            System.err.println("  ✗ Skipping - Error: " + e.getMessage());
            return null;
        }
    }

    private List<RecyclingCentre> createBackupData() {
        List<RecyclingCentre> backup = new ArrayList<>();

        // Create 2 simple backup centres
        Location loc1 = new Location("Backup Location", 50, 50);
        Set<Material> mats1 = new HashSet<>(Arrays.asList(Material.PLASTIC, Material.PAPER));
        backup.add(new RecyclingCentre("Backup Centre 1", loc1, mats1));

        Location loc2 = new Location("Another Backup", 100, 100);
        Set<Material> mats2 = new HashSet<>(Arrays.asList(Material.GLASS, Material.METAL));
        backup.add(new RecyclingCentre("Backup Centre 2", loc2, mats2));

        System.out.println("Created " + backup.size() + " backup centres");
        return backup;
    }
}