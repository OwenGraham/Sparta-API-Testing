package com.github.owengraham.rest_assured_project;

import java.io.IOException;

public class RestartDocker {

    public static void restart() throws IOException, InterruptedException {
        String command;
        String[] shellCommand;

        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            command = "docker restart 4e41793f26567410f23bb237cd137b1ea414353fc07a4c2e3dc27d58967fb4d6";
            shellCommand = new String[]{"cmd", "/c", command};
        } else {
            command = "docker restart 4e41793f26567410f23bb237cd137b1ea414353fc07a4c2e3dc27d58967fb4d6";
            shellCommand = new String[]{"bash", "-c", command};
        }

        ProcessBuilder builder = new ProcessBuilder(shellCommand);
        Process process = builder.start();

        int exitCode = process.waitFor();

        if (exitCode == 0) {
            System.out.println("Docker command executed successfully!");
        } else {
            System.err.println("Error executing Docker command. Exit code: " + exitCode);
        }
    }
}
