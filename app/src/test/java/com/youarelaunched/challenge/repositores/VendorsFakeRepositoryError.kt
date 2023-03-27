package com.youarelaunched.challenge.repositories.com.youarelaunched.challenge.repositores

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor

class VendorsFakeRepositoryError : VendorsRepository {
    override suspend fun getVendors(searchQuery: String): List<Vendor> = throw Exception()
}