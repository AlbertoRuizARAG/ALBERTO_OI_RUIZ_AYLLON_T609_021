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


import com.squareup.moshi.Json

/**
 * 
 *
 * @param userName 
 * @param name 
 * @param surname 
 * @param emailAddress 
 * @param id 
 * @param isActive 
 * @param fullName 
 * @param lastLoginTime 
 * @param creationTime 
 * @param roleNames 
 * @param tenantId 
 * @param tenantName 
 */

data class UserDtoExtended (

    @Json(name = "userName")
    val userName: kotlin.String,

    @Json(name = "name")
    val name: kotlin.String,

    @Json(name = "surname")
    val surname: kotlin.String,

    @Json(name = "emailAddress")
    val emailAddress: kotlin.String,

    @Json(name = "id")
    val id: kotlin.Long? = null,

    @Json(name = "isActive")
    val isActive: kotlin.Boolean? = null,

    @Json(name = "fullName")
    val fullName: kotlin.String? = null,

    @Json(name = "lastLoginTime")
    val lastLoginTime: java.time.OffsetDateTime? = null,

    @Json(name = "creationTime")
    val creationTime: java.time.OffsetDateTime? = null,

    @Json(name = "roleNames")
    val roleNames: kotlin.collections.List<kotlin.String>? = null,

    @Json(name = "tenantId")
    val tenantId: kotlin.Int? = null,

    @Json(name = "tenantName")
    val tenantName: kotlin.String? = null

)
