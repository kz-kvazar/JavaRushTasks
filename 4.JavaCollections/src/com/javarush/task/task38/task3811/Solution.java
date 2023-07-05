package com.javarush.task.task38.task3811;

/* 
Тикеты
*/

@Ticket(
        priority = Ticket.Priority.HIGH,
        createdBy = "Noodles",
        tags = {"bug", "fix asap"}
)
public class Solution {
    public static void main(String[] args) {
        Ticket ticket = Solution.class.getAnnotation(Ticket.class);
        String createdBy = ticket.createdBy();
        System.out.println(createdBy);
    }
}
