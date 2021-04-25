package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station extends Model {
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public double lat;
    public double lng;
    public int latestLoc;
    public String weather;
    public double tempC;
    public double tempF;
    public int wind;


    public Station(String name, int lat, int lng)
    {
        this.name = name;
        this.lat  = lat;
        this.lng = lng;
    }

}
