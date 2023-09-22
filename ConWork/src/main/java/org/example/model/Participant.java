package org.example.model;


/*
  Класс, который  представляет  собой участника розыгрыша.
 */
public class Participant implements Comparable<Participant> {

    private String name;
    private int ticket;

    public Participant(int id, String name) {
        this.name = name;
        this.ticket = id;
    }

    public int getTicket() {
        return ticket;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Участник " + name + " с номером билета " + ticket;
    }

    @Override
    public int compareTo(Participant o) {
        return this.ticket - o.getTicket();
    }
}