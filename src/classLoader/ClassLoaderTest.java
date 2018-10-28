/**
 * 
 */
package classLoader;

import java.io.IOException;
import java.io.InputStream;

import Executor.ConcurrentStack;

/**
 * @author Administrator 自定义类加载器
 * 
 */
public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassLoader myLoader = new ClassLoader() {

			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
				InputStream inputStream = getClass().getResourceAsStream(fileName);
				if (inputStream == null) {
					return super.loadClass(name);
				}
				try {
					byte[] bs = new byte[inputStream.available()];
					inputStream.read(bs);
					return defineClass(name, bs, 0, bs.length);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new ClassNotFoundException();
				}
			}
		};
		try {
//			Object object = myLoader.loadClass("Executor.ConcurrentStack").newInstance();
			Object object = myLoader.loadClass("classLoader.HellWorld").newInstance();
			System.out.println(object.getClass());
			System.out.println(object instanceof classLoader.HellWorld);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
