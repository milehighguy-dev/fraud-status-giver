package com.company.challenge;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class will discriminate profiles as events happen
 *
 */
public class Profiler {

    List<CustomerProfile> profiles = new ArrayList<>();

    /**
     * record the event
     *
     * @param date
     * @param email
     * @param event
     */
    public CustomerStatus recordEvent(LocalDate date, String email, String event) {

        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setEmail(email);

        EventType eventType = EventType.valueOf(event);

        //check if the customer already has been tracked by our system
        boolean newProfile = true;
        for ( CustomerProfile profile : profiles) {

            if (profile.getEmail().equals(email)) {
                //update their history
                profile.addEvent(date, eventType);

                customerProfile = profile;
                newProfile = false;
                break;
            }
        }

        //add an entry for new profile
        if (newProfile) {

            customerProfile.addEvent(date, eventType);
            profiles.add(customerProfile);
        }

        return calcStatus(customerProfile, date);
    }

    /**
     * return the latest status of the last event's profile
     */
    public CustomerStatus calcStatus(CustomerProfile profile, LocalDate latestDate) {

        CustomerStatus status = new CustomerStatus();
        status.setEmail(profile.getEmail());
        status.setStatusDate(latestDate);

        if(profile.getHistory().size() == 1){
            status.setStatus(Status.NO_HISTORY);
        } else {

            for (Map.Entry<LocalDate, EventType > entry : profile.getHistory().entrySet()) {

                if (entry.getValue() == EventType.FRAUD_REPORT) {
                    status.setStatus(Status.FRAUD_HISTORY);
                    status.incrementCount();
                }
            }
        }

        LocalDate currentDate = LocalDate.now();
        //TODO Period object returning wrong day count, ignoring days between different years :(
        // fixed, comment left for reference
//        Period profileAge = Period.between(profile.getBirthdate(), currentDate);
        long age = ChronoUnit.DAYS.between(profile.getBirthdate(),currentDate );
        if (status.getStatus() == null) {

            if (age > 90) {

                status.setStatus(Status.GOOD_HISTORY);
                for (Map.Entry<LocalDate, EventType > entry : profile.getHistory().entrySet()) {
                    if (ChronoUnit.DAYS.between(entry.getKey() ,currentDate ) > 90) {
                        status.incrementCount();
                    }
                }

            } else {

                status.setStatus(Status.UNCONFIRMED_HISTORY);
                status.setStatusCount(profile.getHistory().size());

            }
        }

        return status;
    }

}
