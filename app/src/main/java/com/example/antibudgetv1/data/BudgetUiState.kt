package com.example.antibudgetv1.data

import com.example.antibudgetv1.model.budget.IBudget
import com.example.antibudgetv1.model.historian.IHistorian

/**
 * Data class that represents the current UI state in terms of budget and historical values
 */
data class BudgetUiState (
    /** budget, accounts, and transactions of the budget*/
    val budget: IBudget
)