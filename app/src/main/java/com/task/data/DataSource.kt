package com.task.data

import com.task.data.remote.Data


interface DataSource {
    fun requestProducts(): Data?
}