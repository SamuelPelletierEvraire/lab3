import java.util.*;

public class FusionnerEtTrier extends StrategieTri {

    @Override
    ArrayList<Integer> fusionner(ArrayList<ArrayList<Integer>> collections) {
        ArrayList<Integer> mergedList = new ArrayList<>();

        // Fusionne chaque sous-collection dans la liste finale
        for (ArrayList<Integer> collection : collections) {
            mergedList.addAll(collection);
        }

        // Trie la liste fusionnée
        Collections.sort(mergedList);

        System.out.println("Affichage tri rapide " + mergedList);

        return mergedList;
    }

    // Méthode récursive pour diviser une collection en deux à chaque étape
    @Override
    ArrayList<ArrayList<Integer>> separerCollection(ArrayList<Integer> collection) {
        ArrayList<ArrayList<Integer>> listeCollections = new ArrayList<ArrayList<Integer>>();

        // Cas de base : si la collection a une taille inférieure ou égale à 1, retourne la collection
        if (collection.size() <= 1) {
            ArrayList<Integer> singleCollection = new ArrayList<>(collection);
            listeCollections.add(singleCollection);
            return listeCollections;
        }

        // Divise la collection en deux parties
        int centre = collection.size() / 2;
        ArrayList<Integer> moitieGauche = new ArrayList<>(collection.subList(0, centre));
        ArrayList<Integer> moitieDroite = new ArrayList<>(collection.subList(centre, collection.size()));

        // Ajoute les deux moitiés à la liste de collections
        listeCollections.add(moitieGauche);
        listeCollections.add(moitieDroite);

        listeCollections = trierCollection(listeCollections, 0);

        return listeCollections;
    }


    @Override
    ArrayList<ArrayList<Integer>> trierCollection(ArrayList<ArrayList<Integer>> collection, int start) {
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
}
