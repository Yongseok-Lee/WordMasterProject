package com.mycom.word;

public interface ICRUD
{
    public abstract void add();
    public abstract int update(Object obj);
    public abstract int delete(Object obj);
    public abstract void selectOne(int id);
    public abstract void listAll();
}