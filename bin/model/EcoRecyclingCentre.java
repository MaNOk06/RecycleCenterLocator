import java.util.Set;

public class EcoRecyclingCentre extends RecyclingCentre {
    // the set of materials allowed for eco-centres
    private Set<Material> allowedMaterials;

    // constructor that calls the parent constructor
    public EcoRecyclingCentre(String name, Location location, Set<Material> materials, Set<Material> allowedMaterials) {
        super(name, location, materials);
        this.allowedMaterials = allowedMaterials;
    }

    // returns the allowed materials
    public Set<Material> getAllowedMaterials() {
        return allowedMaterials;
    }

    // checks material acceptance differently for eco centres
    @Override
    public boolean acceptsMaterial(Material material) {
        return allowedMaterials.contains(material) && getMaterials().contains(material);
    }

    // to display info about this type of centre
    @Override
    public String toString() {
        return getName() + " (Eco Centre) at " + getLocation().getPlaceName();
    }
}