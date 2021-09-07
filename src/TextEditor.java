import java.util.ArrayList;
import java.util.List;

public class TextEditor {

    public String [] showTexts(String [][] queries) {
        int cursor = 0;
        Integer selectLeft = null;
        Integer selectRight = null;
        String selectedStr = null;
        String clipboard = null;

        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();

        for(String [] query : queries) {
            String operation = query[0];
            switch (operation) {
                case "APPEND":
                    if(selectedStr != null) {
                        sb.insert(cursor, selectedStr);
                        cursor += selectedStr.length();
                        selectedStr = null;
                        break;
                    }

                    String str = query[1];
                    if(cursor == sb.length()) {
                        sb.append(str);
                        cursor = sb.length();
                    }
                    else {
                        sb.insert(cursor, str);
                        cursor += str.length();
                    }
                    break;

                case "MOVE":
                    int pos = Integer.valueOf(query[1]);
                    if(pos < 0) {
                        cursor = 0;
                    }
                    else if(pos > sb.length()) {
                        cursor = sb.length();
                    }
                    cursor = pos;
                    break;

                case "BACKSPACE":
                    if(selectedStr != null) {
                        sb.delete(selectLeft, selectRight);
                        cursor = selectLeft;
                        selectedStr = null;
                    }
                    else if(cursor > 0) {
                        sb.deleteCharAt(cursor - 1);
                        cursor--;
                    }
                    break;
                // SELECT
                case "SELECT":
                    selectLeft = Integer.valueOf(query[1]);
                    selectRight = Integer.valueOf(query[2]);
                    if(selectLeft < 0) {
                        selectLeft = 0;
                    }

                    if(selectRight > sb.length()) {
                        selectRight = sb.length();
                    }

                    if(selectRight <= selectLeft) {
                        break;
                    }

                    selectedStr = sb.substring(selectLeft, selectRight);
                    clipboard = null;
                    break;

                // COPY
                case "COPY":
                    int copyLeft = Integer.valueOf(query[1]);
                    int copyRight = Integer.valueOf(query[2]);
                    if(copyRight <= copyLeft || copyLeft < 0 || copyRight > sb.length()) {
                        break;
                    }

                    clipboard = sb.substring(copyLeft, copyRight);
                    selectedStr = null;
                    break;

                // PASTE
                case "PASTE":
                    if(clipboard != null) {
                        sb.insert(cursor, clipboard);
                        cursor += clipboard.length();
                    }
                    break;

                default:
                    break;
            }

            if(!operation.equals("MOVE") && !operation.equals("SELECT") && !operation.equals("COPY")) {
                res.add(sb.toString());
            }
        }

        return res.toArray(new String[0]);
    }
}
