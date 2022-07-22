package com.example.sentence.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.core.IntervalFunction;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean("cbConfig1")
    CircuitBreaker circuitBreaker() {
        return CircuitBreaker.of("cbConfig1", this.buildConfig1());
    }
    
    @Bean("cbConfig2")
    CircuitBreaker circuitBreaker2() {
        return CircuitBreaker.of("cbConfig2", this.buildConfig4());
    }

	private CircuitBreakerConfig buildConfig1() {
		return CircuitBreakerConfig.custom()
				.slidingWindowType(SlidingWindowType.COUNT_BASED)
				.slidingWindowSize(4) //por defecto
				.failureRateThreshold(50f) //por defecto es 100%
				.permittedNumberOfCallsInHalfOpenState(2) // por defecto es 10
				.waitDurationInOpenState(Duration.ofSeconds(5)) // por defecto es 60s
				.automaticTransitionFromOpenToHalfOpenEnabled(true)
				.writableStackTraceEnabled(false)
				.build()
				;
	}
	
	private CircuitBreakerConfig buildConfig2() {
		return CircuitBreakerConfig.custom()
				.slidingWindowType(SlidingWindowType.COUNT_BASED)
				.slidingWindowSize(4) //por defecto
				.failureRateThreshold(50f) //por defecto es 100%
				.permittedNumberOfCallsInHalfOpenState(2) // por defecto es 10
				.waitIntervalFunctionInOpenState(
						IntervalFunction.ofExponentialBackoff(Duration.ofSeconds(2), 3, Duration.ofSeconds(100)) 
				)//el tiempo que esperara en estado abierto antes de pasar a medio abierto, va de forma exponencial, empieza 2s luego 3 a 3, 4 a 3
				.automaticTransitionFromOpenToHalfOpenEnabled(true)
				.writableStackTraceEnabled(false)
				.build()
				;
	}
	
	private CircuitBreakerConfig buildConfig3() {
		return CircuitBreakerConfig.custom()
				.slidingWindowType(SlidingWindowType.COUNT_BASED)
				.slidingWindowSize(4) //default
				.failureRateThreshold(50f) //default 100%
				.permittedNumberOfCallsInHalfOpenState(2) // default 10
				.waitDurationInOpenState(Duration.ofSeconds(5)) // default 60s(fixed)
				.slowCallRateThreshold(20f) //20% de llamadas son lentas
				.slowCallDurationThreshold(Duration.ofSeconds(2)) // > 2s es lento
				.automaticTransitionFromOpenToHalfOpenEnabled(true)
				.writableStackTraceEnabled(false)
				.build()
				;
	}
 
	private CircuitBreakerConfig buildConfig4() {
		return CircuitBreakerConfig.custom()
				.slidingWindowType(SlidingWindowType.TIME_BASED)
				.slidingWindowSize(4) //default
				.failureRateThreshold(50f) //default 100%
				.permittedNumberOfCallsInHalfOpenState(2) // default 10
				.waitDurationInOpenState(Duration.ofSeconds(2)) // default 60s(fixed)
				.writableStackTraceEnabled(false)
				.automaticTransitionFromOpenToHalfOpenEnabled(true)
				.minimumNumberOfCalls(4)  
				.build()
				;
	}
} 

