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

package org.openapitools.client.models

import org.openapitools.client.models.UserCreationRequestStatus

import com.squareup.moshi.Json

/**
 * 
 *
 * @param id 
 * @param description 
 * @param lastModificationTime 
 * @param creationTime 
 * @param status 
 * @param extensionData 
 */

data class UserCreationRequest (

    @Json(name = "id")
    val id: kotlin.Int? = null,

    @Json(name = "description")
    val description: kotlin.String? = null,

    @Json(name = "lastModificationTime")
    val lastModificationTime: java.time.OffsetDateTime? = null,

    @Json(name = "creationTime")
    val creationTime: java.time.OffsetDateTime? = null,

    @Json(name = "status")
    val status: UserCreationRequestStatus? = null,

    @Json(name = "extensionData")
    val extensionData: kotlin.String? = null

)

