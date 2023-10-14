import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setQuantity(int i) {
    }
}

public class javaast {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public javaast() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(int id, String name, int quantity, double weight) {
        Toy toy = new Toy(id, name, quantity, weight);
        toys.add(toy);
    }

    public void updateToyWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                break;
            }
        }
    }

 public void drawPrizeToy() {
     double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
     if (totalWeight <= 0) {
        System.out.println("no toys for drawprize.");
        return;
        }

        Random random = new Random();
        double randomNumber = random.nextDouble() * 100;
        double cumulativeWeight = 0;

        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randomNumber <= cumulativeWeight) {
                prizeToys.add(toy);
                toy.setQuantity(toy.getQuantity() - 1);
                break;
            }
        }
    }
    public Toy getPrizeToy() {
        if (!prizeToys.isEmpty()) {
            Toy prizeToy = prizeToys.remove(0);
            try (FileWriter fileWriter = new FileWriter("prize_toys.txt", true)) {
             fileWriter.write(prizeToy.getName() + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prizeToy;
        } else {
            System.out.println("no prize toys to get.");
            return null;
        }
    }
    public static void main(String[] args) {
        javaast toyStore = new javaast();
        toyStore.addToy(1, "teddy bear", 15, 12);
        toyStore.addToy(2, "doll nancy", 10, 10);
        toyStore.addToy(3, "red ball", 5, 7);

        toyStore.updateToyWeight(2, 10);

        toyStore.drawPrizeToy();
        toyStore.drawPrizeToy();
        toyStore.drawPrizeToy();

        Toy prizeToy = toyStore.getPrizeToy();
        if (prizeToy != null) {
            System.out.println("now you got the prize toy: " + prizeToy.getName());
        }
    }
}
