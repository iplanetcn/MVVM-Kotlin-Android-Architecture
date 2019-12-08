package com.task.data.remote.dto.images


data class FinanceBudget(
    val budgetStatus: String?,
    val downPayment: Int?,
    val loanDuration: Int?,
    val localized: Localized?,
    val netIncome: Int?
)