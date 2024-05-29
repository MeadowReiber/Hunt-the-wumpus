
//Cypher Davis
//wumpus
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.lang.StringBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {
  //variables
  private Scanner reader;
  private Scanner lengthReader;
  private File f;
  private Player[] players;
  private int ogFileLength;
  //constructors
  public HighScore(){
    this.f = createFile("players.csv");
    try { 
      this.reader = new Scanner(f);
    } catch (FileNotFoundException e) {
      System.out.println("well thats concerning");
    }

    try{
      this.lengthReader = new Scanner(f);
    }catch(FileNotFoundException e){
      System.out.println("oopsies");
    }
    
    this.ogFileLength = getFileLength();
    this.players = createArray();

    
  }
  //methods

  //file methods
  public File createFile(String fileName){
    File f = new File(fileName);
    if(!f.exists()){
      try{
        f.createNewFile();
      }catch(IOException io){
        System.out.println("that sucks");
      }
    }
    return f;
  }

  public void addPlayer(Player p){
    boolean added = false;
    if(players.length == 1){
      players[0] = p;
    }else{
      for(int i = 0; i < players.length; i++){
        if(p.getHighScore() > players[i].getHighScore() && !added){
          for(int j = players.length - 1; j > i; j--){
            players[j] = players[j-1];
          }
          players[i] = p;
          added = true;
        }
      }
    }
  }

  public int getFileLength(){
    int length = 0;

    
    while(lengthReader.hasNextLine()){
      lengthReader.nextLine();
      length++;
    }
    
    return length;
  }

  public Player[] createArray(){
    if(ogFileLength <= 1){
      return new Player[1];
    }else{
      Player[] players = new Player[ogFileLength - 1];
      reader.nextLine();

      for(int i = 0; i < players.length; i++){
        String[] vars = reader.nextLine().split(",");
        int score = Integer.valueOf(vars[1]).intValue();
        Player p = new Player(vars[0], score );
        players[i] = p;
      }
    }
    return players;
  }

  public String[] turnIntoString(){
    String finalString[] = new String[players.length];

    for(int i = 0; i < players.length; i++){
      String[] shortArray = {players[i].getName(),String.valueOf(players[i].getHighScore())};
      String result = "";

      if(shortArray.length > 0){
        StringBuilder sb = new StringBuilder( );

        for(String s : shortArray){
          sb.append(s).append(",");
        }
        result = sb.deleteCharAt(sb.length() - 1).toString();
      }
      finalString[i] = result;
    }
    return finalString;
  }

  public void writeToFile(String[] str){
    try{
      FileWriter writer = new FileWriter(this.f,false);
      writer.write("name,highscore" + "\n");

      for(int i = 0; i < str.length; i++){
       if(i == str.length - 1){
        writer.write(str[i]);
       }else{
        writer.write(str[i] + "\n");
       }
      }
      writer.close();
      System.out.println();
    }catch (IOException e){
      System.out.println("oopsies i made a mistake");
    }
  }

  

// get array of highscore(player)l

//end of game methods to be called

// addPlayer(Player p); the player from the game returns nothing
// turnIntoString(); returns an array of Strings
// writeToFile(String[] str); uses the same array of strings from the methods before and writes those to the file



}
