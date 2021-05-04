package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Station extends Model {
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public HashSet<Double> tempHashSet;
    public HashSet<Double> windHashSet;
    public HashSet<Integer> pressHashSet = new HashSet<>();
    public double lat;
    public double lng;

    public Station(String name)
    {
        this.name = name;
    }

    public Station(String name, double lat, double lng)
    {
        this.name = name;
        this.lat  = lat;
        this.lng = lng;
    }

    public void tempHashSet()
    {
        tempHashSet = new HashSet<>();
        for (int i = 0; i < readings.size(); i++) {
            tempHashSet.add(readings.get(i).temperature);
        }
    }

    public void pressHashSet()
    {
        pressHashSet = new HashSet<>();
        for (int i = 0; i < readings.size(); i++) {
            pressHashSet.add(readings.get(i).pressure);
        }
    }

    public void windHashSet()
    {
        windHashSet = new HashSet<>();
        for (int i = 0; i < readings.size(); i++) {
            windHashSet.add(readings.get(i).windSpeed);
        }
    }

}
