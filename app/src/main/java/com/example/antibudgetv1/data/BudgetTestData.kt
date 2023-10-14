package com.example.antibudgetv1.data

import com.example.antibudgetv1.model.IAccount
import com.example.antibudgetv1.model.IAntiBudget
import com.example.antibudgetv1.model.ITransaction
import com.example.antibudgetv1.model.SimpleAccount
import com.example.antibudgetv1.model.SimpleAntiBudget
import com.example.antibudgetv1.model.SimpleTransaction

val discoverCreditTransactions = listOf<ITransaction>(
    SimpleTransaction("Groceries", -130f),
    SimpleTransaction(
        "Car insurance", -120f, "estimated monthly, paid biannually"),
    SimpleTransaction("Gas", -40f),
    SimpleTransaction("Dance Classes", -60f)
)

val discoverSavingsTransactions = listOf<ITransaction>(
    SimpleTransaction("iRhythm income", 2400f, "take home pay, paid biweekly"),
    SimpleTransaction("Roth IRA deposit", -650f),
)

val bankOfAmericaTransactions = listOf<ITransaction>(
    SimpleTransaction(
        "Rent", -1267f, "Rent is owed on the first of the month"),
    SimpleTransaction(
        "Utilities", -100f, "Includes Heat, Electricity, Internet"),
)
var bOAAccount:IAccount = SimpleAccount(
    "Bank of America", "Credit and Debit", bankOfAmericaTransactions)
val discoverSavings:IAccount = SimpleAccount(
    "Discover Savings", "High Yield", discoverSavingsTransactions)
val discoverCredit: IAccount = SimpleAccount(
    "Discover Credit", "Discover IT card", discoverCreditTransactions)

val testBudget:IAntiBudget = SimpleAntiBudget(
    "Test Budget", "Version 1 of budget",
    listOf<IAccount>(bOAAccount, discoverSavings, discoverCredit))