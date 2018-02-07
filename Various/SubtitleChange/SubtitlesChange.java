package Various.SubtitleChange;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public class SubtitlesChange {
    public static void main(String[] args) throws Exception {
        System.setProperty("file.encoding", "UTF-8");
        String path = System.getProperty("user.dir");
        File folder = new File(path);
        for (File sourceFile : folder.listFiles()) {
            if (sourceFile.getName().endsWith(".txt") || sourceFile.getName().endsWith(".srt")){
                System.out.println("Starting to change file: " + sourceFile.getName());
                // read file, replace patterns and save into the same file
                try (Scanner input = new Scanner(sourceFile)) {
                    StringBuilder s1 = new StringBuilder();
                    while (input.hasNext()) {
                        String tekst = input.nextLine();
                        // list of wrong characters and corrected polish letters
                        char lWrong = (int) 179; char lFine = (int) 322;
                        char eWrong = (int) 234; char eFine = (int) 281;
                        char aWrong = (int) 185; char aFine = (int) 261;
                        char cWrong = (int) 230; char cFine = (int) 263;
                        char sWrong = (int) 339; char sFine = (int) 347;
                        char SWrong = (int) 338; char SFine = (int) 346;
                        char zWrong = (int) 376; char zFine = (int) 378;
                        char zetWrong = (int) 191; char zetFine = (int) 380;
                        char ZetWrong = (int) 175; char ZetFine = (int) 379;
                        char nWrong = ((int) 241); char nFine = (int) 324;

                        String modifiedString;
                        modifiedString = tekst
                                .replace(lWrong, lFine)
                                .replace(eWrong, eFine)
                                .replace(aWrong, aFine)
                                .replace(cWrong, cFine)
                                .replace(sWrong, sFine)
                                .replace(zWrong, zFine)
                                .replace(zetWrong, zetFine)
                                .replace(ZetWrong, ZetFine)
                                .replace(nWrong, nFine)
                                .replace(SWrong, SFine);
                        s1.append(modifiedString + "\r\n");
                    }
                    PrintWriter output = new PrintWriter(sourceFile);
                    output.println(s1);
                    output.close();
                }
                System.out.println("Completed.");
            }
        }
    }
}
