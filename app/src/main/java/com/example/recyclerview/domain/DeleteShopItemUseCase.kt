package com.example.recyclerview.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem) {
        return shopListRepository.deleteShopItem(shopItem)
    }
}