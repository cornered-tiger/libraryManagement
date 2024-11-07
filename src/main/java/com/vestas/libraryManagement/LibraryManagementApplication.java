package com.vestas.libraryManagement;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.valueaccess.MapValueReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	//TODO: move to config class
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().addValueReader(new MapValueReader());
		return mapper;
	}

}
