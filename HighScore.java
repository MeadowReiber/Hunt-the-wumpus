
//Cypher Davis
//wumpus
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.lang.Integer;

public class HighScore {
  //variables
  private Scanner user;
  private Scanner reader;
  //constructors
  public HighScore(){
    this.user = new Scanner(System.in);
    File f = createFile("players.csv");
    try { 
      this.reader = new Scanner(f);
    } catch (FileNotFoundException e) {
      System.out.println("well thats concerning");
    }

    
  }
  //methods

  //file methods
  public File createFile(String fileName){
    File f = new File(fileName);
    return f;
  }

  public void sortFile(){
    //????? i dont know if this is necesary ?????????
  }

  public int getFileLength(){
    int length = 0;

    
    while(reader.hasNextLine()){
      user.nextLine();
      length++;
    }
    
    return length;
  }

  public Player[] createArray(Scanner reader, int length, String name){
    Player[] players = new Player[length - 1];
    user.nextLine();

    for(int i = 0; i < players.length; i++){
      String[] vars = reader.nextLine().split(",");
      int score = Integer.valueOf(vars[1]).intValue();
      Player p = new Player(vars[0], score );
      players[i] = p;
    }
    
    return players;
  }

  public String[] turnIntoString(Player[] players, String name, int highScore){
    return new String[] {"lol"};//FIX THIS LATER
  }

  public void writeToFile(String[] str, Player p){

  }

  //score methods
  public void displayScore(){
    
  }

// get array of highscore(player)
// if players int is high enough then replace them in the array and fix it
// turn into array of strings
// delete file info
// write array to the file


}
