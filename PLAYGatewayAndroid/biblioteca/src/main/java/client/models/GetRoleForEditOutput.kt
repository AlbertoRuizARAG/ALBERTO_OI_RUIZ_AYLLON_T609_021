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

import org.openapitools.client.models.FlatPermissionDto
import org.openapitools.client.models.RoleEditDto

import com.squareup.moshi.Json

/**
 * 
 *
 * @param role 
 * @param permissions 
 * @param grantedPermissionNames 
 */

data class GetRoleForEditOutput (

    @Json(name = "role")
    val role: RoleEditDto? = null,

    @Json(name = "permissions")
    val permissions: kotlin.collections.List<FlatPermissionDto>? = null,

    @Json(name = "grantedPermissionNames")
    val grantedPermissionNames: kotlin.collections.List<kotlin.String>? = null

)

