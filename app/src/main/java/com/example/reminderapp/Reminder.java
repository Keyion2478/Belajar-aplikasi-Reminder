package com.example.reminderapp;

public class Reminder {

    private int id;
    private String title;
    private String description;
    private String date;
    private String time;
    private String category;
    private String repeatType;
    private String status;

    // Constructor kosong
    public Reminder() {
    }

    // Constructor lengkap
    public Reminder(int id,
                    String title,
                    String description,
                    String date,
                    String time,
                    String category,
                    String repeatType,
                    String status) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.category = category;
        this.repeatType = repeatType;
        this.status = status;
    }

    // Getter dan Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}