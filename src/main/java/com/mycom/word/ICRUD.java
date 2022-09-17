package com.mycom.word;

import java.util.ArrayList;

public interface ICRUD
{
    void add();
    void update();
    void delete();
    void selectOne(int id);
    void listAll();
    ArrayList<Integer> listAll(String keyword);
    void listAll(int level);
    void searchLevel();
    void searchWord();
    void loadFile();
    void saveFile();
}