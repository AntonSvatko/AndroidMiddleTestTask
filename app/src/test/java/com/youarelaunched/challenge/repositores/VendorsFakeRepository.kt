package com.youarelaunched.challenge.repositores

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor

class VendorsFakeRepository : VendorsRepository {
    override suspend fun getVendors(searchQuery: String): List<Vendor> =
        listOf(
            Vendor(
                0,
                "testCompanyName",
                "https://cdn-staging.chatsumer.app/eyJidWNrZXQiOiJjaGF0c3VtZXItZ2VuZXJhbC1zdGFnaW5nLXN0b3JhZ2UiLCJrZXkiOiIxMy84ZjMzZTgyNy0yNzIxLTQ3ZjctYjViNS0zM2Q5Y2E2MTM1OGQuanBlZyJ9",
                "areaTest",
                true,
                null,
                null
            )
        )
}