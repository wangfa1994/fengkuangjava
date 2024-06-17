package unit18_classload.c2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author alvin
 * @date 2020-05-04 15:03
 */
public class CompileClassLoader extends ClassLoader{
    // 读取一个文件的内容
    private byte[] getBytes(String filename) throws IOException {
        System.out.println("getBytes" + filename);
        File file  = new File(filename);
        long len = file.length();
        byte[] raw = new byte[(int)len];
        try(FileInputStream fin = new FileInputStream(file)){
            // 一次拂去 Class 文件的全部二进制数据
            int r = fin.read(raw);
            if(r != len){
                throw new IOException("无法读取全部文件：" +
                        r + " != " + len);
            }
            return  raw;
        }
    }
    // 定义编译指定 Java 文件的方法
    private boolean compile(String javaFile) throws IOException {
        System.out.println("CompileClassLoader:正在编译 " +
                javaFile + "...");
        // 调用系统的 javac 命令
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        try{
            // 其他线程都等待这个线程完成
            p.waitFor();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        // 获取 javac 线程的退出值
        int ret = p.exitValue();
        // 返回编译是否成功
        return ret == 0;
    }
    // 重写 ClassLoader 的 findClass 方法
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass" + name);
        Class clazz = null;
        // 将包路径中的点(.)替换成斜线
        String fileStub = name.replace(".", "/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);
        // 当指定 Java 源文件存在，且 class 文件不存在，或者 Java 源文件
        // 的修改时间比 Class 文件的修改时间晚，重新编译
        if(javaFile.exists() && (!classFile.exists() ||
                javaFile.lastModified() > classFile.lastModified())){
            try{
                // 如果编译失败，或者该 class 文件不存在
                if(!compile(javaFilename) || !classFile.exists()){
                    throw new ClassNotFoundException("ClassNotFoundException:" +
                            javaFilename);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // 如果 class 文件存在，系统负责将该文件转换成 class 对象
        if(classFile.exists()) {
            try {
                // 将 class 文件的二进制数据读入数组
                byte[] raw = getBytes(classFilename);
                // 调用 classLoader 的 defineClass 方法将二进制数据转换成 class 对象
                clazz = defineClass(name, raw, 0, raw.length);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        // 如果 clazz 为 null，表明加载失败，则抛出异常
        if(clazz == null){
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }
    // 定义一个主方法
    public static void main(String[] args) throws Exception {


        // 如果运行该程序时没有参数，即没有目标类
        if(args.length<1) {
            System.out.println("缺少目标类，请按如下格式运行 Java 源文件：");
            System.out.println("java CompileClassLoader ClassName");
        }
        // 第一个参数是需要运行的类
        String progClass = args[0];
        // 剩下的参数将作为运行目标类的参数
        // 将这些参数复制到一个新数组中
        String[] progArgs = new String[args.length -1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);
        CompileClassLoader ccl = new CompileClassLoader();
        // 加载需要运行的类
        Class<?> clazz = ccl.loadClass(progClass);
        // 获取需要运行的类的主方法
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        Object argsArray[] = {progArgs};
        main.invoke(null, argsArray);
    }
}
