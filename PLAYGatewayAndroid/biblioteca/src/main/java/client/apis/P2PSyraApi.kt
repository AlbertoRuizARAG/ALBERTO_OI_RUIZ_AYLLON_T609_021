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

import org.openapitools.client.models.P2PSyraDto
import org.openapitools.client.models.P2PSyraWithCoraIdDto
import org.openapitools.client.models.P2PSyraWithCoraIdDtoPagedResultDto
import org.openapitools.client.models.SaveP2PSyraExtraDataDto
import org.openapitools.client.models.SaveP2PSyraExtraDataDtoSaveExtraDataDto

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

class P2PSyraApi(basePath: kotlin.String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
    * 
    * 
    * @param p2PSyraDto  (optional)
    * @return P2PSyraWithCoraIdDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PSyraCreatePost(p2PSyraDto: P2PSyraDto?) : P2PSyraWithCoraIdDto {
        val localVarResponse = apiServicesAppP2PSyraCreatePostWithHttpInfo(p2PSyraDto = p2PSyraDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as P2PSyraWithCoraIdDto
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
    * @param p2PSyraDto  (optional)
    * @return ApiResponse<P2PSyraWithCoraIdDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PSyraCreatePostWithHttpInfo(p2PSyraDto: P2PSyraDto?) : ApiResponse<P2PSyraWithCoraIdDto?> {
        val localVariableConfig = apiServicesAppP2PSyraCreatePostRequestConfig(p2PSyraDto = p2PSyraDto)

        return request<P2PSyraDto, P2PSyraWithCoraIdDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PSyraCreatePost
    *
    * @param p2PSyraDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PSyraCreatePostRequestConfig(p2PSyraDto: P2PSyraDto?) : RequestConfig<P2PSyraDto> {
        val localVariableBody = p2PSyraDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/P2PSyra/Create",
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
    fun apiServicesAppP2PSyraDeleteDelete(id: kotlin.Int?) : Unit {
        val localVarResponse = apiServicesAppP2PSyraDeleteDeleteWithHttpInfo(id = id)

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
    fun apiServicesAppP2PSyraDeleteDeleteWithHttpInfo(id: kotlin.Int?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppP2PSyraDeleteDeleteRequestConfig(id = id)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PSyraDeleteDelete
    *
    * @param id  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PSyraDeleteDeleteRequestConfig(id: kotlin.Int?) : RequestConfig<Unit> {
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
            path = "/api/services/app/P2PSyra/Delete",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param p2PModulePLSId  (optional)
    * @param sorting  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return P2PSyraWithCoraIdDtoPagedResultDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PSyraGetAllGet(p2PModulePLSId: kotlin.Int?, sorting: kotlin.String?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : P2PSyraWithCoraIdDtoPagedResultDto {
        val localVarResponse = apiServicesAppP2PSyraGetAllGetWithHttpInfo(p2PModulePLSId = p2PModulePLSId, sorting = sorting, skipCount = skipCount, maxResultCount = maxResultCount)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as P2PSyraWithCoraIdDtoPagedResultDto
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
    * @param p2PModulePLSId  (optional)
    * @param sorting  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return ApiResponse<P2PSyraWithCoraIdDtoPagedResultDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PSyraGetAllGetWithHttpInfo(p2PModulePLSId: kotlin.Int?, sorting: kotlin.String?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : ApiResponse<P2PSyraWithCoraIdDtoPagedResultDto?> {
        val localVariableConfig = apiServicesAppP2PSyraGetAllGetRequestConfig(p2PModulePLSId = p2PModulePLSId, sorting = sorting, skipCount = skipCount, maxResultCount = maxResultCount)

        return request<Unit, P2PSyraWithCoraIdDtoPagedResultDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PSyraGetAllGet
    *
    * @param p2PModulePLSId  (optional)
    * @param sorting  (optional)
    * @param skipCount  (optional)
    * @param maxResultCount  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PSyraGetAllGetRequestConfig(p2PModulePLSId: kotlin.Int?, sorting: kotlin.String?, skipCount: kotlin.Int?, maxResultCount: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (p2PModulePLSId != null) {
                    put("P2PModulePLSId", listOf(p2PModulePLSId.toString()))
                }
                if (sorting != null) {
                    put("Sorting", listOf(sorting.toString()))
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
            path = "/api/services/app/P2PSyra/GetAll",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param id  (optional)
    * @return SaveP2PSyraExtraDataDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PSyraGetExtraDataGet(id: kotlin.Int?) : SaveP2PSyraExtraDataDto {
        val localVarResponse = apiServicesAppP2PSyraGetExtraDataGetWithHttpInfo(id = id)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as SaveP2PSyraExtraDataDto
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
    * @return ApiResponse<SaveP2PSyraExtraDataDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PSyraGetExtraDataGetWithHttpInfo(id: kotlin.Int?) : ApiResponse<SaveP2PSyraExtraDataDto?> {
        val localVariableConfig = apiServicesAppP2PSyraGetExtraDataGetRequestConfig(id = id)

        return request<Unit, SaveP2PSyraExtraDataDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PSyraGetExtraDataGet
    *
    * @param id  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PSyraGetExtraDataGetRequestConfig(id: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (id != null) {
                    put("id", listOf(id.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/services/app/P2PSyra/GetExtraData",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param id  (optional)
    * @return P2PSyraWithCoraIdDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PSyraGetGet(id: kotlin.Int?) : P2PSyraWithCoraIdDto {
        val localVarResponse = apiServicesAppP2PSyraGetGetWithHttpInfo(id = id)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as P2PSyraWithCoraIdDto
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
    * @return ApiResponse<P2PSyraWithCoraIdDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PSyraGetGetWithHttpInfo(id: kotlin.Int?) : ApiResponse<P2PSyraWithCoraIdDto?> {
        val localVariableConfig = apiServicesAppP2PSyraGetGetRequestConfig(id = id)

        return request<Unit, P2PSyraWithCoraIdDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PSyraGetGet
    *
    * @param id  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PSyraGetGetRequestConfig(id: kotlin.Int?) : RequestConfig<Unit> {
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
            path = "/api/services/app/P2PSyra/Get",
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
    fun apiServicesAppP2PSyraRetryNodeManagementJobsPost(id: kotlin.Int?) : Unit {
        val localVarResponse = apiServicesAppP2PSyraRetryNodeManagementJobsPostWithHttpInfo(id = id)

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
    fun apiServicesAppP2PSyraRetryNodeManagementJobsPostWithHttpInfo(id: kotlin.Int?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppP2PSyraRetryNodeManagementJobsPostRequestConfig(id = id)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PSyraRetryNodeManagementJobsPost
    *
    * @param id  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PSyraRetryNodeManagementJobsPostRequestConfig(id: kotlin.Int?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (id != null) {
                    put("id", listOf(id.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        
        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/P2PSyra/RetryNodeManagementJobs",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param saveP2PSyraExtraDataDtoSaveExtraDataDto  (optional)
    * @return void
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PSyraSaveExtraDataPost(saveP2PSyraExtraDataDtoSaveExtraDataDto: SaveP2PSyraExtraDataDtoSaveExtraDataDto?) : Unit {
        val localVarResponse = apiServicesAppP2PSyraSaveExtraDataPostWithHttpInfo(saveP2PSyraExtraDataDtoSaveExtraDataDto = saveP2PSyraExtraDataDtoSaveExtraDataDto)

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
    * @param saveP2PSyraExtraDataDtoSaveExtraDataDto  (optional)
    * @return ApiResponse<Unit?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PSyraSaveExtraDataPostWithHttpInfo(saveP2PSyraExtraDataDtoSaveExtraDataDto: SaveP2PSyraExtraDataDtoSaveExtraDataDto?) : ApiResponse<Unit?> {
        val localVariableConfig = apiServicesAppP2PSyraSaveExtraDataPostRequestConfig(saveP2PSyraExtraDataDtoSaveExtraDataDto = saveP2PSyraExtraDataDtoSaveExtraDataDto)

        return request<SaveP2PSyraExtraDataDtoSaveExtraDataDto, Unit>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PSyraSaveExtraDataPost
    *
    * @param saveP2PSyraExtraDataDtoSaveExtraDataDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PSyraSaveExtraDataPostRequestConfig(saveP2PSyraExtraDataDtoSaveExtraDataDto: SaveP2PSyraExtraDataDtoSaveExtraDataDto?) : RequestConfig<SaveP2PSyraExtraDataDtoSaveExtraDataDto> {
        val localVariableBody = saveP2PSyraExtraDataDtoSaveExtraDataDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        
        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/services/app/P2PSyra/SaveExtraData",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param p2PSyraDto  (optional)
    * @return P2PSyraWithCoraIdDto
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun apiServicesAppP2PSyraUpdatePut(p2PSyraDto: P2PSyraDto?) : P2PSyraWithCoraIdDto {
        val localVarResponse = apiServicesAppP2PSyraUpdatePutWithHttpInfo(p2PSyraDto = p2PSyraDto)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as P2PSyraWithCoraIdDto
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
    * @param p2PSyraDto  (optional)
    * @return ApiResponse<P2PSyraWithCoraIdDto?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun apiServicesAppP2PSyraUpdatePutWithHttpInfo(p2PSyraDto: P2PSyraDto?) : ApiResponse<P2PSyraWithCoraIdDto?> {
        val localVariableConfig = apiServicesAppP2PSyraUpdatePutRequestConfig(p2PSyraDto = p2PSyraDto)

        return request<P2PSyraDto, P2PSyraWithCoraIdDto>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation apiServicesAppP2PSyraUpdatePut
    *
    * @param p2PSyraDto  (optional)
    * @return RequestConfig
    */
    fun apiServicesAppP2PSyraUpdatePutRequestConfig(p2PSyraDto: P2PSyraDto?) : RequestConfig<P2PSyraDto> {
        val localVariableBody = p2PSyraDto
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json-patch+json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.PUT,
            path = "/api/services/app/P2PSyra/Update",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

}