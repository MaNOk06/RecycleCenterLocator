public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
/* I designed and implemented the core classes of the system, including Location,
 Material, RecyclingCentre, and the EcoRecyclingCentre subclass, making sure the project uses 
 encapsulation, composition, inheritance, polymorphism, and Java collections. */

 /*Encapsulation: All fields are private and accessed through getters. */

 /*Composition: A RecyclingCentre has a Location and a set of Materials. */
 
 /*Inheritance: EcoRecyclingCentre extends RecyclingCentre.  */

 /* EcoRecyclingCentre overrides acceptsMaterial, so the same method behaves differently
  based on the object type. */

  /* Why enum: Using an enum avoids spelling mistakes and gives a fixed set of allowed material types. */

  /* NB: I didn't do sorting/searching because RecyclingCentre exposes getters, so other 
  parts of the system can sort centres by distance or filter by material. LocatorSystem class
  is the best fit for sorting. */
