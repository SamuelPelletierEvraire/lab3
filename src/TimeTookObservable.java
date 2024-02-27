import java.util.ArrayList;
import java.util.List;

public class TimeTookObservable {
    private List<TimeTookObserver> observers = new ArrayList<>();

    public void addTimeTookObserver(TimeTookObserver observer) {
        observers.add(observer);
    }

    public void notifyTimeTookObservers(long timeTook, int index){
        for (TimeTookObserver observer : observers) {
            observer.updateTimeTook(timeTook, index);
        }
    }
}
