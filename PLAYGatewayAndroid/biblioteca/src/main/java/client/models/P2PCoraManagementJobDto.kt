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

import org.openapitools.client.models.P2PCoraJobStatus
import org.openapitools.client.models.P2PCoraManagementJobType

import com.squareup.moshi.Json

/**
 * 
 *
 * @param id 
 * @param tenantId 
 * @param extensionData 
 * @param managementJobType 
 * @param jobStatus 
 * @param isGroupJob 
 * @param targetNodeId 
 * @param targetGroupId 
 * @param retryNum 
 * @param lastUpdateTime 
 */

data class P2PCoraManagementJobDto (

    @Json(name = "id")
    val id: kotlin.Int? = null,

    @Json(name = "tenantId")
    val tenantId: kotlin.Int? = null,

    @Json(name = "extensionData")
    val extensionData: kotlin.String? = null,

    @Json(name = "managementJobType")
    val managementJobType: P2PCoraManagementJobType? = null,

    @Json(name = "jobStatus")
    val jobStatus: P2PCoraJobStatus? = null,

    @Json(name = "isGroupJob")
    val isGroupJob: kotlin.Boolean? = null,

    @Json(name = "targetNodeId")
    val targetNodeId: kotlin.Int? = null,

    @Json(name = "targetGroupId")
    val targetGroupId: kotlin.Int? = null,

    @Json(name = "retryNum")
    val retryNum: kotlin.Int? = null,

    @Json(name = "lastUpdateTime")
    val lastUpdateTime: java.time.OffsetDateTime? = null

)

