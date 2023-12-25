package com.driver;

import org.apache.commons.lang3.tuple.Pair;
import java.util.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        Collections.sort(calendar, (a, b)->{
            LocalTime time1 = a.getStartTime();
            LocalTime time2 = b.getStartTime();
            return time1.compareTo(time2);
        });
        int maxMeeting = 0;
        LocalTime preEnd = LocalTime.parse("00:00");
        for(Meeting m: calendar) {
            System.out.print("Calender: ");
            System.out.print(m.getStartTime() + " - ");
            System.out.println(m.getEndTime());
            int res = preEnd.compareTo(m.getStartTime());
            if(res < 0) {
                maxMeeting++;
                preEnd = m.getEndTime();
            }else {
                res = preEnd.compareTo(m.getEndTime());
                if(res > 0) preEnd = m.getEndTime();
            }
        }

        return maxMeeting;
    }
}
