package com.example.antibudgetv1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.antibudgetv1.data.discoverCredit
import com.example.antibudgetv1.data.discoverCreditTransactions
import com.example.antibudgetv1.model.IAccount
import com.example.antibudgetv1.model.ITransaction

@Preview
@Composable
fun TransactionCardPreview(modifier: Modifier = Modifier) {
    TransactionCard(transaction = discoverCreditTransactions[1], modifier)
}

@Composable
fun TransactionCard(transaction: ITransaction, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier)
    {
        Column (horizontalAlignment = Alignment.Start, modifier = modifier.padding(8.dp))
        {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 40.dp)
            ) {
                Text( //Transaction Name
                    text = transaction.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier
                )
                Text( //Transaction Value
                    text = transaction.value.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = when {
                        transaction.value > 0f -> MaterialTheme.colorScheme.tertiary
                        transaction.value < 0f -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.inverseSurface
                    },
                    modifier = modifier)
            }
            //Transaction Description (optional)
            if (!transaction.description.isNullOrEmpty()) {
                Text(
                    text = transaction.description,
                    modifier = modifier)
            }
        }
    }
}

@Preview
@Composable
fun BudgetViewPreview (modifier: Modifier = Modifier) {
    BudgetView(discoverCredit)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BudgetView(account: IAccount, modifier: Modifier = Modifier) {
    //Showcase the Account Name and Description as applicable
    LazyColumn (
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ){
        items(items = account.copyTransactions, itemContent = { item ->
            TransactionCard(transaction = item)
        })
    }
}