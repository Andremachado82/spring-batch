package com.andre.fatura_cartao_credito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FaturaCartaoCreditoApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(FaturaCartaoCreditoApplication.class, args);
		context.close();
	}
}
