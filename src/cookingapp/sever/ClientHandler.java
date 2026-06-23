/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingapp.sever;

import cookingapp.network.Response;
import cookingapp.network.Request;
import java.sql.Connection;
import cookingapp.DBConnection;
import cookingapp.model.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class ClientHandler implements Runnable {

    private Socket socket;//socket đại diện cho client
    private Connection conn = null;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(Socket soc_client) {
        this.socket = soc_client;
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void run() {
        try {
            // tạo IO trước
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Request req = (Request) in.readObject();//nhận request
                Response res = action(req);//trả response
                out.writeObject(res);
                out.flush();
            }
        } catch (Exception e) {
            System.out.println("Client disconnected");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Response action(Request req) {
        Response res = new Response();
        switch (req.getAction()) {
            case "LOGIN": {

                User user
                        = (User) req.getData();

                User userDB
                        = UserDAO.login(
                                user.getUsername(),
                                user.getPassword()
                        );

                if (userDB != null) {

                    res.setStatus(
                            "SUCCESS"
                    );

                    res.setMessage(
                            "Đăng nhập thành công"
                    );

                    res.setData(
                            userDB
                    );

                } else {

                    res.setStatus(
                            "FAIL"
                    );

                    res.setMessage(
                            "Sai tài khoản"
                    );

                }

                break;

            }
            case "REGISTER": {

                try {

                    User user
                            = (User) req.getData();

                    boolean ok
                            = UserDAO.register(
                                    user
                            );

                    if (ok) {

                        res.setStatus(
                                "SUCCESS"
                        );

                        res.setMessage(
                                "Đăng ký thành công"
                        );

                    } else {

                        res.setStatus(
                                "FAIL"
                        );

                        res.setMessage(
                                "Đăng ký thất bại"
                        );

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                    res.setStatus(
                            "ERROR"
                    );

                    res.setMessage(
                            e.getMessage()
                    );

                }

                break;

            }

            default: {
                res.setStatus("ERROR");
                res.setMessage("UNKNOWN ACTION: " + req.getAction());
                res.setData(null);
            }

        }
        return res;
    }
}
