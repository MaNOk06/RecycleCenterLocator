package service;

import model.*;
import java.util.List;

public class RecyclingLocator {
    private RecyclingCenterService serviceLogic;
    private service.CentreDataLoader dataLoader; // or just CentreDataLoader if same package

    public RecyclingLocator() {
        this.serviceLogic = new RecyclingCenterService();
        this.dataLoader = new service.CentreDataLoader(); // Adjust if different package
    }

    public void initialize(String filename) {
        System.out.println("=== INITIALIZING RECYCLING LOCATOR ===");

        // Load centres from file (note: filename is "src/centres.txt")
        List<RecyclingCentre> centres = dataLoader.loadCentresFromFile(filename);

        // Add them to the service logic
        for (RecyclingCentre centre : centres) {
            serviceLogic.addRecyclingCentre(centre);
        }

        System.out.println("✓ System ready with " + centres.size() + " centres");
    }

    // Bridge methods - connect to ServiceSortLogic
    public List<RecyclingCentre> findCentresByMaterial(Material material) {
        return serviceLogic.findCentresByMaterial(material);
    }

    public List<RecyclingCentre> sortCentresByDistance(Location userLocation) {
        return serviceLogic.sortCentresByDistance(userLocation);
    }

    public RecyclingCentre findNearestCentre(Location userLocation) {
        return serviceLogic.findNearestCentre(userLocation);
    }

    public List<RecyclingCentre> findCentresInRadius(Location userLocation, double radius) {
        return serviceLogic.findCentresInRadius(userLocation, radius);
    }

    // Getter for Role 4 (UI)
    public RecyclingCenterService getServiceLogic() {
        return serviceLogic;
    }

    public int getCentreCount() {
        return serviceLogic.getCentres().size();
    }
}
