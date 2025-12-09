package service;
import model.*;
import service.ServiceSortLogic;
import java.util.*;

public class TestService {
    public static void main(String[] args) {
        ServiceSortLogic service = new ServiceSortLogic();

        // Create a test center
        Location loc = new Location("Test Place", 100, 200);
        Set<Material> mats = new HashSet<>();
        mats.add(Material.PLASTIC);

        RecyclingCentre centre = new RecyclingCentre("Test Centre", loc, mats);
        service.addRecyclingCentre(centre);

        System.out.println("Service works! Added: " + centre.getName());
    }
}

