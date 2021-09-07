import java.util.ArrayList;
import java.util.List;

public class TextEditor {

    public String [] showTexts(String [][] queries) {
        int cursor = 0;
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();

        for(String [] query : queries) {
            String operation = query[0];
            switch (operation) {
                case "APPEND":
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
                    if(cursor > 0) {
                        sb.deleteCharAt(cursor - 1);
                        cursor--;
                    }
                    break;
                // SELECT
                
                // COPY

                // PASTE

                default:
                    break;
            }

            if(!operation.equals("MOVE") && !operation.equals("COPY")) {
                res.add(sb.toString());
            }
        }

        return res.toArray(new String[0]);
    }
}
