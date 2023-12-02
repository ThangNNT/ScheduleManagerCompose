package com.nnt.schedulemanagercompose.provider

import com.nnt.schedulemanagercompose.data.datastore.DataStore
import com.nnt.schedulemanagercompose.data.datastore.DataStoreImpl

/**
 * Created by ThangNNT on 02/12/2023.
 */
object DataStoreProvider {
    val dataStore: DataStore = DataStoreImpl()
}