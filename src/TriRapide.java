import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriRapide extends StrategieTri {
    public ArrayList<ArrayList<Integer>> separerCollection(ArrayList<Integer> collection) {
        ArrayList<ArrayList<Integer>> listeDeCollection = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> collection1 = new ArrayList<Integer>();
        ArrayList<Integer> collection2 = new ArrayList<Integer>();

        int pivot = (Collections.max(collection) + Collections.min(collection)) / 2; // Calcul du pivot

        for (int value : collection) {
            if (value <= pivot) {
                collection1.add(value);
            } else {
                collection2.add(value);
            }
        }

        listeDeCollection.add(collection1);
        listeDeCollection.add(collection2);

        return listeDeCollection;
    }


    public ArrayList<ArrayList<Integer>> trierCollection(ArrayList<ArrayList<Integer>> collection, int start) {
        for (ArrayList<Integer> liste : collection) {
            if (start < liste.size() - 1) {
                int minIndex = start;
                for (int i = start + 1; i < liste.size(); i++) {
                    if (liste.get(i) < liste.get(minIndex)) {
                        minIndex = i;
                    }
                }
                if (minIndex != start) {
                    int temp = liste.get(start);
                    liste.set(start, liste.get(minIndex));
                    liste.set(minIndex, temp);
                }
                trierCollection(collection, start + 1);
            }
        }
        return collection;
    }



    public ArrayList<Integer> fusionner(ArrayList<ArrayList<Integer>> collections) {
        ArrayList<Integer> collectionFusionner = new ArrayList<Integer>();
        for(ArrayList<Integer> collection : collections){
            collectionFusionner.addAll(collection);
        }
        System.out.println("Affichage tri rapide " + collectionFusionner);
        return collectionFusionner;
    }
}
