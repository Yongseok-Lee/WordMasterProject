package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD
{
    private ArrayList<Word> list;
    private Scanner scanner;

    public WordCRUD(Scanner scanner)
    {
        this.scanner = scanner;
        this.list = new ArrayList<>();
    }

    @Override
    public void add()
    {
        System.out.print("\n>>> 난이도(1 ~ 3) & 새 단어 입력: ");
        int level = scanner.nextInt();
        String word = scanner.nextLine();

        System.out.print(">>> 새 단어의 뜻 입력: ");
        String meaning = scanner.nextLine();

        this.list.add(new Word(0, level, word, meaning));
        System.out.println("\n새 단어가 단어장에 추가되었습니다.");
    }

    @Override
    public int update(Object obj)
    {
        return 0;
    }

    @Override
    public int delete(Object obj)
    {
        return 0;
    }

    @Override
    public void selectOne(int id)
    {

    }

    @Override
    public void listAll()
    {
        System.out.println("\n--------------------");

        for (int i = 0; i < list.size(); ++i)
        {
            System.out.println((i + 1) + " " + list.get(i).toString());
        }

        System.out.println("--------------------");
    }
}