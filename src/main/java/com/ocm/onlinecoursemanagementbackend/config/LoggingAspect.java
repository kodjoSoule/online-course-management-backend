package com.ocm.onlinecoursemanagementbackend.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    /**
     * Log avant l'exécution de chaque méthode dans le package `service`.
     */
    @Before("execution(* com.ocm.onlinecoursemanagementbackend.service.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("Entrée dans la méthode : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    /**
     * Log après l'exécution réussie de chaque méthode dans le package `service`.
     */
    @AfterReturning(value = "execution(* com.ocm.onlinecoursemanagementbackend.service.*.*(..))", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        logger.info("Sortie de la méthode : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
            " avec le résultat : " + (result != null ? result.toString() : "null"));
    }

    /**
     * Mesure et log le temps d'exécution des méthodes dans le package `service`.
     */
    @Around("execution(* com.ocm.onlinecoursemanagementbackend.service.*.*(..))")
    public Object profile(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed(); // Exécution de la méthode cible
        long elapsedTime = System.currentTimeMillis() - start;

        logger.info("Temps d'exécution de la méthode : " +
            proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." +
            proceedingJoinPoint.getSignature().getName() +
            " -> " + elapsedTime + " ms");

        return result;
    }
}
