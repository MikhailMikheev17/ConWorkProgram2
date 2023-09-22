package org.example.model;

import java.util.Random;
import java.util.PriorityQueue;


//Класс, который представляет  собой очередь из участников.
//Каждому участнику СЛУЧАЙНЫМ образом разыгрывается билет.


public class ParticipantQueue  {

    private int ticket;

    private Random random;
    private PriorityQueue<Participant> participants;

    public ParticipantQueue() {
        participants = new PriorityQueue<>();
        random = new Random();
    }

    public void addParticipant(String name) {participants.add(new Participant(random.nextInt(0,100), name)); }

    public boolean isEmpty() {
        return participants.isEmpty();
    }

    public int getParticipants() {

        return participants.peek() != null ? participants.peek().getTicket() : 0;
    }

    public void removeParticipants() {

        participants.poll();
    }
}