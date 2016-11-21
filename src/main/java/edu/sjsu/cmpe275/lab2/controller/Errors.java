package edu.sjsu.cmpe275.lab2.controller;

public class Errors {

    public static final String USER_ENTITY = "user";
    public static final String PHONE_ENTITY = "phone";

    /**
     * This method generates error page for when the ID does not exit
     * @param entity
     * @param id
     * @return html error page string
     */
    public static String getIDNotFoundErrorPage(final String entity, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sorry, the requested ");
        sb.append(entity);
        sb.append(" with ID ");
        sb.append(id);
        sb.append(" does not exist.");
        return generatePage(sb.toString());
    }

    /**
     * Returns error page when deleting a resource is not allowed
     * @param entityMain
     * @param entityOther
     * @param mainEntityID
     * @return html error page string
     */
    public static String getDeleteNotAllowedErrorPage(final String entityMain, final String entityOther, int mainEntityID) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sorry, the deletion of ");
        sb.append(entityMain);
        sb.append(" with ID ");
        sb.append(mainEntityID);
        sb.append(" is not allowed, since there are still ");
        sb.append(entityOther);
        sb.append("s assigned to it.");
        return generatePage(sb.toString());
    }

    /**
     * Returns error page when try to enter duplicate unique key
     * @param phoneNumber
     * @return html error page string
     */
    public static String getDuplicateKeyNotAllowedErrorPage(String phoneNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sorry, phone number ");
        sb.append(phoneNumber);
        sb.append(" already exists. Duplicate not allowed.");
        return generatePage(sb.toString());
    }

    /**
     * Private method to generate HTML page as per the given error message
     * @param errorString
     * @return html error page string
     */
    private static String generatePage(String errorString) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<title>Group 7: Error Page</title>");
        sb.append("<body>");
        sb.append(errorString);
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}