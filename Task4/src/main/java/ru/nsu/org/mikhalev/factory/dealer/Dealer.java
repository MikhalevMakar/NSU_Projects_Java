package ru.nsu.org.mikhalev.factory.dealer;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.observable.Observable;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;
import ru.nsu.org.mikhalev.view.observer.Observer;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
public class Dealer implements Runnable, Observable {
    private LinkedList<Observer> observers = new LinkedList<>();
    private final AutoStorage autoStorage;
    @Setter
    private int time = 50;
    private AtomicInteger countFinishedAuto = new AtomicInteger();
    public Dealer(AutoStorage autoStorage) {
        this.autoStorage = autoStorage;
    }

    @Override
    public void run() {
        log.info("Start work dealer,  method run");

        while (true) {
            try {
                synchronized(autoStorage) {
                    String message  = String.valueOf(autoStorage.getAuto().getId());
                    notifyObservers(message + "\n", Integer.valueOf(countFinishedAuto.incrementAndGet()));

                    if(Boolean.valueOf(Properties_Value.LOG_SALE.getValue())) {
                        log.info(message);
                    }
                }
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void registerObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void notifyObservers(String message, Integer count) {
        for(Observer observer : observers) {
                observer.notification(message, count);
        }
    }
}

