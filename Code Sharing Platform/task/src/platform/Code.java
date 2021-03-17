package platform;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Code {
    private String code;

    public Code() {
        code = "class Code { ...";
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }
}