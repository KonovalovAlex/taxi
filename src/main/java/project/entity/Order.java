package project.entity;


public class Order extends AbstractEntity {
    private String street;
    private String numberOfHouse;
    private String numberOfApartment;
    private String time;
    private int fkUser;
    private OrderStatus statusOfOrder;
    private User phone;

    public String getPhone() {
        return phone.getPhone();
    }

    public void setPhone(User phone) {
        this.phone = phone;
    }

    public OrderStatus getStatusOfOrder() {
        return statusOfOrder;
    }

    public void setStatusOfOrder(OrderStatus statusOfOrder) {
        this.statusOfOrder = statusOfOrder;
    }

    public int getFkUser() {
        return fkUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setFkUser(int fkUser) {
        this.fkUser = fkUser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(String numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public String getNumberOfApartment() {
        return numberOfApartment;
    }

    public void setNumberOfApartment(String numberOfApartment) {
        this.numberOfApartment = numberOfApartment;
    }
}


