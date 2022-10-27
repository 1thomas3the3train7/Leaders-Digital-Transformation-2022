package com.example.gateway;


import com.example.gateway.Config.MyMap;
import com.pdfcrowd.Pdfcrowd;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.fit.pdfdom.PDFDomTree;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
