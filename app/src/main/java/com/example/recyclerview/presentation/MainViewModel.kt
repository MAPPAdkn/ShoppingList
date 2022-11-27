package com.example.recyclerview.presentation

import androidx.lifecycle.ViewModel
import com.example.recyclerview.data.ShopListRepositoryImpl
import com.example.recyclerview.domain.DeleteShopItemUseCase
import com.example.recyclerview.domain.EditShopItemUseCase
import com.example.recyclerview.domain.GetShopListUseCase
import com.example.recyclerview.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }

}