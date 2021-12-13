package org.example.model.store;

import java.util.Date;

public class Store {
    private long id;
    private long petId;
    private long quantity;
    private  String status;
    private String complete;
    private Date shipDate;

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", complete='" + complete + '\'' +
                ", shipDate=" + shipDate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }
}
