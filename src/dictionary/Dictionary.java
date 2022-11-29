package dictionary;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.Arrays;
import utility.Utility;

public class Dictionary {
    private final String SLANG_DEFINITION = "SLANG_DEFINITION";
    private final String NOTFOUND = "NOT FOUND";
    private Hashtable<String, ArrayList<String>> slangMeans;
    private Hashtable<String, ArrayList<String>> defSlangMeans;
    private Hashtable<String, ArrayList<String>> newSlang;
    private final ArrayList<String> notFound = new ArrayList<>();
    private ArrayList<String> history;
    private Hashtable<String, ArrayList<String>> store_history_Slang;


    public Dictionary() {

        slangMeans = new Hashtable<>();
        defSlangMeans = new Hashtable<>();
        history = new ArrayList<>();
        store_history_Slang = new Hashtable<>();
        notFound.add(NOTFOUND);
    }
    public void resetData(){
        slangMeans = new Hashtable<>();
        defSlangMeans = new Hashtable<>();
        slangMeans=Utility.getListStrore();

    }
    public void initData() {
        boolean stateFile;
        try {
            stateFile = Utility.checkOpenFile();
            if (stateFile) {
                Utility.getDataFromTextFile();
                slangMeans = Utility.getListWords();
                defSlangMeans = Utility.getDefWords();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ArrayList<String> findSlang(String slang, String target) {
        if (this.slangMeans.containsKey(slang)) {
            if (!history.contains("Slang :" + slang + "-" + this.slangMeans.get(slang)) && target.equals("Search")) {
                history.add("Slang :" + slang + "-" + this.slangMeans.get(slang));
                storeHistoryToFile();
            }
            return this.slangMeans.get(slang);
        }
        if (!history.contains("Slang :" + notFound)) {
            history.add("Slang :" + slang + " - " + notFound);
            storeHistoryToFile();
        }
        return notFound;
    }

    public void clearHistory() {
        this.history.clear();
    }

    public ArrayList<String> findSlang_Definitions(String defString, String target) {

        if (!target.equals("random")) {
        }
        ArrayList<String> results = new ArrayList<String>();
        for (String e : defSlangMeans.keySet()) {
            String[] iter=e.split(" ");
            ArrayList<String> stringList = new ArrayList<>(Arrays.asList(iter));
            if (stringList.contains(defString) && target.equals("Search")) {
                String rs = defSlangMeans.get(e).toString().replace("[", "");
                rs = rs.replace("]", "");
                results.add(rs);

            }
        }

        if (!history.contains("Slang-Defitinion :" + defString)
                && target.equals("Search")) {
            history.add("Slang-Defitinion :" + defString);
            storeHistoryToFile();
        }


        if (results.size() == 0 && !history.contains("Slang-Defitinion :" + NOTFOUND)) {
            history.add("Slang-Defitinion : " + defString + " - " + NOTFOUND);
            results.add(NOTFOUND);
            storeHistoryToFile();
        }

        return results;

    }

    public String findSlang_Definitions_Quiz(String defString, String target) {

        if (this.defSlangMeans.containsKey(defString)) {
            if (!history.contains("Slang-Defitinion :" + defString + " - " + this.defSlangMeans.get(defString).get(0))
                    && target.equals("Search")) {
                history.add("Slang-Defitinion :" + defString + " - " + this.defSlangMeans.get(defString).get(0));
            }
            return this.defSlangMeans.get(defString).get(0);
        }
        return NOTFOUND;

    }

    public ArrayList<String> getHistoryList() {
        return this.history;
    }

    public int getSizeHistory() {
        return this.history.size();
    }

    public boolean checkExists(String target) {
        if (this.slangMeans.containsKey(target))
            return true;
        return false;
    }

    public void addSlang(String newSlang, String new_Slang_Definition, String target) {
        boolean state = checkExists(newSlang);
        if (state&& target.equals("Override")) {
            editSlang(newSlang, new_Slang_Definition, "SLANG_DEFINITION");

        } else if (state && target.equals("Duplicate")) {
            this.slangMeans.get(newSlang).add(new_Slang_Definition);

        } else {
            String[] value = new_Slang_Definition.split(",");
            ArrayList<String> new_Slang_Definition_main = new ArrayList<String>();
            for (String e : value) {
                new_Slang_Definition_main.add(e);
            }
            slangMeans.put(newSlang, new_Slang_Definition_main);
        }

    }

    public boolean editSlang(String oldSlang, String newInfor, String target) {
        if (this.slangMeans.containsKey(oldSlang)) {
            ArrayList<String> oldMeaning = this.slangMeans.get(oldSlang);

            slangMeans.remove(oldSlang);
            String SLANG = "SLANG";
            if (target.equals(SLANG)) {
                slangMeans.put(newInfor, oldMeaning);
            } else {
                String[] newDefinition = newInfor.split(",");
                ArrayList<String> newDefinitionList = new ArrayList<String>(Arrays.asList(newDefinition));
                slangMeans.put(oldSlang, newDefinitionList);
            }

            return true;
        }
        return false;

    }

    public void resetHistory() {
        for (Map.Entry<String, ArrayList<String>> entry : store_history_Slang.entrySet()) {
            slangMeans.put(entry.getKey(), entry.getValue());
        }

    }

    public boolean deleteSlang(String targetSlang) {
        if (this.slangMeans.containsKey(targetSlang)) {
            store_history_Slang.put(targetSlang, slangMeans.get(targetSlang));
            this.slangMeans.remove(targetSlang);
            return true;
        }
        return false;
    }

    public int getSizeSlang() {
        return this.slangMeans.size();
    }

    public int getSizeSlangDefinition() {
        return this.defSlangMeans.size();
    }

    public HashMap<String, ArrayList<String>> RandomSlang() {
        HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
        String[] keys = this.slangMeans.keySet().toArray(new String[this.slangMeans.size()]);
        String key = keys[new Random().nextInt(keys.length)];
        result.put(key, this.slangMeans.get(key));
        return result;
    }

    public ArrayList<String> randomQuizSlang() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            String[] keys = this.slangMeans.keySet().toArray(new String[this.slangMeans.size()]);
            String key = keys[new Random().nextInt(keys.length)];
            result.add(key);
        }
        return result;

    }

    public ArrayList<String> randomQuizSlang_Definition() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String[] keys = this.defSlangMeans.keySet().toArray(new String[this.defSlangMeans.size()]);
            String key = keys[new Random().nextInt(keys.length)];
            result.add(key);
        }
        return result;

    }

    public boolean storeHistoryToFile() {
        try {
            Utility.writeFile(history);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

}
