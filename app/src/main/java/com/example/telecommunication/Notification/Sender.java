package com.example.telecommunication.Notification;

import android.provider.ContactsContract;

public class Sender {
    public Data data;
    public String to;

    public Sender(Data data, String to) {
        this.data = data;
        this.to = to;
    }
}
