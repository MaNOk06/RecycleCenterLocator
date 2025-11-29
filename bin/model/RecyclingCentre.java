import java.util.Set;

public class RecyclingCentre {
    // name of the centre
    private String name;
    // the location of the centre
    private Location location;
    // set of materials the centre accepts
    private Set<Material> materials;

    // constructor to set the fields
    public RecyclingCentre(String name, Location location, Set<Material> materials) {
        this.name = name;
        this.location = location;
        this.materials = materials;
    }

    // returns the name
    public String getName() {
        return name;
    }

    // returns the location
    public Location getLocation() {
        return location;
    }

    // returns the set of materials
    public Set<Material> getMaterials() {
        return materials;
    }

    // checks if the centre accepts a given material
    public boolean acceptsMaterial(Material material) {
        return materials.contains(material);
    }

    // for displaying information about the centre
    @Override
    public String toString() {
        return name + " at " + location.getPlaceName();
    }
}