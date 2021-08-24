package com.gj.ticketbooker.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
This class defines the thread that starts running once the application is loaded. It stops if any exception is thrown
 */
@Component
public class AsyncRunner extends Thread {

    Hashtable<String, List<Integer>> ticketList;

    @Value("${asyncThreadWaitTime}")
    private long asyncThreadWaitTime;

    /*
    This method runs in an interval of asyncThreadWaitTime(specified in application.properties) seconds.
    It finds the user with maximum number of tickets and adds him to a result hashtable.
    If multiple users request for the same tickets and same number of tickets then the
    user is chosen randomly(It is via the hashing algorithm of Hashtable i.e. the user who is the
    first record in the hash table) is chosen.
     */
    public void run() {
        boolean isAllSeatsAvailable;
        Hashtable<String, List<Integer>> ticketRequests;
        List<String> toBeRemovedUsers;

        while (ThreadAdapter.keepThreadActive) {
            try {
                Thread.sleep(asyncThreadWaitTime*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketRequests = (Hashtable<String, List<Integer>>) ThreadAdapter.ticketRequests.clone();
            ticketList = new Hashtable<>();

            for (Map.Entry<String, List<Integer>> ticketRequest : ticketRequests.entrySet()) {
                isAllSeatsAvailable = true;
                toBeRemovedUsers = new ArrayList<>();
                for (int i = 0; i < ticketRequest.getValue().size() && isAllSeatsAvailable; i++) {
                    isAllSeatsAvailable = true;
                    int finalI = ticketRequest.getValue().get(i);

                    //Stream through the hashtable to check if any user is requesting for same ticket value
                    Map<String, List<Integer>> alreadyReq = ticketList.entrySet()
                            .stream()
                            .filter(map -> map.getValue().contains(finalI))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    if (alreadyReq.size() > 0) {
                        for (Map.Entry<String, List<Integer>> oneReq : alreadyReq.entrySet()) {
                            /*if the no.of tickets requested by this user is greater than the other user
                            present in the list(previously processed record) who has requested
                            the same seat then add the old user to a list
                             */
                            if (oneReq.getValue().size() < ticketRequest.getValue().size()) {
                                toBeRemovedUsers.add(oneReq.getKey());
                            } else {
                                isAllSeatsAvailable = false;
                                break;
                            }
                        }
                    }
                }
                if (isAllSeatsAvailable) {
                    /* if all the seats requested by the user is available remove all the users
                    who would have requested any of the seats requested by this user with a total
                    number of tickets which is less than the total number of tickets requested by this user
                    * */
                    for (String name : toBeRemovedUsers) {
                        ticketList.remove(name);
                    }
                    ticketList.put(ticketRequest.getKey(), ticketRequest.getValue());
                }
                ThreadAdapter.ticketRequests.remove(ticketRequest.getKey());
            }
            ThreadAdapter.ticketResults = (Hashtable<String, List<Integer>>) ticketList.clone();

            synchronized (this) {
                this.notifyAll();
            }
        }

    }
}
