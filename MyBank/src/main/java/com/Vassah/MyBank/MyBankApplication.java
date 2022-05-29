package com.Vassah.MyBank;

/*import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class MyBankApplication {

	private static final Logger log = LoggerFactory.getLogger(MyBankApplication.class);
	public static void main(String[] args) {
/*
		Server starting
		try(ServerSocket server = new ServerSocket(8000)){
			System.out.println("Server started");

			while(true)
			try
			( 
				Socket socket = server.accept();
				BufferedWriter bufwriter = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));

				BufferedReader bufreader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
			)
			{
				String request = bufreader.readLine();
				System.out.println("Request: "+request);

				String response = "Server is working";
				System.out.println("Response: "+response);

				bufwriter.write(response);
				bufwriter.newLine();
				bufwriter.flush();
			}

		}catch(IOException e){	
			throw new RuntimeException(e);
		}
		*/


		SpringApplication application = new SpringApplication(MyBankApplication.class);
		application.addListeners(new ApplicationPidFileWriter("./app.pid"));
		application.run();
	}
}