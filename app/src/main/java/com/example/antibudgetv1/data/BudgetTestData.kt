package com.example.antibudgetv1.data

import com.example.antibudgetv1.model.budget.IAccount
import com.example.antibudgetv1.model.budget.IBudget
import com.example.antibudgetv1.model.budget.ITransaction
import com.example.antibudgetv1.model.budget.SimpleAccount
import com.example.antibudgetv1.model.budget.SimpleBudget
import com.example.antibudgetv1.model.budget.SimpleTransaction

val discoverCreditTransactions = listOf<ITransaction>(
    SimpleTransaction("Groceries", -130f),
    SimpleTransaction(
        "Car insurance", -120f, "estimated monthly, paid biannually"
    ),
    SimpleTransaction("Gas", -40f),
    SimpleTransaction(
        "Dance Classes",
        -60f
    )
)

val discoverSavingsTransactions = listOf<ITransaction>(
    SimpleTransaction(
        "income",
        2000f,
        "take home pay, paid biweekly"
    ),
    SimpleTransaction(
        "Retirement Savings",
        -650f
    ),
)

val bankOfAmericaTransactions = listOf<ITransaction>(
    SimpleTransaction(
        "Rent",
        -1200f,
        "Owed on the first of the month"
    ),
    SimpleTransaction(
        "Utilities",
        -100f,
        "Heat, Electricity, Internet"
    ),
    SimpleTransaction("Date Night", -80f),
    SimpleTransaction(
        "Dog Sitting",
        200f,
        "Average Value"
    )

)
var bOAAccount: IAccount =
    SimpleAccount(
        "Bank of America", "Credit and Debit", bankOfAmericaTransactions
    )
val discoverSavings: IAccount =
    SimpleAccount(
        "Discover Savings", "High Yield", discoverSavingsTransactions
    )
val discoverCredit: IAccount =
    SimpleAccount(
        "Discover Credit", "Discover IT card", discoverCreditTransactions
    )

val testBudget: IBudget =
    SimpleBudget(
        "Test Budget", "Version 1 of budget",
        listOf<IAccount>(
            bOAAccount,
            discoverSavings,
            discoverCredit
        )
    )