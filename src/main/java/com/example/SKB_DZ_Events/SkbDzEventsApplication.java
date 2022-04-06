package com.example.SKB_DZ_Events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Scanner;

@SpringBootApplication
@EnableAsync
public class SkbDzEventsApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SkbDzEventsApplication.class, args);
		var f = context.getBean(TextFormatter.class);

        System.out.println("Enter the text you want to format: ");
		Scanner sc = new Scanner(System.in);
		f.formatText(sc.nextLine());
	}
}
