package com.gj.ticketbooker.runner;

import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.List;

/*
This class contains the variables for giving input to and getting output from the AsyncRunner thread
 */
@Component
public class ThreadAdapter {
    public static Hashtable<String, List<Integer>> ticketRequests = new Hashtable<>();
    public static Hashtable<String, List<Integer>> ticketResults = new Hashtable<>();
    public volatile static boolean keepThreadActive = true;
}
