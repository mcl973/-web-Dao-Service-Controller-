/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: test2
 * Author:   Administrator
 * Date:     2020/02/16 14:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

import java.io.*;
import java.util.Stack;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class test2  extends ClassLoader {
        private String rootDir;
        public test2(String rootDir) {
            this.rootDir = rootDir;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] classData = getClassData(name);
            if (classData == null) {
                throw new ClassNotFoundException();
            } else {
                return defineClass(name, classData, 0, classData.length);
            }
        }

        private byte[] getClassData(String className) {
            String path = classNameToPath(className);
            try {
                InputStream ins = new FileInputStream(path);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int bytesNumRead = 0;
                while ((bytesNumRead = ins.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesNumRead);
                }
                ins.close();
                return baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private String classNameToPath(String className) {
            return rootDir + File.separatorChar
                    + className.replace('.', File.separatorChar) + ".class";
        }
}
