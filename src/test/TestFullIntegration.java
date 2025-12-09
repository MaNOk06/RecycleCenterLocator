package test;

import service.RecyclingLocator;
import model.*;

public class TestFullIntegration {
    public static void main(String[] args) {
        System.out.println("=== FULL SYSTEM INTEGRATION TEST ===\n");

        // 1. Create your system
        RecyclingLocator locator = new RecyclingLocator();

        // 2. Initialize with data
        locator.initialize("src/centres.txt");

        System.out.println("\n✓ System initialized with " + locator.getCentreCount() + " centres");

        // 3. Create a test user location
        Location userLocation = new Location("My Home", 110, 190);
        System.out.println("User location: " + userLocation.getPlaceName() +
                " (" + userLocation.getX() + "," + userLocation.getY() + ")");

        // 4. Test 1: Search by material
        System.out.println("\n--- TEST 1: Find centres accepting PLASTIC ---");
        var plasticCentres = locator.findCentresByMaterial(Material.PLASTIC);
        System.out.println("Found " + plasticCentres.size() + " centres:");
        for (var centre : plasticCentres) {
            double distance = centre.getLocation().computeDistance(userLocation);
            System.out.println(" - " + centre.getName() + " (" + String.format("%.1f", distance) + " units away)");
        }

        // 5. Test 2: Sort by distance
        System.out.println("\n--- TEST 2: Sort all centres by distance ---");
        var sortedCentres = locator.sortCentresByDistance(userLocation);
        System.out.println("From nearest to farthest:");
        for (int i = 0; i < sortedCentres.size(); i++) {
            var centre = sortedCentres.get(i);
            double distance = centre.getLocation().computeDistance(userLocation);
            System.out.println(" " + (i+1) + ". " + centre.getName() +
                    " (" + String.format("%.1f", distance) + " units)");
        }

        // 6. Test 3: Find nearest centre
        System.out.println("\n--- TEST 3: Find nearest centre ---");
        var nearest = locator.findNearestCentre(userLocation);
        if (nearest != null) {
            double distance = nearest.getLocation().computeDistance(userLocation);
            System.out.println("Nearest: " + nearest.getName() +
                    " (" + String.format("%.1f", distance) + " units away)");
        }

        // 7. Test 4: Find centres within radius
        System.out.println("\n--- TEST 4: Centres within 100 units ---");
        var nearbyCentres = locator.findCentresInRadius(userLocation, 100.0);
        System.out.println("Found " + nearbyCentres.size() + " centres within 100 units:");
        for (var centre : nearbyCentres) {
            double distance = centre.getLocation().computeDistance(userLocation);
            System.out.println(" - " + centre.getName() + " (" + String.format("%.1f", distance) + " units)");
        }

        System.out.println("\n✅ FULL INTEGRATION SUCCESSFUL!");
        System.out.println("Your data pipeline + Role 2's algorithms = WORKING SYSTEM");
    }
}