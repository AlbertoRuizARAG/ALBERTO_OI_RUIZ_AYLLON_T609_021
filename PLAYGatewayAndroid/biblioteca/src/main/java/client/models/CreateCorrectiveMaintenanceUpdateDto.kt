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

import org.openapitools.client.models.CorrectiveMaintenance
import org.openapitools.client.models.Criticality
import org.openapitools.client.models.MaintenanceAffectedDevices

import com.squareup.moshi.Json

/**
 * 
 *
 * @param id 
 * @param creationTime 
 * @param parentCorrectiveMaintenanceId 
 * @param criticality 
 * @param isSecurityCompromised 
 * @param observations 
 * @param tenantId 
 * @param parentCorrectiveMaintenance 
 * @param affectedDevices 
 * @param mediaFileIds 
 */

data class CreateCorrectiveMaintenanceUpdateDto (

    @Json(name = "id")
    val id: kotlin.Int? = null,

    @Json(name = "creationTime")
    val creationTime: java.time.OffsetDateTime? = null,

    @Json(name = "parentCorrectiveMaintenanceId")
    val parentCorrectiveMaintenanceId: kotlin.Int? = null,

    @Json(name = "criticality")
    val criticality: Criticality? = null,

    @Json(name = "isSecurityCompromised")
    val isSecurityCompromised: kotlin.Boolean? = null,

    @Json(name = "observations")
    val observations: kotlin.String? = null,

    @Json(name = "tenantId")
    val tenantId: kotlin.Int? = null,

    @Json(name = "parentCorrectiveMaintenance")
    val parentCorrectiveMaintenance: CorrectiveMaintenance? = null,

    @Json(name = "affectedDevices")
    val affectedDevices: kotlin.collections.List<MaintenanceAffectedDevices>? = null,

    @Json(name = "mediaFileIds")
    val mediaFileIds: kotlin.collections.List<kotlin.Int>? = null

)

