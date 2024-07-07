package com.sample.network

import android.content.Context
import com.jakewharton.processphoenix.ProcessPhoenix
import com.kakao.sdk.user.UserApiClient
import com.sample.network.datastore.NetworkPreference
import com.sample.network.service.AuthService
import com.zucchini.common.NavigationProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SPAuthenticator
    @Inject
    constructor(
        private val dataStore: NetworkPreference,
        private val api: AuthService,
        @ApplicationContext private val context: Context,
        private val navigationProvider: NavigationProvider,
    ) : Authenticator {
        override fun authenticate(
            route: Route?,
            response: Response,
        ): Request? {
            if (response.request.header("Authorization") == null) {
                return null
            }

            val responseBody = response.peekBody(Long.MAX_VALUE).string()
            val isAuthError =
                try {
                    val jsonObject = JSONObject(responseBody)
                    jsonObject.getString("code") == "AUTH4001"
                } catch (e: JSONException) {
                    false
                }

            if (isAuthError) {
                return handleTokenRefresh(response)
            }

            return null
        }

        private fun handleTokenRefresh(response: Response): Request? {
            val refreshToken = dataStore.refreshToken
            val newTokens =
                runCatching {
                    runBlocking {
                        api.refreshToken(refreshToken)
                    }
                }.onSuccess {
                    val data = it.data
                    dataStore.refreshToken = data.refreshToken.orEmpty()
                    dataStore.accessToken = data.accessToken.orEmpty()
                }.onFailure {
                    Timber.e("Authenticator", it.toString())
                    runBlocking {
                        dataStore.clear()
                        UserApiClient.instance.logout { error ->
                            Timber.e("Authenticator", error.toString())
                            ProcessPhoenix.triggerRebirth(context, navigationProvider.toLogin())
                        }
                    }
                }.getOrThrow()

            return newTokens.data.accessToken?.let {
                response.request
                    .newBuilder()
                    .header("Authorization", "Bearer $it")
                    .build()
            }
        }
    }
