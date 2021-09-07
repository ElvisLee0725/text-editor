public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        String [][] query = {
                {"APPEND", "Hello world!"},
                {"COPY", "5", "11"},
                {"MOVE", "5"},
                {"PASTE"},
                {"PASTE"},
                {"PASTE"}
        };
        String [] ans = editor.showTexts(query);
        for(String str : ans) {
            System.out.println(str);
        }
    }
}
