package com.task.data

import com.task.data.local.LocalRepository
import com.task.data.remote.Data
import com.task.data.remote.RemoteRepository
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class DataRepository @Inject
constructor(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository) {
    fun requestGiphy(): Data? {
        return remoteRepository.requestGiphy()
    }
}
