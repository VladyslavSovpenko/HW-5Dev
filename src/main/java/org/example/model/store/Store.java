package org.example.model.store;

import java.util.Date;

public class Store {

    private Integer id;
    private Integer petId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    private Integer quantity;
    private String shipDate;
    private String status;
    private boolean complete;


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


}
