package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import ru.nsu.org.mikhalev.factory.detail.Auto;
import ru.nsu.org.mikhalev.factory.storage.Storage;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;


public class AutoStorage extends Storage<Auto> {
    private ControllerAutoStorage controllerAutoStorage;
    public AutoStorage() {
        super(Integer.valueOf(Properties_Value.STORAGE_AUTO_SIZE.getValue()));
        controllerAutoStorage = new ControllerAutoStorage(this, Integer.valueOf(Properties_Value.STORAGE_AUTO_SIZE.getValue()));

    }

    public synchronized void addAuto(Auto auto) {
        notifyObservers(String.valueOf(details.size()), 0);
        details.add(auto);
    }
    public synchronized Auto getAuto() throws InterruptedException{
        System.out.println("BEFORE");
        Auto auto = (!details.isEmpty ()) ? details.removeFirst() : null;
        System.out.println("BEFORE");
        while (auto == null) {
            this.wait();
            if (!details.isEmpty()) auto = details.removeFirst();
        }
        System.out.println("AFTER");
        controllerAutoStorage.isWakesUpWorkers(details.size());
        notifyObservers(String.valueOf(details.size()), 0);
        return auto;
    }
}