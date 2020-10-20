package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.repository.game.BalanceRepository
import javax.transaction.Transactional

@RestController
@RequestMapping("/balance")
open class BalanceProceduresController(
            private val repository: BalanceRepository
) {


    data class TransferForm(
            val from: String = "",
            val to: String = "",
            val currency: String = "",
            val amount: Int = 0
    )
    @PostMapping("/transfer.do")
    @Transactional
    open fun transfer(
            @RequestBody form: TransferForm
    ) {
        val from = repository.findById(form.from).get()
        val to = repository.findById(form.to).get()

        val amountToSubtract = from.amounts.find { it.currency!!.name == form.currency }!!
        val amountToAdd = to.amounts.find { it.currency!!.name == form.currency }!!

        amountToSubtract.amount -= form.amount
        amountToAdd.amount += form.amount

        from.apply { amounts = from.amounts.filter { it.id !== amountToSubtract.id } + amountToSubtract }
                .also { repository.save(it) }

        to.apply { amounts = from.amounts.filter { it.id !== amountToAdd.id } + amountToAdd }
                .also { repository.save(it) }
    }

}