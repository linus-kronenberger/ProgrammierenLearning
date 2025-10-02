package de.dhbwka.java.exercise.control;

public class ShoeSize {
    public static void main(String[] args) {
        System.out.println("Zentimeter                  | Größe");
        System.out.printf("%s+%s", "----------------------------", "------------------");
        System.out.println();

        for (int g = 30; g <= 49; g++) {
            float z = (float) (g / 1.5);
            float zStart = (float) (z - 0.67);
            System.out.printf("%f-%f         | %d", zStart, z, g);
            System.out.println();
        }
    }
}
