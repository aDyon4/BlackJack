package com.example.alap;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.*;


public class HelloController {

    @FXML private TextField tfText;
    @FXML private ListView<String> lvList;

    DatagramSocket socket = null;

    public void initialize(){
        try {
            socket = new DatagramSocket(678);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                fogad();
            }
        });
        t.setDaemon(true);
        t.start();

    }

    private void fogad(){
        byte[] adat = new byte[256];
        DatagramPacket packet = new DatagramPacket(adat, adat.length);
        while (true) {
            try {
                socket.receive(packet);
                String uzenet = new String(adat, 0, packet.getLength(), "utf-8");
                String ip = packet.getAddress().getHostAddress();
                int port = packet.getPort();
                Platform.runLater(() -> onFogad(uzenet, ip, port));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void onFogad(String uzenet, String ip, int port) {
        String[] s = uzenet.split(":");
        int tet = Integer.parseInt(s[1]);

        System.out.printf("TÉT: %d", tet);
        //kuld(tet, ip, port);
    }
/*
    private void kuld(int u, String ip, int port){
        try {
            byte[] adat = uzenet.getBytes("utf-8");
            InetAddress ipv4 = Inet4Address.getByName(ip);
            DatagramPacket packet = new DatagramPacket(adat, adat.length, ipv4, port);
            socket.send(packet);
            System.out.printf("%s:%d -> %s\n", ip, port, uzenet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}