package platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Repository {

    private static Repository instance;
    private List<CodeInformation> codeinfo = new ArrayList<>();
    private boolean isReversed = false;
    private Repository () { }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public List<CodeInformation> getReversed () {
        Collections.reverse(codeinfo);
        return codeinfo;
    }

    public List<CodeInformation> getTenLatestReversed () {
        List<CodeInformation> output = new ArrayList<>();
        output.addAll(codeinfo);
        if (output.size() > 10) {
            output = output.subList(output.size() - 10, output.size());
        }
        Collections.reverse(output);
        return output;
    }

    public void addCode(CodeInformation code) {
        codeinfo.add(code);
    }

    public List<CodeInformation> getCodes() {
        return codeinfo;
    }
}