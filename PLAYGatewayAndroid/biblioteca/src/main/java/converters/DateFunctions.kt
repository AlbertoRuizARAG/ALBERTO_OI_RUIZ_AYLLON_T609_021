package converters

import java.util.*

object DateFunctions {

    fun getDataTimeToBLE(): ByteArray {
        var byteArray = byteArrayOf()
    /*    DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        //That will return 2018-04-16T17:00:08.746Z. For your format, or if you need a different timezone, you can specify those:

        val date = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
*/

        val date2 = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date2
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH).toByte()
        val day = calendar.get(Calendar.DAY_OF_MONTH).toByte()
        val weekday = calendar.get(Calendar.DAY_OF_WEEK).toByte()
        val hour = calendar.get(Calendar.HOUR_OF_DAY).toByte()
        val minutes = calendar.get(Calendar.MINUTE).toByte()
        val seconds = calendar.get(Calendar.SECOND).toByte()

        byteArray += Converters.intToByteArray(year, 2)
        byteArray += month
        byteArray += day
        byteArray += hour
        byteArray += minutes
        byteArray += seconds
        byteArray += weekday

        return byteArray
    }

}
