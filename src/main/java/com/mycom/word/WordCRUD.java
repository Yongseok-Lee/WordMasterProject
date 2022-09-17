package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD
{
    private ArrayList<Word> list;
    private Scanner scanner;
    final String filename = "Dictionary.txt";

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

                list.get((int) matched.get(id - 1)).setMeaning(newMeaning);
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
    public void delete()
    {
        if (list.size() == 0)
        {
            System.out.println("\n단어장에 단어가 없습니다. 새 단어를 추가하세요.");
            return;
        }

        ArrayList<Integer> matched = new ArrayList<>();

        while (matched.size() == 0)
        {
            System.out.print("\n>>> 삭제할 단어 검색: ");
            String keyword = scanner.next();

            matched = this.listAll(keyword);
        }

        while (true)
        {
            try
            {
                System.out.print("\n>>> 삭제할 번호 선택: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print(">>> 정말로 삭제하시겠습니까?(Y/N): ");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("Y"))
                {
                    list.remove((int) matched.get(id - 1));
                    System.out.println("\n단어가 삭제되었습니다.");
                }
                else
                {
                    System.out.println("\n삭제 취소되었습니다.");
                }

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

    public void listAll(int level)
    {
        System.out.println("\n--------------------");

        for (int i = 0, j = 0; i < list.size(); ++i)
        {
            if (list.get(i).getLevel() != level) continue;

            System.out.println(++j + " " + list.get(i).toString());
        }

        System.out.println("--------------------");
    }

    public void searchLevel()
    {
        if (list.size() == 0)
        {
            System.out.println("\n단어장에 단어가 없습니다. 새 단어를 추가하세요.");
            return;
        }

        System.out.print("\n>>> 원하는 레벨 입력(1 ~ 3): ");
        int level = scanner.nextInt();
        scanner.nextLine();

        listAll(level);
    }

    public void searchWord()
    {
        if (list.size() == 0)
        {
            System.out.println("\n단어장에 단어가 없습니다. 새 단어를 추가하세요.");
            return;
        }

        System.out.print("\n>>> 원하는 단어 입력: ");
        String keyword = scanner.next();
        scanner.nextLine();

        listAll(keyword);
    }

    public void loadFile()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            int count = 0;

            while (true)
            {
                line = br.readLine();

                if (line == null) break;

                String[] data = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];

                list.add(new Word(0, level, word, meaning));
                ++count;
            }

            br.close();
            System.out.println("\n>>> " + count + "개 데이터 로드 완료.");
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public void saveFile()
    {
        try
        {
            PrintWriter pr = new PrintWriter(new FileWriter("NewDictionary.txt"));

            for (Word word: list)
            {
                pr.write(word.toFileString() + "\n");
            }

            pr.close();
            System.out.println("\n>>> 데이터 저장 완료.");
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
}