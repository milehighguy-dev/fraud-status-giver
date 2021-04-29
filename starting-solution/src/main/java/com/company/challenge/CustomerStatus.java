package com.company.challenge;

import java.time.LocalDate;

/**
 * holds the status of a customer based on their current profile
 *
 */
public class CustomerStatus {

    private LocalDate statusDate;
    private String email;
    private Status status;

    //number holding counts relevant to different statuses
    private int statusCount = 0;

    public CustomerStatus() {

    }

    public CustomerStatus(LocalDate statusDate, String email, Status status, int statusCount) {
        this.statusDate = statusDate;
        this.email = email;
        this.status = status;
        this.statusCount = statusCount;
    }

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(int statusCount) {
        this.statusCount = statusCount;
    }

    public void incrementCount() {
        this.statusCount++;
    }


    public String getStatusString() {
        String returnString =
                statusDate.toString()
                + "," + email
                + "," + status.toString();
        if (status != Status.NO_HISTORY) {
            returnString += ":" + statusCount;
        }
        return returnString;
    }
}
