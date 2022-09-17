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
        while (true)
        {
            try
            {
                System.out.print("\n>>> 난이도(1 ~ 3) & 새 단어 입력: ");
                int level = scanner.nextInt();
                String word = scanner.next();
                scanner.nextLine();

                System.out.print(">>> 새 단어의 뜻 입력: ");
                String meaning = scanner.nextLine();

                this.list.add(new Word(0, level, word, meaning));
                System.out.println("\n새 단어가 단어장에 추가되었습니다.");
                break;
            }
            catch (Exception exception)
            {
                scanner.nextLine();
                System.out.println("\n올바른 형식이 아닙니다. 다시 입력하세요.");
            }
        }
    }

    @Override
    public void update()
    {
        if (list.size() == 0)
        {
            System.out.println("\n단어장에 단어가 없습니다. 새 단어를 추가하세요.");
            return;
        }

        ArrayList<Integer> matched = new ArrayList<>();

        while (matched.size() == 0)
        {
            System.out.print("\n>>> 수정할 단어 검색: ");
            String keyword = scanner.next();

            matched = this.listAll(keyword);
        }

        while (true)
        {
            try
            {
                System.out.print("\n>>> 수정할 번호 선택: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print(">>> 수정할 뜻 입력: ");
                String newMeaning = scanner.nextLine();

                list.get(matched.get(id - 1)).setMeaning(newMeaning);
                System.out.println("\n단어의 뜻이 수정되었습니다.");
                break;
            }
            catch (Exception exception)
            {
                scanner.nextLine();
                System.out.println("\n번호가 올바르지 않습니다. 다시 입력하세요.");
            }
        }
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
        if (list.size() == 0)
        {
            System.out.println("\n단어장에 단어가 없습니다. 새 단어를 추가하세요.");
            return;
        }

        System.out.println("\n--------------------");

        for (int i = 0; i < list.size(); ++i)
        {
            System.out.println((i + 1) + " " + list.get(i).toString());
        }

        System.out.println("--------------------");
    }

    @Override
    public ArrayList<Integer> listAll(String keyword)
    {
        ArrayList<Integer> matched = new ArrayList<>();

        System.out.println("\n--------------------");

        for (int i = 0, j = 0; i < list.size(); ++i)
        {
            if (!list.get(i).getWord().contains(keyword)) continue;

            System.out.println(++j + " " + list.get(i).toString());
            matched.add(i);
        }

        if (matched.size() == 0)
        {
            System.out.println("매칭된 단어가 없습니다. 키워드를 다시 입력하세요.");
        }

        System.out.println("--------------------");

        return matched;
    }
}