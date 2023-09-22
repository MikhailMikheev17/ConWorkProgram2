package org.example;


import org.example.model.ParticipantQueue;
import org.example.model.ToyMachine;
import org.example.UI;


//Класс Контроллер

public class Controller {

    private ParticipantQueue participantQueue;

    private ToyMachine toyMachine;

    private UI userInterface;

    public Controller() {
        this.participantQueue = new ParticipantQueue();
        this.toyMachine = new ToyMachine(participantQueue);
        this.userInterface = new UI();
    }


    //Метод старта
    public void letsGo() {

        while (true) {
            String toy = userInterface.addToy();
            if (toy.equalsIgnoreCase("S")) break;
            else toyMachine.put(toy);
        }
        registration();
    }

    //Метод регистрации участников


    public void registration() {
        while (true) {
            String participant = userInterface.registrations();
            if (participant.equalsIgnoreCase("S")) break; // для завершения действия
            else {
                participantQueue.addParticipant(participant);
                System.out.println(UI.ticket + participantQueue.getParticipants());
            }
        }
        letsFun();
    }

    //Метод розыгрыша

    public void letsFun() {
        while (true) {
            System.out.println(UI.menu);
            int select = userInterface.select();
            switch (select) {
                case 1 -> toyMachine.getAllToys();
                case 2 -> {
                    toyMachine.letsFun();
                    return;
                }
                case 3 -> {
                    return;
                }
            }
        }
    }
}