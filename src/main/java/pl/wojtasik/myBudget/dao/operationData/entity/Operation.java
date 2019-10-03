package pl.wojtasik.myBudget.dao.operationData.entity;

import java.util.Date;

public class Operation {

    private Integer id;
    private Date date;
    private int category;
    private double price;
    private String description;
    private Date startDate;
    private Date endDate;

    public Operation(Date date, int category, double price , String description) {
        this(date, category, price,  description, null, null);
    }

    public Operation(Date date, int category, double price, String description, Date startDate, Date endDate) {
        this(null, date, category, price, description, startDate, endDate);
    }

    public Operation(Integer id, Date date, int category, double price , String description, Date startDate, Date endDate) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }


}
