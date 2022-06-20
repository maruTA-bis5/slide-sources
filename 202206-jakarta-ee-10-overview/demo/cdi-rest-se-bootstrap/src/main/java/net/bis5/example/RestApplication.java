package net.bis5.example;

import java.net.URI;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.SeBootstrap;
import jakarta.ws.rs.core.Application;

@ApplicationPath("")
public class RestApplication extends Application {

    public static void main(String[] args) throws InterruptedException {

        var config = SeBootstrap.Configuration.builder()
            .rootPath("app")
            .port(8880)
            .build();
        Application app = new RestApplication();
        SeBootstrap.start(app, config) // or: SeBootstrap.start(RestApplication.class, config)
            .thenAccept(instance -> {
                instance.stopOnShutdown(stopResult -> System.out.printf("Instance stopped"));
                URI uri = instance.configuration().baseUri(); 
                System.out.printf("Instance %s running at %s%n", instance, uri); 
                System.out.println("Send SIGKILL to shutdown.");
            });

        Thread.currentThread().join(); // これがないと起動後即終了してしまう
    }
}
