package com.project.transactionApi;

import com.project.transactionApi.Configurations.DynamoDBConfig;
import com.project.transactionApi.Repositories.Abstractions.IAccountRepository;
import com.project.transactionApi.Repositories.Abstractions.IAccountTransactionsRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@EnableDynamoDBRepositories(basePackageClasses = {
		IAccountRepository.class,
		IAccountTransactionsRepository.class
})
@Configuration
@Import({DynamoDBConfig.class})
public class TransactionApiApplication {

	public static void main(String[] args) {
		run(TransactionApiApplication.class, args);
	}

}
