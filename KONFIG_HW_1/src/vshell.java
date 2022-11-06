import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class vshell
{
    static Scanner input = new Scanner(System.in);
    static String arg = "start";
    static File directory = new File("");
    static ZipInputStream zin;
    private static String[] word;

    public static void StartOfFun()
    {
        System.out.print("["+directory.getName()+"]" + "$ ");
        arg = input.nextLine();
        word = arg.split(" ");
    }

    public static void myPWD()
    {
        System.out.println(directory.getAbsolutePath());
    }

    /*public static void myLS()
    {
        System.out.println(Arrays.toString(directory.list()));
    }
    public static void myLS_l()
    {
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
    }*/

    public static void myZipLS(ZipInputStream zin) throws IOException {
        ZipEntry entry;
        String name;
        long size;
        while((entry=zin.getNextEntry())!=null)
        {
            name = entry.getName(); // получим название файла
            size = entry.getSize();  // получим его размер в байтах
            System.out.printf("File name: %s \t File size: %d \n", name, size);

        }
    }

    static public void main(String[] args) throws IOException
    {
        directory = new File(args[0]);
        zin = new ZipInputStream(new FileInputStream(args[0]));
        if(directory.isDirectory()) System.out.println("\nIm dir\n");
        if(directory.isFile()) System.out.println("\nIm file\n");

        while(!arg.equals("end"))
        {
            StartOfFun();
            for (String s : word) {
                if (s.equals("pwd")) {
                    myPWD();
                    continue;
                }
                if (s.equals("ls")) {
                    myZipLS(zin);
                    continue;
                }
                if (s.equals("cd")) {
                    System.out.println("Start of session");
                    continue;
                }
                if (s.equals("cat")) {
                    System.out.println("Start of session");
                }
            }
        }
        System.out.println("End of session");
    }
}