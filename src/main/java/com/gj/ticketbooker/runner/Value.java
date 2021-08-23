package com.gj.ticketbooker.runner;

import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.List;

@Component
public class Value {
    public static Hashtable<String, List<Integer>> ticketRequests = new Hashtable<>();
    public static Hashtable<String, List<Integer>> ticketResults = new Hashtable<>();
    public volatile static boolean keepThreadActive = true;
}
