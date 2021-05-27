package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Booking {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("numberOfAdults")
    @Expose
    private Integer numberOfAdults;

    @SerializedName("numberOfChildren")
    @Expose
    private Integer numberOfChildren;

    @SerializedName("discount")
    @Expose
    private Integer discount;

    @SerializedName("client")
    @Expose
    private Client client;

    @SerializedName("employee")
    @Expose
    private Employee employee;

    @SerializedName("tour")
    @Expose
    private Tour tour;

    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
