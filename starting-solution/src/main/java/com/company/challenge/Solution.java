package com.company.challenge;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Expecting a singular file argument.");
            return;
        }

        CSVParser parser = CSVParser.parse(new File(args[0]), StandardCharsets.UTF_8, CSVFormat.DEFAULT);
        List<CSVRecord> records = parser.getRecords();

        List<CustomerStatus> statuses = new ArrayList<>();

        Profiler profiler = new Profiler();

        for (CSVRecord record : records) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(record.get(0), formatter);
            String email = record.get(1);
            String event = record.get(2);

            CustomerStatus status = profiler.recordEvent(date, email, event);

            statuses.add(status);
        }

        // print result to stdout
        for (CustomerStatus status : statuses) {
            System.out.println(status.getStatusString());
        }

    }
}
