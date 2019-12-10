package com.task.data.remote.dto.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FinanceBudget(
        var budgetStatus: String?,
        var downPayment: Int?,
        var loanDuration: Int?,
        var localized: Localized?,
        var netIncome: Int?
) : Parcelable