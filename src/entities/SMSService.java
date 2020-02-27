package com.esprit.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSService {
    private static SMSService instance;

    public static SMSService getInstance() {
        if (instance == null) {
            instance = new SMSService();
        }
        return instance;
    }
    public void send(int number, String msg) {
        Twilio.init("code", "code");
        Message.creator(new PhoneNumber("+216" + number), new PhoneNumber("+14692676614"), msg).create().getSid();
    }
}
