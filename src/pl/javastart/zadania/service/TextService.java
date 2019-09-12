package pl.javastart.zadania.service;

import java.util.ArrayList;

public class TextService {
    public int charQuantity (String text){
        char[] textToArrayString = text.toCharArray();
        return textToArrayString.length;
    }

    public int wordQuantity (String text){
        String[] textToArrayString = text.split(" ");
        return textToArrayString.length;
    }

    public int charOnlyQuantity (String text){
        String[] textToArrayString = text.split(" ");
        int result = 0;
        for (String s : textToArrayString) {
            result += s.length();
        }
        return result;
    }

    public boolean isPalindrome (String text){
        char[] textToArrayString = text.toCharArray();
        ArrayList<Character> characterArrayList = new ArrayList<>();
        for (char c : textToArrayString) {
            if (c != ' ')
                characterArrayList.add(c);
        }
        for (int i = 0; i < characterArrayList.size()/2; i++)
            if (characterArrayList.get(i) != characterArrayList.get(characterArrayList.size()-1-i))
                return false;
        return true;
    }
}
