package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public void addCodeToStorage(CodeInformation code) {
        codeRepository.save(code);
    }

    public int lastIdRepository(){
        return (int) codeRepository.count();
    }

    public CodeInformation getCodeFromStorage(String id){
        Optional<CodeInformation> requiredCode = codeRepository.findById(id);
        if (requiredCode.isEmpty()) {
            throw new CodeNotFoundException();
        } else {
            return requiredCode.get();
        }
    }

    /*
    public List<CodeInformation> getTenLatestSnippets() {
        List<CodeInformation> output = new ArrayList<>();
        long num = codeRepository.count();
        if (num >= 5) {
            for (CodeInformation task : codeRepository.findTop10ByOrderByIdDesc()) {
                output.add(task);
            }
        }
        else {
            for (CodeInformation task : codeRepository.findAllByOrderByIdDesc()) {
                output.add(task);
            }
        }
        return output;
    }
    */

    public List<CodeInformation> getTenLatestSnippets() {
        List<CodeInformation> output = new ArrayList<>();
        codeRepository.findAll().forEach(code -> {
            if (!code.isViewLimit() && !code.isTimeLimit()) {
                output.add(code);
            }
        });
        List<CodeInformation> lastCodes = new ArrayList<>();
        lastCodes.addAll(output);
        if (lastCodes.size() > 10) {
            lastCodes = lastCodes.subList(lastCodes.size() - 10, lastCodes.size());
        }
        Collections.reverse(lastCodes);
        return lastCodes;
    }

    public long count() {
        return codeRepository.count();
    }

    public void updateViewById(String id) {
        CodeInformation updateCode = getCodeFromStorage(id);
        int views = updateCode.getViews();
        if(views > 0) {
            views--;
            updateCode.setViews(views);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
    }

    public void updateTimeById(String id) {
        CodeInformation updateCode = getCodeFromStorage(id);
        LocalDateTime currentSecond = LocalDateTime.now();

        long secBetween = ChronoUnit.SECONDS.between(updateCode.getStartTime(), currentSecond);
        int time = (int) (updateCode.getTime() - secBetween);
        if (time > 0) {
            updateCode.setTime(time);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }

        //long time = updateCode.getTime() - ((currentSecond - updateCode.getStartSeconds())/1000);
        /*
        System.out.println(currentSecond);
        System.out.println(updateCode.getStartSeconds());
        System.out.println(updateCode.getTime());
        */
        /*
        if (currentSecond < (updateCode.getStartSeconds() + updateCode.getTime()*1000)) {
            System.out.println(updateCode.getTime());
            System.out.println(currentSecond);
            System.out.println(currentSecond/1000);
            updateCode.setTime(updateCode.getTime() - currentSecond/1000);
            codeRepository.save(updateCode);
        }
        else {
            codeRepository.delete(updateCode);
            //System.out.println("we are here");
        }
        */
        /*
        if(time > 0) {
            updateCode.setTime(time);
            codeRepository.save(updateCode);
        } else {
            codeRepository.delete(updateCode);
        }
        */
    }
}
