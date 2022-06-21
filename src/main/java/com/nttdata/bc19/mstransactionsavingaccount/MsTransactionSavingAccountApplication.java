package com.nttdata.bc19.mstransactionsavingaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsTransactionSavingAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTransactionSavingAccountApplication.class, args);
	}

}
