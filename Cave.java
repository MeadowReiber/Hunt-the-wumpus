public class Cave {
    public Cave{
        int wumpusRoom;

    }

    public static int createRoom(){
        int left;
        int right;
        int top;
        int bottom;
        left = (int)(Math.random * 3);
        right = (int)(Math.random * 3);
        top = (int)(Math.random * 3);
        bottom = (int)(Math.random * 3);
    }
    
    public static int getRoom(){
        return wumpusRoom;
    }
}
