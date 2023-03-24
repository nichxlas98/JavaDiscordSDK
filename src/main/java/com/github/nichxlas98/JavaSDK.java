package com.github.nichxlas98;

import com.github.nichxlas98.discordipc.exceptions.NoDiscordClientException;

public class JavaSDK {
    public static void main(String[] args) throws NoDiscordClientException {
        //TODO: Note, Discord must be running!!! - If you receive an error, it has to do with your Discord not being open.
        //TODO: You can also use try-catch on DiscordRPC.run() to catch any errors in case a user has their discord closed.
        DiscordRPC.run();
    }
}