package utility;
import java.io.*;
import java.util.*;

public class Utility {
    private static final Hashtable<String, ArrayList<String>> listWords = new Hashtable<>();
    private static final Hashtable<String, ArrayList<String>> defWords = new Hashtable<>();
    private static Hashtable<String, ArrayList<String>> restoreList = new Hashtable<>();
    private final static String pathFile = "src/assets/file/slang.txt";
    private final static String pathFileImage = "src/assets/img/intro_ui.png";
    private final static String pathHistoryFile = "src/dictionary/history.txt";
    private final static String path_file_name_Slang =  pathFile;

    public static final String getFilePathFileHistory() {
        return  pathHistoryFile;

    }

    public static final String getPathIamge() {
        return  pathFileImage;
    }

    public static boolean checkOpenFile() throws IOException {
        try {
            File file = new File(path_file_name_Slang);
            if (file.canRead()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void getDataFromTextFile() {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            File file = new File(path_file_name_Slang);
            if (file.canRead()) {
                BufferedReader br = new BufferedReader(new FileReader(path_file_name_Slang));
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line.contains("`")) {
                        String[] split_str = line.split("`");
                        if (split_str[1].contains("|")) {
                            String[] list = split_str[1].split("\\| ");
                            ArrayList<String> listMeanings = new ArrayList<String>(Arrays.asList(list));

                            listWords.put(split_str[0], listMeanings);
                            restoreList.put(split_str[0], listMeanings);
                            for (String e : listMeanings) {
                                ArrayList<String> slangs = new ArrayList<String>(Arrays.asList(split_str[0]));
                                defWords.put(e, slangs);

                            }
                        } else {
                            String[] only_one_word = new String[1];
                            only_one_word[0] = split_str[1];
                            ArrayList<String> listMeaning = new ArrayList<String>(Arrays.asList(only_one_word));
                            ArrayList<String> slang = new ArrayList<String>(Arrays.asList(split_str[0]));
                            listWords.put(split_str[0], listMeaning);
                            restoreList.put(split_str[0], listMeaning);
                            defWords.put(split_str[1], slang);

                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Hashtable<String, ArrayList<String>> getListWords() {
        return listWords;
    }
    public static Hashtable<String, ArrayList<String>> getListStrore() {
        return restoreList;
    }


    public static  Hashtable<String, ArrayList<String>> getDefWords() {
        return defWords;
    }

    public static void writeFile(ArrayList<String> his) {
        try {
            FileWriter myWriter = new FileWriter(getFilePathFileHistory());
            for (String e : his) {
                myWriter.write(e + "\n");
            }
            myWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static String[] getDataOneLine() {
        ArrayList<String> lines = new ArrayList<String>();

        for (Map.Entry<String, ArrayList<String>> entry : listWords.entrySet()) {
            String rs=entry.getValue().toString().replace("[", "");
            rs=rs.replace("]","");
            lines.add(entry.getKey() + " : " + rs);
        }
        Collections.sort(lines, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        String[] data = lines.toArray(new String[lines.size()]);
        return data;
    }

}
