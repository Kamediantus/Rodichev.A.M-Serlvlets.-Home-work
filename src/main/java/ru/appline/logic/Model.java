package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();

    private final Map<Integer, User> model;


    public static Model getInstance(){
        return instance;
    }

    private Model(){
        model = new HashMap<>();

        model.put(1, new User("Ivan", "Frolov", 100000));
        model.put(2, new User("Petr", "Kosmakov", 100100));
        model.put(3, new User("Sasha", "Rodichev", 80000 ));
    }

    public void add (User user, int id){
        model.put(id,user);
    }

    public Map<Integer, User> getFromList(){
        return model;
    }

    public void remove(int id) {
        model.remove(id);
    }
    public void clear() {
        model.clear();
    }
    public void update (int id, String name, String surname, double salary){
        model.remove(id);
        model.put(id, new User(name, surname, salary));
    }

}
