package com.digipodium.pizzaapp.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devsadeq.pizzaorderapp.viewmodel.OrderScreenUiState
import com.digipodium.pizzaapp.R

@Composable
fun PizzaIngredients(
    pizza: OrderScreenUiState.Pizza,
    onIngredientClicked: (OrderScreenUiState.Ingredient) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            stringResource(R.string.customize_your_pizza),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(pizza.ingredients.size) { index ->
                IngredientRoundedButton(
                    imageRes = pizza.ingredients[index].imageRes,
                    onClick = { onIngredientClicked(pizza.ingredients[index]) },
                    selected = pizza.ingredients[index].selected
                )
            }
        }
    }
}
