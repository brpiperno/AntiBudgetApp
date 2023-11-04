package com.example.antibudgetv1.ui

import androidx.lifecycle.ViewModel
import com.example.antibudgetv1.data.BudgetUiState
import com.example.antibudgetv1.model.budget.IBudget
import com.example.antibudgetv1.model.budget.SimpleBudget
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * [AntiBudgetViewModel] holds information about the anti budget transactions and historical values
 */
class AntiBudgetViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BudgetUiState(getBudget()))
    val uiState: StateFlow<BudgetUiState> = _uiState.asStateFlow()

    // SETTERS


    // PRIVATE FUNCTIONS TO BUILD VIEW MODEL

    private fun getBudget(): IBudget {
        return SimpleBudget("Test", "Budget")
        //TODO: get the budget from the data layer
    }
}