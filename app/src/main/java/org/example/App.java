package org.example;

import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Integer> insertions = new ArrayList<>(List.of(40, 20, 60, 10, 30, 50, 70, 25)),
                deletions = new ArrayList<>(List.of(50, 70));
        testingTree(insertions, deletions);

        // Insert: (50, 30, 70, 20, 40, 60, 80, 35)
        insertions = new ArrayList<>(List.of(50, 30, 70, 20, 40, 60, 80, 35));
        // Delete: 30 (node with two children).
        deletions = new ArrayList<>(List.of(30));

        testingTree(insertions, deletions);

        // Insert: [20, 10, 40, 30, 50]
        insertions = new ArrayList<>(List.of(20, 10, 40, 30, 50));
        // Delete: 10
        deletions = new ArrayList<>(List.of(10));

        testingTree(insertions, deletions);

        // Insert Sequence: [50, 25, 75, 10, 40, 60, 90, 30]
        insertions = new ArrayList<>(List.of(50, 25, 75, 10, 40, 60, 90, 30));
        deletions = new ArrayList<>();

        testingTree(insertions, deletions);

        // Insert Sequence: [10, 20, 30, 40, 50, 60, 70]
        insertions = new ArrayList<>(List.of(10, 20, 30, 40, 50, 60, 70));
        deletions = new ArrayList<>();

        testingTree(insertions, deletions);
    }

    public static void testingTree(ArrayList<Integer> inserts, ArrayList<Integer> deletions) {
        AVLtree tree = new AVLtree();
        /*
         * for (int insert : inserts) {
         * tree.insert(insert);
         * }
         */
        inserts.forEach(insert -> tree.insert(insert));
        System.out.println(tree.serialize());

        for (int delete : deletions) {
            tree.delete(delete);
            System.out.println(tree.serialize());
        }

        return;
    }
}
