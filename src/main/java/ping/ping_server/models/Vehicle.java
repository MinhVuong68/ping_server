package ping.ping_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;
    @Column(columnDefinition = "nvarchar(50)")
    private String nameVehicle;

    @Column(columnDefinition = "nvarchar(255)")
    private String imageVehicle;
    private Long weight;
    private double cbm;
    private double length;
    private double width;
    private double height;

    @OneToMany(mappedBy = "vehicle")
    private List<Driver> drivers;


    public Vehicle() {
    }

    public Vehicle(Long id, String nameVehicle, String imageVehicle, Long weight, double cbm, double length, double width, double height) {
        this.id = id;
        this.nameVehicle = nameVehicle;
        this.imageVehicle = imageVehicle;
        this.weight = weight;
        this.cbm = cbm;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameVehicle() {
        return nameVehicle;
    }

    public void setNameVehicle(String nameVehicle) {
        this.nameVehicle = nameVehicle;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public double getCbm() {
        return cbm;
    }

    public void setCbm(double cbm) {
        this.cbm = cbm;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getImageVehicle() {
        return imageVehicle;
    }

    public void setImageVehicle(String imageVehicle) {
        this.imageVehicle = imageVehicle;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", nameVehicle='" + nameVehicle + '\'' +
                ", imageVehicle='" + imageVehicle + '\'' +
                ", weight=" + weight +
                ", cbm=" + cbm +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
