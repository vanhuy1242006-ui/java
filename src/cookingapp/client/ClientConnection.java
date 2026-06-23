package cookingapp.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientConnection {

    private static ObjectInputStream in;

    private static ObjectOutputStream out;

    public static void setIn(
            ObjectInputStream i
    ){
        in=i;
    }

    public static void setOut(
            ObjectOutputStream o
    ){
        out=o;
    }

    public static ObjectInputStream getIn(){
        return in;
    }

    public static ObjectOutputStream getOut(){
        return out;
    }

}