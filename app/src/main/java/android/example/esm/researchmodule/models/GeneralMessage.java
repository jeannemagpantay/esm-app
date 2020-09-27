package android.example.esm.researchmodule.models;

public class GeneralMessage {
    String message;
    int type;

    public GeneralMessage(String message, int type){
        this.message = message;
        this.type = type;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
