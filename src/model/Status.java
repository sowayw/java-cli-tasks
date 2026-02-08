package model;

public enum Status {
    TODO("todo", "Todo"),
    IN_PROGRESS("in-progress","In Progress"),
    DONE("done","Done");

    private final String apiLabel;
    private final String display;

    Status(String apiLabel, String display) {
        this.apiLabel = apiLabel;
        this.display = display;
    }

    public String getApiLabel() {
        return apiLabel;
    }

    public String getDisplay() {
        return display;
    }

}
