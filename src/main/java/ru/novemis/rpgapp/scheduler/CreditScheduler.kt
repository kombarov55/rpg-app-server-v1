package ru.novemis.rpgapp.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.service.CreditService
import javax.transaction.Transactional

@Component
open class CreditScheduler(
        private val creditService: CreditService
) {

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    open fun transferJob() {
        creditService.makeForcedPayments()
    }
}