package com.example.recyclerview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclerview.domain.ShopItem
import com.example.recyclerview.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val listOfShopItems = sortedSetOf(Comparator<ShopItem> { p0, p1 -> p0.id.compareTo(p1.id) })
    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 until 10) {
            addShopItem(ShopItem("name $i", i, Random.nextBoolean()))
        }
    }

    private var autoIncrementId = 0

    private fun updateLiveData() {
        shopListLiveData.value = listOfShopItems.toList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    override fun getShopItem(id: Int): ShopItem {
        return listOfShopItems.find {
            it.id == id
        } ?: throw RuntimeException("$id not found")
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        listOfShopItems.add(shopItem)
        updateLiveData()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        listOfShopItems.remove(shopItem)
        updateLiveData()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        deleteShopItem(oldItem)
        addShopItem(shopItem)
    }

}