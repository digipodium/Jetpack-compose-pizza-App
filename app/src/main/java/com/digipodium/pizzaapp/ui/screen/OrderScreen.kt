package com.devsadeq.pizzaorderapp.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.devsadeq.pizzaorderapp.viewmodel.OrderScreenUiState
import com.devsadeq.pizzaorderapp.viewmodel.PizzaSize
import com.digipodium.pizzaapp.R
import com.digipodium.pizzaapp.ui.composable.OrderScreenTopBar
import com.digipodium.pizzaapp.ui.composable.Pizza
import com.digipodium.pizzaapp.ui.composable.PizzaIngredients
import com.digipodium.pizzaapp.ui.composable.PizzaPrice
import com.digipodium.pizzaapp.ui.composable.PizzaSizeSelection
import com.digipodium.pizzaapp.ui.composable.RoundedButton
import com.digipodium.pizzaapp.viewmodel.OrderScreenViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun OrderScreen(viewModel: OrderScreenViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState(initialPage = 0)
    viewModel.setSelectedPizza(pagerState.settledPage)
    Scaffold(
        topBar = { OrderScreenTopBar(state.isFavorite, viewModel::onFavoriteClicked) },
    ) { paddingValues ->
        OrderScreenContent(
            state,
            pagerState,
            paddingValues,
            viewModel::onPizzaSizeClicked,
            viewModel::onIngredientClicked
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OrderScreenContent(
    state: OrderScreenUiState,
    pagerState: PagerState,
    paddingValues: PaddingValues,
    onPizzaSizeClicked: (PizzaSize) -> Unit,
    onIngredientClicked: (OrderScreenUiState.Ingredient) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Pizza(
            pagerState = pagerState,
            pizzaList = state.pizzaList,
            pizzaSize = state.selectedSize,
        )
        PizzaPrice(price = state.totalPrice)
        PizzaSizeSelection(selectedSize = state.selectedSize, onClick = onPizzaSizeClicked)
        Spacer(modifier = Modifier.weight(1f))
        PizzaIngredients(
            pizza = state.selectedPizza,
            onIngredientClicked = onIngredientClicked,
        )
        Spacer(modifier = Modifier.weight(1f))
        RoundedButton(
            label = stringResource(R.string.add_to_cart),
            icon = Icons.Filled.ShoppingCart,
            onClick = { })
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun OrderScreenPreview() {
    OrderScreen()
}