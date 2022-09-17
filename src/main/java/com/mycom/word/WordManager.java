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
        System.out.println("*** 영단어 마스터 ***");

        wordCRUD.loadFile();
        boolean run = true;

        while (run)
        {
            try
            {
                switch (selectMenu())
                {
                    case 0 ->
                    {
                        System.out.print("\n프로그램이 종료되었습니다.");
                        run = false;
                    }
                    case 1 -> wordCRUD.listAll();
                    case 2 -> wordCRUD.searchLevel();
                    case 3 -> wordCRUD.searchWord();
                    case 4 -> wordCRUD.add();
                    case 5 -> wordCRUD.update();
                    case 6 -> wordCRUD.delete();
                    case 7 -> wordCRUD.saveFile();
                    default -> System.out.println("\n번호가 올바르지 않습니다. 다시 입력하세요.");
                }
            }
            catch (Exception exception)
            {
                scanner.nextLine();
                System.out.println("\n번호가 올바르지 않습니다. 다시 입력하세요.");
            }
        }
    }

    private int selectMenu() throws Exception
    {
        System.out.print(
            """

            ********************
            1. 모든 단어 보기
            2. 수준별 단어 보기
            3. 단어 검색
            4. 단어 추가
            5. 단어 수정
            6. 단어 삭제
            7. 파일 저장
            0. 나가기
            ********************
            >>> 원하는 메뉴의 번호:\s""");

        return scanner.nextInt();
    }
}