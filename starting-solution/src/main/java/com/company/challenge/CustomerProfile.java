package com.company.challenge;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Profile of a customer, created at their first purchase
 *
 */
public class CustomerProfile {

    private String email;
    private LocalDate birthdate;
    private Map<LocalDate,EventType> history = new HashMap<>();

    public CustomerProfile() {

    }

    public CustomerProfile(String email, LocalDate birthdate, Map<LocalDate, EventType> history) {
        this.email = email;
        this.birthdate = birthdate;
        this.history = history;
    }

    /**
     * update the profile with an event
     *
     * @param eventDate
     * @param eventType
     */
    public void addEvent( LocalDate eventDate, EventType eventType) {
        history.put(eventDate, eventType);

        //set birthdate if this is the first event
        if (birthdate == null) {
            birthdate = eventDate;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Map<LocalDate, EventType> getHistory() {
        return history;
    }

    public void setHistory(Map<LocalDate, EventType> history) {
        this.history = history;
    }
}
