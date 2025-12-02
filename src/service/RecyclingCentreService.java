package service;

import model.Location;
import model.RecyclingCentre;
import model.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

// Comparators(comparison) provides an odering of collection of objects that do not have natural odering
import java.util.Comparator; 



// This is class is responsible for search, sort and caluclation of distance
// It hables algorithm implementation

public class RecyclingCentreService{
    private List<RecyclingCentre> centres;

    // Constructor (Non parameterised)
    public RecyclingCentreService(){
        this.centres = new ArrayList<>();
    }

    /**
    @param centre -> adding centers
    @throws IllegalArgumentException if the center is null  
    */
    public void addRecyclingCentre(RecyclingCentre centre){
        if (centre == null){
            throw new IllegalArgumentException("A null recycling centre canot be added!!");
        }
        this.centres.add(centre);
    }


    // Access the current list of files
    // returns a copy of the list to prevent any external modification
    public List<RecyclingCentre> getCentres(){
        return new ArrayList<>(this.centres);
    }

    /**
     @param material -> to be able to filter by material
    */
    public List<RecyclingCentre> findCentresByMaterial(Material material){
        List<RecyclingCentre> results = new ArrayList<>();
        if (material == null){
            return new ArrayList<>(); // returns a new ArrayLost to symbolise an invalid input
        }    
        
        // Loop to check every center. If it matches, then add to reults list
        for (RecyclingCentre centre: centres){
            if (centre.acceptsMaterial(material)){
                results.add(centre);
            }
        }
        return results;
    }


    // The sort logic -> sorts all the centers based on distance to the user
    // Uses the algorithm of Location.computeDistance 
    public List<RecyclingCentre> sortCentresByDistance(Location userLocation){
        if (userLocation == null){
            throw new IllegalArgumentException("A user's location cannot be null!");
        }
        
        List<RecyclingCentre> sortedList = new ArrayList<>(centres);

        // Custom comparator method to sort 
        Collections.sort(sortedList, new Comparator<RecyclingCentre>(){
            public int compare(RecyclingCentre c1, RecyclingCentre c2){
                double dist1 = c1.getLocation().computeDistance(userLocation);
                double dist2 = c2.getLocation().computeDistance(userLocation);

                return Double.compare(dist1, dist2); // comparing the two distances
            }
        });
        return sortedList;
                
    }

        // ALgorithm to check for the nearest centre
        // Uses the fin min distance loic
        public RecyclingCentre findNearestCentre(Location userLocation) {
        if (userLocation == null) {
            throw new IllegalArgumentException("User location cannot be null.");
        }
        if (centres.isEmpty()) {
            return null;
        }

        RecyclingCentre nearest = null;
        double minDistance = Double.MAX_VALUE; // Start with a very large number

        for (RecyclingCentre centre : centres) {
            double distance = centre.getLocation().computeDistance(userLocation);
            
            // If we found a closer one, update our 'nearest' variable
            if (distance < minDistance) {
                minDistance = distance;
                nearest = centre;
            }
        }
        return nearest;
    }

    // search and filter helps Finds centres within a specific radius.

    public List<RecyclingCentre> findCentresInRadius(Location userLocation, double maxDistance) {
        if (userLocation == null || maxDistance < 0) {
            throw new IllegalArgumentException("Invalid inputs for radius search.");
        }

        List<RecyclingCentre> results = new ArrayList<>();

        for (RecyclingCentre centre : centres) {
            if (centre.getLocation().computeDistance(userLocation) <= maxDistance) {
                results.add(centre);
            }
        }
        return results;
    }

}