package utils;

public class APIPayloadConstants {
    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Oguz\",\n" +
                "  \"emp_lastname\": \"Bond\",\n" +
                "  \"emp_middle_name\": \"A\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1988-09-25\",\n" +
                "  \"emp_status\": \"active\",\n" +
                "  \"emp_job_title\": \"SDET\"\n" +
                "}";

        return createEmployeePayload;
    }
}
