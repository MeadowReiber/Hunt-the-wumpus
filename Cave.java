// The Cave Object keeps track of the cave, including data that describes the “connectivity information” for adjacent rooms.  Adjacent rooms may either be connected by way of a tunnel, or not connected at all (separated by a wall). The tasks it performs are as follows:



// Read and parse cave data from a file
// Keeps an internal data representation of the cave sufficient for representing connections for each room in the cave
// Exposes appropriate methods and/or attributes for other objects and the main program of the Hunt The Wumpus game.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class Cave {
    private HashMap<Integer, ArrayList<Integer>> roomConnections;

    public Cave() {
        roomConnections = new HashMap<>();
      
        // This initializes connections for all 30 rooms with empty sets
        for (int i = 1; i <= 30; i++) {
            roomConnections.put(i, new ArrayList<>());
        }
    }

    public void readCaveData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int room = Integer.parseInt(parts[0]);
                ArrayList<Integer> connections = roomConnections.get(room);
                for (int i = 1; i < parts.length; i++) {
                    int connectedRoom = Integer.parseInt(parts[i]);
                    connections.add(connectedRoom);
                    roomConnections.get(connectedRoom).add(room);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the cave data file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing room numbers: " + e.getMessage());
        }
    }

    public boolean isConnected(int roomA, int roomB) {
        if (roomConnections.containsKey(roomA)) {
            return roomConnections.get(roomA).contains(roomB);
        }
        return false;
    }


    public ArrayList<Integer> getAdjacentRooms(int room) {
    
        return roomConnections.getOrDefault(room, new ArrayList<>());
    }

    public void displayCave() {
        for (Integer room : roomConnections.keySet()) {
            ArrayList<Integer> connections = roomConnections.get(room);
            System.out.print("Room " + room + " connects to: ");
            for (Integer connectedRoom : connections) {
                System.out.print(connectedRoom + " ");
            }
            System.out.println();
        }
    }

    // Main method to check if this works
    public static void main(String[] args) {
        Cave cave = new Cave();

        // You can read the data from a file as follows:
        // cave.readCaveData("cave_data.txt");

        // You can also manually connect some rooms 
        cave.connectRooms(1, 2);
        cave.connectRooms(1, 30);
        cave.connectRooms(2, 3);
        

        // This displays the cave connections
        cave.displayCave();

        // This is how to check if two rooms are connected
        System.out.println("Are rooms 1 and 2 connected? " + cave.isConnected(1, 2));

        // This gets and display adjacent rooms for room 1
        System.out.println("Rooms adjacent to room 1: " + cave.getAdjacentRooms(1));
    }

    private void connectRooms(int roomA, int roomB) {
        // This private method is used only within this class to set up initial connections
        roomConnections.get(roomA).add(roomB);
        roomConnections.get(roomB).add(roomA);
    }
}

