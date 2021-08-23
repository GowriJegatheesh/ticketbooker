package com.gj.ticketbooker.runner;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AsyncRunner extends Thread {

    Hashtable<String, List<Integer>> ticketList;

    public void run() {
        boolean isAllSeatsAvailable;
        Hashtable<String, List<Integer>> ticketRequests;
        List<String> toBeRemovedUsers;

        while (Value.keepThreadActive) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketRequests = (Hashtable<String, List<Integer>>) Value.ticketRequests.clone();

            ticketList = new Hashtable<>();

            for (Map.Entry<String, List<Integer>> ticketRequest : ticketRequests.entrySet()) {
                isAllSeatsAvailable = true;
                toBeRemovedUsers = new ArrayList<>();
                for (int i = 0; i < ticketRequest.getValue().size() && isAllSeatsAvailable; i++) {
                    isAllSeatsAvailable = true;
                    int finalI = ticketRequest.getValue().get(i);
                    Map<String, List<Integer>> alreadyReq = ticketList.entrySet()
                            .stream()
                            .filter(map -> map.getValue().contains(finalI))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    if (alreadyReq.size() > 0) {
                        for (Map.Entry<String, List<Integer>> oneReq : alreadyReq.entrySet()) {
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
                    for (String name : toBeRemovedUsers) {
                        ticketList.remove(name);
                    }
                    ticketList.put(ticketRequest.getKey(), ticketRequest.getValue());
                }
                Value.ticketRequests.remove(ticketRequest.getKey());
            }
            Value.ticketResults = (Hashtable<String, List<Integer>>) ticketList.clone();

            synchronized (this) {
                this.notifyAll();
            }
        }

    }
//    public CompletableFuture<HashMap<String, List<Integer>>> processTicket(){
//
//        try{
//            Thread.sleep(10000);
//        }
//        catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        System.out.println("Called once----------------------------------------------------");
//        HashMap<String, List<Integer>> ticketRequests = Value.ticketRequests;
//        ticketList = new HashMap<>();
//        for(Map.Entry<String, List<Integer>> ticketRequest: ticketRequests.entrySet()){
//            boolean isAllSeatsAvailable = true;
//            List<String> toBeRemovedUsers = new ArrayList<>();
//            for (int i : ticketRequest.getValue()) {
//                isAllSeatsAvailable = true;
//                Map<String, List<Integer>> alreadyReq = ticketList.entrySet()
//                        .stream()
//                        .filter(map -> map.getValue().contains(i))
//                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//                if (alreadyReq.size() > 1) {
//                    for (Map.Entry<String, List<Integer>> oneReq : alreadyReq.entrySet()) {
//                        if (oneReq.getValue().size() < ticketRequest.getValue().size()) {
//                            toBeRemovedUsers.add(oneReq.getKey());
//                        } else {
//                            isAllSeatsAvailable = false;
//                            break;
//                        }
//                    }
//                }
//            }
//            if (isAllSeatsAvailable) {
//                for (String name: toBeRemovedUsers) {
//                    ticketList.remove(name);
//                }
//                ticketList.put(ticketRequest.getKey(), ticketRequest.getValue());
//            }
//            else{
//                new TicketResponse("Ticket Unavailable");
//            }
//
//        }
//
//        for(Map.Entry<String, List<Integer>> ticketRequest: ticketRequests.entrySet()){
//            Value.ticketRequests.remove(ticketRequest.getKey());
//        }
//
//        return CompletableFuture.completedFuture(ticketList);
//    }
}
