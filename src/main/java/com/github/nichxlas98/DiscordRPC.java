package com.github.nichxlas98;

import com.github.nichxlas98.discordipc.IPCClient;
import com.github.nichxlas98.discordipc.IPCListener;
import com.github.nichxlas98.discordipc.entities.RichPresence;
import com.github.nichxlas98.discordipc.entities.pipe.PipeStatus;
import com.github.nichxlas98.discordipc.exceptions.NoDiscordClientException;
import org.json.JSONObject;

import java.time.OffsetDateTime;

public class DiscordRPC {

    //TODO: Put in your Discord Application/Client ID in here. (you can find it at: https://discord.com/developers/applications)
    private static IPCClient ipcClient = new IPCClient(1087816598544449637L);
    private static OffsetDateTime timestamp = OffsetDateTime.now();
    private static boolean running = false;

    public static void run() throws NoDiscordClientException {
        ipcClient.setListener(new IPCListener() {
            @Override
            public void onReady(IPCClient client) {
                running = true;
                new Thread(() -> {
                    while (running) {
                        update();
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }).start();
            }

            @Override
            public void onClose(IPCClient client, JSONObject json) {
                running = false;
            }
        });
        ipcClient.connect();
    }

    private static void update() {
        RichPresence.Builder builder = new RichPresence.Builder();
        builder.setStartTimestamp(timestamp);
        builder.setLargeImage("cfb8fe2fe9169dc68f7f8c1236b885");

        //TODO: You can modify these however you please, these 4 lines are all details and so on. The version and everything is just an example.
        builder.setDetails("Developed by nichxlas98");
        String remoteIp = "192.168.1.1";
        builder.setState(remoteIp.equalsIgnoreCase("idling") ? "Idling" : "Playing on " + remoteIp);
        builder.setState("Showcase for 'julian248'");

        // Check ipc client is connected and send rpc
        if (ipcClient.getStatus() == PipeStatus.CONNECTED) {
            ipcClient.sendRichPresence(builder.build());
        }
    }

    public void stop() {
        ipcClient.close();
    }
}
