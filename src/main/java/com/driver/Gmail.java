package com.driver;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    List<Mail> inboxMail = new ArrayList<>();
    List<Mail> trashMail = new ArrayList<>();
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public Gmail(String emailId) {
        super(emailId);
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Mail newMail = new Mail(date, sender, message);
        if(inboxMail.size() < inboxCapacity) {
            inboxMail.add(newMail);
        }else {
            Mail removedMail = inboxMail.remove(0);
            trashMail.add(removedMail);
            inboxMail.add(newMail);
        }
        System.out.println("Gmail Added!");
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i = 0; i < inboxMail.size(); i++) {
            Mail mail = inboxMail.get(i);
            if(message.equals(mail.message)) {
                inboxMail.remove(i);
                trashMail.add(mail);
                System.out.println("Gmail Deleted!");
                return;
            }
        }
        System.out.println("Gmail not found");
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        String message = "Inbox is empty";
        if(inboxMail.isEmpty()) return message;
        message = inboxMail.get(inboxMail.size()-1).message;
        return message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        String message = "Inbox is empty";
        if(inboxMail.isEmpty()) return message;
        message = inboxMail.get(0).message;
        return message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(Mail mail: inboxMail) {
            Date emailDate = mail.date;
            if(start.compareTo(emailDate) * emailDate.compareTo(end) >= 0) count++;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxMail.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashMail.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashMail.clear();
        System.out.println("Trash is empty!");
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity - inboxMail.size();
    }

    public static class Mail {
        Date date;
        String sender;
        String message;

        public Mail (Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }
}
