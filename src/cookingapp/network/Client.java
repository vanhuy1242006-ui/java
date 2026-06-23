package cookingapp.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static Response send(
            Request req
    ) {

        try {

            Socket socket
                    = new Socket(
                            "localhost",
                            1234
                    );

            ObjectOutputStream out
                    = new ObjectOutputStream(
                            socket.getOutputStream()
                    );

            ObjectInputStream in
                    = new ObjectInputStream(
                            socket.getInputStream()
                    );

            out.writeObject(req);

            out.flush();

            Response res
                    = (Response) in.readObject();

            socket.close();

            return res;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

}