package com.mycom.word;

import java.util.Scanner;

public class WordManager
{
    private WordCRUD wordCRUD;
    private Scanner scanner;

    public WordManager()
    {
        this.scanner = new Scanner(System.in);
        this.wordCRUD = new WordCRUD(scanner);
    }

    public void start()
    {
        boolean run = true;

        System.out.println("*** 영단어 마스터 ***");

        while (run)
        {
            switch (selectMenu())
            {
                case 0: System.out.print("\n프로그램이 종료되었습니다."); run = false; break;
                case 1: wordCRUD.listAll(); break;
                case 4: wordCRUD.add(); break;
                case 5: wordCRUD.update(); break;
                default: System.out.println("\n번호가 올바르지 않습니다. 다시 입력하세요.");
            }
        }
    }

    private int selectMenu()
    {
        System.out.print(
            "\n********************\n" +
            "1. 모든 단어 보기\n" +
            "2. 수준별 단어 보기\n" +
            "3. 단어 검색\n" +
            "4. 단어 추가\n" +
            "5. 단어 수정\n" +
            "6. 단어 삭제\n" +
            "7. 파일 저장\n" +
            "0. 나가기\n" +
            "********************\n" +
            ">>> 원하는 메뉴의 번호: ");

        return scanner.nextInt();
    }
}