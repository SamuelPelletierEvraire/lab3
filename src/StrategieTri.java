import java.util.ArrayList;

public abstract class StrategieTri extends TimeTookObservable {

    private long startTime = System.currentTimeMillis()/1000;
    private long methodStartTime = System.currentTimeMillis()/1000 -startTime;


    final ArrayList<Integer> EtapeDeTriage(String listeDeNombreString){
        // Étape 1
        ArrayList<Integer> listeNombreInteger = creationCollection(listeDeNombreString);
        notifyTimeTookObservers((System.currentTimeMillis()/1000)-methodStartTime, 0);
        // Étape 2
        ArrayList<ArrayList<Integer>> listeCollections = separerCollection(listeNombreInteger);
        notifyTimeTookObservers((System.currentTimeMillis()/1000)-methodStartTime, 1);
        // Étape 3
        int start = 0;
        listeCollections = trierCollection(listeCollections, start);
        notifyTimeTookObservers((System.currentTimeMillis()/1000)-methodStartTime, 2);
        // Étape 4
        listeNombreInteger = fusionner(listeCollections);
        notifyTimeTookObservers((System.currentTimeMillis()/1000)-methodStartTime, 3);
        // Étape 5

        return listeNombreInteger;
    }
    abstract ArrayList<Integer> fusionner(ArrayList<ArrayList<Integer>> collections);

    abstract ArrayList<ArrayList<Integer>> separerCollection(ArrayList<Integer> collection);

    abstract ArrayList<ArrayList<Integer>> trierCollection(ArrayList<ArrayList<Integer>> collection, int start);

    private ArrayList<Integer> creationCollection(String entrer) {
        ArrayList<Integer> elementInt = new ArrayList<Integer>();
        String[] elements = entrer.split(",");
        for (String element : elements) {
            int intValue = Integer.parseInt(element.trim());
            elementInt.add(intValue);
        }
        return elementInt;
    }
}
