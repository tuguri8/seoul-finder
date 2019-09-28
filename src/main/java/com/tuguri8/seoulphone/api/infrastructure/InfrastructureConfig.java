package com.tuguri8.seoulphone.api.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.tuguri8.seoulphone.api")
@EnableJpaAuditing
public class InfrastructureConfig {
}
