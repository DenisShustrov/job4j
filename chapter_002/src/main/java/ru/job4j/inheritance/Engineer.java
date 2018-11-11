package ru.job4j.inheritance;

/**
 * Class Engineer.
 * @author dshustrov
 * @version 1
 * @since 08.11.2018
 */
public class Engineer extends Profession {
    public House buildHouse(BuildingMaterials buildingMaterials) {
        return new House();
    }
}
