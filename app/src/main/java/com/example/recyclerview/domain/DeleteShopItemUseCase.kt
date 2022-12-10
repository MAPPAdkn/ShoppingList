package com.example.recyclerview.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun deleteShopItem(shopItem: ShopItem) {
        return shopListRepository.deleteShopItem(shopItem)
    }
}