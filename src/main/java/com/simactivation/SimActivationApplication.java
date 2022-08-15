package com.simactivation;

import com.simactivation.util.AddressMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SimActivationApplication {

	public static void main(String[] args) throws IOException {
		SimActivationApplication.initApp();
		SpringApplication.run(SimActivationApplication.class, args);
	}

	public static void initApp() throws IOException {
		AddressMapper addressMapper= new AddressMapper();
		addressMapper.writeConfig();
	}
}
