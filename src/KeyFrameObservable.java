import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.util.ArrayList;
import java.util.List;

public class KeyFrameObservable {

    private List<KeyFrameObserver> observers = new ArrayList<>();

    public void addKeyFrameObserver(KeyFrameObserver observer) {
        observers.add(observer);
    }

    public void notifyKeyFrameObservers(Timeline value) {
        for (KeyFrameObserver observer : observers) {
            observer.updateKeyFrame(value);
        }
    }
}
