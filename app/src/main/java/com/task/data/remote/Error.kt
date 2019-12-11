package com.task.data.remote

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class Error {
    var description: String? = ""
    var code: Int = NO_INTERNET_CONNECTION

    constructor()

    constructor(description: String = "", code: Int = NO_INTERNET_CONNECTION) {
        this.description = description
        this.code = code
    }

    constructor(exception: Exception) {
        this.description = exception.message
        this.code = INTERNAL_SERVER_ERROR
    }

    companion object {
        const val NETWORK_ERROR = "Unknown Error"
        const val INTERNAL_SERVER_ERROR = 500
        const val NO_INTERNET_CONNECTION = -1
        const val SUCCESS_CODE = 200
        const val ERROR_CODE = 400
    }
}
