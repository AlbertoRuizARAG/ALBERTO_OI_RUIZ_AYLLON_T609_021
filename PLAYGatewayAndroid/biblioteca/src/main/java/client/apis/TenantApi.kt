/**
 * PriluxWebUI API
 *
 * PriluxWebUI
 *
 * The version of the OpenAPI document: v1
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.apis

import java.io.IOException

import org.openapitools.client.models.AddMultiTenantUserToTenantDto
import org.openapitools.client.models.CoraBoolErrorResponseDto
import org.openapitools.client.models.CreateTenantDto
import org.openapitools.client.models.TenantDto
import org.openapitools.client.models.TenantDtoPagedResultDto
import org.openapitools.client.models.UserDtoExtendedListResultDto

import com.squareup.moshi.Json

import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ApiResponse
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ClientError
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.infrastructure.ServerError
import org.openapitools.client.infrastructure.MultiValueMap
import org.openapitools.client.infrastructure.RequestConfig
import org.openapitools.client.infrastructure.RequestMethod
import org.openapitools.client.infrastructure.ResponseType
import org.openapitools.client.infrastructure.Success
import org.openapitools.client.infrastructure.toMultiValue

class TenantApi(basePath: kotlin.String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
    * 
    * 
    * @param addMultiTenantUserToTenantDto  (optional)
    * @return CoraBoolErrorResponseDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantAddMultiTenantUserToTenantPost(addMultiTenantUserToTenantDto: AddMultiTenantUserToTenantDto?) : CoraBoolErrorResponseDto {
        val localVarResponse = apiServicesAppTenantAddMultiTenantUserToTenantPostWithHttpInfo(addMultiTenantUserToTenantDto = addMultiTenantUserToTenantDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraBoolErrorResponseDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param addMultiTenantUserToTenantDto  (optional)
    * @return ApiResponse<CoraBoolErrorResponseDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantAddMultiTenantUserToTenantPostWithHttpInfo(addMultiTenantUserToTenantDto: AddMultiTenantUserToTenantDto?) : ApiResponse<CoraBoolErrorResponseDto?> {
        val localVariableConfig = apiServicesAppTenantAddMultiTenantUserToTenantPostRequestConfig(addMultiTenantUserToTenantDto = addMultiTenantUserToTenantDto)

        return request<AddMultiTenantUserToTenantDto, CoraBoolErrorResponseDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantAddMultiTenantUserToTenantPost
    *
    * @param addMultiTenantUserToTenantDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantAddMultiTenantUserToTenantPostRequestConfig(addMultiTenantUserToTenantDto: AddMultiTenantUserToTenantDto?) : RequestConfig<AddMultiTenantUserToTenantDto> {
        val localVariableBody = addMultiTenantUserToTenantDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/Tenant/AddMultiTenantUserToTenant",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param userId  (optional)
    * @param tenantId  (optional)
    * @param newPassword  (optional)
    * @return CoraBoolErrorResponseDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantChangeUserPasswordPost(userId: kotlin.Int?, tenantId: kotlin.Int?, newPassword: kotlin.String?) : CoraBoolErrorResponseDto {
        val localVarResponse = apiServicesAppTenantChangeUserPasswordPostWithHttpInfo(userId = userId, tenantId = tenantId, newPassword = newPassword)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraBoolErrorResponseDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param userId  (optional)
    * @param tenantId  (optional)
    * @param newPassword  (optional)
    * @return ApiResponse<CoraBoolErrorResponseDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantChangeUserPasswordPostWithHttpInfo(userId: kotlin.Int?, tenantId: kotlin.Int?, newPassword: kotlin.String?) : ApiResponse<CoraBoolErrorResponseDto?> {
        val localVariableConfig = apiServicesAppTenantChangeUserPasswordPostRequestConfig(userId = userId, tenantId = tenantId, newPassword = newPassword)

        return request<Unit, CoraBoolErrorResponseDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantChangeUserPasswordPost
    *
    * @param userId  (optional)
    * @param tenantId  (optional)
    * @param newPassword  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantChangeUserPasswordPostRequestConfig(userId: kotlin.Int?, tenantId: kotlin.Int?, newPassword: kotlin.String?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (userId != null) {
                    put("userId", listOf(userId.toString()))
                }
                if (tenantId != null) {
                    put("tenantId", listOf(tenantId.toString()))
                }
                if (newPassword != null) {
                    put("newPassword", listOf(newPassword.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/Tenant/ChangeUserPassword",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param createTenantDto  (optional)
    * @return TenantDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantCreatePost(createTenantDto: CreateTenantDto?) : TenantDto {
        val localVarResponse = apiServicesAppTenantCreatePostWithHttpInfo(createTenantDto = createTenantDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as TenantDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param createTenantDto  (optional)
    * @return ApiResponse<TenantDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantCreatePostWithHttpInfo(createTenantDto: CreateTenantDto?) : ApiResponse<TenantDto?> {
        val localVariableConfig = apiServicesAppTenantCreatePostRequestConfig(createTenantDto = createTenantDto)

        return request<CreateTenantDto, TenantDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantCreatePost
    *
    * @param createTenantDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantCreatePostRequestConfig(createTenantDto: CreateTenantDto?) : RequestConfig<CreateTenantDto> {
        val localVariableBody = createTenantDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/Tenant/Create",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param id  (optional)
    * @return void
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantDeleteDelete(id: kotlin.Int?) : Unit {
        val localVarResponse = apiServicesAppTenantDeleteDeleteWithHttpInfo(id = id)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param id  (optional)
    * @return ApiResponse<Unit?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantDeleteDeleteWithHttpInfo(id: kotlin.Int?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppTenantDeleteDeleteRequestConfig(id = id)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantDeleteDelete
    *
    * @param id  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantDeleteDeleteRequestConfig(id: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (id != null) {
                    put("Id", listOf(id.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        
        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/api/services/app/Tenant/Delete",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param userName  (optional)
    * @param email  (optional)
    * @return CoraBoolErrorResponseDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantDeleteMultiTenantUserDelete(userName: kotlin.String?, email: kotlin.String?) : CoraBoolErrorResponseDto {
        val localVarResponse = apiServicesAppTenantDeleteMultiTenantUserDeleteWithHttpInfo(userName = userName, email = email)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraBoolErrorResponseDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param userName  (optional)
    * @param email  (optional)
    * @return ApiResponse<CoraBoolErrorResponseDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantDeleteMultiTenantUserDeleteWithHttpInfo(userName: kotlin.String?, email: kotlin.String?) : ApiResponse<CoraBoolErrorResponseDto?> {
        val localVariableConfig = apiServicesAppTenantDeleteMultiTenantUserDeleteRequestConfig(userName = userName, email = email)

        return request<Unit, CoraBoolErrorResponseDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantDeleteMultiTenantUserDelete
    *
    * @param userName  (optional)
    * @param email  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantDeleteMultiTenantUserDeleteRequestConfig(userName: kotlin.String?, email: kotlin.String?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (userName != null) {
                    put("UserName", listOf(userName.toString()))
                }
                if (email != null) {
                    put("Email", listOf(email.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/api/services/app/Tenant/DeleteMultiTenantUser",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param keyword  (optional)
    * @param isActive  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return TenantDtoPagedResultDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantGetAllGet(keyword: kotlin.String?, isActive: kotlin.Boolean?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : TenantDtoPagedResultDto {
        val localVarResponse = apiServicesAppTenantGetAllGetWithHttpInfo(keyword = keyword, isActive = isActive, skipCount = skipCount, maxResultCount = maxResultCount)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as TenantDtoPagedResultDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param keyword  (optional)
    * @param isActive  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return ApiResponse<TenantDtoPagedResultDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantGetAllGetWithHttpInfo(keyword: kotlin.String?, isActive: kotlin.Boolean?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : ApiResponse<TenantDtoPagedResultDto?> {
        val localVariableConfig = apiServicesAppTenantGetAllGetRequestConfig(keyword = keyword, isActive = isActive, skipCount = skipCount, maxResultCount = maxResultCount)

        return request<Unit, TenantDtoPagedResultDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantGetAllGet
    *
    * @param keyword  (optional)
    * @param isActive  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantGetAllGetRequestConfig(keyword: kotlin.String?, isActive: kotlin.Boolean?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (keyword != null) {
                    put("Keyword", listOf(keyword.toString()))
                }
                if (isActive != null) {
                    put("IsActive", listOf(isActive.toString()))
                }
                if (skipCount != null) {
                    put("SkipCount", listOf(skipCount.toString()))
                }
                if (maxResultCount != null) {
                    put("MaxResultCount", listOf(maxResultCount.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/Tenant/GetAll",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @return UserDtoExtendedListResultDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantGetAllPlatformUsersGet() : UserDtoExtendedListResultDto {
        val localVarResponse = apiServicesAppTenantGetAllPlatformUsersGetWithHttpInfo()

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as UserDtoExtendedListResultDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @return ApiResponse<UserDtoExtendedListResultDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantGetAllPlatformUsersGetWithHttpInfo() : ApiResponse<UserDtoExtendedListResultDto?> {
        val localVariableConfig = apiServicesAppTenantGetAllPlatformUsersGetRequestConfig()

        return request<Unit, UserDtoExtendedListResultDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantGetAllPlatformUsersGet
    *
    * @return RequestConfig
    */
    fun apiServicesAppTenantGetAllPlatformUsersGetRequestConfig() : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/Tenant/GetAllPlatformUsers",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param id  (optional)
    * @return TenantDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantGetGet(id: kotlin.Int?) : TenantDto {
        val localVarResponse = apiServicesAppTenantGetGetWithHttpInfo(id = id)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as TenantDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param id  (optional)
    * @return ApiResponse<TenantDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantGetGetWithHttpInfo(id: kotlin.Int?) : ApiResponse<TenantDto?> {
        val localVariableConfig = apiServicesAppTenantGetGetRequestConfig(id = id)

        return request<Unit, TenantDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantGetGet
    *
    * @param id  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantGetGetRequestConfig(id: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (id != null) {
                    put("Id", listOf(id.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/Tenant/Get",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param userID  (optional)
    * @param tenantId  (optional)
    * @return CoraBoolErrorResponseDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantRemoveMultiTenantUserFromTenantDelete(userID: kotlin.Int?, tenantId: kotlin.Int?) : CoraBoolErrorResponseDto {
        val localVarResponse = apiServicesAppTenantRemoveMultiTenantUserFromTenantDeleteWithHttpInfo(userID = userID, tenantId = tenantId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as CoraBoolErrorResponseDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param userID  (optional)
    * @param tenantId  (optional)
    * @return ApiResponse<CoraBoolErrorResponseDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantRemoveMultiTenantUserFromTenantDeleteWithHttpInfo(userID: kotlin.Int?, tenantId: kotlin.Int?) : ApiResponse<CoraBoolErrorResponseDto?> {
        val localVariableConfig = apiServicesAppTenantRemoveMultiTenantUserFromTenantDeleteRequestConfig(userID = userID, tenantId = tenantId)

        return request<Unit, CoraBoolErrorResponseDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantRemoveMultiTenantUserFromTenantDelete
    *
    * @param userID  (optional)
    * @param tenantId  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantRemoveMultiTenantUserFromTenantDeleteRequestConfig(userID: kotlin.Int?, tenantId: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (userID != null) {
                    put("UserID", listOf(userID.toString()))
                }
                if (tenantId != null) {
                    put("TenantId", listOf(tenantId.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/api/services/app/Tenant/RemoveMultiTenantUserFromTenant",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param tenantDto  (optional)
    * @return TenantDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppTenantUpdatePut(tenantDto: TenantDto?) : TenantDto {
        val localVarResponse = apiServicesAppTenantUpdatePutWithHttpInfo(tenantDto = tenantDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as TenantDto
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * 
    * 
    * @param tenantDto  (optional)
    * @return ApiResponse<TenantDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppTenantUpdatePutWithHttpInfo(tenantDto: TenantDto?) : ApiResponse<TenantDto?> {
        val localVariableConfig = apiServicesAppTenantUpdatePutRequestConfig(tenantDto = tenantDto)

        return request<TenantDto, TenantDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppTenantUpdatePut
    *
    * @param tenantDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppTenantUpdatePutRequestConfig(tenantDto: TenantDto?) : RequestConfig<TenantDto> {
        val localVariableBody = tenantDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.PUT,
            path = "/api/services/app/Tenant/Update",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

}
