package com.spring.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {
    private final JobRepository jobRepository;

    @Bean
    public Job simpleJob(){
        return new JobBuilder("simpleJob", jobRepository)
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1(){
        return new StepBuilder("simpleStep1", jobRepository)
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>>> This is step1");
                    return RepeatStatus.FINISHED;
                })).build();
    }
}
