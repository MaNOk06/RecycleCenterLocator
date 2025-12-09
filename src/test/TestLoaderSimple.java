package test;

import service.CentreDataLoader;

public class TestLoaderSimple {
    public static void main(String[] args) {
        System.out.println("Testing CentreDataLoader...");

        CentreDataLoader loader = new CentreDataLoader();
        var centres = loader.loadCentresFromFile("centres.txt");

        System.out.println("Got " + centres.size() + " centres");
        for (var c : centres) {
            System.out.println("- " + c.getName());
        }
    }
}
