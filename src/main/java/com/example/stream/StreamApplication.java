package com.example.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class StreamApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StreamApplication.class, args);
	}

	@Override
	public void run(String... args) {
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		somaParalela(numeros.parallelStream());
		execucaoSomaSequencial(numeros.stream());
	}

	private static void somaParalela(Stream<Integer> numeros) {
		// Operação paralela
		StopWatch paralelaStopWatch = new StopWatch();
		paralelaStopWatch.start();
		long somaParalela = numeros
				.reduce(0, Integer::sum);
		paralelaStopWatch.stop();

		log.info("Soma Paralela: {}", somaParalela);
		log.info("Tempo de Execução (Paralela): {} ms", paralelaStopWatch.getTotalTimeMillis());
	}

	private static void execucaoSomaSequencial(Stream<Integer> numeros) {
		// Operação sequencial
		StopWatch sequencialStopWatch = new StopWatch();
		sequencialStopWatch.start();
		long somaSequencial = numeros
				.reduce(0, Integer::sum);
		sequencialStopWatch.stop();

		log.info("Soma Sequencial: {}", somaSequencial);
		log.info("Tempo de Execução (Sequencial): {} ms", sequencialStopWatch.getTotalTimeMillis());
	}

}
