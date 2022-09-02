package com.mycom.word;

public interface ICRUD
{
    void add();
    int update(Object obj);
    int delete(Object obj);
    void selectOne(int id);
    void listAll();
}