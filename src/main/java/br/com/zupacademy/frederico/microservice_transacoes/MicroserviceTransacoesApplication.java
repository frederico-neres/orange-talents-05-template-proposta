package br.com.zupacademy.frederico.microservice_transacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MicroserviceTransacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTransacoesApplication.class, args);
	}

}
