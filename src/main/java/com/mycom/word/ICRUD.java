package com.mycom.word;

import java.util.ArrayList;

public interface ICRUD
{
    void add();
    void update();
    int delete(Object obj);
    void selectOne(int id);
    void listAll();
    ArrayList<Integer> listAll(String keyword);
}