/*************************************************************************************
 * NullString.java
 *
 *      This is used to change the null values from optional fields as "N/A".
 *      The model fields utilize this in their toString methods and certain services
 *      that display object information will also utilize this method.
 *
 *      As there are instances where dates and times are nullable, we must include
 *      the appropriate imports.
 *
 *      the check method:
 * @param <T> Generic: has to be to allow Strings, datetime, and dates through.
 * @returns Object: either itself or "N/A" if the value is null.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 10/20/2025
 * ************************************************************************************/

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class NullString{

    public static Object <T> check(T value){
        if(value == null){return "N/A";}
        return value;
    }
}