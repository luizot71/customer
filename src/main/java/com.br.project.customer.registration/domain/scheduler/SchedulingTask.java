package com.br.project.customer.registration.domain.scheduler;

import com.br.project.customer.registration.handler.CustomerRegistrationErrorCode;
import com.br.project.customer.registration.handler.exceptions.BusinessException;
import com.br.project.customer.registration.service.impl.CustomerRegistrationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@EnableScheduling
public class SchedulingTask {

    private static final Logger log = LoggerFactory.getLogger(SchedulingTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String TIME_ZONE = "America/Sao_Paulo";
    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;

    @Autowired private CustomerRegistrationServiceImpl servive;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationServiceImpl.class);

    @Scheduled(fixedDelay = MINUTO * 10, zone = TIME_ZONE)
    public void run() {
        try {
            //this.servive.getCustomerRegistrationService();

            log.info("Scheduler search values on Customer Api and insert values on Atomic database, {}", dateFormat.format(new Date()));
        }catch (Exception e) {
            LOGGER.warn(e.getMessage());
            throw new BusinessException(CustomerRegistrationErrorCode.errorProcessingGetValuesFromAtomic);
        }
    }
}
