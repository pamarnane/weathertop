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
    public double lat;
    public double lng;
    public Summary summary;

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
}
