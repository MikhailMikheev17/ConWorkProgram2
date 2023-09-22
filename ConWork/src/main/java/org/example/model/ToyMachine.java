package org.example.model;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
   Класс - имитация розыгрыша
*/


public class ToyMachine implements ToyMachineInterface {

    private int id;

    private ParticipantQueue participantQueue;

    private List<Toy> toys;

    private Random random;

    public ToyMachine(ParticipantQueue participantQueue) {
        this.participantQueue = participantQueue;
        random = new Random();
        toys = new ArrayList<>();
    }


    @Override
    public void put(String name) {
        int weight = random.nextInt(100);
        toys.add(new Toy(++id, name, weight));
    }


    @Override
    public Toy getToy() {
        if (toys.isEmpty()) {
            return null;
        }
        int totalWeight = toys.stream().mapToInt(Toy::getWeight).sum();
        int randomValue = random.nextInt(totalWeight) + 1;
        int cumulativeWeight = 0;
        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randomValue <= cumulativeWeight) {
                toys.remove(toy);
                return toy;
            }
        }
        return null;
    }

    /*
      Метод перебора всех элементов коллекции Toy.
    */

    @Override
    public void getAllToys() {
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }


    @Override
    public void write(String name) {
        try (FileWriter fw = new FileWriter("result.txt", true)) {
            fw.write(name);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void letsFun() {
        while (!participantQueue.isEmpty()) {
            Toy toy = getToy();
            if (toy == null) {
                System.out.println("Розыгрыш завершен");
                break;
            } else {
                String result = "Участник с номером " + participantQueue.getParticipants() +
                        " получает игрушку с номером " + toy.getId() + "(" + toy.getName() + ")";
                write(result +  "\n");
                System.out.println(result);
                participantQueue.removeParticipants();
            }
        }
        if (participantQueue.isEmpty()) System.out.println("Очередь пуста");
    }
}