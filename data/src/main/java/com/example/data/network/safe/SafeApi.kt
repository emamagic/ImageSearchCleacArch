package com.example.data.network.safe

import com.example.common.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException

abstract class SafeApi {
   // private val mutex = Mutex()
    private var flag: Boolean = false

    suspend fun <T> safeApi(call: suspend () -> Response<T>): com.example.common.ApiWrapper<T> {

/*        return if (!mutex.isLocked) mutex.withLock { apiTry { call.invoke() } }
        else   null*/
        return withContext(Dispatchers.IO) { apiTry { call.invoke() } }

    }


    private fun <T> handleResponse(response: Response<T>): com.example.common.ApiWrapper<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return com.example.common.ApiWrapper.Success(
                    data = it,
                    headers = response.headers(),
                    code = response.code()
                )
            }
        }
        return com.example.common.ApiWrapper.ApiError(
            error = Gson().fromJson(response.errorBody()?.string() , ErrorResponse::class.java),
            headers = response.headers()
        )
    }

    private suspend fun <T> apiTry(call: suspend () -> Response<T>): com.example.common.ApiWrapper<T> {
        return try {
            handleResponse(call.invoke())
        } catch (e: NoInternetException) {
            com.example.common.ApiWrapper.NetworkError(message = "${e.message}")
        } catch (e: UnknownHostException){
            com.example.common.ApiWrapper.NetworkError(message = "${e.message}")
        } catch (e: SocketException){
            com.example.common.ApiWrapper.NetworkError(message = "${e.message}")
        } catch (t: Throwable) {
            com.example.common.ApiWrapper.UnknownError(message = "${t.message}//${t.cause}")
/*            if (!flag) {
                flag = true
                delay(3000)
                apiTry(call)
            } else {
                flag = false
                ApiWrapper.UnknownError(message = "${t.message}//${t.cause}")
            }*/
        }
    }
}